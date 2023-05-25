package it.voxibyte.voxiapi.task;

import it.voxibyte.voxiapi.VoxiPlugin;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

/**
 * Provides a simple way to schedule tasks and cancel them
 */
public class Tasker {
    private Tasker() {}

    public static BukkitTask schedule(Runnable runnable, long delay) {
        return Bukkit.getScheduler().runTaskLater(VoxiPlugin.getJavaPlugin(), runnable, delay);
    }

    public static BukkitTask scheduleAsync(Runnable runnable, long delay) {
        return Bukkit.getScheduler().runTaskLaterAsynchronously(VoxiPlugin.getJavaPlugin(), runnable, delay);
    }

    public static BukkitTask scheduleRepeating(Runnable runnable, long delay, long every) {
        return Bukkit.getScheduler().runTaskTimer(VoxiPlugin.getJavaPlugin(), runnable, delay, every);
    }

    public static BukkitTask scheduleRepeatingAsync(Runnable runnable, long delay, long every) {
        return Bukkit.getScheduler().runTaskTimerAsynchronously(VoxiPlugin.getJavaPlugin(), runnable, delay, every);
    }
}
