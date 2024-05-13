addEventListener("DOMContentLoaded", (event) => {
    document.querySelector("#one > section > div > div > button").addEventListener('click', () => {
        const productBox = document.querySelector("#checkoutBox .inner");
        const producto = {
            ClothingSize: document.querySelector("#one > section > div > div > p.ClothingSize")
                .innerText.replace("Clothing size: ", ""),
            ClothingBrand: document.querySelector("#one > section > div > div > p.ClothingBrand")
                .innerText.replace("Clothing brand: ", ""),
            Description: document.querySelector("#one > section > div > div > p.description")
                .innerText.replace("Description: ", ""),
            Price: document.querySelector("#one > section > div > div > p.price").innerText.replace("$", "")
        };

        productBox.innerHTML = `
            <h3 class="titleProduct">Product:</h3>
            <p class="ClothingSize">Clothing size: ${producto.ClothingSize}</p>
            <p class="ClothingBrand">Clothing brand: ${producto.ClothingBrand}</p>
            <p class="description">Description: ${producto.Description}</p>
            <p class="title_price">Price: ${producto.Price}</p>
            <button class="boton">Checkout</button>`;
    });
});