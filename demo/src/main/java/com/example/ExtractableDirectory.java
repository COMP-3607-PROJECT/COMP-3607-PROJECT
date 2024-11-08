package com.example;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

// Composite - Represents directories (which can contain files or other directories)
class ExtractableDirectory implements Extractable {
    private List<Extractable> children = new ArrayList<>();
    private String name;

    public ExtractableDirectory(String name) {
        this.name = name;
    }

    public void add(Extractable extractable) {
        children.add(extractable);
    }

    @Override
    public void extract(String destinationDir) throws IOException {
        String dirPath = destinationDir + File.separator + name;
        File dir = new File(dirPath);
        dir.mkdirs();
        System.out.println("Created directory: " + dirPath);

        for (Extractable child : children) {
            child.extract(dirPath);
        }
    }
}
