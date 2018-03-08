package edu.knoldus;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Application {
    public static void main(String args[]) {

        Optional<List<String>> subjectlist1 = Optional.of(Arrays.asList("dm", "ds"));
        Optional<List<String>> subjectlist2 = Optional.empty();
        Student student1 = new Student("bhawna", 2, subjectlist1);
        Student student2 = new Student("Kritika", 5, subjectlist2);
        Student student3 = new Student("Suraj",6,subjectlist2);
        ClassRoom class1 = new ClassRoom("xyz", Optional.of(Arrays.asList(student1, student2)));
        ClassRoom class2 = new ClassRoom("abc", Optional.of(Arrays.asList(student3)));
        List<ClassRoom> listofclasses = Arrays.asList(class1, class2);
        System.out.println("students who do not have any subject " );
        System.out.println(Application.studentHavingNoSubject(listofclasses));
        System.out.println("Subjects of students who are in room xyz");
        System.out.println(Application.studentAssociatedWithSubject(listofclasses));
        System.out.println("say hello");
        Application.sayHelloToStudent(listofclasses)
                .forEach(x->{System.out.println("hello " +  x);});
    }

    /**
     * students who do not have any subject
     * @param listofclasses
     * @return studentWhoDoNotHaveAnySubject
     */
   public static List<String> studentHavingNoSubject (List<ClassRoom> listofclasses) {

        List<ClassRoom> classRoom = listofclasses.stream().filter(c->c.getStudentlist().isPresent()).collect(toList());
        List<List<Student>> studentslist = classRoom.stream().map(c->c.getStudentlist().get()).collect(Collectors.toList());
        List<Student> slist = studentslist.stream().flatMap(x->x.stream()).filter(s->!s.getSubject().isPresent()).collect(toList());
        List<String> studentWhoDoNotHaveAnySubject = slist.stream().map(x->x.getName()).collect(toList());
        return studentWhoDoNotHaveAnySubject;
    }

    /**
     * subject of students who are in room number xyz
     * @param listofclasses
     * @return subject
     */
    public static List<String> studentAssociatedWithSubject (List<ClassRoom> listofclasses){

        List<ClassRoom> classroom = listofclasses.stream().filter(c->c.getRoomid().equals("xyz")).collect(toList());
        List<List<Student>> studentlistingivenroom = classroom.stream().filter(c->c.getStudentlist().isPresent()).map(c->c.getStudentlist().get()).collect(toList());
        List<List<String>> subjectlist = studentlistingivenroom.stream().flatMap(x->x.stream()).filter(x->x.getSubject().isPresent()).map(x->x.getSubject().get()).collect(toList());
        List<String> subject = subjectlist.stream().flatMap(x->x.stream()).collect(toList());
        return subject;
    }

    /**
     * saying hello to student
     * @param listofclasses
     * @return studentList
     */
    public static List<String> sayHelloToStudent(List<ClassRoom> listofclasses){
        List<Student> studentdetails = listofclasses.stream().map(c->c.getStudentlist().get()).flatMap(x->x.stream()).collect(toList());
        List<String> studentList = studentdetails.stream().map(c->c.getName()).collect(toList());
          return studentList;
          }
}




