package br.com.meli.onboardingchecktransparente.dto;

import lombok.Data;

@Data
public class PayerDto {
  private String Name;
  private String Email;
  private Identification identification;

}
