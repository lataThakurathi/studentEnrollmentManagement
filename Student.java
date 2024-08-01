/**
 *  The Student class has seven attributes,which correspond to the 
 *  enrollmentID, 
 *  dateOfBirth, 
 *  courseName, 
 *  studentName, 
 *  dateOfEnrollement, 
 *  courseDuration,
 *  tuitionFee. 
 *  The dateOfBirth, courseName, studentName, dateOfEnrollment are each represented as a string of text and enrollmentID, courseDuration (in months) and... 
 *  ...tuitionFee as a number.
 */
public class Student
{
    private String dateOfBirth;
    private String courseName;
    private String studentName;
    private String dateOfEnrollment;
    private int enrollmentID;
    private int courseDuration;
    private int tuitionFee;

    public Student(String dateOfBirth, String studentName, int courseDuration, int tuitionFee)
    {
        this.dateOfBirth = dateOfBirth;
        this.studentName = studentName;
        this.courseDuration = courseDuration;
        this.tuitionFee = tuitionFee;
        this.enrollmentID = 0;
    }

    public void set_dateOfBirth(String dateOfBirth) {
        this. dateOfBirth= dateOfBirth;
    }

    public String get_dateOfBirth() {
        return this.dateOfBirth;
    }

    public void set_student_Name (String studentName) {
        this.studentName = studentName;
    }

    public String get_studentName() {
        return this.studentName;
    }

    public void set_courseDuration (int courseDuration) {
        this.courseDuration = courseDuration;
    }

    public int get_courseDuration () {
        return this.courseDuration;
    }

    public void set_tuitionFee(int tuitionFee) {
        this.tuitionFee = tuitionFee;
    }

    public int get_tuitionFee () {
        return this.tuitionFee;
    }

    public void set_courseName (String courseName) {
        this. courseName = courseName;
    }

    public String get_courseName () {
        return this.courseName;
    }

    public void set_enrollmentID (int ID) {
        this.enrollmentID = ID;
    }

    public int get_enrollmentID () {
        return this.enrollmentID;
    }

    public void set_dateOfEnrollment (String date) {
        this. dateOfEnrollment = date;
    }

    public String get_dateOfEnrollment () {
        return this.dateOfEnrollment;
    }

    public void display() {
        if (courseName=="" || courseName==null) 
        {
            System.out.print( "CourseName is missing!");
        }

        else if (enrollmentID == 0) 
        {
            System.out.print("EnrollmentID not initialized.");
        }

        else if (dateOfEnrollment=="") 
        {
            System.out.println("Date of enrollment not initialized.");
        }

        else if (dateOfBirth=="" || dateOfBirth== null)
        {
            System.out.println("Date of birth not initialized.");
        }

        else if (studentName=="" || studentName== null) 
        {
            System.out.println("Student name not initialized.");
        }

        else if (tuitionFee==0) 
        {
            System.out.println("Tuition fee not initialized.");
        }

        else if (courseDuration==0) 
        {
            System.out.println("Course duration is not initialized.");
        }

        else
        {
            System.out.println("Course name is:" +get_courseName() );
            System.out.println("Enrollment ID is:" +get_enrollmentID() );
            System.out.println("The date of enrollment is: "+get_dateOfEnrollment ()) ;
            System.out.println("The date of birth is: "+get_dateOfBirth() );
            System.out.println("The student name is: "+get_studentName () );
            System.out.println("The tuition fee is: "+get_tuitionFee() );
            System.out.println("The date of courseDuration is:"+get_courseDuration()+ "months");
        }
    }
}
