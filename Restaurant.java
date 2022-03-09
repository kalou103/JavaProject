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
public class Restaurant {
    int reference;
    String nom;
    String localisation;
    String description;
    String menu;

    public Restaurant() {
    }

    public Restaurant(int reference, String nom, String localisation, String description, String menu) {
        this.reference = reference;
        this.nom = nom;
        this.localisation = localisation;
        this.description = description;
        this.menu = menu;
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getNom() {
        return nom;
    }

    public String getLocalisation() {
        return localisation;
    }

    public String getDescription() {
        return description;
    }

    public String getMenu() {
        return menu;
    }

    @Override
    public String toString() {
        return "Restaurant{" + "reference=" + reference + ", nom=" + nom + ", localisation=" + localisation + ", description=" + description + ", menu=" + menu + '}';
    }
   
    
}
