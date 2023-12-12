package co.unicauca.financialagent.pobserver;
/**
 * @author Ledy Mayerly Astudillo Calderon
 * @author Santiago Nieto Guaca
 * @author Harold Andres Molano Rosero
 * @author Janier Yulder Gomez Galindez
 */
// Interfaz para los observadores
public interface IObserver {
    // Método llamado cuando se actualiza el estado o hay un evento relevante
    void update(String msg);
}
