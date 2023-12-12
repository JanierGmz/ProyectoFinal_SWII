package co.unicauca.financialagent.infra;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ledy Mayerly Astudillo Calderon
 * @author Santiago Nieto Guaca
 * @author Harold Andres Molano Rosero
 * @author Janier Yulder Gomez Galindez
 */
// Clase abstracta que sirve como base para implementar el patrón de observador
public abstract class AbstractObservable {

    // Lista de observadores que serán notificados de eventos
    private List<IObserver> observadores;

    // Método para agregar un observador a la lista
    public void addObserver(IObserver observer) {
        // Si la lista de observadores no ha sido inicializada, se crea una nueva
        if (observadores == null) {
            observadores = new ArrayList<>();
        }
        // Se agrega el observador a la lista
        observadores.add(observer);
    }

    // Método para remover un observador de la lista
    public void removeObserver(IObserver observer) {
        // Si la lista de observadores no es nula, se remueve el observador
        if (observadores != null) {
            observadores.remove(observer);
        }
    }

    // Método para notificar a todos los observadores de un evento
    public void notify(String msg) {
        // Se recorre la lista de observadores y se llama al método update de cada uno
        for (IObserver observer : observadores) {
            observer.update(msg);
        }
    }
}
