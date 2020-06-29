package net.xaviersala.model;

public class Raça {
    public static final String DESCONEGUDA = "Desconeguda";
    
	private String nom;
    private double litresPerKg;

    public Raça(String nom, double litresPerKg) {
    	
    	this.nom = (nom == null || nom.isBlank() || nom.isEmpty()) ? DESCONEGUDA : nom;    	
    	this.litresPerKg = (litresPerKg < 0) ? 0 : litresPerKg; 
    		
    }

    public double getLitresPerKg() {
        return litresPerKg;
    }

    public String getNom() {
        return nom;
    }

}
