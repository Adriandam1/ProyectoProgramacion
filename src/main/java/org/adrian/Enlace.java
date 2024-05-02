package org.adrian;

public class Enlace {
    private String nombreEnlace;
    private String url;

    public Enlace() {
    }

    public Enlace(String nombreEnlace, String url) {
        this.nombreEnlace = nombreEnlace;
        this.url = url;
    }

    public String getNombreEnlace() {
        return nombreEnlace;
    }

    public void setNombreEnlace(String nombreEnlace) {
        this.nombreEnlace = nombreEnlace;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Enlace{" +
                "nombreEnlace='" + nombreEnlace + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
