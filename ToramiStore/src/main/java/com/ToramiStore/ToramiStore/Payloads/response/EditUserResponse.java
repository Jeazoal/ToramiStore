package com.ToramiStore.ToramiStore.Payloads.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditUserResponse {
    private String message;
    private Integer idUser;
}
