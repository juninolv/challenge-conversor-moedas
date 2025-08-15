package com.oracle.cli.service;

import com.oracle.cli.dto.Conversion;
import com.oracle.cli.model.CurrencyType;
import com.oracle.cli.util.Http;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ConverterService {
    private final GsonJsonParser parser;
    private final List<Conversion> history = new ArrayList<>();

    @Value("${api.base.url}")
    private String url;

    public ConverterService(GsonJsonParser parser) {
        this.parser = parser;
    }

    public Conversion convert(Double amount, CurrencyType... types) {
        return sendRequest(this.buildUri(amount, types));
    }

    public List<Conversion> history(int limit) {
        return history.stream()
            .sorted(Comparator.comparing(Conversion::timestamp).reversed())
            .limit(limit)
            .toList();
    }

    @NonNull
    private String buildUri(@NonNull Double amount, @NonNull CurrencyType... types) {
        return String.format(url, types[0], types[1], amount);
    }

    @NonNull
    private Conversion sendRequest(String uri) {
        String response = null;

        try {
            response = Http.get(uri);
        } catch (ExecutionException | InterruptedException exception) {
            System.out.println("\n# Server offline or invalid resource. Leaving...");
            Thread.currentThread().interrupt();
        }

        Conversion result = new Conversion(parser.parseMap(response));
        history.add(result);

        return result;
    }
}
