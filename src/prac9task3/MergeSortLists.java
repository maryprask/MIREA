package prac9task3;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

class Student implements Comparable<Student> {
    private String name;
    private int id;
    private double gpa;

    public Student(String name, int id, double gpa) {
        this.name = name;
        this.id = id;
        this.gpa = gpa;
    }

    public double getGpa() { return gpa; }
    public String getName() { return name; }
    public int getId() { return id; }

    @Override
    public int compareTo(Student other) {
        // Добавим проверку на null на всякий случай
        if (other == null) return 1;
        return Double.compare(this.gpa, other.gpa);
    }

    @Override
    public String toString() {
        return "prac9task3.Student{name='" + name + "', gpa=" + gpa + "}";
    }
}

public class MergeSortLists {

    // Метод для слияния двух УЖЕ ОТСОРТИРОВАННЫХ списков
    public static List<Student> mergeTwoSortedLists(List<Student> left, List<Student> right) {
        List<Student> result = new ArrayList<>(left.size() + right.size());
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i).compareTo(right.get(j)) <= 0) {
                result.add(left.get(i));
                i++;
            } else {
                result.add(right.get(j));
                j++;
            }
        }

        // Добавляем остатки
        while (i < left.size()) {
            result.add(left.get(i));
            i++;
        }
        while (j < right.size()) {
            result.add(right.get(j));
            j++;
        }

        return result;
    }

    // Полноценная сортировка слиянием для одного списка (нужна для подготовки данных)
    public static List<Student> mergeSort(List<Student> list) {
        if (list.size() <= 1) {
            return list;
        }

        int mid = list.size() / 2;
        List<Student> left = new ArrayList<>(list.subList(0, mid));
        List<Student> right = new ArrayList<>(list.subList(mid, list.size()));

        left = mergeSort(left);
        right = mergeSort(right);

        return mergeTwoSortedLists(left, right);
    }

    public static void main(String[] args) {
        List<Student> list1 = new ArrayList<>(Arrays.asList(
                new Student("Иван", 101, 4.5),
                new Student("Мария", 102, 3.8),
                new Student("Алексей", 103, 4.9)
        ));

        List<Student> list2 = new ArrayList<>(Arrays.asList(
                new Student("Ольга", 104, 4.1),
                new Student("Дмитрий", 105, 4.7),
                new Student("Елена", 106, 3.9)
        ));

        System.out.println("Список 1 (до сортировки): " + list1);
        System.out.println("Список 2 (до сортировки): " + list2);

        // Шаг 1: Сортируем каждый список по отдельности
        List<Student> sortedList1 = mergeSort(list1);
        List<Student> sortedList2 = mergeSort(list2);

        // Шаг 2: Объединяем два отсортированных списка в один
        List<Student> finalSortedList = mergeTwoSortedLists(sortedList1, sortedList2);

        System.out.println("\nИтоговый отсортированный список:");
        for (Student s : finalSortedList) {
            System.out.println(s);
        }
    }
}