import java.util.*;

public class QueueProblems {
    
    // 1. Reverse a Queue
    public static <T> void reverseQueue(Queue<T> queue) {
        Stack<T> stack = new Stack<>();
        
        // Pop all elements from queue and push to stack
        while (!queue.isEmpty()) {
            stack.push(queue.remove());
        }
        
        // Pop all elements from stack and add back to queue
        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }
    }
    
    // 2. Generate Binary Numbers Using a Queue
    public static List<String> generateBinaryNumbers(int n) {
        List<String> result = new ArrayList<>();
        if (n <= 0) return result;
        
        Queue<String> queue = new LinkedList<>();
        queue.add("1");
        
        for (int i = 0; i < n; i++) {
            String current = queue.remove();
            result.add(current);
            
            // Add "0" and "1" to the current binary number
            queue.add(current + "0");
            queue.add(current + "1");
        }
        
        return result;
    }
    
    // 3. Hospital Triage System
    static class Patient {
        String name;
        int severity;
        
        public Patient(String name, int severity) {
            this.name = name;
            this.severity = severity;
        }
        
        @Override
        public String toString() {
            return name + "(" + severity + ")";
        }
    }
    
    public static Queue<Patient> hospitalTriageSystem(List<Patient> patients) {
        // Use a priority queue with a custom comparator (higher severity first)
        PriorityQueue<Patient> priorityQueue = new PriorityQueue<>((p1, p2) -> 
            Integer.compare(p2.severity, p1.severity)); // Descending order of severity
        
        // Add all patients to the priority queue
        priorityQueue.addAll(patients);
        
        // Process patients in order of priority
        Queue<Patient> treatmentOrder = new LinkedList<>();
        while (!priorityQueue.isEmpty()) {
            treatmentOrder.add(priorityQueue.remove());
        }
        
        return treatmentOrder;
    }
    
    // 4. Implement a Stack Using Queues
    static class StackUsingQueues<T> {
        private Queue<T> queue1;
        private Queue<T> queue2;
        
        public StackUsingQueues() {
            queue1 = new LinkedList<>();
            queue2 = new LinkedList<>();
        }
        
        public void push(T item) {
            // Add to queue2
            queue2.add(item);
            
            // Move all elements from queue1 to queue2
            while (!queue1.isEmpty()) {
                queue2.add(queue1.remove());
            }
            
            // Swap the queues
            Queue<T> temp = queue1;
            queue1 = queue2;
            queue2 = temp;
        }
        
        public T pop() {
            if (queue1.isEmpty()) {
                throw new RuntimeException("Stack is empty");
            }
            return queue1.remove();
        }
        
        public T top() {
            if (queue1.isEmpty()) {
                throw new RuntimeException("Stack is empty");
            }
            return queue1.peek();
        }
        
        public boolean isEmpty() {
            return queue1.isEmpty();
        }
    }
    
    // 5. Circular Buffer Simulation
    static class CircularBuffer<T> {
        private T[] buffer;
        private int capacity;
        private int size;
        private int front;
        private int rear;
        
        @SuppressWarnings("unchecked")
        public CircularBuffer(int capacity) {
            this.capacity = capacity;
            this.buffer = (T[]) new Object[capacity];
            this.size = 0;
            this.front = 0;
            this.rear = -1;
        }
        
        public void insert(T item) {
            if (size == capacity) {
                // Buffer is full, overwrite the oldest element
                front = (front + 1) % capacity;
                size--;
            }
            
            rear = (rear + 1) % capacity;
            buffer[rear] = item;
            size++;
        }
        
        public T remove() {
            if (size == 0) {
                throw new RuntimeException("Buffer is empty");
            }
            
            T item = buffer[front];
            buffer[front] = null; // Help GC
            front = (front + 1) % capacity;
            size--;
            return item;
        }
        
        public boolean isEmpty() {
            return size == 0;
        }
        
        public boolean isFull() {
            return size == capacity;
        }
        
        public int size() {
            return size;
        }
        
        @Override
        public String toString() {
            if (size == 0) return "[]";
            
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            
            for (int i = 0; i < size; i++) {
                int index = (front + i) % capacity;
                sb.append(buffer[index]);
                if (i < size - 1) sb.append(", ");
            }
            
            sb.append("]");
            return sb.toString();
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Queue Interface Problems ===");
        
        // Test 1: Reverse a Queue
        System.out.println("\n1. Reverse a Queue:");
        Queue<Integer> queue = new LinkedList<>(Arrays.asList(10, 20, 30));
        System.out.println("Original: " + queue);
        reverseQueue(queue);
        System.out.println("Reversed: " + queue);
        
        // Test 2: Generate Binary Numbers Using a Queue
        System.out.println("\n2. Generate Binary Numbers Using a Queue:");
        List<String> binaryNumbers = generateBinaryNumbers(5);
        System.out.println("First 5 binary numbers: " + binaryNumbers);
        
        // Test 3: Hospital Triage System
        System.out.println("\n3. Hospital Triage System:");
        List<Patient> patients = Arrays.asList(
            new Patient("John", 3),
            new Patient("Alice", 5),
            new Patient("Bob", 2)
        );
        System.out.println("Patients: " + patients);
        Queue<Patient> treatmentOrder = hospitalTriageSystem(patients);
        System.out.println("Treatment order: " + treatmentOrder);
        
        // Test 4: Implement a Stack Using Queues
        System.out.println("\n4. Implement a Stack Using Queues:");
        StackUsingQueues<Integer> stack = new StackUsingQueues<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("Top element: " + stack.top());
        System.out.println("Popped: " + stack.pop());
        System.out.println("Popped: " + stack.pop());
        System.out.println("Top element now: " + stack.top());
        
        // Test 5: Circular Buffer Simulation
        System.out.println("\n5. Circular Buffer Simulation:");
        CircularBuffer<Integer> buffer = new CircularBuffer<>(3);
        System.out.println("Buffer size=3");
        buffer.insert(1);
        buffer.insert(2);
        buffer.insert(3);
        System.out.println("After inserting 1, 2, 3: " + buffer);
        buffer.insert(4);
        System.out.println("After inserting 4: " + buffer);
    }
}