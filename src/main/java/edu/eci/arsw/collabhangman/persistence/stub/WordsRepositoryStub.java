/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.collabhangman.persistence.stub;

import edu.eci.arsw.collabhangman.persistence.WordsRepository;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
@Service
public class WordsRepositoryStub implements WordsRepository{

    //change to a thread-safe collection if a modification operation is included
    private static final List<String> WORDS=new LinkedList<>(Arrays.asList(new String[]{
            "happiness",
            "foot",
            "player",
            "winner"
        }));
    
    
    @Override
    public List<String> loadAllWords() {
        
        return WORDS;
        
    }
    
    
}
