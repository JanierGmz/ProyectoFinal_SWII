/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.stockexchange.adapter.output.publisher;

import co.unicauca.stockexchange.port.output.publisher.INotifier;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ledy Mayerly Astudillo Calderon
 * @author Santiago Nieto Guaca
 * @author Harold Andres Molano Rosero
 * @author Janier Yulder Gomez Galindez
 */
@Component
public class Notifier implements INotifier {

    // Nombre del intercambio RabbitMQ
    private static final String EXCHANGE_NAME = "direct_manage_notifications";
    
    // Fábrica de conexiones para RabbitMQ
    private final ConnectionFactory factory;
    
    // Host de RabbitMQ obtenido desde la configuración de Spring
    private final String host;

    // Constructor que recibe el host de RabbitMQ como parámetro
    public Notifier(@Value("${spring.rabbitmq.host}") String host) {
        this.factory = new ConnectionFactory();
        this.host = host;
        factory.setHost(this.host);
    }

    // Método para enviar un mensaje a través de RabbitMQ
    @Override
    public boolean sendMessage(String clientId, String msg) {
        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
            // Declara el intercambio con el nombre especificado y el tipo "direct"
            channel.exchangeDeclare(EXCHANGE_NAME, "direct", true);

            // Configura las propiedades del mensaje, como el modo de entrega
            AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder().deliveryMode(1).build();
            
            // Publica el mensaje en el intercambio usando el clientId como clave de enrutamiento
            channel.basicPublish(EXCHANGE_NAME, clientId, properties, msg.getBytes());

            // Imprime en la consola el mensaje que está siendo enviado
            System.out.println("Enviando mensaje:" + msg);
            return true;
        } catch (IOException | TimeoutException ex) {
            // Maneja cualquier excepción que pueda ocurrir durante el envío del mensaje
            Logger.getLogger(Notifier.class.getName()).log(Level.SEVERE, "Error al enviar el mensaje.", ex);
            return false;
        }
    }
}

