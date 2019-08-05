package com.stream;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

public class ForkJoinCountTask extends RecursiveTask<Integer> {
    private static int length = 5;
    private int start;
    private int end;

    public ForkJoinCountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        boolean isSum = end - start <= length;
        if (isSum) {
            return IntStream.rangeClosed(start, end).sum();
        }
        int middle = (end - start) / 2;
        ForkJoinCountTask leftTask = new ForkJoinCountTask(start, middle);
        ForkJoinCountTask rightTask = new ForkJoinCountTask(middle + 1, end);
        // 执行子任务
        leftTask.fork();
        rightTask.fork();

        Integer left = leftTask.join();
        Integer right = rightTask.join();
        return left + right;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinCountTask task = new ForkJoinCountTask(1, 10);
        ForkJoinTask<Integer> submit = pool.submit(task);
        System.out.println(submit.get());

        /**
         * 1.异常1：
         *      -- java.util.concurrent.ExecutionException:java.lang.StackOverflowError
         *      -- 栈溢出，这是线程栈一直入栈不出栈导致的，
         *      --   可以通过-Xss10m来扩大栈的内存。
         * 2.
         * Exception in thread "ForkJoinPool-1-worker-4" java.lang.NoClassDefFoundError: Could not initialize class java.util.concurrent.locks.AbstractQueuedSynchronizer$Node
         * 	at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquire(AbstractQueuedSynchronizer.java:1198)
         * 	at java.util.concurrent.locks.ReentrantLock$NonfairSync.lock(ReentrantLock.java:209)
         * 	at java.util.concurrent.locks.ReentrantLock.lock(ReentrantLock.java:285)
         * 	at java.util.concurrent.ForkJoinTask.recordExceptionalCompletion(ForkJoinTask.java:464)
         * 	at java.util.concurrent.ForkJoinTask.setExceptionalCompletion(ForkJoinTask.java:491)
         * 	at java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:291)
         * 	at java.util.concurrent.ForkJoinPool$WorkQueue.runTask(ForkJoinPool.java:1056)
         * 	at java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1692)
         * 	at java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:157)
         *
         * 	参考他人解决办法：https://juejin.im/post/5cf8608f6fb9a07ed911b15d
         * 	-- 这个导致的原因是因为子任务的处理长度不平衡。我们需要对原来的长度进行计算处理。
         *
         *  疑问：如何平衡？
         *
         */
    }
}
