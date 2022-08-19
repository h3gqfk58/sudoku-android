package com.example.sudoku;

public class Table_1 {

    private int sq,sa,sw,se,se3d_1,se3d_2,se3d_3,se3d_4,se3d_5,se3d_6,se3d_7,se3d_8,se3d_9;
    private String x1,x2,x3,x4,x5;

    public Table_1(int sq, int sa, int sw, int se, int s3d_1, int s3d_2, int s3d_3, int s3d_4, int s3d_5, int s3d_6, int s3d_7, int s3d_8, int s3d_9, String x1, String x2, String x3, String x4, String x5) {
        this.sq = sq;
        this.sa = sa;
        this.sw = sw;
        this.se = se;
        this.se3d_1 = s3d_1;
        this.se3d_2 = s3d_2;
        this.se3d_3 = s3d_3;
        this.se3d_4 = s3d_4;
        this.se3d_5 = s3d_5;
        this.se3d_6 = s3d_6;
        this.se3d_7 = s3d_7;
        this.se3d_8 = s3d_8;
        this.se3d_9 = s3d_9;
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.x4 = x4;
        this.x5 = x5;
    }

    public Table_1() {
    }

    @Override
    public String toString() {
        return "Table_1{" +
                "sq=" + sq +
                ", sa=" + sa +
                ", sw=" + sw +
                ", se=" + se +
                ", se3d_1=" + se3d_1 +
                ", se3d_2=" + se3d_2 +
                ", se3d_3=" + se3d_3 +
                ", se3d_4=" + se3d_4 +
                ", se3d_5=" + se3d_5 +
                ", se3d_6=" + se3d_6 +
                ", se3d_7=" + se3d_7 +
                ", se3d_8=" + se3d_8 +
                ", se3d_9=" + se3d_9 +
                ", x1='" + x1 + '\'' +
                ", x2='" + x2 + '\'' +
                ", x3='" + x3 + '\'' +
                ", x4='" + x4 + '\'' +
                ", x5='" + x5 + '\'' +
                '}';
    }

    public int getSq() {
        return sq;
    }

    public void setSq(int sq) {
        this.sq = sq;
    }

    public int getSa() {
        return sa;
    }

    public void setSa(int sa) {
        this.sa = sa;
    }

    public int getSw() {
        return sw;
    }

    public void setSw(int sw) {
        this.sw = sw;
    }

    public int getSe() {
        return se;
    }

    public void setSe(int se) {
        this.se = se;
    }

    public int getSe3d_1() {
        return se3d_1;
    }

    public void setSe3d_1(int se3d_1) {
        this.se3d_1 = se3d_1;
    }

    public int getSe3d_2() {
        return se3d_2;
    }

    public void setSe3d_2(int se3d_2) {
        this.se3d_2 = se3d_2;
    }

    public int getSe3d_3() {
        return se3d_3;
    }

    public void setSe3d_3(int se3d_3) {
        this.se3d_3 = se3d_3;
    }

    public int getSe3d_4() {
        return se3d_4;
    }

    public void setSe3d_4(int se3d_4) {
        this.se3d_4 = se3d_4;
    }

    public int getSe3d_5() {
        return se3d_5;
    }

    public void setSe3d_5(int se3d_5) {
        this.se3d_5 = se3d_5;
    }

    public int getSe3d_6() {
        return se3d_6;
    }

    public void setSe3d_6(int se3d_6) {
        this.se3d_6 = se3d_6;
    }

    public int getSe3d_7() {
        return se3d_7;
    }

    public void setSe3d_7(int se3d_7) {
        this.se3d_7 = se3d_7;
    }

    public int getSe3d_8() {
        return se3d_8;
    }

    public void setSe3d_8(int se3d_8) {
        this.se3d_8 = se3d_8;
    }

    public int getSe3d_9() {
        return se3d_9;
    }

    public void setSe3d_9(int se3d_9) {
        this.se3d_9 = se3d_9;
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
