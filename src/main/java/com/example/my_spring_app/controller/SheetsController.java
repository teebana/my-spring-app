package com.example.my_spring_app.controller;

import com.example.my_spring_app.model.OffsetTable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SheetsController {

    @GetMapping("/sheets")
    public OffsetTable getOffsetTable() {
        return new OffsetTable();
    }

}
