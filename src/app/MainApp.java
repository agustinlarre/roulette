/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package app;

import presentacion.VentanaInicio;
import excepcionesSistema.ApuestaException;
import excepcionesSistema.MesaException;
import excepcionesSistema.SaldoInvalidoException;
import excepcionesSistema.UsuarioException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicaNegocio.*;
import servicios.Fachada;

/**
 *
 * @author digregor
 */
public class MainApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Precargas
        precarga();
        
        //testPrueba();
        
        new VentanaInicio().setVisible(true);
    }
    
    private static void precarga() {
        // Crupieres
        Crupier crupier1 = new Crupier("101", "PetLover01");
        Crupier crupier2 = new Crupier("102", "BurnOut07");
        Crupier crupier3 = new Crupier("103", "DDA2023");
        Fachada.getInstancia().addCrupier(crupier1);
        Fachada.getInstancia().addCrupier(crupier2);
        Fachada.getInstancia().addCrupier(crupier3);
        
        // Jugadores
        Jugador jugador1 = new Jugador("201", "LuckyOne321", 500);
        Jugador jugador2 = new Jugador("202", "DarkSide55", 750);
        Jugador jugador3 = new Jugador("203", "PinkKitten77", 250);
        Jugador jugador4 = new Jugador("204", "HappyGuy99", 1000);
        Jugador jugador5 = new Jugador("205", "UnluckyBastard03", 1500);
        Fachada.getInstancia().addJugador(jugador1);
        Fachada.getInstancia().addJugador(jugador2);
        Fachada.getInstancia().addJugador(jugador3);
        Fachada.getInstancia().addJugador(jugador4);
        Fachada.getInstancia().addJugador(jugador5);
        
        // Tipos de apuesta
        TipoApuesta apuestaDirecta = new ApuestaDirecta();
        TipoApuesta apuestaColores = new ApuestaColores();
        TipoApuesta apuestaDocena = new ApuestaDocenas();
        Fachada.getInstancia().addTipoApuesta(apuestaDirecta);
        Fachada.getInstancia().addTipoApuesta(apuestaColores);
        Fachada.getInstancia().addTipoApuesta(apuestaDocena);
        
        // Fichas
        Ficha ficha1 = new Ficha(1);
        Ficha ficha5 = new Ficha(5);
        Ficha ficha10 = new Ficha(10);
        Ficha ficha50 = new Ficha(50);
        Ficha ficha100 = new Ficha(100);
        Fachada.getInstancia().addFicha(ficha1);
        Fachada.getInstancia().addFicha(ficha5);
        Fachada.getInstancia().addFicha(ficha10);
        Fachada.getInstancia().addFicha(ficha50);
        Fachada.getInstancia().addFicha(ficha100);
        
        // Efectos
        Efecto aleatorioCompleto = new ModoAleatorioCompleto();
        Efecto aleatorioParcial = new ModoAleatorioParcial();
        Efecto simulador = new ModoSimulador();
        Fachada.getInstancia().addEfecto(aleatorioCompleto);
        Fachada.getInstancia().addEfecto(aleatorioParcial);
        Fachada.getInstancia().addEfecto(simulador);
    }
    
    private static void testPrueba() {        
        // ---------- CRUPIER ----------
        System.out.println("---------- CRUPIER ----------");
        // Caso login crupier
        Sesion sesionCrupier = null;
        Crupier crupier = null;
        Mesa mesa = null;
        try {
            sesionCrupier = Fachada.getInstancia().loginCrupier("101", "PetLover01");
            crupier = (Crupier) sesionCrupier.getUsuario();
            System.out.println("Login CRUPIER correcto");
        } catch (UsuarioException ex) {
            System.out.println(ex.getMessage());
        }
        
        // Caso iniciar mesa
        try {
            List<TipoApuesta> listaTiposApuesta = Fachada.getInstancia().getTiposApuesta();
            List<TipoApuesta> tiposApuestaElegidos = new ArrayList();
            tiposApuestaElegidos.add(listaTiposApuesta.get(0));
            tiposApuestaElegidos.add(listaTiposApuesta.get(1));
            mesa = new Mesa(tiposApuestaElegidos);
            
            Fachada.getInstancia().addMesa(mesa);
            System.out.println("Mesa iniciada correctamente");
        } catch(MesaException mesaEx) {
            System.out.println(mesaEx.getMessage());
        }
         
        // ---------- JUGADOR ----------
        System.out.println("---------- JUGADOR ----------");
        // Caso login jugador
        Sesion sesionJugador1 = null;
        Jugador jugador1 = null;
        Sesion sesionJugador2 = null;
        Jugador jugador2 = null;
        try {
            sesionJugador1 = Fachada.getInstancia().loginJugador("201", "LuckyOne321");
            jugador1 = (Jugador) sesionJugador1.getUsuario();
            System.out.println("Login JUGADOR correcto");
            sesionJugador2 = Fachada.getInstancia().loginJugador("202", "DarkSide55");
            jugador2 = (Jugador) sesionJugador2.getUsuario();
            System.out.println("Login JUGADOR correcto");
        } catch (UsuarioException ex) {
            System.out.print(ex.getMessage());
        }
        
        // Caso unirse mesa
        Participante participanteJ1 = new Participante(jugador1, mesa);
        try {
            jugador1.participar(participanteJ1);
            // VentanaMesaJugador recibe por parametro al participante
        } catch (MesaException ex1) {
            System.out.println("Debe seleccionar una mesa.");
        }
        
        // Mesa JUGADOR -> APOSTAR
        Ficha ficha1 = new Ficha(1);
        Ficha ficha5 = new Ficha(5);
        
        Casillero casilleroColoresRojo = null;
        // Hardcodeo, obtengo el casillero de colores rojos
        for (TipoApuesta tipo : mesa.getTiposApuesta()) {
            for (Casillero cas : tipo.getCasillerosDisponibles()) {
                if (cas.getCellCode() == 43) casilleroColoresRojo = cas;
            }
        }
        
        // Hardcodeo, creo una apuesta, seleccionando una ficha y un casillero (Puedo tambien agregar una nueva ficha al mismo casillero)
        Apuesta apuesta1 = new Apuesta();
        apuesta1.addFicha(ficha5);
        apuesta1.setCasillero(casilleroColoresRojo);
        Apuesta apuesta2 = new Apuesta();
        apuesta2.setCasillero(casilleroColoresRojo);
        apuesta2.addFicha(ficha5);
        apuesta2.addFicha(ficha1);
        apuesta2.addFicha(ficha5);
        apuesta2.addFicha(ficha1);
        
        System.out.println("Saldo del jugador: " + participanteJ1.getJugador().getSaldo());
        
        // Caso de uso, realizo una apuesta
        try {
            participanteJ1.realizarApuesta(casilleroColoresRojo, ficha1);
            System.out.println("El participante " + participanteJ1.getJugador().getCedula() + " ha apostado $" + participanteJ1.getApuestas().get(participanteJ1.getApuestas().size()-1).getMonto());
        } catch (ApuestaException ex) {
            System.out.println(ex.getMessage());
        }
        
        // Caso de uso, modifico una apuesta ya realizada, agregando una nueva ficha
        try {
            participanteJ1.realizarApuesta(casilleroColoresRojo, ficha1);
            System.out.println("El participante " + participanteJ1.getJugador().getCedula() + " ahora ha apostado $" + participanteJ1.getApuestas().get(participanteJ1.getApuestas().size()-1).getMonto());
        } catch (ApuestaException ex) {
            System.out.println(ex.getMessage());
        }
        
        System.out.println("Saldo del jugador: " + participanteJ1.getJugador().getSaldo());
        
        // ---------- CRUPIER ----------
        // Mesa CRUPIER -> LANZAR
        List<Efecto> listaEfectos = Fachada.getInstancia().getEfectos();
        Efecto efectoSeleccionado = listaEfectos.get(0);
        mesa.accionarMesa(efectoSeleccionado);
        System.out.println("/----- Resultado RONDA n°" + mesa.getNroRondaActual() + "-----/");
        System.out.println("Ronda n°" + mesa.getNroRondaActual() + " - Efecto seleccionado: " + efectoSeleccionado.getNombreEfecto() + " - Número sorteado: " + mesa.getRondaActual().getNumeroSorteado());
        mesa.accionarMesa(efectoSeleccionado);
        
        System.out.println("Saldo del jugador: " + participanteJ1.getJugador().getSaldo());
        
        // ---------- JUGADOR ----------
        // Caso de uso, pierdo apuesta e intento hacer martingala
        try {
            participanteJ1.realizarApuesta(casilleroColoresRojo, ficha5);
        } catch (ApuestaException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
