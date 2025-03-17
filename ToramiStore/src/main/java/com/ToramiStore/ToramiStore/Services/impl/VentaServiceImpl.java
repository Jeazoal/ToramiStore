package com.ToramiStore.ToramiStore.Services.impl;

import com.ToramiStore.ToramiStore.Entity.Venta;
import com.ToramiStore.ToramiStore.Repository.VentaRepository;
import com.ToramiStore.ToramiStore.Services.IVenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaServiceImpl implements IVenta {

    @Autowired
    private VentaRepository ventaRepository;

    @Override
    public void registrarVenta(Venta venta) {
        ventaRepository.save(venta);
    }
}
