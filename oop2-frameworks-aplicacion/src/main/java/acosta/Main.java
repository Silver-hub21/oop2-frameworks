package acosta;

import acosta.framework.Framework;

public class Main {
    public static void main(String[] args) {
        Framework framework = new Framework();
        framework.cargarConfiguracion("configuracion.txt");
        framework.ejecutar();
    }
}