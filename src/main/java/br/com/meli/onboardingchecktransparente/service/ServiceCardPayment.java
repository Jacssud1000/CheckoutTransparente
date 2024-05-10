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
  String uniqueValue = UUID.randomUUID().toString();
  Map<String, String> customHeaders = new HashMap<>();
        customHeaders.put("x-idempotency-key", uniqueValue);
  MPRequestOptions requestOptions = MPRequestOptions.builder()
      .customHeaders(customHeaders)
      .build();

  public ResponsePaymentDto payment(PaymentDto paymentDto) {

    try {
      MercadoPagoConfig.setAccessToken("APP_USR-6370366279349929-050909-2e30b15123f2fa33334414d50e94d7f1-1795901418");

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

     client.create(paymentCreateRequest, requestOptions);


    } catch (MPApiException apiException) {
      System.out.println(apiException.getApiResponse().getContent());
    } catch (MPException e) {
      throw new RuntimeException(e);
    }
    return new ResponsePaymentDto();
  }
}
