package com.generation.java;

import com.generation.model.Course;
import com.generation.model.Student;
import com.generation.service.CourseService;
import com.generation.service.StudentService;
import com.generation.utils.PrinterHelper;

import java.text.ParseException;
import java.util.Scanner;

public class Main
{
    public static void main( String[] args )
            throws ParseException
    {
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        Scanner scanner = new Scanner( System.in );
        int option;
        do
        {
            PrinterHelper.showMainMenu();
            option = scanner.nextInt();
            switch ( option )
            {
                case 1:
                    registerStudent( studentService, scanner );
                    break;
                case 2:
                    findStudent( studentService, scanner );
                    break;
                case 3:
                    gradeStudent( studentService, courseService, scanner );
                    break;
                case 4:
                    enrollCourse( studentService, courseService, scanner );
                    break;
                case 5:
                    showStudentsSummary( studentService, scanner );
                    break;
                case 6:
                    showCoursesSummary( courseService, scanner );
                    break;
                case 7:
                    showPassedCourses( studentService, scanner );
                    break;
            }
        }
        while ( option != 8 );
    }

    private static void enrollCourse( StudentService studentService, CourseService courseService,
                                               Scanner scanner )
    {
        System.out.println( "Insert student ID" );
        String studentId = scanner.next();
        Student student = studentService.findStudent( studentId );
        if ( student == null )
        {
            System.out.println( "Invalid Student ID" );
            return;
        }
        System.out.println( student );
        System.out.println( "Insert course ID" );
        String courseId = scanner.next();
        Course course = courseService.getCourse( courseId );
        if ( course == null )
        {
            System.out.println( "Invalid Course ID" );
            return;
        }
        System.out.println( course );
        studentService.enrollToCourse( studentId, course );
        System.out.println( "Student with ID: " + studentId + " enrolled successfully to " + courseId );

    }

    private static void showCoursesSummary( CourseService courseService, Scanner scanner )
    {
        courseService.showSummary();
    }

    private static void showStudentsSummary( StudentService studentService, Scanner scanner )
    {
        if (!studentService.showSummary())
        {
            System.out.println("No Student Yet");
        }
    }

    private static void gradeStudent( StudentService studentService, CourseService courseService, Scanner scanner )
    {
        float courseGrade;

        // get student ID input
        Student student = getStudentInformation( studentService, scanner );
        System.out.println( "Enrolled course:" );
        studentService.showCoursesRegistered(student.getId());

        // get course ID input
        System.out.println( "Insert course ID to be graded:" );
        String courseId = scanner.next();

        // get grades input for specific course
        Course newCourse = courseService.getCourse(courseId);
        int count = 0;
        do {
            System.out.println("Insert course grade for: " + newCourse.getName());
            if (count > 0) {
                System.out.println("Note: Course grade must be from 1.0 to 6.0");
            }
            courseGrade = scanner.nextFloat();
            count++;
        } while (courseGrade < 1 || courseGrade > 6);
        student.addGrades(newCourse, courseGrade);
    }

    private static Student getStudentInformation( StudentService studentService, Scanner scanner )
    {
        System.out.println( "Enter student ID: " );
        String studentId = scanner.next();
        Student student = studentService.findStudent( studentId );
        if ( student == null )
        {
            System.out.println( "Student not found" );
        }
        return student;
    }

    private static void findStudent( StudentService studentService, Scanner scanner )
    {
        Student student = getStudentInformation( studentService, scanner );
        if ( student != null )
        {
            System.out.println( "Student Found: " );
            System.out.println( student );
        }
    }

    private static void registerStudent( StudentService studentService, Scanner scanner ) throws ParseException {
       Student student = PrinterHelper.createStudentMenu( scanner );
       studentService.subscribeStudent( student );
    }

    private static void showPassedCourses(StudentService studentService, Scanner scanner )
    {
        System.out.println( "Enter student ID: " );
        String studentId = scanner.next();
        Student student = studentService.findStudent( studentId );
        if ( student == null )
        {
            System.out.println( "Student not found" );
        }
        else
        {
            if (student.findPassedCourses().size() == 0)
            {
                System.out.println( "No passed courses available" );
            }
           else
            {
                System.out.println(student.findPassedCourses());
            }
        }
    }
}
