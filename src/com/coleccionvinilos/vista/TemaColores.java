package com.coleccionvinilos.vista;

import java.awt.Color;

/**
 * Clase centralizada de colores para el modo oscuro de la aplicación.
 * Permite mantener consistencia visual entre todas las vistas.
 * Si se desea cambiar el tema (modo claro, por ejemplo),
 * basta con ajustar los valores aquí.
 */
public final class TemaColores {

    // === FONDOS ===
    public static final Color FONDO_PRINCIPAL   = new Color(20, 24, 33);
    public static final Color FONDO_SECUNDARIO  = new Color(30, 33, 40);
    public static final Color FONDO_TERCARIO    = new Color(25, 28, 35);

    // === TEXTO ===
    public static final Color TEXTO_TITULO      = new Color(255, 255, 255);
    public static final Color TEXTO_PRIMARIO    = new Color(200, 205, 215);
    public static final Color TEXTO_SECUNDARIO  = new Color(150, 150, 150);

    // === INPUTS ===
    public static final Color INPUT_FONDO       = new Color(40, 44, 52);
    public static final Color INPUT_BORDE       = new Color(65, 70, 80);

    // === BOTONES ===
    public static final Color BTN_PRIMARIO      = new Color(0, 90, 200);   // Azul oscuro
    public static final Color BTN_EXITO         = new Color(40, 110, 60);  // Verde oscuro
    public static final Color BTN_PELIGRO       = new Color(170, 40, 30);  // Rojo oscuro
    public static final Color BTN_AVISO         = new Color(180, 100, 0);  // Naranja oscuro
    public static final Color BTN_SECUNDARIO    = new Color(70, 85, 95);   // Gris azulado oscuro
    public static final Color BTN_VIOLETA       = new Color(110, 45, 130); // Violeta oscuro

    // === RESULTADOS / BORDES ===
    public static final Color RESULTADO_FONDO   = new Color(25, 28, 35);
    public static final Color RESULTADO_BORDE   = new Color(70, 75, 85);

    private TemaColores() {}
}
