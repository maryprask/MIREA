package prac17;

import java.util.Scanner;

class DoublyNode {
    String name;
    int age;
    DoublyNode next;
    DoublyNode prev; // Ссылка на предыдущий элемент

    public DoublyNode(String name, int age) {
        this.name = name;
        this.age = age;
        this.next = null;
        this.prev = null;
    }

    public void printInfo() {
        System.out.println("Студент: " + name + ", Возраст: " + age);
    }
}

class DoublyLinkedListContainer {
    private DoublyNode head;
    private DoublyNode tail; // Храним хвост для быстрого добавления

    public DoublyLinkedListContainer() {
        this.head = null;
        this.tail = null;
    }

    public void add(String name, int age) {
        DoublyNode newNode = new DoublyNode(name, age);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        System.out.println("Студент добавлен.");
    }

    public void remove(String name) {
        if (head == null) {
            System.out.println("Список пуст.");
            return;
        }
        DoublyNode current = head;
        while (current != null && !current.name.equals(name)) {
            current = current.next;
        }

        if (current == null) {
            System.out.println("Студент с именем " + name + " не найден.");
            return;
        }

        // Если удаляем голову
        if (current == head) {
            head = current.next;
            if (head != null) head.prev = null;
            else tail = null; // Если список стал пустым
        }
        // Если удаляем хвост
        else if (current == tail) {
            tail = current.prev;
            tail.next = null;
        }
        // Если удаляем в середине
        else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }
        System.out.println("Студент " + name + " удален.");
    }

    public void printAll() {
        if (isEmpty()) {
            System.out.println("Список пуст.");
            return;
        }
        DoublyNode current = head;
        while (current != null) {
            current.printInfo();
            current = current.next;
        }
    }

    public void clear() {
        head = null;
        tail = null;
        System.out.println("Список полностью очищен.");
    }

    public boolean isEmpty() {
        return head == null;
    }
}

public class Task2_DoublyLinkedList {
    public static void main(String[] args) {
        DoublyLinkedListContainer list = new DoublyLinkedListContainer();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- МЕНЮ (Двухсвязный список) ---");
            System.out.println("1. Добавить студента");
            System.out.println("2. Удалить студента по имени");
            System.out.println("3. Показать всех");
            System.out.println("4. Очистить список");
            System.out.println("5. Проверить на пустоту");
            System.out.println("0. Выход");
            System.out.print("Выбор: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Ошибка: введите число!");
                scanner.next();
                continue;
            }
            int choice = scanner.nextInt();
            scanner.nextLine();

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
                        scanner.nextLine();
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