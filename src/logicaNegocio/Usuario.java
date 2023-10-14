/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaNegocio;

/**
 *
 * @author agust
 */
public abstract class Usuario {
    private String cedula;
    private String password;

    public Usuario(String cedula, String password) {
        this.cedula = cedula;
        this.password = password;
    }

    public String getCedula() {
        return cedula;
    }

    public String getPassword() {
        return password;
    }
}
