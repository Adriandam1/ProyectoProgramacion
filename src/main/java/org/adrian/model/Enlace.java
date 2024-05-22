package src.main.java.org.adrian.model;

public class Enlace {
    private String titulo;
    private String url;
    private String comentario;

    // Constructor, getters y setters


    public Enlace(String titulo, String url, String comentario) {
        this.titulo = titulo;
        this.url = url;
        this.comentario = comentario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return "Enlace{" +
                "titulo='" + titulo + '\'' +
                ", url='" + url + '\'' +
                ", comentario='" + comentario + '\'' +
                '}';
    }
}