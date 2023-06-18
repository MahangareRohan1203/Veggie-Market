var cartItemsData = [];
var overallPrice = 0;

GeneratedataForCart();
function GeneratedataForCart(){
    var requestOptions = {
        method: 'GET'
      };
      
      fetch("http://localhost:8088/veggieMarket/vegtables/1", requestOptions)
        .then(response => response.text())
        .then(result => {
            cartItemsData = JSON.parse(result);
            console.log(cartItemsData);
            generateCartItems();
        })
        .catch(error => console.log('error', error));
}
   
    
function generateCartItems() {
    var cartItemsContainer = document.getElementById('cart-items');
    var totalItemsElement = document.getElementById('total-items');
    var totalPriceElement = document.getElementById('total-price');

    var totalItems = 0;
    var totalPrice = 0;
    document.getElementById("cart-items").innerHTML = '';
    console.log(cartItemsData);
    // Iterate over the cart items data
    cartItemsData.forEach(function(item) {
    
        console.log("inside")
      var row = document.createElement('tr');

      var nameCell = document.createElement('td');
      nameCell.textContent = item.name;
      row.appendChild(nameCell);

      var imageCell = document.createElement('td');
      var image = document.createElement('img');
      image.src = item.image;
      image.alt = item.name;
      image.width = 100;
      image.height = 100;
      imageCell.appendChild(image);
      row.appendChild(imageCell);

      var priceCell = document.createElement('td');
      priceCell.textContent = '₹' + item.price;
      row.appendChild(priceCell);

      var quantityCell = document.createElement('td');
      var quantityInput = document.createElement('input');
      quantityInput.type = 'number';
      quantityInput.min = '1';
      quantityInput.value = item.quantity;
      quantityInput.classList.add('form-control', 'quantity-input');
      quantityInput.addEventListener('change', function() {
        updateTotal(this);
      });
      quantityCell.appendChild(quantityInput);
      row.appendChild(quantityCell);

      var totalCell = document.createElement('td');
      totalCell.textContent = '₹' + (item.price * item.quantity).toFixed(2);
      row.appendChild(totalCell);

      var actionCell = document.createElement('td');
      var removeButton = document.createElement('button');
      removeButton.classList.add('btn', 'btn-sm', 'btn-danger');
      removeButton.textContent = 'Remove';
      actionCell.appendChild(removeButton);
      row.appendChild(actionCell);

      removeButton.addEventListener("click", ()=>{
        deleteProductApi(item);
        GeneratedataForCart();
      })
      cartItemsContainer.appendChild(row);

      totalItems += item.quantity;
      totalPrice += item.price * item.quantity;
    });

    console.log("end");
    overallPrice = totalPrice;
    totalItemsElement.textContent = totalItems;
    totalPriceElement.textContent = totalPrice.toFixed(2);
  }

  // Function to update the total dynamically
  function updateTotal(input) {
    var priceCell = input.parentNode.previousElementSibling;
    var totalCell = input.parentNode.nextElementSibling;
    var price = parseFloat(priceCell.textContent.replace('₹', ''));
    var quantity = parseInt(input.value);
    var total = price * quantity;
    totalCell.textContent = '₹' + total.toFixed(2);
    var totalPriceElement = document.getElementById('total-price');
    totalPriceElement.textContent = (total + overallPrice).toFixed(2);
    
  }



  function deleteProductApi(item){
    var myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");

var raw = JSON.stringify({
  "vegetableId": item.vegetableId
});

var requestOptions = {
  method: 'DELETE',
  headers: myHeaders,
  body: raw
};

fetch("http://localhost:8088/veggieMarket/removeItem/1", requestOptions)
  .then(response => response.text())
  .then(result => console.log(result))
  .catch(error => console.log('error', error));
  }