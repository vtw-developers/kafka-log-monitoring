package com.vtw.restkafkagrafana.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component("SelectProduct")
public class SelectProductProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        // carInspect forest srtTicket
        String productType = "srtTicket";
        exchange.setProperty("productType", productType);
        String product = (String) exchange.getIn().getHeader("product");
        exchange.setProperty("product", product);

        if (product == null || product.equals("")) {
            exchange.setProperty("fail", "true");
            exchange.setProperty("errorType", "NullPointerException");
            exchange.setProperty("errorMessage", "[선택 없음 오류] 상품이 선택되지 않았습니다.");
            exchange.getIn().setBody("[선택 없음 오류] 상품이 선택되지 않았습니다.");
            throw new RuntimeException("NotExistChoice Error");
        }
        if (!product.equals("ticketA")) {
            exchange.setProperty("fail", "true");
            exchange.setProperty("errorType", "NullPointerException");
            exchange.setProperty("errorMessage", "[상품 조회 오류] 존재하지 않는 상품입니다.");
            exchange.getIn().setBody("[상품 조회 오류] 존재하지 않는 상품입니다.");
            throw new RuntimeException("NotExistProduct Error");
        }
        exchange.setProperty("fail", "false");
        exchange.getIn().setBody(exchange.getProperties());
    }
}
