var products = [];

var requestOptions = {
    method: 'GET',
  };
  
  fetch("http://localhost:8088/vegetables/all", requestOptions)
    .then(response => response.json())
    .then(result =>{
        console.log(result);
        products = result;
        generateProductCards();
    })
    .catch(error => console.log('error', error))
    
  
  // Function to generate product cards
  function generateProductCards() {
    const productContainer = document.getElementById('product-list');
    productContainer.innerHTML = '';
  
    products.forEach((product) => {
        
      const cardColumn = document.createElement('div');
      cardColumn.classList.add('col-md-4');
  
      const card = document.createElement('div');
      card.classList.add('card');
  
      const cardBody = document.createElement('div');
      cardBody.classList.add('card-body');
  
      const image = document.createElement('img');
      image.src = product.image;
      image.style.width = '100%';

      const title = document.createElement('h5');
      title.classList.add('card-title');
      title.textContent = product.name;
  
    //   const description = document.createElement('p');
    //   description.classList.add('card-text');
    //   description.textContent = product.description;
  
      const price = document.createElement('p');
      price.classList.add('card-text');
      price.textContent = 'Price: ₹' + product.price;
  
      const addToCartBtn = document.createElement('a');
      addToCartBtn.classList.add('btn', 'btn-primary');
      addToCartBtn.textContent = 'Add to Cart';
      addToCartBtn.addEventListener('click', () => addToCart(product));
  
      cardBody.appendChild(title);
      cardBody.appendChild(image);
    //   cardBody.appendChild(description);
      cardBody.appendChild(price);
      cardBody.appendChild(addToCartBtn);
  
      card.appendChild(cardBody);
      cardColumn.appendChild(card);
  
      productContainer.appendChild(cardColumn);
    });
  }
  
  // Function to add product to cart
  function addToCart(product) {
    // Implement your logic to add the product to the cart
    console.log('Product added to cart:', product);
  }
  
// Generating Filters
function applyFilters() {
  // Get the selected filters
  const filter1Checked = document.getElementById("filter1").checked;
  const filter2Checked = document.getElementById("filter2").checked;

  // Get the container for vegetable cards
  const vegetableCardsContainer = document.getElementById("vegetableCards");

  // Clear the existing vegetable cards
  vegetableCardsContainer.innerHTML = "";

  // Array of vegetable data (dummy data for demonstration)
  const vegetables = [
    { name: "Carrot", filter1: true, filter2: false },
    { name: "Broccoli", filter1: false, filter2: true },
    { name: "Tomato", filter1: true, filter2: true },
  ];

  // Filter and display the vegetable cards
  const filteredVegetables = vegetables.filter(vegetable => {
    return (!filter1Checked || vegetable.filter1) && (!filter2Checked || vegetable.filter2);
  });

  filteredVegetables.forEach(vegetable => {
    const cardHtml = `
      <div class="col-md-4">
        <div class="card">
          <div class="card-body">
            <h5 class="card-title">${vegetable.name}</h5>
          </div>
        </div>
      </div>
    `;

    vegetableCardsContainer.innerHTML += cardHtml;
  });
}

// Initial call to apply filters
applyFilters();
  