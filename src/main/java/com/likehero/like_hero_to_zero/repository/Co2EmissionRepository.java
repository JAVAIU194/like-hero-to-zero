package com.likehero.like_hero_to_zero.repository;

import com.likehero.like_hero_to_zero.model.Co2Emission;
import com.likehero.like_hero_to_zero.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface Co2EmissionRepository extends JpaRepository<Co2Emission, Long> {
    Optional<Co2Emission> findTopByCountryOrderByYearDesc(Country country);
}