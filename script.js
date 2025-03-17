const inputBox = document.getElementById("input-box");
const listContainer = document.getElementById("list-container");

function addTask(){
    if(inputBox.value === ''){
        alert("You must write something!");
    }
    else{
        let li= document.createElement("li");
        li.innerHTML = inputBox.value;
        listContainer.appendChild(li);
        let span = document.createElement("span");
        span.innerHTML =  "\u00d7" ;
        li.appendChild(span);
    }
    inputBox.value ="";
    saveData();
    }
listContainer.addEventListener("click", function(e){
    if(e.target.tagName === "LI"){
        e.target.classList.toggle("checked");
        saveData();
    }
    else if(e.target.tagName === "SPAN"){
        e.target.parentElement.remove();
        saveData();
    }
}, false);

function saveData(){
    localStorage.setItem("data", listContainer.innerHTML);
}
function showTask(){
    listContainer.innerHTML = localStorage.getItem("data");
}
showTask();
function toggleForm() {
    document.getElementById("login-box").classList.toggle("hidden");
    document.getElementById("register-box").classList.toggle("hidden");
}
  // Toggle between Registration and Login forms
  function toggleToRegister() {
    document.getElementById("login-box").style.display = 'none'; // Hide login form
    document.getElementById("register-box").style.display = 'block'; // Show registration form
}

function toggleToLogin() {
    document.getElementById("register-box").style.display = 'none'; // Hide registration form
    document.getElementById("login-box").style.display = 'block'; // Show login form
}

// Handle the registration form submission (simulated)
function handleRegister(event) {
    event.preventDefault(); // Prevent the default form submission
    
    // Simulate successful registration by showing the login form
    document.getElementById("register-box").style.display = 'none';  // Hide registration box
    document.getElementById("login-box").style.display = 'block'; // Show login box
}

// Handle the login form submission (simulated)
function handleLogin(event) {
    event.preventDefault(); // Prevent the default form submission
    
    // Simulate successful login by hiding login and registration forms
    document.getElementById("login-box").style.display = 'none';  // Hide login box
    document.getElementById("todo-container").style.display = 'block'; // Show To-Do List
}

