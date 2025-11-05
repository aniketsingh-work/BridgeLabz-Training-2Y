class Bike extends Vehicle {
    public Bike(String make, String model) {
        super(make, model);
    }
    
    @Override
    public void start() {
        System.out.println("Starting the bike: " + make + " " + model);
    }
}