/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connexioncultun.services;

import edu.connexioncultun.entities.Activite;
import edu.connexioncultun.entities.Transport;
import edu.connexioncultun.utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Soulaymen
 */
public class TransportCRUD {

    public void ajouterTransport(Transport t) {
        try {
            String requete = "INSERT INTO transport (type,place_depart,place_destination,date_depart,nbre_places,date_arrivee,prix,heure_depart,heure_arrivee) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, t.getType());//0
            pst.setString(2, t.getPlace_depart());//0
            pst.setString(3, t.getPlace_destination());//0
            pst.setString(4, t.getDate_depart());//0
            pst.setInt(5, t.getNbre_places());//e
            pst.setString(6, t.getDate_arrivee());//0
            pst.setInt(7, t.getPrix());//0
            pst.setString(8, t.getHeure_depart());//0
            pst.setString(9, t.getHeure_arrivee());//0

            pst.executeUpdate();
            System.err.println("Transport ajoutée!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ObservableList<Transport> listerTransport() {
        ObservableList<Transport> TransportList = FXCollections.observableArrayList();
        try {
            String requete = "SELECT * FROM transport";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {

                Transport tra = new Transport();
                tra.setId(rs.getInt("id"));
                tra.setType(rs.getString("type"));//*
                tra.setPlace_depart(rs.getString("place_depart"));//*
                tra.setPlace_destination(rs.getString("place_destination"));//*
                tra.setDate_depart(rs.getString("date_depart"));//*
                tra.setNbre_places(rs.getInt("nbre_places"));//*
                tra.setDate_arrivee(rs.getString("date_arrivee"));
                tra.setPrix(rs.getInt("prix"));
                tra.setHeure_depart(rs.getString("heure_depart"));
                tra.setHeure_arrivee(rs.getString("heure_arrivee"));

                TransportList.add(tra);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return TransportList;
    }

    public void modifierTransport(int id, String type, String place_depart, String place_destination, String date_depart, int nbre_places, String date_arrivee, int prix, String heure_depart, String heure_arrivee) {
        try {
            String requete = "UPDATE transport SET"
                    + " `type`='" + type + "' ,`place_depart`='" + place_depart + "' , `place_destination`='" + place_destination + "'"
                    + " , `date_depart`='" + date_depart + "',`nbre_places`='" + nbre_places + "' , `date_arrivee`='" + date_arrivee + "' ,"
                    + " `prix`='" + prix + "' , `heure_depart`='" + heure_depart + "' , `heure_arrivee`='" + heure_arrivee + "' WHERE id='" + id + "'";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.executeUpdate();
            System.err.println("Update Done !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimerTransport(int id) {
        try {
            String requete = "DELETE from transport where id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.err.println("Transport supprimée!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public ObservableList<Transport> trierTransport(int id) {
        ObservableList<Transport> TransportList = FXCollections.observableArrayList();
        try {
            String requete = "SELECT * FROM transport where `id`='" + id + "' ";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {

                Transport act = new Transport();
                act.setType(rs.getString("type"));
                act.setPlace_depart(rs.getString("place_depart"));
                act.setPlace_destination(rs.getString("place_destination"));
                act.setDate_depart(rs.getString("date_depart"));
                act.setNbre_places(rs.getInt("nbre_places"));
                act.setDate_arrivee(rs.getString("date_arrivee"));
                act.setPrix(rs.getInt("prix"));
                act.setHeure_depart(rs.getString("heure_depart"));
                act.setHeure_arrivee(rs.getString("heure_arrivee"));
                TransportList.add(act);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return TransportList;
    }

    public void EliminerPlacestr(int idd, int nbr) {
          try {
              
            String requete = "UPDATE transport SET"
                   + " `nbre_places` = `nbre_places` - '" + nbr +"'  WHERE id='" + idd + "'";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.executeUpdate();
            System.err.println("Update Done !!");
        } catch (SQLException ex) {
            Logger.getLogger(TransportCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public int GetPrix(int idd) throws SQLException {
         int p=1;
    
          try { String requete = "SELECT prix FROM transport where `id`='" + idd + "' ";
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
}
