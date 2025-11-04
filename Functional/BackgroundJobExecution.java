import java.util.*;
import java.util.concurrent.*;

class JobExecutor {
    private ExecutorService executor;
    
    public JobExecutor() {
        this.executor = Executors.newFixedThreadPool(3);
    }
    
    // Execute a task in the background using Runnable
    public void executeBackgroundJob(Runnable task) {
        executor.submit(task);
    }
    
    // Execute multiple tasks and return futures
    public List<Future<?>> executeMultipleJobs(List<Runnable> tasks) {
        List<Future<?>> futures = new ArrayList<>();
        for (Runnable task : tasks) {
            futures.add(executor.submit(task));
        }
        return futures;
    }
    
    public void shutdown() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }
}

public class BackgroundJobExecution {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Background Job Execution System:");
        System.out.println("================================");
        
        JobExecutor jobExecutor = new JobExecutor();
        
        // Example 1: Simple background job using Runnable
        System.out.println("1. Executing simple background jobs:");
        jobExecutor.executeBackgroundJob(() -> {
            System.out.println("Job 1 started on thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(2000); // Simulate work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Job 1 completed");
        });
        
        jobExecutor.executeBackgroundJob(() -> {
            System.out.println("Job 2 started on thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(1500); // Simulate work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Job 2 completed");
        });
        
        // Wait a bit to see the output
        Thread.sleep(100);
        System.out.println();
        
        // Example 2: Multiple jobs using a list
        System.out.println("2. Executing multiple background jobs:");
        List<Runnable> tasks = Arrays.asList(
            () -> {
                System.out.println("Task A started on thread: " + Thread.currentThread().getName());
                simulateWork(1000, "A");
                System.out.println("Task A completed");
            },
            () -> {
                System.out.println("Task B started on thread: " + Thread.currentThread().getName());
                simulateWork(2500, "B");
                System.out.println("Task B completed");
            },
            () -> {
                System.out.println("Task C started on thread: " + Thread.currentThread().getName());
                simulateWork(1800, "C");
                System.out.println("Task C completed");
            }
        );
        
        jobExecutor.executeMultipleJobs(tasks);
        Thread.sleep(100); // Allow jobs to start
        System.out.println();
        
        // Example 3: Data processing jobs
        System.out.println("3. Executing data processing jobs:");
        List<String> dataChunks = Arrays.asList("data1", "data2", "data3", "data4", "data5");
        
        for (int i = 0; i < dataChunks.size(); i++) {
            final int index = i;
            final String data = dataChunks.get(i);
            
            jobExecutor.executeBackgroundJob(() -> {
                System.out.println("Processing " + data + " on thread: " + Thread.currentThread().getName());
                simulateWork(1200, "Data-" + index);
                System.out.println(data + " processed successfully");
            });
        }
        
        Thread.sleep(100); // Allow jobs to start
        System.out.println();
        
        // Example 4: Report generation jobs
        System.out.println("4. Executing report generation jobs:");
        List<String> reportTypes = Arrays.asList("Sales", "Inventory", "Customer", "Finance");
        
        reportTypes.forEach(reportType -> {
            jobExecutor.executeBackgroundJob(() -> {
                System.out.println("Generating " + reportType + " report on thread: " + Thread.currentThread().getName());
                simulateWork(3000, reportType);
                System.out.println(reportType + " report generated");
            });
        });
        
        Thread.sleep(100); // Allow jobs to start
        System.out.println();
        
        // Example 5: Cleanup jobs
        System.out.println("5. Executing cleanup jobs:");
        jobExecutor.executeBackgroundJob(() -> {
            System.out.println("Starting cleanup job on thread: " + Thread.currentThread().getName());
            simulateWork(2000, "Cleanup");
            System.out.println("Cleanup job completed");
        });
        
        // Wait for all jobs to complete before shutting down
        System.out.println("\nWaiting for all jobs to complete...");
        Thread.sleep(5000); // Wait for jobs to finish
        
        jobExecutor.shutdown();
        
        System.out.println("\nAll background jobs completed.");
        System.out.println("\nUsing Runnable functional interface allows for easy background task execution");
        System.out.println("with lambda expressions for simple and clean asynchronous processing.");
    }
    
    // Helper method to simulate work with a specific identifier
    private static void simulateWork(int duration, String identifier) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            System.out.println(identifier + " job was interrupted");
            Thread.currentThread().interrupt();
        }
    }
}