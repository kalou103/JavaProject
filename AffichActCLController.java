/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connexioncultun.tests;

import edu.connexioncultun.entities.Activite;
import edu.connexioncultun.entities.Transport;
import edu.connexioncultun.services.ActiviteCRUD;
import edu.connexioncultun.services.TransportCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Soulaymen
 */
public class AffichActCLController implements Initializable {

    @FXML
    private ListView<Activite> Lact;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ActiviteCRUD act = new ActiviteCRUD();
        ObservableList<Activite> listeActivite = FXCollections.observableArrayList();
        listeActivite = act.listerActivite2();
        Lact.getItems().addAll(listeActivite);
        
    }    

    @FXML
    private void placerRes(ActionEvent event) {
           
        
         Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Ajout reservation");
        alert.setHeaderText("Ajouter un transport ");
        alert.setContentText("Vous voulez ajouter un transport ?");
        Optional<ButtonType> result =alert.showAndWait();
        if(result.get()==ButtonType.OK){
          try{  Activite cl = new Activite();
           // cl = tvActivite.getSelectionModel().getSelectedItem();
            Activite.setIdd(cl.getReference());
            System.out.println(Activite.getIdd());
            Parent root=FXMLLoader.load(getClass().getResource("AffichTraCL.fxml"));
            Scene scene=new Scene(root);
            Stage stage=(Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();}
          catch (IOException ex){}
        }
        if(result.get()==ButtonType.CANCEL){
              try { ActiviteCRUD clc = new ActiviteCRUD();
            Activite cl = new Activite();
          //  cl = tvActivite.getSelectionModel().getSelectedItem();
            Activite.setIdd(cl.getReference());
            System.out.println(Activite.getIdd());
            Parent root=FXMLLoader.load(getClass().getResource("AjouterReservation.fxml"));
            Scene scene=new Scene(root);
            Stage stage=(Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ModifierReservation.class.getName()).log(Level.SEVERE, null, ex);
        } 
        }
        
        
    }
    
    
}
