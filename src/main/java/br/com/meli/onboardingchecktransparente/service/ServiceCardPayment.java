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
import org.springframework.stereotype.Service;

@Service
public class ServiceCardPayment {

  public ResponsePaymentDto payment(PaymentDto paymentDto) {
    try {
      Map<String, String> customHeaders = new HashMap<>();

      customHeaders.put("x-idempotency-key", "0d5020ed-1af6-469c-ae06-c3bec19954bb");

      MPRequestOptions requestOptions = MPRequestOptions.builder()
          .customHeaders(customHeaders)
          .build();

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
