package files;

import java.io.File;
import java.util.*;

public class Solution {

    private static final int NUMBERS_DiVISIONS = 2;

    public static void main(String[] args) {

        String[] rootPaths = {"C:\\Users\\User\\IdeaProjects\\ReserveProject\\src\\test\\resources\\Каталоги\\A", "C:\\Users\\User\\IdeaProjects\\ReserveProject\\src\\test\\resources\\Каталоги\\B"};
        List<String> jsPaths = new ArrayList<>();
        Map<String, Integer> jsCounts = new HashMap<>();

        for (String rootPath : rootPaths) {
            searchJsFiles(new File(rootPath), jsPaths, jsCounts);
        }


        List<Map.Entry<String, Integer>> entries = new ArrayList<>(jsCounts.entrySet());

        // Сортируем список в порядке убывания значений
        entries.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        //  list для хранения данных
        List<List<String>> arrays = new ArrayList<>();
        for (int i = 0; i < NUMBERS_DiVISIONS; i++) {
            arrays.add(new ArrayList<>());
        }

        // Добавляем ключи в массивы, начиная с наименьшего по значению
        for (Map.Entry<String, Integer> entry : entries) {
            List<String> smallestArray = arrays.get(0);
            int smallestSum = getSum(smallestArray, jsCounts);
            for (List<String> array : arrays) {
                int sum = getSum(array, jsCounts);
                if (sum < smallestSum) {
                    smallestArray = array;
                    smallestSum = sum;
                }
            }
            smallestArray.add(entry.getKey());
        }

        // Выводим Результат
        for (int i = 0; i < NUMBERS_DiVISIONS; i++) {
            System.out.println("[" + (i + 1) + "]");
            List<String> array = arrays.get(i);
            for (String key : array) {
                System.out.println(key + " (" + jsCounts.get(key) + ")");
            }
        }
    }

    //сумма HashMap-значений
    private static int getSum(List<String> keys, Map<String, Integer> hashMap) {
        int sum = 0;
        for (String key : keys) {
            sum += hashMap.get(key);
        }
        return sum;
    }


    //Ищем js-он файлы
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
