package com.example.smartwaterqualitymonitoringsystem.Model;

public class User
{
    String Dated;
    String id;
    String Fan;
    String Humidity;
    String LDR;
    String RFID;
    String Smoke;
    String Temp;
    String Timed;
    String img;


    public User(String dated, String id, String fan, String humidity, String LDR, String RFID, String smoke, String temp, String timed, String img) {
        Dated = dated;
        this.id = id;
        Fan = fan;
        Humidity = humidity;
        this.LDR = LDR;
        this.RFID = RFID;
        Smoke = smoke;
        Temp = temp;
        Timed = timed;
        this.img = img;
    }


    public User() {
    }


    public String getDated() {
        return Dated;
    }

    public void setDated(String dated) {
        Dated = dated;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFan() {
        return Fan;
    }

    public void setFan(String fan) {
        Fan = fan;
    }

    public String getHumidity() {
        return Humidity;
    }

    public void setHumidity(String humidity) {
        Humidity = humidity;
    }

    public String getLDR() {
        return LDR;
    }

    public void setLDR(String LDR) {
        this.LDR = LDR;
    }

    public String getRFID() {
        return RFID;
    }

    public void setRFID(String RFID) {
        this.RFID = RFID;
    }

    public String getSmoke() {
        return Smoke;
    }

    public void setSmoke(String smoke) {
        Smoke = smoke;
    }

    public String getTemp() {
        return Temp;
    }

    public void setTemp(String temp) {
        Temp = temp;
    }

    public String getTimed() {
        return Timed;
    }

    public void setTimed(String timed) {
        Timed = timed;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
