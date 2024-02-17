import java.io.Serializable;

public class Alumno implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id; // Identificador único
    private String nombre;
    private String apellido;
    private double nota1;
    private double nota2;
    private double nota3;
    private transient double promedio; // Calculado, no necesita ser serializado

    public Alumno( String nombre, String apellido, double nota1, double nota2, double nota3) {
        this.id = 0;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
        this.promedio = calcularPromedio();
    }

    // Métodos getter y setter para todos los campos

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public double getNota1() {
        return nota1;
    }

    public void setNota1(double nota1) {
        this.nota1 = nota1;
        this.promedio = calcularPromedio();
    }

    public double getNota2() {
        return nota2;
    }

    public void setNota2(double nota2) {
        this.nota2 = nota2;
        this.promedio = calcularPromedio();
    }

    public double getNota3() {
        return nota3;
    }

    public void setNota3(double nota3) {
        this.nota3 = nota3;
        this.promedio = calcularPromedio();
    }


    // Otros getters y setters omitidos por brevedad

    public double calcularPromedio() {
        return (nota1 + nota2 + nota3) / 3;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + nombre + " " + apellido + " | Notas: " + nota1 + ", " + nota2 + ", " + nota3 + " | Promedio: " + calcularPromedio();
    }
}
