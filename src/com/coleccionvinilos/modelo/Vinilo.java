package com.coleccionvinilos.modelo;

import java.time.Year;
import java.util.Objects;

/**
 * Clase que representa un vinilo individual.
 * Responsabilidad: Mantener la información de un vinilo y validar sus datos.
 * 
 * @author BN3K-WEB
 * @version 1.0
 */
public class Vinilo {
    private String nombreArtista;
    private String nombreDisco;
    private int añoLanzamiento;
    
    /**
     * Constructor que crea un nuevo vinilo con validación de datos
     * 
     * @param nombreArtista Nombre del artista o banda
     * @param nombreDisco Nombre del disco o álbum
     * @param añoLanzamiento Año de lanzamiento del disco
     * @throws IllegalArgumentException si algún dato es inválido
     */
    public Vinilo(String nombreArtista, String nombreDisco, int añoLanzamiento) {
        this.nombreArtista = nombreArtista;
        this.nombreDisco = nombreDisco;
        this.añoLanzamiento = añoLanzamiento;
        validarDatos();
    }
    
    /**
     * Valida que los datos del vinilo sean correctos
     * 
     * @throws IllegalArgumentException si algún dato es inválido
     */
    private void validarDatos() {
        if (nombreArtista == null || nombreArtista.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del artista no puede estar vacío");
        }
        
        if (nombreDisco == null || nombreDisco.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del disco no puede estar vacío");
        }
        
        int añoActual = Year.now().getValue();
        if (añoLanzamiento < 1877 || añoLanzamiento > añoActual) {
            throw new IllegalArgumentException(
                "El año debe estar entre 1877 y " + añoActual
            );
        }
    }
    
    /**
     * Verifica si el vinilo coincide con los criterios de búsqueda
     * La comparación es case-insensitive
     * 
     * @param artista Nombre del artista a buscar (null para ignorar)
     * @param disco Nombre del disco a buscar (null para ignorar)
     * @return true si coincide con los criterios, false en caso contrario
     */
    public boolean coincideCon(String artista, String disco) {
        boolean coincideArtista = (artista == null) || 
            this.nombreArtista.equalsIgnoreCase(artista);
        boolean coincideDisco = (disco == null) || 
            this.nombreDisco.equalsIgnoreCase(disco);
        
        return coincideArtista && coincideDisco;
    }
    
    /**
     * Obtiene el nombre del artista
     * 
     * @return Nombre del artista
     */
    public String getNombreArtista() {
        return nombreArtista;
    }
    
    /**
     * Obtiene el nombre del disco
     * 
     * @return Nombre del disco
     */
    public String getNombreDisco() {
        return nombreDisco;
    }
    
    /**
     * Obtiene el año de lanzamiento
     * 
     * @return Año de lanzamiento
     */
    public int getAñoLanzamiento() {
        return añoLanzamiento;
    }
    
    /**
     * Establece el nombre del artista con validación
     * 
     * @param nombreArtista Nuevo nombre del artista
     * @throws IllegalArgumentException si el nombre es inválido
     */
    public void setNombreArtista(String nombreArtista) {
        if (nombreArtista == null || nombreArtista.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del artista no puede estar vacío");
        }
        this.nombreArtista = nombreArtista;
    }
    
    /**
     * Establece el nombre del disco con validación
     * 
     * @param nombreDisco Nuevo nombre del disco
     * @throws IllegalArgumentException si el nombre es inválido
     */
    public void setNombreDisco(String nombreDisco) {
        if (nombreDisco == null || nombreDisco.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del disco no puede estar vacío");
        }
        this.nombreDisco = nombreDisco;
    }
    
    /**
     * Establece el año de lanzamiento con validación
     * 
     * @param añoLanzamiento Nuevo año de lanzamiento
     * @throws IllegalArgumentException si el año es inválido
     */
    public void setAñoLanzamiento(int añoLanzamiento) {
        int añoActual = Year.now().getValue();
        if (añoLanzamiento < 1877 || añoLanzamiento > añoActual) {
            throw new IllegalArgumentException(
                "El año debe estar entre 1877 y " + añoActual
            );
        }
        this.añoLanzamiento = añoLanzamiento;
    }
    
    /**
     * Compara este vinilo con otro objeto
     * Dos vinilos son iguales si tienen el mismo artista y disco (case-insensitive)
     * 
     * @param obj Objeto a comparar
     * @return true si son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vinilo vinilo = (Vinilo) obj;
        return nombreArtista.equalsIgnoreCase(vinilo.nombreArtista) &&
               nombreDisco.equalsIgnoreCase(vinilo.nombreDisco);
    }
    
    /**
     * Genera el código hash del vinilo
     * 
     * @return Código hash basado en artista y disco
     */
    @Override
    public int hashCode() {
        return Objects.hash(
            nombreArtista.toLowerCase(), 
            nombreDisco.toLowerCase()
        );
    }
    
    /**
     * Representación en string del vinilo
     * 
     * @return String con formato "Artista - Disco (Año)"
     */
    @Override
    public String toString() {
        return nombreArtista + " - " + nombreDisco + " (" + añoLanzamiento + ")";
    }
}