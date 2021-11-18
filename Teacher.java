public class Teacher {
    private String fullname, faculty, cathedra, classNumber, students;

    public Teacher(String fullname, String faculty, String cathedra, String classNumber, String students) {
        this.fullname = fullname;
        this.faculty = faculty;
        this.cathedra = cathedra;
        this.classNumber = classNumber;
        this.students = students;
    }

    public String getFullname() {
        return fullname;
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

