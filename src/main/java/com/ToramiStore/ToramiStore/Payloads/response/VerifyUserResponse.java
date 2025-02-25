package com.ToramiStore.ToramiStore.Payloads.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VerifyUserResponse {
    private String message;
    private boolean success;
}
