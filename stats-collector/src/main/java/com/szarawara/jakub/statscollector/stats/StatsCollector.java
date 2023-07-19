package com.szarawara.jakub.statscollector.stats;

import com.szarawara.jakub.statscollector.json.Clash;
import com.szarawara.jakub.statscollector.json.Result;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class StatsCollector {

    public List<Clash> getStats(String body) throws IOException, ParserConfigurationException, SAXException, InterruptedException {
        List<String> matchesId = getMatchIdsFromBody(body);
        List<Clash> clashes = new ArrayList<>();
        for (String matchId : matchesId) {
            clashes.add(fillData(matchId));
        }
        return clashes;
    }

    public List<String> getMatchIdsFromBody(String data) {
        List<String> matchIds = new ArrayList<>();
        Pattern pattern = Pattern.compile("mid=(.*?)&tid", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(data);
        while (matcher.find()) {
            matchIds.add(matcher.group(1));
        }
        return matchIds;
    }

    public Clash fillData(String matchId) throws IOException, ParserConfigurationException, SAXException, InterruptedException {
        Clash clash = new Clash();
        clash.tacticsUrl = "https://www.managerzone.com/dynimg/pitch.php?match_id=" + matchId;
        MatchResult matchResult = new MatchResult(matchId);
        Result result = matchResult.getMatch();
        Value value = new Value(result.opponentId);
        clash.opponentTeamValue = value.getTeamValue();
        clash.result = result;
        return clash;
    }
}
