package net.xaviersala.model;

public class Vaca {
    private String Nom;
    private double pes;
    private Raça raça;

    public Vaca(String nom, double pes, Raça raça) {
        this.Nom = nom;
        this.pes = pes;
        this.raça = raça;
    }

    public String getNom() {
        return Nom;
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
                "nom='" + Nom + '\'' +
                ", pes=" + pes +
                ", raça=" + raça.getNom() +
                '}';
    }
}
