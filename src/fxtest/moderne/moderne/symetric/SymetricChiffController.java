/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxtest.moderne.symetric;

import Classes.AES;
import Classes.DES;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * FXML Controller class
 *
 * @author Blunt_Blowin
 */
public class SymetricChiffController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button retourArriere;
   @FXML
    public void retourArriere(ActionEvent event){
        try {
            URL url=new File("src/fxtest/moderne/SymAsymHomePage.fxml").toURL();
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
    private Label alerte;//les alertes
    @FXML
    private Label alerte2;//les alertes
    
    @FXML
    private TextArea TextClaireAES;
    @FXML
    private TextArea TextResultAES;
    @FXML
    private TextArea TextClaireDES;
    @FXML
    private TextArea TextResultDES;
    @FXML
    private TextField keyAES; 
    @FXML
    private TextField keyDES;
    
    @FXML
    private RadioButton chiffrerAES;
    @FXML
    private RadioButton déchiffrerAES;
    private ToggleGroup ChiiffrerDechifrerAESToggleGrp;
    @FXML
    private RadioButton chiffrerDES;
    @FXML
    private RadioButton déchiffrerDES;
    private ToggleGroup ChiiffrerDechifrerDESToggleGrp;
    
    public int ChiffrerDéchiffrerDES() { //retourner le choix de l'utilisateur chifferer/dechiffrer
        if (this.ChiiffrerDechifrerDESToggleGrp.getSelectedToggle().equals(this.chiffrerDES)) {
            return 1;
        } else {
            return 2;
        }
    }
    
    public int ChiffrerDéchiffrerAES() { //retourner le choix de l'utilisateur chifferer/dechiffrer
        if (this.ChiiffrerDechifrerDESToggleGrp.getSelectedToggle().equals(this.chiffrerAES)) {
            return 1;
        } else {
            return 2;
        }
    }
    
    
    
    @FXML
    private void EncDecDES(ActionEvent event) {
        String mykey2 =keyDES.getText();
        String toencrypt2=TextClaireDES.getText();
        int mode = ChiffrerDéchiffrerDES();   //on veut Déchiffrer le text ou le déchiffrer !
        int echec=0;
        if(toencrypt2.length()<1){alerte.setText("Text à traiter est vide");echec=1;}
        if(mykey2.length()!=8){alerte.setText("la clé DES doit contenir 8 charactères");echec=1;}
        
        if(echec==0){
            if(mode==1){
            alerte.setText("");echec=0;
            SecretKey key2 = new SecretKeySpec(mykey2.getBytes(), "DES");
            DES encrypter2=new DES(key2);   
            String encrypted_data2=encrypter2.encrypt(toencrypt2);
            TextResultDES.setText(encrypted_data2); 
            }
            else{
            alerte.setText("");echec=0;
            SecretKey key2 = new SecretKeySpec(mykey2.getBytes(), "DES");
            DES encrypter2=new DES(key2);   
            String encrypted_data2=encrypter2.decrypt(toencrypt2);
            TextResultDES.setText(encrypted_data2); 
            }
        }        
    }
    @FXML
    private void EncDecAES(ActionEvent event) {
        String mykey2 =keyAES.getText();
        String toencrypt2=TextClaireAES.getText();
        int mode = ChiffrerDéchiffrerAES();   //on veut Déchiffrer le text ou le déchiffrer !
        int echec=0;
        if(toencrypt2.length()<1){alerte2.setText("Text à traiter est vide");echec=1;}
        if(mykey2.length()!=8){alerte2.setText("la clé DES doit contenir 16 charactères");echec=1;}
        
        if(echec==0){
            if(mode==1){
            alerte.setText("");echec=0;
            SecretKey key2 = new SecretKeySpec(mykey2.getBytes(), "AES");
            AES encrypter2=new AES(key2);   
            String encrypted_data2=encrypter2.encrypt(toencrypt2);
            TextResultDES.setText(encrypted_data2); 
            }
            else{
            alerte.setText("");echec=0;
            SecretKey key2 = new SecretKeySpec(mykey2.getBytes(), "AES");
            AES encrypter2=new AES(key2);   
            String encrypted_data2=encrypter2.decrypt(toencrypt2);
            TextResultDES.setText(encrypted_data2); 
            }
        }        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ChiiffrerDechifrerDESToggleGrp = new ToggleGroup();   //
        this.chiffrerDES.setToggleGroup(ChiiffrerDechifrerDESToggleGrp);
        this.déchiffrerDES.setToggleGroup(ChiiffrerDechifrerDESToggleGrp);
        this.ChiiffrerDechifrerDESToggleGrp.selectToggle(chiffrerDES);
        
        ChiiffrerDechifrerAESToggleGrp = new ToggleGroup();   //
        this.chiffrerAES.setToggleGroup(ChiiffrerDechifrerAESToggleGrp);
        this.déchiffrerAES.setToggleGroup(ChiiffrerDechifrerAESToggleGrp);
        this.ChiiffrerDechifrerAESToggleGrp.selectToggle(chiffrerAES);
    }    
    
}
