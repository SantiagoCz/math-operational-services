package com.santiagocz.determinants_service.controllers;

import com.santiagocz.determinants_service.dto.MatrixDto;
import com.santiagocz.determinants_service.services.DeterminantService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/determinants")
public class DeterminantController {

    private final DeterminantService determinantService;

    public DeterminantController(DeterminantService determinantService) {
        this.determinantService = determinantService;
    }

    @GetMapping("/define")
    public String defineDeterminant() {
        return determinantService.defineDeterminant();
    }

    @PostMapping("/calculate-determinant")
    public double calculateDeterminant(@RequestBody MatrixDto matrix) {
        return determinantService.calculateDeterminant(matrix);
    }

    @PostMapping("/calculate-adjugate-Matrix")
    public MatrixDto calculateAdjugateMatrix(@RequestBody MatrixDto matrix) {
        return determinantService.calculateAdjugateMatrix(matrix);
    }

}