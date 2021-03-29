package com.cuni.mff.java.Domain;

public class User {
    public User(String name, String password, String UID, String GID, String GECOS, String directory, String shell) {
        this.name = name;
        this.password = password;
        this.UID = UID;
        this.GID = GID;
        this.GECOS = GECOS;
        this.directory = directory;
        this.shell = shell;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return "x";
    }

    public String getUID() {
        return UID;
    }

    public String getGID() {
        return GID;
    }

    public String getGECOS() {
        return GECOS;
    }

    public String getDirectory() {
        return directory;
    }

    public String getShell() {
        return shell;
    }

    private String name,password,UID,GID,GECOS,directory,shell;

}
