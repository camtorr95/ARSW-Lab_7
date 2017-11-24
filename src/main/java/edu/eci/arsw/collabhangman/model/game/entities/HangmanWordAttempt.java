/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.collabhangman.model.game.entities;

/**
 *
 * @author hcadavid
 */
public class HangmanWordAttempt {
    
    private String word;
    
    private String username;

    public HangmanWordAttempt() {
    }

    public HangmanWordAttempt(String word, String username) {
        this.word = word;
        this.username = username;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    
    
}
