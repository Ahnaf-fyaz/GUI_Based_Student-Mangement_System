import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class StudentManagementGUI {
    private JFrame mainFrame;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private Database database;
    private List<Student> students;
    private List<Teacher> teachers;
    private List<Course> courses;
    private List<Score> scores;
    
    // Tables
    private JTable studentsTable;
    private JTable teachersTable;
    private JTable coursesTable;
    private JTable scoresTable;
    
    public StudentManagementGUI() {
        database = new Database();
        students = database.getAllStudents();
        teachers = database.getAllTeachers();
        courses = database.getAllCourses();
        scores = database.getAllScores();
        
        initializeGUI();
        showLoginScreen();
    }
    
    private void initializeGUI() {
        mainFrame = new JFrame("Student Management System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1000, 700);
        mainFrame.setLocationRelativeTo(null);
        
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        // Create different screens
        mainPanel.add(createLoginPanel(), "LOGIN");
        mainPanel.add(createMainMenuPanel(), "MAIN_MENU");
        mainPanel.add(createStudentManagementPanel(), "STUDENT_MANAGEMENT");
        mainPanel.add(createTeacherManagementPanel(), "TEACHER_MANAGEMENT");
        mainPanel.add(createCourseManagementPanel(), "COURSE_MANAGEMENT");
        mainPanel.add(createScoreManagementPanel(), "SCORE_MANAGEMENT");
        mainPanel.add(createMarksSheetPanel(), "MARKS_SHEET");
        mainPanel.add(createUserManagementPanel(), "USER_MANAGEMENT");
        
        mainFrame.add(mainPanel);
    }
    
    private void showLoginScreen() {
        cardLayout.show(mainPanel, "LOGIN");
        mainFrame.setVisible(true);
    }
    
    private void showScreen(String screenName) {
        cardLayout.show(mainPanel, screenName);
        refreshData();
    }
    
    private void refreshData() {
        students = database.getAllStudents();
        teachers = database.getAllTeachers();
        courses = database.getAllCourses();
        scores = database.getAllScores();
        
        refreshStudentsTable();
        refreshTeachersTable();
        refreshCoursesTable();
        refreshScoresTable();
    }
    
    // LOGIN PANEL
    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 240, 240));
        
        // Header
        JLabel headerLabel = new JLabel("STUDENT MANAGEMENT SYSTEM", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setBorder(BorderFactory.createEmptyBorder(50, 0, 30, 0));
        panel.add(headerLabel, BorderLayout.NORTH);
        
        // Login Form
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBackground(Color.WHITE);
        loginPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));
        loginPanel.setPreferredSize(new Dimension(400, 300));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel loginLabel = new JLabel("LOGIN", JLabel.CENTER);
        loginLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        loginPanel.add(loginLabel, gbc);
        
        gbc.gridwidth = 1;
        gbc.gridy = 1; gbc.gridx = 0;
        loginPanel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        JTextField usernameField = new JTextField(15);
        loginPanel.add(usernameField, gbc);
        
        gbc.gridy = 2; gbc.gridx = 0;
        loginPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        JPasswordField passwordField = new JPasswordField(15);
        loginPanel.add(passwordField, gbc);
        
        gbc.gridy = 3; gbc.gridx = 0; gbc.gridwidth = 2;
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(70, 130, 180));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginPanel.add(loginButton, gbc);
        
        gbc.gridy = 4;
        JButton registerButton = new JButton("Register New User");
        registerButton.setBackground(new Color(34, 139, 34));
        registerButton.setForeground(Color.WHITE);
        loginPanel.add(registerButton, gbc);
        
        panel.add(loginPanel, BorderLayout.CENTER);
        
        // Login button action
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            
            if (login(username, password)) {
                JOptionPane.showMessageDialog(mainFrame, "Login successful! Welcome " + username);
                showScreen("MAIN_MENU");
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        // Register button action
        registerButton.addActionListener(e -> showRegistrationDialog());
        
        return panel;
    }
    
    // MAIN MENU PANEL
    private JPanel createMainMenuPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Header
        JLabel headerLabel = new JLabel("MAIN MENU", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        panel.add(headerLabel, BorderLayout.NORTH);
        
        // Menu Buttons
        JPanel menuPanel = new JPanel(new GridLayout(3, 3, 20, 20));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        
        String[] menuItems = {
            "Student Management", "Teacher Management", "Course Management",
            "Score Management", "Marks Sheet", "User Management", "Logout"
        };
        
        for (String item : menuItems) {
            JButton button = new JButton(item);
            button.setFont(new Font("Arial", Font.PLAIN, 16));
            button.setBackground(new Color(70, 130, 180));
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
            
            button.addActionListener(e -> {
                switch (item) {
                    case "Student Management":
                        showScreen("STUDENT_MANAGEMENT");
                        break;
                    case "Teacher Management":
                        showScreen("TEACHER_MANAGEMENT");
                        break;
                    case "Course Management":
                        showScreen("COURSE_MANAGEMENT");
                        break;
                    case "Score Management":
                        showScreen("SCORE_MANAGEMENT");
                        break;
                    case "Marks Sheet":
                        showScreen("MARKS_SHEET");
                        break;
                    case "User Management":
                        showScreen("USER_MANAGEMENT");
                        break;
                    case "Logout":
                        showScreen("LOGIN");
                        break;
                }
            });
            
            menuPanel.add(button);
        }
        
        panel.add(menuPanel, BorderLayout.CENTER);
        return panel;
    }
    
    // STUDENT MANAGEMENT PANEL
    private JPanel createStudentManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel headerLabel = new JLabel("STUDENT MANAGEMENT", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(headerLabel, BorderLayout.CENTER);
        
        JButton backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(e -> showScreen("MAIN_MENU"));
        headerPanel.add(backButton, BorderLayout.EAST);
        panel.add(headerPanel, BorderLayout.NORTH);
        
        // Controls
        JPanel controlsPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add New Student");
        JButton refreshButton = new JButton("Refresh");
        
        controlsPanel.add(addButton);
        controlsPanel.add(refreshButton);
        panel.add(controlsPanel, BorderLayout.SOUTH);
        
        // Table
        String[] columns = {"Student ID", "Name", "Date of Birth", "Gender", "Email", "Phone"};
        studentsTable = new JTable(new DefaultTableModel(columns, 0));
        refreshStudentsTable();
        
        JScrollPane scrollPane = new JScrollPane(studentsTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Add button action
        addButton.addActionListener(e -> showAddStudentDialog());
        
        // Refresh button action
        refreshButton.addActionListener(e -> refreshStudentsTable());
        
        // Double-click to view details
        studentsTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = studentsTable.getSelectedRow();
                    if (row != -1) {
                        String studentId = (String) studentsTable.getValueAt(row, 0);
                        showStudentDetails(studentId);
                    }
                }
            }
        });
        
        return panel;
    }
    
    private void refreshStudentsTable() {
        DefaultTableModel model = (DefaultTableModel) studentsTable.getModel();
        model.setRowCount(0);
        
        for (Student student : students) {
            model.addRow(new Object[]{
                student.getStudentId(),
                student.getName(),
                student.getDateOfBirth(),
                student.getGender(),
                student.getEmail(),
                student.getPhoneNumber()
            });
        }
    }
    
    private void showAddStudentDialog() {
        JDialog dialog = new JDialog(mainFrame, "Add New Student", true);
        dialog.setLayout(new GridLayout(0, 2, 10, 10));
        dialog.setSize(400, 500);
        dialog.setLocationRelativeTo(mainFrame);
        
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField dobField = new JTextField();
        JTextField genderField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField fatherField = new JTextField();
        JTextField motherField = new JTextField();
        JTextField address1Field = new JTextField();
        JTextField address2Field = new JTextField();
        
        dialog.add(new JLabel("Student ID:"));
        dialog.add(idField);
        dialog.add(new JLabel("Name:"));
        dialog.add(nameField);
        dialog.add(new JLabel("Date of Birth (YYYY-MM-DD):"));
        dialog.add(dobField);
        dialog.add(new JLabel("Gender:"));
        dialog.add(genderField);
        dialog.add(new JLabel("Email:"));
        dialog.add(emailField);
        dialog.add(new JLabel("Phone:"));
        dialog.add(phoneField);
        dialog.add(new JLabel("Father's Name:"));
        dialog.add(fatherField);
        dialog.add(new JLabel("Mother's Name:"));
        dialog.add(motherField);
        dialog.add(new JLabel("Address Line 1:"));
        dialog.add(address1Field);
        dialog.add(new JLabel("Address Line 2:"));
        dialog.add(address2Field);
        
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");
        
        dialog.add(saveButton);
        dialog.add(cancelButton);
        
        saveButton.addActionListener(e -> {
            Student student = new Student(
                idField.getText(), nameField.getText(), dobField.getText(),
                genderField.getText(), emailField.getText(), phoneField.getText(),
                fatherField.getText(), motherField.getText(), address1Field.getText(),
                address2Field.getText()
            );
            database.addStudent(student);
            students.add(student);
            refreshStudentsTable();
            dialog.dispose();
            JOptionPane.showMessageDialog(mainFrame, "Student added successfully!");
        });
        
        cancelButton.addActionListener(e -> dialog.dispose());
        
        dialog.setVisible(true);
    }
    
    private void showStudentDetails(String studentId) {
        Student student = database.getStudentById(studentId);
        if (student == null) return;
        
        String details = String.format(
            "Student ID: %s\nName: %s\nDate of Birth: %s\nGender: %s\nEmail: %s\nPhone: %s\nFather: %s\nMother: %s\nAddress: %s, %s",
            student.getStudentId(), student.getName(), student.getDateOfBirth(),
            student.getGender(), student.getEmail(), student.getPhoneNumber(),
            student.getFatherName(), student.getMotherName(),
            student.getAddressLine1(), student.getAddressLine2()
        );
        
        JOptionPane.showMessageDialog(mainFrame, details, "Student Details", JOptionPane.INFORMATION_MESSAGE);
    }
    
    // TEACHER MANAGEMENT PANEL (Similar structure to student management)
    private JPanel createTeacherManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel headerLabel = new JLabel("TEACHER MANAGEMENT", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(headerLabel, BorderLayout.CENTER);
        
        JButton backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(e -> showScreen("MAIN_MENU"));
        headerPanel.add(backButton, BorderLayout.EAST);
        panel.add(headerPanel, BorderLayout.NORTH);
        
        JPanel controlsPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add New Teacher");
        JButton refreshButton = new JButton("Refresh");
        controlsPanel.add(addButton);
        controlsPanel.add(refreshButton);
        panel.add(controlsPanel, BorderLayout.SOUTH);
        
        String[] columns = {"Teacher ID", "Name", "Email", "Phone", "Department", "Qualification"};
        teachersTable = new JTable(new DefaultTableModel(columns, 0));
        refreshTeachersTable();
        
        JScrollPane scrollPane = new JScrollPane(teachersTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        addButton.addActionListener(e -> showAddTeacherDialog());
        refreshButton.addActionListener(e -> refreshTeachersTable());
        
        return panel;
    }
    
    private void refreshTeachersTable() {
        DefaultTableModel model = (DefaultTableModel) teachersTable.getModel();
        model.setRowCount(0);
        
        for (Teacher teacher : teachers) {
            model.addRow(new Object[]{
                teacher.getTeacherId(),
                teacher.getName(),
                teacher.getEmail(),
                teacher.getPhoneNumber(),
                teacher.getDepartment(),
                teacher.getQualification()
            });
        }
    }
    
    private void showAddTeacherDialog() {
        JDialog dialog = new JDialog(mainFrame, "Add New Teacher", true);
        dialog.setLayout(new GridLayout(0, 2, 10, 10));
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(mainFrame);
        
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField deptField = new JTextField();
        JTextField qualField = new JTextField();
        
        dialog.add(new JLabel("Teacher ID:"));
        dialog.add(idField);
        dialog.add(new JLabel("Name:"));
        dialog.add(nameField);
        dialog.add(new JLabel("Email:"));
        dialog.add(emailField);
        dialog.add(new JLabel("Phone:"));
        dialog.add(phoneField);
        dialog.add(new JLabel("Department:"));
        dialog.add(deptField);
        dialog.add(new JLabel("Qualification:"));
        dialog.add(qualField);
        
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");
        dialog.add(saveButton);
        dialog.add(cancelButton);
        
        saveButton.addActionListener(e -> {
            Teacher teacher = new Teacher(
                idField.getText(), nameField.getText(), emailField.getText(),
                phoneField.getText(), deptField.getText(), qualField.getText()
            );
            database.addTeacher(teacher);
            teachers.add(teacher);
            refreshTeachersTable();
            dialog.dispose();
            JOptionPane.showMessageDialog(mainFrame, "Teacher added successfully!");
        });
        
        cancelButton.addActionListener(e -> dialog.dispose());
        
        dialog.setVisible(true);
    }
    
    // COURSE MANAGEMENT PANEL
    private JPanel createCourseManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel headerLabel = new JLabel("COURSE MANAGEMENT", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(headerLabel, BorderLayout.CENTER);
        
        JButton backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(e -> showScreen("MAIN_MENU"));
        headerPanel.add(backButton, BorderLayout.EAST);
        panel.add(headerPanel, BorderLayout.NORTH);
        
        JPanel controlsPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add New Course");
        JButton refreshButton = new JButton("Refresh");
        controlsPanel.add(addButton);
        controlsPanel.add(refreshButton);
        panel.add(controlsPanel, BorderLayout.SOUTH);
        
        String[] columns = {"Course ID", "Course Name", "Credits"};
        coursesTable = new JTable(new DefaultTableModel(columns, 0));
        refreshCoursesTable();
        
        JScrollPane scrollPane = new JScrollPane(coursesTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        addButton.addActionListener(e -> showAddCourseDialog());
        refreshButton.addActionListener(e -> refreshCoursesTable());
        
        return panel;
    }
    
    private void refreshCoursesTable() {
        DefaultTableModel model = (DefaultTableModel) coursesTable.getModel();
        model.setRowCount(0);
        
        for (Course course : courses) {
            model.addRow(new Object[]{
                course.getCourseId(),
                course.getCourseName(),
                course.getCredits()
            });
        }
    }
    
    private void showAddCourseDialog() {
        JDialog dialog = new JDialog(mainFrame, "Add New Course", true);
        dialog.setLayout(new GridLayout(0, 2, 10, 10));
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(mainFrame);
        
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField creditsField = new JTextField("3");
        
        dialog.add(new JLabel("Course ID:"));
        dialog.add(idField);
        dialog.add(new JLabel("Course Name:"));
        dialog.add(nameField);
        dialog.add(new JLabel("Credits:"));
        dialog.add(creditsField);
        
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");
        dialog.add(saveButton);
        dialog.add(cancelButton);
        
        saveButton.addActionListener(e -> {
            int credits = 3;
            try {
                credits = Integer.parseInt(creditsField.getText());
            } catch (NumberFormatException ex) {
                // Use default value
            }
            
            Course course = new Course(idField.getText(), nameField.getText(), credits);
            database.addCourse(course);
            courses.add(course);
            refreshCoursesTable();
            dialog.dispose();
            JOptionPane.showMessageDialog(mainFrame, "Course added successfully!");
        });
        
        cancelButton.addActionListener(e -> dialog.dispose());
        
        dialog.setVisible(true);
    }
    
    // SCORE MANAGEMENT PANEL
    private JPanel createScoreManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel headerLabel = new JLabel("SCORE MANAGEMENT", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(headerLabel, BorderLayout.CENTER);
        
        JButton backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(e -> showScreen("MAIN_MENU"));
        headerPanel.add(backButton, BorderLayout.EAST);
        panel.add(headerPanel, BorderLayout.NORTH);
        
        JPanel controlsPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add New Score");
        JButton refreshButton = new JButton("Refresh");
        controlsPanel.add(addButton);
        controlsPanel.add(refreshButton);
        panel.add(controlsPanel, BorderLayout.SOUTH);
        
        String[] columns = {"Student ID", "Course ID", "Semester", "Score", "Grade"};
        scoresTable = new JTable(new DefaultTableModel(columns, 0));
        refreshScoresTable();
        
        JScrollPane scrollPane = new JScrollPane(scoresTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        addButton.addActionListener(e -> showAddScoreDialog());
        refreshButton.addActionListener(e -> refreshScoresTable());
        
        return panel;
    }
    
    private void refreshScoresTable() {
        DefaultTableModel model = (DefaultTableModel) scoresTable.getModel();
        model.setRowCount(0);
        
        for (Score score : scores) {
            String grade = calculateGrade(score.getScore());
            model.addRow(new Object[]{
                score.getStudentId(),
                score.getCourseId(),
                score.getSemester(),
                score.getScore(),
                grade
            });
        }
    }
    
    private void showAddScoreDialog() {
        JDialog dialog = new JDialog(mainFrame, "Add New Score", true);
        dialog.setLayout(new GridLayout(0, 2, 10, 10));
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(mainFrame);
        
        JTextField studentIdField = new JTextField();
        JTextField courseIdField = new JTextField();
        JTextField semesterField = new JTextField();
        JTextField scoreField = new JTextField();
        
        dialog.add(new JLabel("Student ID:"));
        dialog.add(studentIdField);
        dialog.add(new JLabel("Course ID:"));
        dialog.add(courseIdField);
        dialog.add(new JLabel("Semester:"));
        dialog.add(semesterField);
        dialog.add(new JLabel("Score:"));
        dialog.add(scoreField);
        
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");
        dialog.add(saveButton);
        dialog.add(cancelButton);
        
        saveButton.addActionListener(e -> {
            try {
                double scoreValue = Double.parseDouble(scoreField.getText());
                Score score = new Score(
                    studentIdField.getText(), 
                    courseIdField.getText(), 
                    semesterField.getText(), 
                    scoreValue
                );
                database.addScore(score);
                scores.add(score);
                refreshScoresTable();
                dialog.dispose();
                JOptionPane.showMessageDialog(mainFrame, 
                    "Score added successfully!\nGrade: " + calculateGrade(scoreValue));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainFrame, "Please enter a valid score!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        cancelButton.addActionListener(e -> dialog.dispose());
        
        dialog.setVisible(true);
    }
    
    // MARKS SHEET PANEL
    private JPanel createMarksSheetPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel headerLabel = new JLabel("MARKS SHEET GENERATOR", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(headerLabel, BorderLayout.CENTER);
        
        JButton backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(e -> showScreen("MAIN_MENU"));
        headerPanel.add(backButton, BorderLayout.EAST);
        panel.add(headerPanel, BorderLayout.NORTH);
        
        JPanel inputPanel = new JPanel(new FlowLayout());
        JTextField studentIdField = new JTextField(15);
        JButton generateButton = new JButton("Generate Marks Sheet");
        
        inputPanel.add(new JLabel("Enter Student ID:"));
        inputPanel.add(studentIdField);
        inputPanel.add(generateButton);
        panel.add(inputPanel, BorderLayout.CENTER);
        
        JTextArea resultArea = new JTextArea(20, 60);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(resultArea);
        panel.add(scrollPane, BorderLayout.SOUTH);
        
        generateButton.addActionListener(e -> {
            String studentId = studentIdField.getText();
            String marksSheet = generateMarksSheet(studentId);
            resultArea.setText(marksSheet);
        });
        
        return panel;
    }
    
    // USER MANAGEMENT PANEL
    private JPanel createUserManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel headerLabel = new JLabel("USER MANAGEMENT", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(headerLabel, BorderLayout.CENTER);
        
        JButton backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(e -> showScreen("MAIN_MENU"));
        headerPanel.add(backButton, BorderLayout.EAST);
        panel.add(headerPanel, BorderLayout.NORTH);
        
        JPanel controlsPanel = new JPanel(new FlowLayout());
        JButton viewUsersButton = new JButton("View All Users");
        controlsPanel.add(viewUsersButton);
        panel.add(controlsPanel, BorderLayout.SOUTH);
        
        viewUsersButton.addActionListener(e -> showAllUsersDialog());
        
        return panel;
    }
    
    // HELPER METHODS
    private boolean login(String username, String password) {
        // Check default users
        if ((username.equals("admin") && password.equals("admin123")) ||
            (username.equals("mu") && password.equals("mu123")) ||
            (username.equals("ms") && password.equals("ms123"))) {
            return true;
        }
        
        // Check registered users from file
        List<User> registeredUsers = User.loadUsersFromFile("C:\\StudentManagementData");
        for (User user : registeredUsers) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        
        return false;
    }
    
    private void showRegistrationDialog() {
        JDialog dialog = new JDialog(mainFrame, "Register New User", true);
        dialog.setLayout(new GridLayout(0, 2, 10, 10));
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(mainFrame);
        
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JTextField fullNameField = new JTextField();
        JTextField emailField = new JTextField();
        
        JComboBox<String> roleComboBox = new JComboBox<>(new String[]{"Student", "Teacher", "Staff"});
        
        dialog.add(new JLabel("Username:"));
        dialog.add(usernameField);
        dialog.add(new JLabel("Password:"));
        dialog.add(passwordField);
        dialog.add(new JLabel("Full Name:"));
        dialog.add(fullNameField);
        dialog.add(new JLabel("Email:"));
        dialog.add(emailField);
        dialog.add(new JLabel("Role:"));
        dialog.add(roleComboBox);
        
        JButton registerButton = new JButton("Register");
        JButton cancelButton = new JButton("Cancel");
        dialog.add(registerButton);
        dialog.add(cancelButton);
        
        registerButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String fullName = fullNameField.getText();
            String email = emailField.getText();
            String role = (String) roleComboBox.getSelectedItem();
            
            if (User.usernameExists(username, "C:\\StudentManagementData") || 
                username.equals("admin") || username.equals("mu") || username.equals("ms")) {
                JOptionPane.showMessageDialog(dialog, "Username already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            User newUser = new User(username, password, role, email, fullName);
            newUser.saveToFile("C:\\StudentManagementData");
            dialog.dispose();
            JOptionPane.showMessageDialog(mainFrame, "Registration successful!");
        });
        
        cancelButton.addActionListener(e -> dialog.dispose());
        
        dialog.setVisible(true);
    }
    
    private void showAllUsersDialog() {
        List<User> users = User.loadUsersFromFile("C:\\StudentManagementData");
        
        String[] columns = {"Username", "Full Name", "Role", "Email"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        
        for (User user : users) {
            model.addRow(new Object[]{
                user.getUsername(),
                user.getFullName(),
                user.getRole(),
                user.getEmail()
            });
        }
        
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        
        JOptionPane.showMessageDialog(mainFrame, scrollPane, "All Registered Users", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private String generateMarksSheet(String studentId) {
        Student student = database.getStudentById(studentId);
        if (student == null) {
            return "Student not found!";
        }
        
        List<Score> studentScores = database.getScoresByStudent(studentId);
        if (studentScores.isEmpty()) {
            return "No scores found for student: " + studentId;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("MARKS SHEET FOR ").append(student.getName().toUpperCase()).append("\n");
        sb.append("Student ID: ").append(studentId).append("\n");
        sb.append("Name: ").append(student.getName()).append("\n\n");
        sb.append(String.format("%-20s %-10s %-8s %-12s\n", "Course", "Score", "Grade", "Grade Points"));
        sb.append("------------------------------------------------------------\n");
        
        double totalGradePoints = 0;
        int totalCredits = 0;
        
        for (Score score : studentScores) {
            String grade = calculateGrade(score.getScore());
            double gradePoints = calculateGradePoints(score.getScore());
            
            // Find course credits
            int credits = 3; // default
            for (Course course : courses) {
                if (course.getCourseId().equals(score.getCourseId())) {
                    credits = course.getCredits();
                    break;
                }
            }
            
            totalGradePoints += gradePoints * credits;
            totalCredits += credits;
            
            sb.append(String.format("%-20s %-10.2f %-8s %-12.2f\n", 
                getCourseName(score.getCourseId()), score.getScore(), grade, gradePoints));
        }
        
        if (totalCredits > 0) {
            double cgpa = totalGradePoints / totalCredits;
            sb.append("\nCGPA: ").append(String.format("%.2f", cgpa));
        }
        
        return sb.toString();
    }
    
    private String getCourseName(String courseId) {
        for (Course course : courses) {
            if (course.getCourseId().equals(courseId)) {
                return course.getCourseName();
            }
        }
        return courseId;
    }
    
    private String calculateGrade(double score) {
        if (score >= 90) return "A+";
        if (score >= 85) return "A";
        if (score >= 80) return "A-";
        if (score >= 75) return "B+";
        if (score >= 70) return "B";
        if (score >= 65) return "B-";
        if (score >= 60) return "C+";
        if (score >= 55) return "C";
        if (score >= 50) return "C-";
        if (score >= 45) return "D+";
        if (score >= 40) return "D";
        return "F";
    }
    
    private double calculateGradePoints(double score) {
        if (score >= 90) return 4.0;
        if (score >= 85) return 4.0;
        if (score >= 80) return 3.7;
        if (score >= 75) return 3.3;
        if (score >= 70) return 3.0;
        if (score >= 65) return 2.7;
        if (score >= 60) return 2.3;
        if (score >= 55) return 2.0;
        if (score >= 50) return 1.7;
        if (score >= 45) return 1.3;
        if (score >= 40) return 1.0;
        return 0.0;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentManagementGUI();
        });
    }
}