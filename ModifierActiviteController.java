/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connexioncultun.tests;

import edu.connexioncultun.entities.Activite;
import edu.connexioncultun.services.ActiviteCRUD;
import edu.connexioncultun.utils.controleSaisie;
import java.awt.AWTException;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.management.Notification;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Soulaymen
 */
public class ModifierActiviteController implements Initializable {

    ObservableList<String> Horaires = FXCollections.observableArrayList("00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00");

    ObservableList<String> types = FXCollections.observableArrayList("Patrimoniale", "Ecotouristique", "Educatif", "Traditionnels", "Spirituels et religieux");
    @FXML
    private TextField tfNom;
    private TextField tfPrix;
    @FXML
    private TextField tfDuree;
    @FXML
    private TextField tfLocalisation;
    @FXML
    private ComboBox<String> cbHoraire;
    @FXML
    private ComboBox<String> cbType;

    private TextField tfDate;
    ActiviteCRUD act = new ActiviteCRUD();
    @FXML
    private DatePicker dpDate;
    @FXML
    private Spinner<Integer> sPrix;
    @FXML
    private Spinner<Integer> sNbre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        SpinnerValueFactory<Integer> ValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 80);
        sPrix.setValueFactory(ValueFactory);
        SpinnerValueFactory<Integer> ValueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
        sNbre.setValueFactory(ValueFactory2);
        sPrix.setValueFactory(ValueFactory);
        cbType.setItems(types);
        cbHoraire.setItems(Horaires);

        ActiviteCRUD t = new ActiviteCRUD();

        ObservableList<Activite> listeActivite = FXCollections.observableArrayList();
        listeActivite = t.trierActivite(Activite.getIdd());
        String dates = listeActivite.get(0).getDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date1 = LocalDate.parse(dates, formatter);
        tfNom.setText(listeActivite.get(0).getNom());
        tfDuree.setText(listeActivite.get(0).getDuree());
        tfLocalisation.setText(listeActivite.get(0).getLocalisation());
        dpDate.setValue(date1);
        ValueFactory.setValue(listeActivite.get(0).getPrix());
        cbType.setValue(listeActivite.get(0).getType());
        cbHoraire.setValue(listeActivite.get(0).getTemp());
        ValueFactory.setValue(listeActivite.get(0).getNbre_places());
        ValueFactory2.setValue(listeActivite.get(0).getPrix());

    }

    @FXML
    private void ModifierActivite(ActionEvent event) {

        controleSaisie cs = new controleSaisie();
        if (!cs.testAdr(tfLocalisation.getText())) {
            JOptionPane.showMessageDialog(null, "Insérer une adresse correct");
        } else {
            ActiviteCRUD act = new ActiviteCRUD();
            Activite a = new Activite();
            String date = dpDate.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            act.modifierActivite(tfNom.getText(), cbType.getValue(), tfLocalisation.getText(),
                    sPrix.getValue(), (int) sNbre.getValue(),
                    date, tfDuree.getText(), cbHoraire.getValue());
           //Notification.main("Activite !", "Activité modifié avec succé !!");  
            try {
                Parent root = FXMLLoader.load(getClass().getResource("AfficherActivite.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AfficherActivite.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /*  
@FXML
private void receiveData(MouseEvent event) {
        Class<? extends ActiviteCRUD> reference= this.act.getClass();
}*/
 /*  @FXML
private void receiveData(MouseEvent event) {
  int reference = ActiviteCRUD.getIdact(act);
}*/

    @FXML
    private void Back(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AfficherActivite.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficherActivite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
