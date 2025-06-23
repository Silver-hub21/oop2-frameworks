package acosta.framework;

import java.io.*;
import java.util.*;

public class Framework {
    private List<Accion> acciones;
    private Scanner scanner;

    public Framework() {
        this.acciones = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void cargarConfiguracion(String rutaArchivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.startsWith("acciones:")) {
                    String clasesStr = linea.substring("acciones:".length()).trim();
                    String[] clases = clasesStr.split(";");

                    for (String clase : clases) {
                        clase = clase.trim();
                        if (!clase.isEmpty()) {
                            cargarAccion(clase);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de configuración: " + e.getMessage());
        }
    }

    private void cargarAccion(String nombreClase) {
        try {
            Class<?> clazz = Class.forName(nombreClase);
            Object instancia = clazz.getDeclaredConstructor().newInstance();
            if (instancia instanceof Accion) {
                acciones.add((Accion) instancia);
            }
        } catch (Exception e) {
            System.err.println("Error al cargar la clase: " + nombreClase + " - " + e.getMessage());
        }
    }

    public void ejecutar() {
        while (true) {
            mostrarMenu();
            int opcion = leerOpcion();

            if (opcion == acciones.size() + 1) {
                System.out.println("¡Hasta luego!");
                break;
            }

            if (opcion >= 1 && opcion <= acciones.size()) {
                ejecutarAccion(opcion - 1);
            } else {
                System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }

    private void mostrarMenu() {
        System.out.println("\nBienvenido, estas son sus opciones:");
        System.out.println();

        for (int i = 0; i < acciones.size(); i++) {
            Accion accion = acciones.get(i);
            System.out.println((i + 1) + ". " + accion.nombreItemMenu() +
                    " (" + accion.descripcionItemMenu() + ")");
        }
        System.out.println((acciones.size() + 1) + ". Salir");
        System.out.println();
        System.out.print("Ingrese su opción: ");
    }

    private int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void ejecutarAccion(int indice) {
        try {
            System.out.println("\nEjecutando acción...");
            acciones.get(indice).ejecutar();
            System.out.println("Acción ejecutada exitosamente.");
        } catch (Exception e) {
            System.err.println("Error al ejecutar la acción: " + e.getMessage());
        }
    }
}

