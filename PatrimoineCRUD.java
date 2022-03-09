/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connexioncultun.services;

import edu.connexioncultun.entities.Patrimoine;
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
public class PatrimoineCRUD {

    public void ajouterPatrimoine(Patrimoine p) {
        try {
            String requete = "INSERT INTO patrimoine (reference,nom,localisation,description,prix_billet) VALUES (?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, p.getReference());
            pst.setString(2, p.getNom());
            pst.setString(3, p.getLocalisation());
            pst.setString(4, p.getDescription());
            pst.setInt(5, p.getPrix_billet());

            pst.executeUpdate();
            System.err.println("Element ajoutée!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Patrimoine> listerPatrimoine() {
        List<Patrimoine> PatrimoineList = new ArrayList();
        try {
            String requete = "SELECT * FROM patrimoine";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {

                Patrimoine pat = new Patrimoine();
                pat.setReference(rs.getInt("reference"));
                pat.setNom(rs.getNString("nom"));
                pat.setLocalisation(rs.getString("localisation"));
                pat.setDescription(rs.getString("description"));
                pat.setPrix_billet(rs.getInt("prix_billet"));
                PatrimoineList.add(pat);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return PatrimoineList;
    }

    public void modifierPatrimoine(int reference, String nom, String localisation, String description, int prix_billet) {
        try {
            String requete = "UPDATE patrimoine SET"   
                    + " `reference`='" + reference + "' ,`nom`='" + nom + "' , `localisation`='" + localisation + "' , `description`='" + description + "',`prix_billet`='" + prix_billet + "' WHERE reference='"+reference+"'" ;
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.executeUpdate();
            System.err.println("Update Patrimoine Done !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimerPatrimoine(Patrimoine pat) {
        try {
            String requete = "DELETE from patrimoine where reference=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, pat.getReference());
            pst.executeUpdate();
            System.err.println("Patrimoine supprimée!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

  
}
