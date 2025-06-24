# Projeto Paciência em Java

Este projeto consiste na implementação do clássico jogo de Paciência (versão Klondike) em linguagem Java, desenvolvido sem o uso de estruturas de dados prontas da API Java (como `ArrayList`, `List`, `Stack`, `Queue`, etc.). Todas as estruturas de dados necessárias foram implementadas do zero, utilizando conceitos de Orientação a Objetos.

## 🚀 Objetivo do Projeto

O principal objetivo é desenvolver uma aplicação que simule o jogo de Paciência, focando na aplicação profunda dos conceitos de Orientação a Objetos (abstração, encapsulamento, herança e polimorfismo) e na implementação manual de Estruturas de Dados.

## ✨ Funcionalidades

* **Jogo de Paciência Clássico:** Simula as regras e a dinâmica do Paciência tradicional.
* **Baralho Completo:** Geração de um baralho de 52 cartas únicas.
* **Embaralhamento:** Função para embaralhar aleatoriamente as cartas.
* **Estruturas de Dados Personalizadas:**
    * **Fila (Queue) Circular:** Para o monte de compra de cartas.
    * **Pilhas (Stacks):** Para as pilhas finais (bases) onde as cartas são organizadas por naipe e para as cartas viradas nas colunas.
    * **Listas Ligadas (Linked Lists) / Duplamente Ligadas:** Para representar as sete colunas do jogo na mesa.
* **Regras de Movimentação:** Implementação de todas as regras de movimentação de cartas (ordem decrescente/alternância de cores nas colunas, ordem crescente/mesmo naipe nas pilhas finais, movimentação de sequências, restrição de Rei em espaços vazios).
* **Interface em Modo Texto:** Toda a interação e exibição do estado do jogo são realizadas via console.
* **Menu de Interação:** Menus claros para as ações do jogo (movimentar cartas, reiniciar, ver estado).

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java
* **Ambiente:** Java Development Kit (JDK) 8 ou superior

## 📂 Estrutura do Projeto

A organização do código segue uma estrutura modular para separar as responsabilidades e facilitar o desenvolvimento:

Paciencia/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/seuprojeto/paciencia/
│   │   │               ├── model/         // Classes para modelagem de entidades (ex: Carta, Naipe)
│   │   │               ├── structures/    // Implementações personalizadas de estruturas de dados (Fila, Pilha, ListaLigada, No)
│   │   │               ├── game/          // Lógica principal do jogo (Baralho, JogoPaciencia, RegrasJogo)
│   │   │               ├── ui/            // Classes para a interface de usuário em modo texto (MenuPrincipal, DisplayJogo)
│   │   │               └── Main.java      // Ponto de entrada da aplicação
│   └── test/              // Testes unitários para validar as implementações
├── .gitignore             // Arquivo para ignorar arquivos e pastas no controle de versão Git
├── README.md              // Este arquivo de documentação





## ⚙️ Como Compilar e Executar

Para compilar e executar o projeto, siga os passos abaixo:

1.  **Clone o repositório** (se estiver usando Git):
    ```bash
    git clone(https://github.com/RaulFerna/Paciencia2.git)
    cd Paciencia2
    ```

2.  **Execute a aplicação:**
    Após a compilação, você pode executar o jogo a partir da pasta `Paciencia/` usando:
    ```bash
    java -cp bin src.main.java.com.paciencia.game.Main.java
    ```
    

## 🤝 Contribuição

Este projeto foi desenvolvido como parte de um exercício acadêmico. Contribuições, sugestões e melhorias são sempre bem-vindas!
