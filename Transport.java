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
public class Transport {
    int id;
    String type;
    String place_depart;
    String place_destination;
    String date_depart;
    int nbre_places;
    String date_arrivee;
    int prix;
    String heure_depart;
    String heure_arrivee;
private static int idd;
/*
private static int idd;
private static int idd;
private static int idd;
private static int idd;
private static int idd;*/


    public static void setIdd(int idd) {
        Transport.idd = idd;
    }

    public static int getIdd() {
        return idd;
    }
    public Transport() {
    }

    public Transport(int id, String type, String place_depart, String place_destination, String date_depart, int nbre_place, String date_arrivee, int prix, String heure_depart, String heure_arrivee) {
        this.id = id;
        this.type = type;
        this.place_depart = place_depart;
        this.place_destination = place_destination;
        this.date_depart = date_depart;
        this.nbre_places = nbre_place;
        this.date_arrivee = date_arrivee;
        this.prix = prix;
        this.heure_depart = heure_depart;
        this.heure_arrivee = heure_arrivee;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getPlace_depart() {
        return place_depart;
    }

    public String getPlace_destination() {
        return place_destination;
    }

    public String getDate_depart() {
        return date_depart;
    }

    public int getNbre_places() {
        return nbre_places;
    }

    public String getDate_arrivee() {
        return date_arrivee;
    }

    public int getPrix() {
        return prix;
    }

    public String getHeure_depart() {
        return heure_depart;
    }

    public String getHeure_arrivee() {
        return heure_arrivee;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPlace_depart(String place_depart) {
        this.place_depart = place_depart;
    }

    public void setPlace_destination(String place_destination) {
        this.place_destination = place_destination;
    }

    public void setDate_depart(String date_depart) {
        this.date_depart = date_depart;
    }

    public void setNbre_places(int nbre_place) {
        this.nbre_places = nbre_place;
    }

    public void setDate_arrivee(String date_arrivee) {
        this.date_arrivee = date_arrivee;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setHeure_depart(String heure_depart) {
        this.heure_depart = heure_depart;
    }

    public void setHeure_arrivee(String heure_arrivee) {
        this.heure_arrivee = heure_arrivee;
    }

    @Override
    public String toString() {
        return "Transport{" + "id=" + id + ", type=" + type + ", place_depart=" + place_depart + ", place_destination=" + place_destination + ", date_depart=" + date_depart + ", nbre_place=" + nbre_places + ", date_arrivee=" + date_arrivee + ", prix=" + prix + ", heure_depart=" + heure_depart + ", heure_arrivee=" + heure_arrivee + '}';
    }

   
    
}
