/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaNegocio;

import excepcionesSistema.RestriccionTipoApuestaException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Agustin
 */
public final class ApuestaColores extends TipoApuesta {
    
    public ApuestaColores() {
        this.factorPago = 2;
        setCasillerosDisponibles();
    }

    @Override
    public String getNombreTipo() {
        return "Apuesta por colores";
    }

    @Override
    protected void setCasillerosDisponibles() {
        this.listaCasilleros.add(new Casillero(43, this.listaNumerosRojo(), this));
        this.listaCasilleros.add(new Casillero(44, this.listaNumerosNegro(), this));
    }
    
    private List<Integer> listaNumerosRojo() {
        List<Integer> lista = new ArrayList();
        lista.add(1);
        lista.add(3);
        lista.add(5);
        lista.add(7);
        lista.add(9);
        lista.add(12);
        lista.add(14);
        lista.add(16);
        lista.add(18);
        lista.add(19);
        lista.add(21);
        lista.add(23);
        lista.add(25);
        lista.add(27);
        lista.add(30);
        lista.add(32);
        lista.add(34);
        lista.add(36);
        return lista;
    }
    
    private List<Integer> listaNumerosNegro() {
        List<Integer> lista = new ArrayList();
        lista.add(2);
        lista.add(4);
        lista.add(6);
        lista.add(8);
        lista.add(10);
        lista.add(11);
        lista.add(13);
        lista.add(15);
        lista.add(17);
        lista.add(20);
        lista.add(22);
        lista.add(24);
        lista.add(26);
        lista.add(28);
        lista.add(29);
        lista.add(31);
        lista.add(33);
        lista.add(35);
        return lista;
    }

    
    
//Apuesta Colores: Apostar a números rojos o negros. Pago: 2 a 1.
//Restricciones: si un jugador pierde una apuesta por valor N a un color, 
//no podrá volver a apostar  un monto superior a N en la siguiente ronda
//en el mismo color. Si un jugador apuesta montos a los dos colores en la misma ronda, 
//la restricción anterior no aplica,salvo que haya salido el cero

    
    @Override
    public void validarApuestaSegunTipo(Participante participante, Apuesta apuestaActual) throws RestriccionTipoApuestaException {
        Ronda ultimaRonda = participante.getMesa().getUltimaRonda();
        List<Apuesta> apuestasPerdedoras = participante.getApuestasPerdedorasUltimaRonda();
        List<Apuesta> apuestasActivas = participante.getApuestasEnCurso();
        
        if (ultimaRonda.getNumeroSorteado() == 0) {
            int montoApuestasColoresAnterior = getMontoApuestasEnAmbosColores(apuestasPerdedoras);
            int montoApuestasColoresActual = getMontoApuestasEnAmbosColores(apuestasActivas);
            if (montoApuestasColoresAnterior > 0 && (montoApuestasColoresActual > montoApuestasColoresAnterior)) {
                throw new RestriccionTipoApuestaException("No puede apostar a ambos casilleros si el resultado anterior fue cero.");
            }
        }
        
        
        // Evaluamos si alguna de las apuestas perdedoras de la ultima ronda pertenece al participante, y si alguna de las mismas es igual a alguno de los casilleros de color en el cual se está apostando
        for (Apuesta apuesta : apuestasPerdedoras) {
            int cellCode = apuesta.getCasillero().getCellCode();
            if (cellCode == apuestaActual.getCasillero().getCellCode() && participante.getApuestas().contains(apuesta)) {
                if (apuestaActual.getMonto() > apuesta.getMonto()) {
                    throw new RestriccionTipoApuestaException("No puede apostar una cantidad mayor en el mismo casillero de color.");
                }
            }
        }
//        if (!ultimaApuesta.esGanadora() && apuestaActual.getCasillero().equals(ultimaApuesta.getCasillero()) && apuestaActual.getMonto() > ultimaApuesta.getMonto()) {
//            throw new RestriccionTipoApuestaException("");
//        }
    }
    
    private int getMontoApuestasEnAmbosColores(List<Apuesta> listaApuestas) {
        int montoAlRojo = 0;
        int montoAlNegro = 0;
        for (Apuesta apuesta : listaApuestas) {
            if (apuesta.getCasillero().getCellCode() == 43) montoAlRojo = apuesta.getMonto();
            if (apuesta.getCasillero().getCellCode() == 44) montoAlNegro = apuesta.getMonto();
        }
        if (montoAlRojo > 0 && montoAlNegro > 0) {
            return montoAlRojo + montoAlNegro;
        } else {
            return 0;
        }
    }
}
