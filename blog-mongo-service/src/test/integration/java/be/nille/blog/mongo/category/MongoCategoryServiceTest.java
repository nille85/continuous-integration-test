/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.blog.mongo.category;

import be.nille.blog.mongo.category.MongoCategoryService;
import be.nille.blog.domain.category.Category;
import be.nille.blog.domain.category.Category;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Ignore;


import org.junit.Test;

/**
 *
 * @author Niels Holvoet
 */
@Slf4j
public class MongoCategoryServiceTest {
    
    private MongoCategoryService service;
    
    @Before
    public void setup(){
        final String url = System.getenv("MONGO_URL");
        MongoClient client = new MongoClient(
                new MongoClientURI(url)
        );
        MongoDatabase database = client.getDatabase("openid-connect");
       

        
        service = new MongoCategoryService(database);
    }
    
    @Test
    public void testFetching(){
       
        List<? extends Category> categories = service.findAll();
        categories.stream().forEach(c -> log.debug(c.toString()));
    }
    
    @Ignore
    public void testSaving(){
        try{
            service.save(new Category("Databases"));
        }catch(MongoException ex){
            log.error(ex.getMessage());
        }
        List<? extends Category> categories = service.findAll();
        categories.stream().forEach(c -> log.debug(c.toString()));
    }
    
}
