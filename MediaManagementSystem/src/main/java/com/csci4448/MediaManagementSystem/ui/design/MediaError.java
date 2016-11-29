package com.csci4448.MediaManagementSystem.ui.design;

public enum MediaError {

    INVALID_INFORMATION("Invalid Information", "ok"),
    USERNAME_TAKEN("Username already taken", "ok"),
    SERVER_ERROR("Server Error (Creating User)", "ok"),
    SERVER_UNAVAILABLE("Server Unavailable", "ok"),
    PASSWORDS_DONT_MATCH("Passwords do not match", "Ok"),

    SYSTEM_ERROR("System Error", "ok"),
    INVALID_PURCHASE_TYPE("Media has invalid purchase type", "ok"),
    OUT_OF_STOCK("Currently out of stock", "ok"),
    INSUFFICIENT_BALANCE("Insufficient account balance", "ok"),
    UNAVAILABLE("Media Unavailable", "ok"),
    NOT_FOUND_IN_INVENTORY("Item can not be found in your personal inventory", "ok"),
    INVALID_RETURN_TYPE("Media has invalid return type", "ok"),

    ALREADY_REVIEWED("Sorry, you have already reviewed this item", "ok"),
    ;

    private String text;
    private String confirmText;

    MediaError(String text, String confirmText) {
        this.text = text;
        this.confirmText = confirmText;
    }

    public String getText() {
        return text;
    }

    public String getConfirmText() {
        return confirmText;
    }

}
