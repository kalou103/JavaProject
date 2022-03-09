/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connexioncultun.entities;

/**
 *
 * @author Soulaymen
 */
public class Reservation {
   int id;
   int prix_res;
   int nbr_places;
   String nom_cl;
   String prenom_cl;
   String date_res;
   int tel_cl;
   String email_cl;
   int refact;
   int reftra;

    public int getRefact() {
        return refact;
    }

    public int getReftra() {
        return reftra;
    }

    public void setRefact(int refact) {
        this.refact = refact;
    }

    public void setReftra(int reftra) {
        this.reftra = reftra;
    }

    public Reservation(int id, int prix_res, int nbr_places, String nom_cl, String prenom_cl, String date_res, int tel_cl, String email_cl, int refact, int reftra) {
        this.id = id;
        this.prix_res = prix_res;
        this.nbr_places = nbr_places;
        this.nom_cl = nom_cl;
        this.prenom_cl = prenom_cl;
        this.date_res = date_res;
        this.tel_cl = tel_cl;
        this.email_cl = email_cl;
        this.refact = refact;
        this.reftra = reftra;
    }
   

    public Reservation() {
    }


    public int getId() {
        return id;
    }

    public int getPrix_res() {
        return prix_res;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", prix_res=" + prix_res + ", nbr_places=" + nbr_places + ", nom_cl=" + nom_cl + ", prenom_cl=" + prenom_cl + ", date_res=" + date_res + ", tel_cl=" + tel_cl + ", email_cl=" + email_cl + ", refact=" + refact + ", reftra=" + reftra + '}';
    }

    public int getNbr_places() {
        return nbr_places;
    }

    public String getNom_cl() {
        return nom_cl;
    }

    public String getPrenom_cl() {
        return prenom_cl;
    }

    public String getDate_res() {
        return date_res;
    }

    public int getTel_cl() {
        return tel_cl;
    }

    public String getEmail_cl() {
        return email_cl;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrix_res(int prix_res) {
        this.prix_res = prix_res;
    }

    public void setNbr_places(int nbr_places) {
        this.nbr_places = nbr_places;
    }

    public void setNom_cl(String nom_cl) {
        this.nom_cl = nom_cl;
    }

    public void setPrenom_cl(String prenom_cl) {
        this.prenom_cl = prenom_cl;
    }

    public void setDate_res(String date_res) {
        this.date_res = date_res;
    }

    public void setTel_cl(int tel_cl) {
        this.tel_cl = tel_cl;
    }

    public void setEmail_cl(String email_cl) {
        this.email_cl = email_cl;
    }

   
  
    
    
}
