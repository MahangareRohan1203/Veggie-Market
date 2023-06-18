var products = [];
var tempVegId = 0;

callProductsAPI();
    
function callProductsAPI(){
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
}


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
  
      // const vegetableId = document.createElement('p');
      // vegetableId.classList.add('card-text');
      // vegetableId.textContent = product.vegetableId;
      // vegetableId.style = "display:none"
  
      const price = document.createElement('p');
      price.classList.add('card-text');
      price.textContent = 'Price: ₹' + product.price;
  
      const Edit = document.createElement('a');
      Edit.classList.add('btn', 'btn-primary');
      Edit.textContent = 'Edit';
      Edit.addEventListener('click', () => EditProduct(product));
  
      cardBody.appendChild(title);
      cardBody.appendChild(image);
      // cardBody.appendChild(vegetableId);
      cardBody.appendChild(price);
      cardBody.appendChild(Edit);
  
      card.appendChild(cardBody);
      cardColumn.appendChild(card);
  
      productContainer.appendChild(cardColumn);
    });
  }
  // Function to add product to cart
  function EditProduct(product) {
    // Implement your logic to add the product to the cart
    console.log('Product added to cart:', product);
    var modalElement = document.getElementById("myModal2");
    var myModal = new bootstrap.Modal(modalElement);

// Show the modal
    myModal.show();
    // alert("Editing Started")
    var name = document.getElementById("name1").value = product.name;
    var image = document.getElementById("image1").value = product.image;
    var type = document.getElementById("type1").value = product.type;
    var price = document.getElementById("price1").value = product.price;
    var quantity = document.getElementById("quantity1").value = product.quantity;
    tempVegId = product.vegetableId;

  }


function updateProducts(){
  var name = document.getElementById("name1").value ;
  var image = document.getElementById("image1").value;
  var type = document.getElementById("type1").value;
  var price = document.getElementById("price1").value;
  var quantity = document.getElementById("quantity1").value;
  var vegetableId = tempVegId;

  var myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");

var raw = JSON.stringify({
  "vegetableId": vegetableId,
  "name": name,
  "image": image,
  "type": type,
  "price": price,
  "quantity": quantity
});

var requestOptions = {
  method: 'PATCH',
  headers: myHeaders,
  body: raw
};

fetch(`http://localhost:8088/vegetables/${vegetableId}`, requestOptions)
  .then(response => response.json())
  .then(result => {
    // alert(result)
  })
  .catch(error => console.log('error', error))
  .finally(() => {
    var modalElement = document.getElementById("myModal2");
    var modal = bootstrap.Modal.getInstance(modalElement);
    modal.hide();
    callProductsAPI();
    
  });


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
// applyFilters();

function AddNewProducts(){
    // alert("Added");
    console.log("New Product Added")

    var name = document.getElementById("name").value;
    var image = document.getElementById("image").value;
    var type = document.getElementById("type").value;
    var price = document.getElementById("price").value;
    var quantity = document.getElementById("quantity").value;

var myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");

var raw = JSON.stringify({
  "vegetableId": 0,
  "name": name,
  "image": image,
  "type": type,
  "price": price,
  "quantity": quantity
});

var requestOptions = {
  method: 'POST',
  headers: myHeaders,
  body: raw
};

fetch("http://localhost:8088/vegetables", requestOptions)
  .then(response => response.text())
  .then(result => console.log(result))
  .then(()=> {
    showAlert("Product Added Successfully", 3000);
  })
  .catch(error => {
    showAlert(error, 3000);
  })
  .finally(() => {
    var modalElement = document.getElementById("myModal");
    var modal = bootstrap.Modal.getInstance(modalElement);
    modal.hide();
  });



}


function showAlert(message, duration) {
    var alertContainer = document.getElementById("alertContainer");
    var alertHTML = `
      <div class="alert alert-success" role="alert">
        ${message}
      </div>
    `;
    alertContainer.innerHTML = alertHTML;
  
    setTimeout(function() {
      alertContainer.innerHTML = "";
    }, duration);
  }
  