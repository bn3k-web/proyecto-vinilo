package com.coleccionvinilos;

import javax.swing.SwingUtilities;
import com.coleccionvinilos.vista.MenuPrincipalVista;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuPrincipalVista ventana = new MenuPrincipalVista();
            ventana.setVisible(true);
        });
    }
}
