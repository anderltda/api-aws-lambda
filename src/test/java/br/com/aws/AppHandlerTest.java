package br.com.aws;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import br.com.aws.GatewayResponse;
import br.com.aws.handle.AppHandler;

public class AppHandlerTest {
  @Test
  public void successfulResponse() {
    AppHandler app = new AppHandler();
    GatewayResponse result = (GatewayResponse) app.handleRequest(null, null);
    assertEquals(result.getStatusCode(), 200);
    assertEquals(result.getHeaders().get("Content-Type"), "application/json");
    String content = result.getBody();
    assertNotNull(content);
    //assertTrue(content.contains("\"message\""));
    //assertTrue(content.contains("\"hello world\""));
    //assertTrue(content.contains("\"location\""));
  }
}
