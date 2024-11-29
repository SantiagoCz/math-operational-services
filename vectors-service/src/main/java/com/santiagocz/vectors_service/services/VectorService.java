package com.santiagocz.vectors_service.services;

import com.santiagocz.vectors_service.model.Vector;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VectorService {

    public Vector add(Vector... vectors) {
        if (vectors.length < 2) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Debe proporcionar al menos dos vectores para realizar la operación.");
        }

        verifyDimensionForEachVector(vectors);

        double[] resultant = new double[vectors[0].getDimension()];

        for (Vector vector : vectors) {
            for (int i = 0; i < vector.getDimension(); i++) {
                resultant[i] += vector.getComponents()[i];
            }
        }

        return new Vector(resultant);
    }

    public double calculateDotProduct(Vector vector1, Vector vector2) {

        verifyDimensionForEachVector(vector1, vector2);

        double dotProduct = 0;

        for (int i = 0; i < vector1.getDimension(); i++) {
            dotProduct += vector1.getComponents()[i] * vector2.getComponents()[i];
        }

        return dotProduct;
    }

    public Vector calculateCrossProduct(Vector vector1, Vector vector2){

        if(vector1.getDimension() != 3 || vector2.getDimension() !=3) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Los vectores deben tener una dimension de 3 componentes");
        }

        double i1 = vector1.getComponents()[1] * vector2.getComponents()[2] - vector1.getComponents()[2] * vector2.getComponents()[1];
        double i2 = (-1) * (vector1.getComponents()[0] * vector2.getComponents()[2] - vector1.getComponents()[2] * vector2.getComponents()[0]);
        double i3 = vector1.getComponents()[0] * vector2.getComponents()[1] - vector1.getComponents()[1] * vector2.getComponents()[0];

        double[] resultant = new double[]{i1, i2, i3};

        return new Vector(resultant);

    }

    public Double calculateScalarTripleProduct(Vector vector0, Vector vector1, Vector vector2){

        if(vector1.getDimension() != 3 || vector2.getDimension() != 3 || vector0.getDimension() != 3) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Los vectores deben tener una dimension de 3 componentes");
        }

        double i1 = vector0.getComponents()[0] * (vector1.getComponents()[1] * vector2.getComponents()[2] - vector1.getComponents()[2] * vector2.getComponents()[1]);
        double i2 = ((-1) * vector0.getComponents()[1]) * (vector1.getComponents()[0] * vector2.getComponents()[2] - vector1.getComponents()[2] * vector2.getComponents()[0]);
        double i3 = vector0.getComponents()[2] * (vector1.getComponents()[0] * vector2.getComponents()[1] - vector1.getComponents()[1] * vector2.getComponents()[0]);

        return i1 + i2 + i3;
    }


    private void verifyDimensionForEachVector(Vector... vectors){
        Vector v1= vectors[0];
        for (int i = 1; i < vectors.length; i++){
            if(v1.getDimension() != vectors[i].getDimension()){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Los vectores deben tener la misma dimension");
            }
        }
    }

}
