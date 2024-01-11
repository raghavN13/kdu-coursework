package org.example.question2;

public class Passenger {
    private String confirmationNumber;

    public Passenger(String firstName, String lastName, int age, String gender, String travelClass,
                     String confirmationNumber) {
        super();
        this.confirmationNumber = confirmationNumber;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((confirmationNumber == null) ? 0 : confirmationNumber.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Passenger other = (Passenger) obj;
        if (confirmationNumber == null) {
            if (other.confirmationNumber != null)
                return false;
        } else if (!confirmationNumber.equals(other.confirmationNumber))
            return false;
        return true;
    }
}
