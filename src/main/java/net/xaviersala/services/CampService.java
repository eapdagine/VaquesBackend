package net.xaviersala.services;

import net.xaviersala.db.IVaquesDbRepository;
import net.xaviersala.db.MemoryVaquesDbRepository;
import net.xaviersala.exceptions.VaquesException;
import net.xaviersala.model.Camio;
import net.xaviersala.model.Vaca;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.stop;

public class CampService {
    IVaquesDbRepository servei;

    List<Vaca> casa;
    List<Vaca> ciutat;
    Camio camio;

    public CampService() {
        this.servei = new MemoryVaquesDbRepository();
    }

    public CampService(IVaquesDbRepository servei) {
        this.servei = servei;
        init(6);
    }

    public void init(int numVaques) {
        try {
            casa = servei.getVaques(numVaques);
            camio = new Camio(1000);
            ciutat = new ArrayList<>();
        } catch (VaquesException e) {
            stop();
        }
    }

    public List<Vaca> getCasa() {
        return casa;
    }

    public List<Vaca> getCiutat() {
        return ciutat;
    }

    public List<Vaca> getCamio() {
        return camio.getVaques();
    }

    public double CamioACiutat() {
        double pes = camio.getPesActual();
        ciutat.addAll(camio.getVaques());
        camio.buida();
        return pes;
    }

    public boolean PosaVacaAlCamio(String nom) {
        Vaca vaca = casa
                .stream()
                .filter(n -> n.getNom().equals(nom))
                .findFirst()
                .get();
        casa.remove(vaca);
        return camio.EntraVaca(vaca);
    }

}
