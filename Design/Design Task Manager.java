class TaskManager {
    private static class Task {
        int userId, taskId, priority;
        Task(int userId, int taskId, int priority) {
            this.userId = userId;
            this.taskId = taskId;
            this.priority = priority;
        }
    }

    private Map<Integer, Task> idMap; // taskId -> Task
    private TreeSet<Task> prioritySet; // tasks ordered by priority

    public TaskManager(List<List<Integer>> tasks) {
        idMap = new HashMap<>();
        prioritySet = new TreeSet<>((a, b) -> {
            if (a.priority != b.priority) return b.priority - a.priority; // max heap
            return b.taskId - a.taskId; // tie-breaker by taskId
        });

        for (List<Integer> t : tasks) {
            Task task = new Task(t.get(0), t.get(1), t.get(2));
            idMap.put(task.taskId, task);
            prioritySet.add(task);
        }
    }

    public void add(int userId, int taskId, int priority) {
        Task task = new Task(userId, taskId, priority);
        idMap.put(taskId, task);
        prioritySet.add(task);
    }

    public void edit(int taskId, int newPriority) {
        if (idMap.containsKey(taskId)) {
            Task task = idMap.get(taskId);
            prioritySet.remove(task); // remove old
            task.priority = newPriority;
            prioritySet.add(task); // reinsert updated
        }
    }

    public void rmv(int taskId) {
        if (idMap.containsKey(taskId)) {
            Task task = idMap.remove(taskId);
            prioritySet.remove(task);
        }
    }

    public int execTop() {
        if (!prioritySet.isEmpty()) {
            Task task = prioritySet.pollFirst(); // get max
            idMap.remove(task.taskId);
            return task.userId;
        }
        return -1;
    }
}

/**
 * Usage:
 * TaskManager obj = new TaskManager(tasks);
 * obj.add(userId, taskId, priority);
 * obj.edit(taskId, newPriority);
 * obj.rmv(taskId);
 * int userId = obj.execTop();
 */
