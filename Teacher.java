import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Teacher extends Person{
    private static final long serialVersionUID = 1L;
    private static final String DATA_FOLDER = "C:\\StudentManagementData\\teachers\\";
    
    private String department;
    private String qualification;
    private List<String> coursesTeaching;

    public Teacher(String teacherId, String name, String email, String phoneNumber, 
                   String department, String qualification) {
        super(teacherId, name, email, phoneNumber);
        this.department = department;
        this.qualification = qualification;
        this.coursesTeaching = new ArrayList<>();
    }

    // Getters & Setters
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    
    public String getQualification() { return qualification; }
    public void setQualification(String qualification) { this.qualification = qualification; }
    
    public List<String> getCoursesTeaching() { return coursesTeaching; }
    public void addCourse(String courseId) { this.coursesTeaching.add(courseId); }
    
    @Override
    public String getRole() {
        return "Teacher";
    }
    
    public String getTeacherId() { return super.getId(); }
    public void setTeacherId(String teacherId) { super.setId(teacherId); }

    // Persistence methods
    public void saveToFile() {
        try {
            File dir = new File(DATA_FOLDER);
            if (!dir.exists()) dir.mkdirs();
            FileOutputStream fileOut = new FileOutputStream(DATA_FOLDER + getId() + ".dat");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            System.out.println("Error saving teacher " + getId() + ": " + e.getMessage());
        }
    }

    public static List<Teacher> loadAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        File dir = new File(DATA_FOLDER);
        if (!dir.exists()) return teachers;
        
        File[] files = dir.listFiles((d, name) -> name.endsWith(".dat"));
        if (files == null) return teachers;
        
        for (File f : files) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(f))) {
                Teacher t = (Teacher) in.readObject();
                teachers.add(t);
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("⚠️ Error loading teacher " + f.getName() + ": " + e.getMessage());
            }
        }
        return teachers;
    }
}