/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connexioncultun.tests;

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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Soulaymen
 */
public class MainInterfaceController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AfficherAct(ActionEvent event) {
          try {
            Parent root=FXMLLoader.load(getClass().getResource("AfficherActivite.fxml"));
            Scene scene=new Scene(root);
            Stage stage=(Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficherActivite.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void AfficherTra(ActionEvent event) {
          try {
            Parent root=FXMLLoader.load(getClass().getResource("AfficherTransport.fxml"));
            Scene scene=new Scene(root);
            Stage stage=(Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficherTransport.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void AffichRes(ActionEvent event) {
          try {
                Parent root = FXMLLoader.load(getClass().getResource("AfficherReservation.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AfficherReservation.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
