package com.example.my_spring_app.client;

import com.example.my_spring_app.model.OffsetTable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Component
public class GoogleSheetsClientReactive {

    private final WebClient client;

    private final String authorisation = "";
    private final String spreadsheetId = "1o6erA7L6jix_-kiMEhyZTi0-q5apw_5QQdj2-N__XX0";
    private final String sheetName = "Copy_of_Offset!";

    public GoogleSheetsClientReactive(WebClient.Builder builder) {
        this.client = builder.baseUrl("https://sheets.googleapis.com/v4/spreadsheets/").build();
    }

    public Mono<OffsetTable> getOffsetTable(String range) {
        return this.client.get().uri(uriBuilder -> uriBuilder
                    .path(spreadsheetId + "/values/" + sheetName + range)
                    .queryParam("majorDimension", "COLUMNS")
                    .queryParam("valueRenderOption", "UNFORMATTED_VALUE")
                .build())
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, authorisation)
                .retrieve()
                .bodyToMono(OffsetTable.class);
    }

    public Mono<OffsetTable> updateOffsetTable(String range) {
        Mono<OffsetTable> current = getOffsetTable(range);

        Mono<OffsetTable> newOt = current.map(x -> {
            x.setValues(addToBucket(x.getValues()[0][0]));
            return x;
        });
        return this.client.put().uri(uriBuilder -> uriBuilder
                    .path(spreadsheetId + "/values/" + sheetName + range)
                    .queryParam("includeValuesInResponse", "true")
                    .queryParam("valueInputOption", "USER_ENTERED")
                .build())
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, authorisation)
                .body(newOt, OffsetTable.class)
                .retrieve()
                .bodyToMono(OffsetTable.class);

    }

    private String[][] addToBucket(String value) {
        BigDecimal bd = new BigDecimal(value).add(BigDecimal.valueOf(150L));
        return new String[][]{{String.valueOf(bd)}};
    }

}
