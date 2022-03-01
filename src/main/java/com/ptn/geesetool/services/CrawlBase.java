package com.ptn.geesetool.services;

public class CrawlBase {
    private String keyWords;

    private String typeDownload;

    private int fixedThread = 1;

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getTypeDownload() {
        return typeDownload;
    }

    public void setTypeDownload(String typeDownload) {
        this.typeDownload = typeDownload;
    }

    public int getFixedThread() {
        return fixedThread;
    }

    public void setFixedThread(int fixedThread) {
        this.fixedThread = fixedThread;
    }
}
