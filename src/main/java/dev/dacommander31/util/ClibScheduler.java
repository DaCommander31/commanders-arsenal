package dev.dacommander31.util;


import dev.dacommander31.cmda.data.ClibRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ClibScheduler {

    private static final HashMap<ClibRunnable<?>, Long> scheduledTasks = new HashMap<>();


    public static <T> void scheduleTask(Consumer<T> consumer, T input, long ticks) {
        scheduledTasks.put(new ClibRunnable<>(consumer, input), ticks);
    }

    public static void tick() {
        for(Map.Entry<ClibRunnable<?>, Long> entry : new HashMap<>(scheduledTasks).entrySet()) {
            if(entry.getValue() <= 0) {
                runTask(entry.getKey());
            } else {
                scheduledTasks.replace(entry.getKey(), scheduledTasks.get(entry.getKey()) - 1);
            }
        }
    }


    private static void runTask(ClibRunnable<?> runnable) {
        runnable.run();
        scheduledTasks.remove(runnable);
    }


}
