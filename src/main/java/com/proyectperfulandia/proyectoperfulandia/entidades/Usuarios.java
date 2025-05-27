package com.proyectperfulandia.proyectoperfulandia.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usuarios {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id_usuario;
    private String nombre_usuario;
    private String apellido_usuario;
    private String correo_usuario;
    private int edad_usuario;
    private String RUT_usuario;
    
    public Usuarios() {
    }
    
    public Usuarios(Long id_usuario, String nombre_usuario, String apellido_usuario, String correo_usuario,
            int edad_usuario, String rUT_usuario) {
        this.id_usuario = id_usuario;
        this.nombre_usuario = nombre_usuario;
        this.apellido_usuario = apellido_usuario;
        this.correo_usuario = correo_usuario;
        this.edad_usuario = edad_usuario;
        RUT_usuario = rUT_usuario;
    }



    public Long getId_usuario() {
        return id_usuario;
    }



    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }



    public String getNombre_usuario() {
        return nombre_usuario;
    }



    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }



    public String getApellido_usuario() {
        return apellido_usuario;
    }



    public void setApellido_usuario(String apellido_usuario) {
        this.apellido_usuario = apellido_usuario;
    }



    public String getCorreo_usuario() {
        return correo_usuario;
    }



    public void setCorreo_usuario(String correo_usuario) {
        this.correo_usuario = correo_usuario;
    }



    public int getEdad_usuario() {
        return edad_usuario;
    }



    public void setEdad_usuario(int edad_usuario) {
        this.edad_usuario = edad_usuario;
    }



    public String getRUT_usuario() {
        return RUT_usuario;
    }



    public void setRUT_usuario(String rUT_usuario) {
        RUT_usuario = rUT_usuario;
    }



    @Override
    public String toString() {
        return "Usuarios [id_usuario=" + id_usuario + ", nombre_usuario=" + nombre_usuario + ", apellido_usuario="
                + apellido_usuario + ", correo_usuario=" + correo_usuario + ", edad_usuario=" + edad_usuario
                + ", RUT_usuario=" + RUT_usuario + "]";
    }





    
}
