package gr.uop.ece.esdalab.server;

import net.datafaker.Faker;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.config.CoapConfig;
import org.eclipse.californium.core.network.CoapEndpoint;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.eclipse.californium.elements.config.Configuration;
import org.eclipse.californium.elements.util.NetworkInterfacesUtil;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.logging.Logger;

public class MyCoapServer extends CoapServer {

    private static Logger LOGGER = null;

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format",
                "[%1$tF %1$tT] [%4$-7s] %5$s %n");
        LOGGER = Logger.getLogger(MyCoapServer.class.getName());
    }

    Faker faker = new Faker();
    private static final int COAP_PORT =
            Configuration.getStandard().get( CoapConfig.COAP_PORT );
    private static final String tempUnti = "C";
    float temperature = 20;
    float humidity = 70;

    public static void main(String[] args) {
        try {
            MyCoapServer server = new MyCoapServer();
            server.addEndpoints();
            server.start();
            LOGGER.info("COAP server is running on localhost:" + COAP_PORT);

        }
        catch ( Exception e ) {
            System.err.println("CoAP server err: " + e.getMessage());
        }
    }

    public MyCoapServer() throws SocketException {
        super();
        add( new TemperatureResource() );
        add( new HumidityResource() );
    }

    private void addEndpoints() {
        Configuration config = Configuration.getStandard();

        // Add an endpoint listener for each host network interface
        for (InetAddress addr :
                NetworkInterfacesUtil.getNetworkInterfaces()) {
            InetSocketAddress bindToAddress =
                    new InetSocketAddress(addr, COAP_PORT);
            CoapEndpoint.Builder builder = new CoapEndpoint.Builder();
            builder.setInetSocketAddress(bindToAddress);
            builder.setConfiguration(config);
            addEndpoint( builder.build() );
        }
        CoapEndpoint.Builder builder = new CoapEndpoint.Builder();
        builder.setInetSocketAddress(new InetSocketAddress("localhost", COAP_PORT));
        builder.setConfiguration(config);
        addEndpoint( builder.build() );

    }

    class TemperatureResource extends CoapResource {
        public TemperatureResource() {
            super("temperature"); // set resource URI identifier
            getAttributes().setTitle("Server room temperature");
        }

        @Override
        public void handleGET(CoapExchange exchange) {
            LOGGER.info("handleGET temperature");
            // get latest temperature reading and return it
            temperature = faker.number().numberBetween(0, 40);
            exchange.respond(String.valueOf(temperature));
        }
    }

    class HumidityResource extends CoapResource {
        public HumidityResource() {
            super("humidity"); // set resource URI identifier
            getAttributes().setTitle("Server room humidity");
        }

        @Override
        public void handleGET(CoapExchange exchange) {
            LOGGER.info("handleGET humidity");
            // get latest humidity reading and return it
            humidity = faker.number().numberBetween(50, 80);
            exchange.respond(String.valueOf(humidity));
        }
    }
}
