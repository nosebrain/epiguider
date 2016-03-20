package de.nosebrain.epiguider.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import de.nosebrain.epiguider.SeriesParser;
import de.nosebrain.epiguider.Store;
import de.nosebrain.epiguider.model.Series;
import de.nosebrain.epiguider.service.EpiguiderLogic;

@Controller
public class MainController {
  
  @Autowired
  private Map<String, SeriesParser> parsers;
  
  @Autowired
  private EpiguiderLogic epiguiderLogic;

  @RequestMapping(value = "/")
  public String home(final Model model) {
    final List<Store> stores = this.epiguiderLogic.getSavedSeries();
    model.addAttribute("stores", stores);
    final Store store = new Store();
    model.addAttribute("store", store);
    model.addAttribute("parsers", this.parsers.keySet());
    return "home";
  }
  
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public String addParser(final Store store) {
    this.epiguiderLogic.add(store);
    
    return "redirect:/";
  }
  
  @RequestMapping(value = "/remove", method = RequestMethod.POST)
  public String removeParser(final Store store) {
    this.epiguiderLogic.remove(store);
    
    return "redirect:/";
  }
  
  @RequestMapping(value = "/{series}")
  public String series(@RequestParam("seriesId") final String seriesId, @RequestParam("parserId") final String parser, final Model model) throws IOException {
    final Series series = this.parsers.get(parser).parse(seriesId);
    final Store store = this.epiguiderLogic.getStore(seriesId, parser);
    series.setName(store.getName());
    model.addAttribute("series", series);
    return "series";
  }

}
