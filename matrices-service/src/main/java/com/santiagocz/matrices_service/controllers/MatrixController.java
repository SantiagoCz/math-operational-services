package com.santiagocz.matrices_service.controllers;

import com.santiagocz.matrices_service.model.Matrix;
import com.santiagocz.matrices_service.services.MatrixService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/matrices")
public class MatrixController {

    private final MatrixService matrixService;

    MatrixController(MatrixService matrixService) {
        this.matrixService = matrixService;
    }

    @PostMapping("/add")
    public ResponseEntity<Matrix> add(@RequestBody Matrix... matrices) {

        Matrix result = matrixService.add(matrices);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/transpose")
    public ResponseEntity<Matrix> transpose(@RequestBody Matrix matrix) {

        Matrix transposedMatrix = matrixService.getTransposedMatrix(matrix);
        return ResponseEntity.ok(transposedMatrix);
    }

    @PostMapping("/multipyByScalar")
    public ResponseEntity<Matrix> multiplyByScalar(@RequestBody Matrix matrix,
                                                   @RequestParam double scalar) {

        Matrix result = matrixService.multiplyByScalar(matrix, scalar);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/multiply")
    public ResponseEntity<Matrix> multiply(@RequestBody Matrix... matrices) {

        if(matrices.length != 2) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Para multiplicar se necesitan 2 matrices.");
        }

        Matrix matrix1 = matrices[0];
        Matrix matrix2 = matrices[1];

        Matrix result = matrixService.multiply(matrix1, matrix2);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/determinant")
    public ResponseEntity<Double> calculateDeterminant(@RequestBody Matrix matrix) {
        double result = matrixService.getDeterminant(matrix);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/adjoint")
    public ResponseEntity<Matrix> calculateAdjugateMatrix(@RequestBody Matrix matrix) {
        Matrix result = matrixService.getAdjugateMatrix(matrix);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/inverse")
    public ResponseEntity<Matrix> calculateInverse(@RequestBody Matrix matrix) {
        Matrix result = matrixService.calculateInverse(matrix);
        return ResponseEntity.ok(result);
    }

}