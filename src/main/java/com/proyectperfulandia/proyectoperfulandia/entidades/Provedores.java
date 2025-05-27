package com.proyectperfulandia.proyectoperfulandia.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Provedores {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_provedor;
private String nombre_Provedor;
private String ubicacion_Provedor;
private String producto_Provedor;

public Provedores() {
}

public Provedores(Long id_provedor, String nombre_Provedor, String ubicacion_Provedor, String producto_Provedor) {
    this.id_provedor = id_provedor;
    this.nombre_Provedor = nombre_Provedor;
    this.ubicacion_Provedor = ubicacion_Provedor;
    this.producto_Provedor = producto_Provedor;






}

public Long getId_provedor() {
    return id_provedor;
}

public void setId_provedor(Long id_provedor) {
    this.id_provedor = id_provedor;
}

public String getNombre_Provedor() {
    return nombre_Provedor;
}

public void setNombre_Provedor(String nombre_Provedor) {
    this.nombre_Provedor = nombre_Provedor;
}

public String getUbicacion_Provedor() {
    return ubicacion_Provedor;
}

public void setUbicacion_Provedor(String ubicacion_Provedor) {
    this.ubicacion_Provedor = ubicacion_Provedor;
}

public String getProducto_Provedor() {
    return producto_Provedor;
}

public void setProducto_Provedor(String producto_Provedor) {
    this.producto_Provedor = producto_Provedor;
}

@Override
public String toString() {
    return "Provedores{" +
            "id_provedor=" + id_provedor +
            ", nombre_Provedor='" + nombre_Provedor + '\'' +
            ", ubicacion_Provedor='" + ubicacion_Provedor + '\'' +
            ", producto_Provedor='" + producto_Provedor + '\'' +
            '}';
        
        }

}