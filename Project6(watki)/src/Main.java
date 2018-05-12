import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args) {

        Worker worker = new Worker("Zbysiu");
        worker.setListener(new WorkerListener() {
            @Override
            public void onWorkerStarted() {
                System.out.println("Worker start ");
            }

            @Override
            public void onWorkerStopped() {
                System.out.println("Worker stop ");
            }

            @Override
            public void onTaskStarted(int taskNumber, String taskName) {
                System.out.println("Task start ");
            }

            @Override
            public void onTaskCompleted(int taskNumber, String taskName) {
                System.out.println("Task zrobiony\n ");
            }
        });
        worker.enqueueTask("firstTask", taskNumber -> {
            System.out.println(" Task1");
            long currentTimeMillis = System.currentTimeMillis();
            while (System.currentTimeMillis() <= currentTimeMillis + 10000) {
            }

        });
        worker.enqueueTask("SecondTask", taskNumber -> {
            Thread.sleep(10000);
            System.out.println("Task2");
        });
        worker.enqueueTask("", taskNumber -> {

            System.out.println("Task3");
            long currentTimeMillis = System.currentTimeMillis();
            while (System.currentTimeMillis() <= currentTimeMillis + 10000) {

            }
        });

        worker.enqueueTask("FourthTask", taskNumber -> {  // Thread.yield() - powodouje pauze w biezacym watku i wywoluje inny do pracy

            long currentTimeMillis = System.currentTimeMillis();
            while (System.currentTimeMillis() <= currentTimeMillis + 10000) {
                Thread.yield();
            }
            System.out.println("Task4");
        });
        worker.enqueueTask("FifthTask", taskNumber -> {
            System.out.println("Task5");
            long currentTimeMillis = System.currentTimeMillis();
            while (System.currentTimeMillis() <= currentTimeMillis + 10000) {

            }
        });
        worker.start();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                worker.stop();

            }
        }, 15000);


        while (worker.isWorking()) {
            Thread.yield();
        }
        System.out.println("FINISH!");
    }


}
