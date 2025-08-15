package com.oracle.cli.view;

import com.oracle.cli.model.ScreenSelector;
import com.oracle.cli.util.exception.ExitException;
import org.springframework.lang.NonNull;

public abstract class ScreenBase {
    protected String title;

    protected ScreenBase(String title) {
        this.title = title;
    }

    public abstract boolean filter(ScreenSelector selector);
    protected abstract void render(StringBuilder builder);

    protected void hasExitCommand(@NonNull String value) {
        if (value.equals("0") || value.isBlank()) {
            throw new ExitException("On Exit Command");
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n# ").append(title).append("\n#\n");
        render(builder);

        return builder.toString();
    }
}
