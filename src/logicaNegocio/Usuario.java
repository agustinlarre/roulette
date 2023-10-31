/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaNegocio;

import servicios.Observable;

/**
 *
 * @author agust
 */
public abstract class Usuario {
    private String cedula;
    private String contrasenia;

    public Usuario(String cedula, String password) {
        this.cedula = cedula;
        this.contrasenia = password;
    }

    public String getCedula() {
        return cedula;
    }
    
    public boolean equals(String cedula, String contrasenia){
        return cedula.equals(this.cedula) && contrasenia.equals(this.contrasenia);
    }
}
