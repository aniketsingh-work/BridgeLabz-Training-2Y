package library.members;

public class Member {
    private String memberId;
    private String name;
    private String email;
    
    public Member(String memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
    }
    
    // Getters
    public String getMemberId() {
        return memberId;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void displayMemberDetails() {
        System.out.println("Member ID: " + memberId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
    }
}