import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

public class ThreadPool {

    private PoolWorker[] pool;
    private Deque<Runnable> tasks;

    public static ThreadPool newPool(int threadsCount){
        ThreadPool threadPool = new ThreadPool();
        threadPool.pool = new PoolWorker[threadsCount];
        threadPool.tasks = new ConcurrentLinkedDeque<>();

        for(int i = 0; i < threadPool.pool.length; i++){
            threadPool.pool[i].start();
        }
        return threadPool;
    }
   public void submit(Runnable r){
        synchronized (tasks){
            tasks.addLast(r);
            tasks.notify();
        }

   }

    private class PoolWorker extends Thread{
        @Override
        public void run(){
            Runnable r;
            while (true){
                synchronized (tasks){
                    while(tasks.isEmpty()){

                        try {
                            tasks.wait();
                        } catch (InterruptedException e) {
                        }

                    }
                    r = (Runnable) tasks.removeFirst();
                }
                try {
                    r.run();
                } catch (Exception e){}
            }
        }
    }
}
