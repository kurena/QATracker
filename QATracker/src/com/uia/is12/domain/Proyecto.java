

package com.uia.is12.domain;

import java.util.ArrayList;

public class Proyecto {
    private int id;
    private String name;
    private String description;
    private int idUserCreador;
    private String nameUserCreador;

    public Proyecto(String name, String description, int idUserCreador, String nombreUserCreador,int id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.idUserCreador = idUserCreador;
        this.nameUserCreador = nombreUserCreador;
    }

    public String getNombreUserCreador() {
        return nameUserCreador;
    }

    public void setNombreUserCreador(String nameUserCreador) {
        this.nameUserCreador = nameUserCreador;
    }
    ArrayList<Integer> idsUsuariosIncluidos = new ArrayList();

    public Proyecto() {
        
    }

    public Proyecto(String name, String description, String nameUserCreador, int id) {
        this.name = name;
        this.description = description;
        this.nameUserCreador = nameUserCreador;
        this.id = id;
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
