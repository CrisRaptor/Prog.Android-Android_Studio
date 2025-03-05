package com.example.apicat;

public class BreedData {

    private String name, temperament, origin, description, life_span, weight;
    private String vetstreet_url;

    public BreedData(String name, String temperament, String origin, String description, String life_span, String weight, String vetstreet_url) {
        this.name = name;
        this.temperament = temperament;
        this.origin = origin;
        this.description = description;
        this.life_span = life_span;
        this.weight = weight;
        this.vetstreet_url = vetstreet_url;
    }

    public String getName() {
        return name;
    }

    public String getTemperament() {
        return temperament;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDescription() {
        return description;
    }

    public String getLife_span() {
        return life_span;
    }

    public String getWeight() {
        return weight;
    }

    public String getVetstreet_url() {
        return vetstreet_url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLife_span(String life_span) {
        this.life_span = life_span;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setVetstreet_url(String vetstreet_url) {
        this.vetstreet_url = vetstreet_url;
    }
}
