import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Conveyor implements Runnable {
    private int id;
    private int sleepTime;
    private volatile Deque<Task> queue;
    private Conveyor nextConveyor;

    public Conveyor(int id, int sleepTime) {
        this.id = id;
        this.sleepTime = sleepTime;
        queue = new ArrayDeque<>();
    }

    public Conveyor(int id, int sleepTime, Conveyor nextConveyor) {
        this(id, sleepTime);
        this.nextConveyor = nextConveyor;
    }

    public Conveyor(int id, int sleepTime, List<Task> tasks, Conveyor nextConveyor) {
        this(id, sleepTime, nextConveyor);
        queue.addAll(tasks);
    }

    @Override
    public void run() {
        State state = State.OK;
        while (state != State.FINISH) {
            state = processTask();
            if (state == State.EMPTY) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public State processTask() {
        Task task = null;
        synchronized (queue) {
            if (queue.size() > 0) {
                task = queue.remove();
            }
        }

        if (task != null) {
            if (task.isLast()) {
                if (nextConveyor != null)
                    nextConveyor.addTask(task);
                return State.FINISH;
            }
            action(task);
            if (nextConveyor != null) {
                nextConveyor.addTask(task);
            }
        } else {
            return State.EMPTY;
        }
        return State.OK;
    }

    protected void action(Task task) {
        task.timeStart[id] = System.currentTimeMillis();
        System.out.println(id + " start " + task.getId() + " " + task.timeStart[id]);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        task.timeEnd[id] = System.currentTimeMillis();
        System.out.println(id + " end " + task.getId() + " " + task.timeEnd[id]);
    }

    public void addTask(Task task) {
        synchronized (queue) {
            queue.add(task);
        }
    }

    enum State {
        EMPTY,
        FINISH,
        OK
    }
}
