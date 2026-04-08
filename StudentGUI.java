import javax.swing.*;
import java.awt.*;

public class StudentGUI {

    private StudentManager sm = new StudentManager();

    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public StudentGUI() {
        frame = new JFrame("Student Management System");
        frame.setSize(550, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(createHomePanel(), "home");
        mainPanel.add(createAddPanel(), "add");
        mainPanel.add(createShowPanel(), "show");
        mainPanel.add(createSearchPanel(), "search");
        mainPanel.add(createDeletePanel(), "delete");
        mainPanel.add(createUpdatePanel(), "update");

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private JPanel createHomePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel title = new JLabel("Student Management System", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));

        JButton addBtn = new JButton("Add Student");
        JButton showBtn = new JButton("Show Students");
        JButton searchBtn = new JButton("Search Student");
        JButton deleteBtn = new JButton("Delete Student");
        JButton updateBtn = new JButton("Update Student");
        JButton exitBtn = new JButton("Exit");

        addBtn.addActionListener(e -> cardLayout.show(mainPanel, "add"));
        showBtn.addActionListener(e -> cardLayout.show(mainPanel, "show"));
        searchBtn.addActionListener(e -> cardLayout.show(mainPanel, "search"));
        deleteBtn.addActionListener(e -> cardLayout.show(mainPanel, "delete"));
        updateBtn.addActionListener(e -> cardLayout.show(mainPanel, "update"));
        exitBtn.addActionListener(e -> System.exit(0));

        panel.add(title);
        panel.add(addBtn);
        panel.add(showBtn);
        panel.add(searchBtn);
        panel.add(deleteBtn);
        panel.add(updateBtn);
        panel.add(exitBtn);

        return panel;
    }

    private JPanel createAddPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Add Student", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel center = new JPanel(new GridLayout(3, 2, 10, 10));
        JTextField nameField = new JTextField();
        JTextField idField = new JTextField();
        JTextField courseField = new JTextField();

        center.add(new JLabel("Name:"));
        center.add(nameField);
        center.add(new JLabel("ID:"));
        center.add(idField);
        center.add(new JLabel("Course:"));
        center.add(courseField);

        JTextArea output = new JTextArea(5, 20);
        output.setEditable(false);
        output.setLineWrap(true);
        output.setWrapStyleWord(true);

        JButton addBtn = new JButton("Add");
        JButton backBtn = new JButton("Back");

        addBtn.addActionListener(e -> {
            try {
                String name = nameField.getText().trim();
                String idText = idField.getText().trim();
                String course = courseField.getText().trim();

                if (name.isEmpty() || idText.isEmpty() || course.isEmpty()) {
                    output.setText("Please fill all fields.");
                    return;
                }

                int id = Integer.parseInt(idText);
                String result = sm.addStudent(name, id, course);
                output.setText(result);

                if (result.equals("Student added successfully.")) {
                    nameField.setText("");
                    idField.setText("");
                    courseField.setText("");
                }
            } catch (Exception ex) {
                output.setText("Please enter a valid number for ID.");
            }
        });

        backBtn.addActionListener(e -> {
            nameField.setText("");
            idField.setText("");
            courseField.setText("");
            output.setText("");
            cardLayout.show(mainPanel, "home");
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addBtn);
        buttonPanel.add(backBtn);

        JPanel southPanel = new JPanel(new BorderLayout(10, 10));
        southPanel.add(new JScrollPane(output), BorderLayout.CENTER);
        southPanel.add(buttonPanel, BorderLayout.SOUTH);

        panel.add(title, BorderLayout.NORTH);
        panel.add(center, BorderLayout.CENTER);
        panel.add(southPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createShowPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Show Students", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        JTextArea output = new JTextArea();
        output.setEditable(false);
        output.setLineWrap(true);
        output.setWrapStyleWord(true);

        JButton refreshBtn = new JButton("Show List");
        JButton backBtn = new JButton("Back");

        refreshBtn.addActionListener(e -> output.setText(sm.showStudents()));

        backBtn.addActionListener(e -> {
            output.setText("");
            cardLayout.show(mainPanel, "home");
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(refreshBtn);
        buttonPanel.add(backBtn);

        panel.add(title, BorderLayout.NORTH);
        panel.add(new JScrollPane(output), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Search Student", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel center = new JPanel(new GridLayout(2, 1, 10, 10));
        JPanel inputPanel = new JPanel(new GridLayout(1, 2, 10, 10));

        JTextField idField = new JTextField();
        JTextArea output = new JTextArea(6, 20);
        output.setEditable(false);
        output.setLineWrap(true);
        output.setWrapStyleWord(true);

        inputPanel.add(new JLabel("Enter ID:"));
        inputPanel.add(idField);

        center.add(inputPanel);
        center.add(new JScrollPane(output));

        JButton searchBtn = new JButton("Search");
        JButton backBtn = new JButton("Back");

        searchBtn.addActionListener(e -> {
            try {
                String text = idField.getText().trim();

                if (text.isEmpty()) {
                    output.setText("Please enter an ID.");
                    return;
                }

                int id = Integer.parseInt(text);
                String result = sm.searchStudent(id);
                output.setText(result);
            } catch (Exception ex) {
                output.setText("Please enter a valid number for ID.");
            }
        });

        backBtn.addActionListener(e -> {
            idField.setText("");
            output.setText("");
            cardLayout.show(mainPanel, "home");
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(searchBtn);
        buttonPanel.add(backBtn);

        panel.add(title, BorderLayout.NORTH);
        panel.add(center, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createDeletePanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Delete Student", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel center = new JPanel(new GridLayout(2, 1, 10, 10));
        JPanel inputPanel = new JPanel(new GridLayout(1, 2, 10, 10));

        JTextField idField = new JTextField();
        JTextArea output = new JTextArea(6, 20);
        output.setEditable(false);
        output.setLineWrap(true);
        output.setWrapStyleWord(true);

        inputPanel.add(new JLabel("Enter ID:"));
        inputPanel.add(idField);

        center.add(inputPanel);
        center.add(new JScrollPane(output));

        JButton deleteBtn = new JButton("Delete");
        JButton backBtn = new JButton("Back");

        deleteBtn.addActionListener(e -> {
            try {
                String text = idField.getText().trim();

                if (text.isEmpty()) {
                    output.setText("Please enter an ID.");
                    return;
                }

                int id = Integer.parseInt(text);
                String result = sm.deleteStudent(id);
                output.setText(result);
            } catch (Exception ex) {
                output.setText("Please enter a valid number for ID.");
            }
        });

        backBtn.addActionListener(e -> {
            idField.setText("");
            output.setText("");
            cardLayout.show(mainPanel, "home");
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(deleteBtn);
        buttonPanel.add(backBtn);

        panel.add(title, BorderLayout.NORTH);
        panel.add(center, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createUpdatePanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Update Student", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel center = new JPanel(new GridLayout(3, 2, 10, 10));
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField courseField = new JTextField();

        center.add(new JLabel("Enter ID:"));
        center.add(idField);
        center.add(new JLabel("New Name:"));
        center.add(nameField);
        center.add(new JLabel("New Course:"));
        center.add(courseField);

        JTextArea output = new JTextArea(5, 20);
        output.setEditable(false);
        output.setLineWrap(true);
        output.setWrapStyleWord(true);

        JButton updateBtn = new JButton("Update");
        JButton backBtn = new JButton("Back");

        updateBtn.addActionListener(e -> {
            try {
                String idText = idField.getText().trim();
                String newName = nameField.getText().trim();
                String newCourse = courseField.getText().trim();

                if (idText.isEmpty() || newName.isEmpty() || newCourse.isEmpty()) {
                    output.setText("Please fill all fields.");
                    return;
                }

                int id = Integer.parseInt(idText);
                String result = sm.updateStudent(id, newName, newCourse);
                output.setText(result);

                if (result.equals("Student updated successfully.")) {
                    idField.setText("");
                    nameField.setText("");
                    courseField.setText("");
                }
            } catch (Exception ex) {
                output.setText("Please enter a valid number for ID.");
            }
        });

        backBtn.addActionListener(e -> {
            idField.setText("");
            nameField.setText("");
            courseField.setText("");
            output.setText("");
            cardLayout.show(mainPanel, "home");
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(updateBtn);
        buttonPanel.add(backBtn);

        JPanel southPanel = new JPanel(new BorderLayout(10, 10));
        southPanel.add(new JScrollPane(output), BorderLayout.CENTER);
        southPanel.add(buttonPanel, BorderLayout.SOUTH);

        panel.add(title, BorderLayout.NORTH);
        panel.add(center, BorderLayout.CENTER);
        panel.add(southPanel, BorderLayout.SOUTH);

        return panel;
    }

    public static void main(String[] args) {
        new StudentGUI();
    }
}