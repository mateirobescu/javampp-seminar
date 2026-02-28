package eu.ase.arrays;

public class ProgMainBidimensionalArrays {
    public static void main() {
        int studNo = 2;
        int lectNo = 3;

        short[][] studentMarks = new short[][] {{5, 5, 9}, {9, 10, 9}};
        float[] avgStudentMarks = new float[studentMarks.length];

        for(int i = 0; i < studentMarks.length; i++) {
            avgStudentMarks[i] = 0.f;

            for(int j = 0; j < studentMarks[0].length; j++)
                avgStudentMarks[i] += studentMarks[i][j];

            avgStudentMarks[i] /= studentMarks[0].length;
        }

        for(int i = 0; i < avgStudentMarks.length; i++)
            System.out.println("Stud " + i + " avg: " + avgStudentMarks[i]);

    }
}
