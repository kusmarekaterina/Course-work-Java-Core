package src.tasks;

import java.time.LocalDateTime;

public class OneTimeTask extends Task{


    public OneTimeTask(String title, String description, TaskType taskType, LocalDateTime dateTime) {
        super(title, description, taskType, dateTime);
    }

    @Override
    public boolean appearsIn(LocalDateTime localDateTime) {
        return getDateTime().toLocalDate().equals(localDateTime.toLocalDate());
    }
}
