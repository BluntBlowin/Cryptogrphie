/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxtest;

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
public class HomePageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button chiffClassBoutton;
    
    @FXML
    private Button chiffModBoutton;
    @FXML
    private Button hachageBoutton;
    @FXML
    public void goToChiffClassic(ActionEvent event){
        try {
            Parent chifSymParent=FXMLLoader.load(getClass().getResource("classique/FXMLDocument.fxml"));
            Scene chifSymScene=new Scene(chifSymParent);
            Stage app_stage=(Stage)((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(chifSymScene);
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void goToChiffModern(ActionEvent event){
        try {
            Parent chifSymParent=FXMLLoader.load(getClass().getResource("moderne/SymAsymHomePage.fxml"));
            Scene chifSymScene=new Scene(chifSymParent);
            Stage app_stage=(Stage)((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(chifSymScene);
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     @FXML
    public void goToHachage(ActionEvent event){
        try {
            Parent chifSymParent=FXMLLoader.load(getClass().getResource("hachage/hachageHomePage.fxml"));
            Scene chifSymScene=new Scene(chifSymParent);
            Stage app_stage=(Stage)((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(chifSymScene);
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     @FXML
    public void goToCryptanalyse(ActionEvent event){
        try {
            Parent chifSymParent=FXMLLoader.load(getClass().getResource("cryptanalyse/cryptanalyse.fxml"));
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
        // TODO(
    }    
    
}
