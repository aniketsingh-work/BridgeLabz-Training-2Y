import java.util.*;

public class HospitalPatientManagement {
    
    static class Patient {
        private String patientId;
        private String name;
        private int age;
        private String condition;
        private Date admissionDate;
        
        public Patient(String patientId, String name, int age, String condition) {
            this.patientId = patientId;
            this.name = name;
            this.age = age;
            this.condition = condition;
            this.admissionDate = new Date();
        }
        
        // Getters
        public String getPatientId() { return patientId; }
        public String getName() { return name; }
        public int getAge() { return age; }
        public String getCondition() { return condition; }
        public Date getAdmissionDate() { return admissionDate; }
        
        @Override
        public String toString() {
            return name + "(ID:" + patientId + ", Age:" + age + ", Condition:" + condition + ")";
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Patient patient = (Patient) obj;
            return Objects.equals(patientId, patient.patientId);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(patientId);
        }
    }
    
    // Use Set to store admitted patients (avoid duplicates)
    private Set<Patient> admittedPatients;
    
    // Use Queue for patients waiting to be treated
    private Queue<Patient> treatmentQueue;
    
    // Use Stack for recently discharged patients (for quick re-admission if needed)
    private Stack<Patient> recentlyDischarged;
    
    // Use List to maintain total patient history
    private List<Patient> patientHistory;
    
    public HospitalPatientManagement() {
        this.admittedPatients = new HashSet<>();
        this.treatmentQueue = new LinkedList<>();
        this.recentlyDischarged = new Stack<>();
        this.patientHistory = new ArrayList<>();
    }
    
    // 1. Admit patients and queue them for treatment
    public boolean admitPatient(Patient patient) {
        if (admittedPatients.contains(patient)) {
            System.out.println("Patient " + patient.getName() + " is already admitted!");
            return false;
        }
        
        admittedPatients.add(patient);
        treatmentQueue.add(patient);
        patientHistory.add(patient);
        System.out.println("Admitted patient: " + patient);
        return true;
    }
    
    // 2. Treat patients in order of arrival
    public Patient treatNextPatient() {
        if (treatmentQueue.isEmpty()) {
            System.out.println("No patients waiting for treatment");
            return null;
        }
        
        Patient patient = treatmentQueue.remove();
        System.out.println("Treating patient: " + patient.getName());
        
        // In a real system, we might update the patient's condition here
        return patient;
    }
    
    // 3. Discharge patients and push them to the stack
    public boolean dischargePatient(String patientId) {
        Patient patientToDischarge = null;
        
        // Find patient in admitted patients
        for (Patient patient : admittedPatients) {
            if (patient.getPatientId().equals(patientId)) {
                patientToDischarge = patient;
                break;
            }
        }
        
        if (patientToDischarge == null) {
            System.out.println("Patient with ID " + patientId + " not found");
            return false;
        }
        
        // Remove from admitted patients and treatment queue
        admittedPatients.remove(patientToDischarge);
        treatmentQueue.remove(patientToDischarge);
        
        // Add to recently discharged stack
        this.recentlyDischarged.push(patientToDischarge);
        
        System.out.println("Discharged patient: " + patientToDischarge.getName());
        return true;
    }
    
    // 4. Allow re-admission of recently discharged patients
    public boolean reAdmitPatient() {
        if (recentlyDischarged.isEmpty()) {
            System.out.println("No recently discharged patients to re-admit");
            return false;
        }
        
        Patient patient = this.recentlyDischarged.pop();
        
        // Re-admit the patient
        admittedPatients.add(patient);
        treatmentQueue.add(patient);
        
        System.out.println("Re-admitted patient: " + patient.getName());
        return true;
    }
    
    // Alternative method to re-admit a specific patient from the stack
    public boolean reAdmitSpecificPatient(String patientId) {
        Stack<Patient> tempStack = new Stack<>();
        Patient patientToReAdmit = null;
        
        // Find the patient in the recently discharged stack
        while (!this.recentlyDischarged.isEmpty()) {
            Patient patient = this.recentlyDischarged.pop();
            if (patient.getPatientId().equals(patientId)) {
                patientToReAdmit = patient;
                break;
            }
            tempStack.push(patient);
        }
        
        // Put back the other patients
        while (!tempStack.isEmpty()) {
            this.recentlyDischarged.push(tempStack.pop());
        }
        
        if (patientToReAdmit == null) {
            System.out.println("Patient with ID " + patientId + " not found in recently discharged");
            return false;
        }
        
        // Re-admit the patient
        admittedPatients.add(patientToReAdmit);
        treatmentQueue.add(patientToReAdmit);
        
        System.out.println("Re-admitted patient: " + patientToReAdmit.getName());
        return true;
    }
    
    // Getters
    public Set<Patient> getAdmittedPatients() {
        return new HashSet<>(admittedPatients);
    }
    
    public Queue<Patient> getTreatmentQueue() {
        return new LinkedList<>(treatmentQueue);
    }
    
    public Stack<Patient> getRecentlyDischarged() {
        return (Stack<Patient>) this.recentlyDischarged.clone();
    }
    
    public List<Patient> getPatientHistory() {
        return new ArrayList<>(patientHistory);
    }
    
    public static void main(String[] args) {
        System.out.println("=== Hospital Patient Management System ===");
        
        HospitalPatientManagement hospital = new HospitalPatientManagement();
        
        // Admit some patients
        hospital.admitPatient(new Patient("P001", "John Doe", 45, "Fever"));
        hospital.admitPatient(new Patient("P002", "Jane Smith", 32, "Broken Arm"));
        hospital.admitPatient(new Patient("P003", "Bob Johnson", 67, "Heart Problem"));
        
        // Try to admit a duplicate patient
        hospital.admitPatient(new Patient("P001", "John Duplicate", 45, "Fever"));
        
        System.out.println("\n1. Admitted patients: " + hospital.getAdmittedPatients().size());
        for (Patient patient : hospital.getAdmittedPatients()) {
            System.out.println(" " + patient);
        }
        
        System.out.println("\n2. Treatment queue size: " + hospital.getTreatmentQueue().size());
        
        // Treat some patients
        System.out.println("\n3. Treating patients:");
        for (int i = 0; i < 2; i++) {
            hospital.treatNextPatient();
        }
        
        System.out.println("\n4. Treatment queue after treating 2 patients: " + 
                          hospital.getTreatmentQueue().size());
        
        // Discharge a patient
        hospital.dischargePatient("P001");
        
        System.out.println("\n5. Recently discharged patients: " + 
                          hospital.getRecentlyDischarged().size());
        
        // Re-admit a patient
        hospital.reAdmitPatient();
        
        System.out.println("\n6. Admitted patients after re-admission: " + 
                          hospital.getAdmittedPatients().size());
        
        System.out.println("\n7. Patient history: " + hospital.getPatientHistory().size());
        for (Patient patient : hospital.getPatientHistory()) {
            System.out.println(" " + patient);
        }
    }
}