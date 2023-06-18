
function payment(){
    
    var totalAmount = document.getElementById("mrp").value;
    var address = {};
    let flatNo = document.getElementById("flatNo").value;
    let buildingName = document.getElementById("buildingName").value;
    let area = document.getElementById("area").value;
    let city = document.getElementById("city").value;
    let pincode = document.getElementById("pincode").value;
    let state = document.getElementById("state").value;

    address = {
      "flatNo" : flatNo,
      "buildingName": buildingName,
      "area": area,
      "city": city,
      "pincode": pincode,
      "state": state
    }

    var myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");

var raw = JSON.stringify({
  "billingId": 0,
  "totalAmount": totalAmount,
  "transactionMode": "CASH_ON_DELIVERY",
  "transactionDateTime": "2023-06-18T05:06:57.107Z",
  "address": address
});

var requestOptions = {
  method: 'POST',
  headers: myHeaders,
  body: raw
};

var customerId = localStorage.getItem('customerId');
var orderId = localStorage.getItem('orderId')

fetch(`http://localhost:8088/bill-detail/${customerId}/${orderId}`, requestOptions)
.then(response => {
    if (response.status == 201|response.status == 200) {
        response.json()
            .then(result => {
                console.log(result)
                alert("Transaction done successfully")
                window.location.href = "Vegetables.html"
            }).catch(error => console.log('error', error));
    } else {
        response.json().then(data => {
            alert(data.message)
        });
    }
})
  .catch(error => console.log('error', error));
  
}