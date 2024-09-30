package com.example.smartwaterqualitymonitoringsystem.Model;

public class User
{
    String Dated;
    String Id;
    String PHSensor;
    String TDSSensor;
    String TemperatureSensor;
    String Timed;
    String TurbiditySensor;
    String Waterlavel;


    public User(String dated, String id, String PHSensor, String TDSSensor, String temperatureSensor, String timed, String turbiditySensor, String waterlavel) {
        Dated = dated;
        Id = id;
        this.PHSensor = PHSensor;
        this.TDSSensor = TDSSensor;
        TemperatureSensor = temperatureSensor;
        Timed = timed;
        TurbiditySensor = turbiditySensor;
        Waterlavel = waterlavel;
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
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getPHSensor() {
        return PHSensor;
    }

    public void setPHSensor(String PHSensor) {
        this.PHSensor = PHSensor;
    }

    public String getTDSSensor() {
        return TDSSensor;
    }

    public void setTDSSensor(String TDSSensor) {
        this.TDSSensor = TDSSensor;
    }

    public String getTemperatureSensor() {
        return TemperatureSensor;
    }

    public void setTemperatureSensor(String temperatureSensor) {
        TemperatureSensor = temperatureSensor;
    }

    public String getTimed() {
        return Timed;
    }

    public void setTimed(String timed) {
        Timed = timed;
    }

    public String getTurbiditySensor() {
        return TurbiditySensor;
    }

    public void setTurbiditySensor(String turbiditySensor) {
        TurbiditySensor = turbiditySensor;
    }

    public String getWaterlavel() {
        return Waterlavel;
    }

    public void setWaterlavel(String waterlavel) {
        Waterlavel = waterlavel;
    }
}
