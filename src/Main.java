import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.EOFException;
import java.lang.ClassNotFoundException;

public class Main {
    static Scanner scannerStr = new Scanner(System.in);
    static Scanner scannerNum = new Scanner(System.in).useLocale(Locale.US);

    public static void main(String[] args) {
        boolean salir = false;
        int opcion;
        do {
            menuPrincipal();
            opcion = scannerNum.nextInt();
            try {
                switch (opcion) {
                    case 1:
                        agregarAlumno();
                        break;
                    case 2:
                        mostrarAlumnos();
                        break;
                    case 3:
                        modificarAlumno();
                        break;
                    case 4:
                        eliminarAlumno();
                        break;
                    case 9:
                        // Mockear data, si aplica
                        break;
                    case 0:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opcion no valida");
                        break;
                }
            } catch (ClassNotFoundException e) {
                System.out.println("No se encontró la clase al leer los objetos: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Error de entrada/salida: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Ha ocurrido un error: " + e.getMessage());
            }
        } while (!salir);
        System.out.println("Fin del programa");
    }

    private static void menuPrincipal() {
        System.out.println("Menu de Opciones");
        System.out.println("================");
        System.out.println("1. Agregar Alumno");
        System.out.println("2. Listar Alumnos");
        System.out.println("3. Modifier Alumno");
        System.out.println("4. Eliminar Alumno");
        System.out.println("9. Moquear Data Ejemplo");
        System.out.println("0. Salir");
        System.out.println();
        System.out.print("Ingrese una opcion: ");
    }
    private static void agregarAlumno() throws IOException, ClassNotFoundException {
        System.out.print("Nombre: ");
        String nombre = scannerStr.nextLine();
        System.out.print("Apellido: ");
        String apellido = scannerStr.nextLine();
        System.out.print("Nota 1: ");
        double nota1 = scannerNum.nextDouble();
        System.out.print("Nota 2: ");
        double nota2 = scannerNum.nextDouble();
        System.out.print("Nota 3: ");
        double nota3 = scannerNum.nextDouble();

        Alumno alumno = new Alumno(nombre, apellido, nota1, nota2, nota3);
        GestorAlumnos.agregarAlumno(alumno);
        System.out.println("Alumno agregado exitosamente.");
    }
    private static void mostrarAlumnos() throws IOException, ClassNotFoundException {
        List<Alumno> alumnos = GestorAlumnos.leerAlumnos();
        if (alumnos.isEmpty()) {
            System.out.println("No hay alumnos registrados.");
            return;
        }
        System.out.println("Lista de Alumnos");
        System.out.println("================");
        for (Alumno alumno : alumnos) {
            System.out.println(alumno);
        }
    }
    private static void modificarAlumno() throws IOException, ClassNotFoundException {
        System.out.print("Ingrese el ID del alumno a modificar: ");
        int id = scannerNum.nextInt();
        List<Alumno> alumnos = GestorAlumnos.leerAlumnos();
        Alumno alumno = alumnos.stream().filter(a -> a.getId() == id).findFirst().orElse(null);
        if (alumno == null) {
            System.out.println("Alumno no encontrado.");
            return;
        }
        System.out.print("Nuevo nombre (" + alumno.getNombre() + "): ");
        String nombre = scannerStr.nextLine();
        System.out.print("Nuevo apellido (" + alumno.getApellido() + "): ");
        String apellido = scannerStr.nextLine();
        System.out.print("Nueva nota 1 (" + alumno.getNota1() + "): ");
        double nota1 = scannerNum.nextDouble();
        System.out.print("Nueva nota 2 (" + alumno.getNota2() + "): ");
        double nota2 = scannerNum.nextDouble();
        System.out.print("Nueva nota 3 (" + alumno.getNota3() + "): ");
        double nota3 = scannerNum.nextDouble();
        Alumno alumnoActualizado = new Alumno(nombre, apellido, nota1, nota2, nota3);
        alumnoActualizado.setId(id); // Mantener el mismo ID
        GestorAlumnos.actualizarAlumno(alumnoActualizado);
        System.out.println("Alumno actualizado exitosamente.");
    }
    private static void eliminarAlumno() throws IOException, ClassNotFoundException {
        System.out.print("Ingrese el ID del alumno a eliminar: ");
        int id = scannerNum.nextInt();
        GestorAlumnos.eliminarAlumno(id);
        System.out.println("Alumno eliminado exitosamente.");
    }

}