package BackEnd;

import BackEnd.Estructuras.ListaCanciones;

/**
 * Clase Album Contiene la informacion de un album y los atributos necesario
 * para una estructura de tipo Arbol de albumes
 *
 * @author Maximiliano Casale
 */
public class Album {

    private final String _Nombre;
    private final ListaCanciones _Canciones;
    private Album _HijoDer;
    private Album _HijoIzq;

    /**
     * Constructor del album Le asigna el nombre del album y una cancion
     * asignada
     *
     * @param _Nombre nombre del album
     * @param cancion primera cancion del album
     */
    public Album(String _Nombre, Cancion cancion) {
        this._Nombre = _Nombre;
        _Canciones = new ListaCanciones();
        _Canciones.InsertarFinal(cancion);
    }
  /**
     * Enlistar Album
     *
     * vacia el album seleccionado en el metodo ConcatenarAlbumes en la lista
     * indicada
     *
     * @param aux cabeza de la lista de canciones del album
     * @param listaCanciones lista donde se vacia el album
     */
    public void EnlistarAlbum(Cancion aux, ListaCanciones listaCanciones) {
        while (aux != null) {
            Cancion nueva;
            if (aux instanceof Video) {
                nueva = new Video(aux.getArtista(), aux.getAlbum(), aux.getTitulo(), aux.getDuracion(), aux.getTrack(), ((Video) aux).getFormato());
            } else if (aux instanceof Pista) {
                nueva = new Pista(aux.getArtista(), aux.getAlbum(), aux.getTitulo(), aux.getDuracion(), aux.getTrack(), ((Pista) aux).getNombreArchivoLetra());
            } else {
                nueva = new Cancion(aux.getArtista(), aux.getAlbum(), aux.getTitulo(), aux.getDuracion(), aux.getTrack());
            }
            listaCanciones.InsertarFinal(nueva);
            aux = aux.getProximo();
        }
    }
    // Getters y Setters de los atributos
    public String getNombre() {
        return _Nombre;
    }

    public Album getHijoDer() {
        return _HijoDer;
    }

    public void setHijoDer(Album _HijoDer) {
        this._HijoDer = _HijoDer;
    }

    public Album getHijoIzq() {
        return _HijoIzq;
    }

    public void setHijoIzq(Album _HijoIzq) {
        this._HijoIzq = _HijoIzq;
    }

    public ListaCanciones getCanciones() {
        return _Canciones;
    }
}// fin de clase
