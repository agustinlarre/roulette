/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaNegocio;

import excepcionesSistema.ApuestaInvalidaException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Agustin
 */
public class Ronda {
    private int numeroSorteado;
    private Efecto efecto;
    private List<Apuesta> listaApuestas;

    public Ronda(Efecto efecto) {
        this.efecto = efecto;
        this.listaApuestas = new ArrayList();
    }

    public Efecto getEfecto() {
        return efecto;
    }

    public void setEfecto(Efecto efecto) {
        this.efecto = efecto;
    }

    public int getNumeroSorteado() {
        return numeroSorteado;
    }
    
    public void sortearNumero(Mesa mesa) {
        // Desde mesa...
        // listaCasillerosNumeros -> todos los nros del 1 al 36
        // listaNumerosSorteados -> historial de numeros sorteados por cada ronda (proveniente de la mesa)
        // listaNumerosApostados -> lista de numeros que SI se apostaron en esta ronda
        
        this.numeroSorteado = this.efecto.sortear(mesa);
    }
    
    // Apuesta llegada desde el manejo de eventos de la MesaCrupier
    public void addApuesta(Apuesta apuesta) {
        // Excepciones???
        this.listaApuestas.add(apuesta);
    }

    public List<Apuesta> getListaApuestas() {
        return listaApuestas;
    }
    
    public List<Apuesta> getApuestasSegunParticipante(Participante participante) {
        List<Apuesta> listaApuestasParticipante = new ArrayList();
        for (Apuesta apuesta : listaApuestas) {
            if (participante.getApuestas().contains(apuesta)) {
                listaApuestasParticipante.add(apuesta);
            }
        }
        return listaApuestasParticipante;
    }
    
    public int getCantTotalApuestas() {
        return this.listaApuestas.size();
    }
    
    public int getMontoTotalApostado() {
        int montoTotal = 0;
        for (Apuesta apuesta : this.listaApuestas) {
            montoTotal += apuesta.getMonto();
        }
        return montoTotal;
    }
    
    public int getMontoTotalApostadoSegunParticipante(Participante participante) {
        int montoTotal = 0; 
        if (!this.getApuestasSegunParticipante(participante).isEmpty()) {
            for (Apuesta apuesta : this.getApuestasSegunParticipante(participante)) {
                montoTotal += apuesta.getMonto();
            }
        } 
        return montoTotal;
    }
    
    public List<Apuesta> getApuestasGanadoras() {
        List<Apuesta> listaApuestasGanadoras = new ArrayList();
        // Excepcion para el caso de que no haya un número sorteado???
        for (Apuesta apuesta : this.listaApuestas) {
            if (esApuestaGanadora(apuesta)) listaApuestasGanadoras.add(apuesta);
        }
        return listaApuestasGanadoras;
    }
    
    public int getMontoTotalGanadoPorParticipantes() {
        int montoTotal = 0;
        for (Apuesta apuesta : this.listaApuestas) {
            if (esApuestaGanadora(apuesta)) montoTotal += (apuesta.getMonto() * apuesta.getCasillero().getTipoApuesta().getFactorPago());
        }
        return montoTotal;
    }
    
    public int getMontoGanadoSegunParticipante(Participante participante) {
        int montoGanado = 0;
        if (!this.getApuestasSegunParticipante(participante).isEmpty()) {
            for (Apuesta apuesta : this.getApuestasSegunParticipante(participante)) {
                if (esApuestaGanadora(apuesta)) montoGanado += (apuesta.getMonto() * apuesta.getCasillero().getTipoApuesta().getFactorPago());
            }
        }
        
        return montoGanado;
    }
    
    public List<Apuesta> getApuestasPerdedoras() {
        List<Apuesta> listaApuestasPerdedoras = new ArrayList();
        // Excepcion para el caso de que no haya un número sorteado???
        for (Apuesta apuesta : this.listaApuestas) {
            if (!esApuestaGanadora(apuesta)) listaApuestasPerdedoras.add(apuesta);
        }
        return listaApuestasPerdedoras;
    }
    
    public int getMontoTotalGanadoPorMesa() {
        int montoTotal = 0;
        for (Apuesta apuesta : this.listaApuestas) {
            if (!esApuestaGanadora(apuesta)) montoTotal += (apuesta.getMonto());
        }
        return montoTotal;
    }
    
    public int getMontoPerdidoSegunParticipante(Participante participante) {
        int montoPerdido = 0;
        if (!this.getApuestasSegunParticipante(participante).isEmpty()) {
            for (Apuesta apuesta : this.getApuestasSegunParticipante(participante)) {
                if (!esApuestaGanadora(apuesta)) montoPerdido += apuesta.getMonto();
            }
        }
        return montoPerdido;
    }
    
    public int getBalanceSegunParticipante(Participante participante) {
        return this.getMontoGanadoSegunParticipante(participante) -this.getMontoPerdidoSegunParticipante(participante);
    }
    
    public List<Integer> getTotalNumerosApostados() {
        List<Integer> listaNum = new ArrayList();
        for (Apuesta apuesta : this.listaApuestas) {
            for (int num : apuesta.getCasillero().getNumerosVinculados()) {
                // Se verifica que el número no haya sido agregado anteriormente
                if (!listaNum.contains(num)) {
                    listaNum.add(num);
                }
            }
        }
        return listaNum;
    }
    
    public List<Casillero> getCasillerosConApuestas() {
        if (!listaApuestas.isEmpty()) {
            List<Casillero> listaCasilleros = new ArrayList();
            for (Apuesta apuesta : this.listaApuestas) {
                if (!listaCasilleros.contains(apuesta.getCasillero())) listaCasilleros.add(apuesta.getCasillero());
            }
            return listaCasilleros;
        }
        return null;
    }
    
    public int calcularMontoPorCasillero(Casillero casillero) {
        int monto = 0;
        for (Apuesta apuesta : this.listaApuestas) {
            if (apuesta.getCasillero().equals(casillero)) monto += apuesta.getMonto();
        }
        return monto;
    }
    
    private boolean esApuestaGanadora(Apuesta apuesta) {
        return apuesta.getCasillero().getNumerosVinculados().contains(numeroSorteado);
    }
}
