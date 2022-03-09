/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connexioncultun.tests;

import edu.connexioncultun.tests.AfficherActivite;
import edu.connexioncultun.entities.Activite;
import edu.connexioncultun.services.ActiviteCRUD;
import edu.connexioncultun.utils.controleSaisie;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
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
public class AjoutActiviteController implements Initializable {

    ObservableList<String> Horaires = FXCollections.observableArrayList("00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00");

    ObservableList<String> types = FXCollections.observableArrayList("Patrimoniale", "Ecotouristique", "Educatif", "Traditionnels", "Spirituels et religieux");
    @FXML
    private TextField tfNom;
    private TextField tfPrix;
    @FXML
    private TextField tfLocalisation;

    @FXML
    private ChoiceBox<String> cbType;
    private Slider sNombreplaces;
    @FXML
    private Button btnAjouter;
    @FXML
    private TextField tfDuree;
    @FXML
    private ChoiceBox<String> cbHoraire;
    private TextField tfDate;
    private TextField tfNbre_place;
    @FXML
    private DatePicker dpDate;
    @FXML
    private Spinner<Integer> snbr;
    @FXML
    private Spinner<Integer> sPrix;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        SpinnerValueFactory<Integer> ValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 80);
        ValueFactory.setValue(1);
        snbr.setValueFactory(ValueFactory);
        SpinnerValueFactory<Integer> ValueFactoryprix = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 200);
        ValueFactoryprix.setValue(1);
        sPrix.setValueFactory(ValueFactoryprix);
        cbType.setItems(types);
        cbType.setValue("Patrimoniale");
        cbHoraire.setItems(Horaires);
        cbHoraire.setValue("00:00");
    }

    @FXML
    private void AjouterActivite(ActionEvent event) throws IOException {
        //String pattern = "MM/dd/yyyy";
       // DateFormat df = new SimpleDateFormat(pattern);

       // String datepickerAsString = df.format(dpDate.getValue());
       
       controleSaisie cs = new controleSaisie();
        if(!cs.testAdr(tfLocalisation.getText())){
            JOptionPane.showMessageDialog(null,"Insérer une adresse correct");
 
        }
        else{
         ActiviteCRUD pcd = new ActiviteCRUD();
        Activite a = new Activite();
        
        a.setNom(tfNom.getText());
        a.setType(cbType.getValue());
        a.setPrix(sPrix.getValue());
String date = dpDate.getValue().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        a.setDate(date);
        a.setTemp(cbHoraire.getValue());
        a.setDuree(tfDuree.getText());
        a.setLocalisation(tfLocalisation.getText());
        a.setNbre_places(snbr.getValue());
          /* a.setImage(file_image);

        pathfrom = FileSystems.getDefault().getPath(Current_file.getPath());
        pathto = FileSystems.getDefault().getPath("C:\\xampp\\htdocs\\imgproj\\images\\" + Current_file.getName());
        Path targetDir = FileSystems.getDefault().getPath("C:\\xampp\\htdocs\\imgproj\\images\\");*/

        pcd.ajouterActivite(a);
            try {
            Parent root=FXMLLoader.load(getClass().getResource("AfficherActivite.fxml"));
            Scene scene=new Scene(root);

            Stage stage=(Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficherActivite.class.getName()).log(Level.SEVERE, null, ex);
        }
        
/*
        ActiviteCRUD pcd = new ActiviteCRUD();
        Activite a = new Activite();
        
        a.setNom(tfNom.getText());
        a.setType(cbType.getValue());
        a.setPrix(Integer.parseInt(tfPrix.getText()));
        a.setDate(tfDate.getText());
        a.setTemp(cbHoraire.getValue());
        a.setDuree(tfDuree.getText());
        a.setLocalisation(tfLocalisation.getText());
        a.setNbre_places(Integer.parseInt(tfNbre_place.getText()));

        pcd.ajouterActivite(a);

        //  System.out.println(pcd.trierActivite("VisiteColisee"));*/
    }

}

    @FXML
    private void back(ActionEvent event) {
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
