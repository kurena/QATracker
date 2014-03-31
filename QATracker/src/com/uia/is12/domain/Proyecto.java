

package com.uia.is12.domain;

import java.util.ArrayList;

public class Proyecto {
    private int id;
    private String name;
    private String description;
    private int idUserCreador;
    private String nombreUserCreador;

    public Proyecto(String name, String description, int idUserCreador, String nombreUserCreador,int id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.idUserCreador = idUserCreador;
        this.nombreUserCreador = nombreUserCreador;
    }

    public String getNombreUserCreador() {
        return nombreUserCreador;
    }

    public void setNombreUserCreador(String nombreUserCreador) {
        this.nombreUserCreador = nombreUserCreador;
    }
    ArrayList<Integer> idsUsuariosIncluidos = new ArrayList();

    public Proyecto() {
        
    }

    public Proyecto(String name, String description, int idUserCreador) {
        this.name = name;
        this.description = description;
        this.idUserCreador = idUserCreador;
    }

    public Proyecto(String name, String description) {
        this.name = name;
        this.description = description;
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

    public int getIdUserCreador() {
        return idUserCreador;
    }

    public void setIdUserCreador(int idUserCreador) {
        this.idUserCreador = idUserCreador;
    }

    public ArrayList<Integer> getIdsUsuariosIncluidos() {
        return idsUsuariosIncluidos;
    }

    public void setIdsUsuariosIncluidos(ArrayList<Integer> idsUsuariosIncluidos) {
        this.idsUsuariosIncluidos = idsUsuariosIncluidos;
    }
    
    
    
    
}
