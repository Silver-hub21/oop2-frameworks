package acosta;

import acosta.framework.Accion;

public class AccionUno implements Accion {

    @Override
    public void ejecutar() {
        System.out.println("Acción Uno ejecutada");
    }

    @Override
    public String nombreItemMenu() {
        return "Acción Uno";
    }

    @Override
    public String descripcionItemMenu() {
        return "Esta es la acción uno.";
    }
}
