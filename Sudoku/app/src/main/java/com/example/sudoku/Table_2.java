package com.example.sudoku;

public class Table_2 {
    private int resume_status, level, mistakes;
    private String validation_status, notes_status, game_mode;
    private long time;
    private String x1,x2,x3,x4,x5;

    public Table_2(int resume_status, int level, String validation_status, String notes_status, long time, int mistakes, String game_mode, String x1, String x2, String x3, String x4, String x5) {
        this.resume_status = resume_status;
        this.level = level;
        this.validation_status = validation_status;
        this.notes_status = notes_status;
        this.time = time;
        this.mistakes = mistakes;
        this.game_mode = game_mode;
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.x4 = x4;
        this.x5 = x5;
    }

    public Table_2() {
    }

    @Override
    public String toString() {
        return "Table_2{" +
                "resume_status=" + resume_status +
                ", level=" + level +
                ", mistakes=" + mistakes +
                ", validation_status='" + validation_status + '\'' +
                ", notes_status='" + notes_status + '\'' +
                ", game_mode='" + game_mode + '\'' +
                ", time=" + time +
                ", x1='" + x1 + '\'' +
                ", x2='" + x2 + '\'' +
                ", x3='" + x3 + '\'' +
                ", x4='" + x4 + '\'' +
                ", x5='" + x5 + '\'' +
                '}';
    }

    public int getResume_status() {
        return resume_status;
    }

    public void setResume_status(int resume_status) {
        this.resume_status = resume_status;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getValidation_status() {
        return validation_status;
    }

    public void setValidation_status(String validation_status) {
        this.validation_status = validation_status;
    }

    public String getNotes_status() {
        return notes_status;
    }

    public void setNotes_status(String notes_status) {
        this.notes_status = notes_status;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getMistakes() {
        return mistakes;
    }

    public void setMistakes(int mistakes) {
        this.mistakes = mistakes;
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
