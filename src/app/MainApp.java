/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package app;

import componentes.VentanaInicio;
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
        
        new VentanaInicio().setVisible(true);
    }
    
    private static void precarga() {
        // Crupieres
        Crupier crupier1 = new Crupier("101", "PetLover01");
        Crupier crupier2 = new Crupier("102", "BurnOut07");
        Crupier crupier3 = new Crupier("103", "DDA2023");
        Fachada.getInstancia().agregarCrupier(crupier1);
        Fachada.getInstancia().agregarCrupier(crupier2);
        Fachada.getInstancia().agregarCrupier(crupier3);
        
        // Jugadores
        Jugador jugador1 = new Jugador("201", "LuckyOne321");
        Jugador jugador2 = new Jugador("202", "DarkSide55");
        Jugador jugador3 = new Jugador("203", "PinkKitten77");
        Jugador jugador4 = new Jugador("204", "HappyGuy99");
        Jugador jugador5 = new Jugador("205", "UnluckyBastard03");
        Fachada.getInstancia().agregarJugador(jugador1);
        Fachada.getInstancia().agregarJugador(jugador2);
        Fachada.getInstancia().agregarJugador(jugador3);
        Fachada.getInstancia().agregarJugador(jugador4);
        Fachada.getInstancia().agregarJugador(jugador5);
        
        // Tipos de apuesta
        TipoApuesta apuestaDirecta = new TipoApuesta("Apuesta directa");
        TipoApuesta apuestaColores = new TipoApuesta("Apuesta colores");
        TipoApuesta apuestaDocena = new TipoApuesta("Apuesta docena");
        Fachada.getInstancia().agregarTipoApuesta(apuestaDirecta);
        Fachada.getInstancia().agregarTipoApuesta(apuestaColores);
        Fachada.getInstancia().agregarTipoApuesta(apuestaDocena);
    }
}
