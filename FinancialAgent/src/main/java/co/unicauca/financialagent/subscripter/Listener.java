package co.unicauca.financialagent.subscripter;

import co.unicauca.financialagent.infra.AbstractObservable;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

/**
 * @author Ledy Mayerly Astudillo Calderon
 * @author Santiago Nieto Guaca
 * @author Harold Andres Molano Rosero
 * @author Janier Yulder Gomez Galindez
 */
public class Listener extends AbstractObservable {

    // Propiedades del intercambio (exchange) obtenidas del archivo de configuración
    private static final String EXCHANGE_NAME_PROPERTY = "publisher.exchange_name";
    private static final String PUBLISHER_HOST_PROPERTY = "publisher.host";

    // Nombre del intercambio
    private static String EXCHANGE_NAME = "";

    // Factoría de conexión a RabbitMQ
    private final ConnectionFactory factory;

    // Identificador del cliente al que se le notificarán los mensajes
    private final String clientId;

    // Constructor
    public Listener(String clientId) {
        // Configuración predeterminada del host de RabbitMQ
        String host = "localhost";

        // Propiedades cargadas desde el archivo de configuración
        Properties properties = new Properties();

        // Inicialización de la factoría de conexión
        this.factory = new ConnectionFactory();

        // Configuración del identificador del cliente
        this.clientId = clientId.toUpperCase();

        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            // Cargar el archivo de propiedades
            properties.load(input);

            // Obtener el host y el nombre del intercambio desde las propiedades
            host = properties.getProperty(PUBLISHER_HOST_PROPERTY);
            EXCHANGE_NAME = properties.getProperty(EXCHANGE_NAME_PROPERTY);
        } catch (IOException e) {
            // Manejo de excepciones al cargar el archivo de configuración
            e.printStackTrace();
            EXCHANGE_NAME = "";
        }

        // Configuración del host en la factoría de conexión
        factory.setHost(host);
    }

    // Método para escuchar las notificaciones de RabbitMQ
    public void listenNotifications() throws IOException, TimeoutException {
        // Crear una nueva conexión a RabbitMQ
        Connection connection = factory.newConnection();

        // Crear un canal de comunicación
        Channel channel = connection.createChannel();

        // Declarar el intercambio (exchange) en el canal
        channel.exchangeDeclare(EXCHANGE_NAME, "direct", true);

        // Declarar una cola en el canal y obtener su nombre
        String queueName = channel.queueDeclare("main_" + clientId, true, false, false, null).getQueue();

        // Vincular la cola al intercambio usando el identificador del cliente como clave de enrutamiento
        channel.queueBind(queueName, EXCHANGE_NAME, clientId);

        // Configurar un objeto de devolución de llamada para procesar los mensajes recibidos
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            // Convertir el cuerpo del mensaje a una cadena UTF-8
            String message = new String(delivery.getBody(), "UTF-8");

            // Notificar a los observadores (observadores registrados) con el mensaje recibido
            notify(message);
        };

        // Comenzar a consumir mensajes de la cola con la devolución de llamada configurada
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });
    }
}
