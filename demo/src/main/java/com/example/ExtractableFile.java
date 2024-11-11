package com.example;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

class ExtractableFile implements Extractable {
    private ZipEntry entry;
    private ZipInputStream zipInputStream;

    public ExtractableFile(ZipEntry entry, ZipInputStream zipInputStream) {
        this.entry = entry;
        this.zipInputStream = zipInputStream;
    }

    @Override
    public void extract(String destinationDir) throws IOException {
        String filePath = destinationDir + File.separator + entry.getName();
        File javaFile = new File(filePath);
        ZipExtractor.createParentDirs(javaFile);

        try (FileOutputStream fos = new FileOutputStream(javaFile)) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = zipInputStream.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
        }
        System.out.println("Extracted Java file: " + javaFile.getName() + " to " + destinationDir);
    }
}
