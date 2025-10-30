package com.coleccionvinilos.vista;

import com.coleccionvinilos.controlador.GestorColeccion;
import com.coleccionvinilos.servicio.ColeccionVinilos;
import javax.swing.*;
import java.awt.*;

/**
 * Ventana para eliminar vinilos de la colección.
 * Responsabilidad: Eliminar vinilos por artista y disco.
 */
public class EliminarViniloVista extends JDialog {
    
    private ColeccionVinilos coleccion;
    private JTextField txtArtista;
    private JTextField txtDisco;
    private JButton btnEliminar;
    private JButton btnCancelar;
    
    public EliminarViniloVista(JFrame parent, GestorColeccion gestor) {
        super(parent, "Eliminar Vinilo", true);
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
        JLabel lblTitulo = new JLabel("Eliminar Vinilo");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(TemaColores.BTN_PELIGRO);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 15, 0);
        panelFormulario.add(lblTitulo, gbc);
        
        // advertencia
        gbc.gridy = 1;
        JLabel lblAdvertencia = new JLabel("Esta acción no se puede deshacer");
        lblAdvertencia.setFont(new Font("Arial", Font.ITALIC, 12));
        lblAdvertencia.setForeground(TemaColores.BTN_AVISO);
        panelFormulario.add(lblAdvertencia, gbc);
        
        // artista
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 5, 5, 5);
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
        gbc.insets = new Insets(5, 5, 5, 5);
        JLabel lblDisco = new JLabel("Disco:");
        lblDisco.setFont(new Font("Arial", Font.BOLD, 14));
        lblDisco.setForeground(TemaColores.TEXTO_PRIMARIO);
        panelFormulario.add(lblDisco, gbc);
        
        gbc.gridx = 1;
        txtDisco = crearCampoTexto();
        panelFormulario.add(txtDisco, gbc);
        
        // botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.setBackground(TemaColores.FONDO_PRINCIPAL);
        
        btnEliminar = crearBoton("Eliminar", TemaColores.BTN_PELIGRO);
        btnEliminar.addActionListener(e -> eliminarVinilo());
        
        btnCancelar = crearBoton("Cancelar", TemaColores.BTN_SECUNDARIO);
        btnCancelar.addActionListener(e -> dispose());
        
        panelBotones.add(btnEliminar);
        panelBotones.add(btnCancelar);
        
        panel.add(panelFormulario, BorderLayout.CENTER);
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

    private void eliminarVinilo() {
        try {
            String artista = txtArtista.getText().trim();
            String disco = txtDisco.getText().trim();
            
            if (artista.isEmpty() || disco.isEmpty()) {
                JOptionPane.showMessageDialog(
                    this,
                    "Debe proporcionar el artista y el disco",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            
            int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro que desea eliminar:\n" + artista + " - " + disco + "?",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );
            
            if (confirmacion == JOptionPane.YES_OPTION) {
                if (coleccion.eliminarVinilo(artista, disco)) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Vinilo eliminado exitosamente",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(
                        this,
                        "No se encontró ningún vinilo con esos datos",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
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

    private void limpiarCampos() {
        txtArtista.setText("");
        txtDisco.setText("");
        txtArtista.requestFocus();
    }

    private void configurarVentana() {
        setSize(450, 300);
        setLocationRelativeTo(getParent());
        setResizable(false);
    }
}
