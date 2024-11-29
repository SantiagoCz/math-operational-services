package com.santiagocz.determinants_service.services;

import org.springframework.stereotype.Service;

@Service
public class PropertiesService {

    private final DeterminantService determinantService;

    PropertiesService(DeterminantService determinantService) {
        this.determinantService = determinantService;
    }

    public String isDeterminantIdentity(double[][] matrix) {
        determinantService.verifyIfSquareMatrix(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (i == j && matrix[i][j] != 1) {
                    return "No es una matriz identidad: un elemento en la diagonal principal no es 1.";
                }
                if (i != j && matrix[i][j] != 0) {
                    return "No es una matriz identidad: un elemento fuera de la diagonal principal no es 0.";
                }
            }
        }
        return "Es una matriz identidad.";
    }
}
