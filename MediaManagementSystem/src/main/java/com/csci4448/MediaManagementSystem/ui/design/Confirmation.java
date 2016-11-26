package com.csci4448.MediaManagementSystem.ui.design;

public enum Confirmation {

    BUYMEDIA ("Are you sure you want to buy this?", "Yes", "No"),
    RENTMEDIA ("Are you sure you want to buy this?", "Yes", "No"),
    SELLMEDIA ("Are you sure you want to buy this?", "Yes", "No"),
    RETURNMEDIA ("Are you sure you want to buy this?", "Yes", "No"),
    LOGOUT ("Are you sure you want to sign out?", "Sign out", "Cancel"),;

    private String text;
    private String confirmText;
    private String cancelText;

    Confirmation(String text, String confirmText, String cancelText) {
        this.text = text;
        this.confirmText = confirmText;
        this.cancelText = cancelText;
    }

    public String getText() {
        return text;
    }

    public String getConfirmText() {
        return confirmText;
    }

    public String getCancelText() {
        return cancelText;
    }

}
