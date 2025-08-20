# Projeto DIO: Jogo de Sudoku em Java

Este projeto consiste em uma implementação do clássico jogo de Sudoku, desenvolvido em Java. A aplicação é executada puramente no terminal, sem a necessidade de uma interface gráfica, oferecendo uma experiência interativa e funcional através de um menu de console.

O jogo foi desenvolvido como parte de um bootcamp da Digital Innovation One (DIO).

## Estrutura do Projeto

O projeto segue a arquitetura de pacotes para uma melhor organização e separação de responsabilidades.

.
└── src
└── br
└── com
└── dio
├── model
│   ├── Board.java
│   ├── GameStatusEnum.java
│   └── Space.java
├── util
│   └── BoardTemplate.java
└── Main.java


-   **`src/br/com/dio/Main.java`**: A classe principal do projeto. Contém o menu de interação via terminal e toda a lógica de controle e visualização do jogo. É o ponto de entrada da aplicação.
-   **`src/br/com/dio/model/`**: Pacote que contém as classes de modelo de dados do jogo.
    -   `Board.java`: Representa o tabuleiro do Sudoku, gerenciando a lógica do jogo, a validação de movimentos e o status.
    -   `GameStatusEnum.java`: Uma enumeração para definir os possíveis estados do jogo (ex: `COMPLETO_SEM_ERRO`, `INCOMPLETO_COM_ERRO`).
    -   `Space.java`: Representa uma única célula (espaço) no tabuleiro do Sudoku, armazenando seu valor atual e se é um valor fixo inicial.
-   **`src/br/com/dio/util/`**: Pacote que contém classes utilitárias.
    -   `BoardTemplate.java`: Uma classe que armazena o template de formatação do tabuleiro para exibição no terminal.

## Como Executar

Para rodar o projeto, você precisa ter o Java Development Kit (JDK) instalado em sua máquina.

### Via IntelliJ IDEA

1.  Abra a pasta do projeto no IntelliJ.
2.  Navegue até o arquivo `src/br/com/dio/Main.java`.
3.  Clique com o botão direito no arquivo e selecione **`Run 'Main.main()'`**.

### Via VS Code / Terminal

1.  Abra o terminal na pasta raiz do projeto.
2.  Compile todos os arquivos Java:
    ```bash
    javac src/br/com/dio/Main.java src/br/com/dio/model/*.java src/br/com/dio/util/*.java
    ```
3.  Execute a classe principal:
    ```bash
    java -cp src br.com.dio.Main
    ```

## Funcionalidades do Jogo

O menu interativo do terminal oferece as seguintes opções:

1.  **Iniciar um novo Jogo**: Reseta o tabuleiro para o estado inicial.
2.  **Colocar um novo número**: Permite que o usuário insira um número em uma célula vazia.
3.  **Remover um número**: Remove um número inserido pelo usuário.
4.  **Visualizar jogo atual**: Exibe o tabuleiro do Sudoku no terminal.
5.  **Verificar status do jogo**: Mostra se o tabuleiro está completo ou se há erros.
6.  **Limpar jogo**: Apaga todos os números inseridos pelo usuário.
7.  **Finalizar jogo**: Verifica se o jogo foi resolvido corretamente.
8.  **Sair**: Encerra a aplicação.

---

**Tecnologias:**

-   Java 11+
-   Git & GitHub

---

<div id="autor" align="center">
  
  **Criado e desenvolvido por [Poliana Santos](https://www.linkedin.com/in/polianasantoss/).**
  
 <div align="center"> 
  <a href="mailto:zpolianasantos@gmail.com"><img src="https://cdn-icons-png.flaticon.com/512/552/552486.png" height="40em" title="Enviar E-mail"></a>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <a href="https://github.com/PollySantos" target="_blank"><img src="https://cdn-icons-png.flaticon.com/512/733/733553.png" height="40em" title="GitHub de PollySantos"></a>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <a href="https://www.linkedin.com/in/polianasantoss/" target="_blank"><img src="https://cdn-icons-png.flaticon.com/512/145/145807.png" height="40em" title="LinkedIn de Poliana Santos"></a>
  </div>
</div>