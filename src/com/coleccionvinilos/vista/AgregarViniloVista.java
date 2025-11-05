package com.coleccionvinilos.vista;

import com.coleccionvinilos.controlador.GestorColeccion;
import com.coleccionvinilos.modelo.Vinilo;
import com.coleccionvinilos.servicio.ColeccionVinilos;
import javax.swing.*;
import java.awt.*;

public class AgregarViniloVista extends VentanaBase {

    private ColeccionVinilos coleccion;
    private JTextField txtArtista, txtDisco, txtAño;
    private JButton btnGuardar, btnCancelar;

    public AgregarViniloVista(JFrame parent, GestorColeccion gestor) {
        super(parent, "Agregar Vinilo");
        this.coleccion = gestor.getColeccion();
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(TemaColores.FONDO_PRINCIPAL);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitulo = new JLabel("Agregar Nuevo Vinilo");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(TemaColores.BTN_EXITO);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panelFormulario.add(lblTitulo, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1; gbc.gridx = 0;
        panelFormulario.add(new JLabel("Artista:"), gbc);
        gbc.gridx = 1;
        txtArtista = new JTextField(20);
        panelFormulario.add(txtArtista, gbc);

        gbc.gridy = 2; gbc.gridx = 0;
        panelFormulario.add(new JLabel("Disco:"), gbc);
        gbc.gridx = 1;
        txtDisco = new JTextField(20);
        panelFormulario.add(txtDisco, gbc);

        gbc.gridy = 3; gbc.gridx = 0;
        panelFormulario.add(new JLabel("Año:"), gbc);
        gbc.gridx = 1;
        txtAño = new JTextField(20);
        panelFormulario.add(txtAño, gbc);

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(TemaColores.FONDO_PRINCIPAL);
        btnGuardar = crearBoton("💾 Guardar", TemaColores.BTN_PRIMARIO);
        btnCancelar = crearBotonCerrar();
        btnGuardar.addActionListener(e -> guardarVinilo());
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);

        panelPrincipal.add(panelFormulario, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
    }

    private void guardarVinilo() {
        try {
            String artista = txtArtista.getText().trim();
            String disco = txtDisco.getText().trim();
            String añoTexto = txtAño.getText().trim();

            if (artista.isEmpty() || disco.isEmpty() || añoTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int año = Integer.parseInt(añoTexto);
            Vinilo vinilo = new Vinilo(artista, disco, año);

            if (coleccion.agregarVinilo(vinilo)) {
                JOptionPane.showMessageDialog(this, "Vinilo agregado exitosamente:\n" + vinilo, "Éxito", JOptionPane.INFORMATION_MESSAGE);
                txtArtista.setText(""); txtDisco.setText(""); txtAño.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "La colección está llena", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
