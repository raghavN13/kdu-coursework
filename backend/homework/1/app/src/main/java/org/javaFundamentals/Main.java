package org.javaFundamentals;
import org.javaFundamentals.Student.Student;
import org.javaFundamentals.StudentRepository.StudentRepository;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        StudentRepository obj = new StudentRepository();
        Scanner sc = new Scanner(System.in);



        while (true) {
            System.out.println("Choose the option 1.Add Student 2.Retrieve Data 3.Upgrade Data 4.stop");
            int option = sc.nextInt();
            if(option==4){
                break;
            }
            if (option == 1) {
                obj.addStudent(obj.arr);
            } else if (option == 2) {
                obj.retrieve(obj.arr);
            } else if (option == 3) {
                obj.upgrade(obj.arr);
            } else {
                System.out.println("Invalid Choice!!");
            }
        }
    }
}