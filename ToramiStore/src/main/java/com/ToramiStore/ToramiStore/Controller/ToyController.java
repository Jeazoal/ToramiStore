package com.ToramiStore.ToramiStore.Controller;

import com.ToramiStore.ToramiStore.Payloads.request.SaveRequest;
import com.ToramiStore.ToramiStore.Payloads.response.SaveResponse;
import com.ToramiStore.ToramiStore.Services.IToy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Torami")
public class ToyController {

    @Autowired
    private IToy toyservice;

    @PostMapping("/save")
    public ResponseEntity<SaveResponse> saveToy(@RequestBody SaveRequest request) {
        try {
            SaveResponse response = toyservice.save(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            String errorMessage = "Error al guardar el juguete: " + e.getMessage();
            return ResponseEntity.badRequest().body(new SaveResponse(false, errorMessage));
        }
    }

}
