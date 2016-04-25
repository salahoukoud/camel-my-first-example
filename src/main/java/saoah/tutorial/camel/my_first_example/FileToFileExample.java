package saoah.tutorial.camel.my_first_example;

import org.apache.camel.CamelContext;
import org.apache.camel.Component;
import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Producer;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.file.FileComponent;
import org.apache.camel.component.log.LogComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class FileToFileExample {

	CamelContext camelContext = new DefaultCamelContext();
	ProducerTemplate template;

	/**
	 * 
	 */
	public FileToFileExample() {
		this.camelContext = new DefaultCamelContext();

		// add the log component
		camelContext.addComponent("log", new LogComponent());

		// this.template = camelContext.createProducerTemplate();
	}

	void sendToCamelLog(String name) {
		try {
			Component component = camelContext.getComponent("log");
			Endpoint endpoint = component.createEndpoint("log:saoah.tutorial.camel.my_first_example?level=INFO&showAll=true");

			Exchange exchange = endpoint.createExchange();
			exchange.getIn().setBody(name);

			Producer producer = endpoint.createProducer();
			producer.start();

			producer.process(exchange);

			producer.stop();

		} catch (Exception e) {
			// we ignore any exceptions and just rethrow as runtime
			throw new RuntimeException(e);
		}
	}

	void sendToCamelFile(String text) {
		try {
			Component component = camelContext.getComponent("file");
			Endpoint endpoint = component.createEndpoint("file://target");

			Exchange exchange = endpoint.createExchange();
			exchange.getIn().setBody(text);
			exchange.getIn().setHeader(Exchange.FILE_NAME, "IDX.txt");

			Producer producer = endpoint.createProducer();
			producer.start();
			producer.process(exchange);
			producer.stop();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
