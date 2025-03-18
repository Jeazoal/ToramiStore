package com.ToramiStore.ToramiStore.Adapter;

import com.ToramiStore.ToramiStore.Entity.*;
import com.ToramiStore.ToramiStore.Payloads.request.PaymentRequest;
import com.ToramiStore.ToramiStore.Payloads.request.SavefiguraRequest;
import com.ToramiStore.ToramiStore.Repository.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FiguraAdapter {
    private final CategoriaRepository categoriaRepository;
    private final FabricanteRepository fabricanteRepository;
    private final LineaRepository lineaRepository;
    private final MaterialRepository materialRepository;
    private final FranquiciaRepository franquiciaRepository;
    private final TematicaRepository tematicaRepository;
    private final EdicionRepository edicionRepository;

    public FiguraAdapter(
            CategoriaRepository categoriaRepository,
            FabricanteRepository fabricanteRepository,
            LineaRepository lineaRepository,
            MaterialRepository materialRepository,
            FranquiciaRepository franquiciaRepository,
            TematicaRepository tematicaRepository,
            EdicionRepository edicionRepository
    ) {
        this.categoriaRepository = categoriaRepository;
        this.fabricanteRepository = fabricanteRepository;
        this.lineaRepository = lineaRepository;
        this.materialRepository = materialRepository;
        this.franquiciaRepository = franquiciaRepository;
        this.tematicaRepository = tematicaRepository;
        this.edicionRepository = edicionRepository;
    }

    public Figura toEntity(SavefiguraRequest request) {
        Figura figura = new Figura();
        figura.setCodigoFigura(
                (request.getCodigoFigura() != null && !request.getCodigoFigura().isEmpty())
                        ? request.getCodigoFigura()
                        : "FIG-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase()
        );

        figura.setCodigoPedido(
                (request.getCodigoPedido() != null && !request.getCodigoPedido().isEmpty())
                        ? request.getCodigoPedido()
                        : "PED-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase()
        );

        figura.setNombreFigura(request.getNombreFigura());
        figura.setPrecio(request.getPrecio());
        figura.setCantidadInventario(request.getCantidadInventario());
        figura.setImagenUrl(request.getImagenUrl());
        figura.setDescripcion(request.getDescripcion());

        // Asignar relaciones con validación
        figura.setCategoria(
                request.getCategoriaId() != null
                        ? categoriaRepository.findById(request.getCategoriaId())
                        .orElseThrow(() -> new RuntimeException("Categoría no encontrada"))
                        : null
        );

        figura.setFabricante(
                request.getFabricanteId() != null
                        ? fabricanteRepository.findById(request.getFabricanteId())
                        .orElseThrow(() -> new RuntimeException("Fabricante no encontrado"))
                        : null
        );

        figura.setLinea(
                request.getLineaId() != null
                        ? lineaRepository.findById(request.getLineaId())
                        .orElseThrow(() -> new RuntimeException("Línea no encontrada"))
                        : null
        );

        figura.setMaterial(
                request.getMaterialId() != null
                        ? materialRepository.findById(request.getMaterialId())
                        .orElseThrow(() -> new RuntimeException("Material no encontrado"))
                        : null
        );

        figura.setFranquicia(
                request.getFranquiciaId() != null
                        ? franquiciaRepository.findById(request.getFranquiciaId())
                        .orElseThrow(() -> new RuntimeException("Franquicia no encontrada"))
                        : null
        );

        figura.setTematica(
                request.getTematicaId() != null
                        ? tematicaRepository.findById(request.getTematicaId())
                        .orElseThrow(() -> new RuntimeException("Temática no encontrada"))
                        : null
        );

        figura.setEdicion(
                request.getEdicionId() != null
                        ? edicionRepository.findById(request.getEdicionId())
                        .orElseThrow(() -> new RuntimeException("Edición no encontrada"))
                        : null
        );

        // Nuevos atributos
        figura.setPreventa(request.getPreventa());
        figura.setDestacado(request.getDestacado());
        figura.setAltura(request.getAltura());
        figura.setPeso(request.getPeso());

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
