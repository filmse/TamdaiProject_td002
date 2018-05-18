package com.tamdai.model.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Arrays;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserImage {

    @Id
    @GeneratedValue
    private Long id;
    private String fileName;
    private byte[] content;
    private String contentType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public UserImage() {
    }

    public UserImage(String fileName, byte[] content, String contentType) {
        this.fileName = fileName;
        this.content = content;
        this.contentType = contentType;
    }

    @Override
    public String toString() {
        return "UserImage{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", content=" + Arrays.toString(content) +
                ", contentType='" + contentType + '\'' +
                '}';
    }

}

