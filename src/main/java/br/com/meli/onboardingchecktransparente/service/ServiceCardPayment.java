package br.com.meli.onboardingchecktransparente.service;

import br.com.meli.onboardingchecktransparente.dto.PaymentDto;
import br.com.meli.onboardingchecktransparente.dto.ResponsePaymentDto;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.core.MPRequestOptions;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ServiceCardPayment {

  public ResponsePaymentDto payment(PaymentDto paymentDto) {
    try {
      Map<String, String> customHeaders = new HashMap<>();

      customHeaders.put("x-idempotency-key", UUID.randomUUID().toString());

      MPRequestOptions requestOptions = MPRequestOptions.builder()
          .customHeaders(customHeaders)
          .accessToken("APP_USR-5879859661431869-050816-63fe2574c50c3369bae624b038a5c0c4-1259153998")
          .build();

      MercadoPagoConfig.setAccessToken("APP_USR-5879859661431869-050816-63fe2574c50c3369bae624b038a5c0c4-1259153998");

      PaymentClient client = new PaymentClient();

      PaymentCreateRequest paymentCreateRequest =
          PaymentCreateRequest.builder()
              .transactionAmount(paymentDto.getTransactionAmount())
              .token(paymentDto.getToken())
              .description(paymentDto.getDescription())
              .installments(paymentDto.getInstallments())
              .paymentMethodId(paymentDto.getPaymentMethodId())
              .payer(
                  PaymentPayerRequest.builder()
                      .email(paymentDto.getPayerDto().getEmail())
                      .firstName(paymentDto.getPayerDto().getName())
                      .identification(
                          IdentificationRequest.builder()
                              .type(paymentDto.getPayerDto().getIdentification().getType())
                              .number(paymentDto.getPayerDto().getIdentification().getNumber())
                              .build())
                      .build())
              .build();

      Payment createdPayment = client.create(paymentCreateRequest, requestOptions);

      return new ResponsePaymentDto(
          createdPayment.getId(),
          createdPayment.getStatus(),
          createdPayment.getStatusDetail()
      );

    } catch (MPApiException apiException) {
      System.out.println(apiException.getApiResponse().getContent());
      return new ResponsePaymentDto(1L, "error", "error");
    } catch (MPException e) {
      throw new RuntimeException(e);
    }
  }
}
