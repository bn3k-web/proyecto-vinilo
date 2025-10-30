package com.coleccionvinilos;

import javax.swing.SwingUtilities;
import com.coleccionvinilos.vista.MenuPrincipalVista;

/**
 * Clase principal para ejecutar el Sistema de Gestión de Colección de Vinilos (versión GUI).
 * Responsabilidad: Punto de entrada de la aplicación con interfaz gráfica.
 * 
 * @author BN3K-WEB
 * @version 1.0
 */
public class Main {

    /**
     * Método principal que inicia la aplicación con interfaz gráfica.
     * 
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {

        // Mostrar título en consola (opcional)
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║   SISTEMA DE GESTIÓN DE COLECCIÓN DE VINILOS      ║");
        System.out.println("║              Interfaz Gráfica (Swing)              ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
        System.out.println();

        // Ejecutar la vista principal en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            MenuPrincipalVista ventana = new MenuPrincipalVista();
            ventana.setVisible(true);
        });
    }
}
