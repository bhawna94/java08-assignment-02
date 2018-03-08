package edu.knoldus;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;

public class ApplicationTest {


    Optional<List<String>> subjectlist1 = Optional.of(Arrays.asList("dm", "ds"));
    Optional<List<String>> subjectlist2 = Optional.empty();
    Student student1 = new Student("bhawna", 2, subjectlist1);
    Student student2 = new Student("Kritika", 5, subjectlist2);
    Student student3 = new Student("Suraj", 6, subjectlist2);
    ClassRoom class1 = new ClassRoom("xyz", Optional.of(Arrays.asList(student1, student2)));
    ClassRoom class2 = new ClassRoom("abc", Optional.of(Arrays.asList(student3)));
    List<ClassRoom> listofclasses = Arrays.asList(class1, class2);

    @Test
    public final void Test() {

        List<String> actualStudentList = Application.studentHavingNoSubject(listofclasses);
        List<String> expectedStudentList = Arrays.asList("Kritika", "Suraj");
        List<String> actualSubjectList = Application.studentAssociatedWithSubject(listofclasses);
        List<String> expectedSubjectList = Arrays.asList("dm", "ds");
        List<String> actualresult = Application.sayHelloToStudent(listofclasses);
        List<String> expectedresult = Arrays.asList("bhawna", "Kritika", "Suraj");

        List<String> getSayHello = Application.sayHelloToStudent(listofclasses);

        assertEquals("It should return List of Student who do not have any subject", expectedStudentList, actualStudentList);
        assertEquals("It should return List of Subject", expectedSubjectList, actualSubjectList);
        assertEquals("It should return the list of student", expectedresult, actualresult);

    }
}



