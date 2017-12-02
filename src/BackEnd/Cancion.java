package BackEnd;

import java.io.Serializable;

/**
 * Clase Cancion Define atributos y metodos de las canciones es padre de la
 * clase pista y video, ya que estas son tambien a su vez canciones. Contiene
 * los atributos para la estructura Arbol de Canciones Lista Circular de
 * Canciones Doblemente Enlazada y Lista Simple de Canciones
 *
 * @author Maximiliano Casale
 */
public class Cancion implements Serializable {

    protected String _Artista;
    protected String _Album;
    protected String _Titulo;
    protected int _Track;
    protected int _Duracion;
    protected Cancion _Proximo;
    protected Cancion _Anterior;
    protected Cancion _HijoIzq;
    protected Cancion _HijoDer;

    /**
     * Constructor Cancion Contiene el nombre de su artista El nombre del album
     * al que pertenece El titulo de la cancion su duracion y su posicion en el
     * album
     *
     * @param artista nombre del artista
     * @param album nombre del album
     * @param titulo titulo de la cancion
     * @param duracion
     * @param track posicion en el album
     */
    public Cancion(String artista, String album, String titulo, int duracion, int track) {
        _Artista = artista;
        _Album = album;
        _Titulo = titulo;
        _Duracion = duracion;
        _Track = track;
        _Proximo = null;
        _Anterior = null;
        _HijoIzq = null;
        _HijoDer = null;
    }

    //Getters y Setters de sus atributos
    public String getTitulo() {
        return _Titulo;
    }

    public int getDuracion() {
        return _Duracion;
    }

    public Cancion getProximo() {
        return _Proximo;
    }

    public void setProximo(Cancion _Proximo) {
        this._Proximo = _Proximo;
    }

    public Cancion getAnterior() {
        return _Anterior;
    }

    public void setAnterior(Cancion _Anterior) {
        this._Anterior = _Anterior;
    }

    public String getArtista() {
        return _Artista;
    }

    public String getAlbum() {
        return _Album;
    }

    public int getTrack() {
        return _Track;
    }

    public Cancion getHijoIzq() {
        return _HijoIzq;
    }

    public void setHijoIzq(Cancion _HijoIzq) {
        this._HijoIzq = _HijoIzq;
    }

    public Cancion getHijoDer() {
        return _HijoDer;
    }

    public void setHijoDer(Cancion _HijoDer) {
        this._HijoDer = _HijoDer;
    }
} //fin de clase
