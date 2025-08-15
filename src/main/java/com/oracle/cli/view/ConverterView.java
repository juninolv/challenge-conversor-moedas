package com.oracle.cli.view;

import com.oracle.cli.dto.Conversion;
import com.oracle.cli.model.CurrencyType;
import com.oracle.cli.model.ScreenSelector;
import com.oracle.cli.service.ConverterService;
import com.oracle.cli.service.ScreenService;
import com.oracle.cli.util.exception.ExitException;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Scanner;

@Component
public class ConverterView extends ScreenBase {
    private final Scanner input;
    private final ScreenService screenService;
    private final ConverterService converterService;

    public ConverterView(
        ScreenService screenService,
        ConverterService converterService,
        Scanner input
    ) {
        super("CURRENCY CONVERTER");
        this.screenService = screenService;
        this.converterService = converterService;
        this.input = input;
    }

    @Override
    public boolean filter(ScreenSelector selector) {
        screenService.print(this);
        screenService.skip();

        try {
            CurrencyType source = getCurrencyType("source");
            CurrencyType target = getCurrencyType("target");
            Double amount = getAmount();

            screenService.println("\n# Fetching...");
            showResult(converterService.convert(amount, source, target));
        } catch (ExitException exception) {
            screenService.println("\n# Leaving...");
        } catch (NumberFormatException exception) {
            screenService.println("\n# Value must be digits, try again...");
        } catch (IllegalArgumentException exception) {
            screenService.println("\n# Currency Type not found, try again...");
        }

        return true;
    }

    @Override
    protected void render(@NonNull StringBuilder builder) {
        builder
            .append("# Type - ")
            .append(Arrays.toString(CurrencyType.values()))
            .append("\n#\n# 0 - Exit\n");
    }

    private CurrencyType getCurrencyType(String type) {
        screenService.print(String.format("# Enter %S -> ", type));
        String value = input.nextLine().toUpperCase();
        hasExitCommand(value);

        return CurrencyType.valueOf(value);
    }

    private double getAmount() {
        screenService.print("# Set AMOUNT -> ");
        String value = input.nextLine().replace(",", ".");
        hasExitCommand(value);

        return Double.parseDouble(value);
    }

    private void showResult(@NonNull Conversion conversion) {
        screenService.print(String.format("%n# Conversion: %s -> %s", conversion.source(), conversion.target()));
        screenService.print(String.format("%n# Rate: %s", conversion.rate()));
        screenService.print(String.format("%n# Result: %s", conversion.result()));
        screenService.skip();
    }
}
