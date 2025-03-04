package com.ToramiStore.ToramiStore.Adapter;

import com.ToramiStore.ToramiStore.Entity.Toy;
import com.ToramiStore.ToramiStore.Payloads.request.PaymentRequest;
import com.ToramiStore.ToramiStore.Payloads.request.SaveRequest;
import org.springframework.stereotype.Component;

@Component
public class ToyAdapter {

    public Toy toEntity(SaveRequest request){
        Toy toy = new Toy();
        toy.setNombreFigura(request.getNombreFigura());
        toy.setPrecio(request.getPrecio());
        toy.setCantidadInventario(request.getCantidadInventario());
        toy.setLinea(request.getLinea());
        toy.setFabricante(request.getFabricante());
        toy.setAltura(request.getAltura());
        toy.setDistribuidor(request.getDistribuidor());
        toy.setRutaImagen(request.getRutaImagen());
        return toy;
    }

    public PaymentRequest toPaymentRequest(Toy toy, int cantidad) {
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setTitle(toy.getNombreFigura());
        paymentRequest.setPrice(toy.getPrecio());
        paymentRequest.setQuantity(cantidad); 
        return paymentRequest;
    }



}
