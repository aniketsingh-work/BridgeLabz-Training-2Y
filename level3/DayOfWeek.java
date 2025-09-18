package level3;

public class DayOfWeek {
    public static void main(String[] args) {
        // Check if correct number of arguments are provided
        if (args.length != 3) {
            System.out.println("Usage: java DayOfWeek m d y");
            System.out.println("Where m = month (1-12), d = day (1-31), y = year");
            return;
        }
        
        // Parse command line arguments
        int m = Integer.parseInt(args[0]);
        int d = Integer.parseInt(args[1]);
        int y = Integer.parseInt(args[2]);
        
        // Calculate day of week using the given formulas
        int y0 = y - (14 - m) / 12;
        int x = y0 + y0 / 4 - y0 / 100 + y0 / 400;
        int m0 = m + 12 * ((14 - m) / 12) - 2;
        int dayOfWeek = (d + x + (31 * m0) / 12) % 7;
        
        // Print the result (0 for Sunday, 1 for Monday, etc.)
        System.out.println(dayOfWeek);
    }
}