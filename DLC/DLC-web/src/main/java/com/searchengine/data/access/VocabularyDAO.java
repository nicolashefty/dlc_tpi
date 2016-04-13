/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.searchengine.data.access;

import com.searchengine.logic.Vocabulary;

/**
 *
 * @author aacorn
 */
public interface VocabularyDAO {
    void save(Vocabulary vocabulary);
}
