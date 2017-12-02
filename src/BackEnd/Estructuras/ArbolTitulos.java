package BackEnd.Estructuras;

import BackEnd.Cancion;
import BackEnd.Pista;
import BackEnd.Video;

/**
 * Arbol de Canciones Estructura de dato de tipo arbol donde estan contenidas
 * todas las canciones del App
 *
 * @author Maximiliano Casale
 */
public class ArbolTitulos {

    private Cancion _Raiz;

    /**
     * Constructor de arbol inicializa la raiz en null
     */
    public ArbolTitulos() {
        this._Raiz = null;
    }

        /**
     * Llenar arbol de titulos Llena el arbol de canciones con las canciones en
     * la biblioteca
     * @param biblioteca lista de canciones
     */
    public void Llenar(ListaCanciones biblioteca) {
        Cancion aux = biblioteca.getCabeza();
        while (aux != null) {
            Cancion nueva;
            if (aux instanceof Video) {
                nueva = new Video(aux.getArtista(), aux.getAlbum(), aux.getTitulo(), aux.getDuracion(), aux.getTrack(), ((Video) aux).getFormato());
            } else if (aux instanceof Pista) {
                nueva = new Pista(aux.getArtista(), aux.getAlbum(), aux.getTitulo(), aux.getDuracion(), aux.getTrack(), ((Pista) aux).getNombreArchivoLetra());
            } else {
                nueva = new Cancion(aux.getArtista(), aux.getAlbum(), aux.getTitulo(), aux.getDuracion(), aux.getTrack());
            }
            Insertar(_Raiz, nueva);
            aux = aux.getProximo();
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
     * Insertar Ingresa una Cancion nuevo al arbol
     *
     * @param raiz principal del arbol
     * @param nuevo Cancion a ser ingresado en el arbol
     */
    public void Insertar(Cancion raiz, Cancion nuevo) {
        if (raiz == null) {
            _Raiz = nuevo;
        } else {
            int comparacion = raiz.getTitulo().compareTo(nuevo.getTitulo());
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
     * Ya Existe revisa si la Cancion indicada ya esta en el arbol
     *
     * @param raiz principal del arbol
     * @param cancion a buscar dentro del arbol
     * @return boolean si esta o no
     */
    public boolean yaExiste(Cancion raiz, String cancion) {
        if (raiz != null) {
            if (raiz.getTitulo().equals(cancion)) {
                return true;
            } else {
                return (yaExiste(raiz.getHijoIzq(), cancion) || yaExiste(raiz.getHijoDer(), cancion));
            }

        } else {
            return false;
        }
    }

    /**
     * Buscar Busca una cancion dentro del arbol
     *
     * @param raiz principal del arbol
     * @param nombre de la cancion a buscar
     * @return la cancion buscado
     */
    public Cancion buscar(Cancion raiz, String nombre) {
        if (raiz != null) {
            int comparacion = nombre.compareTo(raiz.getTitulo());
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

    /**
     * Retorna la cantidad de nodos en el arbol
     *
     * @param raiz principal del arbol
     * @return int cantidad de nodos
     */
    public int size(Cancion raiz) {
        if (raiz == null) {
            return 0;
        } else {
            return 1 + size(raiz.getHijoIzq()) + size(raiz.getHijoDer());
        }
    }

    /**
     * Buscar Eliminar, busca el papa del nodo a ser eliminado
     *
     * @param raiz raiz principal del arbol
     * @param titulo titulo del nodo a ser eliminado
     * @return el padre del nodo a ser eliminado
     */
    public Cancion buscareliminar(Cancion raiz, String titulo) {
        if (raiz != null) {
            if (raiz.getTitulo().compareTo(titulo) == 0) {
                return raiz;
            } else if ((raiz.getHijoIzq() != null && raiz.getHijoIzq().getTitulo().compareTo(titulo) == 0) || (raiz.getHijoDer() != null && raiz.getHijoDer().getTitulo().compareTo(titulo) == 0)) {
                return raiz;
            } else if (titulo.compareTo(raiz.getTitulo()) < 0) {
                return buscareliminar(raiz.getHijoIzq(), titulo);
            } else {
                return buscareliminar(raiz.getHijoDer(), titulo);
            }
        }
        return null;
    }

    /**
     * Eliminar un nodo Elimina el nodo indicado
     *
     * @param papa nodo superior al nodo a ser eliminado
     * @param titulo nombre de la cancion a ser eliminada
     * @return retorna el nodo eliminado
     */
    public Cancion eliminarunnodo(Cancion papa, String titulo) {
        if (_Raiz != null && _Raiz.getTitulo().compareTo(titulo) == 0) {
            return suprimircabeza();
        } else if (papa.getHijoIzq() != null && papa.getHijoIzq().getTitulo().compareTo(titulo) == 0) {
            return SuprimeIzq(papa);
        } else if (papa.getHijoDer() != null && papa.getHijoDer().getTitulo().compareTo(titulo) == 0) {
            return SuprimeDer(papa);
        }
        return null;
    }

    /**
     * Suprimircabeza Suprime la raiz indicada
     *
     * @return retorna el nodo eliminado
     */
    public Cancion suprimircabeza() {

        Cancion aux = _Raiz;
        _Raiz = null;
        if (aux.getHijoIzq() != null && aux.getHijoDer() != null) {

            _Raiz = aux.getHijoDer();
            Cancion aux2 = aux.getHijoDer();
            while (aux2.getHijoIzq() != null) {
                aux2 = aux2.getHijoIzq();
            }
            aux2.setHijoIzq(aux.getHijoIzq());
            return aux;
        } else if (aux.getHijoIzq() != null) {
            _Raiz = aux.getHijoIzq();
            return aux;
        } else if (aux.getHijoDer() != null) {
            _Raiz = aux.getHijoDer();
            return aux;
        }

        return null;
    }

    /**
     * Suprimeizquierda Elimina el hijo izquierdo de la raiz indicada
     *
     * @param papa papa del nodo a ser eliminado
     * @return retorna el nodo eliminado
     */
    public Cancion SuprimeIzq(Cancion papa) {
        if (papa.getHijoIzq() != null) {
            Cancion aux = papa.getHijoIzq();
            papa.setHijoIzq(null);

            if (aux.getHijoIzq() != null && aux.getHijoDer() != null) {
                Cancion aux2 = aux.getHijoIzq();
                papa.setHijoIzq(aux.getHijoIzq());
                while (aux2.getHijoDer() != null) {
                    aux2 = aux2.getHijoDer();
                }
                aux2.setHijoDer(aux.getHijoDer());
            } else if (aux.getHijoIzq() != null) {
                papa.setHijoIzq(aux.getHijoIzq());
            } else if (aux.getHijoDer() != null) {
                papa.setHijoDer(aux.getHijoDer());
            }
            return aux;
        }
        return null;
    }

    /**
     * Suprimeder Elimina el hijo derecho de la raiz indicada
     *
     * @param papa papa del nodo a ser eliminado
     * @return retorna el nodo eliminado
     */
    public Cancion SuprimeDer(Cancion papa) {
        if (papa.getHijoDer() != null) {
            Cancion aux = papa.getHijoDer();
            papa.setHijoDer(null);
            if (aux.getHijoIzq() != null && aux.getHijoDer() != null) {
                Cancion aux2 = aux.getHijoDer();
                papa.setHijoDer(aux.getHijoDer());
                while (aux2.getHijoIzq() != null) {
                    aux2 = aux2.getHijoIzq();
                }
                aux2.setHijoIzq(aux.getHijoIzq());
            } else if (aux.getHijoIzq() != null) {
                papa.setHijoIzq(aux.getHijoIzq());
            } else if (aux.getHijoDer() != null) {
                papa.setHijoDer(aux.getHijoDer());
            }
            return aux;
        }
        return null;
    }

    //getters y setters
    public Cancion getRaiz() {
        return _Raiz;
    }

    public void setRaiz(Cancion _Raiz) {
        this._Raiz = _Raiz;
    }
    //fin de getters y setters
}//fin de clase
