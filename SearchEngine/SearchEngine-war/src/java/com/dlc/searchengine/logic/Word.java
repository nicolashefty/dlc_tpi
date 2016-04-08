
package com.dlc.searchengine.logic;

import java.util.Iterator;
import java.util.LinkedList;

public class Word {

    private String word;
    private LinkedList<Document> files;
    private int termFreq;
    private int filesSize;    

    private Word() {

    }

    private Word(String word) {
        this.word = word;
        termFreq = 1;
        files = new LinkedList<>();
    }

   
    public static Word producirPalabra(String p) {
        
        String aux = p;
        String aux2 = aux.replaceAll("\\d", "");
        boolean deletePrefix = true, deleteSufix = true;
        if (aux.compareTo(aux2) == 0 && !(aux.isEmpty()) && (aux.length() > 1)) {
           
            while (deletePrefix) {
                if (aux.length() > 0 && aux.charAt(0) == '-') {
                    if (aux.length() > 1) {
                        aux = aux.substring(1, aux.length());
                    } else {
                        aux = "";
                    }
                } else {
                    deletePrefix = false;
                }
            }
            while (deleteSufix) {
                if (aux.length() > 0 && aux.charAt(aux.length() - 1) == '-') {
                    aux = aux.substring(0, aux.length() - 1);
                } else {
                    deleteSufix = false;
                }
            }
            if (aux.length() < 2) {
                return null;
            } 

            Word word = new Word(aux.toLowerCase());
            return word;
        } else {
            return null;
        }

    }

    public int getTermFreq() {
        return termFreq;
    }

    /**
     * This method allows to set the word's frequency
     * @param con 
     */
    public void setTermFreq(int con) {
        termFreq = con;
    }
    
    /**
     * Increase the value of the word's frequency in 1
     */
    public void repeat() {
        termFreq++;
    }
    
    public void addFile(Document d) {
        if (d == null) return;

        if (!isInDocument(d)) {
            files.add(d);
            filesSize++;
        }
    }
    private boolean isInDocument(Document doc) {
        if( files.isEmpty()) return false;
        else {
        	if (files.getLast().equals(doc)) {
	            return true;
	            }
	        else{
	        	for (Document d : files) {
	                if (d.equals(doc)) {
	                    return true;
	                }
	            }
	        }
        }
        return false;
    }
    
    public Iterator<Document> getDocumentIterator() {
        return files.iterator();
    }

    public Document getLastDocument() {
        return files.getLast();
    }

    public String getWord() {
        return word;
    }

    public void setWord(String p) {
        word = p;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Word: ").append(word).append(" - Term frquency: ").append(termFreq).append(" - Amount of Documents: ").append(filesSize).append("\n ");
        Iterator<Document> it = files.iterator();
        while (it.hasNext()) {
            sb.append("Documents: \n").append(it.next().toString()).append("\n ");
        }
        return sb.toString();
    }

   
    public void setFilesSize(int c)
    {
       
       filesSize = c;
    }
    
    public int getFilesSize()
    {
                return filesSize;
    }

}
