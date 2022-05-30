package com.generation.service;

import com.generation.model.Course;
import com.generation.model.Student;

import java.util.*;

public class StudentService
{
    private final Map<String, Student> students = new HashMap<>();

    public void subscribeStudent( Student student )
    {
        students.put(student.getId(), student);
    }

    public Student findStudent( String studentId )
    {
        // check if student exist
        if(students.containsKey(studentId)) {
            return students.get(studentId);
        }
        return null;
    }

    public boolean showSummary()
    {
        if (!students.isEmpty()){
            for (String id : students.keySet())
            {
                Student newStudent = students.get(id);
                System.out.println(newStudent);
                System.out.println("Enrolled Courses: ");

                // check if student has been enrolled in courses, print out all courses
                if (newStudent.getEnrolledCourses() != null)
                {
                    for (Course c : newStudent.getEnrolledCourses()) {
                        // if there are grades, print with grades
                        if (newStudent.getGrades().containsKey(c)) {
                            System.out.println(c + " Grade: " + newStudent.returnGrades(c.getCode()));
                        }
                        // else print w/o grades
                        else {
                            System.out.println(c);
                        }
                    }
                }
                // if student not enrolled, print no courses enrolled
                else
                {
                    System.out.println("No courses enrolled!");
                }

            }
            return true;
        }

        return false;
    }

    public void enrollToCourse( String studentId, Course course )
    {
        Student newStudent = students.get(studentId);

        // check whether course has already been enrolled
        if (newStudent.findCourseById(course.getCode()) != null) {
            System.out.println("Course already registered.");
        }
        else {
            newStudent.enrollToCourse(course);
        }
    }

    public void showCoursesRegistered(String studentID)
    {
        Student newStudent = students.get(studentID);
        // iterate through enrolled courses by particular student and print all courses
        for (Course c : newStudent.getEnrolledCourses())
        {
            System.out.println(c);
        }
    }

}
