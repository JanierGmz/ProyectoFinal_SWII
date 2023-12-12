package co.unicauca.financialagent;

import co.unicauca.financialagent.gui.FinancialAgentGUI;
import co.unicauca.financialagent.subscripter.Listener;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import javax.swing.JOptionPane;

/**
 * Clase principal que inicia la aplicación del Agente Financiero.
 * Esta clase solicita al usuario que ingrese un ID, crea un Listener
 * para escuchar notificaciones y muestra la interfaz gráfica.
 * 
 * Se utilizan clases externas como FinancialAgentGUI y Listener.
 * FinancialAgentGUI es la interfaz gráfica de la aplicación.
 * Listener es una clase que se encarga de escuchar notificaciones.
 * 
 * @author Ledy Mayerly Astudillo Calderon
 * @author Santiago Nieto Guaca
 * @author Harold Andres Molano Rosero
 * @author Janier Yulder Gomez Galindez
 */
public class FinancialAgent {

    /**
     * Método principal que inicia la aplicación.
     * @param args Argumentos de la línea de comandos (no se utilizan en este caso)
     * @throws IOException Excepción de E/S en caso de problemas de lectura/escritura
     * @throws TimeoutException Excepción relacionada con problemas de tiempo de espera
     */
    public static void main(String[] args) throws IOException, TimeoutException {
        // Solicitar al usuario que ingrese un ID
        String id = JOptionPane.showInputDialog("Digita el id del Cliente:");
        
        // Crear un Listener con el ID proporcionado
        Listener listener = new Listener(id);
        
        // Crear una instancia de la interfaz gráfica
        FinancialAgentGUI gui = new FinancialAgentGUI();
        
        // Agregar la interfaz gráfica como observador del Listener
        listener.addObserver(gui);
        
        // Iniciar la escucha de notificaciones
        listener.listenNotifications();
        
        // Mostrar la interfaz gráfica
        gui.setVisible(true);
    }
}
