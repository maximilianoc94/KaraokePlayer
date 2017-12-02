package BackEnd;

/** Video
 * Tipo de cancion con un formato de video
 *
 * @author Maximiliano Casale
 */
public class Video extends Cancion{
    private final String _Formato;
    
    /**Constructor Video
     * Utiliza el constructor del padre
     * y asigna el formato del video a reproducir
     * 
     * @param formato tipo de video
     */
        public Video(String artista, String album, String titulo, int duracion, int track, String formato)
    {
        super(artista, album,titulo,duracion, track);
        _Formato = formato;
    }

        //getters y setters de los atributos
    public String getFormato() {
        return _Formato;
    }    
}//fin de la clase
