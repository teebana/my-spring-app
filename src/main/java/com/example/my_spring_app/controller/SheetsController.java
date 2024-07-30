package com.example.my_spring_app.controller;

import com.example.my_spring_app.client.GoogleSheetsClientReactive;
import com.example.my_spring_app.model.OffsetTable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class SheetsController {

    private final GoogleSheetsClientReactive googleSheetsClientReactive;

    public SheetsController(GoogleSheetsClientReactive googleSheetsClientReactive) {
        this.googleSheetsClientReactive = googleSheetsClientReactive;
    }

    @GetMapping("/sheets")
    public Mono<OffsetTable> getOffsetTable() {
        return googleSheetsClientReactive.getOffsetTable("A1:D32");
    }

    @PutMapping("/sheets-update")
    public Mono<OffsetTable> updateOffsetTable() {
        return googleSheetsClientReactive.updateOffsetTable("B3");
    }

}
