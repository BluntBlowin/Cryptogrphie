/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxtest.cryptanalyse.textAnalFreq;

import fxtest.HomePageController;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Blunt_Blowin
 */
public class TextAnalFreqController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    @FXML
    private Button retourArriere;
    @FXML
    public void retourArriere(ActionEvent event){
        try {
            URL url=new File("src/fxtest/cryptanalyse/cryptanalyse.fxml").toURL();
            Parent chifSymParent=FXMLLoader.load(url);
            Scene chifSymScene=new Scene(chifSymParent);
            Stage app_stage=(Stage)((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(chifSymScene);
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private Button Analyser;
      @FXML
    private TextField selectedFilet;
      @FXML
    private TextArea Resultat;
      @FXML
    private Label alert;
      String result="";
    @FXML
    public void analyser(ActionEvent event){
        if(selectedFilet.getText().length()>1){
        alert.setText("");
        TextAnalyser textAnalyser = new TextAnalyser();
        textAnalyser.loadfile(selectedFilet.getText());
       
        int[] freq = textAnalyser.letterFrequency();
        int charsum=0;

        for (int i = 0; i < 26; i++  ) {
            charsum +=freq[i];
        }
        int c;
        float f=0;

        for (int i = 0; i < 26; i ++ ) {
            c=i+97;
            f=freq[i]*100;
            result+=(char)c+ " -> " +freq[i] +" fois - "+ f/charsum + " % \n";
        }
        Resultat.setText(result);
      }
        else{
            alert.setText("choisir le fichier qui contient le text à analyser");
        }
    }
    
    @FXML
    private Button selectFile;
  
    @FXML
    public void choisirUnFichier(ActionEvent event){
        FileChooser fc = new  FileChooser();
        File selectedFile=fc.showOpenDialog(null);
        if(selectedFile != null){
            selectedFilet.setText(selectedFile.getPath());
        }else{
            System.out.println("FileNotValid");
            
        }
    }
    
    
}
