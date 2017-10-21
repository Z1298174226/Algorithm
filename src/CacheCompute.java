import java.util.concurrent.*;

/**
 * Created by qtfs on 2017/9/26.
 */
public class CacheCompute {
    private final ConcurrentHashMap<String, Future<String>> taskCache =
            new ConcurrentHashMap<String, Future<String>>();
    public String executionTask(final String taskName) {
        while (true) {
            Future<String> future = taskCache.get(taskName);
            if (future == null) {
                Callable<String> task = new Callable<String>() {
                    public String call() throws InterruptedException {
                        return taskName;
                    }
                };
                FutureTask<String> futureTask = new FutureTask<String>(task);
                future = taskCache.putIfAbsent(taskName, futureTask);
                if (future == null) {
                    future = futureTask;
                    futureTask.run();
                }
            }
            try{
                return future.get();
            }catch(CancellationException ex) {

            }catch(InterruptedException ex) {

            }catch(ExecutionException ex) {

            }
        }
    }
}
