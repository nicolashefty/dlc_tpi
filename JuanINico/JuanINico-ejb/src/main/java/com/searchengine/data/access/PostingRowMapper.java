/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.searchengine.data.access;

import com.searchengine.logic.Posting;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
/**
 *
 * @author nicoe
 */
public class PostingRowMapper implements RowMapper {
      public Posting mapRow(ResultSet rs, int rowNum) throws SQLException {
      Posting posting = new Posting();
      posting.setDocumentId(rs.getString("doc_id"));
      posting.setTermId(rs.getString("term"));
      posting.setTermFreq(rs.getInt("term_freq"));
      return posting;
   }  
}
