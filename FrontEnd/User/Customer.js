
let customerForm = document.getElementById("customerForm");
customerForm.addEventListener("submit",(e)=>{
    e.preventDefault();
    customerSubmitmeMethod();
    // alert("calling")
})


function customerSubmitmeMethod() {
    var myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");

var raw = JSON.stringify({
  "customerId": 0,
  "name": document.getElementById("name").value,
  "mobileNumber": document.getElementById("mobileNumber").value,
  "emailId": document.getElementById("emailId").value,
  "password": document.getElementById("password").value,
  "confirmPassword": document.getElementById("confirmPassword").value,
  "role": "ROLE_USER",
  "exist": true
});

var requestOptions = {
  method: 'POST',
  headers: myHeaders,
  body: raw
};

fetch("http://localhost:8088/vegiee/user/customers", requestOptions)
.then(response => {
    if (response.status == 201|response.status == 200) {
        response.json()
            .then(result => {
                console.log(result)
                alert("Customer Register Succesfully ")
                window.location.href = "login.html"
            }).catch(error => console.log('error', error));
    } else {
        response.json().then(data => {
            alert(data.message)
        });
    }
})
.catch(error => console.log('error', error));

}

















