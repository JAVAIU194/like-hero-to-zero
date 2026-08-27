package com.likehero.like_hero_to_zero.controller;

import com.likehero.like_hero_to_zero.model.Co2Emission;
import com.likehero.like_hero_to_zero.model.Country;
import com.likehero.like_hero_to_zero.repository.Co2EmissionRepository;
import com.likehero.like_hero_to_zero.repository.CountryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/scientist")
public class ScientistController {

    private final CountryRepository countryRepository;
    private final Co2EmissionRepository co2EmissionRepository;

    public ScientistController(CountryRepository countryRepository,
                               Co2EmissionRepository co2EmissionRepository) {
        this.countryRepository = countryRepository;
        this.co2EmissionRepository = co2EmissionRepository;
    }

    @GetMapping
    public String scientistPage(Model model) {
        model.addAttribute("countries", countryRepository.findAll());
        return "scientist";
    }

    @PostMapping("/add")
    public String addEmission(@RequestParam String countryName,
                              @RequestParam Integer year,
                              @RequestParam Double co2Kt,
                              Model model) {
        Country country = countryRepository.findByNameIgnoreCase(countryName)
                .orElseGet(() -> {
                    Country newCountry = new Country();
                    newCountry.setName(countryName);
                    return countryRepository.save(newCountry);
                });

        Co2Emission emission = new Co2Emission();
        emission.setCountry(country);
        emission.setYear(year);
        emission.setCo2Kt(co2Kt);
        co2EmissionRepository.save(emission);

        model.addAttribute("success", "Daten erfolgreich gespeichert!");
        model.addAttribute("countries", countryRepository.findAll());
        return "scientist";
    }
}