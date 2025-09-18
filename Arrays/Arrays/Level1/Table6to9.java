import java.util.Scanner;
public class Table6to9 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your number: ");
        int number = sc.nextInt();

        int[] arr = new int[10];
        if(number >= 6 && number <= 9){
            for(int i=0; i < arr.length; i++){
                arr[i] = number*(i+1);
    
            }
            for(int i=0; i < arr.length; i++){
                System.out.println(number+ " * " +(i+1)+ " = " +arr[i]);
    
            }
        }
        else{
            System.out.println("Invalid Input");
        }
        sc.close();


        
    }

}