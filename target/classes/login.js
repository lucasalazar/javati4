async function loginUser() {
    var email = document
        .getElementById("username")
        .value.replace("'", "")
        .trim();
    var senha = document.getElementById("password").value.trim();
    await fetch("http://localhost:5000/login/" + email + "/" + senha, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then((res) => res.json)
        .then((data) => {
            if (data != -1) {
                var logado = {
                    id: data,
                    email: email,
                    senha: senha,
                };
                alert("Sucesso ao realizar login");
                sessionStorage.setItem("User", JSON.stringify(logado));
                window.location.href = "home.html";
            } else {
                alert("Dados incorretos");
            }
        })
        .catch((err) => {
            alert("erro=" + err.message);
        });
}
