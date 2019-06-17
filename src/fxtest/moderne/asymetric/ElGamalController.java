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
import java.util.Scanner;
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
public class ElGamalController implements Initializable {

    @FXML
    private Button retourArriere;
    @FXML
    public void retourArriere(ActionEvent event){
        try {
            URL url=new File("src/fxtest/moderne/asymetric/RSA.fxml").toURL();
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
        /*try {
            URL url=new File("src/fxtest/moderne/ElGamal.fxml").toURL();
            Parent chifSymParent=FXMLLoader.load(url);
            Scene chifSymScene=new Scene(chifSymParent);
            Stage app_stage=(Stage)((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(chifSymScene);
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        */

    }
    
    @FXML
    private Button generer;
    @FXML
    private TextField nombrePremierApprox;
    @FXML
    private TextField nombrePremierP;
    @FXML
    private TextField generateurG;
    @FXML
    private TextField secretS;
    @FXML
    private TextField publiqueY;
     
   
    Random r = new Random();

    BigInteger p;
    BigInteger g ;
    BigInteger s ;
    BigInteger y ;
  
     @FXML
     public void genererDesCles(ActionEvent event){
        if(nombrePremierApprox.getText().length()>0){
        p = getNextPrime(nombrePremierApprox.getText());
        g = getGenerator(p, r);
        if (g != null) {
            alerte.setText("");
        // Pick a secret s.
        s = new BigInteger(p.bitCount()-1, r);
        // Calculate the corresponding public y.
	y = g.modPow(s, p);
        
        nombrePremierP.setText(p.toString());
        generateurG.setText(g.toString());
        secretS.setText(s.toString());
        publiqueY.setText(y.toString());
        }
        else{
            alerte.setText("Generatot non trouvé, essayer autre fois avec un autre premir approximé");
        }
        }
        else{
             alerte.setText("nombre premier approximé est vide");
        }
     }
     
     @FXML
    private TextArea TextClaire;
    @FXML
    private TextArea TextResultC1;
    @FXML
    private TextArea TextResultC2;
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
     String toencrypt;
     BigInteger k ;
     BigInteger c1;
     BigInteger c2;
     BigInteger m;
     BigInteger temp ;
     BigInteger recover;
    @FXML
    private void EncDec(ActionEvent event) {
        int echec=0;
        toencrypt=TextClaire.getText();
        String result="";
        String decC1=TextResultC1.getText();
        String decC2=TextResultC2.getText();
        
        if(nombrePremierP.getText().length()<1){
            alerte.setText("champs du nombre premier  est vide");
            echec=1;
        }
        
        else{
            if(ChiffrerDéchiffrer()==1){
                if(toencrypt.length()<1){
                    alerte.setText("champs du text à Chif/Déchif est vide");
                }
                else if(generateurG.getText().length()<1){
                        alerte.setText("champs du nombre generateur  est vide");
                }
                else if(publiqueY.getText().length()<1){
                        alerte.setText("champs de clé publique  est vide");
                }
                else{
                    // When we send a message, the sender picks a random k.
                    k = new BigInteger(p.bitCount()-1, r);
                    // Here, the sender starts calculating parts of the ciphertext that
                    // don't involve the actual message.
                    c1 = g.modPow(k, p);
                    c2 = y.modPow(k, p);
             
                    // Here we get the message from the user.
                    m = new BigInteger(toencrypt);
                    c2 = c2.multiply(m);
                    c2 = c2.mod(p);
                    TextResultC1.setText(c1.toString());
                    TextResultC2.setText(c2.toString());   
                    
                }
                
            }
            else{
                if(decC1.length()<1){
                    alerte.setText("champs du message C1  est vide");
                }
                else if(decC2.length()<1){
                    alerte.setText("champs du message C2  est vide");
                }
                else{
                    // First, determine the inverse of c1 raised to the a power mod p.
			temp = c1.modPow(s,p);
			temp = temp.modInverse(p);
                        // Now, just multiply this by the second ciphertext
			recover = temp.multiply(c2);
			recover = recover.mod(p);
                        TextClaire.setText(recover.toString());
                }
                
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
    public BigInteger getNextPrime(String ans) {
		
		BigInteger one = new BigInteger("1");
		BigInteger test = new BigInteger(ans);
		while (!test.isProbablePrime(99))
			test = test.add(one);
		return test;		
    }
    public  BigInteger getGenerator(BigInteger p, Random r) {

		int numtries = 0;
		
		// Try finding a generator at random 100 times.
		while (numtries <50) {
		
    		// Here's what we're trying as the generator this time.
    		BigInteger rand = new BigInteger(p.bitCount()-1,r);

    		BigInteger exp = BigInteger.ONE;
    		BigInteger next = rand.mod(p);

			// We exponentiate our generator until we get 1 mod p.
    		while (!next.equals(BigInteger.ONE)) {
      			next = (next.multiply(rand)).mod(p);
      			exp = exp.add(BigInteger.ONE);
    		}

			// If the first time we hit 1 is the exponent p-1, then we have
			// a generator.
    		if (exp.equals(p.subtract(BigInteger.ONE)))
      			return rand;
      	}
      	
      	// None of the 50 values we tried was a generator.
      	return null;

  }
}
