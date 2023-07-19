package com.szarawara.jakub.statscollector.rest;

import com.szarawara.jakub.statscollector.json.Clash;
import com.szarawara.jakub.statscollector.stats.StatsCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

@RestController
public class StatsService {

    @Autowired
    private StatsCollector statsCollector;

    @PostMapping(path = "/stats")
    public List<Clash> stats(@RequestBody String body) throws IOException, ParserConfigurationException, InterruptedException, SAXException {
        return statsCollector.getStats(body);
    }

}