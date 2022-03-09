/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connexioncultun.services;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.jdbc.Connection;
import com.sun.scenario.effect.ImageData;
import edu.connexioncultun.entities.Reservation;
import edu.connexioncultun.utils.MyConnection;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 *
 * @author Soulaymen
 */
public class ReservationCRUD {

    private String deriterio;
    private static final String DIR = "QRDir";

    public void ajouterReservation(Reservation a) {
        try {
            String requete = "INSERT INTO reservation (nom_cl,prenom_cl,date_res,prix_res,nbre_places,tel_cl,email_cl,refact,reftra) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);

            pst.setString(1, a.getNom_cl());
            pst.setString(2, a.getPrenom_cl());
            pst.setString(3, a.getDate_res());
            pst.setInt(4, a.getPrix_res());
            pst.setInt(5, a.getNbr_places());
            pst.setInt(6, a.getTel_cl());
            pst.setString(7, a.getEmail_cl());
            pst.setInt(8, a.getRefact());
            pst.setInt(9, a.getReftra());

            pst.executeUpdate();
            System.err.println("Element ajoutée!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ObservableList<Reservation> listerReservation() {
        ObservableList<Reservation> ReservationList = FXCollections.observableArrayList();
        try {
            String requete = "SELECT * FROM reservation";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {

                Reservation res = new Reservation();
                res.setId(rs.getInt("id"));
                res.setNom_cl(rs.getString("nom_cl"));
                res.setPrenom_cl(rs.getString("prenom_cl"));
                res.setDate_res(rs.getString("date_res"));
                res.setPrix_res(rs.getInt("prix_res"));
                res.setNbr_places(rs.getInt("nbre_places"));
                res.setTel_cl(rs.getInt("tel_cl"));
                res.setEmail_cl(rs.getString("email_cl"));

                ReservationList.add(res);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ReservationList;
    }

    public void modifierReservation(String nom_cl, String prenom_cl, String date_res, int prix_res, int nbr_places, int tel_cl, String email_cl) {
        try {
            String requete = "UPDATE reservation SET"
                    + " `nom_cl`='" + nom_cl + "' , `prenom_cl`='" + prenom_cl + "'"
                    + " , `nbre_places`='" + nbr_places + "',`prix_res`='" + prix_res + "' , `date_res`='" + date_res + "' ,"
                    + " `tel_cl`='" + tel_cl + "' , `email_cl`='" + email_cl + "' WHERE nom_cl='" + nom_cl + "'";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.executeUpdate();
            System.err.println("Update Done !!");
        } catch (SQLException ex) {
            Logger.getLogger(ReservationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void supprimerReservation(int id) {
        try {
            String requete = "DELETE from reservation where id='" + id + "'";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.executeUpdate();
            System.err.println("Réservation supprimer");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    //----------------------------------metier-------------------------------------------
    //--------------------------------tri selon le client--------------------------------
    public List<Reservation> trierReservation(String nom_cl, String prenom_cl) {
        List<Reservation> ReservationList = new ArrayList();
        try {
            String requete = "SELECT * FROM reservation where `nom_cl`='" + nom_cl + "and prenom_cl='" + prenom_cl + "' ";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {

                Reservation res = new Reservation();
                res.setNom_cl(rs.getString("nom_cl"));
                res.setPrenom_cl(rs.getNString("prenom_cl"));
                res.setDate_res(rs.getString("date_res"));
                res.setPrix_res(rs.getInt("prix_res"));
                res.setNbr_places(rs.getInt("nbr_places"));
                res.setTel_cl(rs.getInt("tel_cl"));
                res.setEmail_cl(rs.getString("email_cl"));

                ReservationList.add(res);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ReservationList;
    }

    public void toPDFCRUD(int id) {
        try {
            initi(id);
        } catch (SQLException ex) {
            Logger.getLogger(ReservationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReservationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\Soulaymen\\Desktop\\pdf\\Reservation.pdf"));
            doc.open();
            Paragraph p = new Paragraph();
            p.add("Voucher activite");
            doc.add(p);

            PdfPTable t = new PdfPTable(5);
            PdfPCell c = new PdfPCell(new Phrase("Nom client :"));
            t.addCell(c);
            c = new PdfPCell(new Phrase("Prénom client :"));
            t.addCell(c);
            c = new PdfPCell(new Phrase("Nombre de places :"));
            t.addCell(c);
            c = new PdfPCell(new Phrase("Date de réservation :"));
            t.addCell(c);
            c = new PdfPCell(new Phrase("Téléphone :"));
            t.addCell(c);

            doc.add(t);

            String requete = "SELECT * FROM reservation  where id='" + id + "'";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
  Paragraph p4 = null;
            PdfPTable table2 = new PdfPTable(4);
            Paragraph p3 = null;
            PdfPTable table = new PdfPTable(5);
            while (rs.next()) {

                PdfPCell c1 = new PdfPCell(new Phrase(rs.getString("nom_cl")));
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase(rs.getString("prenom_cl")));
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase(String.valueOf(rs.getInt("nbre_places"))));
                table.addCell(c1);
                c1 = new PdfPCell(new Phrase(rs.getString("date_res")));
                table.addCell(c1);
                c1 = new PdfPCell(new Phrase(String.valueOf(rs.getInt("tel_cl"))));
                table.addCell(c1);

                PdfPCell c3 = new PdfPCell(new Phrase(rs.getString("email_cl")));
                table2.addCell(c3);
               
                c3 = new PdfPCell(new Phrase(String.valueOf(rs.getInt("refact"))));
                table2.addCell(c3);
                c3 = new PdfPCell(new Phrase(String.valueOf(rs.getInt("reftra"))));
                table2.addCell(c3);
                c3 = new PdfPCell(new Phrase(String.valueOf(rs.getInt("prix_res"))));
                table2.addCell(c3);
            }
                        doc.add(table);


        /*   PdfWriter.getInstance(doc, os)
             String imageFile = "C:\\Users\\Soulaymen\\Desktop\\pdf\\qrcode.png"; 
             */

            PdfPTable t2 = new PdfPTable(4);
            PdfPCell c2 = new PdfPCell(new Phrase("Email :"));
            t2.addCell(c2);

            c2 = new PdfPCell(new Phrase("Ref. activité :"));
            t2.addCell(c2);
            c2 = new PdfPCell(new Phrase("Ref. transport :"));

            t2.addCell(c2);
            c2 = new PdfPCell(new Phrase("Montant a payer :"));
            t2.addCell(c2);
            doc.add(t2);

          
            doc.add(table2);

            doc.close();

            //BinaryBitmap binaryBitmap= new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(new FileInputStream("C:\\Users\\Soulaymen\\Desktop\\pdf\\qrcode.png")))));
            Desktop.getDesktop().open(new File("C:\\Users\\Soulaymen\\Desktop\\pdf\\Reservation.pdf"));
        } catch (Exception e) {
            System.err.print(e);
        }
        try {
            initi(id);
        } catch (Exception e) {
            System.out.println("lenna");
        };

    }

    private void initi(int id) throws SQLException, IOException {

    

        try {

            String requete = "SELECT * FROM reservation  where id='" + id + "'";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            String cnt_qrcode = "";
            cnt_qrcode += "Nom:" + rs.getString("nom_cl") + "\n";
            cnt_qrcode += "Prenom:" + rs.getString("prenom_cl") + "\n";
            cnt_qrcode += "Nombre de places:" + String.valueOf(rs.getInt("nbre_places")) + "\n";
            cnt_qrcode += "Date de reservation:" + rs.getString("date_res") + "\n";
            cnt_qrcode += "Numéro télephone :" + String.valueOf(rs.getInt("tel_cl")) + "\n";
            cnt_qrcode += "email:" + rs.getString("email_cl") + "\n";
            cnt_qrcode += "Reference activité :" + rs.getInt("refact") + "\n";
            cnt_qrcode += "Reference transport :" + String.valueOf(rs.getInt("reftra")) + "\n";
            cnt_qrcode += "Reference activité :" + String.valueOf(rs.getInt("prix_res")) + "\n";

            FileOutputStream fout = new FileOutputStream("C:\\Users\\Soulaymen\\Desktop\\pdf\\qrcode.png");
            ByteArrayOutputStream bos = QRCode.from(cnt_qrcode).withSize(125, 125).to(ImageType.PNG).stream();
            fout.write(bos.toByteArray());
            bos.close();
            fout.close();
            fout.flush();
            Image image = new Image(new FileInputStream("C:\\Users\\Soulaymen\\Desktop\\pdf\\qrcode.png"));
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }

    }

 

}
