package com.searchengine.logic;

import javax.persistence.*;

@Entity
public class Document {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String path;
    private String title;

    protected Document() {

    }

    public Document(String path) {
        if (!path.isEmpty()) {
            this.path = path;
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        return String.format(
                "Documento[id=%d, path='%s', title='%s']",
                id, path, title);
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
