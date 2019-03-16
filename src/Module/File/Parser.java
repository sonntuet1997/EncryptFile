package Module.File;

import java.io.File;

public class Parser {

    public static void main(String args[]) {
        Parser x = new Parser();
        File file = new File("Files");
        x.addJavaFiles(file);
    }

    private void addJavaFiles(final File files) {
        for (final File fileEntry : files.listFiles()) {
            if (fileEntry.isDirectory()) {
                addJavaFiles(fileEntry);
            } else{
                System.out.println(fileEntry.getPath());
//              fileEntry.getPath()
            }
        }
    }
}