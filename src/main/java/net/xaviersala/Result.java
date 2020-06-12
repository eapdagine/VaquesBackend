package net.xaviersala;

public class Result {
    public String resultat;

    public Result() {}

    public Result(String missatge) {
        resultat = missatge;
    }
    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }
}
