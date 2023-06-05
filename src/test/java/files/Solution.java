package files;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public static void main(String[] args) {

        String[] rootPaths = {"C:\\Users\\User\\IdeaProjects\\ReserveProject\\src\\test\\resources\\Каталоги\\A", "C:\\Users\\User\\IdeaProjects\\ReserveProject\\src\\test\\resources\\Каталоги\\B"};
        List<String> jsPaths = new ArrayList<>();
        Map<String, Integer> jsCounts = new HashMap<>();

        for (String rootPath : rootPaths) {
            searchJsFiles(new File(rootPath), jsPaths, jsCounts);
        }

        for (int i = 0; i < jsPaths.size(); i++) {
            String jsPath = jsPaths.get(i);
            int jsCount = jsCounts.get(jsPath);
            System.out.println(jsPath + " (" + jsCount + ")");
        }
    }

    private static void searchJsFiles(File directory, List<String> jsPaths, Map<String, Integer> jsCounts) {
        File[] files = directory.listFiles();
        if (files == null) {
            return;
        }

        int jsCount = 0;
        for (File file : files) {
            if (file.isDirectory()) {
                searchJsFiles(file, jsPaths, jsCounts);//если директория-то применяем рекурсию
            } else if (file.getName().endsWith(".js")) {
                jsCount++;
            }
        }

        if (jsCount > 0) {
            jsPaths.add(directory.getPath());
            jsCounts.put(directory.getPath(), jsCount);
        }
    }
}
