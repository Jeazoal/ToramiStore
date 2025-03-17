package com.ToramiStore.ToramiStore.Payloads.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor  // ✅ Agrega un constructor con todos los argumentos
@NoArgsConstructor
public class PaymentRequest {
    private String title;
    private double price;
    private int quantity;
}
