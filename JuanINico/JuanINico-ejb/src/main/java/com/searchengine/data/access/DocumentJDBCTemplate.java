/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.searchengine.data.access;

import com.searchengine.logic.Document;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author nicoe
 */
public class DocumentJDBCTemplate implements DocumentDAO {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public void create(String title, String id) {
        String SQL = "insert into DOCUMENT (doc_title, doc_id) values (?, ?)";

        jdbcTemplateObject.update(SQL, title, id);
        System.out.println("Created Record Title = " + title + " Id = " + id);
        return;
    }

    public Document getDocument(String id) {
        String SQL = "select * from DOCUMENT where doc_id = ?";
        Document document = (Document)jdbcTemplateObject.queryForObject(SQL,
                new Object[]{id}, new DocumentRowMapper());
        return document;
    }

    public List<Document> listDocuments() {
        String SQL = "select * from DOCUMENT";
        List<Document> documents = jdbcTemplateObject.query(SQL,
                new DocumentRowMapper());
        return documents;
    }

    public void delete(String id) {
        String SQL = "delete from DOCUMENT where doc_id = ?";
        jdbcTemplateObject.update(SQL, id);
        System.out.println("Deleted Record with ID = " + id);
        return;
    }

    public void update(String id, String title) {
        String SQL = "update DOCUMENT set doc_title = ? where doc_id = ?";
        jdbcTemplateObject.update(SQL, title, id);
        System.out.println("Updated Record with ID = " + id);
        return;
    }
}
