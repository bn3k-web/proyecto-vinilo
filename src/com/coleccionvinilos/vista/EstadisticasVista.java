package com.coleccionvinilos.vista;

import com.coleccionvinilos.controlador.GestorColeccion;
import com.coleccionvinilos.servicio.ColeccionVinilos;
import javax.swing.*;
import java.awt.*;

/**
 * Ventana para mostrar estadísticas de la colección.
 * Hereda estilo y botones base de VentanaBase.
 * 
 * @author BN3K
 * @version 2.0
 */
public class EstadisticasVista extends VentanaBase {

    private ColeccionVinilos coleccion;

    public EstadisticasVista(JFrame parent, GestorColeccion gestor) {
        super(parent, "Estadísticas de la Colección");
        this.coleccion = gestor.getColeccion();
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBackground(TemaColores.FONDO_PRINCIPAL);
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel lblTitulo = new JLabel("📊 Estadísticas de la Colección");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(TemaColores.BTN_AVISO);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCentral.add(lblTitulo);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 20)));

        int totalVinilos = coleccion.obtenerCantidadVinilos();
        int capacidadMaxima = ColeccionVinilos.getCapacidadMaxima();
        int disponibles = coleccion.obtenerEspaciosDisponibles();
        double porcentaje = (double) totalVinilos / capacidadMaxima * 100;

        panelCentral.add(crearEtiquetaEstadistica("📀 Total de Vinilos:", String.valueOf(totalVinilos), TemaColores.BTN_PRIMARIO));
        panelCentral.add(Box.createRigidArea(new Dimension(0, 10)));
        panelCentral.add(crearEtiquetaEstadistica("✅ Espacios Disponibles:", String.valueOf(disponibles), TemaColores.BTN_EXITO));
        panelCentral.add(Box.createRigidArea(new Dimension(0, 10)));
        panelCentral.add(crearEtiquetaEstadistica("📦 Capacidad Máxima:", String.valueOf(capacidadMaxima), TemaColores.BTN_SECUNDARIO));
        panelCentral.add(Box.createRigidArea(new Dimension(0, 15)));
        panelCentral.add(crearEtiquetaEstadistica("📈 Ocupación:", String.format("%.1f%%", porcentaje), TemaColores.BTN_AVISO));
        panelCentral.add(Box.createRigidArea(new Dimension(0, 20)));

        // Barra de progreso
        JProgressBar barra = new JProgressBar(0, capacidadMaxima);
        barra.setValue(totalVinilos);
        barra.setStringPainted(true);
        barra.setString(totalVinilos + " / " + capacidadMaxima);
        barra.setPreferredSize(new Dimension(400, 30));
        barra.setMaximumSize(new Dimension(400, 30));
        barra.setFont(new Font("Arial", Font.BOLD, 12));

        if (porcentaje < 50)
            barra.setForeground(TemaColores.BTN_EXITO);
        else if (porcentaje < 80)
            barra.setForeground(TemaColores.BTN_AVISO);
        else
            barra.setForeground(TemaColores.BTN_PELIGRO);

        panelCentral.add(barra);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 20)));

        // Estado final
        JLabel lblEstado = new JLabel(obtenerEstadoTexto(porcentaje));
        lblEstado.setFont(new Font("Arial", Font.BOLD, 14));
        lblEstado.setForeground(obtenerColorEstado(porcentaje));
        lblEstado.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCentral.add(lblEstado);

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(TemaColores.FONDO_PRINCIPAL);
        panelBotones.add(crearBotonCerrar());

        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
    }

    private JPanel crearEtiquetaEstadistica(String titulo, String valor, Color color) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(TemaColores.FONDO_PRINCIPAL);
        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 15));
        lblTitulo.setForeground(TemaColores.TEXTO_PRIMARIO);

        JLabel lblValor = new JLabel(valor);
        lblValor.setFont(new Font("Arial", Font.BOLD, 18));
        lblValor.setForeground(color);

        panel.add(lblTitulo, BorderLayout.WEST);
        panel.add(lblValor, BorderLayout.EAST);
        return panel;
    }

    private String obtenerEstadoTexto(double porcentaje) {
        if (porcentaje == 0) return "⚪ La colección está vacía";
        if (porcentaje >= 100) return "🔴 La colección está llena";
        if (porcentaje >= 80) return "🟡 La colección está casi llena";
        return "🟢 La colección tiene espacio disponible";
    }

    private Color obtenerColorEstado(double porcentaje) {
        if (porcentaje == 0) return TemaColores.TEXTO_SECUNDARIO;
        if (porcentaje >= 100) return TemaColores.BTN_PELIGRO;
        if (porcentaje >= 80) return TemaColores.BTN_AVISO;
        return TemaColores.BTN_EXITO;
    }
}
