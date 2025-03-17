package com.ToramiStore.ToramiStore.Controller;

import com.ToramiStore.ToramiStore.Adapter.FiguraAdapter;
import com.ToramiStore.ToramiStore.Entity.Figura;
import com.ToramiStore.ToramiStore.Entity.Venta;
import com.ToramiStore.ToramiStore.Payloads.request.PaymentRequest;
import com.ToramiStore.ToramiStore.Repository.FiguraRepository;
import com.ToramiStore.ToramiStore.Services.impl.PaymentServiceImpl;
import com.ToramiStore.ToramiStore.Services.impl.VentaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentServiceImpl paymentService;

    @Autowired
    private FiguraAdapter FiguraAdapter;

    @Autowired
    private FiguraRepository FiguraRepository;

    @Autowired
    private VentaServiceImpl ventaService;

    public PaymentController(PaymentServiceImpl paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/create/{idfigura}")
    public ResponseEntity<String> createPayment(@PathVariable Integer idfigura, @RequestParam int quantity) {
        Optional<Figura> figuraOptional = FiguraRepository.findById(idfigura);
        if (figuraOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("El juguete no existe.");
        }

        Figura figura = figuraOptional.get();
        PaymentRequest paymentRequest = FiguraAdapter.toPaymentRequest(figura, quantity);

        // 🔹 Se corrige la llamada para enviar idfigura
        String paymentUrl = paymentService.createPayment(paymentRequest, idfigura, quantity);

        return paymentUrl != null ? ResponseEntity.ok(paymentUrl) : ResponseEntity.badRequest().body("Error al crear el pago.");
    }


    @GetMapping("/success")
    public ResponseEntity<String> paymentSuccess(
            @RequestParam("collection_id") String paymentId,
            @RequestParam("collection_status") String status,
            @RequestParam(value = "external_reference", required = false) String externalReference) {

        System.out.println("🔍 External Reference recibido: " + externalReference);

        if (externalReference == null || externalReference.isBlank()) {
            return ResponseEntity.badRequest().body("❌ Error: external_reference es nulo o vacío.");
        }

        // 🔥 Decodificar el external_reference para evitar errores
        String decodedReference = URLDecoder.decode(externalReference, StandardCharsets.UTF_8);
        System.out.println("✅ External Reference decodificado: " + decodedReference);

        // Dividir el external_reference
        String[] parts = decodedReference.split("\\|");
        if (parts.length != 2) {
            return ResponseEntity.badRequest().body("❌ Error: external_reference inválido. Recibido: " + decodedReference);
        }

        Integer idFigura, cantidad;
        try {
            idFigura = Integer.parseInt(parts[0]);
            cantidad = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("❌ Error: ID de figura o cantidad inválida.");
        }

        if (!"approved".equalsIgnoreCase(status)) {
            return ResponseEntity.badRequest().body("❌ El pago no fue aprobado.");
        }

        // Buscar figura en la BD
        Optional<Figura> figuraOptional = FiguraRepository.findById(idFigura);
        if (figuraOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("❌ La figura no existe.");
        }

        Figura figura = figuraOptional.get();

        // Registrar venta
        Venta venta = new Venta();
        venta.setFigura(figura);
        venta.setCantidad(cantidad);
        venta.setFecha(LocalDateTime.now());
        ventaService.registrarVenta(venta);

        // Reducir stock
        figura.setCantidadInventario(figura.getCantidadInventario() - cantidad);
        FiguraRepository.save(figura);

        return ResponseEntity.ok("✅ Pago exitoso. ¡Gracias por tu compra!");
    }


    @GetMapping("/failure")
    public ResponseEntity<String> paymentFailure() {
        return ResponseEntity.ok("❌ El pago ha fallado. Inténtalo nuevamente.");
    }

    @GetMapping("/pending")
    public ResponseEntity<String> paymentPending() {
        return ResponseEntity.ok("🕓 Tu pago está pendiente. Espera confirmación.");
    }
}
