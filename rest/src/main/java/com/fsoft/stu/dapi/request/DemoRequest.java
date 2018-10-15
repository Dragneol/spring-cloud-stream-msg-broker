package com.fsoft.stu.dapi.request;

public class DemoRequest {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "DemoRequest{"
                + "content="
                + content
                + '}';
    }
}
