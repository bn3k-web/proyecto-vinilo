package com.coleccionvinilos.vista;

import com.coleccionvinilos.controlador.GestorColeccion;
import com.coleccionvinilos.modelo.Vinilo;
import com.coleccionvinilos.servicio.ColeccionVinilos;
import javax.swing.*;
import java.awt.*;

/**
 * Ventana para agregar un nuevo vinilo a la colección.
 * Responsabilidad: Capturar los datos del vinilo y agregarlo.
 */
public class AgregarViniloVista extends JDialog {
    
    private ColeccionVinilos coleccion;
    private JTextField txtArtista;
    private JTextField txtDisco;
    private JTextField txtAño;
    private JButton btnGuardar;
    private JButton btnCancelar;
    
    public AgregarViniloVista(JFrame parent, GestorColeccion gestor) {
        super(parent, "Agregar Vinilo", true);
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
        JLabel lblTitulo = new JLabel("Agregar Nuevo Vinilo");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(TemaColores.TEXTO_TITULO);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        panelFormulario.add(lblTitulo, gbc);
        
        // artista
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel lblArtista = new JLabel("Artista:");
        lblArtista.setFont(new Font("Arial", Font.BOLD, 14));
        lblArtista.setForeground(TemaColores.TEXTO_PRIMARIO);
        panelFormulario.add(lblArtista, gbc);
        
        gbc.gridx = 1;
        txtArtista = crearCampoTexto();
        panelFormulario.add(txtArtista, gbc);
        
        // disco
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel lblDisco = new JLabel("Disco:");
        lblDisco.setFont(new Font("Arial", Font.BOLD, 14));
        lblDisco.setForeground(TemaColores.TEXTO_PRIMARIO);
        panelFormulario.add(lblDisco, gbc);
        
        gbc.gridx = 1;
        txtDisco = crearCampoTexto();
        panelFormulario.add(txtDisco, gbc);
        
        // año
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel lblAño = new JLabel("Año:");
        lblAño.setFont(new Font("Arial", Font.BOLD, 14));
        lblAño.setForeground(TemaColores.TEXTO_PRIMARIO);
        panelFormulario.add(lblAño, gbc);
        
        gbc.gridx = 1;
        txtAño = crearCampoTexto();
        panelFormulario.add(txtAño, gbc);
        
        // botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.setBackground(TemaColores.FONDO_PRINCIPAL);
        
        btnGuardar = crearBoton("Guardar", TemaColores.BTN_EXITO);
        btnGuardar.addActionListener(e -> guardarVinilo());
        
        btnCancelar = crearBoton("Cancelar", TemaColores.BTN_SECUNDARIO);
        btnCancelar.addActionListener(e -> dispose());
        
        panelBotones.add(btnGuardar);
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
        boton.setPreferredSize(new Dimension(120, 38));
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
    
    private void guardarVinilo() {
        try {
            String artista = txtArtista.getText().trim();
            String disco = txtDisco.getText().trim();
            String añoTexto = txtAño.getText().trim();
            
            if (artista.isEmpty() || disco.isEmpty() || añoTexto.isEmpty()) {
                JOptionPane.showMessageDialog(
                    this, "Todos los campos son obligatorios",
                    "Error", JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            
            int año = Integer.parseInt(añoTexto);
            Vinilo vinilo = new Vinilo(artista, disco, año);
            
            if (coleccion.agregarVinilo(vinilo)) {
                JOptionPane.showMessageDialog(
                    this,
                    "Vinilo agregado exitosamente:\n" + vinilo,
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE
                );
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(
                    this,
                    "La colección está llena (100 vinilos)",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                this,
                "El año debe ser un número válido",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(
                this,
                e.getMessage(),
                "Error de Validación",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    private void limpiarCampos() {
        txtArtista.setText("");
        txtDisco.setText("");
        txtAño.setText("");
        txtArtista.requestFocus();
    }
    
    private void configurarVentana() {
        setSize(450, 350);
        setLocationRelativeTo(getParent());
        setResizable(false);
    }
}
