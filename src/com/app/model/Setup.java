package com.app.model;

public class Setup {

    private int id;
    private String identification_name;
    private String identification_prefix;
    private int starting_number;
    private int current_number;

    public Setup() {
    }

    public Setup(int id, String identification_name, String identification_prefix, int starting_number, int current_number) {
        this.id = id;
        this.identification_name = identification_name;
        this.identification_prefix = identification_prefix;
        this.starting_number = starting_number;
        this.current_number = current_number;
    }

    public Setup(String identification_prefix, int current_number) {
        this.identification_prefix = identification_prefix;
        this.current_number = current_number;
    }

    public String getNewNumber() {
        return identification_prefix +"-"+ (current_number + 1);
    }

    public int getNextCurrentNumber() {
        return ++current_number;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentification_name() {
        return identification_name;
    }

    public void setIdentification_name(String identification_name) {
        this.identification_name = identification_name;
    }

    public String getIdentification_prefix() {
        return identification_prefix;
    }

    public void setIdentification_prefix(String identification_prefix) {
        this.identification_prefix = identification_prefix;
    }

    public int getStarting_number() {
        return starting_number;
    }

    public void setStarting_number(int starting_number) {
        this.starting_number = starting_number;
    }

    public int getCurrent_number() {
        return current_number;
    }

    public void setCurrent_number(int current_number) {
        this.current_number = current_number;
    }
}
