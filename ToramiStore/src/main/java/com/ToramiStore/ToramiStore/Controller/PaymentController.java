package com.ToramiStore.ToramiStore.Controller;

import com.ToramiStore.ToramiStore.Adapter.FiguraAdapter;
import com.ToramiStore.ToramiStore.Entity.Figura;
import com.ToramiStore.ToramiStore.Payloads.request.PaymentRequest;
import com.ToramiStore.ToramiStore.Repository.FiguraRepository;
import com.ToramiStore.ToramiStore.Services.impl.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        String paymentUrl = paymentService.createPayment(paymentRequest);

        return paymentUrl != null ? ResponseEntity.ok(paymentUrl) : ResponseEntity.badRequest().body("Error al crear el pago.");
    }



    @GetMapping("/success")
    public ResponseEntity<String> paymentSuccess() {
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
