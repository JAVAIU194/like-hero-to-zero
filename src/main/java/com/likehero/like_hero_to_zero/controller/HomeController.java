package com.likehero.like_hero_to_zero.controller;

import com.likehero.like_hero_to_zero.model.Co2Emission;
import com.likehero.like_hero_to_zero.model.Country;
import com.likehero.like_hero_to_zero.repository.Co2EmissionRepository;
import com.likehero.like_hero_to_zero.repository.CountryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class HomeController {

    private final CountryRepository countryRepository;
    private final Co2EmissionRepository co2EmissionRepository;

    public HomeController(CountryRepository countryRepository,
                          Co2EmissionRepository co2EmissionRepository) {
        this.countryRepository = countryRepository;
        this.co2EmissionRepository = co2EmissionRepository;
    }

    @GetMapping("/")
    public String home(@RequestParam(required = false) String country, Model model) {
        if (country != null && !country.isBlank()) {
            Optional<Country> found = countryRepository.findByNameIgnoreCase(country);
            if (found.isPresent()) {
                Optional<Co2Emission> emission =
                        co2EmissionRepository.findTopByCountryOrderByYearDesc(found.get());
                emission.ifPresent(e -> model.addAttribute("emission", e));
                model.addAttribute("countryName", found.get().getName());
            } else {
                model.addAttribute("error", "Land nicht gefunden: " + country);
            }
        }
        return "index";
    }
}