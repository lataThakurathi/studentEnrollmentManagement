/**
 * The Dropout class is also a subclass of the Student class and it has five attributes: 
 * numOfRemainingModules (an integer)
 * numOfMonthsAttended (an integer)
 * dateOfDropout (a String)
 * remainingAmount (an integer)
 * hasPaid (either true or false ie, Boolean)
 */

public class Dropout extends Student
{
    private int numOfRemainingModules;
    private int numOfMonthsAttended;
    private String dateOfDropout;
    private int remainingAmount;
    private boolean hasPaid;

    public Dropout(String dateOfBirth, String studentName, int courseDuration,
    int tuitionFee, int numOfRemainingModules, int numOfMonthsAttended,
    String dateOfDropout, int enrollmentID, String dateOfEnrollment, String courseName)
    {
        super(dateOfBirth, studentName, courseDuration, tuitionFee);
        set_enrollmentID(enrollmentID);
        set_dateOfEnrollment(dateOfEnrollment);
        set_courseName(courseName);
        this.numOfRemainingModules = numOfRemainingModules;
        this.numOfMonthsAttended = numOfMonthsAttended;
        this.dateOfDropout = dateOfDropout;
        this.remainingAmount = 0;
        this.hasPaid = false;
    }

    public int getnumOfRemainingModules()
    {
        return this.numOfRemainingModules;
    }

    public int getnumOfMonthsAttended()
    {
        return this.numOfMonthsAttended;
    }

    public String getdateOfDropout()
    {
        return this.dateOfDropout;
    }

    public int getremainingAmount()
    {
        return this.remainingAmount;
    }

    public boolean gethasPaid()
    {
        return this.hasPaid;
    }

    public void billsPayable()
    {
        remainingAmount = (super.get_courseDuration() - numOfMonthsAttended) * super.get_tuitionFee();
        if(remainingAmount==0)
        {
            hasPaid = true;
        }
    }

    public void removeStudent()
    {
        if(hasPaid)
        {
            set_enrollmentID(0);
            set_dateOfBirth("");
            set_courseName("");
            set_student_Name("");
            set_dateOfEnrollment("");
            set_courseDuration(0);
            set_tuitionFee(0);
            dateOfDropout = "";
            numOfRemainingModules = 0;
            numOfMonthsAttended = 0;
            remainingAmount = 0;
        }
        else
        {
            System.out.println("Please clear your bills at the finance department!");
        }
    }

    public void display()
    {
        super.display();
        System.out.println("Number of remaining modules: " +numOfRemainingModules);
        System.out.println("Number of months attended: " +numOfMonthsAttended);
        System.out.println("Date of dropout: " +dateOfDropout);
        System.out.println("Remaining amount: " +remainingAmount);
    }

}

