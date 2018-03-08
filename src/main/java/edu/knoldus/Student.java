package edu.knoldus;

import java.util.List;
import java.util.Optional;

public class Student {
    String name;
    Integer rollnumber;
    Optional<List<String>> subject;

    public Student(String name, Integer rollnumber, Optional<List<String>> subject) {

        this.name = name;
        this.rollnumber = rollnumber;
        this.subject = subject;


    }

    public String getName() {
        return name;
    }

    public Integer getRollnumber() {
        return rollnumber;
    }

    public Optional<List<String>> getSubject() {
        return subject;
    }

    @Override
    public String toString() {
        return "Student{"
                + "name='" + name + '\''
                + ", rollnumber=" + rollnumber
                + ", subject=" + subject
                + '}';
    }
}
