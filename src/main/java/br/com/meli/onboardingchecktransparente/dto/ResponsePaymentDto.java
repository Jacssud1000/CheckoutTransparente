package br.com.meli.onboardingchecktransparente.dto;

import lombok.Data;

@Data
public class ResponsePaymentDto {
    private Long id;
    private String status;
    private String detail;

    public ResponsePaymentDto(Long id, String status, String detail) {
        this.id = id;
        this.detail = status;
        this.detail = detail;
    }
}
