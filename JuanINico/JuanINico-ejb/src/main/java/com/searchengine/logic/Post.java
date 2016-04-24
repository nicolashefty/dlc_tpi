
package com.searchengine.logic;

import javax.persistence.*;

@Entity
public class Post {

    @Id
    private Document document;
    @Id
    private Integer key;
    private Integer termFreq;
    
    protected Post()
    {
        
    }
    public Post(Integer key, Document document) {
        this.key = key;
        this.document = document;
        this.termFreq = 1;
    }

    public Integer getKey()
    {
        return key;
    }
        public void setKey(int key)
    {
        this.key = key;
    }
   
    public void increaseFreq() {

        termFreq ++;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Integer getTermFreq() {
        return termFreq;
    }

    public void setTermFreq(Integer termFreq) {
        this.termFreq = termFreq;
    }
    @Override
    public String toString() {
        return String.format("Post: \nId Term: %d \nDocument: %s \nFrequency: %d",key,document.getTitle(),termFreq);
    }
}
