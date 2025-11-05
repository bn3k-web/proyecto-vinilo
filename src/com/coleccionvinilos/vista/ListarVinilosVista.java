package com.coleccionvinilos.vista;

import com.coleccionvinilos.controlador.GestorColeccion;
import com.coleccionvinilos.modelo.Vinilo;
import com.coleccionvinilos.servicio.ColeccionVinilos;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;

/**
 * Ventana para listar todos los vinilos.
 * Hereda de VentanaBase para mantener consistencia visual.
 * 
 * @author BN3K
 * @version 2.0
 */
public class ListarVinilosVista extends VentanaBase {

    private ColeccionVinilos coleccion;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JLabel lblInfo;

    public ListarVinilosVista(JFrame parent, GestorColeccion gestor) {
        super(parent, "Lista de Vinilos");
        this.coleccion = gestor.getColeccion();
        inicializarComponentes();
        cargarDatos();
    }

    private void inicializarComponentes() {
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(TemaColores.FONDO_PRINCIPAL);
        JLabel lblTitulo = new JLabel("📋 Lista de Vinilos");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(TemaColores.BTN_VIOLETA);
        panelTitulo.add(lblTitulo);

        String[] columnas = {"#", "Artista", "Disco", "Año"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tabla = new JTable(modeloTabla);
        tabla.setFont(new Font("Arial", Font.PLAIN, 13));
        tabla.setRowHeight(25);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setGridColor(new Color(60, 60, 70));

        JTableHeader header = tabla.getTableHeader();
        header.setBackground(TemaColores.BTN_VIOLETA);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setReorderingAllowed(false);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createLineBorder(TemaColores.FONDO_TERCARIO));

        lblInfo = new JLabel();
        lblInfo.setFont(new Font("Arial", Font.BOLD, 13));
        lblInfo.setForeground(TemaColores.TEXTO_SECUNDARIO);
        lblInfo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JButton btnActualizar = crearBoton("🔄 Actualizar", TemaColores.BTN_PRIMARIO);
        btnActualizar.addActionListener(e -> cargarDatos());

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(TemaColores.FONDO_PRINCIPAL);
        panelBotones.add(btnActualizar);
        panelBotones.add(crearBotonCerrar());

        panelPrincipal.add(panelTitulo, BorderLayout.NORTH);
        panelPrincipal.add(scroll, BorderLayout.CENTER);
        panelPrincipal.add(lblInfo, BorderLayout.SOUTH);
        panelPrincipal.add(panelBotones, BorderLayout.PAGE_END);
    }

    private void cargarDatos() {
        modeloTabla.setRowCount(0);
        List<Vinilo> vinilos = coleccion.listarVinilos();

        if (vinilos.isEmpty()) {
            modeloTabla.addRow(new Object[]{"", "No hay vinilos en la colección", "", ""});
        } else {
            for (int i = 0; i < vinilos.size(); i++) {
                Vinilo v = vinilos.get(i);
                modeloTabla.addRow(new Object[]{i + 1, v.getNombreArtista(), v.getNombreDisco(), v.getAñoLanzamiento()});
            }
        }
        lblInfo.setText("Total de vinilos: " + vinilos.size());
    }
}
