package gr.uop.ece.esdalab;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.Utils;
import org.eclipse.californium.elements.exception.ConnectorException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Main {
    public static void main(String args[])
            throws URISyntaxException, ConnectorException, IOException {

        // make synchronous get call
        URI uri = new URI("coap://localhost:5683/temp");
        CoapClient client = new CoapClient(uri);
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
