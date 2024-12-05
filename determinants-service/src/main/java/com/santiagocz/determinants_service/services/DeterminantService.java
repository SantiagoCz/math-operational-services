package com.santiagocz.determinants_service.services;

import com.santiagocz.determinants_service.dto.MatrixDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DeterminantService {

    public String defineDeterminant() {
        return "Un determinante es un valor numérico asociado a una matriz cuadrada (es decir, una matriz con el mismo número de filas y columnas). "
                + "El determinante se utiliza para analizar diversas propiedades de la matriz, como su invertibilidad. Si el determinante es diferente de cero, la matriz es invertible; "
                + "si es igual a cero, la matriz no tiene inversa. El determinante también tiene aplicaciones en la geometría, como el cálculo de áreas y volúmenes de figuras definidas por vectores. "
                + "Para matrices de 2x2 y 3x3, el determinante tiene fórmulas específicas, pero el concepto general se puede extender a matrices de mayor dimensión mediante una técnica llamada cofactores."
                + "\n\nEjemplo para una matriz 2x2:"
                + "\n|a b| -> Determinante = (a * d) - (b * c)"
                + "\n|c d|\n"
                + "\nEjemplo para una matriz 3x3:"
                + "\n|a b c| -> Determinante = a(ei - fh) - b(di - fg) + c(dh - eg)"
                + "\n|d e f|"
                + "\n|g h i|";
    }

    public double calculateDeterminant(MatrixDto matrix) {
        verifyIfSquareMatrix(matrix);
        if(matrix.getRows() == 2) {
            return calculateInOrderTwo(matrix);
        } else if( matrix.getRows() == 3) {
            return calculateInOrderThree(matrix);
        } else {
            return calculateInSuperiorOrder(matrix);
        }
    }

    private void verifyIfSquareMatrix(MatrixDto matrix) {
        if (matrix.getRows() != matrix.getColumns()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "La matriz debe ser cuadrada.");
        }
    }

    private double calculateInOrderTwo(MatrixDto matrix) {
        return matrix.getMatrix()[1][1] * matrix.getMatrix()[2][2] - matrix.getMatrix()[2][1] * matrix.getMatrix()[1][2];
    }

    private double calculateInOrderThree(MatrixDto matrix) {
        // Regla de Sarrus
        double i1 = matrix.getMatrix()[0][0] * matrix.getMatrix()[1][1] * matrix.getMatrix()[2][2];
        double i2 = matrix.getMatrix()[0][1] * matrix.getMatrix()[1][2] * matrix.getMatrix()[2][0];
        double i3 = matrix.getMatrix()[0][2] * matrix.getMatrix()[1][0] * matrix.getMatrix()[2][1];

        double i4 = matrix.getMatrix()[2][0] * matrix.getMatrix()[1][1] * matrix.getMatrix()[0][2];
        double i5 = matrix.getMatrix()[2][1] * matrix.getMatrix()[1][2] * matrix.getMatrix()[0][0];
        double i6 = matrix.getMatrix()[2][2] * matrix.getMatrix()[1][0] * matrix.getMatrix()[0][1];

        return i1 + i2 + i3 - i4 - i5 - i6;
    }

    private double calculateInSuperiorOrder(MatrixDto matrix){
        double[] firstRow = matrix.getMatrix()[0];

        double determinant = 0;

        for(int i = 0; i < firstRow.length; i++){
            int sign = (i % 2 == 0) ? 1 : -1;
            MatrixDto reducedMatrix = reduceMatrix(matrix, i);
            determinant += sign * firstRow[i] * calculateDeterminant(reducedMatrix);
        }

        return determinant;
    }

    private MatrixDto reduceMatrix(MatrixDto matrix, int columnIndex) {
        int rows = matrix.getRows();
        int columns = matrix.getColumns();

        MatrixDto newMatrix = new MatrixDto(new double[rows - 1][columns - 1]);

        for (int i = 1; i < rows; i++) {
            int newRow = i - 1;
            for (int j = 0; j < columns; j++) {
                if (j != columnIndex) {
                    int newColumn = (j < columnIndex) ? j : j - 1;
                    newMatrix.getMatrix()[newRow][newColumn] = matrix.getMatrix()[i][j];
                }
            }
        }
        return newMatrix;
    }
}

