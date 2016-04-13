
package com.searchengine.logic;

import java.io.Serializable;
import java.security.Identity;
import javax.persistence.*;
import java.util.Iterator;
import java.util.LinkedList;

@Entity
public class Word implements Serializable{

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String word;
    private int termFreq;
    private int filesSize;    

    private Word() {

    }

    private Word(String word) {
        this.word = word;
        termFreq = 1;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    public String getWord() {
        return word;
    }

    public void setWord(String p) {
        word = p;
    }
    
    public void setFilesSize(int c)
    {
       
       filesSize = c;
    }
    
    public int getFilesSize()
    {
                return filesSize;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Word: ").append(word).append(" - Term frquency: ").append(termFreq).append(" - Amount of Documents: ").append(filesSize).append("\n ");

        return sb.toString();
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
}
