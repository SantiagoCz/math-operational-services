package com.santiagocz.matrices_service.services;

import com.santiagocz.matrices_service.clients.DeterminantClient;
import com.santiagocz.matrices_service.model.Matrix;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class MatrixService {

    private final DeterminantClient determinantClient;

    public MatrixService(DeterminantClient determinantClient) {
        this.determinantClient = determinantClient;
    }

    public String defineMatrix() {
        return "";
    }

    public Matrix getTransposedMatrix(Matrix matrix) {
        double[][] transposedComponents = new double[matrix.getColumns()][matrix.getRows()];

        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getColumns(); j++) {
                transposedComponents[j][i] = matrix.getMatrix()[i][j];
            }
        }
        return new Matrix(transposedComponents);
    }

    public Matrix add(Matrix... matrices) {
        verifyDimensionForEachMatrix(matrices);
        double[][] resultant = new double[matrices[0].getRows()][matrices[0].getColumns()];

        for (Matrix matrix : matrices) {
            for (int i = 0; i < matrix.getRows(); i++) {
                for(int j = 0; j < matrix.getColumns(); j++) {
                    resultant[i][j] += matrix.getMatrix()[i][j];;
                }
            }
        }
        return new Matrix(resultant);
    }

    public Matrix multiplyByScalar(Matrix matrix, double scalar) {
        double[][] resultant = new double[matrix.getRows()][matrix.getColumns()];

        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getColumns(); j++) {
                resultant[i][j] = scalar * matrix.getMatrix()[i][j];
            }
        }
        return new Matrix(resultant);
    }

    public Matrix multiply(Matrix matrix1, Matrix matrix2) {
        verifyConditionForMultiplicate(matrix1, matrix2);
        double[][] resultant = new double[matrix1.getRows()][matrix2.getColumns()];

        for(int i = 0; i < matrix1.getRows(); i++) {

            for(int j = 0; j < matrix2.getColumns(); j++) {
                double acumulator = 0;

                for(int k = 0; k < matrix1.getColumns(); k++) {
                    acumulator += matrix1.getMatrix()[i][k] * matrix2.getMatrix()[k][j];
                }
                resultant[i][j] = acumulator;
            }
        }
        return new Matrix(resultant);
    }

    public double getDeterminant(Matrix matrix) {
        verifyIfSquareMatrix(matrix);
        return determinantClient.calculateDeterminant(matrix);
    }

    public Matrix getAdjugateMatrix(Matrix matrix) {
        verifyIfSquareMatrix(matrix);
        return determinantClient.calculateAdjugateMatrix(matrix);
    }

    public Matrix calculateInverse(Matrix matrix) {
        double determinant = getDeterminant(matrix);

        if(determinant == 0){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "No se puede calcular inversa, el determinante de la matriz es igual a 0.");
        } else {
            Matrix adjugateMatrix = getAdjugateMatrix(matrix);
            Matrix transposedMatrix = getTransposedMatrix(adjugateMatrix);
            double scalar = 1/determinant;
            return multiplyByScalar(transposedMatrix, scalar);
        }
    }

    //-------------------------------------------------------------------------------------------

    private void verifyDimensionForEachMatrix(Matrix... matrices) {
        Matrix m1 = matrices[0];
        for(int i = 0; i < matrices.length; i++){
            if (!Objects.equals(m1.getDimensions(), matrices[i].getDimensions())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Las matrices deben tener la misma dimension.");
            }
        }
    }

    private void verifyIfSquareMatrix(Matrix matrix) {
        if (matrix.getRows() != matrix.getColumns()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "La matriz debe ser cuadrada.");
        }
    }

    private void verifyConditionForMultiplicate(Matrix matrix1, Matrix matrix2){
        if(matrix1.getColumns() != matrix2.getRows()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "No hay coincidencias entre cantidad de columnas y filas.");
        }
    }
}