/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.unicauca.stockexchange.port.input.data;

import co.unicauca.stockexchange.commons.domain.Client;
import java.util.List;


/**
 *
 * @author Ledy Mayerly Astudillo Calderon
 * @author Santiago Nieto Guaca
 * @author Harold Andres Molano Rosero
 * @author Janier Yulder Gomez Galindez
 */
public interface IClientData {

    /**
     * Busca un cliente por su ID.
     *
     * @param id ID del cliente a buscar.
     * @return Cliente correspondiente al ID proporcionado.
     */
    Client findClient(String id);

    /**
     * Obtiene la lista de todos los clientes en el repositorio.
     *
     * @return Lista de todos los clientes almacenados en el repositorio.
     */
    List<Client> getClients();

    /**
     * Registra un nuevo cliente.
     *
     * @param clientId   ID del nuevo cliente.
     * @param clientName Nombre del nuevo cliente.
     * @param phone      Número de teléfono del nuevo cliente.
     * @return Mensaje de confirmación del registro del cliente.
     */
    String registerClient(String clientId, String clientName, String phone);
}

