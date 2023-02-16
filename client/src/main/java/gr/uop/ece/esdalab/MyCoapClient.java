package gr.uop.ece.esdalab;

import org.eclipse.californium.core.*;
import org.eclipse.californium.core.config.CoapConfig;
import org.eclipse.californium.core.network.CoapEndpoint;
import org.eclipse.californium.elements.config.TcpConfig;
import org.eclipse.californium.elements.config.UdpConfig;
import org.eclipse.californium.elements.exception.ConnectorException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

public class MyCoapClient {
    public static void main(String[] args) throws ConnectorException, IOException, URISyntaxException {

        CoapConfig.register();
        UdpConfig.register();
        TcpConfig.register();

        CoapClient client = new CoapClient("coap://localhost:5683/humidity");
        client.setTimeout(10L);

        CoapResponse response = client.get();
        System.out.println(client.get().toString());
        System.out.println(client.get().getResponseText());
//        System.out.println(client.get().getCode());

    }
}
