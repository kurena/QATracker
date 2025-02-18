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
    private int id = -1;
    private String nombreAsignador;
    private String nombreCreador;
    private String attachment;
    private String state;
    private String priority;
    private int idTask;

    public String getAttachment() {
        return attachment;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Issue(String name, String description, int idUserCreador, int idUserAsignar, int id, String nombreAsignador, String nombreCreador, String attachment, String state, String priority, int idTask) {
        this.name = name;
        this.description = description;
        this.idUserCreador = idUserCreador;
        this.idUserAsignar = idUserAsignar;
        this.nombreAsignador = nombreAsignador;
        this.nombreCreador = nombreCreador;
        this.attachment = attachment;
        this.state = state;
        this.priority = priority;
        this.id = id;
        this.idTask = idTask;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public Issue(String name, String description, int idUserCreador, int idUserAsignar, int id, String nombreAsignador, String nombreCreador, String attachment) {
        this.name = name;
        this.description = description;
        this.idUserCreador = idUserCreador;
        this.idUserAsignar = idUserAsignar;
        this.id = id;
        this.nombreAsignador = nombreAsignador;
        this.nombreCreador = nombreCreador;
        this.attachment = attachment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Issue(String name, String description, int idUserCreador, int idUserAsignar, int id, String nombreCreador, String nombreAsignador) {
        this.name = name;
        this.description = description;
        this.idUserCreador = idUserCreador;
        this.idUserAsignar = idUserAsignar;
        this.id = id;
        this.nombreAsignador = nombreAsignador;
        this.nombreCreador = nombreCreador;
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

    public Issue(String name, String description, int idUserCreador, int idUserAsignar, String nombreAsignador, String nombreCreador) {
        this.name = name;
        this.description = description;
        this.idUserCreador = idUserCreador;
        this.idUserAsignar = idUserAsignar;
        this.nombreAsignador = nombreAsignador;
        this.nombreCreador = nombreCreador;
    }

    public Issue(String name, String description, int id) {
        this.name = name;
        this.description = description;
        this.id = id;
    }
    
    
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public String getNombreAsignador() {
        return nombreAsignador;
    }

    public void setNombreAsignador(String nombreAsignador) {
        this.nombreAsignador = nombreAsignador;
    }

    public String getNombreCreador() {
        return nombreCreador;
    }

    public void setNombreCreador(String nombreCreador) {
        this.nombreCreador = nombreCreador;
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

    public int getIdTask() {
        return idTask;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }
    
    
    
}
