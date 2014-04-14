

package com.uia.is12.domain;


public class Usuario {
    
    private String username;
    private String password;
    private String name;
    private String lastname;
    private int edad;
    private int id;
    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario(String username, String password, String name, String lastname, int edad, int id) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.edad = edad;
        this.id = id;
    }
    public Usuario(String username, String password, String name, String lastname, int edad) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.edad = edad;
    }

    public Usuario() {
        
    }

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
    
    
}
