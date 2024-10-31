package com.example.demo.util;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadUtils {

    /**
     * 设置空闲线程在终止之前等待的时间.
     */
    private static final long KEEP_ALIVE_TIME = 5L;
    private static final TimeUnit KEEP_ALIVE_TIME_UTIL = TimeUnit.SECONDS;

    /**
     * 可用核心数.
     * 某些设备的 CPU 会根据系统负载停用一个或多个内核。
     * 对于这些设备，availableProcessors() 会返回活跃内核的数量，该数量可能少于内核总数.
     */
    private static final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();

    /**
     * 任务队列.
     */
    private static final BlockingQueue<Runnable> WORK_QUEUE = new LinkedBlockingQueue<>();

    /**
     * 线程拒绝策略.
     */
    private static final RejectedExecutionHandler REJECT_HANDLER = new ThreadPoolExecutor.DiscardOldestPolicy();

    /**
     * ThreadPoolExecutor.
     */
    private static final ThreadPoolExecutor COMMON_POOL = new ThreadPoolExecutor(
            NUMBER_OF_CORES, NUMBER_OF_CORES, KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UTIL,
            WORK_QUEUE, target -> new Thread(target, "Common-Thread"), REJECT_HANDLER
    );

    /**
     * -
     * 在新线程执行任务.
     * -
     *
     * @param runnable runnable
     */
    public static void execute(Runnable runnable) {
        COMMON_POOL.execute(runnable);
    }
    
}
