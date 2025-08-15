package com.oracle.cli.view;

import com.oracle.cli.dto.Conversion;
import com.oracle.cli.model.ScreenSelector;
import com.oracle.cli.service.ConverterService;
import com.oracle.cli.service.ScreenService;
import com.oracle.cli.util.exception.ExitException;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class HistoryView extends ScreenBase {
    private final Scanner input;
    private final ScreenService screenService;
    private final ConverterService converterService;

    public HistoryView(
        ScreenService screenService,
        ConverterService converterService,
        Scanner input
    ) {
        super("CONVERSION HISTORY");
        this.screenService = screenService;
        this.converterService = converterService;
        this.input = input;
    }

    @Override
    public boolean filter(ScreenSelector selector) {
        screenService.println(this);
        screenService.skip();

        try {
            int limit = getLimit();

            screenService.println("\n# Fetching...");
            showResult(converterService.history(limit));
        } catch (ExitException exception) {
            screenService.println("\n# Leaving...");
        } catch (NumberFormatException exception) {
            screenService.println("\n# Value must be digits, try again...");
        }

        return true;
    }

    @Override
    protected void render(@NonNull StringBuilder builder) {
        builder
            .append("# 0 - Exit");
    }

    private int getLimit() {
        screenService.print("# Set LIMIT -> ");
        String value = input.nextLine();
        hasExitCommand(value);

        return Integer.parseInt(value);
    }

    private void showResult(@NonNull List<Conversion> conversions) {
        for (int i = 0; i < conversions.size(); i++) {
            Conversion conversion = conversions.get(i);

            screenService.print(String.format("%n# Index: #%s", i + 1));
            screenService.print(String.format("%n# Conversion: %s -> %s", conversion.source(), conversion.target()));
            screenService.print(String.format("%n# Rate: %s", conversion.rate()));
            screenService.print(String.format("%n# Result: %s", conversion.result()));
            screenService.print(String.format("%n# Date: %s", conversion.timestamp()));
            screenService.skip();
        }
    }
}
