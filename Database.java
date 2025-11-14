import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private List<Student> students;
    private List<Teacher> teachers;
    private List<Course> courses;
    private List<Score> scores;

    public Database() {
        students = loadStudentsFromFolder();
        teachers = Teacher.loadAllTeachers();
        courses = Course.loadAllCourses();
        scores = Score.loadAllScores();
        
        if (!students.isEmpty()) {
            System.out.println("Loaded " + students.size() + " students from file storage.");
        }
        if (!teachers.isEmpty()) {
            System.out.println("Loaded " + teachers.size() + " teachers from file storage.");
        }
        System.out.println("In-Memory Database initialized");
    }

    private List<Student> loadStudentsFromFolder() {
        List<Student> students = new ArrayList<>();
        File folder = new File("C:\\StudentManagementData\\students");
        if (!folder.exists()) {
            folder.mkdirs();
            return students;
        }
        
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".dat"));
        if (files == null) return students;
        
        for (File file : files) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                Student s = (Student) ois.readObject();
                students.add(s);
            } catch (Exception e) {
                System.out.println("⚠️ Error loading student from " + file.getName() + ": " + e.getMessage());
            }
        }
        return students;
    }

    // Student methods
    public void addStudent(Student student) {
        students.add(student);
        student.saveToFile();
        System.out.println("💾 Student " + student.getStudentId() + " saved successfully!");
    }

    // Teacher methods
    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
        teacher.saveToFile();
        System.out.println("💾 Teacher " + teacher.getTeacherId() + " saved successfully!");
    }

    // Course methods
    public void addCourse(Course course) {
        courses.add(course);
        course.saveToFile();
    }

    // Score methods
    public void addScore(Score score) {
        scores.add(score);
        score.saveToFile();
    }

    // Getters
    public Student getStudentById(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    public Teacher getTeacherById(String teacherId) {
        for (Teacher teacher : teachers) {
            if (teacher.getTeacherId().equals(teacherId)) {
                return teacher;
            }
        }
        return null;
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public List<Teacher> getAllTeachers() {
        return new ArrayList<>(teachers);
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courses);
    }

    public List<Score> getAllScores() {
        return new ArrayList<>(scores);
    }

    public List<Score> getScoresByStudent(String studentId) {
        List<Score> studentScores = new ArrayList<>();
        for (Score score : scores) {
            if (score.getStudentId().equals(studentId)) {
                studentScores.add(score);
            }
        }
        return studentScores;
    }

    public void close() {
        // Nothing to close for in-memory database
    }
}