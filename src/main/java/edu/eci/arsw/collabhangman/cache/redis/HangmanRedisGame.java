/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.collabhangman.cache.redis;

import edu.eci.arsw.collabhangman.model.game.HangmanGame;
import java.util.Arrays;
import java.util.List;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

/**
 *
 * @author 2106340
 */
public class HangmanRedisGame extends HangmanGame {

    private final int id;
    private final StringRedisTemplate template;

    public HangmanRedisGame(int id, StringRedisTemplate template) {
        super((String) template.opsForHash().get("game:" + id, "word"));
        this.id = id;
        this.template = template;
    }

    /**
     * @pre gameFinished==false
     * @param l new letter
     * @return the secret word with all the characters 'l' revealed
     */
    @Override
    public String addLetter(char l) {
        
//        El framework no soporta esta operación en lua, según la documentación en la página de Spring
//        Se supone que el script está bien hecho y funciona en la página demo de lua

//        template.execute(new SessionCallback< List< Object>>() {
//            @SuppressWarnings("unchecked")
//            @Override
//            public < K, V> List<Object> execute(final RedisOperations< K, V> operations)
//                    throws DataAccessException {
//
//                DefaultRedisScript<String> redisScript = new DefaultRedisScript<>();
//                redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("/scripts/addLetter.lua")));
//                operations.watch((K) ("game:" + id));
//                operations.multi();
//                operations.execute(redisScript, Arrays.asList((K) ("game:" + id)), "word", "guessedword", String.valueOf(l));
//                return operations.exec();
//            }
//        });
        String gword = (String) template.opsForHash().get("game:" + id, "guessedword");
        StringBuilder response = new StringBuilder();
        for (int i = 0; i < word.length(); ++i) {
            if (word.charAt(i) == l) {
                response.append(l);
            } else {
                response.append(gword.charAt(i));
            }
        }
        return response.toString();
    }

    @Override
    public synchronized boolean tryWord(String playerName, String s) {
        if (word.equalsIgnoreCase(s)) {
            template.opsForHash().put("game:" + id, "winner", playerName);
            template.opsForHash().put("game:" + id, "finished", "true");
            template.opsForHash().put("game:" + id, "guessedword", word);
            return true;
        }
        return false;
    }

    @Override
    public boolean gameFinished() {
        //consultar el valor en un hash
        String value = (String) template.opsForHash().get("game:" + id, "finished");
        return Boolean.parseBoolean(value);
    }

    /**
     * @pre gameFinished=true;
     * @return winner's name
     */
    @Override
    public String getWinnerName() {
        //consultar el valor en un hash
        return (String) template.opsForHash().get("game:" + id, "winner");
    }

    @Override
    public String getCurrentGuessedWord() {
        //consultar el valor en un hash
        return (String) template.opsForHash().get("game:" + id, "guessedword");
    }

}
