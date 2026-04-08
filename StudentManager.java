import java.util.ArrayList;

public class StudentManager {

    private ArrayList<Student> list = new ArrayList<>();

    public String addStudent(String name, int id, String course) {
        for (Student s : list) {
            if (s.getId() == id) {
                return "This ID already exists.";
            }
        }

        Student st = new Student(name, id, course);
        list.add(st);
        return "Student added successfully.";
    }

    public String showStudents() {
        if (list.size() == 0) {
            return "No students in the system.";
        }

        String result = "----- Student List -----\n";

        for (int i = 0; i < list.size(); i++) {
            result += (i + 1) + ". " + list.get(i) + "\n";
        }

        result += "Total students: " + list.size();
        return result;
    }

    public String searchStudent(int id) {
        for (Student s : list) {
            if (s.getId() == id) {
                return "Student found:\n" + s;
            }
        }

        return "Student not found.";
    }

    public String deleteStudent(int id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                list.remove(i);
                return "Student deleted successfully.";
            }
        }

        return "Student not found.";
    }

    public String updateStudent(int id, String newName, String newCourse) {
        for (Student s : list) {
            if (s.getId() == id) {
                s.setName(newName);
                s.setCourse(newCourse);
                return "Student updated successfully.";
            }
        }

        return "Student not found.";
    }
}