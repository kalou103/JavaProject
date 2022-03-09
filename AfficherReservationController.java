/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connexioncultun.tests;

import edu.connexioncultun.entities.Reservation;
import edu.connexioncultun.services.ReservationCRUD;
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
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.Segment;

/**
 * FXML Controller class
 *
 * @author Soulaymen
 */
public class AfficherReservationController implements Initializable {

    @FXML
    private TableColumn<Reservation, String> tcID;
    @FXML
    private TableColumn<Reservation, String> tcNom;
    @FXML
    private TableColumn<Reservation, String> tcPrenom;
    @FXML
    private TableColumn<Reservation, String> tcNombre;
    @FXML
    private TableColumn<Reservation, String> tcDate_res;
    @FXML
    private TableColumn<Reservation, String> tcNum_tel;
    @FXML
    private TableColumn<Reservation, String> tcEmail;
    @FXML
    private TableView<Reservation> tvReservation;
    @FXML
    private TableColumn<Reservation, String> tcPrix;
    @FXML
    private Button btnPDF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ReservationCRUD res = new ReservationCRUD();
        ObservableList<Reservation> listeReservation = FXCollections.observableArrayList();
        listeReservation = res.listerReservation();
        remplirTableReservation(listeReservation);
    }

    private void remplirTableReservation(ObservableList<Reservation> listeReservation) {
        tcID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcNom.setCellValueFactory(new PropertyValueFactory<>("nom_cl"));

        tcPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom_cl"));
        tcNombre.setCellValueFactory(new PropertyValueFactory<>("nbr_places"));
        tcPrix.setCellValueFactory(new PropertyValueFactory<>("prix_res"));

        tcDate_res.setCellValueFactory(new PropertyValueFactory<>("date_res"));
        tcNum_tel.setCellValueFactory(new PropertyValueFactory<>("tel_cl"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("email_cl"));
       

        tvReservation.setItems(listeReservation);

    }

    @FXML
    private void SupprimerReservation(ActionEvent event) {
          Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression reservation");
        alert.setHeaderText("Supprimer "+tvReservation.getSelectionModel().getSelectedItem().getId());
        alert.setContentText("Vous voulez vraiment supprimer la réservation de " +tvReservation.getSelectionModel().getSelectedItem().getNom_cl()+ " ?");
        Optional<ButtonType> result =alert.showAndWait();
        if(result.get()==ButtonType.OK){
            ReservationCRUD clc=new ReservationCRUD();
            Reservation cl=new Reservation();
            cl=tvReservation.getSelectionModel().getSelectedItem();
            clc.supprimerReservation(cl.getId());
               // Notification.main("Admin !", "Admin supprimé avec succé !!");       
                ObservableList<Reservation> liste=FXCollections.observableArrayList();
                liste=clc.listerReservation();
                remplirTableReservation(liste);
        }
        if(result.get()==ButtonType.CANCEL){
            alert.close();
        }
    }

    @FXML
    private void AllerModifier(ActionEvent event) {
         try {
            Parent root=FXMLLoader.load(getClass().getResource("ModifierReservation.fxml"));
            Scene scene=new Scene(root);
            Stage stage=(Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ModifierReservation.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void AllerAjouter(ActionEvent event) {
         try {
            Parent root=FXMLLoader.load(getClass().getResource("AjouterReservation.fxml"));
            Scene scene=new Scene(root);
            Stage stage=(Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ModifierReservation.class.getName()).log(Level.SEVERE, null, ex);
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

  /*  @FXML
    private void toPDF(ActionEvent event) {
       try { 
                 
              
                Document doc = new Document();
                PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\Dhia\\OneDrive\\Bureau\\produit.pdf"));
                doc.open();
                 Paragraph p =new Paragraph();
                p.add("liste de produit");
             doc.add(p);
                 PdfPTable t = new PdfPTable(4);
            PdfPCell c = new PdfPCell(new Phrase("prix"));
            t.addCell(c);
             c = new PdfPCell(new Phrase("quantite"));
            t.addCell(c);
              c = new PdfPCell(new Phrase("date expiration"));
            t.addCell(c);
              c = new PdfPCell(new Phrase("type"));
            t.addCell(c);
            doc.add(t);
               
            
              Connection cnx =Myconnection.getInstance().getCnx();
            String query = "select * from Produit";
            Statement stmt = null;
            stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            Paragraph p3 = null;
                PdfPTable table = new PdfPTable(4);
               while(rs.next()){ 
           
                 
            PdfPCell  c1 = new PdfPCell(new Phrase(rs.getString("prix")));
            table.addCell(c1);
                 
             c1 = new PdfPCell(new Phrase(rs.getString("quantite")));
            table.addCell(c1);
                 
             c1 = new PdfPCell(new Phrase(rs.getString("date_exp")));
            table.addCell(c1);
             c1 = new PdfPCell(new Phrase(rs.getString("type")));
            table.addCell(c1);
             
               }
          
         // table.setHeaderRows(1);
//         ObservableList<Produit> list = (ObservableList<Produit>) ps.afficherProduit();
 //  colids.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("id"));
//      colprix.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("prix"));
//      colquan.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("quantite"));
//      date_exp.setCellValueFactory(new PropertyValueFactory<Produit,Date>("date_exp"));
//      coltype.setCellValueFactory(new PropertyValueFactory<Produit,String>("type"));
//     
//        tab_s.setItems(list);
       
//        table.addCell("");
//   table.addCell("1.1");
//             table.addCell("1.2");
//               table.addCell("2.1");
//                table.addCell("2.2");
//                 table.addCell("2.3");
                doc.add(table);
                
                doc.close();
                Desktop.getDesktop().open(new File("C:\\Users\\Dhia\\OneDrive\\Bureau\\produit.pdf"));
             } catch (Exception e) {
          System.err.print(e);
        }
  
         */

    @FXML
    private void toPDF(ActionEvent event) {
        ReservationCRUD r= new ReservationCRUD();
        r.toPDFCRUD(tvReservation.getSelectionModel().getSelectedItem().getId());
    }
    
    

}
