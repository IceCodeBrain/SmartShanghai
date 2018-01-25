package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models;

/**
 * Created by Pithou on 11/04/2016.
 */
public class ModelResponseUpdateData {

    private boolean isMostRecent;
    private String fileName;
    private String lastModified;
    private String absoluteFilePath;

    public ModelResponseUpdateData() {
    }

    public ModelResponseUpdateData(boolean isMostRecent, String fileName, String lastModified, String absoluteFilePath) {
        this.isMostRecent = isMostRecent;
        this.fileName = fileName;
        this.lastModified = lastModified;
        this.absoluteFilePath = absoluteFilePath;
    }

    public boolean isMostRecent() {
        return isMostRecent;
    }

    public void setIsMostRecent(boolean isMostRecent) {
        this.isMostRecent = isMostRecent;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getAbsoluteFilePath() {
        return absoluteFilePath;
    }

    public void setAbsoluteFilePath(String absoluteFilePath) {
        this.absoluteFilePath = absoluteFilePath;
    }
}
