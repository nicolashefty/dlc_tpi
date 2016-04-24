/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.searchengine.data.access;

import com.searchengine.logic.Posting;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author nicoe
 */
public interface PostingDAO {
       /** 
    * This is the method to be used to initialize
    * database resources ie. connection.
    */
   public void setDataSource(DataSource ds);
   /** 
    * This is the method to be used to create
    * a record in the Posting table.
    */
   public void create(String doc_id, String term, Integer term_freq);
   /** 
    * This is the method to be used to list down
    * a record from the Posting table corresponding
    * to a passed posting ids.
    */
   public Posting getPosting(String term, String doc_id);
   /** 
    * This is the method to be used to list down
    * all the records from the Posting table.
    */
   public List<Posting> listPostings();
   /** 
    * This is the method to be used to delete
    * a record from the Posting table corresponding
    * to a passed posting ids.
    */
   public void delete(String term, String doc_id);
   /** 
    * This is the method to be used to update
    * a record into the Posting table.
    */
   public void update(String doc_id, String term, Integer term_freq);
}
