package edu.knoldus;

import java.util.List;
import java.util.Optional;

public class ClassRoom {
    String roomid;
    Optional<List<Student>> studentlist;

    public ClassRoom(String roomid,Optional<List<Student>> studentlist){
        this.roomid = roomid;
        this.studentlist = studentlist;
    }

    public String getRoomid() {
        return roomid;
    }

    public Optional<List<Student>> getStudentlist() {
        return studentlist;
    }

    @Override
    public String toString() {
        return "ClassRoom{" + "roomid='" + roomid + '\'' + ","
               + " studentlist=" + studentlist + '}';
    }
}
