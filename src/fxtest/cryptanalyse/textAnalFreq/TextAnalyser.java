/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxtest.cryptanalyse.textAnalFreq;

import fxtest.HomePageController;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author Blunt_Blowin
 */
public class TextAnalyser {
         /**
     * Texte à analyser
     */
    private String mytext = "";
    /**
     * nombre de ligne dans le texte
     */
    private int linecount = 0;
 
    /**
     * Fixer le texte à partir d'un string
     * @param text
     */
    public void assign(String text) {
        this.mytext = text;
        StringTokenizer st = new StringTokenizer(this.mytext, System.getProperty("line.separator"));
        this.linecount = st.countTokens();
    }
 
    /**
     * Charger le texte à partir d'un fichier
     * @param filename nom du fichier
     */
    public void loadfile(String filename) {
        FileReader filereader = null;
        StringBuffer contents = new StringBuffer();
        try {
            filereader = new FileReader(filename);
            BufferedReader bufferReader = new BufferedReader(filereader);
            String line = null;
            while ((line = bufferReader.readLine()) != null) {
                contents.append(line).append(System.getProperty("line.separator"));
                this.linecount ++;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TextAnalyser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TextAnalyser.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.mytext = contents.toString();
    }
 
    /**
     * Nombre de caractère
     * @return
     */
    public int charsize() {
        return this.mytext.length();
    }
 
    /**
     * Nombre de ligne
     * @return
     */
    public int linecount() {
        return this.linecount;
    }
 
    /**
     * Nombre de mot
     * @return
     */
    public int wordcount() {
        StringTokenizer st = new StringTokenizer(this.mytext);
        return st.countTokens();
    }
 
    /**
     * Frequence de caractère
     * @return
     */
    public int[] letterFrequency() {
        int[] freqs = new int[26];
        String text = this.mytext.toLowerCase();
 
        char[] chars = text.toCharArray();
        int p = 0;
        int len = text.length();
        for (int i = 0; i < len; i ++ ) {
            p = chars[i] - 97;
            if (p > -1 && p < 27) {
                freqs[p]++;
            }
        }
        return freqs;
    }
}
