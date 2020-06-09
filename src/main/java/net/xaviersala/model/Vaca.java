package net.xaviersala.model;

public class Vaca {
    private String nom;
    private double pes;
    private Raça raça;

    public Vaca(String nom, double pes, Raça raça) {
        this.nom = nom;
        this.pes = pes;
        this.raça = raça;
    }

    public String getNom() {
        return nom;
    }

    public double getPes() {
        return pes;
    }

    public double getLitres() {
        return pes * raça.getLitresPerKg();
    }

    @Override
    public String toString() {
        return "Vaca{" +
                "nom='" + nom + '\'' +
                ", pes=" + pes +
                ", raça=" + raça.getNom() +
                '}';
    }
}
