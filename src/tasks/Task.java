package src.tasks;

import java.time.LocalDateTime;
import java.util.Objects;


public abstract class Task {
    private static int counter = 0;
    private String title;
    private final TaskType taskType;
    private final int id;
    private LocalDateTime dateTime;
    private String description;

    public Task(String title, String description, TaskType taskType, LocalDateTime dateTime) {
        this.title = ValidateUtils.validateString(title);
        this.description = ValidateUtils.validateString(description);
        this.dateTime = dateTime;
        this.taskType = taskType;
        this.id = counter++;
    }

    public String getTitle() {
        return title;
    }

    public TaskType getType() {
        return taskType;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDateTime(LocalDateTime dateTime) {this.dateTime = dateTime;}

    public abstract boolean appearsIn (LocalDateTime localDateTime);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(title, task.title) && Objects.equals(taskType, task.taskType) && Objects.equals(dateTime, task.dateTime) && Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, taskType, id, dateTime, description);
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", taskType=" + taskType +
                ", id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                '}';
    }
}

