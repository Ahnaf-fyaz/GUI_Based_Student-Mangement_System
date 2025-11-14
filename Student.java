import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
    private static final long serialVersionUID = 1L;
    private static final String DATA_FOLDER = "C:\\StudentManagementData\\students\\";
    
    private String dateOfBirth;
    private String gender;
    private String fatherName;
    private String motherName;
    private String addressLine1;
    private String addressLine2;
    private List<Score> scores;

    public Student(String studentId, String name, String dateOfBirth, String gender, 
                  String email, String phoneNumber, String fatherName, String motherName, 
                  String addressLine1, String addressLine2) {
        super(studentId, name, email, phoneNumber);
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.scores = new ArrayList<>();
    }

    // Getters & Setters for Student-specific fields
    public String getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    
    public String getFatherName() { return fatherName; }
    public void setFatherName(String fatherName) { this.fatherName = fatherName; }
    
    public String getMotherName() { return motherName; }
    public void setMotherName(String motherName) { this.motherName = motherName; }
    
    public String getAddressLine1() { return addressLine1; }
    public void setAddressLine1(String addressLine1) { this.addressLine1 = addressLine1; }
    
    public String getAddressLine2() { return addressLine2; }
    public void setAddressLine2(String addressLine2) { this.addressLine2 = addressLine2; }
    
    public List<Score> getScores() { return scores; }
    public void addScore(Score score) { this.scores.add(score); }
    
    @Override
    public String getRole() {
        return "Student";
    }
    
    public String getStudentId() { return super.getId(); }
    public void setStudentId(String studentId) { super.setId(studentId); }

    // ==============================================================
    // 🧩 Custom Serialization Methods to Handle Inheritance
    // ==============================================================
    
    private void writeObject(ObjectOutputStream out) throws IOException {
        // First write Person fields
        out.writeObject(getId());
        out.writeObject(getName());
        out.writeObject(getEmail());
        out.writeObject(getPhoneNumber());
        
        // Then write Student fields
        out.writeObject(dateOfBirth);
        out.writeObject(gender);
        out.writeObject(fatherName);
        out.writeObject(motherName);
        out.writeObject(addressLine1);
        out.writeObject(addressLine2);
        out.writeObject(scores);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        // First read Person fields and initialize superclass
        String id = (String) in.readObject();
        String name = (String) in.readObject();
        String email = (String) in.readObject();
        String phoneNumber = (String) in.readObject();
        
        // Manually set the Person fields (since we can't call super() in readObject)
        setId(id);
        setName(name);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        
        // Then read Student fields
        dateOfBirth = (String) in.readObject();
        gender = (String) in.readObject();
        fatherName = (String) in.readObject();
        motherName = (String) in.readObject();
        addressLine1 = (String) in.readObject();
        addressLine2 = (String) in.readObject();
        scores = (List<Score>) in.readObject();
    }

    // ==============================================================
    // 🧩 Persistence Methods (PRESERVED)
    // ==============================================================
    
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
            System.out.println("Error saving student " + getId() + ": " + e.getMessage());
        }
    }
}