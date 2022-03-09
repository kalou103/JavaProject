/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connexioncultun.services;

import edu.connexioncultun.entities.Restaurant;
import edu.connexioncultun.utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Soulaymen
 */
public class RestaurantCRUD {

   
    

    public void ajouterRestaurant(Restaurant r) {
        try {
            String requete = "INSERT INTO restaurant (reference,nom,localisation,description,menu) VALUES (?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, r.getReference());
            pst.setString(2, r.getNom());
            pst.setString(3, r.getLocalisation());
            pst.setString(4, r.getDescription());
            pst.setString(5, r.getMenu());
           
            pst.executeUpdate();
            System.err.println("Restaurant ajoutée!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Restaurant> listerRestaurant() {
        List<Restaurant> RestaurantList = new ArrayList();
        try {
            String requete = "SELECT * FROM restaurant";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                
                Restaurant res = new Restaurant();
                res.setNom(rs.getString("nom"));
                res.setLocalisation(rs.getNString("localisation"));
                res.setDescription(rs.getNString("description"));
                                res.setMenu(rs.getString("menu"));
               
             RestaurantList.add(res);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return RestaurantList;
    }
     public void modifierRestaurant(int reference ,String nom ,String localisation,String description,String menu){
        try {
             String requete = "UPDATE restaurant SET"   
                    + " `reference`='" + reference + "' ,`nom`='" + nom + "' , `localisation`='" + localisation + "'"
                     + " , `menu`='" + menu + "' WHERE reference='"+reference+"'" ;
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.executeUpdate();
            System.err.println("Update Done !!");
        } catch (SQLException ex) {
            Logger.getLogger(RestaurantCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void supprimerRestaurant(Restaurant res){
        try {
            String requete="DELETE from restaurant where reference=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, res.getReference());
            pst.executeUpdate();
            System.err.println("Restaurant deleted");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            
        }
    }
}
