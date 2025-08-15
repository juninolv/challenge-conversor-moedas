package com.oracle.cli.service;

import com.oracle.cli.model.ScreenSelector;
import com.oracle.cli.view.ScreenBase;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.Map;

@Service
public class ScreenService {
    private final Map<ScreenSelector, ScreenBase> screens = new EnumMap<>(ScreenSelector.class);

    public void setScreen(ScreenSelector selector, ScreenBase screen) {
        screens.putIfAbsent(selector, screen);
    }

    public boolean run() {
        return this.show(ScreenSelector.HOME);
    }

    public boolean show(ScreenSelector selector) {
        ScreenBase screen = screens.get(selector);

        if (screen == null) {
            return false;
        }

        return screen.filter(selector);
    }

    public void print(Object screen) {
        System.out.print(screen);
    }

    public void println(Object value) {
        System.out.println(value);
    }

    public void skip() {
        System.out.println();
    }
}
