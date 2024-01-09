package org.javaFundamentals2.Question1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;

public class studentUtil {
        private static final Logger logger = LoggerFactory.getLogger(studentUtil.class);

        private static double[] calculateGPA(int[] studentIdList, char[][] studentsGrades) {
            if (studentIdList == null || studentsGrades == null || studentIdList.length != studentsGrades.length) {
                return null; // Parameter validation
            }

            int studentCount = studentIdList.length;
            double[] arr = new double[studentCount];

            for (int i = 0; i < studentCount; i++) {
                char[] temp = studentsGrades[i];

                if (temp == null || temp.length == 0) {
                    return null;
                }

                int gradesSize = temp.length;
                int sum = 0;

                for (int j = 0; j < gradesSize; j++) {
                    char temp1 = studentsGrades[i][j];

                    sum += switch (temp1) {
                        case 'A' -> 4;
                        case 'B' -> 3;
                        case 'C' -> 2;
                        default -> 0;
                    };
                }

                double gpa = (double) sum / (double) (gradesSize);
                arr[i] = gpa;
            }

            return arr;
        }

    public static int[] getStudentsByGPA(double lower, double higher, int[] studentIdList, char[][] studentsGrades) {
        if (lower < 0 || higher < 0 || lower > higher || studentIdList == null || studentsGrades == null || studentIdList.length != studentsGrades.length) {
            return null;
        }

        double[] gpaStudent = calculateGPA(studentIdList, studentsGrades);

        if (gpaStudent == null) {
            return null;
        }

        int count = 0;
        for (double gpa : gpaStudent) {
            if (gpa >= lower && gpa <= higher) {
                count++;
            }
        }

        int[] result = new int[count];
        int index = 0;

        for (int i = 0; i < studentIdList.length; i++) {
            if (gpaStudent[i] >= lower && gpaStudent[i] <= higher) {
                result[index++] = studentIdList[i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] studentIdList = {1001, 1002};
        char[][] studentsGrades = {{'A', 'A', 'A', 'B'}, {'A', 'B', 'B'}};

        double[] gpaStudent = calculateGPA(studentIdList, studentsGrades);


        int[] filteredStudents = getStudentsByGPA(3.2, 3.5, studentIdList, studentsGrades);
        logger.info(Arrays.toString(gpaStudent));
        logger.info(Arrays.toString(filteredStudents));
    }
}
