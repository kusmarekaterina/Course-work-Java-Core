package src.tasks;

import java.time.LocalDateTime;

public class WeeklyTask extends Task{


    public WeeklyTask(String title, String description, TaskType taskType, LocalDateTime dateTime) {
        super(title, description, taskType, dateTime);
    }

    @Override
    public boolean appearsIn(LocalDateTime localDateTime) {
        return getDateTime().getDayOfWeek() == localDateTime.getDayOfWeek();
    }
}
