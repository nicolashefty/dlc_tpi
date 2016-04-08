package com.dlc.searchengine.logic;

public class Document {

    private String path;
    private String title;
    private String briefDesc;

    public Document() {

    }

    public Document(String path) {
        if (!path.isEmpty()) {
            this.path = path;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns a brief description for this document
     * @return 
     */
    public String getBriefDesc() {
        return briefDesc;
    }

    public void setBriefDesc(String briefDesc) {
        this.briefDesc = briefDesc;
    }

    /**
     * Returns the URL or the file location
     * @return 
     */
    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return title + "\n" + briefDesc;
    }

    /**
     * This method allows you to know if the given Doc has the same path that
     * the object
     *
     * @param d Document
     * @return true if they are equals (paths)
     */
    public boolean equals(Document d) {
        return (this.getPath().equals(d.getPath()));
    }
}
