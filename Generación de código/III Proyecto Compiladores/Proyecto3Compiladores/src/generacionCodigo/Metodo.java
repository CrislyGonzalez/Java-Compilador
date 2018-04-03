package generacionCodigo;


public class Metodo {
    public String clase;
    public String nombre;
    public String descriptor;

    public Metodo(String c, String n, String d){
        this.nombre = n;
        this.clase = c;
        this.descriptor = d;
    }

    @Override
    public String toString() {
        return "Metodo{" +
                "clase='" + clase + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descriptor='" + descriptor + '\'' +
                '}';
    }
}
