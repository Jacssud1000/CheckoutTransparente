package br.com.meli.onboardingchecktransparente.controller;

import br.com.meli.onboardingchecktransparente.dto.PaymentDto;
import br.com.meli.onboardingchecktransparente.dto.ResponsePaymentDto;
import br.com.meli.onboardingchecktransparente.service.ServiceCardPayment;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ControllerCardPayment {

  @Autowired
  private ServiceCardPayment serviceCardPayment;

  @PostMapping
  private ResponseEntity<ResponsePaymentDto> paymentRequest(@RequestBody @Valid PaymentDto paymentDto){
  ResponsePaymentDto payment = serviceCardPayment.payment(paymentDto);
  return ResponseEntity.status(HttpStatus.OK).body(payment);
  }
}