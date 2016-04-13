package com.searchengine.logic;

import com.dlc.searchengine.data.access.DBAdministrator;
import java.util.*;


@SuppressWarnings("rawtypes")
public class Vocabulary {

    private HashMap<String, Word> map;
    private DBAdministrator bdmanager = DBAdministrator.getDBAdministrator();
    private Searcher searcher;

    public Vocabulary(Searcher c) {
        map = new HashMap<>(300000);

        searcher = c;
    }

    
	public Collection Values() {
        return map.values();
    }

    public String toString() {
        Collection coleccion = map.values();
        Iterator i = coleccion.iterator();
        StringBuilder sb = new StringBuilder();
        while (i.hasNext()) {

            sb.append(i.next().toString()).append("\n");
        }
        return sb.toString();
    }

    public void registrarInicioPersistencia() {
//        manejador.registrarInicioPersist();
    }

    public void registrarAvancePersistencia() {
       // manejador.registrarAvancePersist();
    }

    public void registrarFinPersistencia() {
       // manejador.registrarFinPersist();

    }

    public void registrarFinPersitiendoArchivos(String estado) {
       // manejador.cambiarEstadoArchivoPersistido(estado);
        //manejador.limpiarProceso();
    }

    public void agregar(String cadena, Document doc) {
        Word palabra = map.get(cadena);
        if (palabra == null) {
            palabra = Word.producirPalabra(cadena);
            if (palabra != null) {
                map.put(cadena, palabra);
                palabra.addFile(doc);

            }

        } else {
            palabra.repeat();
            palabra.addFile(doc);
        }

    }

    public int getContador() {
        return this.Values().size();
    }

    public void clear() {
        map = null;
        map = new HashMap<>(300000);

    }

    /*    public void persistirPalabras() {
    bdmanager.persistenciaDePalabras(map.values().iterator(), this, getContador());
    }*/

}
