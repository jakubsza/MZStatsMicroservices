package com.szarawara.jakub.excelgenerator.excel;

import com.szarawara.jakub.excelgenerator.json.Clash;
import com.szarawara.jakub.excelgenerator.json.Result;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.List;

@Component
public class ExcelGenerator {

    private ExcelDataBuilder excelDataBuilder;

    public void generateExcel(List<Clash> clashes) throws IOException {
        this.excelDataBuilder = new ExcelDataBuilder();
        for (Clash clash : clashes) {
            fillData(clash);
        }
        excelDataBuilder.addPictures();
        excelDataBuilder.drawPictures();
        excelDataBuilder.autoSizeFirstColumn();
        saveExcel();
    }

    private void fillData(Clash clash) throws IOException {
        excelDataBuilder.createRow();
        String url = clash.tacticsUrl;
        excelDataBuilder.saveImage(url);
        Result result = clash.result;
        String opponentValue = clash.opponentTeamValue;
        excelDataBuilder.writeResultFull(result.homeTeam, result.awayTeam);
        excelDataBuilder.writeResult(result.result);
        excelDataBuilder.writeOpponentValue(opponentValue);
        excelDataBuilder.writeColour(result.colour);
        excelDataBuilder.writeOpponentColour(result.opponentColour);
        excelDataBuilder.writeMatchLink(result.matchUrl);
        excelDataBuilder.writeTactic(result.mentality, result.pressing);
        excelDataBuilder.writeOpponentTactic(result.opponentMentality, result.opponentPressing);
    }

    private void saveExcel() throws IOException {
        long timeStamp = Instant.now().toEpochMilli();
        try (FileOutputStream saveExcel = new FileOutputStream("result/stats_" + timeStamp + ".xlsx")) {
            excelDataBuilder.getWorkbook().write(saveExcel);
        }
    }
}