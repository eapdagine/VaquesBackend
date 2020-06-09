package net.xaviersala.model;

public class Raça {
    private String nom;
    private double litresPerKg;

    public Raça(String nom, double litresPerKg) {
        this.nom = nom;
        this.litresPerKg = litresPerKg;
    }

    public double getLitresPerKg() {
        return litresPerKg;
    }

    public String getNom() {
        return nom;
    }

}
