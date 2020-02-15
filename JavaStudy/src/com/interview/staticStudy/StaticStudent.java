package com.interview.staticStudy;

public class StaticStudent {
    private String name;
    private int stdtNo = 0;

    private static String colName = "Korea University";
    private static int addStdtNo = 0;

    public StaticStudent(String name) {
        this.name = name;
        this.stdtNo = ++addStdtNo;
    }

    public void stduentInfo() {
        System.out.println(colName +" 학번 : " + this.stdtNo + ", 이름 : " + this.name);
    }

    public static void updateColName(String name) {
        colName = name;
    }

    static{
        System.out.println("학생을 추가하자!!");
    }
}
