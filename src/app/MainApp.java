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
       // Precargas
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
        Jugador jugador4 = new Jugador("201", "HappyGuy99");
        Jugador jugador5 = new Jugador("201", "UnluckyBastard03");
        Fachada.getInstancia().agregarJugador(jugador1);
        Fachada.getInstancia().agregarJugador(jugador2);
        Fachada.getInstancia().agregarJugador(jugador3);
        Fachada.getInstancia().agregarJugador(jugador4);
        Fachada.getInstancia().agregarJugador(jugador5);
        
        // Efectos
        Efecto aleatorioCompleto = new Efecto("Aleatorio completo");
        Efecto aleatorioParcial = new Efecto("Aleatorio parcial");
        Efecto simulador = new Efecto("Simulador");
    }
}
