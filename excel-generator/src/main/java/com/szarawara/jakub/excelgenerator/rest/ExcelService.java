package com.szarawara.jakub.excelgenerator.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.szarawara.jakub.excelgenerator.excel.ExcelGenerator;
import com.szarawara.jakub.excelgenerator.json.Clash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class ExcelService {

    @Autowired
    private ExcelGenerator excelGenerator;
    private final ObjectMapper objectMapper;

    @Autowired
    public ExcelService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostMapping(path = "/generateExcel")
    public void generateExcel(@RequestBody String body) throws IOException {
        List<Clash> clashes = objectMapper.readValue(body, new TypeReference<>() {});
        excelGenerator.generateExcel(clashes);
    }
}
