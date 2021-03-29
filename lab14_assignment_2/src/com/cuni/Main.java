package com.cuni;

class Vector{

}

class Matrix{
    Double[][] matrix;
    Matrix(Double[][] paramData)
    {
        matrix = paramData;
    }

    Matrix multiply(Matrix first, Matrix second)
    {
        if(first == null || second == null)
        {
            throw new NullPointerException();
        }
    }

}

public class Main {

    public static void main(String[] args) {

    }
}
