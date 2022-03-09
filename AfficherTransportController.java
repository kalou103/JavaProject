/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connexioncultun.tests;

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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Soulaymen
 */
public class AfficherTransportController implements Initializable {

    ActiviteCRUD pcd = new ActiviteCRUD();
    
    @FXML
    private TableView<Transport> tvTransport;
    @FXML
    private TableColumn<Transport, String> tcType;
    @FXML
    private TableColumn<Transport, String> tcDepart;
    @FXML
    private TableColumn<Transport, String> tcDestination;
    @FXML
    private TableColumn<Transport, String> tcDate_depart;
    @FXML
    private TableColumn<Transport, String> tcHeure_depart;
    @FXML
    private TableColumn<Transport, String> tcDate_arrivee;
    @FXML
    private TableColumn<Transport, String> tcHeure_arrivee;
    @FXML
    private TableColumn<Transport, Integer> tcNombre_places;
    @FXML
    private TableColumn<Transport, Integer> tcPrix;
    @FXML
    private TableColumn<Transport, Integer> tcID;
    @FXML
    private Button btnres;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        TransportCRUD tra = new TransportCRUD();
        ObservableList<Transport> listeTransport = FXCollections.observableArrayList();
        listeTransport = tra.listerTransport();
        remplirTableTransport(listeTransport);
    }    
    
    private void remplirTableTransport(ObservableList<Transport> listeTransport) {
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
        
    }
    
    @FXML
    private void supprimerTransport(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression Transport");
        alert.setHeaderText("Supprimer" + tvTransport.getSelectionModel().getSelectedItem().getId());
        alert.setContentText("Vous voulez vraiment supprimer ce transport? " + tvTransport.getSelectionModel().getSelectedItem().getId() + " ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            TransportCRUD clc = new TransportCRUD();
            Transport cl = new Transport();
            cl = tvTransport.getSelectionModel().getSelectedItem();
            clc.supprimerTransport(cl.getId());
            // Notification.main("Admin !", "Admin supprimé avec succé !!");       
            ObservableList<Transport> liste = FXCollections.observableArrayList();
            liste = clc.listerTransport();
            remplirTableTransport(liste);
        }
        if (result.get() == ButtonType.CANCEL) {
            alert.close();
        }
    }
    
    @FXML
    private void ModifierTransport(ActionEvent event) {
        
       try {
              TransportCRUD clc = new TransportCRUD();
            Transport cl = new Transport();
            cl = tvTransport.getSelectionModel().getSelectedItem();
            Transport.setIdd(cl.getId());
            System.out.println(Transport.getIdd());
            Parent root=FXMLLoader.load(getClass().getResource("ModifierTransport.fxml"));
            Scene scene=new Scene(root);
            Stage stage=(Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ModifierTransport.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    @FXML
    private void AlletAjout(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AjouterTransport.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterTransport.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    @FXML
    private void Back(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("MainInterface.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void placerres(ActionEvent event) {
        try {
              TransportCRUD clc = new TransportCRUD();
            Transport cl = new Transport();
            cl = tvTransport.getSelectionModel().getSelectedItem();
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
