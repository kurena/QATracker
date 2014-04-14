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
public class Tarea {
    private int id;
    private String name;
    private String description;
    private String attachment;
    private String state;
    private int idCreatorUser;
    private int idUserAsignar;
    private int idProyect;

    public Tarea(int id, String name, String description, String attachment, String state, int idCreatorUser, int idUserAsignar) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.attachment = attachment;
        this.state = state;
        this.idCreatorUser = idCreatorUser;
        this.idUserAsignar = idUserAsignar;
    }

    public Tarea(String name, String description, int id) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    
    public Tarea(String name, String description, int idProyect,int id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.idProyect = idProyect;
    }
    public Tarea(int id,String name) {
        this.name = name;
        this.id = id;
    }
    
    

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getIdCreatorUser() {
        return idCreatorUser;
    }

    public void setIdCreatorUser(int idCreatorUser) {
        this.idCreatorUser = idCreatorUser;
    }

    public int getIdUserAsignar() {
        return idUserAsignar;
    }

    public void setIdUserAsignar(int idUserAsignar) {
        this.idUserAsignar = idUserAsignar;
    }

    public int getIdProyect() {
        return idProyect;
    }

    public void setIdProyect(int idProyect) {
        this.idProyect = idProyect;
    }
    
    

    public Tarea() {
    }

    public Tarea(String name, String description, String attachment, int id,String state,int idCreatorUser,int idUserAsignar) {
        this.idProyect = id;
        this.name = name;
        this.description = description;
        this.attachment = attachment;
        this.state = state;
        this.idCreatorUser = idCreatorUser;
        this.idUserAsignar = idUserAsignar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
    
    
    
}
