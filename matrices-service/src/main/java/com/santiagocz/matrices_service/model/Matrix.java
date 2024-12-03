package com.santiagocz.matrices_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Matrix {

    private double[][] matrix;

    public int getRows() {
        return matrix != null ? matrix.length : 0;
    }

    public int getColumns() {
        return (matrix != null && matrix.length > 0) ? matrix[0].length : 0;
    }

    public String getDimensions() {
        return getRows() + ", " + getColumns();
    }
}