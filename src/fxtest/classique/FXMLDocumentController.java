/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxtest.classique;

import Classes.Cezar;
import Classes.ChiffAffine;
import Classes.Hill;
import Classes.Vigenaire;
import Classes.playfaire;
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

/**
 *
 * @author Blunt_Blowin
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;//les alertes
    @FXML
    private TextArea TextClaire;
    @FXML
    private TextArea TextResult;
    @FXML
    private TextField key; //clé de cézar

    @FXML
    private TextField a; //coeffeicient a pour Affine+Hill
    @FXML
    private TextField b; //coeffeicient b pour Affine+Hill
    @FXML
    private TextField c; //coeffeicient c pour Hill
    @FXML
    private TextField d; //coeffeicient d pour Hill

    //le choix entre chiffrer ou dechiffrer
    @FXML
    private RadioButton chiffrer;
    @FXML
    private RadioButton déchiffrer;
    private ToggleGroup ChiiffrerDechifrerToggleGrp;

    //le choix entre les algorithmes de cryptographie
    @FXML
    private RadioButton CezarRB;
    @FXML
    private RadioButton VigenaireRB;
    @FXML
    private RadioButton HillRB;
    @FXML
    private RadioButton PlayFaireRB;
    @FXML
    private RadioButton AffineRB;

    private ToggleGroup AlgorithmesDeCryptToggleGrp;
    
    @FXML
    private Button retourArriere;
    
    @FXML
    public void retourArriere(ActionEvent event){
        try {
            URL url=new File("src/fxtest/homePage.fxml").toURL();
            Parent chifSymParent=FXMLLoader.load(url);
            Scene chifSymScene=new Scene(chifSymParent);
            Stage app_stage=(Stage)((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(chifSymScene);
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ChiiffrerDechifrerToggleGrp = new ToggleGroup();   //
        this.chiffrer.setToggleGroup(ChiiffrerDechifrerToggleGrp);
        this.déchiffrer.setToggleGroup(ChiiffrerDechifrerToggleGrp);
        this.ChiiffrerDechifrerToggleGrp.selectToggle(chiffrer);

        AlgorithmesDeCryptToggleGrp = new ToggleGroup();
        this.CezarRB.setToggleGroup(AlgorithmesDeCryptToggleGrp);
        this.VigenaireRB.setToggleGroup(AlgorithmesDeCryptToggleGrp);
        this.HillRB.setToggleGroup(AlgorithmesDeCryptToggleGrp);
        this.PlayFaireRB.setToggleGroup(AlgorithmesDeCryptToggleGrp);
        this.AffineRB.setToggleGroup(AlgorithmesDeCryptToggleGrp);
        this.AlgorithmesDeCryptToggleGrp.selectToggle(CezarRB);

    }

    public int ChiffrerDéchiffrer() { //retourner le choix de l'utilisateur chifferer/dechiffrer
        if (this.ChiiffrerDechifrerToggleGrp.getSelectedToggle().equals(this.chiffrer)) {
            return 1;
        } else {
            return 2;
        }
    }

    public int AlgoChiffrement() { //retourner l'algorithme choisi par l'utilisateur
        if (this.AlgorithmesDeCryptToggleGrp.getSelectedToggle().equals(this.CezarRB)) {
            return 1;
        } else if (this.AlgorithmesDeCryptToggleGrp.getSelectedToggle().equals(this.VigenaireRB)) {
            return 2;
        } else if (this.AlgorithmesDeCryptToggleGrp.getSelectedToggle().equals(this.HillRB)) {
            return 3;
        } else if (this.AlgorithmesDeCryptToggleGrp.getSelectedToggle().equals(this.PlayFaireRB)) {
            return 4;
        } else {
            return 5;
        }
    }

    @FXML
    private void EncDec(ActionEvent event) {
        String claire = TextClaire.getText();
        String cléString = key.getText();
        String NULL = "";
        int cmp = cléString.compareTo("");  //pour verifier que le champ de text à traiter n'est pas vide
        int mode = ChiffrerDéchiffrer();   //on veut Déchiffrer le text ou le déchiffrer ! 
        int algo = AlgoChiffrement();     //quel Algo on utilise Cézar,Vigenaire,Hill,...?
        
        if (mode == 1) {//Chiffrement
            if (algo == 1) {//Cézar
                if (cmp != 0) {
                    try {
                        int clé = Integer.valueOf(key.getText());
                        Cezar cezar = new Cezar();
                        label.setStyle("-fx-background-color:white;");
                        label.setText("chiffrer avec  Cézar \n clé: " + cléString);
                        TextResult.setText(cezar.chiffrer(clé, claire));
                    } catch (Exception e) {
                        label.setText("La clé doit ètre un entier");
                        label.setStyle("-fx-background-color:red;");
                    }

                } else {
                    label.setText("Key TextField est vide");
                    label.setStyle("-fx-background-color:red;");
                }
            } else if (algo == 2) {//Vigenaire
                if (cmp != 0) {
                    try {
                        Vigenaire vigenaire = new Vigenaire();
                        label.setStyle("-fx-background-color:white;");
                        label.setText("chiffrer avec  Vigenaire \n clé: " + cléString);
                        TextResult.setText(vigenaire.chiffrer(cléString, claire));
                    } catch (Exception e) {
                        label.setText("Verifier la clé");
                        label.setStyle("-fx-background-color:red;");
                    }

                } else {
                    label.setText("Key TextField est vide");
                    label.setStyle("-fx-background-color:red;");
                }

            } else if (algo == 3) {//Hill
                String aString = a.getText();
                String bString = b.getText();
                String cString = c.getText();
                String dString = d.getText();

                int cmp2 = aString.compareTo("");
                int cmp3 = bString.compareTo("");
                int cmp4 = aString.compareTo("");
                int cmp5 = bString.compareTo("");
                if (cmp2 != 0 || cmp3 != 0 || cmp4 != 0 || cmp5 != 0) {
                    Hill hill = new Hill();
                    int ak = Integer.valueOf(a.getText());
                    int bk = Integer.valueOf(b.getText());
                    int ck = Integer.valueOf(c.getText());
                    int dk = Integer.valueOf(d.getText());
                    label.setStyle("-fx-background-color:white;");
                    label.setText("chiffrer avec  Hill \n clé \t a: " + ak + "\n    \t b:" + bk + "\n    \t c:" + ck + "\n    \t d:" + dk);
                    TextResult.setText(hill.chiffrer(ak, bk, ck, dk, claire));
                } else {
                    label.setText("le(s) champ(s) de a,b,c ou d \n est(sont) vide");
                    label.setStyle("-fx-background-color:red;");
                }
            } else if (algo == 4) {//PlayFaire
               if (cmp != 0) {
                    try {
                         playfaire pf = new playfaire();
                        label.setStyle("-fx-background-color:white;");
                        label.setText("chiffrer avec  Play Fair \n clé: " + cléString);
                        TextResult.setText(pf.chiffrer(cléString, claire));
                    } catch (Exception e) {
                        label.setText("Verifier la clé");
                        label.setStyle("-fx-background-color:red;");
                    }

                } else {
                    label.setText("Verifier la clé est vide");
                    label.setStyle("-fx-background-color:red;");
                }
            } else {//Affine
                String aString = a.getText();
                String bString = b.getText();
                int cmp2 = aString.compareTo("");
                int cmp3 = bString.compareTo("");

                if (cmp2 != 0 || cmp3 != 0) {
                    try {
                        ChiffAffine chiffrementAffine = new ChiffAffine();
                        int ak = Integer.valueOf(a.getText());
                        int bk = Integer.valueOf(b.getText());
                        label.setStyle("-fx-background-color:white;");
                        label.setText("dechiffrer avec  Affine \n clé \t a: " + ak + "\n    \t b:" + bk);
                        TextResult.setText(chiffrementAffine.chiffrer(ak, bk, claire));
                    } catch (Exception e) {
                        label.setText("Verifier a et b \n doivent \n etre des entiers");
                        label.setStyle("-fx-background-color:red;");
                    }

                } else {
                    label.setText("le(s) champ(s) de a ou b \n est(sont) vide");
                    label.setStyle("-fx-background-color:red;");
                }
            }

        } else if (mode == 2) {//DeChiffrement
            if (algo == 1) {//Cezar
                if (cmp != 0) {
                    try {
                        int clé = Integer.valueOf(key.getText());
                        Cezar cezar = new Cezar();
                        label.setStyle("-fx-background-color:white;");
                        label.setText("Déchiffrer avec  Cézar \n clé: " + cléString);
                        TextResult.setText(cezar.dechiffrer(clé, claire));
                    } catch (Exception e) {
                        label.setText("La clé doit ètre un entier");
                        label.setStyle("-fx-background-color:red;");
                    }

                } else {
                    label.setText("Key TextField est vide");
                    label.setStyle("-fx-background-color:red;");
                }
            } else if (algo == 2) {//Vigenaire
                if (cmp != 0) {
                    try {
                        Vigenaire vigenaire = new Vigenaire();
                        label.setStyle("-fx-background-color:white;");
                        label.setText("chiffrer avec  Vigenaire \n clé: " + cléString);
                        TextResult.setText(vigenaire.Dechiffrer(cléString, claire));
                    } catch (Exception e) {
                        label.setText("Verifier la clé");
                        label.setStyle("-fx-background-color:red;");
                    }

                } else {
                    label.setText("Key TextField est vide");
                    label.setStyle("-fx-background-color:red;");
                }
            } else if (algo == 3) {//Hill
                String aString = a.getText();
                String bString = b.getText();
                String cString = c.getText();
                String dString = d.getText();

                int cmp2 = aString.compareTo("");
                int cmp3 = bString.compareTo("");
                int cmp4 = aString.compareTo("");
                int cmp5 = bString.compareTo("");
                if (cmp2 != 0 || cmp3 != 0 || cmp4 != 0 || cmp5 != 0) {
                    Hill hill = new Hill();
                    int ak = Integer.valueOf(a.getText());
                    int bk = Integer.valueOf(b.getText());
                    int ck = Integer.valueOf(c.getText());
                    int dk = Integer.valueOf(d.getText());
                    label.setStyle("-fx-background-color:white;");
                    label.setText("Dechiffrer avec  Hill \n clé \t a: " + ak + "\n    \t b:" + bk + "\n    \t c:" + ck + "\n    \t d:" + dk);
                    TextResult.setText(hill.chiffrer(ak, bk, ck, dk, claire));//Dechiffrer vers HILL   
                } else {
                    label.setText("le(s) champ(s) de a,b,c ou d \n est(sont) vide");
                    label.setStyle("-fx-background-color:red;");
                }
            } else if (algo == 4) {//PlayFaire
                if (cmp != 0) {
                    try {
                         playfaire pf = new playfaire();
                        label.setStyle("-fx-background-color:white;");
                        label.setText("Dechiffrer avec  Play Fair \n clé: " + cléString);
                        TextResult.setText(pf.dechiffrer(cléString, claire));
                    } catch (Exception e) {
                        label.setText("Verifier la clé");
                        label.setStyle("-fx-background-color:red;");
                    }

                } else {
                    label.setText("Verifier la clé");
                    label.setStyle("-fx-background-color:red;");
                }
            } else {//Affine
                String aString = a.getText();
                String bString = b.getText();
                int cmp2 = aString.compareTo("");
                int cmp3 = bString.compareTo("");

                if (cmp2 != 0 || cmp3 != 0) {
                    try {
                        ChiffAffine chiffrementAffine = new ChiffAffine();
                        int ak = Integer.valueOf(a.getText());
                        int bk = Integer.valueOf(b.getText());
                        label.setStyle("-fx-background-color:white;");
                        label.setText("dechiffrer avec  Affine \n clé \t a: " + ak + "\n    \t b:" + bk);
                        TextResult.setText(chiffrementAffine.Dechiffrer(ak, bk, claire));
                    } catch (Exception e) {
                        label.setText("Verifier a et b \n doivent \n etre des entier");
                        label.setStyle("-fx-background-color:red;");
                    }

                } else {
                    label.setText("le(s) champ(s) de a ou b \n est(sont) vide");
                    label.setStyle("-fx-background-color:red;");
                }
            }

        }

    }

}
