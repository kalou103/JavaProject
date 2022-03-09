/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connexioncultun.services;

import edu.connexioncultun.entities.Activite;
import edu.connexioncultun.utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Soulaymen
 */
public class ActiviteCRUD {

    public void ajouterActivite(Activite a) {
        try {
            String requete = "INSERT INTO activite (type,localisation,nbre_places,prix,date,duree,temp,nom,image) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);

            pst.setString(1, a.getType());
            pst.setString(2, a.getLocalisation());
            pst.setInt(3, a.getNbre_places());
            pst.setInt(4, a.getPrix());
            pst.setString(5, a.getDate());
            pst.setString(6, a.getDuree());
            pst.setString(7, a.getTemp());
            pst.setString(8, a.getNom());
            pst.setString(9, a.getImage());

            pst.executeUpdate();
            System.err.println("Element ajoutée!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

     public ObservableList<Activite> listerActivite2() {
        ObservableList<Activite> ActiviteList = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM activite";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {

                Activite act = new Activite();
                act.setNom(rs.getString("nom"));
                act.setType(rs.getString("type"));
                act.setLocalisation(rs.getString("localisation"));
                act.setNbre_places(rs.getInt("nbre_places"));
                act.setPrix(rs.getInt("prix"));
                act.setDate(rs.getString("date"));
                act.setDuree(rs.getString("duree"));
                act.setReference(rs.getInt("reference"));
                act.setTemp(rs.getString("temp"));
                act.setImage(rs.getString("image"));
                ActiviteList.add(act);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ActiviteList;
    }
    public ObservableList<Activite> listerActivite() {
        ObservableList<Activite> ActiviteList = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM activite";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {

                Activite act = new Activite();
                act.setNom(rs.getString("nom"));
                act.setType(rs.getString("type"));
                act.setLocalisation(rs.getString("localisation"));
                act.setNbre_places(rs.getInt("nbre_places"));
                act.setPrix(rs.getInt("prix"));
                act.setDate(rs.getString("date"));
                act.setDuree(rs.getString("duree"));
                act.setReference(rs.getInt("reference"));
                act.setTemp(rs.getString("temp"));
                act.setImage(rs.getString("image"));
                ActiviteList.add(act);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ActiviteList;
    }

    public void modifierActivite(String nom, String type, String localisation, int prix, int nbre_places, String date, String duree, String temp) {
        try {
            String requete = "UPDATE activite SET"
                    + " `nom`='" + nom + "' ,`type`='" + type + "' , `localisation`='" + localisation + "'"
                    + " , `nbre_places`='" + nbre_places + "',`prix`='" + prix + "' , `date`='" + date + "' ,"
                    + " `duree`='" + duree + "' , `temp`='" + temp + "' WHERE nom='" + nom + "'";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.executeUpdate();
            System.err.println("Update Done !!");
        } catch (SQLException ex) {
            Logger.getLogger(ActiviteCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void supprimerActivite(int reference) {
        try {
            String requete = "DELETE from activite where reference='"+reference+"'";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.executeUpdate();
            System.err.println("Activity deleted");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
   /* public int getId(Activite act) {
        System.err.println("Retour de l'id");
        return act.getReference();
    }*/
    
    //------------------------------------------méier-------------------------------------------
    //------------------------------------------tri activité selon type------------------------

    public ObservableList<Activite> trierActivite(int reference) {
 ObservableList<Activite> ActivityList = FXCollections.observableArrayList();    
 try {
            String requete = "SELECT * FROM activite where `reference`='" + reference + "' ";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                Activite act = new Activite();
                   act.setReference(rs.getInt("reference"));
                act.setNom(rs.getString("nom"));
                act.setType(rs.getString("type"));
                act.setLocalisation(rs.getString("localisation"));
                act.setNbre_places(rs.getInt("nbre_places"));
                act.setPrix(rs.getInt("prix"));
                act.setDate(rs.getString("date"));
                act.setDuree(rs.getString("duree"));
                                act.setImage(rs.getString("image"));

             

                ActivityList.add(act);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ActivityList;
    }

    //----------------------------------------Tri bel reference lel modifier------------------------------------
  /*  public static Activite  SelectedActivite(int Reference) throws SQLException {

        String requete = "SELECT * FROM activite where `reference`='" + Reference + "' ";
        PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
        Statement st = MyConnection.getInstance().getCnx().createStatement();
        ResultSet rs = st.executeQuery(requete);

        Activite act = new Activite();
        act.setNom(rs.getNString("nom"));
        act.setType(rs.getString("type"));
        act.setLocalisation(rs.getNString("localisation"));
        act.setNbre_places(rs.getInt("nbre_places"));
        act.setPrix(rs.getInt("prix"));
        act.setDate(rs.getString("date"));
        act.setDuree(rs.getString("duree"));
        act.setReference(rs.getInt("reference"));
        act.setTemp(rs.getString("temp"));
        return act;

    }*/

    public int GetPrix(int idd)  {
        int p=1;
    
          try { String requete = "SELECT prix FROM activite where `reference`='" + idd + "' ";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
              while (rs.next()) {
           p =  rs.getInt("prix");
              }
          }catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
          return p ;
    }

    public void EliminerPlaces(int idd, int nbre) {
          try {
              
            String requete = "UPDATE activite SET"
                   + " `nbre_places` = `nbre_places` - '" + nbre +"'  WHERE reference='" + idd + "'";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.executeUpdate();
            System.err.println("Update Done !!");
        } catch (SQLException ex) {
            Logger.getLogger(ActiviteCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
