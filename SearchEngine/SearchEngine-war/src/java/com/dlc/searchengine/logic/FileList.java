
package com.dlc.searchengine.logic;

import com.dlc.searchengine.persistency.*;
import java.util.*;


public class FileList {

    private LinkedList<Document> list;
    private DBAdministrator dbAdm = DBAdministrator.getDBAdministrator();
    private Searcher searcher;
    private LinkedList<Document> dbDocs;

    public FileList(Searcher m) {
        list = new LinkedList<>();
        searcher = m;

    }

    public int count() {
        return list.size();
    }

   
    public void addFile(String path) {

        if (path == null) {
            return;
        }
        Iterator<Document> i = dbDocs.iterator();
        Document d = new Document(path);

        while (i.hasNext()) {

            if (i.next().equals(d)) {
                //manejador.cambiarEstadoArchivo(d.getPath(), "Error: Archivo ya almacenado");
                return;
            }

        }
        if (!this.contieneArchivo(d)) {

            list.add(d);
            //manejador.cambiarEstadoArchivo(d.getPath(), "Listo");
        } else {
            //manejador.cambiarEstadoArchivo(d.getPath(), "Error: Archivo duplicado");
        }

    }

    private boolean contieneArchivo(Document doc) {
        if (list.isEmpty()) {
            return false;
        }
        if (list.getLast().equals(doc)) {
            return true;
        }
        else
        {
	        for (Document d : list) {
	            if (d.equals(doc)) {
	                return true;
	            }
	        }
        }
        return false;
    }

    public Iterator<Document> getIteratorDocumentos() {
        return list.iterator();
    }

    /*public void persistirArchivos() {
    bdAdm.persistenciaDeDocumentos(lista.iterator());
    }*/

    public void clear() {
        list = null;
        list = new LinkedList<>();
        dbDocs = null;
        dbDocs = new LinkedList<>();
    }

    /*  public void recuperarDocs() {
    docsbd = bdAdm.getDocumentsFromDB();
    }*/
}
