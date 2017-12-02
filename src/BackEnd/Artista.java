package BackEnd;

import BackEnd.Estructuras.ArbolAlbumes;
import java.io.Serializable;

/**
 * Clase Artista Contiene los elementos de un artista los cuales son su nombre,
 * y sus discos con sus respectivas canciones
 *
 * @author Maximiliano Casale
 */
public class Artista implements Serializable {

    private final String _Nombre;
    private final ArbolAlbumes _Albumes;
    private Artista _HijoDer;
    private Artista _HijoIzq;

    /**
     * Constructor Artista Inicia un nuevo artista, asignandole su nombre un
     * nuevo album en una estructura de arbol y una cancion de ese album
     *
     * @param nombre nombre del artista
     * @param album album nuevo
     * @param titulo cancion nueva
     */
    public Artista(String nombre, String album, Cancion titulo) {
        this._Nombre = nombre;
        this._Albumes = new ArbolAlbumes();
        Album nuevo = new Album(album, titulo);
        _Albumes.Insertar(_Albumes.getRaiz(), nuevo);
        this._HijoDer = null;
        this._HijoIzq = null;
    }

    // Getters y Setters de los atributos
    public String getNombre() {
        return _Nombre;
    }

    public Artista getHijoDer() {
        return _HijoDer;
    }

    public void setHijoDer(Artista _HijoDer) {
        this._HijoDer = _HijoDer;
    }

    public Artista getHijoIzq() {
        return _HijoIzq;
    }

    public void setHijoIzq(Artista _HijoIzq) {
        this._HijoIzq = _HijoIzq;
    }

    public ArbolAlbumes getAlbumes() {
        return _Albumes;
    }
}//fin de la clase
