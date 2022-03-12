package practicum;

import java.util.*;

public class Algorithms {
    
    /**
     * Отсортируйте список, НЕ используя методы стандартной библиотеки (напр. Collections.sort).
     */
    public static List<Integer> sort(List<Integer> list) {
        Integer[] arr = new Integer[list.size()];
        arr = list.toArray(arr);
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int x = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = x;
                }
            }
        }
        return List.of(arr);
    }
    
    /**
     * Удалите дубликаты из списка.
     * <p>
     * Усложнение: не используйте дополнительные структуры данных
     * для хранения промежуточных значений.
     * (списки, массивы, хэш-таблицы, множества и т.п.).
     * К списку-результату это не относится.
     */
    public static List<Integer> removeDuplicates(List<Integer> list) {
        if (list.size() == 0 || list.size() == 1) return list;
        List<Integer> result = new ArrayList<>();
        result.add(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            boolean isDuplicate = false;
            for (int j = 0; j < result.size(); j++) {
                if (list.get(i) == result.get(j)) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) result.add(list.get(i));
        }
        return result;
    }
    
    /**
     * Проверьте, является ли список "палиндромом".
     * Палиндром -- это список, который в обе стороны читается одинаково.
     * Например:
     * палиндромы: [1 2 1], [3 2 1 2 3], [2 2 2], []
     * не палиндромы: [1 2 3], [2 2 3], [3 2 1 3 2]
     * <p>
     * Доп. условие: у алгоритма должна быть линейная сложность, O(n)
     */
    public static boolean isPalindrome(List<Integer> list) {
        for (int i = 0; i < list.size() / 2; i++) {
            if (list.get(i) != list.get(list.size() - i - 1)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Объедините два отсортированных списка в один отсортированный список.
     * Например:
     * [1 3 5] + [2 4 6] = [1 2 3 4 5 6]
     * [1 2 3] + [1 3 5] = [1 1 2 3 3 5]
     * [] + [1] = [1]
     * [7] + [1 4] = [1 4 7]
     * <p>
     * Доп. условие: у алгоритма должна быть линейная сложность, O(n).
     */
    public static List<Integer> mergeSortedLists(List<Integer> a, List<Integer> b) {
        int totalLength = a.size() + b.size();
        Integer[] result = new Integer[totalLength];
        int aPointer = 0;
        int bPointer = 0;
        for (int i = 0; i < totalLength; i++) {
            if (aPointer == a.size()) {
                for (int j = i; j < totalLength; j++) {
                    result[j] = b.get(bPointer++);
                }
                break;
            }
            if (bPointer == b.size()) {
                for (int j = i; j < totalLength; j++) {
                    result[j] = a.get(aPointer++);
                }
                break;
            }
            
            if (a.get(aPointer) < b.get(bPointer)) {
                result[i] = a.get(aPointer++);
            } else {
                result[i] = b.get(bPointer++);
            }
        }
        return List.of(result);
    }
    
    /**
     * Проверьте, что в массиве нет дубликатов.
     * Верните true, если дубликатов нет, иначе false.
     * <p>
     * Усложнение: не используйте дополнительные структуры данных
     * (списки, массивы, хэш-таблицы, множества и т.п.).
     */
    public static boolean containsEveryElementOnce(int[] array) {
        Arrays.sort(array);
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] == array[i + 1]) return false;
        }
        return true;
    }
    
    /**
     * Определите, является ли один массив перестановкой другого.
     * Т.е. в массивах хранится один и тот же набор элементов, но (возможно) в разном порядке.
     * <p>
     * Для решения нжуно использовать одну хэш-таблицу.
     * <p>
     * Например:
     * [1 2 3] и [3 2 1] = true
     * [1 1 2] и [1 2 1] = true
     * [1 2 3] и [1 2 3] = true
     * [] и [] = true
     * <p>
     * [1 2] и [1 1 2] = false, разный набор элементов
     */
    public static boolean isPermutation(int[] a, int[] b) {
        if (a.length == 0 && b.length == 0) return true;
        if (a.length != b.length) return false;
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < a.length; i++) {
            if (map.containsKey(a[i])) {
                map.put(a[i], map.get(a[i]) + 1);
                continue;
            }
            map.put(a[i], 1);
        }
        
        for (int i = 0; i < b.length; i++) {
            if (map.containsKey(b[i])) {
                if (map.get(b[i]) == 0) return false;
                map.put(b[i], map.get(b[i]) - 1);
                continue;
            }
            return false;
        }
        return true;
    }
    
    /**
     * Сложная задача.
     * <p>
     * В памяти компьютера изображения (часто) хранятся в виде двумерного массива.
     * Напишите метод, который повернёт "изображение" на 90 градусов вправо.
     * "Изображение" в данном примере -- двумерный массив целых чисел.
     * <p>
     * Например:
     * на входе:
     * [ [1 2]
     * [3 4]
     * [5 6] ]
     * <p>
     * на выходе:
     * [ [5 3 1]
     * [6 4 2] ]
     */
    public static int[][] rotateRight(int[][] image) {
        int[][] output = new int[image[0].length][image.length];
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                output[j][image.length - i - 1] = image[i][j];
            }
        }
        return output;
    }
}
