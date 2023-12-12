/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.unicauca.stockexchange.port.output.publisher;

/**
 *
 * @author Ledy Mayerly Astudillo Calderon
 * @author Santiago Nieto Guaca
 * @author Harold Andres Molano Rosero
 * @author Janier Yulder Gomez Galindez
 */
public interface INotifier {

    /**
     * Envía un mensaje a un cliente identificado por su ID.
     *
     * @param clientId ID del cliente.
     * @param msg Mensaje a enviar.
     * @return `true` si el mensaje se envió correctamente, `false` en caso contrario.
     */
    boolean sendMessage(String clientId, String msg);
}

