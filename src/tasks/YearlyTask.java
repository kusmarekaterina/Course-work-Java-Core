package src.tasks;

import java.time.LocalDateTime;

public class YearlyTask extends Task {

    public YearlyTask(String title, String description, TaskType taskType, LocalDateTime dateTime) {
        super(title, description, taskType, dateTime);
    }

    @Override
    public boolean appearsIn(LocalDateTime localDateTime) {
        return getDateTime().getDayOfYear() == localDateTime.getDayOfYear();
    }
}
