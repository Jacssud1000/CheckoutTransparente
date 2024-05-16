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
});
