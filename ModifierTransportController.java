/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connexioncultun.tests;

import edu.connexioncultun.entities.Transport;
import edu.connexioncultun.services.TransportCRUD;
import edu.connexioncultun.utils.controleSaisie;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Soulaymen
 */
public class ModifierTransportController implements Initializable {

    ObservableList<String> Horaires = FXCollections.observableArrayList("00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00");

    @FXML
    private TextField tfType;
    @FXML
    private TextField tfDepart;
    @FXML
    private TextField tfDestination;
    private TextField tfDate_dep;
    private TextField tfDate_arrivee;
    private TextField tfNbre_places;
    @FXML
    private TextField tfDuree;
    @FXML
    private ChoiceBox<String> cbHeure_dep;
    @FXML
    private ChoiceBox<String> cbHeure_arrivee;
    private TextField tfID;
    @FXML
    private DatePicker dpdep;
    @FXML
    private DatePicker dparr;
    @FXML
    private Spinner<Integer> sPlaces;
    @FXML
    private Spinner<Integer> sPrix;

    /*
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cbHeure_arrivee.setItems(Horaires);
        cbHeure_dep.setItems(Horaires);
        SpinnerValueFactory<Integer> ValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 80);
        // ValueFactory.setValue(1);
        sPlaces.setValueFactory(ValueFactory);
        SpinnerValueFactory<Integer> ValueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
        //ValueFactory2.setValue(1);
        sPrix.setValueFactory(ValueFactory2);
        TransportCRUD t = new TransportCRUD();

        ObservableList<Transport> listeTransport = FXCollections.observableArrayList();
        listeTransport = t.trierTransport(Transport.getIdd());
        listeTransport.get(0).getType();
        String date_arr = listeTransport.get(0).getDate_depart();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date1 = LocalDate.parse(date_arr, formatter);

        String date_arr1 = listeTransport.get(0).getDate_arrivee();

        LocalDate date2 = LocalDate.parse(date_arr1, formatter);

        tfType.setText(listeTransport.get(0).getType());
        tfDepart.setText(listeTransport.get(0).getPlace_depart());
        tfDestination.setText(listeTransport.get(0).getPlace_destination());
        dpdep.setValue(date1);
        dparr.setValue(date2);

        cbHeure_dep.setValue(listeTransport.get(0).getHeure_depart());
        cbHeure_arrivee.setValue(listeTransport.get(0).getHeure_arrivee());
        ValueFactory.setValue(listeTransport.get(0).getNbre_places());
        ValueFactory2.setValue(listeTransport.get(0).getPrix());
      

    }

    @FXML
    private void ModifierTransport(ActionEvent event) {
        controleSaisie cs = new controleSaisie();
        if (!cs.testAdr(tfDepart.getText())) {
            JOptionPane.showMessageDialog(null, "Insérer une adresse départ correcte ");
        } else if (!cs.testAdr(tfDestination.getText())) {
            JOptionPane.showMessageDialog(null, "Insérer une adresse destination correcte ");
        } else {
            TransportCRUD tra = new TransportCRUD();
            String datedep = dpdep.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String datearr = dparr.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            tra.modifierTransport(Transport.getIdd(), tfType.getText(), tfDepart.getText(), tfDestination.getText(), datedep, sPlaces.getValue(), datearr, sPrix.getValue(), cbHeure_dep.getValue(), cbHeure_arrivee.getValue());
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

    @FXML
    private void Back(ActionEvent event) {
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

    private void remplirTableModifier(ObservableList<Transport> listeTransport) {
        tfID.setText(new PropertyValueFactory<>("type"));
        tfType.setText(new PropertyValueFactory<>("type"));
        tfDepart.setText(new PropertyValueFactory<>("depart"));
        tfDestination.setText(new PropertyValueFactory<>("destination"));
        tfDate_dep.setText(new PropertyValueFactory<>("date_depart"));
        tfDate_arrivee.setText(new PropertyValueFactory<>("date_arrivee"));
        cbHeure_dep.setValue(new PropertyValueFactory<>("heure_depart"));
        cbHeure_arrivee.setValue(new PropertyValueFactory<>("heure_arrivee"));
        sPlaces.setValueFactory(new PropertyValueFactory<>("nbre_places"));
        sPrix.setValueFactory(new PropertyValueFactory<>("prix"));

    }

}
