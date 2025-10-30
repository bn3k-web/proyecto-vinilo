package com.coleccionvinilos.vista;

import com.coleccionvinilos.controlador.GestorColeccion;
import com.coleccionvinilos.modelo.Vinilo;
import com.coleccionvinilos.servicio.ColeccionVinilos;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

/**
 * Ventana para listar todos los vinilos de la colección.
 * Responsabilidad: Mostrar todos los vinilos en una tabla.
 */
public class ListarVinilosVista extends JDialog {
    
    private ColeccionVinilos coleccion;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JButton btnCerrar;
    private JButton btnActualizar;
    private JLabel lblInfoTotal;
    
    public ListarVinilosVista(JFrame parent, GestorColeccion gestor) {
        super(parent, "Lista de Vinilos", true);
        this.coleccion = gestor.getColeccion();
        inicializarComponentes();
        configurarVentana();
        cargarDatos();
    }
    
    private void inicializarComponentes() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(TemaColores.FONDO_PRINCIPAL);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // título
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(TemaColores.FONDO_PRINCIPAL);
        JLabel lblTitulo = new JLabel("Lista de Vinilos");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(TemaColores.BTN_VIOLETA);
        panelTitulo.add(lblTitulo);
        
        // tabla
        String[] columnas = {"#", "Artista", "Disco", "Año"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tabla = new JTable(modeloTabla);
        tabla.setFont(new Font("Arial", Font.PLAIN, 13));
        tabla.setRowHeight(25);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setGridColor(new Color(60, 60, 60));
        tabla.setBackground(TemaColores.FONDO_SECUNDARIO);
        tabla.setForeground(TemaColores.TEXTO_PRIMARIO);
        tabla.setSelectionBackground(new Color(70, 90, 120));
        tabla.setSelectionForeground(Color.WHITE);
        
        JTableHeader header = tabla.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setBackground(new Color(70, 60, 90));
        header.setForeground(Color.WHITE);
        header.setReorderingAllowed(false);
        
        tabla.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(250);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(80);
        
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.getViewport().setBackground(TemaColores.FONDO_SECUNDARIO);
        scrollPane.setBorder(BorderFactory.createLineBorder(TemaColores.RESULTADO_BORDE));
        
        // panel info
        JPanel panelInfo = new JPanel();
        panelInfo.setBackground(TemaColores.FONDO_TERCARIO);
        panelInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        lblInfoTotal = new JLabel("Total de vinilos: 0");
        lblInfoTotal.setFont(new Font("Arial", Font.BOLD, 13));
        lblInfoTotal.setForeground(TemaColores.TEXTO_PRIMARIO);
        panelInfo.add(lblInfoTotal);
        
        // botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.setBackground(TemaColores.FONDO_PRINCIPAL);
        
        btnActualizar = crearBoton("Actualizar", TemaColores.BTN_PRIMARIO);
        btnActualizar.addActionListener(e -> cargarDatos());
        
        btnCerrar = crearBoton("Cerrar", TemaColores.BTN_SECUNDARIO);
        btnCerrar.addActionListener(e -> dispose());
        
        panelBotones.add(btnActualizar);
        panelBotones.add(btnCerrar);
        
        // panel inferior
        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.setBackground(TemaColores.FONDO_PRINCIPAL);
        panelInferior.add(panelInfo, BorderLayout.NORTH);
        panelInferior.add(panelBotones, BorderLayout.CENTER);
        
        panel.add(panelTitulo, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(panelInferior, BorderLayout.SOUTH);
        
        add(panel);
    }
    
    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setPreferredSize(new Dimension(140, 40));
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
    
    private void cargarDatos() {
        modeloTabla.setRowCount(0);
        
        List<Vinilo> vinilos = coleccion.listarVinilos();
        
        if (vinilos.isEmpty()) {
            Object[] fila = {"", "No hay vinilos en la colección", "", ""};
            modeloTabla.addRow(fila);
        } else {
            for (int i = 0; i < vinilos.size(); i++) {
                Vinilo v = vinilos.get(i);
                Object[] fila = {
                    i + 1,
                    v.getNombreArtista(),
                    v.getNombreDisco(),
                    v.getAñoLanzamiento()
                };
                modeloTabla.addRow(fila);
            }
        }
        
        lblInfoTotal.setText("Total de vinilos: " + vinilos.size());
    }
    
    private void configurarVentana() {
        setSize(700, 500);
        setLocationRelativeTo(getParent());
        setResizable(true);
    }
}
