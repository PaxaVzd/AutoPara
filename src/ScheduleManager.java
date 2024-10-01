import java.awt.Desktop;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScheduleManager {
    private List<Pair> schedule;
    private final String filePath = "rozklad.txt";

    public ScheduleManager() {
        this.schedule = new ArrayList<>();
        loadSchedule();
    }

    public void addPair(Pair pair) {
        schedule.add(pair);
        saveSchedule();
    }

    public void removePair(int index) {
        if (index >= 0 && index < schedule.size()) {
            schedule.remove(index);
            saveSchedule();
        } else {
            System.out.println("Невірний індекс пари.");
        }
    }

    public void editPair(int index, Pair newPair) {
        if (index >= 0 && index < schedule.size()) {
            schedule.set(index, newPair);
            saveSchedule();
        } else {
            System.out.println("Невірний індекс пари.");
        }
    }

    public void displaySchedule() {
        if (schedule.isEmpty()) {
            System.out.println("Розклад порожній.");
        } else {
            System.out.printf("%-5s %-20s %-20s %-20s %-10s%n", "№", "Дисципліна", "Викладач", "Посилання", "День");
            System.out.println("----------------------------------------------------------------------------");
            for (int i = 0; i < schedule.size(); i++) {
                Pair pair = schedule.get(i);
                System.out.printf("%-5d %-20s %-20s %-20s %-10s%n", i + 1, pair.subject, pair.teacher, pair.meetLink, pair.day);
            }
        }
    }

    public void joinClass(int index) {
        if (index >= 0 && index < schedule.size()) {
            String link = schedule.get(index).meetLink;
            try {
                Desktop.getDesktop().browse(new URI(link));
                System.out.println("Відкрито клас за посиланням: " + link);
            } catch (IOException | URISyntaxException e) {
                System.out.println("Не вдалося відкрити посилання.");
            }
        } else {
            System.out.println("Невірний індекс пари.");
        }
    }

    private void saveSchedule() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Pair pair : schedule) {
                writer.write(pair.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Помилка збереження файлу.");
        }
    }

    private void loadSchedule() {
        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                schedule.add(Pair.fromString(line));
            }
        } catch (IOException e) {
            System.out.println("Помилка завантаження файлу.");
        }
    }

    public void choosePair() {
        displaySchedule();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Виберіть пару для підключення (введіть номер): ");
        int choice = scanner.nextInt() - 1; // зменшити на 1 для індексації з 0
        joinClass(choice);
    }
}
