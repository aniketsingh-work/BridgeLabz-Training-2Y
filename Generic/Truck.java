class Truck extends Vehicle {
    public Truck(String make, String model) {
        super(make, model);
    }
    
    @Override
    public void start() {
        System.out.println("Starting the truck: " + make + " " + model);
    }
}