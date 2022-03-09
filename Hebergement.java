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
public class Hebergement {
    int reference;
    String nom;
    String localisation;
    String type_tourisme;
    int numero_tel;
    String description;
    int capacite;

    public Hebergement() {
    }

    public Hebergement(int reference, String nom, String localisation, String type_tourisme, int numero_tel, String description, int capacite) {
        this.reference = reference;
        this.nom = nom;
        this.localisation = localisation;
        this.type_tourisme = type_tourisme;
        this.numero_tel = numero_tel;
        this.description = description;
        this.capacite=capacite;
        
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

    @Override
    public String toString() {
        return "Hebergement{" + "reference=" + reference + ", nom=" + nom + ", localisation=" + localisation + ", type_tourisme=" + type_tourisme + ", numero_tel=" + numero_tel + ", description=" + description +  ", capacite=" + capacite +'}';
    }

    public String getType_tourisme() {
        return type_tourisme;
    }

    public int getNumero_tel() {
        return numero_tel;
    }

    public String getDescription() {
        return description;
    }
    public int getCapacite(){
        return capacite;
        
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

    public void setType_tourisme(String type_tourisme) {
        this.type_tourisme = type_tourisme;
    }

    public void setNumero_tel(int numero_tel) {
        this.numero_tel = numero_tel;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }
    
}
