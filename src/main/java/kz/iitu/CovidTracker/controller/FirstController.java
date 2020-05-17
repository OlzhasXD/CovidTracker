package kz.iitu.CovidTracker.controller;

import kz.iitu.CovidTracker.model.Country;
import kz.iitu.CovidTracker.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FirstController {

    @Autowired
    private DataService dataService;

    @GetMapping("/")
    public String homePage(Model model) {
        List<Country> temp = dataService.getAllCountry();
        int total = 0;
        for(Country country : temp) {
            total += country.getNumberOfCases();
        }
        int difference = 0;
        for(Country country : temp) {
            difference += country.getDifference();
        }
        model.addAttribute("countries", dataService.getAllCountry());
        model.addAttribute("total", total);
        model.addAttribute("difference", difference);
        return "homePage";
    }

    @GetMapping("/search")
    public String search(@RequestParam String filter, Model model) {
        List<Country> temp = dataService.getAllCountry();
        List<Country> countryFilter = new ArrayList<>();
        int total = 0, difference = 0;
        for(Country country : temp) {
            if(country.getCountry().equals(filter) || country.getState().equals(filter)) {
                countryFilter.add(country);
                total += country.getNumberOfCases();
                difference += country.getDifference();
            }
        }
        model.addAttribute("countries", countryFilter);
        model.addAttribute("total", total);
        model.addAttribute("difference", difference);
        return "homePage";
    }
}
