import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Worker {

    private final String workerName;
    private final ConcurrentLinkedQueue<NamedTask> queue = new ConcurrentLinkedQueue<>();
    private final Thread thread;
    private AtomicInteger taskNumber = new AtomicInteger(0);

    private AtomicBoolean isStarted = new AtomicBoolean(false);
    private AtomicBoolean isStopped = new AtomicBoolean(false);
    private WorkerListener workerListener = null;

    public Worker(String workerName)
    {
        this.workerName = workerName;
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (!isStopped.get()) {
                        NamedTask task = queue.peek();
                        if (task != null) {
                            int currentTaskCount = taskNumber.getAndAdd(1);
                            try {
                                workerListener.onTaskStarted(currentTaskCount, task.name);
                                task.task.run(currentTaskCount);
                                workerListener.onTaskCompleted(currentTaskCount, task.name);
                                queue.remove(task);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }, "Worker" + workerName + "Thread");

    }


    void enqueueTask(String taskName, Task task) {
        queue.add(new NamedTask(taskName, task));
    }

    void start() {

        if (workerListener != null) {
            workerListener.onWorkerStarted();

        }
        if (!isStarted.get()) {
            isStarted.set(true);
            thread.start();
        }
        isStopped.set(false);

    }

    void stop() {
        isStopped.set(true);
        thread.interrupt();
        if (workerListener != null) {
            workerListener.onWorkerStopped();
        }
    }

    void setListener(WorkerListener workerListener) {
        this.workerListener = workerListener;
    }

    boolean isStarted() {
        return isStarted.get();
    }

    boolean isWorking() {
        return !isStopped.get() && !queue.isEmpty() ;
    }


    private static class NamedTask{
        private final String name;
        private final Task task;

        private NamedTask(String name, Task task) {
            this.name = name;
            this.task = task;
        }
    }

}

