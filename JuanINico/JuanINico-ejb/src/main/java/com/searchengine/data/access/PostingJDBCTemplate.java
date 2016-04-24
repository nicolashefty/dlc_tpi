/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.searchengine.data.access;

import com.searchengine.logic.Posting;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author nicoe
 */
public class PostingJDBCTemplate {
    
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public void create(String doc_id, String term, Integer term_freq) {
        String SQL = "insert into POSTING (doc_id, term, term_freq) values (?, ?)";

        jdbcTemplateObject.update(SQL, doc_id, term, term_freq);
        System.out.println("Created Record Doc_id = " + doc_id + " term = " + term);
        return;
    }

    public Posting getPosting(String term, String doc_id) {
        String SQL = "select * from POSTING where doc_id = ? and term = ?";
        Posting posting = (Posting)jdbcTemplateObject.queryForObject(SQL,
                new Object[]{doc_id, term}, new PostingRowMapper());
        return posting;
    }

    public List<Posting> listPostings() {
        String SQL = "select * from POSTING";
        List<Posting> postings = jdbcTemplateObject.query(SQL,
                new PostingRowMapper());
        return postings;
    }

    public void delete(String term, String doc_id) {
        String SQL = "delete from POSTING where doc_id = ? and term = ?";
        jdbcTemplateObject.update(SQL, doc_id, term);
        System.out.println("Deleted Record with ID = " + doc_id + " term = "+ term);
        return;
    }

    public void update(String doc_id, String term, Integer term_freq) {
        String SQL = "update POSTING set term_freq = ? where doc_id = ? and term = ?";
        jdbcTemplateObject.update(SQL, term_freq, doc_id, term);
        System.out.println("Updated Record with ID = " + doc_id + " term = "+ term);
        return;
    }
}
