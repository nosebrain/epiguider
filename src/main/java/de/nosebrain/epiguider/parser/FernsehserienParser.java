package de.nosebrain.epiguider.parser;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import de.nosebrain.epiguider.SeriesParser;
import de.nosebrain.epiguider.model.Episode;
import de.nosebrain.epiguider.model.Season;
import de.nosebrain.epiguider.model.Series;

@Component
public class FernsehserienParser implements SeriesParser {
  private static final String URL = "http://www.fernsehserien.de/%s/episodenguide";
  private static final Pattern NUMBER_PATTERN = Pattern.compile("[^0-9]*([0-9]{1,2})[^0-9]*");
  
  @Override
  public Series parse(final String id) throws IOException {
    final String url = String.format(URL, id);
    final Document site = Jsoup.connect(url).get();
    
    final Series series = new Series();
    series.setName(site.select(".serie-titel").text());
    final Elements rows = site.select(".serie-content-left table tr");
    Season season = null;
    for (final Element row : rows) {
      final Elements seasonDiv = row.select("div.gray-bar-header-left");
      if (seasonDiv.size() == 1) {
        final String seasonString = seasonDiv.text().trim();
        final Matcher matcher = NUMBER_PATTERN.matcher(seasonString);
        if (matcher.find()) {
          season = new Season();
          final String group = matcher.group(1);
          season.setNumber(Integer.parseInt(group));
          series.addSeason(season);
        } else {
          // TODO: handle case?
        }
      } else if (row.hasClass("ep-hover")) {
        final Elements epiNumberSelect = row.select(".episodenliste-episodennummer");
        if (epiNumberSelect.size() >= 3) {
          final String test = epiNumberSelect.get(2).text();
          final int nr = Integer.parseInt(test);
          final String title = row.select(".episodenliste-titel").text();
          final Episode episode = new Episode();
          episode.setNumber(nr);
          episode.setTitle(title);
          season.addEpisode(episode);
        } else {
          // TODO: add specials
        }
      }
    }
    return series;
  }
}