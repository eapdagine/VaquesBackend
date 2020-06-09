package net.xaviersala.db;

import net.xaviersala.exceptions.VaquesException;
import net.xaviersala.model.Raça;
import net.xaviersala.model.Vaca;

import java.util.ArrayList;
import java.util.List;

public class MemoryVaquesDbRepository implements IVaquesDbRepository {

    public static List<Raça> races = new ArrayList<>() {
        {
            add(new Raça("Holstein-Friesian", 0.3));
            add(new Raça("Jersey", 0.1));
            add(new Raça("Simental", 0.05));
            add(new Raça("Ayshire", 0.12));
            add(new Raça("Guernsey", 0.09));
        }
    };

    public static List<Vaca> vaques = new ArrayList<>() {
        {
            {
                add(new Vaca("Toñi", 360.3, races.get(0)));
                add(new Vaca("Pepa", 250.25, races.get(1)));
                add(new Vaca("Flor", 400.5, races.get(2)));
                add(new Vaca("Maria", 180.7, races.get(0)));
                add(new Vaca("Blanca", 99.8, races.get(3)));
                add(new Vaca("Conxi", 201.7, races.get(0)));
                add(new Vaca("Guenya", 173.0, races.get(4)));
            }
        };
    };

    @Override
    public List<Vaca> getVaques(int quantes) throws VaquesException {
        return vaques.subList(0,quantes);
    }
}
