package com.example;
import java.io.IOException;

interface Extractable {
    void extract(String destinationDir) throws IOException;
}