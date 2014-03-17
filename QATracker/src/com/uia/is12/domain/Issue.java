/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uia.is12.domain;

/**
 *
 * @author kevin
 */
public class Issue {
    private String name;
    private String description;
    private int idUserCreador;
    private int idUserAsignar;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Issue(String name, String description, int idUserCreador, int idUserAsignar, int id) {
        this.name = name;
        this.description = description;
        this.idUserCreador = idUserCreador;
        this.idUserAsignar = idUserAsignar;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdUserCreador() {
        return idUserCreador;
    }

    public void setIdUserCreador(int idUserCreador) {
        this.idUserCreador = idUserCreador;
    }

    public int getIdUserAsignar() {
        return idUserAsignar;
    }

    public void setIdUserAsignar(int idUserAsignar) {
        this.idUserAsignar = idUserAsignar;
    }

    public Issue() {
    }

    public Issue(String name, String description, int idUserCreador, int idUserAsignar) {
        this.name = name;
        this.description = description;
        this.idUserCreador = idUserCreador;
        this.idUserAsignar = idUserAsignar;
    }
    
    
}
