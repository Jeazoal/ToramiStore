package com.ToramiStore.ToramiStore.Adapter;

import com.ToramiStore.ToramiStore.Entity.Figura;
import com.ToramiStore.ToramiStore.Payloads.request.PaymentRequest;
import com.ToramiStore.ToramiStore.Payloads.request.SaveRequest;
import org.springframework.stereotype.Component;

@Component
public class FiguraAdapter {

    public Figura toEntity(SaveRequest request){
        Figura figura = new Figura();
        figura.setNombreFigura(request.getNombreFigura());
        figura.setPrecio(request.getPrecio());
        figura.setCantidadInventario(request.getCantidadInventario());
        figura.setImagenUrl(request.getImagenUrl());
        figura.setDescripcion(request.getDescripcion());

        // Relaciones con otras entidades (llaves foráneas)
        figura.setCategoria(request.getCategoria());
        figura.setFabricante(request.getFabricante());
        figura.setMarca(request.getMarca());
        figura.setMaterial(request.getMaterial());
        figura.setFranquicia(request.getFranquicia());
        figura.setTematica(request.getTematica());
        figura.setEdicion(request.getEdicion());

        // Nuevas propiedades agregadas
        figura.setPreventa(request.getPreventa());
        figura.setDestacado(request.getDestacado());

        return figura;
    }

    // No Tocar
    public PaymentRequest toPaymentRequest(Figura figura, int cantidad) {
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setTitle(figura.getNombreFigura());
        paymentRequest.setPrice(figura.getPrecio());
        paymentRequest.setQuantity(cantidad);
        return paymentRequest;
    }
}
