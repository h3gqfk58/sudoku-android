package com.example.sudoku;

public class Table_3 {
    private String game_mode;
    private String x1,x2,x3,x4,x5;

    public Table_3(String game_mode, String x1, String x2, String x3, String x4, String x5) {
        this.game_mode = game_mode;
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.x4 = x4;
        this.x5 = x5;
    }

    public Table_3() {
    }

    @Override
    public String toString() {
        return "Table_3{" +
                "game_mode='" + game_mode + '\'' +
                ", x1='" + x1 + '\'' +
                ", x2='" + x2 + '\'' +
                ", x3='" + x3 + '\'' +
                ", x4='" + x4 + '\'' +
                ", x5='" + x5 + '\'' +
                '}';
    }

    public String getGame_mode() {
        return game_mode;
    }

    public void setGame_mode(String game_mode) {
        this.game_mode = game_mode;
    }

    public String getX1() {
        return x1;
    }

    public void setX1(String x1) {
        this.x1 = x1;
    }

    public String getX2() {
        return x2;
    }

    public void setX2(String x2) {
        this.x2 = x2;
    }

    public String getX3() {
        return x3;
    }

    public void setX3(String x3) {
        this.x3 = x3;
    }

    public String getX4() {
        return x4;
    }

    public void setX4(String x4) {
        this.x4 = x4;
    }

    public String getX5() {
        return x5;
    }

    public void setX5(String x5) {
        this.x5 = x5;
    }
}
