package src.tasks;


import src.exceptions.TaskNotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Service {
    private static final Map<Integer, Task> currentTask = new HashMap<>();
    private static final Map<Integer, Task> archivedTask = new HashMap<>();
    public static void addTask(Scanner scanner) {
        try {
            System.out.println("Введите название задачи: ");
            String title = ValidateUtils.validateString(scanner.nextLine());
            System.out.println("Введите описание задачи: ");
            String description = ValidateUtils.validateString(scanner.nextLine());
            System.out.println("Введите тип задачи: WORK, PERSONAL");
            TaskType taskType = TaskType.valueOf(scanner.nextLine());
            System.out.println("Введите повторяемость задачи: 0 - Однократная, 1 - Ежедневная, 2 - Еженедельная, 3 - Ежемесячная, 4 - Ежегодная");
            int appear = scanner.nextInt();
            System.out.println("Введите дату: dd.MM.yyyy HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
            createTask(title, description, taskType, dateTime, appear);
            System.out.println("Задача создана");
        } catch (DateTimeParseException e) {
            System.out.println("Введите дату правильно");
        } catch (RuntimeException e) {
            System.out.println("Ошибка ввода");
        } finally {
            scanner.close();
        }
    }

    public static void createTask(String title, String description, TaskType taskType, LocalDateTime dateTime, int appear) {
        switch (appear) {
            case 0:
                Task oneTimeTask = new OneTimeTask(title, description, taskType, dateTime);
                currentTask.put(oneTimeTask.getId(), oneTimeTask);
            case 1:
                Task dailyTask = new DailyTask(title, description, taskType, dateTime);
                currentTask.put(dailyTask.getId(), dailyTask);
            case 2:
                Task weeklyTask = new WeeklyTask(title, description, taskType, dateTime);
                currentTask.put(weeklyTask.getId(), weeklyTask);
            case 3:
                Task monthlyTask = new MonthlyTask(title, description, taskType, dateTime);
                currentTask.put(monthlyTask.getId(), monthlyTask);
            case 4:
                Task yearlyTask = new YearlyTask(title, description, taskType, dateTime);
                currentTask.put(yearlyTask.getId(), yearlyTask);
            default:
                throw new IllegalStateException("Unexpected value: " + appear);
        }
    }

    public static void deleteTask(Scanner scanner) {
        System.out.println("Введите id задачи");
        int id = scanner.nextInt();
        if (!currentTask.containsKey(id)) {
            throw new TaskNotFoundException("Задача не найдена");
        } else {
            Task removedTask = currentTask.get(id);
            archivedTask.put(id, removedTask);
            currentTask.remove(id);
            System.out.println("Задача удалена");
        }
    }

    public static void getTaskByDay(Scanner scanner) {
        System.out.println("Введите дату: dd.MM.yyyy HH:mm");
        try {
            String date = scanner.next();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate localDate = LocalDate.parse(date, dateFormatter);
            List<Task> foundTasks = findTasksByDate(localDate);
            System.out.println("Задачи на " + localDate + ": ");
            for (Task task : foundTasks) {
                System.out.println(task);
            }
        } catch (DateTimeParseException e) {
            System.out.println("Введите дату правильно");
        } finally {
            scanner.close();
        }
    }

    public static List<Task> findTasksByDate(LocalDate date) {
        List<Task> tasks = new ArrayList<>();
        for (Task task : currentTask.values()) {
            if (task.appearsIn(date.atStartOfDay())) {
                tasks.add(task);
            }
        }
        return tasks;
    }

    public static void editTask(Scanner scanner) {
        System.out.println("Введите Id");
        int id = scanner.nextInt();
        if (!currentTask.containsKey(id)) {
            throw new TaskNotFoundException("Задача не найдена");
        } else {
            System.out.println("Выберите, что хотите отредактировать: 0 - заголовок, 1 - описание, 2 - дату");
            int index = scanner.nextInt();
            switch (index) {
                case 0 -> {
                    scanner.nextLine();
                    System.out.println("Введите новое название задачи: ");
                    String title = scanner.nextLine();
                    Task task = currentTask.get(id);
                    task.setTitle(title);
                }
                case 1 -> {
                    scanner.nextLine();
                    System.out.println("Введите новое описание задачи: ");
                    String description = scanner.nextLine();
                    Task task = currentTask.get(id);
                    task.setDescription(description);
                }
                case 2 -> {
                    try {
                        scanner.nextLine();
                        System.out.println("Введите новый тип: ");
                        LocalDateTime dateTime = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
                        Task task = currentTask.get(id);
                        task.setDateTime(dateTime);
                    } catch (DateTimeParseException e) {
                        System.out.println("Введите дату правильно");
                    }
                }
            }
        }
    }
}
