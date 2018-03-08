package com.weekseven.demo.Model;

public class Value {
    private Long id;
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Value{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
