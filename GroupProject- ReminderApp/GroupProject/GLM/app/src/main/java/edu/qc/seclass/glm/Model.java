
package edu.qc.seclass.glm;

public class Model {
    String  type, name, date, time, note;
    boolean check = false;
    Model(){

    }

    public Model(String type, String name, String date, String time, String note) {
        this.type = type;
        this.name = name;
        this.date = date;
        this.time = time;
        this.note = note;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getNote() {
        return note;
    }

    public boolean getCheck() { return check; }

    public void setType(String type) {this.type = type;}

    public void setName(String name) {this.name = name;}

    public void setDate(String date) {this.date = date;}

    public void setTime(String time) {this.time = time;}

    public void setNote(String note) {this.name = note;}

    public void setChecked(boolean check) { this.check = check; }




}