package com.generation.model;

import java.util.*;

public class Student
    extends Person
    implements Evaluation
{

    double PASS_MIN_GRADE = 3.0;
    private List<Course> courses;
    private Map<Course, Double> grades;

    public Student( String id, String name, String email, Date birthDate )
    {
        super( id, name, email, birthDate );
        courses = new ArrayList<>();
        grades = new HashMap<>();
    }

    public Map<Course, Double> getGrades() {
        return grades;
    }

    public void addGrades(Course course, double grade)
    {
        grades.put(course, grade);
    }

    public double returnGrades(String courseId) {

        for (Course c : courses) {
            if (c.getCode().equals(courseId)) {
                return grades.get(c);
            }
        }

        return -1.0;
    }

    public void enrollToCourse(Course course)
    {
        courses.add(course);
    }

    @Override
    public List<Course> findPassedCourses()
    {
        List<Course> passList= new ArrayList<>();

        for (Course c : grades.keySet()) {
            if (grades.get(c) >= PASS_MIN_GRADE) {
                passList.add(c);
            }
        }

        return passList;
    }

    public Course findCourseById( String courseId )
    {
        for (Course c : courses)
        {
            if (c.getCode().equals(courseId)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public List<Course> getEnrolledCourses()
    {
        if (courses.size() > 0) {
            return courses;
        }

        return null;
    }

    @Override
    public String toString()
    {
        return "Student {" + super.toString() + "}";
    }

}
