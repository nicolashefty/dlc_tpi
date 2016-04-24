package com.searchengine.logic;

public class Posting {

    private String document_id;
    private String term_id;
    private Integer termFreq;

    public String getDocumentId() {
        return document_id;
    }

    public void setDocumentId(String document_id) {
        this.document_id = document_id;
    }

    public String getTermId() {
        return term_id;
    }

    public void setTermId(String term_id) {
        this.term_id = term_id;
    }

    public Integer getTermFreq() {
        return termFreq;
    }

    public void setTermFreq(Integer termFreq) {
        this.termFreq = termFreq;
    }

}
