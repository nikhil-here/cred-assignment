package com.assignment.cred.Entity;

public class Emi {
    private String amount;
    private String duration;
    private int colorId;
    private Boolean isSelected = false;

    public Emi(String amount, String duration, int colorId, Boolean isSelected) {
        this.amount = amount;
        this.duration = duration;
        this.colorId = colorId;
        this.isSelected = isSelected;
    }


    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
