const mp = new window.MercadoPago("APP_USR-7476b225-c07f-4be5-9f49-9a3a53869e70");

const cardForm = mp.cardForm({
    amount: "100",
    iframe: true,
    form: {
        id: "form-checkout",
        cardholderName: {
            id: "form-checkout__cardholderName",
            placeholder: "Holder name",
        },
        cardholderEmail: {
            id: "form-checkout__cardholderEmail",
            placeholder: "E-mail",
        },
        cardNumber: {
            id: "form-checkout__cardNumber",
            placeholder: "Card number",
        },
        expirationDate: {
            id: "form-checkout__expirationDate",
            placeholder: "MM/YYYY",
        },
        securityCode: {
            id: "form-checkout__securityCode",
            placeholder: "Security code",
        },
        installments: {
            id: "form-checkout__installments",
            placeholder: "Installments",
        },
        identificationType: {
            id: "form-checkout__identificationType",
        },
        identificationNumber: {
            id: "form-checkout__identificationNumber",
            placeholder: "Identification number",
        },
        issuer: {
            id: "form-checkout__issuer",
            placeholder: "Issuer",
        },
    },
    callbacks: {
        onFormMounted: error => {
            if (error) return console.warn("Form Mounted handling error: ", error);
            console.log("Form mounted");
        },
        onSubmit: event => {
            event.preventDefault();

            const cardholderNameInput = document.getElementById('form-checkout__cardholderName');
            const name = cardholderNameInput.value;

            const {
                paymentMethodId: payment_method_id,
                issuerId: issuer_id,
                cardholderEmail: email,
                amount,
                token,
                installments,
                identificationNumber,
                identificationType,
            } = cardForm.getCardFormData();

            fetch("/payments", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    token,
                    issuer_id,
                    payment_method_id,
                    transaction_amount: Number(amount),
                    installments: Number(installments),
                    description: "Descrição do produto",
                    payer: {
                        name,
                        email,
                        identification: {
                            type: identificationType,
                            number: identificationNumber,
                        },
                    },
                }),
            });
        },
        onFetching: (resource) => {
            console.log("Fetching resource: ", resource);

            // Animate progress bar
            const progressBar = document.querySelector(".progress-bar");
            progressBar.removeAttribute("value");

            return () => {
                progressBar.setAttribute("value", "0");
            };
        }
    },
});