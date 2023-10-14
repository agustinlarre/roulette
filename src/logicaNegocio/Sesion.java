/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaNegocio;

import java.util.Date;

/**
 *
 * @author agust
 */
public class Sesion {
    private Date horaIngreso;
    private Usuario usuario;

    public Sesion(Usuario usuario) {
        this.usuario = usuario;
        this.horaIngreso = new Date();
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
