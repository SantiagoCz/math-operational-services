package com.santiagocz.matrices_service.clients;

import com.santiagocz.matrices_service.model.Matrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "determinants-service")
public interface DeterminantClient {

    @PostMapping("/determinants/calculate-determinant")
    double calculateDeterminant(@RequestBody Matrix matrix);

    @PostMapping("/determinants/calculate-adjugate-Matrix")
    Matrix calculateAdjugateMatrix(@RequestBody Matrix matrix);
}