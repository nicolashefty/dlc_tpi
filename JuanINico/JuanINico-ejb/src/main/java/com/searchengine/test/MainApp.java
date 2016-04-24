/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.searchengine.test;

/**
 *
 * @author nicoe
 */
import com.searchengine.data.access.DocumentJDBCTemplate;
import com.searchengine.logic.Document;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

    public static void main(String[] args) {
        ApplicationContext context
                = new ClassPathXmlApplicationContext("com/searchengine/test/Beans.xml");

        DocumentJDBCTemplate documentJDBCTemplate
                = (DocumentJDBCTemplate) context.getBean("documentJDBCTemplate");

        System.out.println("------Records Creation--------");
        documentJDBCTemplate.create("ATITLE", "ANID");
        documentJDBCTemplate.create("ATITLE2", "ANID2");
        documentJDBCTemplate.create("ATITLE3", "ANID3");

        System.out.println("------Listing Multiple Records--------");
        List<Document> students = documentJDBCTemplate.listDocuments();
        for (Document record : students) {
            System.out.print("ID : " + record.getId());
            System.out.print(", Title : " + record.getTitle());
        }

        System.out.println("----Updating Record with ID = 2 -----");
        documentJDBCTemplate.update("ANID2", "TITLEEE");

        System.out.println("----Listing Record with ID = 2 -----");
        Document student = documentJDBCTemplate.getDocument("ANID2");
        System.out.print("ID : " + student.getId());
        System.out.print(", Name : " + student.getTitle());

        documentJDBCTemplate.delete("ANID");
        documentJDBCTemplate.delete("ANID2");
        documentJDBCTemplate.delete("ANID3");

    }
}
