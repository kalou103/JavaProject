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
public class Activite {
   private int reference;
   private int nbre_places;
   private int prix;
   private String duree;
   private String date;
   private String localisation;
   private String type;
   private String temp;
   private String nom;
   private static int idd;
   private String image;

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public static void setIdd(int idd) {
        Activite.idd = idd;
    }

    public static int getIdd() {
        return idd;
    }

    public Activite() {
    }
    public Activite(int reference,String type, String localisation, int nbre_places, int prix, String date, String duree ,String temp,String nom,String image) {
        this.nom = nom;
        this.nbre_places = nbre_places;
        this.prix = prix;
        this.duree = duree;
        this.date = date;
        this.localisation = localisation;
        this.type = type;
        this.temp = temp;
        this.image = image;
        
    }

    public int getReference() {
        return reference;
    }

    public String getTemp() {
        return temp;
    }

    public int getNbre_places() {
        return nbre_places;
    }

    public int getPrix() {
        return prix;
    }

    public String getDuree() {
        return duree;
    }

    public String getDate() {
        return date;
    }

    public String getNom() {
        return nom;
    }

    public String getLocalisation() {
        return localisation;
    }

    public String getType() {
        return type;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public void setNbre_places(int nbre_places) {
        this.nbre_places = nbre_places;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Activite{" + "reference=" + reference + ", nbre_places=" + nbre_places + ", prix=" + prix + ", duree=" + duree + ", date=" + date + ", localisation=" + localisation + ", type=" + type + "temp=" + temp + '}';
    }
   
    
    
}
