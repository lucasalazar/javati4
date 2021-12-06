var saveLinkVerified = function () {
    let link = document.getElementById("link").value;
    let status = document.getElementById("status").value;
    let descricao = document.getElementById("coment").value;

    let sites = localStorage.getItem("sites");
    if (sites) {
        sitesMap = [];
        let a = JSON.parse(sites);
        for (const i of a) {
            sitesMap.push({
                site: i.site,
                avaliacao: i.avaliacao,
                comentario: i.comentario,
            });
        }

        let sites_arr = [];

        for (const i of sitesMap) {
            const { site } = i;

            sites_arr.push(site);
        }

        if (sites_arr.indexOf(link) != -1) {
            window.location.href = "./encontrada.html?link=" + link;
            alert("O site já está cadastrado.");
        } else {
            sitesMap.push({
                site: link,
                avaliacao: status,
                comentario: descricao,
            });
            localStorage.setItem("sites", JSON.stringify(sitesMap));

            alert("O site foi cadastrado com sucesso.");
            window.location.href = "./encontrada.html?link=" + link;
        }
    } else {
        sitesMap = [];

        sitesMap.push({
            site: link,
            avaliacao: status,
            comentario: descricao,
        });
        localStorage.setItem("sites", JSON.stringify(sitesMap));

        alert("O site foi cadastrado com sucesso.");
        window.location.href = "./encontrada.html?link=" + link;
    }
};
let sitesMap = [];

function verificar() {
    let link = document.getElementById("inputLink1").value || null;
    if (link) {
        let sites = localStorage.getItem("sites");
        if (sites) {
            sitesMap = [];
            let a = JSON.parse(sites);
            for (const i of a) {
                sitesMap.push({
                    site: i.site,
                    avaliacao: i.avaliacao,
                    comentario: i.descricao,
                });
            }

            let sites_arr = [];

            for (const i of sitesMap) {
                const { site } = i;

                sites_arr.push(site);
            }

            if (sites_arr.indexOf(link) != -1) {
                alert("O site foi encontrado.");
                window.location.href = "./encontrada.html?link=" + link;
            } else {
                alert("O site não foi encontrado.");
                window.location.href = "./encontrada.html?link=" + link;
            }

            // console.log(sites);
            // sitesMap = []
            // let a = JSON.parse(sites)
            // for (const i of a) {
            //     sitesMap.push({site: i.site})
        }
        // sitesMap.push({site: link})
        // localStorage.setItem('sites', JSON.stringify(sitesMap))
        else {
            window.location.href = "./encontrada.html?link=" + link;
            // sitesMap.push({
            //     site: link
            // })
            // localStorage.setItem('sites', JSON.stringify(sitesMap))
        }
        // console.log(sites);
    } else {
        alert("Você deve preencher o campo de verificação.");
    }
}

function confiavel() {
    const params = new URLSearchParams(window.location.search);
    let link = params.get("link");

    let sites = localStorage.getItem("sites");
    if (sites) {
        sitesMap = [];
        let a = JSON.parse(sites); // [{site: link, avaliacao: 1}, {site: link, avaliacao: 1}]
        for (const i of a) {
            sitesMap.push({
                site: i.site,
                avaliacao: i.avaliacao,
                comentario: i.comentario,
            });
        }

        let sites_arr = [];
        let avaliacao_arr = [];
        let comentario_arr = [];

        for (const i of sitesMap) {
            const { site, avaliacao, comentario } = i;

            sites_arr.push(site);
            avaliacao_arr.push(avaliacao);
            comentario_arr.push(comentario);
        }

        if (sites_arr.indexOf(link) != -1) {
            let avaliacao1 = parseInt(avaliacao_arr[sites_arr.indexOf(link)]);
            let new_avaliacao = avaliacao1 + 1;
            let sitesMap1 = [];
            for (const i of sitesMap) {
                let { site, avaliacao, comentario } = i;

                if (site == link) {
                    avaliacao = new_avaliacao;
                }

                sitesMap1.push({
                    site: site,
                    avaliacao: avaliacao,
                    comentario: comentario,
                });
            }
            console.log(JSON.stringify(sitesMap));
            console.log(JSON.stringify(sitesMap1));
            localStorage.setItem("sites", JSON.stringify(sitesMap1));

            window.location.href = "./encontrada.html?link=" + link;
            alert("Votação computada.");
        } else {
            alert("O site não foi encontrado.");
            // window.location.href = "./registrar.html";
        }

        // console.log(sites);
        // sitesMap = []
        // let a = JSON.parse(sites)
        // for (const i of a) {
        //     sitesMap.push({site: i.site})
    }
}

function inconfiavel() {
    const params = new URLSearchParams(window.location.search);
    let link = params.get("link");

    let sites = localStorage.getItem("sites");
    if (sites) {
        sitesMap = [];
        let a = JSON.parse(sites); // [{site: link, avaliacao: 1}, {site: link, avaliacao: 1}]
        for (const i of a) {
            sitesMap.push({
                site: i.site,
                avaliacao: i.avaliacao,
                comentario: i.comentario,
            });
        }

        let sites_arr = [];
        let avaliacao_arr = [];
        let comentario_arr = [];

        for (const i of sitesMap) {
            const { site, avaliacao, comentario } = i;

            sites_arr.push(site);
            avaliacao_arr.push(avaliacao);
            comentario_arr.push(comentario);
        }

        if (sites_arr.indexOf(link) != -1) {
            let avaliacao1 = parseInt(avaliacao_arr[sites_arr.indexOf(link)]);
            let new_avaliacao = avaliacao1 - 1;
            let sitesMap1 = [];
            for (const i of sitesMap) {
                let { site, avaliacao, comentario } = i;

                if (site == link) {
                    avaliacao = new_avaliacao;
                }

                sitesMap1.push({
                    site: site,
                    avaliacao: avaliacao,
                    comentario: comentario,
                });
            }
            console.log(JSON.stringify(sitesMap));
            console.log(JSON.stringify(sitesMap1));
            localStorage.setItem("sites", JSON.stringify(sitesMap1));

            window.location.href = "./encontrada.html?link=" + link;
            alert("Votação computada.");
        } else {
            alert("O site não foi encontrado.");
            // window.location.href = "./registrar.html";
        }

        // console.log(sites);
        // sitesMap = []
        // let a = JSON.parse(sites)
        // for (const i of a) {
        //     sitesMap.push({site: i.site})
    }
}
