package com.vtw.restkafkagrafana.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

@Component("LogProcessor")
public class LogProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        String apiPath = (String) exchange.getIn().getHeader("CamelHttpUri");
        String httpMethod = (String) exchange.getIn().getHeader("CamelHttpMethod");
        String productType = (String) exchange.getProperties().get("productType");
        String product = (String) exchange.getProperties().get("product");
        String fail = (String) exchange.getProperties().get("fail");
        String errorType = (String) exchange.getProperties().get("errorType");
        String errorMessage = (String) exchange.getProperties().get("errorMessage");

        Map<String, String> map = new LinkedHashMap<>();
        map.put("apiPath", apiPath);
        map.put("httpMethod", httpMethod);
        map.put("productType", productType);
        map.put("fail", fail);
        map.put("@timestamp", Instant.now().toString());

        if (fail.equals("true")) {
            map.put("errorType", errorType);
            map.put("errorMessage", errorMessage);
            if (product != null) {
                map.put("parameters", Map.of("product", product).toString());
            } else {
                map.put("parameters", Map.of("product", "null").toString());
            }
        }
        exchange.getIn().setBody(map);
    }
}
