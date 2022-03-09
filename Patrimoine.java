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
public class Patrimoine {

    int reference;
    String nom;
    String localisation;
    String description;
    int prix_billet;

    public Patrimoine() {
    }

 
    public Patrimoine(int reference, String nom, String localisation, String description, int prix_billet) {
        this.reference = reference;
        this.nom = nom;
        this.localisation = localisation;
        this.description = description;
        this.prix_billet = prix_billet;
    }

  

    public int getReference() {
        return reference;
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

    public int getPrix_billet() {
        return prix_billet;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrix_billet(int prix_billet) {
        this.prix_billet = prix_billet;
    }

    @Override
    public String toString() {
        return "Patrimoine{" + "reference=" + reference + ", nom=" + nom + ", localisation=" + localisation + ", description=" + description + ", prix_billet=" + prix_billet + '}';
    }

}
