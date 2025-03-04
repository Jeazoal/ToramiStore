package com.ToramiStore.ToramiStore.Services.impl;

import com.ToramiStore.ToramiStore.Adapter.ToyAdapter;
import com.ToramiStore.ToramiStore.Entity.Toy;
import com.ToramiStore.ToramiStore.Payloads.request.SaveRequest;
import com.ToramiStore.ToramiStore.Payloads.response.SaveResponse;
import com.ToramiStore.ToramiStore.Repository.ToyRepository;
import com.ToramiStore.ToramiStore.Services.IToy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ToyServiceImpl implements IToy {

    @Autowired
    private ToyRepository toyRepository;

    @Autowired
    private ToyAdapter toyAdapter;

    @Override
    public SaveResponse save(SaveRequest request) {
        Toy toy = toyAdapter.toEntity(request);
        String codigoPedido = "PED-" + UUID.randomUUID().toString();
        toy.setCodigoPedido(codigoPedido);
        toy.setDisponible(toy.getCantidadInventario() > 0);

        Toy savedToy = toyRepository.save(toy);
        return new SaveResponse(savedToy.getDisponible(), savedToy.getCodigoPedido());
    }
}
