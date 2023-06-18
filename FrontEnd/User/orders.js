      // Sample data - replace with your dynamic data retrieval logic
      var orders = [
      ];

      var customerId = localStorage.getItem('customerId');  
    GenerateOrdersAPI();
    function GenerateOrdersAPI(){
      customerId = localStorage.getItem('customerId');  
        var requestOptions = {
            method: 'GET'
          };
          
          fetch(`http://localhost:8088/orders/c/${customerId}`, requestOptions)
          .then(response => response.text())
          .then(result => {
            orders = JSON.parse(result);
            console.log(orders);
            GenerateOrdersDOM();
          })
            .catch(error => console.log('error', error));
    }  

    GenerateOrdersDOM();

     function GenerateOrdersDOM(){
        
        var ordersContainer = document.getElementById('ordersContainer');

        orders.forEach(function(order) {
          var orderCard = document.createElement('div');
          orderCard.classList.add('card', 'mb-3');
          
          var cardBody = document.createElement('div');
          cardBody.classList.add('card-body');
          
          var orderTitle = document.createElement('h5');
          orderTitle.classList.add('card-title');
          orderTitle.textContent = 'Order ID: ' + order.orderId;
          
          var orderStatus = document.createElement('p');
          orderStatus.classList.add('card-text');
          orderStatus.textContent = 'Status: ' + order.status;
          
          var productsTable = document.createElement('table');
          productsTable.classList.add('table');
          
          var tableHeader = document.createElement('thead');
          var headerRow = document.createElement('tr');
          headerRow.innerHTML = '<th>Product</th><th>Quantity</th><th>Price</th>';
          
          var tableBody = document.createElement('tbody');
          
          order.vegetableList.forEach(function(product) {
            console.log(product +" product")
            var productRow = document.createElement('tr');
            productRow.innerHTML = '<td>' + product.vegetableName + '</td><td>' + product.vegetableQuantity + '</td><td>' +"₹"+ (product.vegetableDtoPrice *product.vegetableQuantity ) + '</td>';
            tableBody.appendChild(productRow);
          });

          var productRow = document.createElement('tr');
          productRow.innerHTML = '<td>' + "Total" + '</td><td>'  + '</td><td>' +"₹"+ order.totalAmount + '</td>';
          tableBody.appendChild(productRow);

          const Edit = document.createElement('a');
          Edit.classList.add('btn', 'btn-primary');
          Edit.textContent = 'Pay';
          Edit.addEventListener('click', () => Transaction(order));
          
          tableHeader.appendChild(headerRow);
          productsTable.appendChild(tableHeader);
          productsTable.appendChild(tableBody);
          
          cardBody.appendChild(orderTitle);
          cardBody.appendChild(orderStatus);
          cardBody.appendChild(productsTable);

          cardBody.appendChild(Edit);

          orderCard.appendChild(cardBody);


          
          ordersContainer.appendChild(orderCard);
        });
  
     }




     function Transaction(order){
      localStorage.setItem('orderId', order.orderId)
      window.location.href = "payment.html"
     }