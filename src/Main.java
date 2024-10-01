import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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

            int choice = getIntInput(scanner);
            if (choice == 0) continue;

            switch (choice) {
                case 1:
                    addPair(scanner, scheduleManager);
                    break;
                case 2:
                    removePair(scanner, scheduleManager);
                    break;
                case 3:
                    editPair(scanner, scheduleManager);
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

    private static int getIntInput(Scanner scanner) {
        while (true) {
            try {
                String input = scanner.nextLine();
                if (input.equals("0")) {
                    return 0;
                }
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Невірний ввід. Будь ласка, введіть число (0 для повернення): ");
            }
        }
    }

    private static void addPair(Scanner scanner, ScheduleManager scheduleManager) {
        System.out.print("Введіть назву дисципліни (0 для повернення): ");
        String subject = scanner.nextLine();
        if (subject.equals("0")) return;

        System.out.print("Введіть ПІБ викладача (0 для повернення): ");
        String teacher = scanner.nextLine();
        if (teacher.equals("0")) return;

        System.out.print("Введіть посилання на Meet (0 для повернення): ");
        String meetLink = scanner.nextLine();
        if (meetLink.equals("0")) return;

        System.out.print("Введіть день (натисніть Enter, щоб пропустити): ");
        String day = scanner.nextLine();
        if (day.isEmpty()) {
            day = "Не вказано";
        }

        scheduleManager.addPair(new Pair(subject, teacher, meetLink, day));
    }

    private static void removePair(Scanner scanner, ScheduleManager scheduleManager) {
        System.out.print("Введіть номер пари для видалення (0 для повернення): ");
        int removeIndex = getIntInput(scanner);
        if (removeIndex == 0) return;

        scheduleManager.removePair(removeIndex - 1);
    }

    private static void editPair(Scanner scanner, ScheduleManager scheduleManager) {
        System.out.print("Введіть номер пари для редагування (0 для повернення): ");
        int editIndex = getIntInput(scanner);
        if (editIndex == 0) return;

        System.out.print("Введіть нову назву дисципліни (0 для повернення): ");
        String newSubject = scanner.nextLine();
        if (newSubject.equals("0")) return;

        System.out.print("Введіть новий ПІБ викладача (0 для повернення): ");
        String newTeacher = scanner.nextLine();
        if (newTeacher.equals("0")) return;

        System.out.print("Введіть нове посилання на Meet (0 для повернення): ");
        String newMeetLink = scanner.nextLine();
        if (newMeetLink.equals("0")) return;

        System.out.print("Введіть новий день (натисніть Enter, щоб пропустити): ");
        String newDay = scanner.nextLine();
        if (newDay.isEmpty()) {
            newDay = "Не вказано";
        }

        scheduleManager.editPair(editIndex - 1, new Pair(newSubject, newTeacher, newMeetLink, newDay));
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
