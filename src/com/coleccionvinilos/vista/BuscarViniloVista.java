package com.coleccionvinilos.vista;

import com.coleccionvinilos.controlador.GestorColeccion;
import com.coleccionvinilos.modelo.Vinilo;
import com.coleccionvinilos.servicio.ColeccionVinilos;
import javax.swing.*;
import java.awt.*;

/**
 * Ventana para buscar vinilos en la colección.
 * Responsabilidad: Buscar vinilos por artista y/o disco.
 */
public class BuscarViniloVista extends JDialog {
    
    private ColeccionVinilos coleccion;
    private JTextField txtArtista;
    private JTextField txtDisco;
    private JTextArea txtResultado;
    private JButton btnBuscar;
    private JButton btnCerrar;
    
    public BuscarViniloVista(JFrame parent, GestorColeccion gestor) {
        super(parent, "Buscar Vinilo", true);
        this.coleccion = gestor.getColeccion();
        inicializarComponentes();
        configurarVentana();
    }
    
    private void inicializarComponentes() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(TemaColores.FONDO_PRINCIPAL);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // formulario
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(TemaColores.FONDO_PRINCIPAL);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // título
        JLabel lblTitulo = new JLabel("Buscar Vinilo");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(TemaColores.TEXTO_TITULO);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 15, 0);
        panelFormulario.add(lblTitulo, gbc);
        
        // instrucciones
        gbc.gridy = 1;
        JLabel lblInstrucciones = new JLabel("(Deje en blanco si no desea buscar por ese criterio)");
        lblInstrucciones.setFont(new Font("Arial", Font.ITALIC, 12));
        lblInstrucciones.setForeground(TemaColores.TEXTO_SECUNDARIO);
        panelFormulario.add(lblInstrucciones, gbc);
        
        // artista
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel lblArtista = new JLabel("Artista:");
        lblArtista.setFont(new Font("Arial", Font.BOLD, 14));
        lblArtista.setForeground(TemaColores.TEXTO_PRIMARIO);
        panelFormulario.add(lblArtista, gbc);
        
        gbc.gridx = 1;
        txtArtista = crearCampoTexto();
        panelFormulario.add(txtArtista, gbc);
        
        // disco
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel lblDisco = new JLabel("Disco:");
        lblDisco.setFont(new Font("Arial", Font.BOLD, 14));
        lblDisco.setForeground(TemaColores.TEXTO_PRIMARIO);
        panelFormulario.add(lblDisco, gbc);
        
        gbc.gridx = 1;
        txtDisco = crearCampoTexto();
        panelFormulario.add(txtDisco, gbc);
        
        // resultado
        JPanel panelResultado = new JPanel(new BorderLayout(5, 5));
        panelResultado.setBackground(TemaColores.FONDO_SECUNDARIO);
        panelResultado.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(TemaColores.RESULTADO_BORDE),
                "Resultado",
                0, 0,
                new Font("Arial", Font.BOLD, 12),
                TemaColores.TEXTO_PRIMARIO
        ));
        
        txtResultado = new JTextArea(6, 30);
        txtResultado.setFont(new Font("Monospaced", Font.PLAIN, 13));
        txtResultado.setEditable(false);
        txtResultado.setBackground(TemaColores.RESULTADO_FONDO);
        txtResultado.setForeground(Color.WHITE);
        txtResultado.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JScrollPane scrollResultado = new JScrollPane(txtResultado);
        scrollResultado.setBorder(null);
        panelResultado.add(scrollResultado, BorderLayout.CENTER);
        
        // botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.setBackground(TemaColores.FONDO_PRINCIPAL);
        
        btnBuscar = crearBoton("Buscar", TemaColores.BTN_PRIMARIO);
        btnBuscar.addActionListener(e -> buscarVinilo());
        
        btnCerrar = crearBoton("Cerrar", TemaColores.BTN_SECUNDARIO);
        btnCerrar.addActionListener(e -> dispose());
        
        panelBotones.add(btnBuscar);
        panelBotones.add(btnCerrar);
        
        panel.add(panelFormulario, BorderLayout.NORTH);
        panel.add(panelResultado, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);
        
        add(panel);
    }

    private JTextField crearCampoTexto() {
        JTextField txt = new JTextField(20);
        txt.setFont(new Font("Arial", Font.PLAIN, 14));
        txt.setBackground(TemaColores.INPUT_FONDO);
        txt.setForeground(Color.WHITE);
        txt.setCaretColor(Color.WHITE);
        txt.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(TemaColores.INPUT_BORDE),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        return txt;
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
    
    private void buscarVinilo() {
        try {
            String artista = txtArtista.getText().trim();
            String disco = txtDisco.getText().trim();
            
            if (artista.isEmpty()) artista = null;
            if (disco.isEmpty()) disco = null;
            
            if (artista == null && disco == null) {
                JOptionPane.showMessageDialog(
                    this,
                    "Debe proporcionar al menos un criterio de búsqueda",
                    "Error",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            
            Vinilo vinilo = coleccion.buscarVinilo(artista, disco);
            
            if (vinilo != null) {
                txtResultado.setText(
                    "✓ VINILO ENCONTRADO\n\n" +
                    "Artista: " + vinilo.getNombreArtista() + "\n" +
                    "Disco: " + vinilo.getNombreDisco() + "\n" +
                    "Año: " + vinilo.getAñoLanzamiento()
                );
                txtResultado.setForeground(new Color(76, 175, 80));
            } else {
                txtResultado.setText("✗ No se encontró ningún vinilo con esos criterios");
                txtResultado.setForeground(new Color(244, 67, 54));
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                this,
                "Error: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    private void configurarVentana() {
        setSize(500, 450);
        setLocationRelativeTo(getParent());
        setResizable(false);
    }
}
