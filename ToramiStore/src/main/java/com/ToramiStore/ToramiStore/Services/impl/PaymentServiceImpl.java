package com.ToramiStore.ToramiStore.Services.impl;

import com.ToramiStore.ToramiStore.Entity.Toy;
import com.ToramiStore.ToramiStore.Payloads.request.PaymentRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;
import java.util.List;

@Service
public class PaymentServiceImpl {

    @Value("${mercadopago.access.token}")

    private String accessToken;
    private static final String PREFERENCE_URL = "https://api.mercadopago.com/checkout/preferences";

    private final RestTemplate restTemplate = new RestTemplate();

    public String createPayment(PaymentRequest paymentRequest) {
        try {
            JSONObject requestBody = new JSONObject();
            requestBody.put("items", List.of(new JSONObject()
                    .put("title", paymentRequest.getTitle())
                    .put("quantity", paymentRequest.getQuantity())
                    .put("currency_id", "PEN")
                    .put("unit_price", paymentRequest.getPrice())
            ));

            requestBody.put("back_urls", new JSONObject()
                    .put("success", "http://localhost:8080/toramistore/payment/success")
                    .put("failure", "http://localhost:8080/toramistore/payment/failure")
                    .put("pending", "http://localhost:8080/toramistore/payment/pending")
            );
            requestBody.put("auto_return", "approved");

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(accessToken);

            HttpEntity<String> entity = new HttpEntity<>(requestBody.toString(), headers);
            ResponseEntity<String> response = restTemplate.exchange(PREFERENCE_URL, HttpMethod.POST, entity, String.class);

            JSONObject responseJson = new JSONObject(response.getBody());
            return responseJson.getString("init_point");

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
