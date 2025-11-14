import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Score implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String DATA_FOLDER = "C:\\StudentManagementData\\scores\\";
    private String studentId;
    private String courseId;
    private String semester;
    private double score;
    private String scoreId;

    public Score(String studentId, String courseId, String semester, double score) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.semester = semester;
        this.score = score;
        this.scoreId = studentId + "_" + courseId + "_" + semester;
    }

    // Getters and setters
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    
    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }
    
    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }
    
    public double getScore() { return score; }
    public void setScore(double score) { this.score = score; }


        public void saveToFile() {
        try {
            File dir = new File(DATA_FOLDER);
            if (!dir.exists()) dir.mkdirs();

            FileOutputStream fileOut = new FileOutputStream(DATA_FOLDER + scoreId + ".dat");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            System.out.println("⚠️ Error saving score " + scoreId + ": " + e.getMessage());
        }
    }

    public static List<Score> loadAllScores() {
        List<Score> scores = new ArrayList<>();
        File dir = new File(DATA_FOLDER);
        if (!dir.exists()) return scores;

        File[] files = dir.listFiles((d, name) -> name.endsWith(".dat"));
        if (files == null) return scores;

        for (File f : files) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(f))) {
                Score s = (Score) in.readObject();
                scores.add(s);
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("⚠️ Error loading " + f.getName() + ": " + e.getMessage());
            }
        }
        return scores;
    }
}
