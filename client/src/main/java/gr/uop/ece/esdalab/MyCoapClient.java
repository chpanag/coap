package gr.uop.ece.esdalab;

import org.eclipse.californium.core.*;
import org.eclipse.californium.elements.exception.ConnectorException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

public class MyCoapClient {
    public static void main(String[] args) throws ConnectorException, IOException, URISyntaxException {

        CoapClient client = new CoapClient(new URI("coap://18.196.160.156:5683/humidity"));
        client.setTimeout(10L);

        CoapResponse response = client.get();
        if ( response != null ) {
            byte[] bytes = response.getPayload();
            System.out.println(response.getCode());
            System.out.println(response.getOptions());
            System.out.println(response.getResponseText());
            System.out.println("\nDETAILED RESPONSE:");
            System.out.println(Utils.prettyPrint(response));
        }
    }
}
