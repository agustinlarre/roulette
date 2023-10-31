/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaNegocio;

import excepcionesSistema.TipoApuestaObligatoriaException;
import excepcionesSistema.TiposApuestaVaciaException;
import java.util.ArrayList;
import java.util.List;
import servicios.Observable;
import servicios.Observador;

/**
 *
 * @author agust
 */
public class Mesa extends Observable {
    private List<TipoApuesta> tiposApuesta;
    private List<Casillero> listaCasilleros;
    private List<Ronda> listaRondas;
    // PREGUNTAR POSIBLE BIDIRECCION MESA <--> PARTICIPANTE
    private List<Participante> listaParticipantes;
    private Ronda rondaActual;
    private boolean hayPausa;
    private boolean hayLiquidacion;
    private int nroMesa;
    private static int nro = 1;

    public Mesa(List<TipoApuesta> tiposApuesta) {
        inicializarMesa(tiposApuesta);
    }

    public int getNroMesa() {
        return nroMesa;
    }

    public List<TipoApuesta> getTiposApuesta() {
        return tiposApuesta;
    }
    
    public void setTipoApuesta(TipoApuesta tipo) {
        this.tiposApuesta.add(tipo);
    }
    
    public void validar() throws TipoApuestaObligatoriaException, TiposApuestaVaciaException {
        if (this.tiposApuesta.isEmpty()) {
            throw new TiposApuestaVaciaException();
        } else {
            boolean esValido = false;
            for (TipoApuesta tipo : tiposApuesta) {
                if (tipo.getNombreTipo().equals("Apuesta directa")) esValido = true;
            }
            if (!esValido) throw new TipoApuestaObligatoriaException();
        }
    }
    
    public void addApuesta(Apuesta apuesta) {
        //Atajar excepciones
        this.rondaActual.recibirApuesta(apuesta);
    }
    
    public void accionarMesa(Efecto efecto) {
        if (efecto != null) {
            if (this.hayPausa) {
                this.hayPausa = false;
                // Evaluar si es necesario el evento RONDA_LIQUIDADA ???
                this.notificar(Observador.Evento.RONDA_LIQUIDADA);
            } else {
                generarSorteo(efecto);
            }
        }
    }
    
    public int getNroRondaActual() {
        // La ronda se agregará una vez lanzada la bola
        return listaRondas.size() +1;
    }
    
    public void addParticipante(Participante participante) {
        this.listaParticipantes.add(participante);
        this.notificar(Observador.Evento.PARTICIPANTE_AGREGADO);
    }
    
    private void generarSorteo(Efecto efecto) {
        // Se trae a la instancia de apuesta directa para obtener los números 1 - 36
        TipoApuesta apuestaDirecta = (ApuestaDirecta) getTipoApuestaByNombre("Apuesta directa");
        this.rondaActual.setEfecto(efecto);
        this.rondaActual.sortearNumero(apuestaDirecta.getCasillerosDisponibles(), listaHistoricoNumerosSorteados());
        this.listaRondas.add(rondaActual);
        this.hayPausa = true;
        this.notificar(Observador.Evento.MESA_PAUSADA);
    }
    
    public int getUltimoNumeroSorteado() {
        // posible manejo de excepciones
        return rondaActual.getNumeroSorteado();
    }
    
    private void inicializarMesa(List<TipoApuesta> tiposApuesta) {
        this.tiposApuesta = tiposApuesta;
        this.nroMesa = nro;
        nro++;
        this.hayPausa = false;
        this.hayLiquidacion = false;
        this.listaParticipantes = new ArrayList();
        this.listaRondas = new ArrayList();
        this.rondaActual = new Ronda(null);
    }
    
    private TipoApuesta getTipoApuestaByNombre(String nombre) {
        for (TipoApuesta tipo : tiposApuesta) {
            if (tipo.getNombreTipo().equals(nombre)) return tipo;
        }
        return null;
    }
    
    private List<Integer> listaHistoricoNumerosSorteados() {
        List<Integer> listaNum = new ArrayList();
        //Deberiamos evaluar si hay rondas -> posible manejo de excepciones?
        for (Ronda ronda : listaRondas) {
            listaNum.add(ronda.getNumeroSorteado());
        }
        return listaNum;
    }
}
