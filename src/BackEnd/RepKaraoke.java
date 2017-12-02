package BackEnd;

import BackEnd.Estructuras.ListaReproduccion;
import BackEnd.Estructuras.ListaCanciones;
import BackEnd.Estructuras.ArbolArtistas;
import BackEnd.Estructuras.ArbolTitulos;
import GUI.MenuPrincipal;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class RepKaraoke Clase principal para controlar el programa Inicializa las
 * respectivas estructuras de datos utilizadas
 *
 * @author Maximiliano Casale
 */
public class RepKaraoke {

    public ListaCanciones _Biblioteca;
    public ListaReproduccion _Reproduccion;
    public ArbolArtistas _Artistas;
    public ArbolTitulos _Titulos;
    public MenuPrincipal GUI;

    public RepKaraoke() {
        _Reproduccion = new ListaReproduccion();
        _Biblioteca = new ListaCanciones();
        _Artistas = new ArbolArtistas();
        _Titulos = new ArbolTitulos();
        // Llenar la biblioteca desde un .dat
        leerData(_Biblioteca);
        // con la biblioteca llenar los arboles de artistas y titulos
        _Artistas.Llenar(_Biblioteca);
        _Titulos.Llenar(_Biblioteca);
    }

    /**
     * LeerData Metodo en donde se lee el archivo .dat con las canciones
     * ingresadas al sistema anteriormente
     *
     * @param biblioteca Estructura donde es guardada la informacion
     */
    public void leerData(ListaCanciones biblioteca) {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("biblioteca.dat"));
            _Biblioteca.InsertarFinal((Cancion) ois.readObject());

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(RepKaraoke.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ois.close();
            } catch (IOException ex) {
                Logger.getLogger(RepKaraoke.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Escribir Data Guarda la informacion actual en el sistema en un archivo
     * .dat para poder leerla cuando se vuelva a iniciar el programa
     *
     * @param biblioteca
     */
    public void escribirData(ListaCanciones biblioteca) {
        ObjectOutputStream oos = null;
        Cancion aux = biblioteca.getCabeza();
        try {
            oos = new ObjectOutputStream(new FileOutputStream("biblioteca.dat"));
            oos.writeObject(aux);
        } catch (IOException e) {
        } finally {
            try {
                oos.close();
            } catch (IOException ex) {
                Logger.getLogger(RepKaraoke.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /**
     * Formato Tiempo metodo para darle formato de tiempo a un int
     *
     * @param seg la duracion en segundos de las canciones
     * @return String del formato de tiempo
     */
    public String formatoTiempo(int seg) {
        int min = seg / 60;
        int segundos = seg % 60;
        if (segundos < 10) {
            return (min + ":0" + segundos);
        } else {
            return (min + ":" + segundos);
        }
    }
//getters y setters para los atributos

    public ListaCanciones getBiblioteca() {
        return _Biblioteca;
    }

    public void setBiblioteca(ListaCanciones aux) {
        _Biblioteca = aux;
    }

    public ListaReproduccion getReproduccion() {
        return _Reproduccion;
    }

    public ArbolArtistas getArtistas() {
        return _Artistas;
    }

    public ArbolTitulos getTitulos() {
        return _Titulos;
    }

    public void setGUI(MenuPrincipal GUI) {
        this.GUI = GUI;
    }

    public MenuPrincipal getGUI() {
        return GUI;
    }
//fin de getters y setters

    public static void main(String[] args) {
        RepKaraoke App = new RepKaraoke();
        MenuPrincipal GUI = new MenuPrincipal(App);
        App.setGUI(GUI);
        GUI.setVisible(true);
    } // fin de main
}//fin de clase
