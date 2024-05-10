package br.com.meli.onboardingchecktransparente.dto;

import lombok.Data;

@Data
public class ResponsePaymentDto {
    private Long id;
    private String status;
    private String detail;

}
