/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.collabhangman.cache.stub;

import edu.eci.arsw.collabhangman.model.game.HangmanGame;
import java.util.HashSet;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 *
 * @author 2106340
 */
public class HangmanRedisGame extends HangmanGame {

    private int id;
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
        String gword = (String) template.opsForHash().get("game:" + id, "guessword");
        HashSet<Integer> index = new HashSet<>();
        for (int i = -1; (i = word.indexOf(l, i + 1)) != -1; i++) {
            index.add(i);
        }
        StringBuilder response = new StringBuilder();
        for (int i = 0; i < word.length(); ++i) {
            if (index.contains(i)) {
                response.append(l);
            } else {
                response.append(gword.charAt(i));
            }
        }
        String rta = response.toString();
        template.opsForHash().put("game:" + id, rta, "guessword");
        return rta;
    }

    @Override
    public synchronized boolean tryWord(String playerName, String s) {
        if(word.equalsIgnoreCase(s)){
            template.opsForHash().put("game:" + id, playerName, "winner");
            template.opsForHash().put("game:" + id, true, "status");
            template.opsForHash().put("game:" + id, s, "guessword");
            return true;
        }
        return false;
    }

    @Override
    public boolean gameFinished() {
        //consultar el valor en un hash
        String value = (String) template.opsForHash().get("game:" + id, "status");
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
        return (String) template.opsForHash().get("game:" + id, "guessword");
    }

}
