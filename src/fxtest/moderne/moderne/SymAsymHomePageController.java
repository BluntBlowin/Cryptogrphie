/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxtest.moderne;

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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Blunt_Blowin
 */
public class SymAsymHomePageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button chiffSymetrique;  
    @FXML
    private Button chiffAsymetrique;
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
    @FXML
     public void goToSymetric(ActionEvent event){
        try {
            Parent chifSymParent=FXMLLoader.load(getClass().getResource("symetric/SymetricChiff.fxml"));
            Scene chifSymScene=new Scene(chifSymParent);
            Stage app_stage=(Stage)((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(chifSymScene);
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     @FXML
     public void goToAsymetric(ActionEvent event){
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
