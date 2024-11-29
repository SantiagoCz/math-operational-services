package com.santiagocz.vectors_service.model;

import java.util.Arrays;

public class Vector {

    private final double[] components;

    public Vector(double[] components) {
        // Crear una copia para evitar modificaciones externas al array original
        this.components = Arrays.copyOf(components, components.length);
    }

    public int getDimension() {
        return components.length;
    }

    public double[] getComponents() {
        // Retornar una copia para preservar la inmutabilidad
        return Arrays.copyOf(components, components.length);
    }
}
