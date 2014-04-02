/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uia.is12.domain;

/**
 *
 * @author raiam.quesada.urena
 */
public class Comentario {
    private int id;
    private int idUser;
    private int idIssue;
    private String date;
    private String comment;

    public Comentario(int id, int idUser, int idIssue, String date, String comment) {
        this.id = id;
        this.idUser = idUser;
        this.idIssue = idIssue;
        this.date = date;
        this.comment = comment;
    }

    public Comentario(int idUser, int idIssue, String date, String comment) {
        this.idUser = idUser;
        this.idIssue = idIssue;
        this.date = date;
        this.comment = comment;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdIssue() {
        return idIssue;
    }

    public void setIdIssue(int idIssue) {
        this.idIssue = idIssue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    
}
