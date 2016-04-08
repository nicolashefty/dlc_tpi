
package com.dlc.searchengine.persistency;

import java.sql.*;
import java.util.*;

import com.dlc.searchengine.logic.*;

/**
 * This class provides the connection to the DB. Implements the Singleton
 * Pattern so you should obtain the instance from <code>getDBAdministrator()</code>.
 * @author Nicolas Hefty
 */
public class DBAdministrator {

    private static DBAdministrator singleton;
    private Connection connection = null;
    private Statement statement = null;
    private String query = "";
    private ResultSet rs = null;
    private PreparedStatement pioi = null;
    private PreparedStatement pior = null;

    private DBAdministrator() {
        String error = connect();
        if (!error.equals("")) {
            System.out.println("" + error);
        }
    }

    private String connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:.\\com\\dlc\\searchengine\\database\\vocabulary");
        } catch (SQLException ex) {
            return "Error " + ex.getMessage();
        } catch (ClassNotFoundException ex) {
            return "Class Error " + ex.getMessage();
        }
        return "";
    }

    /**
     * Singleton Pattern
     * @return the instance.
     */
    public static DBAdministrator getDBAdministrator() {
        if (singleton != null) {
            return singleton;
        } else {
            singleton = new DBAdministrator();
            return singleton;
        }
    }

   
    public LinkedList<Word> getWordsFromDB() {
        statement = null;
        rs = null;
        Word pal;
        LinkedList<Word> list = new LinkedList<>();

        query = "select p.cadena as 'Palabra', p.repeticiones as 'Repeticiones', count(pxd.idDoc) as 'Aparicion' ";
        query += "from Palabra as p join PalabraXDocumento as pxd on (p.cadena= pxd.cadena) ";
        query += "group by p.cadena, p.repeticiones";

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                pal = Word.producirPalabra(rs.getString("Palabra"));
                pal.setTermFreq(rs.getInt("Repeticiones"));

                pal.setFilesSize(rs.getInt("Aparicion"));

                list.add(pal);

            }

        } catch (SQLException ex) {

            System.out.println("Error " + ex.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    System.out.println("Error " + ex.getMessage());
                }
            }

        }

        return list;

    }

 
    public Word obtenerAparicionDeBD(String s) {
        statement = null;
        Word pal = null;
        Document d;
        query = "select d.link as 'link', pxd.cadena as 'cadena', p.repeticiones as 'repeticiones' ";
        query += "from PalabraXDocumento as pxd join Documento as d on (pxd.idDoc = d.idDoc) ";
        query += "join Palabra as p on (pxd.cadena = p.cadena) ";
        query += "where pxd.cadena like '" + s + "'";
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);

            while (rs.next()) {
                if (null == pal) {
                    pal = Word.producirPalabra(rs.getString("cadena"));
                    pal.setTermFreq(rs.getInt("repeticiones"));
                }
                d = new Document(rs.getString("link"));

                pal.addFile(d);
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {

                    System.out.println("Error " + ex.getMessage());
                }
            }

        }

        return pal;

    }

    public void deleteAll() {

        statement = null;

        try {
            query = "Delete from Palabra";
            statement = connection.createStatement();
            statement.executeUpdate(query);

            query = "Delete from Documento";
            statement = connection.createStatement();
            statement.executeUpdate(query);

            query = "Delete from PalabraXDocumento";
            statement = connection.createStatement();
            statement.executeUpdate(query);

        } catch (SQLException ex) {

            System.out.println("Error " + ex.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {

                    System.out.println("Error " + ex.getMessage());
                }
            }
        }

    }

    
    public void persistenciaDeDocumentos(Iterator<Document> it) {

        if (it == null) {
            return;
        }

        try {
            connection.setAutoCommit(false);
            pioi = connection.prepareStatement("Insert or ignore into Documento (link) values (?)");
            while (it.hasNext()) {
                String enlace = it.next().getPath();
                pioi.setString(1, enlace);
                pioi.executeUpdate();

            }
            connection.commit();
            connection.setAutoCommit(true);

        } catch (SQLException ex) {

            System.out.println("Error " + ex.getMessage());
            try {
                connection.rollback();
            } catch (SQLException e) {

                System.out.println("Error " + e.getMessage());

            }

        }

    }

  
    public void persistenciaDePalabras(Iterator<Word> it, Vocabulary mapaPal, int cont) {

        Iterator<Document> jIt;
        String s;
        String enlace;

        if (it == null) {
            return;
        }

        try {
            connection.setAutoCommit(false);

            String ior = "Insert or replace into Palabra  (cadena, repeticiones)";
            ior += " values ( ?,coalesce";
            ior += "( (SELECT repeticiones FROM palabra WHERE cadena LIKE ?) + ?,?))";
            pior = connection.prepareStatement(ior);
            String ioi = "Insert or ignore into PalabraXDocumento (idDoc, cadena) ";
            ioi += "values ((select idDoc from Documento where link like ?), ?)";
            pioi = connection.prepareStatement(ioi);

            mapaPal.registrarInicioPersistencia();

            double cantidadpalabras = cont / 100.0;
            double barreradenvio;

            if (cantidadpalabras > 1) {
                barreradenvio = cantidadpalabras;
            } else {
                cantidadpalabras = 1;
                barreradenvio = 1;
            }
            int auxcount = 0;

            while (it.hasNext()) {
                Word pal = it.next();
                s = pal.getWord();
                cont = pal.getTermFreq();

                pior.setString(1, s);
                pior.setString(2, s);
                pior.setInt(3, cont);
                pior.setInt(4, cont);
                pior.executeUpdate();

                jIt = pal.getDocumentIterator();
                while (jIt.hasNext()) {
                    Document d = jIt.next();
                    enlace = d.getPath();

                    pioi.setString(1, enlace);
                    pioi.setString(2, s);
                    pioi.executeUpdate();
                }

                auxcount += 1;
                if ((int) barreradenvio == auxcount) {
                    mapaPal.registrarAvancePersistencia();

                    barreradenvio += cantidadpalabras;
                }

            }

            connection.commit();

            mapaPal.registrarFinPersistencia();
            mapaPal.registrarFinPersitiendoArchivos("Listo");

            connection.setAutoCommit(true);

        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
            try {
                connection.rollback();
                mapaPal.registrarFinPersitiendoArchivos("Error al guardar.");
            } catch (SQLException e) {

                System.out.println("Error " + e.getMessage());
            }
        }

    }

    public LinkedList<Document> getDocumentsFromDB() {

        statement = null;
        Document doc;
        LinkedList<Document> lista = new LinkedList<>();

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery("select link from Documento");
            while (rs.next()) {
                doc = new Document(rs.getString("link"));
                lista.add(doc);
            }

        } catch (SQLException ex) {

            System.out.println("Error " + ex.getMessage());
        }

        return lista;

    }
}