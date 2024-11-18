
package com.example;
import java.util.ArrayList;
import java.util.List;

public class DirectoryIterator implements FileExplorerIterator {
    private ArrayList<String> directoryList;
    private int currentIndex;

    public DirectoryIterator(List<String> directoryList) {
        System.out.println(directoryList);
        this.directoryList = new ArrayList<>(directoryList);
        this.currentIndex = 0;
    }

    public boolean hasNext() {
        return currentIndex < directoryList.size();
    }

    public String next() {
        System.out.println(hasNext());
        System.out.println(directoryList);
        if (hasNext()) {
            return directoryList.get(currentIndex++);
        }
        return null;
    }

}
