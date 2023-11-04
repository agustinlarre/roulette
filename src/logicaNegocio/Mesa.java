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
    // Necesario? ya contamos con la lista de casilleros a traves de los tipos de apuesta de la mesa
    //private List<Casillero> listaCasilleros;
    private List<Ronda> listaRondas;
    // PREGUNTAR POSIBLE BIDIRECCION MESA <--> PARTICIPANTE
    private List<Participante> listaParticipantes;
    private int balance;
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
        //Necesario? ya que al inicializar la ventana mesa, el primer select queda seleccionado por defecto, nunca van a existir valores nulos
        if (efecto != null) {
            if (this.hayPausa) {
                this.hayPausa = false;
                realizarLiquidacion();
                // Evaluar si es necesario el evento RONDA_LIQUIDADA ???
                this.notificar(Observador.Evento.RONDA_LIQUIDADA);
            } else {
                generarSorteo(efecto);
            }
        }
    }
    
    public Ronda getRondaActual() {
        return this.rondaActual;
    }
    
    public int getNroRondaActual() {
        // La ronda se agregará una vez lanzada la bola
        return listaRondas.size() +1;
    }
    
    public void addParticipante(Participante participante) {
        this.listaParticipantes.add(participante);
        this.notificar(Observador.Evento.PARTICIPANTE_AGREGADO);
    }
    
    private void realizarLiquidacion() {
        List<Apuesta> listaApuestasGanadoras = this.rondaActual.getApuestasGanadoras();
        if (!listaApuestasGanadoras.isEmpty()) {
            for (Participante participante : listaParticipantes) {
                procesarApuestas(participante, listaApuestasGanadoras);
            }
        }
    }
    private void procesarApuestas(Participante participante, List<Apuesta> apuestasGanadoras) {
        for (Apuesta apuesta : participante.getApuestas()) {
            if (apuestasGanadoras.contains(apuesta)) {
                procesarPagoApuesta(participante, apuesta);
                //Se notifica al jugador que su saldo fue modificado, posible evento???
            } else {
                modificarBalanceMesa(apuesta.getMonto(), true);
            }
        }
    }
    
    private void procesarPagoApuesta(Participante participante, Apuesta apuesta) {
        Jugador jugador = participante.getJugador();
        int saldo = jugador.getSaldo();
        int montoGanado = calcularMontoGanado(apuesta, saldo);
        actualizarSaldoJugador(jugador, montoGanado);
        modificarBalanceMesa(apuesta.getMonto(), false);
    }
    
    private int calcularMontoGanado(Apuesta apuesta, int saldo) {
        for (TipoApuesta tipoApuesta : tiposApuesta) {
            if (tipoApuesta.getCasillerosDisponibles().contains(apuesta.getCasillero())) {
                return tipoApuesta.getFactorPago() * apuesta.getMonto();
            }
        }
        return 0;
    }
    
    private void actualizarSaldoJugador(Jugador jugador, int montoGanado) {
        int nuevoSaldo = jugador.getSaldo() + montoGanado;
        jugador.setSaldo(nuevoSaldo);
    }

    private void modificarBalanceMesa(int montoApostado, boolean laCasaGana) {
        if (laCasaGana) {
            this.balance += montoApostado;
        } else {
            this.balance -= montoApostado;
        }
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
        this.balance = 0;
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
