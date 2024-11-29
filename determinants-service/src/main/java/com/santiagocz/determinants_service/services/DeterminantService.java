package com.santiagocz.determinants_service.services;

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

    public void verifyIfSquareMatrix(double[][] matrix) {
        if (matrix.length != matrix[0].length) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "La matriz debe ser cuadrada.");
        }
    }
}

