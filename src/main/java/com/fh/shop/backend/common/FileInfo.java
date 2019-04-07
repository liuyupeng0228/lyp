package com.fh.shop.backend.common;

import java.io.InputStream;
import java.io.Serializable;

public class FileInfo implements Serializable {

    private static final long serialVersionUID = 6214944229198777952L;

    private InputStream is;
    private long size;
    private String fileName;

    public InputStream getIs() {
        return is;
    }

    public void setIs(InputStream is) {
        this.is = is;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
