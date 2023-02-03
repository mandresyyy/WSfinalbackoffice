package com.example.demo.utilitaire;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mandr
 */
public class UrlParser {
    public static String repair(String url) {
          
        char[] specChar = {'+','%',' '};
        char[] characters = url.toCharArray();
        
        boolean verif = false;
        int index = 0;
        for(int j = 0; j < specChar.length; j += 1) {
            System.out.println(specChar[j]);
            for(int i = 0; i < characters.length; i += 1) {
                if(characters[i] == specChar[j]) {
                    index = i;
                    verif = true; 
                    break;
                }
            }
            if(verif) {
                break;
            }
        }
        String newurl = "";
        for(int a = 0; a < index; a += 1) {
            newurl += characters[a];
        }
        
        return newurl;
    } 
}
