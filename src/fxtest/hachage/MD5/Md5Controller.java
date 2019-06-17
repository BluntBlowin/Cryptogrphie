/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxtest.hachage.MD5;

import fxtest.hachage.classes.*;
import fxtest.HomePageController;
import static fxtest.hachage.classes.TypeHachage.MD5;
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
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Blunt_Blowin
 */
public class Md5Controller implements Initializable {

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
            URL url=new File("src/fxtest/hachage/hachageHomePage.fxml").toURL();
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
    private Button Hacher;
    @FXML
    private TextArea text;
    @FXML
    private TextArea textHache;
    @FXML
    private Label alert;
    
    public void hacher(ActionEvent event){
        if(text.getText().length()<1){
            alert.setText("champs du text est vide");
        }
        else{
         alert.setText("");
        md5HachClass m = new md5HachClass();
	textHache.setText(m.hacher(text.getText()));
        }

    }
}
