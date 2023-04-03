package src.tasks;

import java.time.LocalDateTime;

public class MonthlyTask extends Task {


    public MonthlyTask(String title, String description, TaskType taskType, LocalDateTime dateTime) {
        super(title, description, taskType, dateTime);
    }

    @Override
    public boolean appearsIn(LocalDateTime localDateTime) {
        return getDateTime().getDayOfMonth() == localDateTime.getDayOfMonth();
    }
}
