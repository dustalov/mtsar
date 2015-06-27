package mtsar.processors.task;

import mtsar.api.Process;
import mtsar.api.Task;
import mtsar.api.TaskAllocation;
import mtsar.api.Worker;
import mtsar.api.jdbi.TaskDAO;
import mtsar.processors.TaskAllocator;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.Optional;

public class RandomAllocator implements TaskAllocator {
    protected final Provider<Process> process;
    protected final TaskDAO taskDAO;

    @Inject
    public RandomAllocator(Provider<Process> processProvider, TaskDAO taskDAO) {
        this.process = processProvider;
        this.taskDAO = taskDAO;
    }

    @Override
    public Optional<TaskAllocation> allocate(Worker worker) {
        final Task task = taskDAO.random(process.get().getId());
        if (task == null) return Optional.empty();
        return Optional.ofNullable(TaskAllocation.create(worker, task));
    }
}
