/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxtest.moderne.asymetric;

import fxtest.HomePageController;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.util.Random;
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

/**
 * FXML Controller class
 *
 * @author Blunt_Blowin
 */
public class RSAController implements Initializable {

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
    private Button Suivant;
     @FXML
    public void suivant(ActionEvent event){
        try {
            URL url=new File("src/fxtest/moderne/asymetric/ElGamal.fxml").toURL();
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
    private Button generer;
    @FXML
    private TextField nombreDeBits;
    @FXML
    private TextField nombreP;
    @FXML
    private TextField nombreQ;
    @FXML
    private TextField nombreN;
    @FXML
    private TextField nombreW;
    @FXML
    private TextField nombreD;
    @FXML
    private TextField nombreE;
    String alphabet = "abcdefghijklmnopqrstuvwxyz";
    int rang;
    int bitlength = 1024;
        Random r = new Random();
        
     BigInteger p;
     BigInteger q;
     BigInteger N;
     BigInteger phi;
     BigInteger e;
     BigInteger d;
    
     @FXML
     public void genererDesCles(ActionEvent event){
         
     
      bitlength= Integer.valueOf(nombreDeBits.getText());
        //Generer P et Q premiers
       p = BigInteger.probablePrime(bitlength, r);
       q = BigInteger.probablePrime(bitlength, r);
       N = p.multiply(q);                //N=p*q
       phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)); //phi=(p-1)*(q-1)
       e = BigInteger.probablePrime(bitlength / 2, r);                    
        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0)
            e.add(BigInteger.ONE);
        d = e.modInverse(phi);          //d:l'inverse module phi de e
        
        nombreP.setText(p.toString());
        nombreQ.setText(q.toString());
        nombreN.setText(N.toString());
        nombreW.setText(phi.toString());
        nombreE.setText(e.toString());
        nombreD.setText(d.toString());        
     }
     
     @FXML
    private TextArea TextClaire;
    @FXML
    private TextArea TextResult;
    @FXML
    private RadioButton chiffrer;
    @FXML
    private RadioButton déchiffrer;
    private ToggleGroup ChiiffrerDechifrerToggleGrp;
    public int ChiffrerDéchiffrer() { //retourner le choix de l'utilisateur chifferer/dechiffrer
        if (this.ChiiffrerDechifrerToggleGrp.getSelectedToggle().equals(this.chiffrer)) {
            return 1;
        } else {
            return 2;
        }
    }
     
     @FXML
    private Label alerte;//les alertes
     
    @FXML
    private void EncDec(ActionEvent event) {
        int echec=0;
        String toencrypt=TextClaire.getText();
        String result="";
        if(toencrypt.length()<1){
            alerte.setText("champs de text à Chif/Déchif est vide");
            echec=1;
        }
        else if(nombreE.getText().length()<1 ){
             //nombreE.setStyle("-fx-background-color:red;");
             alerte.setText("champs du nombre E est vide");
             echec=1;
        }
        else if(nombreN.getText().length()<1){
            //nombreN.setStyle("-fx-background-color:red;");
             alerte.setText("champs du nombre N est vide");
             echec=1;
        }
        if(echec==0){
           // nombreE.setStyle("-fx-background-color:black;");
           // nombreN.setStyle("-fx-background-color:black;");
            if(ChiffrerDéchiffrer()==1){
                alerte.setText("");
                e=new BigInteger(nombreE.getText());
                N=new BigInteger(nombreN.getText());
            
                for(int i=0;i<toencrypt.length();i++){
                    rang=alphabet.indexOf(toencrypt.charAt(i));
                    BigInteger bigIntegerCorrespond = BigInteger.valueOf(rang); //le rang correspond à la lettre 
                    BigInteger chiffreCorresp=bigIntegerCorrespond.modPow(e, N);
                    result+=chiffreCorresp;      
                }
                TextResult.setText(result); 
           }
            else{
                TextResult.setText("le dechiffrement est pas encore implémnté");
            }
            
        }
          
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ChiiffrerDechifrerToggleGrp = new ToggleGroup();   //
        this.chiffrer.setToggleGroup(ChiiffrerDechifrerToggleGrp);
        this.déchiffrer.setToggleGroup(ChiiffrerDechifrerToggleGrp);
        this.ChiiffrerDechifrerToggleGrp.selectToggle(chiffrer);
    }    
    
}
