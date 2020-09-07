package com.ruoyi.code.domain;

import java.util.List;

public class FileData {
    private List<String> initialPreview;
    private List<FileObject> initialPreviewConfig;

    public List<String> getInitialPreview() {
        return initialPreview;
    }

    public List<FileObject> getInitialPreviewConfig() {
        return initialPreviewConfig;
    }

    public void setInitialPreview(List<String> initialPreview) {
        this.initialPreview = initialPreview;
    }

    public void setInitialPreviewConfig(List<FileObject> initialPreviewConfig) {
        this.initialPreviewConfig = initialPreviewConfig;
    }
}
