 /**
 *  The Regular class is a subclass Of Student class and has four attributes: 
 *  numOfModules(an integer)
 *  numOfCreditHours(an integer)
 *  daysPresent(a double)
 *  isGrantedScholarship (either true or false ie, Boolean)
 */

public class Regular extends Student
{
    private int numOfCreditHours,numOfModules;
    private double daysPresent; 
    private boolean isGrantedScholarship;
    public Regular(
        String dateOfBirth,
        String courseName,
        int tuitionFee,
        String studentName,
        String dateOfEnrollment,
        int courseDuration,
        int enrollmentID,
        int numOfModules,
        int numOfCreditHours,
        double daysPresent
    )
    {
        super(dateOfBirth, studentName, courseDuration, tuitionFee);
        super.set_courseName(courseName);
        super.set_enrollmentID(enrollmentID);
        super.set_dateOfEnrollment(dateOfEnrollment);
        this.numOfModules = numOfModules;
        this.numOfCreditHours = numOfCreditHours;
        this.daysPresent = daysPresent;
        this.isGrantedScholarship = false;
    }

    public int get_numOfModules()
    {
        return numOfModules;
    }

    public int get_numOfCreditHours()
    {
        return this.numOfCreditHours;
    }

    public double get_daysPresent()
    {
        return daysPresent;
    }

    public boolean get_isGrantedScholarship()
    {
        return isGrantedScholarship;
    }

    //calculating the present percentage  
    public String presentPercentage(double daysPresent) {

        double presentPercentage = (daysPresent / (super.get_courseDuration()*30)) * 100;

        if (presentPercentage >100)
        { 
            System.out.print("error:number of days present cannot be greater than course duration!");
            return " E" ;
        } 
        if (presentPercentage >=80 )     
        {
            isGrantedScholarship = true;
            return " A"  ;
        }
        else if (presentPercentage >=60 )
        {
            isGrantedScholarship = false;
            return " B"  ;
        }
        else if (presentPercentage >=40 )
        {
            isGrantedScholarship = false;
            return " C"  ;
        }
        else if (presentPercentage >=20 )
        {
            isGrantedScholarship= false;
            return " D"  ;
        }
        else
        {
            isGrantedScholarship= false;
            return " E" ;
        }
    }

    public void grantCertificate(String courseName, int enrollmentID, String dateOfEnrollment)
    {
        String messageString = super.get_studentName() + " has graduated from " + courseName + " with enrollmentID " + enrollmentID + " and enrollment date is " + super.get_dateOfEnrollment();

        if(isGrantedScholarship) {
            messageString = messageString + "\nThe scholarship has been granted.";
        }

        System.out.println(messageString);
    }

    public void display() 
    {
        super.display();
        System.out.println("Number of modules:" + this.numOfModules);
        System.out.println("Number of credit hours:"+ this.numOfCreditHours);
        System.out.println("Days present:"+ this.daysPresent);
    }

   public static void main(String[] args)
    {
    }
}
