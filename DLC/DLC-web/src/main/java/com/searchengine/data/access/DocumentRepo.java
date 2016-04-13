/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.searchengine.data.access;

import com.searchengine.logic.Document;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author aacorn
 */
public interface DocumentRepo extends CrudRepository<Document, Long>{
    List<Document> findByTitle(String title);
    List<Document> findByPath(String path);
}
