package kz.iitu.CovidTracker.controller;

import kz.iitu.CovidTracker.model.Country;
import kz.iitu.CovidTracker.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
