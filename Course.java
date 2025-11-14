import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Course implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String DATA_FOLDER = "C:\\StudentManagementData\\courses\\";
    private String courseId;
    private String courseName;
    private int credits;

    public Course(String courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = 3; // Default credits
    }

    public Course(String courseId, String courseName, int credits) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
    }

    // Getters and setters
    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }
    
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    
    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }
    
    public String toString() {
        return courseId + " - " + courseName;
    }
    public void saveToFile() {
        try {
            File dir = new File(DATA_FOLDER);
            if (!dir.exists()) dir.mkdirs();

            FileOutputStream fileOut = new FileOutputStream(DATA_FOLDER + courseId + ".dat");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            System.out.println("⚠️ Error saving course " + courseId + ": " + e.getMessage());
        }
    }

    public static List<Course> loadAllCourses() {
        List<Course> courses = new ArrayList<>();
        File dir = new File(DATA_FOLDER);
        if (!dir.exists()) return courses;

        File[] files = dir.listFiles((d, name) -> name.endsWith(".dat"));
        if (files == null) return courses;

        for (File f : files) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(f))) {
                Course c = (Course) in.readObject();
                courses.add(c);
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("⚠️ Error loading " + f.getName() + ": " + e.getMessage());
            }
        }
        return courses;
    }
}
