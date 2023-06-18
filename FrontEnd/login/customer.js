
let customerForm = document.getElementById("customerForm");
customerForm.addEventListener("submit",(e)=>{
    e.preventDefault();
    customerSubmitmeMethod();
})
function customerSubmitmeMethod() {

    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    myHeaders.append("Authorization", "Basic cmFtQGdtYWlsLmNvbToxMjM0");

    var raw = JSON.stringify({
       
        "name": document.getElementById("name").value,
        "mobileNumber": document.getElementById("mobileNumber").value,
        "emailId": document.getElementById("emailId").value,
        "password": document.getElementById("password").value,
        "confirmPassword": document.getElementById("confirmPassword").value,
        "role": document.getElementById("role").value,
        "exist": true
    });

    var requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: raw,
        redirect: 'follow'
    };

    fetch("http://localhost:8088/vegiee/user/customers", requestOptions)
    .then(response => {
        if (response.status == 201|response.status == 200) {
            response.json()
                .then(result => {
                    console.log(result)
                    alert("Customer Register Succesfully with Register Id :"+result.customerId)

                }).catch(error => console.log('error', error));
        } else {
            response.json().then(data => {
                alert(data.message)
            });
        }
    });
}

