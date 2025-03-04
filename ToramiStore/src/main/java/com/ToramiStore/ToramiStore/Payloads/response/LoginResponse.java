package com.ToramiStore.ToramiStore.Payloads.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private Integer idUser;
    private String message;
}
