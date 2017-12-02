package BackEnd.Estructuras;

import BackEnd.Album;

/**
 * Arbol Albumes Estructura de datos tipo Arbol que contiene el conjunto de
 * albumes de un artista
 *
 * @author Maximiliano Casale
 */
public class ArbolAlbumes {

    private Album _Raiz;

    /**
     * Constructor, inicializa el arbol vacio
     */
    public ArbolAlbumes() {
        this._Raiz = null;
    }

    /**
     * Esta Vacio Revisa si el arbol esta vacio o no
     *
     * @return boolean si esta vacio o no
     */
    public boolean estaVacio() {
        return (_Raiz == null);
    }
    
        /**
     * ConcatenarAlbumes
     *
     * Anade ordenadamente cada album del artista a la lista indicada
     *
     * @param raiz raiz del arbol subarbol de albumes a revisar
     * @param aux lista donde se guardaran las canciones
     */
    public void ConcatenarAlbumes(Album raiz, ListaCanciones aux) {
        if (raiz != null) {
            ConcatenarAlbumes(raiz.getHijoIzq(), aux);
            raiz.EnlistarAlbum(raiz.getCanciones().getCabeza(), aux);
            ConcatenarAlbumes(raiz.getHijoDer(), aux);
        }
    }

    /**
     * Insertar Inserta un album nuevo en el arbol
     *
     * @param raiz recibe la raiz principal del arbol
     * @param nuevo recibe el album nuevo a ser introducido
     */
    public void Insertar(Album raiz, Album nuevo) {
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
     * yaExiste Revisa si el album indicado ya existe dentro del arbol
     *
     * @param raiz principal del arbol
     * @param album nombre del album a buscar
     * @return boolean si encontro o no
     */
    public boolean yaExiste(Album raiz, String album) {
        if (raiz != null) {
            if (raiz.getNombre().equals(album)) {
                return true;
            } else {
                return (yaExiste(raiz.getHijoIzq(), album) || yaExiste(raiz.getHijoDer(), album));
            }

        } else {
            return false;
        }
    }

    /**
     * Buscar Busca dentro del arbol el album con el nombre indicado
     *
     * @param raiz nodo principal del arbol
     * @param nombre del album a buscar
     * @return boolean si lo encontro o no
     */
    public Album buscar(Album raiz, String nombre) {
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
//getters y setters de los atributos 

    public Album getRaiz() {
        return _Raiz;
    }

    public void setRaiz(Album _Raiz) {
        this._Raiz = _Raiz;
    }

}//fin de clase
