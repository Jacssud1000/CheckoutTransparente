document.addEventListener("DOMContentLoaded", () => {
    const addToCartBtns = document.querySelectorAll(".add-to-cart");
    const checkoutBox = document.querySelector(".checkout-box");

    addToCartBtns.forEach(btn => {
        btn.addEventListener('click', () => {

            checkoutBox.style.display = "block";

            const productBox = document.querySelector(".checkout-box .product-details");
            const product = {
                Size: btn.parentElement.querySelector(".size").innerText.replace("Talla: ", ""),
                Brand: btn.parentElement.querySelector(".brand").innerText.replace("Marca: ", ""),
                Description: btn.parentElement.querySelector(".description").innerText.replace("Descripción: ", ""),
                Price: btn.parentElement.querySelector(".price").innerText
            };

            productBox.innerHTML = `
                <h4 class="title">Producto:</h4>
                <p class="size">Talla: ${product.Size}</p>
                <p class="brand">Marca: ${product.Brand}</p>
                <p class="description">Descripción: ${product.Description}</p>
                <p class="price">Precio: ${product.Price}</p>`;
        });
    });

    const formulario = document.querySelector("#form-checkout");

    for (let i = 1; i <= 12; i++) {
        let option = document.createElement("option");
        option.value = i;
        option.innerText = i;
        formulario.querySelector("#mes").appendChild(option); // Corregido aquí
    }

    let currentYear = new Date().getFullYear();
    for (let i = currentYear; i <= currentYear + 8; i++) {
        let option = document.createElement("option");
        option.value = i;
        option.innerText = i;
        formulario.querySelector("#year").appendChild(option);
    }
});
