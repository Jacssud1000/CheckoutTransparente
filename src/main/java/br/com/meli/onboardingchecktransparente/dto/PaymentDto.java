package br.com.meli.onboardingchecktransparente.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class PaymentDto {

  private BigDecimal transactionAmount;
  private String token;
  private String description;
  private Integer installments;
  private String paymentMethodId;
  private PayerDto payerDto;
}

