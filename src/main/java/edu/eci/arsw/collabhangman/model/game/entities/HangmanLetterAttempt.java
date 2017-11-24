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
public class HangmanLetterAttempt {
    
    private char letter;
    
    private String username;

    public HangmanLetterAttempt() {
    }

    public HangmanLetterAttempt(char letter, String username) {
        this.letter = letter;
        this.username = username;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    
    
    
}
