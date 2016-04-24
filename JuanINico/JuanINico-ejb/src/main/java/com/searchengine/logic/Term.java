
package com.searchengine.logic;



public class Term {

    private String word;
    /** Maximum Frequency in documents */
    private int maxFreq;
    /** Number of documents where this term is referenced */
    private int nRef; 
    
    private Document lastDoc;

    protected Term() {

    }

    public Term(String word) {
        this.word = word;
        maxFreq = 1;
    }

    public int getMaxFreq() {
        return maxFreq;
    }

    /**
     * This method allows to set the word's max frequency
     * @param con 
     */
    public void setMaxFreq(int con) {
        maxFreq = con;
    }
    
    /**
     * Increase the value of the word's frequency in 1
     * if the doc passed is no the same as the last set, 
     * then this will be the last
     */
    public void repeatForDoc(Document doc) {
        if(!isSameDoc(doc))
        {
            this.lastDoc = doc;
            nRef++;
        }
        else
        {
            
        }
    }
    public String getWord() {
        return word;
    }

    public void setWord(String p) {
        word = p;
    }
    
    public void setNRef(int c)
    {
       
       nRef = c;
    }
    
    public int getNRef()
    {
                return nRef;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Word: ").append(word).append(" - Term frquency: ").append(maxFreq).append(" - Amount of Documents: ").append(nRef).append("\n ");

        return sb.toString();
    }
    
    @Override
    public int hashCode() {
        return this.word.hashCode();
    }

    private boolean isSameDoc(Document doc) {
        return this.lastDoc.equals(doc);
    }
}
