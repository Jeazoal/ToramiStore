package com.ToramiStore.ToramiStore.Controller;

import com.ToramiStore.ToramiStore.Adapter.ToyAdapter;
import com.ToramiStore.ToramiStore.Entity.Toy;
import com.ToramiStore.ToramiStore.Payloads.request.PaymentRequest;
import com.ToramiStore.ToramiStore.Repository.ToyRepository;
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
    private ToyAdapter toyAdapter;

    @Autowired
    private ToyRepository toyRepository;


    public PaymentController(PaymentServiceImpl paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/create/{idToy}")
    public ResponseEntity<String> createPayment(@PathVariable Integer idToy, @RequestParam int quantity) {
        Optional<Toy> toyOptional = toyRepository.findById(idToy);
        if (toyOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("El juguete no existe.");
        }

        Toy toy = toyOptional.get();
        PaymentRequest paymentRequest = toyAdapter.toPaymentRequest(toy, quantity);
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
