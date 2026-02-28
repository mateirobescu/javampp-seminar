package eu.ase.oopArrays;

import java.util.Arrays;

public class Student {
    private String name;
    private short[] marks;
    private float avgMarks;
    private static int noStud;

    public Student() {

    }

    public Student(String name, short[] marks) {
        this.name = name;
        this.marks = marks;
        Student.noStud++;
        this.avgMarks = this.calcAvgMarks();
    }

    public void setMarks(short[] marks) {
        this.marks = marks;
        this.avgMarks = this.calcAvgMarks();
    }

    public float getAvgMark() {
        return this.avgMarks;
    }

    private float calcAvgMarks() {
        if(marks == null || marks.length == 0) {
            return 0.0f;
        }
        float result = 0.f;

        for(int i = 0; i < marks.length; i++) {
            result += marks[i];
        }

        result /= marks.length;

        return result;
    }

    @Override
    public String toString() {
        return "Student " + name + ", marks: " + Arrays.toString(marks);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;

        if(obj == null)
            return false;

        if(this.getClass() != obj.getClass())
            return false;

        Student other = (Student) obj;

        if(!name.equals(other.name)) {
            return false;
        }

        if(!Arrays.equals(marks, other.marks))
                return false;

        if(Float.floatToIntBits(avgMarks) != Float.floatToIntBits(other.avgMarks))
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;

        if(name != null) {
            result += name.hashCode();
        }

        if(marks != null)
            result += Arrays.hashCode(marks);

        result += Float.floatToIntBits(avgMarks);

        return result;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Student cloneObj = new Student();
        cloneObj.name = new String(this.name);
        cloneObj.marks = this.marks.clone();
        cloneObj.avgMarks = this.avgMarks;
        Student.noStud++;
        return (Object) cloneObj;


    }

}
