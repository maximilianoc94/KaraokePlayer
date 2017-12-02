package BackEnd.Estructuras;

import BackEnd.Cancion;
import BackEnd.Pista;
import BackEnd.Video;

/**
 * ListaReproduccion Estructura de DAtos de tipo Lista doble circular donde se
 * asignan las canciones a Reproducir La cancion reproducida siempre sera la
 * cabeza de la lista
 *
 * @author Maximiliano Casale
 */
public class ListaReproduccion {

    private Cancion _Cabeza;
    private Cancion _Cola;

    /**
     * Constructor. inicializa la lista en null
     */
    public ListaReproduccion() {
        _Cabeza = null;
        _Cola = null;
    }

    /**
     * Sacarcancion. Saca la cancion que se esta reproduciendo, eliminando la
     * cabeza de la lista
     */
    public void SacarCancion() {
        if (_Cabeza != _Cola) {
            Cancion aux = _Cabeza;
            _Cabeza = aux.getProximo();
            _Cola.setProximo(_Cabeza);
            _Cabeza.setAnterior(_Cola);
            aux.setAnterior(null);
            aux.setProximo(null);
        }
    }

    /**
     * Asigna como cabeza a la cancion anterior a la cabeza o en su defecto la
     * cola de la lista
     *
     * @param Repetir Parametro para validar que se quiere repetir la cancion en
     * este caso, si es true no se cambia la cancion
     */
    public void Anterior(boolean Repetir) {
        if (!Repetir) {
            Cancion aux = _Cabeza.getAnterior();
            _Cabeza = aux;
            _Cola = _Cabeza.getAnterior();
        }
    }

    /**
     * Asigna como cabeza a la cancion siguiente a la cabeza
     *
     * @param Repetir Parametro para validar que se quiere repetir la cancion en
     * este caso, si es true no se cambia la cancion
     */
    public void Siguiente(boolean Repetir) {
        if (!Repetir) {
            if (_Cola.getProximo() != null && _Cabeza.getProximo() != null) {
                Cancion aux = _Cabeza.getProximo();
                _Cabeza = aux;
                _Cola = aux.getAnterior();
            }
        }
    }

    /**
     * Vaciar. deja la lista circular vacia
     */
    public void Vaciar() {
        _Cabeza = null;
        _Cola = null;
    }

    /**
     * Llenar Reproduccion. Llena la lista de reproduccion a partir de una lista
     * pasada como parametro esta lista incluye solo las canciones a ser
     * reproducidas
     *
     * @param lista donde estan las canciones a reproducir
     */
    public void LlenarReproduccion(ListaCanciones lista) {
        Cancion aux = lista.getCabeza();
        while (aux != null) {
            Cancion nueva;
            if (aux instanceof Video) {
                nueva = new Video(aux.getArtista(), aux.getAlbum(), aux.getTitulo(), aux.getDuracion(), aux.getTrack(), ((Video) aux).getFormato());
            } else if (aux instanceof Pista) {
                nueva = new Pista(aux.getArtista(), aux.getAlbum(), aux.getTitulo(), aux.getDuracion(), aux.getTrack(), ((Pista) aux).getNombreArchivoLetra());
            } else {
                nueva = new Cancion(aux.getArtista(), aux.getAlbum(), aux.getTitulo(), aux.getDuracion(), aux.getTrack());
            }
            InsertaFinal(nueva);
            aux = aux.getProximo();
        }
        _Cabeza.setAnterior(_Cola);
        _Cola.setProximo(_Cabeza);
    }

    /**
     * Inserta una cancion al final de la lista
     *
     * @param nuevo cancion a ser ingresada
     */
    public void InsertaFinal(Cancion nuevo) {
        if (_Cabeza == null) {
            _Cabeza = nuevo;
            _Cola = nuevo;
        } else {
            _Cola.setProximo(nuevo);
            nuevo.setAnterior(_Cola);
            _Cola = nuevo;
            _Cola.setProximo(_Cabeza);
            _Cabeza.setAnterior(_Cola);
        }
    }

    /**
     * RotarCabeza. Cambia la cabeza de posicion, girando en sentido horario o
     * en su defecto al nodo de la derecha(el siguiente) cuantas veces sea
     * indicado
     *
     * @param pos cantidad de veces a ser rotada la cabeza
     */
    public void RotarCabezaA(int pos) {
        if (pos != 0) {
            Cancion aux = _Cabeza;
            for (int i = 1; i < pos; i++) {
                aux = aux.getProximo();
            }
            _Cola = aux;
            _Cabeza = _Cola.getProximo();
        }
    }
//getters y setters 

    public Cancion getCabeza() {
        return _Cabeza;
    }

    public void setCabeza(Cancion _Cabeza) {
        this._Cabeza = _Cabeza;
    }

    public Cancion getCola() {
        return _Cola;
    }

    public void setCola(Cancion _Cola) {
        this._Cola = _Cola;
    }
//fin de getters y setters
}//fin de la clase
