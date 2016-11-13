package com.csci4448.MediaManagementSystem.model.media;

public class SystemInventory {

    private static SystemInventory systemInventory;

    private SystemInventory() {}

    public static synchronized SystemInventory getSystemInventory() {
        if (systemInventory == null)
            systemInventory = new SystemInventory();
        return systemInventory;
    }

}
