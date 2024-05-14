package br.com.meli.onboardingchecktransparente.dto;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentDto {

  @NotNull
  private BigDecimal transactionAmount;
  @NotNull
  private String token;
  private String issuerId;
  @NotNull
  private String description;
  @NotNull
  private Integer installments;
  @NotNull
  private String paymentMethodId;
  @NotNull
  PayerDto payerDto;
}

