package com.searchengine.data.access;

import com.searchengine.logic.Document;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author nicoe
 */
public interface DocumentDAO {
       /** 
    * This is the method to be used to initialize
    * database resources ie. connection.
    */
   public void setDataSource(DataSource ds);
   /** 
    * This is the method to be used to create
    * a record in the Document table.
    */
   public void create(String title, String id);
   /** 
    * This is the method to be used to list down
    * a record from the Student table corresponding
    * to a passed student id.
    */
   public Document getDocument(String id);
   /** 
    * This is the method to be used to list down
    * all the records from the Document table.
    */
   public List<Document> listDocuments();
   /** 
    * This is the method to be used to delete
    * a record from the Document table corresponding
    * to a passed student id.
    */
   public void delete(String id);
   /** 
    * This is the method to be used to update
    * a record into the Document table.
    */
   public void update(String id, String title);
}
