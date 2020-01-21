package com.example.validator;

public class DataTest {

    @DocumentConstraint
    private String document;

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }
}
