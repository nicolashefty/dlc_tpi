/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.searchengine.data.access;

import com.searchengine.logic.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.SessionFactory;

@Repository
public class WordDAOImpl implements WordDAO {

    @Autowired
    private SessionFactory sessionFactory;
    
    public WordDAOImpl() {
        
    }
    
    public WordDAOImpl(SessionFactory sessionFactory) {
        
        this.sessionFactory = sessionFactory;
    }
    @Override
    public void save(Word word) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
