package generacionCodigo;


public class Variable {
    public String clase;
    public String nombre;
    public String tipo;

    public Variable(String c, String n, String t){
        this.clase = c;
        this.nombre = n;
        this.tipo = t;
    }

    @Override
    public String toString() {
        return "Variable{" +
                "nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
