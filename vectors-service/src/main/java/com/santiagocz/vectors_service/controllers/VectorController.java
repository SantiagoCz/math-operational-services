package com.santiagocz.vectors_service.controllers;

import com.santiagocz.vectors_service.model.Vector;
import com.santiagocz.vectors_service.services.VectorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vectors")
public class VectorController {

    private final VectorService vectorService;

    public VectorController(VectorService vectorService) {
        this.vectorService = vectorService;
    }

    @PostMapping("/add")
    public ResponseEntity<Vector> add(@RequestBody Vector... vectors){

        Vector result = vectorService.add(vectors);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/dotProduct")
    public ResponseEntity<Double> calculateDotProduct(@RequestBody List<Vector> vectors){

        Vector vector1 = vectors.get(0);
        Vector vector2 = vectors.get(1);

        Double result = vectorService.calculateDotProduct(vector1, vector2);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/crossProduct")
    public ResponseEntity<Vector> calculateCrossProduct(@RequestBody List<Vector> vectors){

        Vector vector1 = vectors.get(0);
        Vector vector2 = vectors.get(1);

        Vector result = vectorService.calculateCrossProduct(vector1, vector2);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/scalarTripleProduct")
    public ResponseEntity<Double> calculateScalarTripleProduct(@RequestBody List<Vector> vectors){

        Vector vector0 = vectors.get(0);
        Vector vector1 = vectors.get(1);
        Vector vector2 = vectors.get(2);

        Double result = vectorService.calculateScalarTripleProduct(vector0, vector1, vector2);
        return ResponseEntity.ok(result);
    }
}
