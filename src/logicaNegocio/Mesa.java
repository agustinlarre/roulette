/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaNegocio;

import excepcionesSistema.AbandonarMesaEnPausaException;
import excepcionesSistema.ApuestasEnProgresoException;
import excepcionesSistema.MesaException;
import excepcionesSistema.MesaPausadaException;
import excepcionesSistema.TipoApuestaObligatoriaException;
import excepcionesSistema.TiposApuestaVaciaException;
import java.util.ArrayList;
import java.util.List;
import servicios.Fachada;
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
    private List<Integer> listaNumerosSorteados;
    private int balance;
    private int balanceAnterior;
    private Ronda rondaActual;
    private boolean hayPausa;
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
    
    public List<Participante> getParticipantes() {
        return this.listaParticipantes;
    }

    public int getBalance() {
        return balance;
    }
        
    public void removeParticipacionActiva(Participante participante) throws AbandonarMesaEnPausaException, ApuestasEnProgresoException {
        this.validarPausa();
        this.validarNoHayApuestasEnCursoDeParticipante(participante);
        this.eliminarParticipante(participante);
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
    
    public void recibirApuesta(Apuesta apuesta) throws MesaPausadaException {
        if (!this.hayPausa) {
            this.rondaActual.addApuesta(apuesta);
            // Se le avisa a a la fachada, ya que si el jugador se une a otras mesas, sus ventanas estaran pendientes del saldo modificado
            Fachada.getInstancia().notificar(Observador.Evento.APUESTA_REALIZADA);
        } else {
            throw new MesaPausadaException();
        }
    }
    
    public void accionarMesa(Efecto efecto) {
        //Necesario? ya que al inicializar la ventana mesa, el primer select queda seleccionado por defecto, nunca van a existir valores nulos
        if (efecto != null) {
            if (this.hayPausa) {
                this.hayPausa = false;
                realizarLiquidacion();
                inicializarNuevaRonda();
                // Doble notificación? (Fachada & mesa) -> se le notifica a la mesa para que el panel deje de estar pausado, pero... actualizarSaldoJugador()
                // Notificar a la fachada, ya que el participante quiere saber si gano plata en una mesa para poder utilizarla en las otras
                this.notificar(Observador.Evento.RONDA_REANUDADA);
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
    
    public int getBalanceAnterior() {
        return balanceAnterior;
    }
    
    public void addParticipante(Participante participante) {
        this.listaParticipantes.add(participante);
        this.notificar(Observador.Evento.PARTICIPANTE_AGREGADO);
    }
    
    public void cerrarMesa() throws MesaException {
        // Si bien la mesa se borra de la lista de mesas de la fachada, la instancia sigue presente, es necesario hacer otro borrado???
        if (this.hayPausa) {
            realizarLiquidacion();
            this.notificar(Observador.Evento.MESA_CERRADA);
            Fachada.getInstancia().removeMesa(this);
        } else {
            throw new MesaException("Solamente puede cerrar la mesa en caso de que esta se encuentre pausada.");
        }
    }
    
    public List<Integer> getlistaNumerosSorteados() {
        return this.listaNumerosSorteados;
    }
    
    public Casillero getCasilleroSegunCellCode(int cellCode) {
        // Alternativa -> if (tipo.contieneCasillero(cellCode)) ???
        for (TipoApuesta tipo : this.getTiposApuesta()) {
            for (Casillero cas : tipo.getCasillerosDisponibles()) {
                if (cas.getCellCode() == cellCode) return cas;
            }
        }
        return null;
    }
    
    public void validarPausa() throws AbandonarMesaEnPausaException {
        if (this.hayPausa) {
            throw new AbandonarMesaEnPausaException();
        }
    }
    
    public void validarNoHayApuestasEnCursoDeParticipante(Participante participante) throws ApuestasEnProgresoException {
        if (!this.rondaActual.getApuestasSegunParticipante(participante).isEmpty()) {
            throw new ApuestasEnProgresoException();
        }
    }
    
    public int getUltimoNumeroSorteado() {
        // posible manejo de excepciones
        return rondaActual.getNumeroSorteado();
    }
    
    public List<Casillero> getCasillerosNumericos() {
        for (TipoApuesta tipo : tiposApuesta) {
            if (tipo.getNombreTipo().equals("Apuesta directa")) {
                return tipo.getCasillerosDisponibles();
            }
        }
        return null;
    }
    
    public boolean tieneLanzamientos() {
        return !listaRondas.isEmpty();
    }
    
    public Ronda getUltimaRonda() {
        return listaRondas.get(listaRondas.size()-1);
    }
    
    public int getNroUltimaRonda() {
        return this.getNroRondaActual()-1;
    }
    
    public List<Casillero> obtenerTodosLosCasilleros() {
        List<Casillero> listaCasilleros = new ArrayList();
        for (TipoApuesta tipoApuesta : tiposApuesta) {
            for (Casillero cas : tipoApuesta.getCasillerosDisponibles()) {
                listaCasilleros.add(cas);
            }
        }
        return listaCasilleros;
    }
    
    public double calcularOcurrencia(Casillero casillero) {
        int cantRondas = this.listaRondas.size();
        int ocurrencia = 0;
        for (int numero : this.listaNumerosSorteados) {
            for (int cellCode : casillero.getNumerosVinculados()) {
                if (cellCode == numero) {
                    ocurrencia++;
                }
            }
        }
        return (double)ocurrencia / cantRondas * 100;
    }
    
    public void eliminarParticipante(Participante participante) {
        if (listaParticipantes.contains(participante)) {
            listaParticipantes.remove(participante);
            this.notificar(Observador.Evento.PARTICIPANTE_ELIMINADO);
        }
    }
    
    private void realizarLiquidacion() {
        List<Apuesta> listaApuestasGanadoras = this.rondaActual.getApuestasGanadoras();
        List<Apuesta> listaApuestasPerdedoras = this.rondaActual.getApuestasPerdedoras();
        if (!listaApuestasGanadoras.isEmpty()) {
            for (Participante participante : listaParticipantes) {
                procesarApuestas(participante, listaApuestasGanadoras);
            }
        }
        if (!listaApuestasPerdedoras.isEmpty()) {
            for (Apuesta apuesta : listaApuestasPerdedoras) {
                modificarBalanceMesa(apuesta.getMonto(), true);
            }
        }
    }
    
    private void procesarApuestas(Participante participante, List<Apuesta> apuestasGanadoras) {
        for (Apuesta apuesta : participante.getApuestas()) {
            if (apuestasGanadoras.contains(apuesta)) {
                procesarPagoApuesta(participante, apuesta);
            }
        }
    }
    
    private void procesarPagoApuesta(Participante participante, Apuesta apuesta) {
        Jugador jugador = participante.getJugador();
        int montoGanado = calcularMontoGanado(apuesta);
        actualizarSaldoJugador(jugador, montoGanado);
        modificarBalanceMesa(montoGanado, false);
    }
    
    private int calcularMontoGanado(Apuesta apuesta) {
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
        Fachada.getInstancia().notificar(Observador.Evento.PAGO_REALIZADO);
    }

    private void modificarBalanceMesa(int montoApostado, boolean laCasaGana) {
        if (laCasaGana) {
            this.balance += montoApostado;
        } else {
            this.balance -= montoApostado;
        }
    }
    
    private void generarSorteo(Efecto efecto) {
        balanceAnterior = this.balance;
        // Se trae a la instancia de apuesta directa para obtener los números 1 - 36
        this.rondaActual.setEfecto(efecto);
        // Pasaje de mesa por parámetro, ya que es esta quien es la responsable de conocer los casilleros que se encuentran actualmente disponibles
        this.rondaActual.sortearNumero(this);
        this.listaRondas.add(rondaActual);
        this.listaNumerosSorteados.add(rondaActual.getNumeroSorteado());
        this.hayPausa = true;
        this.notificar(Observador.Evento.MESA_PAUSADA);
    }
    
    private void inicializarMesa(List<TipoApuesta> tiposApuesta) {
        this.tiposApuesta = tiposApuesta;
        this.balance = 0;
        this.nroMesa = nro;
        nro++;
        this.hayPausa = false;
        this.listaParticipantes = new ArrayList();
        this.listaNumerosSorteados = new ArrayList();
        this.listaRondas = new ArrayList();
        inicializarNuevaRonda();
    }
    
    private void inicializarNuevaRonda() {
        this.rondaActual = new Ronda(null);
    }
}
