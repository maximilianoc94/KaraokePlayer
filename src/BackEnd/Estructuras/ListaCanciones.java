package BackEnd.Estructuras;

import BackEnd.*;
import java.util.Random;

/**
 * Lista Canciones Estructura de Datos tipo Lista Simple que contiene Canciones
 *
 * @author Maximiliano Casale
 */
public class ListaCanciones {

    private Cancion _Cabeza;
    private Cancion _Cola;

    /**
     * Constructor, inicializa la lista en null
     */
    public ListaCanciones() {
        _Cabeza = null;
        _Cola = null;
    }

    /**
     * Inserta un nodo al final de la lista
     *
     * @param nueva cancion a ser insertada
     */
    public void InsertarFinal(Cancion nueva) {
        if (_Cabeza == null) {
            _Cabeza = nueva;
            _Cola = nueva;
        } else {
            Cancion temp = _Cola;
            temp.setProximo(nueva);
            _Cola = nueva;
            _Cola.setProximo(null);
        }
    }

    /**
     * Elimina el nodo en la posicion indicada
     *
     * @param pos posicion a eliminar
     * @return nodo eliminado
     */
    public Cancion EliminarPos(int pos) {
        if (_Cabeza != null) {
            Cancion temp;
            Cancion siguiente;
            Cancion auxi;
            int aux = 1;
            temp = _Cabeza;
            siguiente = _Cabeza.getProximo();

            if (_Cabeza.getProximo() == null) {
                auxi = _Cabeza;
                _Cabeza = null;
                return auxi;
            } else {

                if (pos == 1) {
                    auxi = EliminarPrimero();
                    return auxi;
                } else {
                    while (aux < pos - 1) {
                        temp = siguiente;
                        siguiente = siguiente.getProximo();
                        aux++;
                    }
                    auxi = temp.getProximo();
                    temp.setProximo(siguiente.getProximo());
                    auxi.setProximo(null);
                    return auxi;
                }
            }
        } else {
            return _Cabeza;
        }
    }

    /**
     * Eliminar Primero, Elimina el primer nodo de la lista
     *
     * @return el nodo eliminado
     */
    public Cancion EliminarPrimero() {
        if (_Cabeza != null) {
            Cancion temp = _Cabeza;
            _Cabeza = _Cabeza.getProximo();
            temp.setProximo(null);
            return temp;
        } else {
            return _Cabeza;
        }
    }

    /**
     * Ordenar por artistas ordena la lista de reproduccion por aritstas sacados
     * EnOrden del arbol de artistas
     *
     * @param raiz, raiz del arbol de artistas para sacar la informacion
     * @param lista recibe como parametro la lista que representara la nueva
     * lista mostrada
     */
    public void OrdenarPorArtista(Artista raiz, ListaCanciones lista) {
        if (raiz != null) {
            OrdenarPorArtista(raiz.getHijoIzq(), lista);
            raiz.getAlbumes().ConcatenarAlbumes(raiz.getAlbumes().getRaiz(), lista);
            OrdenarPorArtista(raiz.getHijoDer(), lista);
        }
    }

    /**
     * Insertar Ordenada por Artista inserta la cancion en orden alfabetico con
     * respecto al artista
     *
     * @param nueva Cancion a ser ingresada
     */
    public void InsertaOrdenadoPorArtista(Cancion nueva) {
        if (EstaVacia()) {
            InsertaPrimero(nueva);
        } else if ((nueva.getArtista().compareTo(_Cola.getArtista()) == 0 || nueva.getArtista().compareTo(_Cabeza.getArtista()) == 0)) {
            InsertaOrdenadoPorAlbum(nueva);
        } else if (nueva.getArtista().compareTo(_Cabeza.getArtista()) < 0) {
            InsertaPrimero(nueva);
        } else if (nueva.getArtista().compareTo(_Cabeza.getArtista()) > 0) {
            InsertarFinal(nueva);
        } else {
            if (_Cabeza.getProximo() != null) {
                Cancion aux = _Cabeza;
                while (nueva.getArtista().compareTo(aux.getProximo().getArtista()) > 0) {
                    aux = aux.getProximo();
                }
                nueva.setProximo(aux.getProximo());
                aux.setProximo(nueva);
            }
        }
    }

    /**
     * OrdenarPorAlbum Ordena la lista mostrada por orden alfabetico de acuerdo
     * al album
     *
     * @param raiz raiz del arbol de canciones
     * @param lista lista donde se guardan las canciones
     * @return la lista ordenada
     */
    public ListaCanciones OrdenarPorAlbum(Cancion raiz, ListaCanciones lista) {
        if (raiz != null) {
            OrdenarPorAlbum(raiz.getHijoIzq(), lista);
            Cancion nueva;
            if (raiz instanceof Video) {
                nueva = new Video(raiz.getArtista(), raiz.getAlbum(), raiz.getTitulo(), raiz.getDuracion(), raiz.getTrack(), ((Video) raiz).getFormato());
            } else if (raiz instanceof Pista) {
                nueva = new Pista(raiz.getArtista(), raiz.getAlbum(), raiz.getTitulo(), raiz.getDuracion(), raiz.getTrack(), ((Pista) raiz).getNombreArchivoLetra());
            } else {
                nueva = new Cancion(raiz.getArtista(), raiz.getAlbum(), raiz.getTitulo(), raiz.getDuracion(), raiz.getTrack());
            }
            lista.InsertarFinal(nueva);
            OrdenarPorAlbum(raiz.getHijoDer(), lista);
        }
        ListaCanciones ln = new ListaCanciones();
        Cancion Auxi = lista.getCabeza();
        while (Auxi != null) {
            Cancion nueva;
            if (Auxi instanceof Video) {
                nueva = new Video(Auxi.getArtista(), Auxi.getAlbum(), Auxi.getTitulo(), Auxi.getDuracion(), Auxi.getTrack(), ((Video) Auxi).getFormato());
            } else if (Auxi instanceof Pista) {
                nueva = new Pista(Auxi.getArtista(), Auxi.getAlbum(), Auxi.getTitulo(), Auxi.getDuracion(), Auxi.getTrack(), ((Pista) Auxi).getNombreArchivoLetra());
            } else {
                nueva = new Cancion(Auxi.getArtista(), Auxi.getAlbum(), Auxi.getTitulo(), Auxi.getDuracion(), Auxi.getTrack());
            }
            ln.InsertaOrdenadoPorAlbum(nueva);
            Auxi = Auxi.getProximo();
        }
        return ln;
    }

    /**
     * Inserta Ordenador por Album Revisa los atributos de la cancion y la
     * inserta en la posicion adecuada segun el orden
     *
     * @param nueva cancion a ser insertada
     */
    public void InsertaOrdenadoPorAlbum(Cancion nueva) {
        if (EstaVacia()) {
            InsertaPrimero(nueva);
        } else if (nueva.getAlbum().compareTo(_Cabeza.getAlbum()) < 0) {
            InsertaPrimero(nueva);
        } else if (nueva.getAlbum().compareTo(_Cola.getAlbum()) > 0) {
            InsertarFinal(nueva);
        } else if ((nueva.getAlbum().compareTo(_Cola.getAlbum()) == 0 || nueva.getAlbum().compareTo(_Cabeza.getAlbum()) == 0)) {
            InsertaOrdenadoPorTrack(nueva);
        } else {
            if (_Cabeza.getProximo() != null) {
                Cancion aux = _Cabeza;
                while (nueva.getAlbum().compareTo(aux.getProximo().getAlbum()) > 0) {
                    aux = aux.getProximo();
                }
                nueva.setProximo(aux.getProximo());
                aux.setProximo(nueva);
            }
        }
    }

    /**
     * Ordenar Por Titulo
     *
     * Ordena la lista mostrada por titulo vacia el arbol de canciones ENORDEN y
     * lo guarda en la lista
     *
     * @param lista donde se guardan las canciones en orden
     * @param raiz raiz del arbol de canciones
     */
    public void OrdenarPorTitulo(Cancion raiz, ListaCanciones lista) {
        if (raiz != null) {
            OrdenarPorTitulo(raiz.getHijoIzq(), lista);
            Cancion nueva;
            if (raiz instanceof Video) {
                nueva = new Video(raiz.getArtista(), raiz.getAlbum(), raiz.getTitulo(), raiz.getDuracion(), raiz.getTrack(), ((Video) raiz).getFormato());
            } else if (raiz instanceof Pista) {
                nueva = new Pista(raiz.getArtista(), raiz.getAlbum(), raiz.getTitulo(), raiz.getDuracion(), raiz.getTrack(), ((Pista) raiz).getNombreArchivoLetra());
            } else {
                nueva = new Cancion(raiz.getArtista(), raiz.getAlbum(), raiz.getTitulo(), raiz.getDuracion(), raiz.getTrack());
            }
            lista.InsertarFinal(nueva);
            OrdenarPorTitulo(raiz.getHijoDer(), lista);
        }
    }

    /**
     * OrdenarPorDuracion Ordena la lista mostrada por duracion de la cancion
     *
     * @param raiz raiz del arbol de canciones
     * @param lista lista donde se ordenaran las canciones
     * @return
     */
    public ListaCanciones OrdenarPorDuracion(Cancion raiz, ListaCanciones lista) {

        if (raiz != null) {
            OrdenarPorDuracion(raiz.getHijoIzq(), lista);
            Cancion nueva;
            if (raiz instanceof Video) {
                nueva = new Video(raiz.getArtista(), raiz.getAlbum(), raiz.getTitulo(), raiz.getDuracion(), raiz.getTrack(), ((Video) raiz).getFormato());
            } else if (raiz instanceof Pista) {
                nueva = new Pista(raiz.getArtista(), raiz.getAlbum(), raiz.getTitulo(), raiz.getDuracion(), raiz.getTrack(), ((Pista) raiz).getNombreArchivoLetra());
            } else {
                nueva = new Cancion(raiz.getArtista(), raiz.getAlbum(), raiz.getTitulo(), raiz.getDuracion(), raiz.getTrack());
            }
            lista.InsertarFinal(nueva);
            OrdenarPorDuracion(raiz.getHijoDer(), lista);
        }

        ListaCanciones ln = new ListaCanciones();
        Cancion Auxi = lista.getCabeza();
        while (Auxi != null) {
            Cancion nueva;
            if (Auxi instanceof Video) {
                nueva = new Video(Auxi.getArtista(), Auxi.getAlbum(), Auxi.getTitulo(), Auxi.getDuracion(), Auxi.getTrack(), ((Video) Auxi).getFormato());
            } else if (Auxi instanceof Pista) {
                nueva = new Pista(Auxi.getArtista(), Auxi.getAlbum(), Auxi.getTitulo(), Auxi.getDuracion(), Auxi.getTrack(), ((Pista) Auxi).getNombreArchivoLetra());
            } else {
                nueva = new Cancion(Auxi.getArtista(), Auxi.getAlbum(), Auxi.getTitulo(), Auxi.getDuracion(), Auxi.getTrack());
            }
            ln.InsertaOrdenadoPorDuracion(nueva);
            Auxi = Auxi.getProximo();
        }
        return ln;
    }

    /**
     * Inserta ordenado por orden de duracion inserta una cancion a la lista
     * revisando su duracion y colocandola en la respectiva posicion con
     * respecto al parametro
     *
     * @param nueva cancion a ser ingresada
     */
    public void InsertaOrdenadoPorDuracion(Cancion nueva) {
        if (EstaVacia()) {
            InsertaPrimero(nueva);
        } else if (nueva.getDuracion() <= _Cabeza.getDuracion()) {
            InsertaPrimero(nueva);
        } else if (nueva.getDuracion() >= _Cola.getDuracion()) {
            InsertarFinal(nueva);
        } else {
            Cancion aux = _Cabeza;
            while (nueva.getDuracion() > aux.getProximo().getDuracion()) {
                aux = aux.getProximo();
            }
            nueva.setProximo(aux.getProximo());
            aux.setProximo(nueva);
        }
    }

    /**
     * Ordenar por Track Ordena la lista mostrada en orden de track
     *
     * @param raiz del arbol de canciones
     * @param lista donde se guardan las canciones del arbol
     * @return la lista ordenada aleatoriamente
     */
    public ListaCanciones OrdenarPorTrack(Cancion raiz, ListaCanciones lista) {

        if (raiz != null) {
            OrdenarPorTrack(raiz.getHijoIzq(), lista);
            Cancion nueva;
            if (raiz instanceof Video) {
                nueva = new Video(raiz.getArtista(), raiz.getAlbum(), raiz.getTitulo(), raiz.getDuracion(), raiz.getTrack(), ((Video) raiz).getFormato());
            } else if (raiz instanceof Pista) {
                nueva = new Pista(raiz.getArtista(), raiz.getAlbum(), raiz.getTitulo(), raiz.getDuracion(), raiz.getTrack(), ((Pista) raiz).getNombreArchivoLetra());
            } else {
                nueva = new Cancion(raiz.getArtista(), raiz.getAlbum(), raiz.getTitulo(), raiz.getDuracion(), raiz.getTrack());
            }
            lista.InsertarFinal(nueva);
            OrdenarPorTrack(raiz.getHijoDer(), lista);
        }
        ListaCanciones ln = new ListaCanciones();
        Cancion Auxi = lista.getCabeza();
        while (Auxi != null) {
            Cancion nueva;
            if (Auxi instanceof Video) {
                nueva = new Video(Auxi.getArtista(), Auxi.getAlbum(), Auxi.getTitulo(), Auxi.getDuracion(), Auxi.getTrack(), ((Video) Auxi).getFormato());
            } else if (Auxi instanceof Pista) {
                nueva = new Pista(Auxi.getArtista(), Auxi.getAlbum(), Auxi.getTitulo(), Auxi.getDuracion(), Auxi.getTrack(), ((Pista) Auxi).getNombreArchivoLetra());
            } else {
                nueva = new Cancion(Auxi.getArtista(), Auxi.getAlbum(), Auxi.getTitulo(), Auxi.getDuracion(), Auxi.getTrack());
            }
            ln.InsertaOrdenadoPorTrack(nueva);
            Auxi = Auxi.getProximo();
        }
        return ln;
    }

    /**
     * Inserta ordenado por track inserta una cancion a la lista revisando su
     * track y colocandola en la respectiva posicion con respecto al parametro
     *
     * @param nueva cancion a ser ingresada
     */
    public void InsertaOrdenadoPorTrack(Cancion nueva) {
        if (EstaVacia()) {
            InsertaPrimero(nueva);
        } else if (nueva.getTrack() <= _Cabeza.getTrack()) {
            InsertaPrimero(nueva);
        } else if (nueva.getTrack() >= _Cola.getTrack()) {
            InsertarFinal(nueva);
        } else {
            Cancion aux = _Cabeza;
            while (nueva.getTrack() > aux.getProximo().getTrack()) {
                aux = aux.getProximo();
            }
            nueva.setProximo(aux.getProximo());
            aux.setProximo(nueva);
        }
    }

    /**
     * Ordenar Aleatorio Ordena la lista mostrada aleatoriamente
     *
     * @param raiz Raiz del arbol de Canciones
     * @param tamano maximas posiciones a randomizar
     * @param lista donde se guardan las canciones del arbol
     * @return la lista ordenada aleatoriamente
     */
    public ListaCanciones OrdenarAleatorio(Cancion raiz, ListaCanciones lista, int tamano) {

        if (raiz != null) {
            OrdenarAleatorio(raiz.getHijoIzq(), lista, tamano);
            Cancion nueva;
            if (raiz instanceof Video) {
                nueva = new Video(raiz.getArtista(), raiz.getAlbum(), raiz.getTitulo(), raiz.getDuracion(), raiz.getTrack(), ((Video) raiz).getFormato());
            } else if (raiz instanceof Pista) {
                nueva = new Pista(raiz.getArtista(), raiz.getAlbum(), raiz.getTitulo(), raiz.getDuracion(), raiz.getTrack(), ((Pista) raiz).getNombreArchivoLetra());
            } else {
                nueva = new Cancion(raiz.getArtista(), raiz.getAlbum(), raiz.getTitulo(), raiz.getDuracion(), raiz.getTrack());
            }
            lista.InsertarFinal(nueva);
            OrdenarAleatorio(raiz.getHijoDer(), lista, tamano);
        }

        ListaCanciones ln = new ListaCanciones();
        Cancion Auxi = lista.getCabeza();
        while (Auxi != null) {
            Cancion nueva;
            if (Auxi instanceof Video) {
                nueva = new Video(Auxi.getArtista(), Auxi.getAlbum(), Auxi.getTitulo(), Auxi.getDuracion(), Auxi.getTrack(), ((Video) Auxi).getFormato());
            } else if (Auxi instanceof Pista) {
                nueva = new Pista(Auxi.getArtista(), Auxi.getAlbum(), Auxi.getTitulo(), Auxi.getDuracion(), Auxi.getTrack(), ((Pista) Auxi).getNombreArchivoLetra());
            } else {
                nueva = new Cancion(Auxi.getArtista(), Auxi.getAlbum(), Auxi.getTitulo(), Auxi.getDuracion(), Auxi.getTrack());
            }
            int random = new Random().nextInt(tamano);
            ln.Insertapos(nueva, random);
            Auxi = Auxi.getProximo();
        }
        return ln;
    }

    /**
     * metodo InsertaPos
     *
     * Insertar un nodo despues de una posicion determinada
     *
     * @param nuevo
     * @param pos
     */
    public void Insertapos(Cancion nuevo, int pos) {
        if (_Cabeza == null) {
            nuevo.setProximo(_Cabeza);
            _Cabeza = nuevo;
            _Cola = nuevo;
        } else {
            if (pos == 1) {
                nuevo.setProximo(_Cabeza);
                _Cabeza = nuevo;
            } else {
                if (_Cabeza.getProximo() == null) {
                    InsertarFinal(nuevo);
                } else {

                    Cancion temp = _Cabeza;
                    int cont = 1;
                    while ((cont < (pos - 1)) && (temp.getProximo() != null)) {
                        cont++;
                        temp = temp.getProximo();
                    }
                    if (temp.getProximo() != null) {
                        nuevo.setProximo(temp.getProximo());
                        temp.setProximo(nuevo);
                    } else {
                        temp.setProximo(nuevo);
                    }
                }
            }
        }
    }

    /**
     * Filtrar Lista muestra solo los tipos de canciones de la opcion
     * seleccionada en la lista mostrada por interface
     *
     * @param Pista tipo de filtro
     * @param Video tipo de filtro
     * @param Cancion tipo de filtro
     * @param listaActual lista a filtrar
     * @return lista filtrada
     */
    public ListaCanciones FiltrarLista(boolean Pista, boolean Video, boolean Cancion, ListaCanciones listaActual) {
        //creamos una nueva lista
        ListaCanciones ln = new ListaCanciones();
        // creamos un auxiliar con la primera cancion de la lista actual
        Cancion aux = listaActual.getCabeza();
        // preguntamos si no es null
        if (aux != null) {
            // preguntamos si hay algun tipo de filtro seleccionado
            if (Pista || Video || Cancion) {
                // si los tres filtros son true, significa que no sacaremos a nadie
                if (Pista && Video && Cancion) {
                    // retornamos la lista actual tal como esta
                    return listaActual;
                    //sino, preguntamos que filtro es no es true para saber a quien vamos a sacar de la lista
                } else if (Pista && Video) {
                    //si pista y video son true y cancion no
                    while (aux != null) {
                        //mientras el aux no sea null
                        //pregunto si el aux es de tipo pista o de tipo video
                        if ((aux instanceof Pista) || (aux instanceof Video)) {
                            //creo una cancion nueva
                            Cancion nueva;
                            if (aux instanceof Pista) {
                                //si el aux es tipo pista, creamos una pista nueva con los datos del aux
                                nueva = new Pista(aux.getArtista(), aux.getAlbum(), aux.getTitulo(), aux.getDuracion(), aux.getTrack(), ((Pista) aux).getNombreArchivoLetra());
                            } else {
                                // sino, es un video, y creamos un video nuevo con los datos del aux
                                nueva = new Video(aux.getArtista(), aux.getAlbum(), aux.getTitulo(), aux.getDuracion(), aux.getTrack(), ((Video) aux).getFormato());
                            }
                            //insertamos la cancion nueva en la lista a retornar
                            ln.InsertarFinal(nueva);
                        }
                        //pasamos a la siguiente cancion
                        aux = aux.getProximo();
                        //una vez que termine el while salimos y retornamos la lista sin ninguna cancion que no sea o pista o video
                    }// si no queremos videos
                } else if (Pista && Cancion) {
                    while (aux != null) {
                        if (!(aux instanceof Video)) {
                            Cancion nueva;
                            if (aux instanceof Pista) {
                                nueva = new Pista(aux.getArtista(), aux.getAlbum(), aux.getTitulo(), aux.getDuracion(), aux.getTrack(), ((Pista) aux).getNombreArchivoLetra());
                            } else {
                                nueva = new Cancion(aux.getArtista(), aux.getAlbum(), aux.getTitulo(), aux.getDuracion(), aux.getTrack());
                            }
                            ln.InsertarFinal(nueva);
                        }
                        aux = aux.getProximo();
                    }
                } else if (Video && Cancion) { //si no queremos pista
                    while (aux != null) {
                        if (!(aux instanceof Pista)) {
                            Cancion nueva;
                            if (aux instanceof Video) {
                                nueva = new Video(aux.getArtista(), aux.getAlbum(), aux.getTitulo(), aux.getDuracion(), aux.getTrack(), ((Video) aux).getFormato());
                            } else {
                                nueva = new Cancion(aux.getArtista(), aux.getAlbum(), aux.getTitulo(), aux.getDuracion(), aux.getTrack());
                            }
                            ln.InsertarFinal(nueva);
                        }
                        aux = aux.getProximo();
                    }
                } else if (Pista) { // si solo queremos pista
                    while (aux != null) {
                        if (aux instanceof Pista) {
                            Cancion nueva = new Pista(aux.getArtista(), aux.getAlbum(), aux.getTitulo(), aux.getDuracion(), aux.getTrack(), ((Pista) aux).getNombreArchivoLetra());
                            ln.InsertarFinal(nueva);
                        }
                        aux = aux.getProximo();
                    }
                } else if (Video) { // si solo queremos video
                    while (aux != null) {
                        if (aux instanceof Video) {
                            Cancion nueva = new Video(aux.getArtista(), aux.getAlbum(), aux.getTitulo(), aux.getDuracion(), aux.getTrack(), ((Video) aux).getFormato());
                            ln.InsertarFinal(nueva);
                        }
                        aux = aux.getProximo();
                    }
                } else if (Cancion) { //si solo queremos cancion
                    while (aux != null) {
                        if (!(aux instanceof Pista) && !(aux instanceof Video)) { //vemos si no es pista ni video
                            Cancion nueva = new Cancion(aux.getArtista(), aux.getAlbum(), aux.getTitulo(), aux.getDuracion(), aux.getTrack());
                            ln.InsertarFinal(nueva);
                        }
                        aux = aux.getProximo();
                    }
                }
            }
        }
        return ln;
    }

    /**
     * Ordenar Por Lista Reordena la lista a como esta por default
     */
    public void OrdenarPorLista() {
    }

    /**
     * Revisa si una cancion ya existe en la lista
     *
     * @param titulo cancion a buscar
     * @return boolean si existe o no
     */
    public boolean yaExiste(String titulo) {
        Cancion aux = _Cabeza;
        while (aux != null) {
            if (aux.getTitulo().equals(titulo)) {
                return true;
            }
            aux = aux.getProximo();
        }
        return false;
    }

    /**
     * Insertar primero inserta una cancion de primero en la lista
     *
     * @param nuevo cancion a ingresar
     */
    public void InsertaPrimero(Cancion nuevo) {
        if (_Cabeza == null) {
            _Cabeza = nuevo;
            _Cola = nuevo;
        } else {
            nuevo.setProximo(_Cabeza);
            _Cabeza = nuevo;
        }
    }

    /**
     * Esta Vacia Revisa si la lista esta vacia
     *
     * @return boolean si la lista esta vacia o no
     */
    public boolean EstaVacia() {
        return (_Cabeza == null);
    }

//getters and setters
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
//fin de getters and setters

}// fin de clase
