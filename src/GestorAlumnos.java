


import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorAlumnos {
    private static final String ARCHIVO = "alumnos.bin";

    public static void agregarAlumno(@org.jetbrains.annotations.NotNull Alumno alumno) throws IOException, ClassNotFoundException {
        List<Alumno> alumnos = leerAlumnos();
        alumno.setId(generarNuevoId(alumnos));
        alumnos.add(alumno);
        escribirAlumnos(alumnos);
    }

    @SuppressWarnings("unchecked")
    private static <T> T leerObjeto(String path) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            return (T) ois.readObject();
        }
    }


    public static List<Alumno> leerAlumnos() throws IOException, ClassNotFoundException {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            return new ArrayList<>();
        }
        // Casting seguro utilizando el método genérico
        return leerObjeto(archivo.getPath());
    }


    public static void actualizarAlumno(Alumno alumnoActualizado) throws IOException, ClassNotFoundException {
        List<Alumno> alumnos = leerAlumnos();
        alumnos.replaceAll(alumno -> alumno.getId() == alumnoActualizado.getId() ? alumnoActualizado : alumno);
        escribirAlumnos(alumnos);
    }

    public static void eliminarAlumno(int id) throws IOException, ClassNotFoundException {
        List<Alumno> alumnos = leerAlumnos();
        alumnos.removeIf(alumno -> alumno.getId() == id);
        escribirAlumnos(alumnos);
    }

    private static void escribirAlumnos(List<Alumno> alumnos) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            oos.writeObject(alumnos);
        }
    }

    private static int generarNuevoId(@NotNull List<Alumno> alumnos) {
        return alumnos.stream().mapToInt(Alumno::getId).max().orElse(0) + 1;
    }
}
