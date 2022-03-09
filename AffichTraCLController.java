/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connexioncultun.tests;

import edu.connexioncultun.entities.Transport;
import edu.connexioncultun.services.TransportCRUD;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ListView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Soulaymen
 */
public class AffichTraCLController implements Initializable {

    @FXML
    private ListView<Transport> Ltra;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   TransportCRUD tra = new TransportCRUD();
        ObservableList<Transport> listeTransport = FXCollections.observableArrayList();
        listeTransport = tra.listerTransport();
        Ltra.getItems().addAll(listeTransport);
        
    }    
   /*   private void remplirTableTransport(ObservableList<Transport> listeTransport) {
        tcID.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        tcType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tcDepart.setCellValueFactory(new PropertyValueFactory<>("place_depart"));
        
        tcDestination.setCellValueFactory(new PropertyValueFactory<>("place_destination"));
        tcDate_depart.setCellValueFactory(new PropertyValueFactory<>("date_depart"));
        tcHeure_depart.setCellValueFactory(new PropertyValueFactory<>("heure_depart"));
        tcDate_arrivee.setCellValueFactory(new PropertyValueFactory<>("date_arrivee"));
        tcHeure_arrivee.setCellValueFactory(new PropertyValueFactory<>("heure_arrivee"));
        tcNombre_places.setCellValueFactory(new PropertyValueFactory<>("nbre_places"));
        tcPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        
        tvTransport.setItems(listeTransport);
        
    }*/

    @FXML
    private void allerRes(ActionEvent event) {
          try {
              TransportCRUD clc = new TransportCRUD();
            Transport cl = new Transport();
            cl = Ltra.getSelectionModel().getSelectedItem();
            Transport.setIdd(cl.getId());
            System.out.println(Transport.getIdd());
            Parent root=FXMLLoader.load(getClass().getResource("AjouterReservation.fxml"));
            Scene scene=new Scene(root);
            Stage stage=(Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ModifierTransport.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
}
