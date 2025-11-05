package com.coleccionvinilos.vista;

import com.coleccionvinilos.controlador.GestorColeccion;
import com.coleccionvinilos.modelo.Vinilo;
import com.coleccionvinilos.servicio.ColeccionVinilos;
import javax.swing.*;
import java.awt.*;

public class BuscarViniloVista extends VentanaBase {

    private ColeccionVinilos coleccion;
    private JTextField txtArtista, txtDisco;
    private JTextArea txtResultado;
    private JButton btnBuscar;

    public BuscarViniloVista(JFrame parent, GestorColeccion gestor) {
        super(parent, "Buscar Vinilo");
        this.coleccion = gestor.getColeccion();
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(TemaColores.FONDO_PRINCIPAL);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitulo = new JLabel("Buscar Vinilo");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(TemaColores.BTN_PRIMARIO);
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

        txtResultado = new JTextArea(6, 30);
        txtResultado.setEditable(false);
        txtResultado.setBackground(TemaColores.FONDO_SECUNDARIO);
        txtResultado.setForeground(TemaColores.TEXTO_PRIMARIO);
        txtResultado.setFont(new Font("Monospaced", Font.PLAIN, 13));

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(TemaColores.FONDO_PRINCIPAL);
        btnBuscar = crearBoton("🔍 Buscar", TemaColores.BTN_PRIMARIO);
        btnBuscar.addActionListener(e -> buscarVinilo());
        panelBotones.add(btnBuscar);
        panelBotones.add(crearBotonCerrar());

        panelPrincipal.add(panelFormulario, BorderLayout.NORTH);
        panelPrincipal.add(new JScrollPane(txtResultado), BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
    }

    private void buscarVinilo() {
        String artista = txtArtista.getText().trim();
        String disco = txtDisco.getText().trim();

        if (artista.isEmpty() && disco.isEmpty()) {
            txtResultado.setText("Debe ingresar al menos un criterio.");
            txtResultado.setForeground(Color.RED);
            return;
        }

        Vinilo vinilo = coleccion.buscarVinilo(artista.isEmpty() ? null : artista, disco.isEmpty() ? null : disco);

        if (vinilo != null) {
            txtResultado.setForeground(new Color(80, 200, 120));
            txtResultado.setText("✅ VINILO ENCONTRADO\n\n" + vinilo);
        } else {
            txtResultado.setForeground(new Color(220, 80, 80));
            txtResultado.setText("❌ No se encontró ningún vinilo con esos datos.");
        }
    }
}
