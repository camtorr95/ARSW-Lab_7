/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.collabhangman.cache.stub;

import edu.eci.arsw.collabhangman.cache.GameStateCache;
import edu.eci.arsw.collabhangman.services.GameCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author 2106340
 */
@Service
public class GameStateRedisCache implements GameStateCache{

    @Autowired
    private StringRedisTemplate template;

    @Override
    public HangmanRedisGame getGame(int id) {
        return new HangmanRedisGame(id, template);
    }

    @Override
    public void createGame(int id, String word) throws GameCreationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
