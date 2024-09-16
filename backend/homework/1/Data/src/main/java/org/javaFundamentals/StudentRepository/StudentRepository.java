package org.javaFundamentals.StudentRepository;
import org.javaFundamentals.Student.Student;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





public class StudentRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentRepository.class);
    public ArrayList<Student>arr =  new ArrayList<>();

    static int idAllott = 1;


    public void addStudent(ArrayList<Student>arr){

        Scanner obj = new Scanner(System.in);


        System.out.println("Enter the number of entries ");
        int entries = obj.nextInt();
        obj.nextLine();

        for(int i=0;i<entries;i++){
            Student s = new Student();
            System.out.println("Enter the Name of the Student");

            String Name = obj.next();
            s.setName(Name);
            s.setId(idAllott);

            System.out.println("Enter the age of the Student");
            int Age = obj.nextInt();
            s.setAge(Age);
            System.out.println("Enter the Grade of the Student");
            obj.nextLine();
            String Grade = obj.nextLine();
            s.setGrade(Grade);


            idAllott++;

            arr.add(s);
        }



        LOGGER.info("Student Added and Data set");
    }

    public  void retrieve(ArrayList<Student>arr){
        Scanner obj = new Scanner(System.in);
        System.out.println("Enter the id of the student that you want to retrieve");

        int ID = obj.nextInt();
        for(int i=0;i<arr.size();i++){
            Student s = arr.get(i);
            if(s.id==ID){
                System.out.println("The id of the student is "+s.id);
                System.out.println("The Name of the student is "+s.name);
                System.out.println("The age of the student is "+s.age);
                System.out.println("The grade of the student is "+s.grade);
            }
        }
        LOGGER.info("Student Data Retrieved");
    }

    public  void upgrade(ArrayList<Student>arr){
        Scanner obj = new Scanner(System.in);
        System.out.println("Enter the id of the student ");
        int ID = obj.nextInt();
        Student s = arr.get(0);
        for(int i=0 ;i<arr.size() ;i++){
            if(arr.get(i).id==ID){
                s = arr.get(i);
            }
        }


        System.out.println("Enter the field that you want to upgrade 1.Name 2.Age 3.Grade");
        int option = obj.nextInt();
        obj.nextLine();
        if(option==1){
            System.out.println("Enter the new name ");
            String newName = obj.nextLine();
            s.name = newName;
        }
        else if(option==2){
            System.out.println("Enter the new age ");
            int  newAge = obj.nextInt();
            s.age = (int)newAge;
        }
        else if(option==3){
            System.out.println("Enter the new grade ");
            String newGrade= obj.nextLine();
            s.grade = newGrade;
        }
        else{
            System.out.println("Invalid Choice!!");
        }

        LOGGER.info("Student Data Upgraded");
    }




}
