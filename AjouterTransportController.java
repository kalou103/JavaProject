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
import edu.connexioncultun.utils.controleSaisie;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Soulaymen
 */
public class AjouterTransportController implements Initializable {
  ObservableList<String> Horaires = FXCollections.observableArrayList("00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00");

    @FXML
    private TextField tfType;
    @FXML
    private TextField tfDepart;
    @FXML
    private TextField tfDestination;
    private TextField tfDate_dep;
    private TextField tfNbre_places;
    @FXML
    private TextField tfDuree;
    private TextField tfPrix;
    @FXML
    private ChoiceBox<String> cbHeure_dep;
    private TextField tfDate_arrivee;
    @FXML
    private ChoiceBox<String> cbHeure_arrivee;
    @FXML
    private DatePicker dpdep;
    @FXML
    private DatePicker dparr;
    @FXML
    private Spinner<Integer> sPlaces;
    @FXML
    private Spinner<Integer> sPrix;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         cbHeure_dep.setItems(Horaires);
        cbHeure_dep.setValue("00:00");
         cbHeure_arrivee.setItems(Horaires);
        cbHeure_arrivee.setValue("00:00");
          SpinnerValueFactory<Integer> ValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 80);
        ValueFactory.setValue(1);
        sPlaces.setValueFactory(ValueFactory);
          SpinnerValueFactory<Integer> ValueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
        ValueFactory2.setValue(1);
        sPrix.setValueFactory(ValueFactory2);
    }    

    @FXML
    private void AjouterTransport(ActionEvent event) {
         controleSaisie cs = new controleSaisie();
        if(!cs.testAdr(tfDepart.getText())){
            JOptionPane.showMessageDialog(null,"Insérer une adresse départ correcte ");
        }
        else if(!cs.testAdr(tfDestination.getText())){
            JOptionPane.showMessageDialog(null,"Insérer une adresse destination correcte ");
        }
       
        
        else{
        TransportCRUD pcd = new TransportCRUD();
        Transport t = new Transport();
        
        t.setType(tfType.getText());
        t.setPlace_depart(tfDepart.getText());
        t.setPlace_destination(tfDestination.getText());
        String datedep = dpdep.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String datearr = dparr.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        t.setDate_depart(datedep);
        t.setDate_arrivee(datearr);
                t.setPrix(sPrix.getValue());
        t.setNbre_places(sPlaces.getValue());
        t.setHeure_depart(cbHeure_dep.getValue());
                t.setHeure_arrivee(cbHeure_arrivee.getValue());


        

        pcd.ajouterTransport(t);
           try {
            Parent root=FXMLLoader.load(getClass().getResource("AfficherTransport.fxml"));
            Scene scene=new Scene(root);

            Stage stage=(Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficherTransport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }}

    @FXML
    private void back(ActionEvent event) {
          try {
                Parent root = FXMLLoader.load(getClass().getResource("AfficherTransport.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AfficherTransport.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
