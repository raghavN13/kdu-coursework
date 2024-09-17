package org.example.hospitalmanagement;

public class Doctor extends User{
    private long doctorId;
    private String specialization;

    public long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(long doctorId) {
        this.doctorId = doctorId;
    }


}
