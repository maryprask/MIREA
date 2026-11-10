package prac17;

import java.util.Scanner;

// 1. Класс Узел (Элемент картотеки)
class StudentNode {
    String name;
    int age;
    StudentNode next; // Ссылка на следующий элемент

    public StudentNode(String name, int age) {
        this.name = name;
        this.age = age;
        this.next = null;
    }

    public void printInfo() {
        System.out.println("Студент: " + name + ", Возраст: " + age);
    }
}

// 2. Контейнерный класс (Односвязный список)
class LinkedListContainer {
    private StudentNode head; // Голова списка

    public LinkedListContainer() {
        this.head = null;
    }

    public void add(String name, int age) {
        StudentNode newNode = new StudentNode(name, age);
        if (head == null) {
            head = newNode;
            return;
        }
        StudentNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
        System.out.println("Студент добавлен.");
    }


    public void remove(String name) {
        if (head == null) {
            System.out.println("Список пуст.");
            return;
        }
        if (head.name.equals(name)) {
            head = head.next;
            System.out.println("Студент " + name + " удален.");
            return;
        }
        StudentNode current = head;
        // Ищем элемент, стоящий ПЕРЕД тем, который нужно удалить
        while (current.next != null && !current.next.name.equals(name)) {
            current = current.next;
        }
        if (current.next != null) {
            current.next = current.next.next; // Перекидываем ссылку через удаляемый элемент
            System.out.println("Студент " + name + " удален.");
        } else {
            System.out.println("Студент с именем " + name + " не найден.");
        }
    }

    public void printAll() {
        if (isEmpty()) {
            System.out.println("Список пуст.");
            return;
        }
        StudentNode current = head;
        while (current != null) {
            current.printInfo();
            current = current.next;
        }
    }

    public void clear() {
        head = null;
        System.out.println("Список полностью очищен.");
    }

    public boolean isEmpty() {
        return head == null;
    }
}

// 3. Класс Тестер
public class Task1_SinglyLinkedList {
    public static void main(String[] args) {
        LinkedListContainer list = new LinkedListContainer();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- МЕНЮ (Односвязный список) ---");
            System.out.println("1. Добавить студента");
            System.out.println("2. Удалить студента по имени");
            System.out.println("3. Показать всех");
            System.out.println("4. Очистить список");
            System.out.println("5. Проверить на пустоту");
            System.out.println("0. Выход");
            System.out.print("Выбор: ");

            // Защита от ввода букв в меню
            if (!scanner.hasNextInt()) {
                System.out.println("Ошибка: введите число!");
                scanner.next(); // очищаем неверный ввод
                continue;
            }
            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1:
                    System.out.print("Введите имя: ");
                    String name = scanner.nextLine();
                    System.out.print("Введите возраст: ");
                    if (scanner.hasNextInt()) {
                        int age = scanner.nextInt();
                        scanner.nextLine();
                        list.add(name, age);
                    } else {
                        System.out.println("Ошибка: возраст должен быть числом.");
                        scanner.nextLine(); // очистка
                    }
                    break;
                case 2:
                    System.out.print("Введите имя для удаления: ");
                    list.remove(scanner.nextLine());
                    break;
                case 3:
                    list.printAll();
                    break;
                case 4:
                    list.clear();
                    break;
                case 5:
                    System.out.println("Список пуст? " + list.isEmpty());
                    break;
                case 0:
                    System.out.println("Выход...");
                    return;
                default:
                    System.out.println("Неверный пункт меню.");
            }
        }
    }
}