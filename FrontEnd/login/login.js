
let userLoginfrom = document.getElementById("loginform");
userLoginfrom.addEventListener("submit", (e) => {
    e.preventDefault();
    loginUserFunction();
})

function loginUserFunction() {

    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    myHeaders.append("Authorization", "Basic cmFtQGdtYWlsLmNvbToxMjM0");

    var raw = JSON.stringify({

        "emailId": document.getElementById("emailId").value,
        "password": document.getElementById("password").value,
        "role": document.getElementById("role").value

    });

    var requestOptions = {
        method: 'GET',
        headers: myHeaders,
        body: raw,
        redirect: 'follow'
    };

    fetch("http://localhost:8088/login", requestOptions)
        .then(response => response.text())
        .then(result => console.log(result))
        .catch(error => console.log('error', error));
}
