package BackEnd.Estructuras;

import BackEnd.Album;
import BackEnd.Artista;
import BackEnd.Cancion;
import BackEnd.Pista;
import BackEnd.Video;

/**
 * Clase arbol de artistas Estructura de datos de tipo arbol donde se almacenan
 * los artistas
 *
 * @author Maximiliano Casale
 */
public class ArbolArtistas {

    private Artista _Raiz;

    /**
     * constructor de arbol inicializa la raiz en null
     */
    public ArbolArtistas() {
        _Raiz = null;
    }

        /**
     * LLenar Arbol de Artistas pasa las canciones de la biblioteca al arbol
     * @param biblioteca lista de canciones
     */
    public void Llenar(ListaCanciones biblioteca) {
        Cancion aux = biblioteca.getCabeza();
        while (aux != null) {
            insertarCancion(aux);
            aux = aux.getProximo();
        }
    }

    /**
     * Insertar Cancion
     *
     * metodo para ingresar una cancion en el arbol de artistas asignando un
     * artista y un album en caso de que ya exista y creando uno nuevo en caso
     * de que no
     *
     * @param aux Cancion a insertar
     */
    public void insertarCancion(Cancion aux) {
        Cancion nueva;
        //Creamos la nueva nodo de la cancion en su respectivo tipo
        if (aux instanceof Video) {
            nueva = new Video(aux.getArtista(), aux.getAlbum(), aux.getTitulo(), aux.getDuracion(), aux.getTrack(), ((Video) aux).getFormato());
        } else if (aux instanceof Pista) {
            nueva = new Pista(aux.getArtista(), aux.getAlbum(), aux.getTitulo(), aux.getDuracion(), aux.getTrack(), ((Pista) aux).getNombreArchivoLetra());
        } else {
            nueva = new Cancion(aux.getArtista(), aux.getAlbum(), aux.getTitulo(), aux.getDuracion(), aux.getTrack());
        }// creamos un nuevo artista, y preguntamos si existe
        Artista nuevoArtista = new Artista(aux.getArtista(), aux.getAlbum(), nueva);
        if (yaExiste(_Raiz, aux.getArtista())) {
            // si ya existe procedemos a comparar los albumes
            Artista actualArtista = buscar(_Raiz, aux.getArtista());
            Album nuevoAlbum = new Album(aux.getAlbum(), nueva);
            // preguntamos si el album ya existe
            if (actualArtista.getAlbumes().yaExiste(actualArtista.getAlbumes().getRaiz(), aux.getAlbum())) {
                Album actualAlbum = actualArtista.getAlbumes().buscar(actualArtista.getAlbumes().getRaiz(), aux.getAlbum());
                //si ya existe, comparamos la lista de canciones dentro del album
                if (!actualAlbum.getCanciones().yaExiste(aux.getTitulo())) {
                    // si la cancion no existe la introducimos en la lista
                    actualAlbum.getCanciones().InsertarFinal(nueva);
                } //sino se desecha
            } else { // si no existe el album, creamos uno nuevo para ese artista
                actualArtista.getAlbumes().Insertar(actualArtista.getAlbumes().getRaiz(), nuevoAlbum);

            }
        } else { //si el artista no existe lo ingresamos nuevo
            Insertar(_Raiz, nuevoArtista);
        }
    }

    
    /**
     * Esta vacio revisa si el arbol esta vacio
     *
     * @return boolean si esta vacio o no
     */
    public boolean estaVacio() {
        return (_Raiz == null);
    }

    /**
     * Insertar Ingresa un artista nuevo al arbol
     *
     * @param raiz principal del arbol
     * @param nuevo Artista a ser ingresado en el arbol
     */
    public void Insertar(Artista raiz, Artista nuevo) {
        if (raiz == null) {
            _Raiz = nuevo;
        } else {
            int comparacion = raiz.getNombre().compareTo(nuevo.getNombre());
            if (comparacion > 0) {
                if (raiz.getHijoIzq() == null) {
                    raiz.setHijoIzq(nuevo);
                } else {
                    Insertar(raiz.getHijoIzq(), nuevo);
                }
            } else if ((comparacion < 0)) {
                if (raiz.getHijoDer() == null) {
                    raiz.setHijoDer(nuevo);
                } else {
                    Insertar(raiz.getHijoDer(), nuevo);
                }
            }
        }
    }

    /**
     * Ya Existe revisa si el artista indicado ya esta en el arbol
     *
     * @param raiz principal del arbol
     * @param artista a buscar dentro del arbol
     * @return boolean si esta o no
     */
    public boolean yaExiste(Artista raiz, String artista) {
        if (raiz != null) {
            if (raiz.getNombre().equals(artista)) {
                return true;
            } else {
                return (yaExiste(raiz.getHijoIzq(), artista) || yaExiste(raiz.getHijoDer(), artista));
            }

        } else {
            return false;
        }
    }

    /**
     * Buscar Busca un artista dentro del arbol
     *
     * @param raiz principal del arbol
     * @param nombre del artista a buscar
     * @return el artista buscado
     */
    public Artista buscar(Artista raiz, String nombre) {
        if (raiz != null) {
            int comparacion = nombre.compareTo(raiz.getNombre());
            if (comparacion == 0) {
                return raiz;
            } else if (comparacion < 0) {
                return buscar(raiz.getHijoIzq(), nombre);
            } else {
                return buscar(raiz.getHijoDer(), nombre);
            }

        } else {
            return null;
        }
    }
    // getters y setters de los atributos

    public Artista getRaiz() {
        return _Raiz;
    }

    public void setRaiz(Artista _Raiz) {
        this._Raiz = _Raiz;
    }
}// fin de la clase
