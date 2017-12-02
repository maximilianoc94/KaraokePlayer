package BackEnd;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Pista extends de Cancion Clase para trabajar los tipos de canciones a las que
 * se le reproducira la lirica
 *
 * @author Maximiliano Casale
 */
public class Pista extends Cancion {

    private String _NombreArchivoLetra;

    /**
     * Constructor Pista
     *
     * Utiliza el mismo constructor de su padre y le asigna el nombre del
     * archivo donde se encuentra la lirica de la cancion
     *
     * @param archivoLetra Nombre del archivo donde esta la letra
     */
    public Pista(String artista, String album, String titulo, int duracion, int track, String archivoLetra) {
        super(artista, album, titulo, duracion, track);
        _NombreArchivoLetra = archivoLetra;
    }

    /**
     * leerPista metodo para leer las liricas de la cancion asigna cada linea a
     * un string y retorna el vector de strings para ser reproducido en la
     * interface
     *
     * @param duracion duracion de la cancion
     * @return vector de strings con la letra
     * @throws FileNotFoundException En caso de que no consiga el documento
     * @throws IOException En caso de que ocurra algo al cerrar el archivo
     */
    public String[] leerPista(int duracion) throws FileNotFoundException, IOException {
        String[] letras = new String[duracion + 1];
        try (FileReader fr = new FileReader(_NombreArchivoLetra)) {
            Scanner s = new Scanner(fr);
            int i = 0;
            while (s.hasNextLine() && i < duracion + 1) {
                if (i % 2 == 0) {
                    letras[i] = s.nextLine();
                } else {
                    letras[i] = letras[i - 1];
                }
                i++;
            }
        }
        return letras;
    }

    //getters and setters de sus atributos diferentes al padre
    public String getNombreArchivoLetra() {
        return _NombreArchivoLetra;
    }

    public void setNombreArchivoLetra(String _NombreArchivoLetra) {
        this._NombreArchivoLetra = _NombreArchivoLetra;
    }
}//fin de la clase
