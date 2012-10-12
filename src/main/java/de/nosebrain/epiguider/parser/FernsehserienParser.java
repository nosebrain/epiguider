package de.nosebrain.epiguider.parser;

import java.io.IOException;

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
        try {
          season = new Season();
          final String seasonNrString = seasonDiv.text().trim().replace("Staffel", "").trim();
          season.setNumber(Integer.parseInt(seasonNrString));
          series.addSeason(season);
        } catch (final NumberFormatException e) {
          // maybe a specials section TODO: handle
          break; // TODO: quick fix
        }
      } else if (row.hasClass("ep-hover")) {
        final String test = row.select(".episodenliste-episodennummer").first().text();
        final int nr = Integer.parseInt(test);
        final String title = row.select(".episodenliste-titel").text();
        final Episode episode = new Episode();
        episode.setNumber(nr);
        episode.setTitle(title);
        season.addEpisode(episode);
      }
    }
    return series;
  }
}
