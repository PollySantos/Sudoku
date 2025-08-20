package br.com.dio.model;

public class Space {
    private Integer actualValue;
    private final Integer fixedValue;
    private final boolean isFixed;

    public Space(Integer fixedValue) {
        this.fixedValue = fixedValue;
        this.isFixed = (fixedValue != null);
        if (this.isFixed) {
            this.actualValue = fixedValue;
        }
    }

    public Integer getActualValue() {
        return actualValue;
    }

    public void setActualValue(Integer actualValue) {
        this.actualValue = actualValue;
    }

    public Integer getFixedValue() {
        return fixedValue;
    }

    public boolean isFixed() {
        return isFixed;
    }

    public void clear() {
        if (!isFixed) {
            this.actualValue = null;
        }
    }

    @Override
    public String toString() {
        return String.valueOf(actualValue == null ? " " : actualValue);
    }
}
