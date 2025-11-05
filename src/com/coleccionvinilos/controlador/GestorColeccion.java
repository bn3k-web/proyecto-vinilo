package com.coleccionvinilos.controlador;

import com.coleccionvinilos.modelo.Vinilo;
import com.coleccionvinilos.servicio.ColeccionVinilos;
import java.util.Scanner;
import java.util.List;

public class GestorColeccion {
    private ColeccionVinilos coleccion;
    private Scanner scanner;
    
    /**
     * Constructor que inicializa el gestor con una nueva colección
     */
    public GestorColeccion() {
        this.coleccion = new ColeccionVinilos();
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Obtiene la colección de vinilos
     * @return La colección de vinilos gestionada
     */
    public ColeccionVinilos getColeccion() {
        return coleccion;
    }
    
    /**
     * Muestra el menú principal de opciones del sistema
     */
    private void mostrarMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("SISTEMA DE GESTIÓN DE COLECCIÓN DE VINILOS");
        System.out.println("=".repeat(50));
        System.out.println("1. Agregar vinilo");
        System.out.println("2. Buscar vinilo");
        System.out.println("3. Eliminar vinilo");
        System.out.println("4. Ver estadísticas de la colección");
        System.out.println("5. Listar todos los vinilos");
        System.out.println("6. Salir");
        System.out.println("=".repeat(50));
    }
    
    /**
     * Solicita los datos al usuario y agrega un nuevo vinilo a la colección
     */
    private void agregarViniloInteractivo() {
        try {
            System.out.println("\n--- Agregar Nuevo Vinilo ---");
            
            System.out.print("Nombre del artista: ");
            String artista = scanner.nextLine().trim();
            
            System.out.print("Nombre del disco: ");
            String disco = scanner.nextLine().trim();
            
            System.out.print("Año de lanzamiento: ");
            int año = Integer.parseInt(scanner.nextLine().trim());
            
            Vinilo vinilo = new Vinilo(artista, disco, año);
            
            if (coleccion.agregarVinilo(vinilo)) {
                System.out.println("✓ Vinilo '" + vinilo + "' agregado exitosamente");
            } else {
                System.out.println("✗ Error: La colección está llena");
            }
            
        } catch (IllegalArgumentException e) {
            System.out.println("✗ Error de validación: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("✗ Error inesperado: " + e.getMessage());
        }
    }
    
    /**
     * Solicita criterios de búsqueda y busca un vinilo en la colección
     */
    private void buscarViniloInteractivo() {
        try {
            System.out.println("\n--- Buscar Vinilo ---");
            System.out.println("(Presione Enter para omitir un criterio)");
            
            System.out.print("Nombre del artista: ");
            String artista = scanner.nextLine().trim();
            if (artista.isEmpty()) artista = null;
            
            System.out.print("Nombre del disco: ");
            String disco = scanner.nextLine().trim();
            if (disco.isEmpty()) disco = null;
            
            if (artista == null && disco == null) {
                System.out.println("✗ Debe proporcionar al menos un criterio de búsqueda");
                return;
            }
            
            Vinilo vinilo = coleccion.buscarVinilo(artista, disco);
            
            if (vinilo != null) {
                System.out.println("✓ Vinilo encontrado: " + vinilo);
            } else {
                System.out.println("✗ Vinilo no encontrado en la colección");
            }
            
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }
    
    /**
     * Solicita los datos y elimina un vinilo de la colección
     */
    private void eliminarViniloInteractivo() {
        try {
            System.out.println("\n--- Eliminar Vinilo ---");
            
            System.out.print("Nombre del artista: ");
            String artista = scanner.nextLine().trim();
            
            System.out.print("Nombre del disco: ");
            String disco = scanner.nextLine().trim();
            
            if (coleccion.eliminarVinilo(artista, disco)) {
                System.out.println("✓ Vinilo eliminado exitosamente");
            } else {
                System.out.println("✗ Vinilo no encontrado en la colección");
            }
            
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }
    
    /**
     * Muestra las estadísticas actuales de la colección
     */
    private void mostrarEstadisticas() {
        System.out.println("\n--- Estadísticas de la Colección ---");
        System.out.println("Total de vinilos: " + coleccion.obtenerCantidadVinilos());
        System.out.println("Espacios disponibles: " + coleccion.obtenerEspaciosDisponibles());
        System.out.println("Capacidad máxima: " + ColeccionVinilos.getCapacidadMaxima());
        
        double porcentaje = (double) coleccion.obtenerCantidadVinilos() / 
                           ColeccionVinilos.getCapacidadMaxima() * 100;
        System.out.printf("Ocupación: %.1f%%\n", porcentaje);
    }
    
    /**
     * Lista todos los vinilos almacenados en la colección
     */
    private void listarTodosLosVinilos() {
        System.out.println("\n--- Lista de Vinilos ---");
        
        if (coleccion.estaVacia()) {
            System.out.println("La colección está vacía");
            return;
        }
        
        List<Vinilo> vinilos = coleccion.listarVinilos();
        for (int i = 0; i < vinilos.size(); i++) {
            System.out.println((i + 1) + ". " + vinilos.get(i));
        }
    }
    
    /**
     * Ejecuta el bucle principal del programa
     * Muestra el menú y procesa las opciones del usuario
     */
    public void ejecutar() {
        boolean continuar = true;
        
        while (continuar) {
            mostrarMenu();
            System.out.print("\nSeleccione una opción: ");
            
            try {
                String opcion = scanner.nextLine().trim();
                
                switch (opcion) {
                    case "1":
                        agregarViniloInteractivo();
                        break;
                    case "2":
                        buscarViniloInteractivo();
                        break;
                    case "3":
                        eliminarViniloInteractivo();
                        break;
                    case "4":
                        mostrarEstadisticas();
                        break;
                    case "5":
                        listarTodosLosVinilos();
                        break;
                    case "6":
                        System.out.println("\n¡Hasta luego!");
                        continuar = false;
                        break;
                    default:
                        System.out.println("✗ Opción no válida");
                }
                
            } catch (Exception e) {
                System.out.println("✗ Error inesperado: " + e.getMessage());
            }
        }
        
        scanner.close();
    }
}