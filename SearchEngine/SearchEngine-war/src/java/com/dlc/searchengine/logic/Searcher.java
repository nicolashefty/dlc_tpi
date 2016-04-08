package com.dlc.searchengine.logic;

import com.dlc.searchengine.persistency.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Searcher {

    private DBAdministrator dbAdm = DBAdministrator.getDBAdministrator();
    private Vocabulary mapapalabras;
    private FileList conjuntoarchivos;
    public Searcher() {
        mapapalabras = new Vocabulary(this);
        conjuntoarchivos = new FileList(this);
      
    }

    public void cargarArchivos(String path) {
        if (!path.isEmpty()) {
            conjuntoarchivos.addFile(path);
        }
    }

    public void procesarArchivos() {

        Iterator<Document> k = conjuntoarchivos.getIteratorDocumentos();
        int archleidos = 0;
        int archtotal = conjuntoarchivos.count();

        
        while (k.hasNext()) {

            archleidos++;
            
            Document file = new Document(k.next().getPath());// crea documento del iterador
            //frame.cambiarEstadoArchivo(file.getPath(), "Procesando...");
            File archivo = new File(file.getPath());

            try (Scanner sc = new Scanner(archivo);) {
                
                Pattern patron = Pattern.compile("-{2,}|[^a-z0-9A-ZáéíóúÁÉÍÓÚñÑüÜ-]");
                sc.useDelimiter(patron);

                while (sc.hasNext()) {
                    mapapalabras.agregar(sc.next(), file);

                }

            } catch (FileNotFoundException ex) {
                System.out.println("No existe el archivo de entrada");
                System.out.println("Error " + ex.getMessage());
            }

            
        }
        
        /*conjuntoarchivos.persistirArchivos();*/
        /*mapapalabras.persistirPalabras();*/
        mapapalabras.clear();
        conjuntoarchivos.clear();

    }

    /*    public String buscarPalabra(String cadena) {
    return dbAdm.obtenerAparicionDeBD(cadena).toString();
    
    }*/
    private int calcularProgreso(int docstotal, int docsleidos) {
        int progreso = (int) ((docsleidos / (float) docstotal) * 100);
        return progreso;
    }

    /* public void recuperarArchivos() {
    
    conjuntoarchivos.recuperarDocs();
    }*/
    
    public void deleteAll()
    {
          dbAdm.deleteAll();
    }

}
