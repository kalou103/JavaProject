/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connexioncultun.tests;

import edu.connexioncultun.tests.AfficherActivite;
import edu.connexioncultun.entities.Activite;
import edu.connexioncultun.entities.Reservation;
import edu.connexioncultun.entities.Transport;
import edu.connexioncultun.services.ActiviteCRUD;
import edu.connexioncultun.services.ReservationCRUD;
import edu.connexioncultun.services.TransportCRUD;
import edu.connexioncultun.utils.controleSaisie;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import static java.sql.Types.NULL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Soulaymen
 */
public class AjouterReservationController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfNbre_places;
    @FXML
    private TextField tfNum_tel;
    @FXML
    private TextField tfEmail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void PlacerReservation(ActionEvent event) throws IOException, SQLException {
        int s;
        controleSaisie cs = new controleSaisie();
        if (!cs.testnomprenom(tfNom.getText())) {
            JOptionPane.showMessageDialog(null, "Insérer un nom correct");
        } else if (!cs.testnomprenom(tfPrenom.getText())) {
            JOptionPane.showMessageDialog(null, "Insérer un prénom correct");
        } else if ((!cs.GSM(tfNum_tel.getText()))) {
            JOptionPane.showMessageDialog(null, "Insérer un numéro valide SVP ");
        } else if ((!cs.mailformat(tfEmail.getText()))) {
            JOptionPane.showMessageDialog(null, "Insérer un Email valide SVP ");
        } else {

            ReservationCRUD pcd = new ReservationCRUD();
            Reservation a = new Reservation();
            ActiviteCRUD act = new ActiviteCRUD();
            Activite ac = new Activite();
            TransportCRUD tra = new TransportCRUD();
            Transport tr = new Transport();
            a.setNom_cl(tfNom.getText());
            a.setPrenom_cl(tfPrenom.getText());
if (Transport.getIdd() > 0) {
                a.setReftra(Transport.getIdd());
                 s=tra.GetPrix(Transport.getIdd());
                 tra.EliminerPlacestr(Transport.getIdd(), Integer.parseInt(tfNbre_places.getText()));
               // tra.EliminerPlacestr(Transport.getIdd(), Integer.parseInt(tfNbre_places.getText()));
            } else {
                a.setReftra(0);
              s=0;
            };
            a.setNbr_places(Integer.parseInt(tfNbre_places.getText()));
            act.EliminerPlaces(Activite.getIdd(), Integer.parseInt(tfNbre_places.getText()));
            a.setTel_cl(Integer.parseInt(tfNum_tel.getText()));
            a.setPrix_res((act.GetPrix(Activite.getIdd())+s) * Integer.parseInt(tfNbre_places.getText()));
 act.EliminerPlaces(Activite.getIdd(), Integer.parseInt(tfNbre_places.getText()));
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            String strDate = dateFormat.format(date);
           
            a.setDate_res(strDate);
            a.setEmail_cl(tfEmail.getText());
            a.setRefact(Activite.getIdd());

            pcd.ajouterReservation(a);

            //act.EliminerPlaces(Activite.getIdd(), Integer.parseInt(tfNbre_places.getText()));
            try {
                Parent root = FXMLLoader.load(getClass().getResource("AfficherReservation.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AfficherActivite.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    

    @FXML
    private void back(ActionEvent event) {
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
