package com.oracle.cli.view;

import com.oracle.cli.model.ScreenSelector;
import com.oracle.cli.service.ScreenService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class HomeView extends ScreenBase {
    private final ScreenService service;
    private final Scanner input;

    public HomeView(ScreenService service, Scanner input) {
        super("HOME");
        this.service = service;
        this.input = input;
    }

    @Override
    public boolean filter(ScreenSelector selector) {
        service.print(this);
        ScreenSelector option = getSelectorByAction();

        return switch (option) {
            case HOME -> true;
            case CONVERTER, CONVERSION_HISTORY -> service.show(option);
            default -> false;
        };
    }

    @Override
    protected void render(@NonNull StringBuilder builder) {
        builder
            .append("# 1 - Converter\n")
            .append("# 2 - Conversion History\n")
            .append("# 0 - Exit\n")
            .append("#\n# -> ");
    }

    private ScreenSelector getSelectorByAction() {
        int action = Integer.parseInt(input.nextLine());

        if (action == 0) {
            service.println("\n# Leaving...");
            return ScreenSelector.EXIT;
        }

        if (action >= ScreenSelector.values().length - 1) {
            service.println("\n# Invalid action.");
            return ScreenSelector.HOME;
        }

        return ScreenSelector.values()[action];
    }
}
