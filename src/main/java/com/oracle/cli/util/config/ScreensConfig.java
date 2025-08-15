package com.oracle.cli.util.config;

import com.oracle.cli.model.ScreenSelector;
import com.oracle.cli.service.ScreenService;
import com.oracle.cli.view.ConverterView;
import com.oracle.cli.view.HistoryView;
import com.oracle.cli.view.HomeView;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScreensConfig {
    private final ScreenService service;
    private final HomeView home;
    private final ConverterView convert;
    private final HistoryView history;

    public ScreensConfig(
        ScreenService service,
        HomeView home,
        ConverterView convert,
        HistoryView history
    ) {
        this.service = service;
        this.home = home;
        this.convert = convert;
        this.history = history;
    }

    @PostConstruct
    public void setupScreens() {
        service.setScreen(ScreenSelector.HOME, home);
        service.setScreen(ScreenSelector.CONVERTER, convert);
        service.setScreen(ScreenSelector.CONVERSION_HISTORY, history);
    }
}
