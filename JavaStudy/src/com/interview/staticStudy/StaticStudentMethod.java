package com.interview.staticStudy;

public class StaticStudentMethod {

    public static void main(String [] a) {
        StaticStudent staticStudent1 = new StaticStudent("mesung");
        StaticStudent staticStudent2 = new StaticStudent("haong");

        staticStudent1.stduentInfo();
        staticStudent2.stduentInfo();

        StaticStudent.updateColName("America University");

        staticStudent1.stduentInfo();
        staticStudent2.stduentInfo();
    }
}
