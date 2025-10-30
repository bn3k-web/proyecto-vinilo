package com.coleccionvinilos.servicio;

import com.coleccionvinilos.modelo.Vinilo;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona la colección de vinilos.
 * Responsabilidad: Administrar el almacenamiento y operaciones sobre la colección.
 * 
 * @author BN3K-WEB
 * @version 1.0
 */
public class ColeccionVinilos {
    private static final int CAPACIDAD_MAXIMA = 100;
    private List<Vinilo> vinilos;
    
    /**
     * Constructor que inicializa una colección vacía de vinilos
     */
    public ColeccionVinilos() {
        this.vinilos = new ArrayList<>();
    }
    
    /**
     * Agrega un vinilo a la colección
     * 
     * @param vinilo El vinilo a agregar
     * @return true si se agregó exitosamente, false si la colección está llena
     * @throws IllegalArgumentException si el vinilo es nulo o ya existe en la colección
     */
    public boolean agregarVinilo(Vinilo vinilo) {
        if (vinilo == null) {
            throw new IllegalArgumentException("El vinilo no puede ser nulo");
        }
        
        if (estaLlena()) {
            return false;
        }
        
        if (existeVinilo(vinilo.getNombreArtista(), vinilo.getNombreDisco())) {
            throw new IllegalArgumentException(
                "El vinilo '" + vinilo + "' ya existe en la colección"
            );
        }
        
        vinilos.add(vinilo);
        return true;
    }
    
    /**
     * Busca un vinilo en la colección según criterios
     * 
     * @param artista Nombre del artista (puede ser null para buscar solo por disco)
     * @param disco Nombre del disco (puede ser null para buscar solo por artista)
     * @return El vinilo encontrado o null si no existe
     * @throws IllegalArgumentException si ambos criterios son null
     */
    public Vinilo buscarVinilo(String artista, String disco) {
        if (artista == null && disco == null) {
            throw new IllegalArgumentException(
                "Debe proporcionar al menos un criterio de búsqueda"
            );
        }
        
        for (Vinilo vinilo : vinilos) {
            if (vinilo.coincideCon(artista, disco)) {
                return vinilo;
            }
        }
        return null;
    }
    
    /**
     * Verifica si un vinilo existe en la colección
     * 
     * @param artista Nombre del artista
     * @param disco Nombre del disco
     * @return true si el vinilo existe, false en caso contrario
     */
    public boolean existeVinilo(String artista, String disco) {
        return buscarVinilo(artista, disco) != null;
    }
    
    /**
     * Obtiene la cantidad de vinilos almacenados en la colección
     * 
     * @return Número de vinilos en la colección
     */
    public int obtenerCantidadVinilos() {
        return vinilos.size();
    }
    
    /**
     * Obtiene la cantidad de espacios disponibles en la colección
     * 
     * @return Número de espacios libres
     */
    public int obtenerEspaciosDisponibles() {
        return CAPACIDAD_MAXIMA - vinilos.size();
    }
    
    /**
     * Verifica si la colección está llena
     * 
     * @return true si la colección alcanzó su capacidad máxima, false en caso contrario
     */
    public boolean estaLlena() {
        return vinilos.size() >= CAPACIDAD_MAXIMA;
    }
    
    /**
     * Verifica si la colección está vacía
     * 
     * @return true si no hay vinilos en la colección, false en caso contrario
     */
    public boolean estaVacia() {
        return vinilos.isEmpty();
    }
    
    /**
     * Obtiene una copia de la lista de vinilos
     * Retorna una nueva lista para proteger la colección interna
     * 
     * @return Lista con todos los vinilos de la colección
     */
    public List<Vinilo> listarVinilos() {
        return new ArrayList<>(vinilos);
    }
    
    /**
     * Elimina un vinilo de la colección
     * 
     * @param artista Nombre del artista del vinilo a eliminar
     * @param disco Nombre del disco del vinilo a eliminar
     * @return true si se eliminó exitosamente, false si no se encontró
     */
    public boolean eliminarVinilo(String artista, String disco) {
        Vinilo vinilo = buscarVinilo(artista, disco);
        if (vinilo != null) {
            vinilos.remove(vinilo);
            return true;
        }
        return false;
    }
    
    /**
     * Obtiene la capacidad máxima de la colección
     * 
     * @return Capacidad máxima (100 vinilos)
     */
    public static int getCapacidadMaxima() {
        return CAPACIDAD_MAXIMA;
    }
    
    /**
     * Representación en string de la colección con estadísticas
     * 
     * @return String con información de la colección
     */
    @Override
    public String toString() {
        return String.format(
            "Colección de Vinilos: %d/%d (%d espacios disponibles)",
            obtenerCantidadVinilos(),
            CAPACIDAD_MAXIMA,
            obtenerEspaciosDisponibles()
        );
    }
}
