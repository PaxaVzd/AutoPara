import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Виведення надпису "U Z H N U - F I T"
        printUZHNUFIT();

        ScheduleManager scheduleManager = new ScheduleManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Додати пару");
            System.out.println("2. Видалити пару");
            System.out.println("3. Редагувати пару");
            System.out.println("4. Переглянути розклад");
            System.out.println("5. Підключитися до пари");
            System.out.println("6. Вихід");
            System.out.print("Виберіть опцію: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // очищення буфера

            switch (choice) {
                case 1:
                    System.out.print("Введіть назву дисципліни: ");
                    String subject = scanner.nextLine();
                    System.out.print("Введіть ПІБ викладача: ");
                    String teacher = scanner.nextLine();
                    System.out.print("Введіть посилання на Meet: ");
                    String meetLink = scanner.nextLine();
                    System.out.print("Введіть день: ");
                    String day = scanner.nextLine();
                    scheduleManager.addPair(new Pair(subject, teacher, meetLink, day));
                    break;
                case 2:
                    System.out.print("Введіть номер пари для видалення: ");
                    int removeIndex = scanner.nextInt() - 1;
                    scheduleManager.removePair(removeIndex);
                    break;
                case 3:
                    System.out.print("Введіть номер пари для редагування: ");
                    int editIndex = scanner.nextInt() - 1;
                    scanner.nextLine(); // очищення буфера
                    System.out.print("Введіть нову назву дисципліни: ");
                    String newSubject = scanner.nextLine();
                    System.out.print("Введіть новий ПІБ викладача: ");
                    String newTeacher = scanner.nextLine();
                    System.out.print("Введіть нове посилання на Meet: ");
                    String newMeetLink = scanner.nextLine();
                    System.out.print("Введіть новий день: ");
                    String newDay = scanner.nextLine();
                    scheduleManager.editPair(editIndex, new Pair(newSubject, newTeacher, newMeetLink, newDay));
                    break;
                case 4:
                    scheduleManager.displaySchedule();
                    break;
                case 5:
                    scheduleManager.choosePair();
                    break;
                case 6:
                    System.out.println("Вихід з програми.");
                    return;
                default:
                    System.out.println("Невірний вибір, спробуйте ще раз.");
            }
        }
    }

    private static void printUZHNUFIT() {
        System.out.println(
                "U   U   ZZZZZ   H   H   N   N   U   U    -   FFFFF   I   TTTTT\n" +
                        "U   U      Z    H   H   NN  N   U   U        F       I     T  \n" +
                        "U   U    Z      HHHHH   N N N   U   U        FFFFF   I     T  \n" +
                        "U   U   Z       H   H   N  NN   U   U        F       I     T  \n" +
                        " UUU   ZZZZZ    H   H   N   N    UUU         F       I     T  \n"
        );
    }
}
