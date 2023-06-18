
let userLoginfrom = document.getElementById("loginform");
userLoginfrom.addEventListener("submit", (e) => {
    e.preventDefault();
    loginUserFunction();
})


function loginUserFunction() {

    var myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");

var raw = JSON.stringify({
    "userName": document.getElementById("emailId").value,
    "password": document.getElementById("password").value,
    "role": document.getElementById("role").value,
    "userId": 0
});

console.log(raw+" => raw");
var requestOptions = {
  method: 'POST',
  headers: myHeaders,
  body: raw
};

console.log(requestOptions);
fetch("http://localhost:8088/veggieMarket/login", requestOptions)
.then(response => {
    if (response.status == 201|response.status == 200) {
        response.json()
            .then(result => {
                console.log(result)
                alert("Login Successful")
                saveDetailsOnLocalStorge(result);
                if(result.role == "ROLE_ADMIN"){
                    window.location.href = "adminVegetables.html"
                }else{
                    window.location.href = "../User/Vegetables.html"
                }
            }).catch(error => console.log('error', error));
    } else {
        response.json().then(data => {
            alert(data.message)
        });
    }
});

}


function saveDetailsOnLocalStorge(result){
    // Storing data in localStorage
localStorage.setItem('customerId', result.userId);

// Retrieving data from localStorage
const customerId = localStorage.getItem('customerId');

}
