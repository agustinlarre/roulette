/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaNegocio;

import excepcionesSistema.SaldoInvalidoException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author agust
 */
public class Jugador extends Usuario {
    private String cedula;
    private String password;
    private int saldoInicial;
    private List<Participante> participaciones;

    public Jugador(String cedula, String password) {
        super(cedula, password);
        this.participaciones = new ArrayList();
    }

    public int getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(int saldoInicial) throws SaldoInvalidoException {
        if (saldoInicial <= 0) {
            throw new SaldoInvalidoException("El saldo debe ser mayor a cero.");
        }
        this.saldoInicial = saldoInicial;
    }
}
