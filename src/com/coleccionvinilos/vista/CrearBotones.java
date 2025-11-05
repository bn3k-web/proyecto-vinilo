package com.coleccionvinilos.vista;

import javax.swing.*;
import java.awt.*;


public final class CrearBotones {
    
    private CrearBotones() {} 
    
    public static JButton crearBoton(String texto, Color color, int fontSize, Dimension size) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, fontSize));
        boton.setForeground(Color.WHITE);
        boton.setBackground(color);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setPreferredSize(size);

        // Efecto hover
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(color.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(color);
            }
        });
        return boton;
    }
    
    public static JButton crearBotonVentana(String texto, Color color) {
        return crearBoton(texto, color, 14, new Dimension(130, 40));
    }
    
    public static JButton crearBotonMenu(String texto, Color color) {
        return crearBoton(texto, color, 16, new Dimension(400, 50));
    }
}