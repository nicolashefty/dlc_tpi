package com.searchengine.logic;

import com.searchengine.data.access.DBAdministrator;
import java.util.*;

@SuppressWarnings("rawtypes")
public class Vocabulary {

    private HashMap<Integer, Term> map;

    public Vocabulary() {
        map = new HashMap<>(300000);
    }

    public Collection Values() {
        return map.values();
    }

    public void agregar(String cadena, Document doc) 
    {
        Term palabra = map.get(cadena.hashCode());
        if (palabra == null) 
        {
            palabra = new Term(cadena);
            map.put(cadena.hashCode(), palabra);
        } 
        else 
        {
            if(palabra.isSameDoc(doc))
            palabra.repeat();
//            palabra.addFile(doc);
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
