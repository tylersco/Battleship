package com.csci4448.MediaManagementSystem.ui.design;

public enum Confirmation {

    BUYMEDIA ("Are you sure you want to buy this?", "Yes", "No"),
    RENTMEDIA ("Are you sure you want to rent this?", "Yes", "No"),
    SELLMEDIA ("Are you sure you want to sell this?", "Yes", "No"),
    RETURNMEDIA ("Are you sure you want to return this?", "Yes", "No"),
    LOGOUT ("Are you sure you want to sign out?", "Sign out", "Cancel"),
    ADMINSAVE ("Are you sure you want to save changes to the media?", "Yes", "No"),
    ADMINNEW ("Are you sure you want to create new media?", "Yes", "No"),
    ADMINCANCEL ("Are you sure you want to discard changes to the media?", "Yes", "No"),
    ;

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
