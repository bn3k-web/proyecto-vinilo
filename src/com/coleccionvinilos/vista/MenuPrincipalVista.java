package com.coleccionvinilos.vista;

import com.coleccionvinilos.controlador.GestorColeccion;
import javax.swing.*;
import java.awt.*;

public class MenuPrincipalVista extends JFrame {

    private GestorColeccion gestor;
    private JButton btnAgregar, btnBuscar, btnEliminar, btnEstadisticas, btnListar, btnSalir;

    public MenuPrincipalVista() {
        this.gestor = new GestorColeccion();
        inicializarComponentes();
        configurarVentana();
    }

    private void inicializarComponentes() {
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBackground(TemaColores.FONDO_PRINCIPAL);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panelPrincipal.add(crearPanelTitulo(), BorderLayout.NORTH);
        panelPrincipal.add(crearPanelBotones(), BorderLayout.CENTER);
        panelPrincipal.add(crearPanelFooter(), BorderLayout.SOUTH);

        add(panelPrincipal);
    }

    private JPanel crearPanelTitulo() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(TemaColores.FONDO_PRINCIPAL);

        JLabel lblTitulo = new JLabel("COLECCIÓN DE VINILOS");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitulo.setForeground(TemaColores.TEXTO_TITULO);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSubtitulo = new JLabel("Sistema de Gestión");
        lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 16));
        lblSubtitulo.setForeground(TemaColores.TEXTO_PRIMARIO);
        lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(lblTitulo);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(lblSubtitulo);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        return panel;
    }

    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 15));
        panel.setBackground(TemaColores.FONDO_SECUNDARIO);

        btnAgregar = CrearBotones.crearBotonMenu("Agregar Vinilo", TemaColores.BTN_PRIMARIO);
        btnBuscar = CrearBotones.crearBotonMenu("Buscar Vinilo", TemaColores.BTN_EXITO);
        btnEliminar = CrearBotones.crearBotonMenu("Eliminar Vinilo", TemaColores.BTN_PELIGRO);
        btnEstadisticas = CrearBotones.crearBotonMenu("Ver Estadísticas", TemaColores.BTN_AVISO);
        btnListar = CrearBotones.crearBotonMenu("Listar Vinilos", TemaColores.BTN_VIOLETA);
        btnSalir = CrearBotones.crearBotonMenu("Salir", TemaColores.BTN_SECUNDARIO);

        btnAgregar.addActionListener(e -> new AgregarViniloVista(this, gestor).setVisible(true));
        btnBuscar.addActionListener(e -> new BuscarViniloVista(this, gestor).setVisible(true));
        btnEliminar.addActionListener(e -> new EliminarViniloVista(this, gestor).setVisible(true));
        btnEstadisticas.addActionListener(e -> new EstadisticasVista(this, gestor).setVisible(true));
        btnListar.addActionListener(e -> new ListarVinilosVista(this, gestor).setVisible(true));
        btnSalir.addActionListener(e -> salir());

        panel.add(btnAgregar);
        panel.add(btnBuscar);
        panel.add(btnEliminar);
        panel.add(btnEstadisticas);
        panel.add(btnListar);
        panel.add(btnSalir);
        return panel;
    }

    private JPanel crearPanelFooter() {
        JPanel panel = new JPanel();
        panel.setBackground(TemaColores.FONDO_SECUNDARIO);

        JLabel lblFooter = new JLabel("v1.0 - Sistema de Gestión de Vinilos");
        lblFooter.setFont(new Font("Arial", Font.ITALIC, 12));
        lblFooter.setForeground(TemaColores.TEXTO_SECUNDARIO);
        panel.add(lblFooter);
        return panel;
    }
    private void salir() {
        int confirmacion = JOptionPane.showConfirmDialog(
            this, "¿Está seguro que desea salir?", "Confirmar Salida",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE
        );
        if (confirmacion == JOptionPane.YES_OPTION) System.exit(0);
    }

    private void configurarVentana() {
        setTitle("Sistema de Colección de Vinilos");
        setSize(500, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
        catch (Exception e) { e.printStackTrace(); }
        SwingUtilities.invokeLater(() -> new MenuPrincipalVista().setVisible(true));
    }
}
