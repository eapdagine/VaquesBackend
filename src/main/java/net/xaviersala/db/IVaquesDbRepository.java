package net.xaviersala.db;

import net.xaviersala.exceptions.VaquesException;
import net.xaviersala.model.Vaca;

import java.util.List;

public interface IVaquesDbRepository {

        List<Vaca> getVaques(int quantes) throws VaquesException;

}
