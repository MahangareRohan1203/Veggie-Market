var cartItemsData = [];
var overallPrice = 0;
var customerId = localStorage.getItem('customerId');

GeneratedataForCart();
function GeneratedataForCart(){
    var requestOptions = {
        method: 'GET'
      };
      
      fetch(`http://localhost:8088/veggieMarket/vegtables/${customerId}`, requestOptions)
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
        updateTotal(this, quantityInput.value);
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
  // function updateTotal(input, item) {
  //   var priceCell = input.parentNode.previousElementSibling;
  //   var totalCell = input.parentNode.nextElementSibling;
  //   var price = parseFloat(priceCell.textContent.replace('₹', ''));
  //   var quantity = parseInt(input.value);
    
  //   item.quantity = quantity;
  //   var total = price * quantity;
  //   totalCell.textContent = '₹' + total.toFixed(2);
  //   var totalPriceElement = document.getElementById('total-price');
  //   totalPriceElement.textContent = (total).toFixed(2);
    
  // }
// Update the `updateTotal` function with the changes below:
function updateTotal(item, newQuantity) {
  var priceCell = item.parentElement.previousSibling;
  var totalCell = item.parentElement.nextSibling;

  var price = parseFloat(priceCell.textContent.substring(1)); // Remove the '₹' symbol
  var total = price * newQuantity;

  totalCell.textContent = '₹' + total.toFixed(2); 
  // Update the cartItemsData with the new quantity
  item.quantity = newQuantity;

  // Recalculate the overall total
  var cartItems = document.getElementsByClassName('quantity-input');
  var overallTotal = 0;

  for (var i = 0; i < cartItems.length; i++) {
    var currentItem = cartItems[i];
    var currentPriceCell = currentItem.parentElement.previousSibling;
    var currentTotalCell = currentItem.parentElement.nextSibling;
    var currentItemPrice = parseFloat(currentPriceCell.textContent.substring(1)); // Remove the '₹' symbol
    var currentItemQuantity = parseInt(currentItem.value);
    var currentItemTotal = currentItemPrice * currentItemQuantity;
    overallTotal += currentItemTotal;


    if(currentItem == item){
      cartItemsData[i].quantity = newQuantity;
    }

  }

  overallPrice = overallTotal;
  document.getElementById('total-price').textContent = overallTotal.toFixed(2)
  console.log(cartItemsData)
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

customerId = localStorage.getItem('customerId');

fetch(`http://localhost:8088/veggieMarket/removeItem/${customerId}`, requestOptions)
  .then(response => {
    if(response.status == 200 || response.status == 201){
      alert("Product removed successfully");
      GeneratedataForCart();
    }else{
      alert(response.json().message);
    }
  })
  .then(result => console.log(result))
  .catch(error => console.log('error', error));
  }




  function placeOrder(){
    // alert("ordering");
    console.log(cartItemsData);
    postOrderAPI(cartItemsData);

  }



  function postOrderAPI(cartItemsData){
    vegetableList = [];
    let totalPrice = 0;

    console.log("=>" + cartItemsData);
    cartItemsData.forEach(a=>{
      let x = {
      "vegetableDtoId": a.vegetableId,
      "vegetableName": a.name,
      "vegetableQuantity" : a.quantity,
      "vegetableDtoPrice" : a.price,
      "vegetableDtoType" : a.type
      }
      totalPrice += a.price * a.quantity;
      vegetableList.push(x);
    })
    var myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");

var raw = JSON.stringify({
  "orderId": 0,
  vegetableList,
  "totalAmount": totalPrice
});

var requestOptions = {
  method: 'POST',
  headers: myHeaders,
  body: raw
};

customerId = localStorage.getItem('customerId');

fetch(`http://localhost:8088/orders/${customerId}`, requestOptions)
  .then(response => {
    if (response.status == 201|response.status == 200) {
      response.json()
          .then(result => {
              console.log(result)
              alert("Order Created Successfully")
              cartItemsData = [];
              generateCartItems();
              window.location.href = "Orders.html"
          }).catch(error => console.log('error', error));
  } else {
      response.json().then(data => {
          alert(data.message)
      });
  }
  })
  .catch(error => console.log('error', error));
  }