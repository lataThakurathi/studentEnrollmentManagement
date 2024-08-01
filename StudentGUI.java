import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class StudentGUI implements ActionListener {
    // Frame for the entire app, window and the separate panels to be displayed in
    // the window
    private JFrame frame;
    private JPanel welcomePanel, regularPanel, dropoutPanel;
    private JButton openRegularButton, openDropoutButton, terminateButton;

    // FOR REGULAR STUDENT
    // Regular student lables
    private JLabel labelWelcome, labelTitle, labelStudent, labelCourse, labelDOB, labelFee, labelEnroll,
            labelEnrolldate, labelPresent,
            labelDuration, labelModules, labelCredit;

    // Regular student text fields
    private JTextField textStudentName, textCourseName, textTuitionFee, textEnrollmentID, textEnrollmentDate,
            textPresent,
            textDuration, textModules, textCredit, textDateOfDropout, textRemainingAmount, textMonthsAttended,
            textRemainingModules;

    // Regular student comboBoxes fields
    private JComboBox<String> comboDOBDays, comboDOBMonths, comboDOBYears, comboEnrollmentDays, comboEnrollmentMonths,
            comboEnrollmentYears;

    // Regular student buttons
    private JButton addRegularButton, calculatePresentPercentageButton, grantCertificateButton, displayButton,
            clearButton, goHomeButton;

    // FOR DROPOUT STUDENT
    // Dropout student lables
    private JLabel labelWelcomeDropout, labelStudentDropout, labelCourseDropout, labelDOBDropout, labelFeeDropout,
            labelEnrollmentIDDropout, labelEnrolldateDropout, labelPresentDropout,
            labelDurationDropout, labelModulesDropout, labelCreditDropout, labelDropoutDateDropout,
            labelRemainingAmountDroput, labelMonthsAttendedDropout, labelRemainingModulesDropout;

    // Dropout student text fields
    private JTextField textStudentNameDropout, textCourseNameDropout, textTuitionFeeDropout, textEnrollmentIDDropout,
            textEnrollmentDate1, textPresent1,
            textDurationDropout, textModules1, textCredit1, textDateOfDropout1, textRemainingAmount1,
            textMonthsAttendedDropout, textRemainingModulesDropout;

    // Dropout student comboBoxes
    private JComboBox<String> comboDOBDaysDropout, comboDOBMonthsDropout, comboDOBYearsDropout,
            comboEnrollmentDaysDropout, comboEnrollmentMonthsDropout, comboEnrollmentYearsDropout,
            comboDropoutDays, comboDropoutMonths, comboDropoutYears;

    // Dropout student buttons
    private JButton addDropoutButton, payDropoutBillsButton, removeDropoutButton, displayButtonDropout,
            clearButtonDropout, goHomeButtonDropout;

    // DISPLAY TABLE
    private JFrame displayRegularFrame, displayDropoutFrame;
    private JTable regularTable, dropoutTable;
    private JScrollPane regularScroll, dropoutScroll;

    // Properties defined to be instantiated later or easier access cause there are
    // nested code blocks
    private int numOfCreditHours;
    private int numOfModules;
    private int daysPresent;
    private String dateOfBirth;
    private String courseName;
    private String studentName;
    private String dateOfEnrollment;
    private int enrollmentID;
    private int courseDuration;
    private int tuitionFee;
    private int numOfRemainingModules;
    private int numOfMonthsAttended;

    private ArrayList<Student> arraylist = new ArrayList<>();

    public StudentGUI() {
        // Background images declaration
        ImageIcon backgroundImageHome = new ImageIcon("home.png");
        ImageIcon backgroundImageRegular = new ImageIcon("regular.png");
        ImageIcon backgroundImageDropout = new ImageIcon("dropout.png");

        // Welcome panel creation with background image
        welcomePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImageHome.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Resetting the default flow layout
        frame = new JFrame("Welcome to the Student Database!");
        welcomePanel.setLayout(null);
        welcomePanel.setSize(950, 750);
        frame.add(welcomePanel);

        // ========== HOME SCREEN ==========

        // Home screen window
        labelTitle = new JLabel("Please select an option below to continue: ");
        welcomePanel.add(labelTitle);
        labelTitle.setFont(new Font("AmericanTypewriter", Font.BOLD, 20));
        labelTitle.setForeground(Color.ORANGE);

        // Home screen Buttons
        openRegularButton = new JButton();
        openRegularButton.setBounds(400, 250, 150, 50);
        openRegularButton.setText("Regular Student");
        welcomePanel.add(openRegularButton);
        openRegularButton.addActionListener(this);

        openDropoutButton = new JButton();
        openDropoutButton.setBounds(400, 350, 150, 50);
        openDropoutButton.setText("Droput Student");
        welcomePanel.add(openDropoutButton);
        openDropoutButton.addActionListener(this);

        terminateButton = new JButton();
        terminateButton.setBounds(400, 450, 150, 50);
        terminateButton.setText("Terminate");
        welcomePanel.add(terminateButton);
        terminateButton.addActionListener(this);

        // Days, Months and Years Arrays for the comboBoxes
        String Days[] = new String[31];
        for (int i = 0; i < 31; i++) {
            Days[i] = Integer.toString(i + 1);
        }

        String Months[] = { "January", "February", "March", "April", "May", "June", "July", "August",
                "September", "October", "November", "December" };

        int startYear = 1995;
        int endYear = 2005;
        int numberOfYears = endYear - startYear + 1;

        String Years[] = new String[numberOfYears];
        for (int i = 0; i < numberOfYears; i++) {
            Years[i] = Integer.toString(startYear + i);
        }

        int startYearE = 2013;
        int endYearE = 2023;
        int numberOfYearsE = endYearE - startYearE + 1;

        String YearsE[] = new String[numberOfYearsE];
        for (int i = 0; i < numberOfYearsE; i++) {
            YearsE[i] = Integer.toString(startYearE + i);
        }

        // ========== REGULAR STUDENT SCREEN ==========
        // Regular panel creation with the background image
        regularPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImageRegular.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Resetting the layout
        regularPanel.setLayout(null);
        regularPanel.setSize(950, 750);
        frame.add(regularPanel);
        regularPanel.setVisible(false);

        // Regular panel labels
        labelEnroll = new JLabel("EnrollmentID");
        labelStudent = new JLabel("Student Name");
        labelDOB = new JLabel("Date Of Birth");
        labelCourse = new JLabel("courseName");
        labelDuration = new JLabel("course Duration");
        labelModules = new JLabel("No. Of Modules");
        labelCredit = new JLabel("No. Of Credit Hours");
        labelFee = new JLabel("TuitionFee");
        labelEnrolldate = new JLabel("Date Of Enrollment");
        labelPresent = new JLabel("Days Present");

        // Reular panel text fields
        textEnrollmentID = new JTextField();
        textStudentName = new JTextField();

        textCourseName = new JTextField();
        textDuration = new JTextField();
        textModules = new JTextField();
        textCredit = new JTextField();
        textTuitionFee = new JTextField();

        textPresent = new JTextField();

        // Setting bounds (size and position) for regular panel labeles
        labelTitle.setBounds(300, 100, 800, 150);
        labelEnroll.setBounds(50, 100, 100, 40);
        labelStudent.setBounds(50, 150, 100, 40);
        labelDOB.setBounds(50, 200, 200, 40);
        labelCourse.setBounds(50, 250, 200, 40);
        labelDuration.setBounds(50, 300, 200, 40);
        labelModules.setBounds(50, 350, 200, 40);
        labelCredit.setBounds(50, 400, 200, 40);
        labelFee.setBounds(50, 450, 100, 40);
        labelEnrolldate.setBounds(50, 500, 200, 40);
        labelPresent.setBounds(50, 550, 100, 40);

        // Setting bounds (size and position) for regular panel text fields
        textEnrollmentID.setBounds(200, 100, 200, 40);
        textStudentName.setBounds(200, 150, 200, 40);

        textCourseName.setBounds(200, 250, 200, 40);
        textDuration.setBounds(200, 300, 200, 40);
        textModules.setBounds(200, 350, 200, 40);
        textCredit.setBounds(200, 400, 200, 40);
        textTuitionFee.setBounds(200, 450, 200, 40);

        textPresent.setBounds(200, 550, 200, 40);

        // Reular panel comboBoxes
        comboDOBDays = new JComboBox<String>(Days);
        comboDOBDays.setBounds(200, 200, 100, 40);
        comboDOBMonths = new JComboBox<String>(Months);
        comboDOBMonths.setBounds(300, 200, 120, 40);
        comboDOBYears = new JComboBox<String>(Years);
        comboDOBYears.setBounds(420, 200, 100, 40);

        comboEnrollmentDays = new JComboBox<String>(Days);
        comboEnrollmentDays.setBounds(200, 500, 100, 40);
        comboEnrollmentMonths = new JComboBox<String>(Months);
        comboEnrollmentMonths.setBounds(300, 500, 120, 40);
        comboEnrollmentYears = new JComboBox<String>(YearsE);
        comboEnrollmentYears.setBounds(420, 500, 100, 40);

        regularPanel.add(labelEnroll);
        regularPanel.add(textEnrollmentID);
        regularPanel.add(labelStudent);
        regularPanel.add(textStudentName);
        regularPanel.add(labelDOB);
        regularPanel.add(comboDOBDays);
        regularPanel.add(comboDOBMonths);
        regularPanel.add(comboDOBYears);
        regularPanel.add(labelCourse);
        regularPanel.add(textCourseName);
        regularPanel.add(labelDuration);
        regularPanel.add(textDuration);
        regularPanel.add(labelModules);
        regularPanel.add(textModules);
        regularPanel.add(labelCredit);
        regularPanel.add(textCredit);
        regularPanel.add(labelFee);
        regularPanel.add(textTuitionFee);
        regularPanel.add(labelEnrolldate);
        regularPanel.add(comboEnrollmentDays);
        regularPanel.add(comboEnrollmentMonths);
        regularPanel.add(comboEnrollmentYears);
        regularPanel.add(labelPresent);
        regularPanel.add(textPresent);

        // Regular Panel Buttons
        addRegularButton = new JButton();
        addRegularButton.setBounds(700, 150, 150, 50);
        addRegularButton.setText("Add Regular");
        regularPanel.add(addRegularButton);
        addRegularButton.addActionListener(this);

        clearButton = new JButton();
        clearButton.setBounds(700, 250, 150, 50);
        clearButton.setText("Clear");
        regularPanel.add(clearButton);
        clearButton.addActionListener(this);

        goHomeButton = new JButton();
        goHomeButton.setBounds(100, 630, 150, 50);
        goHomeButton.setText("Go Home");
        regularPanel.add(goHomeButton);
        goHomeButton.addActionListener(this);

        calculatePresentPercentageButton = new JButton();
        calculatePresentPercentageButton.setBounds(300, 630, 150, 50);
        calculatePresentPercentageButton.setText("Calculate Present %");
        regularPanel.add(calculatePresentPercentageButton);
        calculatePresentPercentageButton.addActionListener(this);

        grantCertificateButton = new JButton();
        grantCertificateButton.setBounds(500, 630, 150, 50);
        grantCertificateButton.setText("Grant Certificate");
        regularPanel.add(grantCertificateButton);
        grantCertificateButton.addActionListener(this);

        displayButton = new JButton();
        displayButton.setBounds(700, 630, 150, 50);
        displayButton.setText("Display");
        regularPanel.add(displayButton);
        displayButton.addActionListener(this);

        // ========== DROPOUT STUDENT SCREEN ==========
        dropoutPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImageDropout.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Resetting the layout
        dropoutPanel.setLayout(null);
        dropoutPanel.setBackground(Color.lightGray);
        dropoutPanel.setSize(900, 700);
        frame.add(dropoutPanel);
        dropoutPanel.setVisible(false);

        // Dropout Screen Labels
        labelEnrollmentIDDropout = new JLabel("EnrollmentID");
        labelStudentDropout = new JLabel("Student Name");
        labelDOBDropout = new JLabel("Date Of Birth");
        labelCourseDropout = new JLabel("Course Name");
        labelDurationDropout = new JLabel("Course Duration");
        labelRemainingModulesDropout = new JLabel("No. Of Remaining Modules");
        labelMonthsAttendedDropout = new JLabel("No. Of Months Attended");
        labelEnrolldateDropout = new JLabel("Date Of Enrollment");
        labelDropoutDateDropout = new JLabel("Date Of Dropout");
        labelFeeDropout = new JLabel("Remaining Amount");

        // Dropout Screen Text fields
        textEnrollmentIDDropout = new JTextField();
        textStudentNameDropout = new JTextField();

        textCourseNameDropout = new JTextField();
        textDurationDropout = new JTextField();
        textRemainingModulesDropout = new JTextField();
        textMonthsAttendedDropout = new JTextField();

        textTuitionFeeDropout = new JTextField();

        // Setting bounds (size and position) for lables and text fields
        labelEnrollmentIDDropout.setBounds(50, 100, 100, 40);
        labelStudentDropout.setBounds(50, 150, 100, 40);
        labelDOBDropout.setBounds(50, 200, 100, 40);
        labelCourseDropout.setBounds(50, 250, 200, 40);
        labelDurationDropout.setBounds(50, 300, 200, 40);
        labelRemainingModulesDropout.setBounds(50, 350, 200, 40);
        labelMonthsAttendedDropout.setBounds(50, 400, 200, 40);
        labelEnrolldateDropout.setBounds(50, 450, 200, 40);
        labelDropoutDateDropout.setBounds(50, 500, 200, 40);
        labelFeeDropout.setBounds(50, 550, 120, 40);

        textEnrollmentIDDropout.setBounds(250, 100, 200, 40);
        textStudentNameDropout.setBounds(250, 150, 200, 40);
        textCourseNameDropout.setBounds(250, 250, 200, 40);
        textDurationDropout.setBounds(250, 300, 200, 40);
        textRemainingModulesDropout.setBounds(250, 350, 200, 40);
        textMonthsAttendedDropout.setBounds(250, 400, 200, 40);
        textTuitionFeeDropout.setBounds(250, 550, 200, 40);

        // Combo Boxes for dropout student
        comboDOBDaysDropout = new JComboBox<String>(Days);
        comboDOBDaysDropout.setBounds(250, 200, 100, 40);
        comboDOBMonthsDropout = new JComboBox<String>(Months);
        comboDOBMonthsDropout.setBounds(350, 200, 120, 40);
        comboDOBYearsDropout = new JComboBox<String>(Years);
        comboDOBYearsDropout.setBounds(470, 200, 100, 40);

        comboEnrollmentDaysDropout = new JComboBox<String>(Days);
        comboEnrollmentDaysDropout.setBounds(250, 450, 100, 40);
        comboEnrollmentMonthsDropout = new JComboBox<String>(Months);
        comboEnrollmentMonthsDropout.setBounds(350, 450, 120, 40);
        comboEnrollmentYearsDropout = new JComboBox<String>(YearsE);
        comboEnrollmentYearsDropout.setBounds(470, 450, 100, 40);

        comboDropoutDays = new JComboBox<String>(Days);
        comboDropoutDays.setBounds(250, 500, 100, 40);
        dropoutPanel.add(comboDropoutDays);
        comboDropoutMonths = new JComboBox<String>(Months);
        comboDropoutMonths.setBounds(350, 500, 120, 40);
        dropoutPanel.add(comboDropoutMonths);
        comboDropoutYears = new JComboBox<String>(YearsE);
        comboDropoutYears.setBounds(470, 500, 100, 40);
        dropoutPanel.add(comboDropoutYears);

        dropoutPanel.add(labelEnrollmentIDDropout);
        dropoutPanel.add(textEnrollmentIDDropout);
        dropoutPanel.add(labelStudentDropout);
        dropoutPanel.add(textStudentNameDropout);
        dropoutPanel.add(labelCourseDropout);
        dropoutPanel.add(textCourseNameDropout);
        dropoutPanel.add(labelDOBDropout);
        dropoutPanel.add(labelDropoutDateDropout);
        dropoutPanel.add(comboDOBDaysDropout);
        dropoutPanel.add(comboDOBMonthsDropout);
        dropoutPanel.add(comboDOBYearsDropout);
        dropoutPanel.add(labelFeeDropout);
        dropoutPanel.add(textTuitionFeeDropout);
        dropoutPanel.add(labelEnrolldateDropout);
        dropoutPanel.add(labelDurationDropout);
        dropoutPanel.add(textDurationDropout);
        dropoutPanel.add(comboEnrollmentDaysDropout);
        dropoutPanel.add(comboEnrollmentMonthsDropout);
        dropoutPanel.add(comboEnrollmentYearsDropout);
        dropoutPanel.add(labelRemainingModulesDropout);
        dropoutPanel.add(textRemainingModulesDropout);
        dropoutPanel.add(labelMonthsAttendedDropout);
        dropoutPanel.add(textMonthsAttendedDropout);

        // Dropout screen buttons
        addDropoutButton = new JButton();
        addDropoutButton.setBounds(700, 100, 150, 50);
        addDropoutButton.setText("Add Dropout");
        dropoutPanel.add(addDropoutButton);
        addDropoutButton.addActionListener(this);

        clearButtonDropout = new JButton();
        clearButtonDropout.setBounds(700, 200, 150, 50);
        clearButtonDropout.setText("Clear");
        dropoutPanel.add(clearButtonDropout);
        clearButtonDropout.addActionListener(this);

        goHomeButtonDropout = new JButton();
        goHomeButtonDropout.setBounds(100, 630, 150, 50);
        goHomeButtonDropout.setText("Go Home");
        dropoutPanel.add(goHomeButtonDropout);
        goHomeButtonDropout.addActionListener(this);

        payDropoutBillsButton = new JButton();
        payDropoutBillsButton.setBounds(300, 630, 150, 50);
        payDropoutBillsButton.setText("Pay Dropout Bills");
        dropoutPanel.add(payDropoutBillsButton);
        payDropoutBillsButton.addActionListener(this);

        removeDropoutButton = new JButton();
        removeDropoutButton.setBounds(500, 630, 150, 50);
        removeDropoutButton.setText("Remove Dropout");
        dropoutPanel.add(removeDropoutButton);
        removeDropoutButton.addActionListener(this);

        displayButtonDropout = new JButton();
        displayButtonDropout.setBounds(700, 630, 150, 50);
        displayButtonDropout.setText("Display");
        dropoutPanel.add(displayButtonDropout);
        displayButtonDropout.addActionListener(this);

        // Frame (window) sizing and properties
        frame.setSize(950, 750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {

        // Actions for Button Presses that change the paneles:
        // Add Regular, Add Dropout, Go Home: from regular screen and dropout screen,
        // and Terminate
        if (event.getSource() == openRegularButton) {
            welcomePanel.setVisible(false);
            regularPanel.setVisible(true);
        }

        else if (event.getSource() == openDropoutButton) {
            welcomePanel.setVisible(false);
            dropoutPanel.setVisible(true);
        }

        else if (event.getSource() == goHomeButton) {
            welcomePanel.setVisible(true);
            regularPanel.setVisible(false);
        }

        else if (event.getSource() == goHomeButtonDropout) {
            welcomePanel.setVisible(true);
            dropoutPanel.setVisible(false);
        } else if (event.getSource() == terminateButton) {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to Terminate?", "Confirmation",
                    JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                frame.setVisible(false);
            } else if (option == JOptionPane.NO_OPTION) {
                frame.setVisible(true);
            }
        }

        // Actions for regular screen button presses
        else if (event.getSource() == addRegularButton) {
            addRegularButtonPressed();
        }

        else if (event.getSource() == calculatePresentPercentageButton) {
            calculatePresentPercentageButtonPressed();
        }

        else if (event.getSource() == grantCertificateButton) {
            grantCertificateButtonPressed();
        }

        else if (event.getSource() == displayButton) {
            displayButtonPressed();
        }

        // Actions for dropout screen button presses
        else if (event.getSource() == addDropoutButton) {
            addDropoutButtonPressed();
        } else if (event.getSource() == payDropoutBillsButton) {
            payDropoutBillsButtonPressed();
        }

        else if (event.getSource() == removeDropoutButton) {
            removeDropoutButtonPressed();
        }

        else if (event.getSource() == displayButtonDropout) {
            displayButtonPressedDropout();
        }

        else if (event.getSource() == clearButton) {
            clearButtonPressed();
        } else if (event.getSource() == clearButtonDropout) {
            clearButtonPressedDropout();
        }
    }

    private void addRegularButtonPressed() {
        // Flags for errors
        boolean emptyFieldsExist = false;
        boolean invalidNumberInputs = false;
        boolean valuesLessThanOne = false;
        boolean daysPresentLessThanZero = false;
        boolean studentAlreadyInList = false;

        // Check for empty fields
        if (textCourseName.getText().isEmpty()
                || textTuitionFee.getText().isEmpty()
                || textStudentName.getText().isEmpty()
                || textDuration.getText().isEmpty()
                || textEnrollmentID.getText().isEmpty()
                || textModules.getText().isEmpty()
                || textCredit.getText().isEmpty()
                || textPresent.getText().isEmpty()) {
            emptyFieldsExist = true;
        } else {
            courseName = textCourseName.getText();
            studentName = textStudentName.getText();
        }

        // Check for invalid numerical inputs
        try {
            enrollmentID = Integer.parseInt(textEnrollmentID.getText());
            daysPresent = Integer.parseInt(textPresent.getText());
            courseDuration = Integer.parseInt(textDuration.getText());
            numOfModules = Integer.parseInt(textModules.getText());
            numOfCreditHours = Integer.parseInt(textCredit.getText());
            tuitionFee = Integer.parseInt(textTuitionFee.getText());
        } catch (NumberFormatException e) {
            invalidNumberInputs = true;
        }

        // Check for values less than 1
        if (enrollmentID < 1
                || courseDuration < 1
                || numOfModules < 1
                || numOfCreditHours < 1
                || tuitionFee < 1) {
            valuesLessThanOne = true;
        }

        // Check for Days Present less than zero
        if (daysPresent < 0) {
            daysPresentLessThanZero = true;
        }

        // Messages in case of an error
        if (emptyFieldsExist) {
            JOptionPane.showMessageDialog(frame,
                    "Please do not leave any these fields empty:\n EnrollmentID,\nStudent Name,\nCourse Name,\nCourse Duration,\nNo. Of Modules,\nNo. of Credit Hours,\nTuition Fee and\nDays Present.\n\nFill all of them to continue",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } else if (invalidNumberInputs) {
            JOptionPane.showMessageDialog(frame,
                    "Please enter a valid number for:\nEnrollmentID,\nCourse Duration,\nNo. Of Modules,\nNo.Of Credit Hours,\nTuition Fee and\n Days Present.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } else if (valuesLessThanOne) {
            JOptionPane.showMessageDialog(frame,
                    "Theses fields cannot be less than zero:\nEnrollment ID,\nCourse Duration,\nNum of Modules,\nTuition Fee.\n\nPlease enter numbers greater than zero to continue.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } else if (daysPresentLessThanZero) {
            JOptionPane.showMessageDialog(frame,
                    "Days Present cannot be less than zero.\n\nPlease enter a positive number to continue.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        // This part runs only if there are no errors
        else {
            // To get enrollment date from combo box
            String selectedEnrollmentDay = (String) comboEnrollmentDays.getSelectedItem();
            String selectedEnrollmentMonth = (String) comboEnrollmentMonths.getSelectedItem();
            String selectedEnrollmentYear = (String) comboEnrollmentYears.getSelectedItem();
            String dateOfEnrollment = selectedEnrollmentDay + " " + selectedEnrollmentMonth + " "
                    + selectedEnrollmentYear;

            // To get date of birth from combo box
            String selectedDOBDay = (String) comboDOBDays.getSelectedItem();
            String selectedDOBMonth = (String) comboDOBMonths.getSelectedItem();
            String selectedDOBYear = (String) comboDOBYears.getSelectedItem();
            String dateOfBirth = selectedDOBDay + " " + selectedDOBMonth + " " + selectedDOBYear;

            // Creating a regular student from all the fields
            Regular regularStudent = new Regular(
                    dateOfBirth,
                    courseName,
                    tuitionFee,
                    studentName,
                    dateOfEnrollment,
                    courseDuration,
                    enrollmentID,
                    numOfModules,
                    numOfCreditHours,
                    daysPresent);

            // Direct addition to the arraylist if arraylist is empty
            if (arraylist.isEmpty()) {
                arraylist.add(regularStudent);
                JOptionPane.showMessageDialog(frame,
                        "Student with id: " + enrollmentID + " has been added successfully.");
            } else {

                // Check if student already exists in the arraylist
                for (Student studentInArray : arraylist) {
                    if (studentInArray instanceof Regular) {
                        if (studentInArray.get_enrollmentID() == Integer.parseInt(textEnrollmentID.getText())) {
                            studentAlreadyInList = true;
                            break;
                        }
                    }
                }

                if (studentAlreadyInList) {
                    JOptionPane.showMessageDialog(frame,
                            "DUPLICATE! Student with id: " + enrollmentID + " is already in the list.", "ERROR!",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    arraylist.add(regularStudent);
                    JOptionPane.showMessageDialog(frame,
                            "Student with id: " + enrollmentID + " has been added successfully.");
                }
            }

        }
    }

    private void addDropoutButtonPressed() {

        // Flags for errors
        boolean emptyFieldsExist = false;
        boolean invalidNumberInputs = false;
        boolean valuesLessThanOne = false;
        boolean studentAlreadyInList = false;

        // Check for empty fields
        if (textCourseNameDropout.getText().isEmpty()
                || textTuitionFeeDropout.getText().isEmpty()
                || textStudentNameDropout.getText().isEmpty()
                || textDurationDropout.getText().isEmpty()
                || textEnrollmentIDDropout.getText().isEmpty()
                || textRemainingModulesDropout.getText().isEmpty()
                || textMonthsAttendedDropout.getText().isEmpty()) {
            emptyFieldsExist = true;
        } else {
            courseName = textCourseNameDropout.getText();
            studentName = textStudentNameDropout.getText();
        }

        // Check for invalid numerical inputs
        try {
            enrollmentID = Integer.parseInt(textEnrollmentIDDropout.getText());
            courseDuration = Integer.parseInt(textDurationDropout.getText());
            numOfRemainingModules = Integer.parseInt(textRemainingModulesDropout.getText());
            numOfMonthsAttended = Integer.parseInt(textMonthsAttendedDropout.getText());
            tuitionFee = Integer.parseInt(textTuitionFeeDropout.getText());
        } catch (NumberFormatException e) {
            invalidNumberInputs = true;
        }

        // Check for values less than 1
        if (enrollmentID < 1
                || courseDuration < 1
                || numOfRemainingModules < 1
                || numOfMonthsAttended < 1
                || tuitionFee < 1) {
            valuesLessThanOne = true;
        }

        // Messages in case of an error
        if (emptyFieldsExist) {
            JOptionPane.showMessageDialog(frame,
                    "Please do not leave any these fields empty:\n EnrollmentID,\nStudent Name,\nCourse Name,\nCourse Duration,\nNo. Of Remaining Modules,\nNo. of Months Attended and\nTuition Fee.\n\nFill all of them to continue",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } else if (invalidNumberInputs) {
            JOptionPane.showMessageDialog(frame,
                    "Please enter a valid number for:\nEnrollmentID,\nCourse Duratio,\nNo. Of Remaining Modules,\nNo.Of Months Attended and\nTuition Fee.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } else if (valuesLessThanOne) {
            JOptionPane.showMessageDialog(frame,
                    "Theses fields cannot be less than zero:\nEnrollment ID,\nCourse Duration,\nNum of Remaining Modules\nNo. of Months Attended and\nTuition Fee.\n\nPlease enter numbers greater than zero to continue.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

        // This part only runs if there are no errors
        else {

            // To get date of birth from combo box
            String selectedDOBDay = (String) comboDOBDaysDropout.getSelectedItem();
            String selectedDOBMonth = (String) comboDOBMonthsDropout.getSelectedItem();
            String selectedDOBYear = (String) comboDOBYearsDropout.getSelectedItem();
            String dateOfBirth = selectedDOBDay + " " + selectedDOBMonth + " " + selectedDOBYear;

            // To get enrollment date from combo box
            String selectedEnrollmentDay = (String) comboEnrollmentDaysDropout.getSelectedItem();
            String selectedEnrollmentMonth = (String) comboEnrollmentMonthsDropout.getSelectedItem();
            String selectedEnrollmentYear = (String) comboEnrollmentYearsDropout.getSelectedItem();
            String dateOfEnrollment = selectedEnrollmentDay + " " + selectedEnrollmentMonth + " "
                    + selectedEnrollmentYear;

            // To get dropout date from combo box
            String selectedDropoutDay = (String) comboDropoutDays.getSelectedItem();
            String selectedDropoutMonth = (String) comboDropoutMonths.getSelectedItem();
            String selectedDropoutYear = (String) comboDropoutYears.getSelectedItem();
            String dateOfDropout = selectedDropoutDay + " " + selectedDropoutMonth + " " + selectedDropoutYear;

            // Creating a new dropout student from all the fields
            Dropout dropoutStudent = new Dropout(
                    dateOfBirth,
                    studentName,
                    courseDuration,
                    tuitionFee,
                    numOfRemainingModules,
                    numOfMonthsAttended,
                    dateOfDropout,
                    enrollmentID,
                    dateOfEnrollment,
                    courseName);

            // Direct insertion into the arraylist if arraylist is empty
            if (arraylist.isEmpty()) {
                arraylist.add(dropoutStudent);
                JOptionPane.showMessageDialog(frame,
                        "Student with id: " + enrollmentID + " has been added successfully.");
            } else {
                for (Student studentInArray : arraylist) {
                    if (studentInArray instanceof Dropout) {
                        if (studentInArray.get_enrollmentID() == enrollmentID) {
                            studentAlreadyInList = true;
                        }
                    }
                }

                if (studentAlreadyInList) {
                    JOptionPane.showMessageDialog(frame,
                            "DUPLICATE! Student with id: " + enrollmentID + " is already in the list.", "ERROR!",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    arraylist.add(dropoutStudent);
                    JOptionPane.showMessageDialog(frame,
                            "Student with id: " + enrollmentID + " has been added successfully.");
                }
            }
        }
    }

    private void calculatePresentPercentageButtonPressed() {
        // Errors have already been checked for in the getEnrollmentIDFromDialog method
        int enrollmentIDFromDialog = getEnrollmentIDFromDialog();

        if (enrollmentIDFromDialog != 0) {
            Regular foundRegularStudent = (Regular) findRegularStudentByID(enrollmentIDFromDialog);
            if (foundRegularStudent != null) {
                String presentPercentage = foundRegularStudent.presentPercentage(foundRegularStudent.get_daysPresent());
                JOptionPane.showMessageDialog(frame, "The attendence grade of the student with id: "
                        + foundRegularStudent.get_enrollmentID() + " is: " + presentPercentage);
            } else {
                JOptionPane.showMessageDialog(frame,
                        "The student with id: " + enrollmentIDFromDialog + " does not exist in the list", "ERROR!",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void grantCertificateButtonPressed() {
        // Errors have already been checked for in the getEnrollmentIDFromDialog method
        int enrollmentIDFromDialog = getEnrollmentIDFromDialog();

        if (enrollmentIDFromDialog != 0) {
            Regular foundRegularStudent = (Regular) findRegularStudentByID(enrollmentIDFromDialog);
            if (foundRegularStudent != null) {
                foundRegularStudent.grantCertificate(foundRegularStudent.get_courseName(), enrollmentIDFromDialog,
                        foundRegularStudent.get_dateOfEnrollment());
                JOptionPane.showMessageDialog(frame,
                        foundRegularStudent.get_studentName() + " has graduated from "
                                + foundRegularStudent.get_courseName() + " with enrollmentID " + enrollmentIDFromDialog
                                + " and enrollment date is " + foundRegularStudent.get_dateOfEnrollment());
            } else {
                JOptionPane.showMessageDialog(frame,
                        "The student with id: " + enrollmentIDFromDialog + " does not exist in the list", "ERROR!",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void payDropoutBillsButtonPressed() {
        // Errors have already been checked for in the getEnrollmentIDFromDialog method
        int enrollmentIDFromDialog = getEnrollmentIDFromDialog();

        if (enrollmentIDFromDialog != 0) {
            Dropout foundDropoutStudent = (Dropout) findDropoutStudentByID(enrollmentIDFromDialog);
            if (foundDropoutStudent != null) {
                foundDropoutStudent.billsPayable();
                String messageString = "Remaining amount: " + foundDropoutStudent.getremainingAmount()
                        + " of student with id: " + foundDropoutStudent.get_enrollmentID()
                        + " has been paid";
                JOptionPane.showMessageDialog(frame, messageString);
            } else {
                JOptionPane.showMessageDialog(frame,
                        "The student with id: " + enrollmentIDFromDialog + " does not exist in the list", "ERROR!",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void removeDropoutButtonPressed() {
        // Errors have already been checked for in the getEnrollmentIDFromDialog method
        int enrollmentIDFromDialog = getEnrollmentIDFromDialog();

        if (enrollmentIDFromDialog != 0) {
            Dropout foundDropoutStudent = (Dropout) findDropoutStudentByID(enrollmentIDFromDialog);
            if (foundDropoutStudent != null) {
                if (foundDropoutStudent.gethasPaid()) {
                    JOptionPane.showMessageDialog(frame, "The student with id: "
                            + foundDropoutStudent.get_enrollmentID() + " has been removed from the list.");
                    arraylist.remove(foundDropoutStudent);
                    foundDropoutStudent.removeStudent();
                } else {
                    JOptionPane.showMessageDialog(frame, "Please pay the remaining amount of student with id: "
                            + foundDropoutStudent.get_enrollmentID() + " first to remove them.");
                }
            } else {
                JOptionPane.showMessageDialog(frame,
                        "The student with id: " + enrollmentIDFromDialog + " does not exist in the list", "ERROR!",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void displayButtonPressed() {
        displayRegularFrame = new JFrame("Displaying Regular Students"); // creating a frame
        displayRegularFrame.setLayout(null);
        displayRegularFrame.setSize(900, 700);
        displayRegularFrame.setVisible(true);

        regularScroll = new JScrollPane(); // creating a JScrollPane
        regularScroll.setBounds(0, 0, 1200, 500);
        displayRegularFrame.add(regularScroll);

        regularTable = new JTable(); // creating a JmyTable
        regularScroll.setViewportView(regularTable);
        regularTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] { " " }));
        regularTable.setRowHeight(20);

        DefaultTableModel myTablemod1 = (DefaultTableModel) regularTable.getModel();

        String[] columnNames = { "Enrollment ID", "Student Name", "Date of Birth", "Course Name ", "Course Duration",
                "No. of Modules", "No. of Credit Hours", "TutionFee", "Date of Enrollment", "Days Present" };
        myTablemod1.setColumnIdentifiers(columnNames);

        // Assuming 'arraylist' is your ArrayList<Student>
        for (Student student : arraylist) {
            if (student instanceof Regular) {
                Regular r = (Regular) student;
                Object[] myTablerow = {
                        r.get_enrollmentID(),
                        r.get_studentName(),
                        r.get_dateOfBirth(),
                        r.get_courseName(),
                        r.get_courseDuration(),
                        r.get_numOfModules(),
                        r.get_numOfCreditHours(),
                        r.get_tuitionFee(),
                        r.get_dateOfEnrollment(),
                        r.get_daysPresent()
                };

                myTablemod1.addRow(myTablerow);
            }
        }
    }

    private void displayButtonPressedDropout() {
        displayDropoutFrame = new JFrame("Displaying Regular Students"); // creating a frame
        displayDropoutFrame.setLayout(null);
        displayDropoutFrame.setSize(900, 700);
        displayDropoutFrame.setVisible(true);

        dropoutScroll = new JScrollPane(); // creating a JScrollPane
        dropoutScroll.setBounds(0, 0, 1200, 500);
        displayDropoutFrame.add(dropoutScroll);

        dropoutTable = new JTable(); // creating a JmyTable
        dropoutScroll.setViewportView(dropoutTable);
        dropoutTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] { " " }));
        dropoutTable.setRowHeight(20);

        DefaultTableModel myTablemod1 = (DefaultTableModel) dropoutTable.getModel();

        String[] columnNames = { "EnrollmentID", "Student Name", "Date of Birth", "Date of Enrollment", "Course Name",
                "Course Duration", "Tuition Fee", "Number of Remaining Modules", "No. Of Months Attended",
                "Date of Dropout" };
        myTablemod1.setColumnIdentifiers(columnNames);

        for (Student student : arraylist) {
            if (student instanceof Dropout) {
                Dropout d = (Dropout) student;
                Object[] myTableRow = {
                        d.get_enrollmentID(),
                        d.get_studentName(),
                        d.get_dateOfBirth(),
                        d.get_dateOfEnrollment(),
                        d.get_courseName(),
                        d.get_courseDuration(),
                        d.get_tuitionFee(),
                        d.getnumOfRemainingModules(),
                        d.getnumOfMonthsAttended(),
                        d.getdateOfDropout(),
                };

                myTablemod1.addRow(myTableRow); // adding myTablerow to the model

            }
        }
    }

    private void clearButtonPressed() {
        // Clear text fields
        textStudentName.setText("");
        textCourseName.setText("");
        textEnrollmentID.setText("");
        textPresent.setText("");
        textDuration.setText("");
        textModules.setText("");
        textCredit.setText("");
        textTuitionFee.setText("");
        textRemainingModulesDropout.setText("");

        // Clear comboBoxes
        comboDOBDays.setSelectedIndex(0);
        comboDOBMonths.setSelectedIndex(0);
        comboDOBYears.setSelectedIndex(0);
        comboEnrollmentDays.setSelectedIndex(0);
        comboEnrollmentMonths.setSelectedIndex(0);
        comboEnrollmentYears.setSelectedIndex(0);
    }

    private void clearButtonPressedDropout() {
        // Clear text fields
        textStudentNameDropout.setText("");
        textCourseNameDropout.setText("");
        textEnrollmentIDDropout.setText("");
        textPresent.setText("");
        textDurationDropout.setText("");
        textModules.setText("");
        textCredit.setText("");
        textTuitionFeeDropout.setText("");
        textRemainingModulesDropout.setText("");
        textMonthsAttendedDropout.setText("");

        // Clear comboBoxes
        comboDOBDaysDropout.setSelectedIndex(0);
        comboDOBMonthsDropout.setSelectedIndex(0);
        comboDOBYearsDropout.setSelectedIndex(0);
        comboEnrollmentDaysDropout.setSelectedIndex(0);
        comboEnrollmentMonthsDropout.setSelectedIndex(0);
        comboEnrollmentYearsDropout.setSelectedIndex(0);
        comboDropoutDays.setSelectedIndex(0);
        comboDropoutMonths.setSelectedIndex(0);
        comboDropoutYears.setSelectedIndex(0);
    }

    // Helper functions
    private Student findRegularStudentByID(int enrollmentID) {
        for (Student student : arraylist) {
            if (student instanceof Regular) {
                if (student.get_enrollmentID() == enrollmentID) {
                    return student;
                }
            }
        }
        return null;
    }

    private Student findDropoutStudentByID(int enrollmentID) {
        for (Student student : arraylist) {
            if (student instanceof Dropout) {
                if (student.get_enrollmentID() == enrollmentID) {
                    return student;
                }
            }
        }
        return null;
    }

    private int getEnrollmentIDFromDialog() {
        boolean enrollementIDFromDialogEmpty = false;
        boolean enrollmentIDFromDialogInvalid = false;
        boolean enrollmentIDFromDialogLessThanOne = false;

        String enrollmentIDTextFromDialog = JOptionPane.showInputDialog(frame,
                "Please enter the EnrollmentID of the student.");
        int enrollmentIDFromDialog = 0;

        // Check for empty and null dialog field
        // JOptionPane returns null if CANCEL is presses
        if (enrollmentIDTextFromDialog == null || enrollmentIDTextFromDialog.isEmpty()) {
            enrollementIDFromDialogEmpty = true;
        }

        // Check for invalid dialog inputs
        try {
            enrollmentIDFromDialog = Integer.parseInt(enrollmentIDTextFromDialog);
        } catch (NumberFormatException e) {
            enrollmentIDFromDialogInvalid = true;
        }

        // Check for enrollment id from input less than one
        if (enrollmentIDFromDialog < 1) {
            enrollmentIDFromDialogLessThanOne = true;
        }

        // Error messages in case of errors
        if (enrollementIDFromDialogEmpty) {
            JOptionPane.showMessageDialog(frame, "EnrollmentID cannot be left empty.\nPlease try again.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else if (enrollmentIDFromDialogInvalid) {
            JOptionPane.showMessageDialog(frame, "EnrollmentID must be in valid numbers.\n Please try again.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else if (enrollmentIDFromDialogLessThanOne) {
            JOptionPane.showMessageDialog(frame, "EnrollmentID cannot be less that 1.\nPlease try again.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        // The enrollment id is only returned if there are no errors
        if (enrollementIDFromDialogEmpty || enrollmentIDFromDialogInvalid || enrollmentIDFromDialogLessThanOne) {
            return 0;
        } else {
            return enrollmentIDFromDialog;
        }

    }

    public static void main(String[] args) {
        // Create an instance of the StudentGUI class
        StudentGUI gui = new StudentGUI();
    }
}