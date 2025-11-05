package com.coleccionvinilos.vista;

import javax.swing.*;
import java.awt.*;

/**
 * Clase base para todas las ventanas del sistema.
 * Define estilo, colores, tamaño y botones comunes.
 * 
 * @author BN3K
 * @version 2.0
 */
public abstract class VentanaBase extends JDialog {

    protected JPanel panelPrincipal;

    public VentanaBase(JFrame parent, String titulo) {
        super(parent, titulo, true);
        configurarVentana();
        configurarPanelPrincipal();
    }

    private void configurarVentana() {
        setSize(500, 400);
        setLocationRelativeTo(getParent());
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void configurarPanelPrincipal() {
        panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBackground(TemaColores.FONDO_PRINCIPAL);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(panelPrincipal);
    }

    /**
     * Crea un botón estilizado delegando a CrearBotones
     * 
     * @param texto Texto del botón
     * @param color Color de fondo
     * @return JButton estilizado
     */
    protected JButton crearBoton(String texto, Color color) {
        return CrearBotones.crearBotonVentana(texto, color);
    }

    /**
     * Crea un botón "Cerrar" que cierra la ventana actual
     * 
     * @return JButton con acción de cerrar
     */
    protected JButton crearBotonCerrar() {
        JButton boton = crearBoton("Cerrar", TemaColores.BTN_SECUNDARIO);
        boton.addActionListener(e -> dispose());
        return boton;
    }
}