package com.searchengine.data.access;

import com.searchengine.logic.Document;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author nicoe
 */
public class DocumentRowMapper implements RowMapper {
   public Document mapRow(ResultSet rs, int rowNum) throws SQLException {
      Document document = new Document();
      document.setId(rs.getString("doc_id"));
      document.setTitle(rs.getString("doc_title"));
      return document;
   }  
}
