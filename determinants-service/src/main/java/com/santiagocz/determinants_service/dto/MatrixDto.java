package com.santiagocz.determinants_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatrixDto {

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

