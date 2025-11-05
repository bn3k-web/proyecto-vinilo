package com.coleccionvinilos.vista;

import com.coleccionvinilos.controlador.GestorColeccion;
import com.coleccionvinilos.servicio.ColeccionVinilos;
import javax.swing.*;
import java.awt.*;

public class EliminarViniloVista extends VentanaBase {

    private ColeccionVinilos coleccion;
    private JTextField txtArtista, txtDisco;
    private JButton btnEliminar;

    public EliminarViniloVista(JFrame parent, GestorColeccion gestor) {
        super(parent, "Eliminar Vinilo");
        this.coleccion = gestor.getColeccion();
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel panelFormulario = new JPanel(new GridLayout(3, 2, 10, 10));
        panelFormulario.setBackground(TemaColores.FONDO_PRINCIPAL);

        JLabel lblTitulo = new JLabel("Eliminar Vinilo");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(TemaColores.BTN_PELIGRO);

        panelFormulario.add(new JLabel("Artista:"));
        txtArtista = new JTextField();
        panelFormulario.add(txtArtista);

        panelFormulario.add(new JLabel("Disco:"));
        txtDisco = new JTextField();
        panelFormulario.add(txtDisco);

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(TemaColores.FONDO_PRINCIPAL);
        btnEliminar = crearBoton("🗑 Eliminar", TemaColores.BTN_PELIGRO);
        btnEliminar.addActionListener(e -> eliminarVinilo());
        panelBotones.add(btnEliminar);
        panelBotones.add(crearBotonCerrar());

        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelFormulario, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
    }

    private void eliminarVinilo() {
        String artista = txtArtista.getText().trim();
        String disco = txtDisco.getText().trim();

        if (coleccion.eliminarVinilo(artista, disco)) {
            JOptionPane.showMessageDialog(this, "Vinilo eliminado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Vinilo no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
