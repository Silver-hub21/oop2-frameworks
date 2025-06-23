package acosta.main;

import acosta.framework.Framework;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Uso: java garcia.main.Main <ruta-archivo-configuracion>");
            System.exit(1);
        }

        Framework framework = new Framework();
        framework.cargarConfiguracion(args[0]);
        framework.ejecutar();
    }
}
