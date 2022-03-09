/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connexioncultun.tests;

import edu.connexioncultun.entities.Activite;
import edu.connexioncultun.services.ActiviteCRUD;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Soulaymen
 */
public class AfficherActiviteController implements Initializable {

    ActiviteCRUD pcd = new ActiviteCRUD();
    @FXML
    private TableView<Activite> tvActivite;

    // New Table View
    TableView tbv = new TableView();
// Create two columns 
    @FXML
    private TableColumn<Activite, String> tcNom;
    @FXML
    private TableColumn<Activite, String> tcType;
    @FXML
    private TableColumn<Activite, String> tcDate;
    @FXML
    private TableColumn<Activite, String> tcLocalisation;
    @FXML
    private AnchorPane apActivite;
    @FXML
    private TableColumn<Activite, Integer> tcNombreplaces;
    @FXML
    private TableColumn<Activite, Integer> tcPrix;
    @FXML
    private TableColumn<Activite, String> tcDuree;
    @FXML
    private TableColumn<Activite, String> tcHoraire;
    @FXML
    private ChoiceBox<String> cbRecherche;
    @FXML
    private TextField tfRecherche;
    @FXML
    private TableColumn<Activite, String> tcRef;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ActiviteCRUD act = new ActiviteCRUD();
        ObservableList<Activite> listeActivite = FXCollections.observableArrayList();
        listeActivite = act.listerActivite();
        remplirTableActivite(listeActivite);
    }
    private void AfficherActiviteList(ActionEvent event) {
        apActivite.toFront();
    }
    private void remplirTableActivite(ObservableList<Activite> listeActivites) {
         tcRef.setCellValueFactory(new PropertyValueFactory<>("Reference"));
        tcNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        tcType.setCellValueFactory(new PropertyValueFactory<>("Type"));

        tcDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        tcLocalisation.setCellValueFactory(new PropertyValueFactory<>("Localisation"));
                tcNombreplaces.setCellValueFactory(new PropertyValueFactory<>("Nbre_places"));
        tcPrix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        tcDuree.setCellValueFactory(new PropertyValueFactory<>("Duree"));
        tcHoraire.setCellValueFactory(new PropertyValueFactory<>("temp"));

            tvActivite.setItems(listeActivites);
            

    }

    @FXML
    private void supprimerActivite(ActionEvent event) {
          Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression Activite");
        alert.setHeaderText("Supprimer "+tvActivite.getSelectionModel().getSelectedItem().getNom());
        alert.setContentText("Vous voulez vraiment supprimer l'admin " +tvActivite.getSelectionModel().getSelectedItem().getNom() + " ?");
        Optional<ButtonType> result =alert.showAndWait();
        if(result.get()==ButtonType.OK){
            ActiviteCRUD clc=new ActiviteCRUD();
            Activite cl=new Activite();
            cl=tvActivite.getSelectionModel().getSelectedItem();
            clc.supprimerActivite(cl.getReference());
               // Notification.main("Admin !", "Admin supprimé avec succé !!");       
                ObservableList<Activite> liste=FXCollections.observableArrayList();
                liste=clc.listerActivite();
                remplirTableActivite(liste);
        }
        if(result.get()==ButtonType.CANCEL){
            alert.close();
        }
    }

    @FXML
    private void AllerAjout(ActionEvent event) {
          try {
            Parent root=FXMLLoader.load(getClass().getResource("AjoutActivite.fxml"));
            Scene scene=new Scene(root);
            Stage stage=(Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjoutActivite.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    
    @FXML
    private void AllerModif(ActionEvent event) {
         try { 
            
               ActiviteCRUD clc = new ActiviteCRUD();
            Activite cl = new Activite();
            cl = tvActivite.getSelectionModel().getSelectedItem();
            Activite.setIdd(cl.getReference());
            System.out.println(Activite.getIdd());
            Parent root=FXMLLoader.load(getClass().getResource("ModifierActivite.fxml"));
            Scene scene=new Scene(root);
            Stage stage=(Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
             System.out.println(IOException.class);
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
    private void alleres(ActionEvent event) {
        
        
         Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Ajout reservation");
        alert.setHeaderText("Ajouter un transport ");
        alert.setContentText("Vous voulez ajouter un transport ?");
        Optional<ButtonType> result =alert.showAndWait();
        if(result.get()==ButtonType.OK){
          try{  Activite cl = new Activite();
            cl = tvActivite.getSelectionModel().getSelectedItem();
            Activite.setIdd(cl.getReference());
            System.out.println(Activite.getIdd());
            Parent root=FXMLLoader.load(getClass().getResource("AfficherTransport.fxml"));
            Scene scene=new Scene(root);
            Stage stage=(Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();}
          catch (IOException ex){}
        }
        if(result.get()==ButtonType.CANCEL){
              try { ActiviteCRUD clc = new ActiviteCRUD();
            Activite cl = new Activite();
            cl = tvActivite.getSelectionModel().getSelectedItem();
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
