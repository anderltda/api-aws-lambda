package br.com.aws.handle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.xray.AWSXRay;
import com.amazonaws.xray.entities.Subsegment;
import br.com.aws.GatewayResponse;

/**
 * Handler for requests to Lambda function.
 */
public class AppHandler implements RequestHandler<Object, Object> {

    public Object handleRequest(final Object input, final Context context) {
        
        Map<String, String> headers = new HashMap<>();

        headers.put("Content-Type", "application/json");
       
        headers.put("X-Custom-Header", "application/json");
        
        Subsegment subsegment = AWSXRay.beginSubsegment("AppHandler::handleRequest");
        
        try {
            
            return new GatewayResponse(getJson(), headers, 200);
                        
        } catch (Exception e) {
            subsegment.addException(e);
            return new GatewayResponse("{}", headers, 500);
        } finally {
            AWSXRay.endSubsegment();
        }
    }

    private String getPageContents(String address) throws IOException{
        URL url = new URL(address);
        try(BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
            return br.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }
    
    
    private String getJson() throws Exception {
    	
    	final String pageContents = this.getPageContents("https://checkip.amazonaws.com");

        StringBuilder builder = new StringBuilder();
        
        builder.append("{ ");
        
        builder.append("\"name\"");
        builder.append(" : ");
        builder.append("\"Anderson\"");
        
        builder.append(",");
        
        builder.append("\"nickname\"");
        builder.append(" : ");
        builder.append("\"anderltda\"");
        
        builder.append(",");
        
        builder.append("\"lastname\"");
        builder.append(" : ");
        builder.append("\"Nascimento\"");
        
        builder.append(",");
        
        builder.append("\"age\"");
        builder.append(" : ");
        builder.append("\"37\"");
        
        builder.append(",");
        
        builder.append("\"city\"");
        builder.append(" : ");
        builder.append("\"SP\"");
        
        builder.append(",");
        
        builder.append("\"working\"");
        builder.append(" : ");
        builder.append("\"Itau-Unibanco\"");
        
        builder.append(",");
        
        builder.append("\"time\"");
        builder.append(" : ");
        builder.append("\"Real Madrid\"");
        
        builder.append(",");
        
        builder.append("\"favorite food\"");
        builder.append(" : ");
        builder.append("\"japonese foods\"");
        
        builder.append(",");
        
        builder.append("\"location\"");
        builder.append(" : ");
        builder.append("\"" + pageContents + "\"");
        
        builder.append(" }");
        
        return builder.toString();
    }

}
