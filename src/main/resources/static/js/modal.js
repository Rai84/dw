function toggleModal(modalId) {
    const container = document.getElementById("modais");
    const modalAtual = document.getElementById(modalId);

    // Se já estiver visível, fecha
    if (modalAtual && !modalAtual.classList.contains("hidden")) {
        modalAtual.classList.add("hidden");
        container.classList.add("hidden");
        atualizarBotaoEstado(modalId, false);
        return;
    }

    // Remove modal antigo se já existir
    const existente = document.getElementById(modalId);
    if (existente) existente.remove();

    // Carrega novo modal
    fetch(`/fragments/modais/${modalId}.html?ts=${Date.now()}`)
        .then(res => res.text())
        .then(html => {
            container.insertAdjacentHTML("beforeend", html);
            const novoModal = container.querySelector(`#${modalId}`);
            if (novoModal) {
                novoModal.classList.remove("hidden");
                container.classList.remove("hidden");
                ativarBotaoTema?.(); // se existir
                atualizarBotaoEstado(modalId, true);
            }
        })
        .catch(err => console.error(`Erro ao carregar o modal ${modalId}:`, err));
}

function fecharModal(modalId) {
    const modal = document.getElementById(modalId);
    const container = document.getElementById("modais");

    if (modal) {
        modal.classList.add("hidden");
        container.classList.add("hidden");
        atualizarBotaoEstado(modalId, false);
    }
}

function atualizarBotaoEstado(modalId, ativo) {
    // MODAL 1
    if (modalId === "modal1") {
        const divBotao1 = document.querySelector(".row-start-2.row-end-3");
        const icone1 = divBotao1?.querySelector("i");

        if (ativo) {
            divBotao1?.classList.replace("bg-stone-700", "bg-orange-600");
            icone1?.classList.replace("bi-chevron-compact-right", "bi-chevron-compact-left");
        } else {
            divBotao1?.classList.replace("bg-orange-600", "bg-stone-700");
            icone1?.classList.replace("bi-chevron-compact-left", "bi-chevron-compact-right");
        }
    }

    // MODAL 2
    if (modalId === "modal2") {
        const icone2 = document.getElementById("iconModal2");
        if (!icone2) {
            console.warn("❌ Ícone do modal 2 não encontrado!");
            return;
        }

        if (ativo) {
            icone2.classList.remove("text-white");
            icone2.classList.add("text-orange-600");
            icone2.style.color = "#ea580c";
            console.log("✅ Modal 2 aberto — cor aplicada");
        } else {
            icone2.classList.remove("text-orange-600");
            icone2.classList.add("text-white");
            icone2.style.color = "";
            console.log("🔁 Modal 2 fechado — cor resetada");
        }
    }
}

// Opcional: fecha modal ao clicar fora dele
document.addEventListener("click", function (e) {
    const container = document.getElementById("modais");
    if (!container || container.classList.contains("hidden")) return;

    const modalAberto = container.querySelector("div:not(.hidden)");
    if (modalAberto && !modalAberto.contains(e.target)) {
        modalAberto.classList.add("hidden");
        container.classList.add("hidden");
        atualizarBotaoEstado(modalAberto.id, false);
    }
});
