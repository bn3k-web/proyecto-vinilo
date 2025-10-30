package com.coleccionvinilos.vista;

import com.coleccionvinilos.controlador.GestorColeccion;
import com.coleccionvinilos.servicio.ColeccionVinilos;
import javax.swing.*;
import java.awt.*;

/**
 * Ventana para mostrar estadísticas de la colección.
 * Responsabilidad: Mostrar información sobre la colección.
 */
public class EstadisticasVista extends JDialog {
    
    private ColeccionVinilos coleccion;
    private JButton btnCerrar;
    
    public EstadisticasVista(JFrame parent, GestorColeccion gestor) {
        super(parent, "Estadísticas de la Colección", true);
        this.coleccion = gestor.getColeccion();
        inicializarComponentes();
        configurarVentana();
    }
    
    private void inicializarComponentes() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(TemaColores.FONDO_PRINCIPAL);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // título
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(TemaColores.FONDO_PRINCIPAL);
        JLabel lblTitulo = new JLabel("Estadísticas de la Colección");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(TemaColores.BTN_AVISO);
        panelTitulo.add(lblTitulo);
        
        // estadísticas
        JPanel panelEstadisticas = crearPanelEstadisticas();
        
        // botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotones.setBackground(TemaColores.FONDO_PRINCIPAL);
        
        btnCerrar = crearBoton("Cerrar", TemaColores.BTN_SECUNDARIO);
        btnCerrar.addActionListener(e -> dispose());
        panelBotones.add(btnCerrar);
        
        panel.add(panelTitulo, BorderLayout.NORTH);
        panel.add(panelEstadisticas, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);
        
        add(panel);
    }
    
    private JPanel crearPanelEstadisticas() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(TemaColores.FONDO_SECUNDARIO);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        
        int totalVinilos = coleccion.obtenerCantidadVinilos();
        int espaciosDisponibles = coleccion.obtenerEspaciosDisponibles();
        int capacidadMaxima = ColeccionVinilos.getCapacidadMaxima();
        double porcentajeOcupacion = (double) totalVinilos / capacidadMaxima * 100;
        
        panel.add(crearEtiquetaEstadistica("Total de Vinilos:", 
            String.valueOf(totalVinilos), TemaColores.BTN_PRIMARIO));
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        panel.add(crearEtiquetaEstadistica("Espacios Disponibles:", 
            String.valueOf(espaciosDisponibles), TemaColores.BTN_EXITO));
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        panel.add(crearEtiquetaEstadistica("Capacidad Máxima:", 
            String.valueOf(capacidadMaxima), TemaColores.BTN_SECUNDARIO));
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        panel.add(crearEtiquetaEstadistica("Ocupación:", 
            String.format("%.1f%%", porcentajeOcupacion), TemaColores.BTN_AVISO));
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // barra
        JProgressBar barraProgreso = new JProgressBar(0, capacidadMaxima);
        barraProgreso.setValue(totalVinilos);
        barraProgreso.setStringPainted(true);
        barraProgreso.setString(totalVinilos + " / " + capacidadMaxima);
        barraProgreso.setFont(new Font("Arial", Font.BOLD, 12));
        barraProgreso.setPreferredSize(new Dimension(400, 30));
        barraProgreso.setMaximumSize(new Dimension(400, 30));
        barraProgreso.setBackground(TemaColores.FONDO_TERCARIO);
        
        if (porcentajeOcupacion < 50) {
            barraProgreso.setForeground(TemaColores.BTN_EXITO);
        } else if (porcentajeOcupacion < 80) {
            barraProgreso.setForeground(TemaColores.BTN_AVISO);
        } else {
            barraProgreso.setForeground(TemaColores.BTN_PELIGRO);
        }
        
        panel.add(barraProgreso);
        
        // estado
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        String estado;
        Color colorEstado;
        
        if (coleccion.estaVacia()) {
            estado = "La colección está vacía";
            colorEstado = TemaColores.TEXTO_SECUNDARIO;
        } else if (coleccion.estaLlena()) {
            estado = "La colección está llena";
            colorEstado = TemaColores.BTN_PELIGRO;
        } else if (porcentajeOcupacion > 80) {
            estado = "La colección está casi llena";
            colorEstado = TemaColores.BTN_AVISO;
        } else {
            estado = "La colección tiene espacio disponible";
            colorEstado = TemaColores.BTN_EXITO;
        }
        
        JLabel lblEstado = new JLabel(estado);
        lblEstado.setFont(new Font("Arial", Font.BOLD, 14));
        lblEstado.setForeground(colorEstado);
        lblEstado.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblEstado);
        
        return panel;
    }
    
    private JPanel crearEtiquetaEstadistica(String label, String valor, Color color) {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        panel.setBackground(TemaColores.FONDO_SECUNDARIO);
        panel.setMaximumSize(new Dimension(400, 30));
        
        JLabel lblLabel = new JLabel(label);
        lblLabel.setFont(new Font("Arial", Font.BOLD, 16));
        lblLabel.setForeground(TemaColores.TEXTO_PRIMARIO);
        
        JLabel lblValor = new JLabel(valor);
        lblValor.setFont(new Font("Arial", Font.BOLD, 20));
        lblValor.setForeground(color);
        
        panel.add(lblLabel, BorderLayout.WEST);
        panel.add(lblValor, BorderLayout.EAST);
        return panel;
    }

    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setPreferredSize(new Dimension(120, 40));
        boton.setBorderPainted(false);

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
    
    private void configurarVentana() {
        setSize(500, 450);
        setLocationRelativeTo(getParent());
        setResizable(false);
    }
}
