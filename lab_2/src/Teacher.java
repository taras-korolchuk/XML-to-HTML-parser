public class Teacher {
    private String name, faculty, cathedra, classNumber, students;

    public Teacher(String name, String faculty, String cathedra, String classNumber, String students) {
        this.name = name;
        this.faculty = faculty;
        this.cathedra = cathedra;
        this.classNumber = classNumber;
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getCathedra() {
        return cathedra;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public String getStudents() {
        return students;
    }
}

