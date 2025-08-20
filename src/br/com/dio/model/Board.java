package br.com.dio.model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private static final int GRID_SIZE = 9;
    private final List<List<Space>> spaces;

    public Board() {
        this.spaces = new ArrayList<>();
        for (int i = 0; i < GRID_SIZE; i++) {
            List<Space> row = new ArrayList<>();
            for (int j = 0; j < GRID_SIZE; j++) {
                row.add(new Space(null));
            }
            this.spaces.add(row);
        }
    }

    public void initializeBoard(int[][] initialData) {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (initialData[i][j] != 0) {
                    this.spaces.get(i).set(j, new Space(initialData[i][j]));
                }
            }
        }
    }

    public boolean placeNumber(int row, int col, int number) {
        Space space = spaces.get(row).get(col);
        if (space.isFixed() || space.getActualValue() != null) {
            return false;
        }
        if (isValidPlacement(row, col, number)) {
            space.setActualValue(number);
            return true;
        }
        return false;
    }

    public boolean removeNumber(int row, int col) {
        Space space = spaces.get(row).get(col);
        if (space.isFixed()) {
            return false;
        }
        space.clear();
        return true;
    }

    public void clearUserNumbers() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (!spaces.get(i).get(j).isFixed()) {
                    spaces.get(i).get(j).clear();
                }
            }
        }
    }

    public boolean isValidPlacement(int row, int col, int number) {
        // Check row
        for (int i = 0; i < GRID_SIZE; i++) {
            if (spaces.get(row).get(i).getActualValue() != null && spaces.get(row).get(i).getActualValue() == number) {
                return false;
            }
        }
        // Check column
        for (int i = 0; i < GRID_SIZE; i++) {
            if (spaces.get(i).get(col).getActualValue() != null && spaces.get(i).get(col).getActualValue() == number) {
                return false;
            }
        }
        // Check 3x3 sub-grid
        int subGridRowStart = row - row % 3;
        int subGridColStart = col - col % 3;
        for (int i = subGridRowStart; i < subGridRowStart + 3; i++) {
            for (int j = subGridColStart; j < subGridColStart + 3; j++) {
                if (spaces.get(i).get(j).getActualValue() != null && spaces.get(i).get(j).getActualValue() == number) {
                    return false;
                }
            }
        }
        return true;
    }

    public GameStatusEnum getStatus() {
        boolean isComplete = true;
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (spaces.get(i).get(j).getActualValue() == null) {
                    isComplete = false;
                    break;
                }
            }
            if (!isComplete) break;
        }

        if (isComplete) {
            return hasErrors() ? GameStatusEnum.COMPLETO_COM_ERRO : GameStatusEnum.COMPLETO_SEM_ERRO;
        } else {
            return hasErrors() ? GameStatusEnum.INCOMPLETO_COM_ERRO : GameStatusEnum.INCOMPLETO_SEM_ERRO;
        }
    }

    public boolean isFinished() {
        return getStatus() == GameStatusEnum.COMPLETO_SEM_ERRO;
    }

    public boolean hasErrors() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                Integer tempValue = spaces.get(i).get(j).getActualValue();
                if (tempValue != null) {
                    spaces.get(i).get(j).setActualValue(null);
                    if (!isValidPlacement(i, j, tempValue)) {
                        spaces.get(i).get(j).setActualValue(tempValue);
                        return true;
                    }
                    spaces.get(i).get(j).setActualValue(tempValue);
                }
            }
        }
        return false;
    }

    public List<List<Space>> getSpaces() {
        return spaces;
    }
}