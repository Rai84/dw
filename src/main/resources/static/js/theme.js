document.addEventListener("DOMContentLoaded", () => {
  const temaSalvo = localStorage.getItem("tema") || "dark";
  document.documentElement.classList.toggle("dark", temaSalvo === "dark");

  ativarBotaoTema();
});

function ativarBotaoTema() {
  const botao = document.getElementById("themeToggle");
  if (!botao) return;

  // Remove event listeners antigos antes de adicionar outro
  const novoBotao = botao.cloneNode(true);
  botao.parentNode.replaceChild(novoBotao, botao);

  novoBotao.addEventListener("click", () => {
    document.documentElement.classList.toggle("dark");
    const temaAtual = document.documentElement.classList.contains("dark") ? "dark" : "light";
    localStorage.setItem("tema", temaAtual);
    novoBotao.textContent = temaAtual === "dark" ? "🌙 Tema Escuro" : "☀️ Tema Claro";
  });
}
