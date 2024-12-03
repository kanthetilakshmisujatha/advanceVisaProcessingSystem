
public class VisaApplication {
    private String firstName;
    private String lastName;
    private String passportNumber;
    private String visaType;
    private String status;

    public VisaApplication(String firstName, String lastName, String passportNumber, String visaType, String status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportNumber = passportNumber;
        this.visaType = visaType;
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public String getVisaType() {
        return visaType;
    }

    public String getStatus() {
        return status;
    }
}
