package gr.uop.ece.esdalab;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.Utils;
import org.eclipse.californium.elements.exception.ConnectorException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;

public class MyCoapClient {
    public static void main(String[] args)
            throws URISyntaxException, ConnectorException, IOException {

        // make synchronous get call
        System.out.println("GET TEMP");
        URI uri = new URI("coap://18.196.160.156:5684/temperature");
        CoapClient client = new CoapClient(uri);
        CoapResponse response = client.get();
        if ( response != null ) {
//            byte[] bytes = response.getPayload();

            System.out.println(response.getCode());
            System.out.println(response.getOptions());
            System.out.println(response.getResponseText());
            System.out.println("\nDETAILED RESPONSE:");
            System.out.println(Utils.prettyPrint(response));
        }


        uri = new URI("coap://18.196.160.156:5684/humidity");
        client = new CoapClient(uri);
        response = client.get();
        if ( response != null ) {
//            byte[] bytes = response.getPayload();

            System.out.println(response.getCode());
            System.out.println(response.getOptions());
            System.out.println(response.getResponseText());
            System.out.println("\nDETAILED RESPONSE:");
            System.out.println(Utils.prettyPrint(response));
        }
    }
}
