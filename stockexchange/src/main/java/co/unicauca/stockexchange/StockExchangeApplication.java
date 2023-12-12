package co.unicauca.stockexchange;

import co.unicauca.stockexchange.adapter.output.data.ClientRepository;
import co.unicauca.stockexchange.adapter.output.data.StockExchangeRepository;
import co.unicauca.stockexchange.adapter.output.publisher.Notifier;
import co.unicauca.stockexchange.port.output.data.IClientRepository;
import co.unicauca.stockexchange.port.output.data.IStockExchangeRepository;
import co.unicauca.stockexchange.port.output.publisher.INotifier;
import co.unicauca.stockexchange.publisher.StockValueMonitorThread;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *
 * @author Ledy Mayerly Astudillo Calderon
 * @author Santiago Nieto Guaca
 * @author Harold Andres Molano Rosero
 * @author Janier Yulder Gomez Galindez
 */
/*
 * Clase principal que inicia la aplicación de la bolsa de valores.
 * Utiliza Spring Boot para la configuración y gestión del contexto de la aplicación.
 */
@SpringBootApplication
public class StockExchangeApplication {

    public static void main(String[] args) {
        // Configuración de la aplicación Spring Boot y obtención del contexto
        ConfigurableApplicationContext context = SpringApplication.run(StockExchangeApplication.class, args);

        // Obtención de instancias de repositorios y notificador a través del contexto de Spring
        IStockExchangeRepository stockExchangeRepo = context.getBean(StockExchangeRepository.class);
        IClientRepository clientRepo = context.getBean(ClientRepository.class);
        INotifier notifier = new Notifier("localhost");

        // Configuración y arranque del hilo monitor de cambios en los valores de las acciones
        StockValueMonitorThread stockMonitor = new StockValueMonitorThread(stockExchangeRepo, clientRepo, notifier);
        stockMonitor.start();
    }
}
