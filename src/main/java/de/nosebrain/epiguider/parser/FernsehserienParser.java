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
  private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_3) AppleWebKit/536.29.13 (KHTML, like Gecko) Version/6.0.4 Safari/536.29.13";
  private static final String URL = "https://www.fernsehserien.de/%s/episodenguide";
  private static final Pattern NUMBER_PATTERN = Pattern.compile("Staffel [^0-9]*([0-9]{1,2})[^0-9]*");
  
  @Override
  public Series parse(final String id) throws IOException {
    final String url = String.format(URL, id);
    final Document site = Jsoup.connect(url).userAgent(USER_AGENT).maxBodySize(0).get();

    final Series series = new Series();
    series.setName(site.select("li.infos h1").text());
    final Elements seasonTBodys = site.select("tbody[itemprop=\"season\"]");
    
    for (final Element seasonTBody : seasonTBodys) {
      final Elements seasonLink = seasonTBody.select("h2.header-2015 a");
      if (seasonLink.size() == 1) {
        final String seasonString = seasonLink.text().trim();
        final Matcher matcher = NUMBER_PATTERN.matcher(seasonString);
        if (matcher.find()) {
          final Season season = new Season();
          final String group = matcher.group(1);
          season.setNumber(Integer.parseInt(group));
          series.addSeason(season);
          
          final Elements episodeTrs = seasonTBody.select("tr[itemprop=\"episode\"]");
          for (final Element episodeTr : episodeTrs) {
            final String nrStr = episodeTr.select(".episodenliste-episodennummer").get(2).text();
            final int nr = Integer.parseInt(nrStr);
            final String title = episodeTr.select(".episodenliste-titel span[itemprop=\"name\"]").text();
            final Episode episode = new Episode();
            episode.setNumber(nr);
            episode.setTitle(title);
            season.addEpisode(episode);
          }
        }
      }
    }
    return series;
  }
}
