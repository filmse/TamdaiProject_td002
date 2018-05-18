package com.tamdai.model.robomind.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Content {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "stStudentId")
    private String stStudentId;

    @Column(name = "stContent", length = 2000)
    private String stContent;

    @Column(name = "stTeacher")
    private String stTeacher;

    @Column(name = "contentDate")
    private String contentDate;

    @Column(name = "rbName")
    private String rbName;

    @Column(name = "rbGroup")
    private String rbGroup;

    @Column(name = "stTime")
    private String stTime;

    @ManyToMany(fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<ContentImg> contentImgs = new HashSet<>();

    public String getRbName() {
        return rbName;
    }

    public void setRbName(String rbName) {
        this.rbName = rbName;
    }

    public String getRbGroup() {
        return rbGroup;
    }

    public void setRbGroup(String rbGroup) {
        this.rbGroup = rbGroup;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStStudentId() {
        return stStudentId;
    }

    public void setStStudentId(String stStudentId) {
        this.stStudentId = stStudentId;
    }

    public String getStContent() {
        return stContent;
    }

    public void setStContent(String stContent) {
        this.stContent = stContent;
    }

    public Set<ContentImg> getContentImgs() {
        return contentImgs;
    }

    public void setContentImgs(Set<ContentImg> contentImgs) {
        this.contentImgs = contentImgs;
    }

    public String getContentDate() {
        return contentDate;
    }

    public void setContentDate(String contentDate) {
        this.contentDate = contentDate;
    }

    public String getStTeacher() {
        return stTeacher;
    }

    public void setStTeacher(String stTeacher) {
        this.stTeacher = stTeacher;
    }

    public String getStTime() {
        return stTime;
    }

    public void setStTime(String stTime) {
        this.stTime = stTime;
    }

    public Content() {
    }

    public Content(String stStudentId, String stContent, String stTeacher, String contentDate, String rbName, String rbGroup, Set<ContentImg> contentImgs) {
        this.stStudentId = stStudentId;
        this.stContent = stContent;
        this.stTeacher = stTeacher;
        this.contentDate = contentDate;
        this.rbName = rbName;
        this.rbGroup = rbGroup;
        this.contentImgs = contentImgs;
    }

    public Content(String stStudentId, String stContent, String stTeacher, String contentDate, String rbName, Set<ContentImg> contentImgs) {
        this.stStudentId = stStudentId;
        this.stContent = stContent;
        this.stTeacher = stTeacher;
        this.contentDate = contentDate;
        this.rbName = rbName;
        this.contentImgs = contentImgs;
    }

    public Content(String stStudentId, String stContent, String stTeacher, String contentDate, String rbName, String rbGroup, String stTime, Set<ContentImg> contentImgs) {
        this.stStudentId = stStudentId;
        this.stContent = stContent;
        this.stTeacher = stTeacher;
        this.contentDate = contentDate;
        this.rbName = rbName;
        this.rbGroup = rbGroup;
        this.stTime = stTime;
        this.contentImgs = contentImgs;
    }

    public Content(String stStudentId, String stContent, String stTeacher, String contentDate, Set<ContentImg> contentImgs) {
        this.stStudentId = stStudentId;
        this.stContent = stContent;
        this.stTeacher = stTeacher;
        this.contentDate = contentDate;
        this.contentImgs = contentImgs;
    }

    public Content(String stStudentId, String stContent) {
        this.stStudentId = stStudentId;
        this.stContent = stContent;
    }

    public Content(String stStudentId, String stContent, Set<ContentImg> contentImgs) {
        this.stStudentId = stStudentId;
        this.stContent = stContent;
        this.contentImgs = contentImgs;
    }

    public Content(String stStudentId, String stContent, String contentDate, Set<ContentImg> contentImgs) {
        this.stStudentId = stStudentId;
        this.stContent = stContent;
        this.contentDate = contentDate;
        this.contentImgs = contentImgs;
    }

    @Override
    public String toString() {
        return "Content{" +
                "id=" + id +
                ", stStudentId='" + stStudentId + '\'' +
                ", stContent='" + stContent + '\'' +
                ", stTeacher='" + stTeacher + '\'' +
                ", contentDate='" + contentDate + '\'' +
                ", rbName='" + rbName + '\'' +
                ", rbGroup='" + rbGroup + '\'' +
                ", stTime='" + stTime + '\'' +
                ", contentImgs=" + contentImgs +
                '}';
    }
}
