package telefonbuch;

import java.io.Serializable;

public class TelefonNumber implements Serializable {
    private String lastName;
    private String firstName;
    private String number;

    TelefonNumber(String lastName, String firstName, String number) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.number = number;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getNumber() {
        return number;
    }
}
