package com.likehero.like_hero_to_zero.model;

import jakarta.persistence.*;

@Entity
@Table(name = "co2_emission")
public class Co2Emission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @Column(nullable = false)
    private Integer year;

    @Column(name = "co2_kt")
    private Double co2Kt;

    // Getter und Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Country getCountry() { return country; }
    public void setCountry(Country country) { this.country = country; }

    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }

    public Double getCo2Kt() { return co2Kt; }
    public void setCo2Kt(Double co2Kt) { this.co2Kt = co2Kt; }
}