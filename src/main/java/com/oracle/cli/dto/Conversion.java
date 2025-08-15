package com.oracle.cli.dto;

import com.oracle.cli.model.CurrencyType;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.Map;

public record Conversion(
    CurrencyType source,
    CurrencyType target,
    Double rate,
    Double result,
    LocalDateTime timestamp
) {
    public Conversion(@NonNull Map<String, Object> json) {
        this(
            CurrencyType.valueOf((String) json.get("base_code")),
            CurrencyType.valueOf((String) json.get("target_code")),
            (Double) json.get("conversion_rate"),
            (Double) json.get("conversion_result"),
            LocalDateTime.now()
        );
    }
}
