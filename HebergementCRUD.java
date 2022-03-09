/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connexioncultun.services;

import edu.connexioncultun.entities.Hebergement;
import edu.connexioncultun.utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Soulaymen
 */
public class HebergementCRUD {

    public void ajouterHebergement(Hebergement h) {
        try {
            String requete = "INSERT INTO hebergement (reference,nom,localisation,type_tourisme,numero_tel,description,capacite) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, h.getReference());
            pst.setString(2, h.getNom());
            pst.setString(3, h.getLocalisation());
            pst.setString(4, h.getType_tourisme());
            pst.setInt(5, h.getNumero_tel());
            pst.setString(6, h.getDescription());
            pst.setInt(7, h.getCapacite());

            pst.executeUpdate();
            System.err.println("Element ajoutée!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Hebergement> listerHebergement() {
        List<Hebergement> HebergementList = new ArrayList();
        try {
            String requete = "SELECT * FROM hebergement";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {

                Hebergement heb = new Hebergement();
                heb.setReference(rs.getInt("reference"));
                heb.setNom(rs.getNString("nom"));
                heb.setLocalisation(rs.getString("localisation"));
                heb.setType_tourisme(rs.getString("type_tourisme"));
                heb.setNumero_tel(rs.getInt("numero_tel"));
                heb.setDescription(rs.getString("description"));
                heb.setCapacite(rs.getInt("capacite"));

            HebergementList.add(heb);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return HebergementList;
    }

     public void modifierHebergement(int reference ,String nom ,String localisation,String type_tourisme,int numero_tel,String description,int capacite){
        try {
             String requete = "UPDATE hebergement SET"   
                    + " `reference`='" + reference + "' ,`nom`='" + nom + "' , `localisation`='" + localisation + "'"
                     + " , `type_tourisme`='" + type_tourisme + "',`numero_tel`='" + numero_tel +  "' , `description`='" + description + "' ,"
                     + " `capacite`='" + capacite + "' WHERE reference='"+reference+"'" ;
            // String requete2= "UPDATE `hebergement` SET `reference`='[value-1]',`nom`='[value-2]',`localisation`='[value-3]',`type_tourisme`='[value-4]',`numero_tel`='[value-5]',`description`='[value-6]',`capacite`='[value-7]' WHERE 1"
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.executeUpdate();
            System.err.println("Update Done !!");
        } catch (SQLException ex) {
          //  Logger.getLogger(HebergementCRUD.class.getName()).log(Level.SEVERE, null, ex);
                      System.err.println(ex.getMessage());

        }
    }
    public void supprimerHebergement(Hebergement heb) {
        try {
            String requete = "DELETE from hebergement where reference=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, heb.getReference());
            pst.executeUpdate();
            System.err.println("Hebergement deleted");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
