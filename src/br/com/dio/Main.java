package br.com.dio;

import br.com.dio.model.Board;
import br.com.dio.model.GameStatusEnum;
import br.com.dio.model.Space;
import br.com.dio.util.BoardTemplate;

import java.util.InputMismatchException;
import java.util.Scanner;

import static java.util.Objects.isNull;

public class Main {
    private static Board board;
    private static final Scanner scanner = new Scanner(System.in);
    private static final int[][] INITIAL_PUZZLE = {
            {9, 5, 8, 0, 0, 0, 0, 2, 0},
            {0, 0, 0, 2, 5, 6, 0, 4, 0},
            {0, 0, 6, 0, 0, 0, 5, 1, 7},
            {6, 0, 0, 3, 7, 8, 0, 0, 0},
            {7, 8, 4, 0, 0, 0, 9, 3, 2},
            {0, 0, 0, 4, 2, 9, 0, 0, 8},
            {4, 9, 2, 0, 0, 0, 0, 1, 0},
            {0, 6, 0, 5, 8, 1, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 7, 6, 3}
    };

    private static final int BOARD_LIMIT = 9;

    public static void main(String[] args) {
        int option;
        while (true) {
            System.out.println("\n--- Jogo de Sudoku ---");
            System.out.println("Selecione uma das opções a seguir");
            System.out.println("1 - Iniciar um novo Jogo");
            System.out.println("2 - Colocar um novo número");
            System.out.println("3 - Remover um número");
            System.out.println("4 - Visualizar jogo atual");
            System.out.println("5 - Verificar status do jogo");
            System.out.println("6 - Limpar jogo");
            System.out.println("7 - Finalizar jogo");
            System.out.println("8 - Sair");

            option = readIntFromConsole("Sua opção: ");

            switch (option) {
                case 1:
                    startGame();
                    break;
                case 2:
                    inputNumber();
                    break;
                case 3:
                    removeNumber();
                    break;
                case 4:
                    showCurrentGame();
                    break;
                case 5:
                    showGameStatus();
                    break;
                case 6:
                    clearGame();
                    break;
                case 7:
                    finishGame();
                    break;
                case 8:
                    System.out.println("Saindo do jogo. Até mais!");
                    return;
                default:
                    System.out.println("Opção inválida, selecione uma das opções do menu.");
            }
        }
    }

    private static void startGame() {
        if (board != null) {
            System.out.println("Um jogo já está em andamento. Iniciando um novo jogo...");
        }
        board = new Board();
        board.initializeBoard(INITIAL_PUZZLE);
        System.out.println("O jogo foi iniciado. Tente resolvê-lo!");
        showCurrentGame();
    }

    private static void inputNumber() {
        if (isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado.");
            return;
        }

        int row = readIntFromConsole("Informe a linha (0-8): ");
        int col = readIntFromConsole("Informe a coluna (0-8): ");
        int value = readIntFromConsole("Informe o número (1-9) para a posição [" + row + "," + col + "]: ");

        if (row < 0 || row > 8 || col < 0 || col > 8 || value < 1 || value > 9) {
            System.out.println("Entrada fora do intervalo permitido. Por favor, tente novamente.");
            return;
        }

        if (!board.placeNumber(row, col, value)) {
            System.out.println("Não foi possível colocar o número. A posição pode estar preenchida, ser fixa ou o número é inválido.");
        } else {
            System.out.println("Número colocado com sucesso!");
        }
        showCurrentGame();
    }

    private static void removeNumber() {
        if (isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado.");
            return;
        }

        int row = readIntFromConsole("Informe a linha do número a ser removido (0-8): ");
        int col = readIntFromConsole("Informe a coluna do número a ser removido (0-8): ");

        if (row < 0 || row > 8 || col < 0 || col > 8) {
            System.out.println("Entrada fora do intervalo permitido. Por favor, tente novamente.");
            return;
        }

        if (!board.removeNumber(row, col)) {
            System.out.println("Não foi possível remover o número. A posição pode ser fixa.");
        } else {
            System.out.println("Número removido com sucesso.");
        }
        showCurrentGame();
    }

    private static void showCurrentGame() {
        if (isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado.");
            return;
        }
        System.out.println("Seu jogo se encontra da seguinte forma:");

        var args = new Object[BOARD_LIMIT * BOARD_LIMIT];
        var argPos = 0;
        for (int i = 0; i < BOARD_LIMIT; i++) {
            for (int j = 0; j < BOARD_LIMIT; j++) {
                Space space = board.getSpaces().get(i).get(j);
                args[argPos++] = " " + (space.getActualValue() == null ? " " : space.getActualValue());
            }
        }
        System.out.printf((BoardTemplate.BOARD_TEMPLATE) + "\n", args);
    }

    private static void showGameStatus() {
        if (isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado.");
            return;
        }
        GameStatusEnum status = board.getStatus();
        System.out.printf("O jogo atualmente se encontra no status %s\n", status.getLabel());
        if (board.hasErrors()) {
            System.out.println("O jogo contém erros.");
        } else {
            System.out.println("O jogo não contém erros.");
        }
    }

    private static void clearGame() {
        if (isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado.");
            return;
        }
        System.out.print("Tem certeza que deseja limpar seu jogo e perder todo seu progresso? (sim/nao): ");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("sim")) {
            board.clearUserNumbers();
            System.out.println("Números inseridos pelo usuário foram limpos.");
            showCurrentGame();
        } else {
            System.out.println("Ação cancelada.");
        }
    }

    private static void finishGame() {
        if (isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado.");
            return;
        }
        if (board.isFinished()) {
            System.out.println("Parabéns você concluiu o jogo com sucesso!");
            showCurrentGame();
            board = null;
        } else if (board.hasErrors()) {
            System.out.println("Seu jogo contém erros, verifique seu board e ajuste-o.");
        } else {
            System.out.println("Você ainda precisa preencher algum espaço.");
        }
    }

    private static int readIntFromConsole(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
            }
        }
    }
}