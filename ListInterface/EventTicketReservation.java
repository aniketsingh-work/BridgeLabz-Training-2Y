import java.util.*;

public class EventTicketReservation {
    
    static class Booking {
        private String bookingId;
        private String userId;
        private String userName;
        private String eventId;
        private String eventName;
        private Date bookingDate;
        private boolean isVip;
        
        public Booking(String bookingId, String userId, String userName, String eventId, 
                      String eventName, boolean isVip) {
            this.bookingId = bookingId;
            this.userId = userId;
            this.userName = userName;
            this.eventId = eventId;
            this.eventName = eventName;
            this.isVip = isVip;
            this.bookingDate = new Date();
        }
        
        // Getters
        public String getBookingId() { return bookingId; }
        public String getUserId() { return userId; }
        public String getUserName() { return userName; }
        public String getEventId() { return eventId; }
        public String getEventName() { return eventName; }
        public boolean isVip() { return isVip; }
        public Date getBookingDate() { return bookingDate; }
        
        @Override
        public String toString() {
            return bookingId + "(" + userName + ", " + eventName + ", VIP:" + isVip + ")";
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Booking booking = (Booking) obj;
            return Objects.equals(bookingId, booking.bookingId);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(bookingId);
        }
    }
    
    // Use List to store all bookings
    private List<Booking> allBookings;
    
    // Use Set to prevent duplicate user registrations
    private Set<String> registeredUsers;
    
    // Use Queue to process booking confirmations in order
    private Queue<Booking> bookingQueue;
    
    // Use PriorityQueue to prioritize VIP bookings
    private PriorityQueue<Booking> vipPriorityQueue;
    
    public EventTicketReservation() {
        this.allBookings = new ArrayList<>();
        this.registeredUsers = new HashSet<>();
        this.bookingQueue = new LinkedList<>();
        // Priority queue with custom comparator: VIP bookings first, then by booking date
        this.vipPriorityQueue = new PriorityQueue<>((b1, b2) -> {
            if (b1.isVip() && !b2.isVip()) {
                return -1; // b1 (VIP) comes first
            } else if (!b1.isVip() && b2.isVip()) {
                return 1; // b2 (VIP) comes first
            } else {
                // Both are VIP or both are not VIP, sort by booking date
                return b1.getBookingDate().compareTo(b2.getBookingDate());
            }
        });
    }
    
    // 1. Register new users and ensure no duplicates
    public boolean registerUser(String userId, String userName) {
        if (registeredUsers.contains(userId)) {
            System.out.println("User " + userId + " already registered!");
            return false;
        }
        
        registeredUsers.add(userId);
        System.out.println("User " + userName + " registered successfully.");
        return true;
    }
    
    // 2. Accept booking requests and queue them
    public void addBooking(Booking booking) {
        allBookings.add(booking);
        bookingQueue.add(booking);
        
        // Add to priority queue based on VIP status
        vipPriorityQueue.add(booking);
        
        System.out.println("Added booking to queue: " + booking);
    }
    
    // 3. Prioritize VIP users for faster confirmation
    public boolean confirmNextBooking() {
        if (vipPriorityQueue.isEmpty()) {
            System.out.println("No bookings to confirm");
            return false;
        }
        
        Booking booking = vipPriorityQueue.remove();
        
        // Remove from regular queue as well
        bookingQueue.remove(booking);
        
        System.out.println("Confirmed booking: " + booking.getBookingId() + 
                         " for " + booking.getUserName() + 
                         " (VIP: " + booking.isVip() + ")");
        
        return true;
    }
    
    // Alternative method to process bookings in regular order (FIFO)
    public boolean confirmNextBookingFIFO() {
        if (bookingQueue.isEmpty()) {
            System.out.println("No bookings in regular queue to confirm");
            return false;
        }
        
        Booking booking = bookingQueue.remove();
        
        // Also remove from priority queue
        vipPriorityQueue.remove(booking);
        
        System.out.println("Confirmed booking (FIFO): " + booking.getBookingId() + 
                         " for " + booking.getUserName());
        
        return true;
    }
    
    // 4. Confirm and store bookings in the list
    public List<Booking> getAllBookings() {
        return new ArrayList<>(allBookings);
    }
    
    // Getters
    public Set<String> getRegisteredUsers() {
        return new HashSet<>(registeredUsers);
    }
    
    public Queue<Booking> getBookingQueue() {
        return new LinkedList<>(bookingQueue);
    }
    
    public PriorityQueue<Booking> getVipPriorityQueue() {
        return new PriorityQueue<>(vipPriorityQueue);
    }
    
    public static void main(String[] args) {
        System.out.println("=== Event Ticket Reservation System ===");
        
        EventTicketReservation reservationSystem = new EventTicketReservation();
        
        // Register users
        reservationSystem.registerUser("U001", "Alice Johnson");
        reservationSystem.registerUser("U002", "Bob Smith");
        reservationSystem.registerUser("U003", "Charlie Brown");
        reservationSystem.registerUser("U001", "Alice Duplicate"); // This should fail
        
        System.out.println("\n1. Registered users: " + reservationSystem.getRegisteredUsers().size());
        
        // Add some bookings (including VIP)
        reservationSystem.addBooking(new Booking("B01", "U001", "Alice Johnson", "E001", "Concert", false));
        reservationSystem.addBooking(new Booking("B002", "U002", "Bob Smith", "E001", "Concert", true)); // VIP
        reservationSystem.addBooking(new Booking("B003", "U003", "Charlie Brown", "E002", "Theater", false));
        reservationSystem.addBooking(new Booking("B004", "U001", "Alice Johnson", "E003", "Sports", true)); // VIP
        reservationSystem.addBooking(new Booking("B05", "U002", "Bob Smith", "E002", "Theater", false));
        
        System.out.println("\n2. Total bookings: " + reservationSystem.getAllBookings().size());
        
        System.out.println("\n3. Processing bookings with VIP priority:");
        while (reservationSystem.getVipPriorityQueue().size() > 0) {
            reservationSystem.confirmNextBooking();
        }
        
        System.out.println("\n4. All bookings in system:");
        for (Booking booking : reservationSystem.getAllBookings()) {
            System.out.println(" " + booking);
        }
    }
}