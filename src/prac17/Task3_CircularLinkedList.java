package prac17;

import java.util.Scanner;

class CircularNode {
    String name;
    int age;
    CircularNode next;

    public CircularNode(String name, int age) {
        this.name = name;
        this.age = age;
        this.next = null;
    }

    public void printInfo() {
        System.out.println("Студент: " + name + ", Возраст: " + age);
    }
}

class CircularLinkedListContainer {
    private CircularNode head;

    public CircularLinkedListContainer() {
        this.head = null;
    }

    public void add(String name, int age) {
        CircularNode newNode = new CircularNode(name, age);
        if (head == null) {
            head = newNode;
            head.next = head; // Замыкаем на себя
        } else {
            CircularNode current = head;
            // Идем до последнего элемента (который ссылается на head)
            while (current.next != head) {
                current = current.next;
            }
            current.next = newNode;
            newNode.next = head; // Новый узел замыкается на голову
        }
        System.out.println("Студент добавлен.");
    }


    public void remove(String name) {
        if (head == null) {
            System.out.println("Список пуст.");
            return;
        }

        // 1. Если удаляем голову
        if (head.name.equals(name)) {
            if (head.next == head) {
                head = null; // Был всего один элемент
            } else {
                CircularNode tail = head;
                while (tail.next != head) {
                    tail = tail.next;
                }
                head = head.next; // Сдвигаем голову вперед
                tail.next = head; // Замыкаем хвост на новую голову
            }
            System.out.println("Студент " + name + " удален.");
            return;
        }

        // 2. Если удаляем НЕ голову
        CircularNode current = head;
        CircularNode prev = null;

        // Ищем элемент, пока не вернемся к голове
        while (current.next != head && !current.name.equals(name)) {
            prev = current;
            current = current.next;
        }

        // Проверяем последний элемент (перед головой)
        if (current.name.equals(name) && prev != null) {
            prev.next = current.next; // Перекидываем ссылку
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
        CircularNode current = head;
        // Используем do-while, чтобы гарантированно вывести хотя бы один элемент
        do {
            current.printInfo();
            current = current.next;
        } while (current != head);
    }

    public void clear() {
        head = null;
        System.out.println("Список полностью очищен.");
    }

    public boolean isEmpty() {
        return head == null;
    }
}

public class Task3_CircularLinkedList {
    public static void main(String[] args) {
        CircularLinkedListContainer list = new CircularLinkedListContainer();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- МЕНЮ (Кольцевой список) ---");
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