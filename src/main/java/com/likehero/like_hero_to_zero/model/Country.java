package com.likehero.like_hero_to_zero.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "iso_code", length = 3)
    private String isoCode;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private List<Co2Emission> emissions;

    // Getter und Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getIsoCode() { return isoCode; }
    public void setIsoCode(String isoCode) { this.isoCode = isoCode; }

    public List<Co2Emission> getEmissions() { return emissions; }
    public void setEmissions(List<Co2Emission> emissions) { this.emissions = emissions; }
}