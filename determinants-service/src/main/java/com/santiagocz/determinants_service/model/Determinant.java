package com.santiagocz.determinants_service.model;

import java.util.Arrays;

public class Determinant {

    private final double[][] matrix;

    public Determinant(double[][] matrix) {
        // Realiza una copia profunda de la matriz
        this.matrix = new double[matrix.length][];
        for (int i = 0; i < matrix.length; i++) {
            this.matrix[i] = Arrays.copyOf(matrix[i], matrix[i].length);
        }
    }

    public int getDimension() {
        return matrix.length;
    }

    public double[][] getMatrix() {
        // Copia profunda de la matriz para garantizar inmutabilidad
        double[][] copy = new double[matrix.length][];
        for (int i = 0; i < matrix.length; i++) {
            copy[i] = Arrays.copyOf(matrix[i], matrix[i].length);
        }
        return copy;
    }
}

