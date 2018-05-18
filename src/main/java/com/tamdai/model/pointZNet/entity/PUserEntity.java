package com.tamdai.model.pointZNet.entity;

import javax.persistence.*;

@Entity
@Table(name = "puser")
public class PUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private long id;

    @Column(name = "userId")
    private String userId;

    @Column(name = "point")
    private String point;

    @Column(name = "money")
    private String money;

    @Column(name = "status")
    private String status;

    @Column(name = "statementDate")
    private String statementDate;

    @Column(name = "statementTime")
    private String statementTime;

    @Column(name = "bankName")
    private String bankName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatementDate() {
        return statementDate;
    }

    public void setStatementDate(String statementDate) {
        this.statementDate = statementDate;
    }

    public String getStatementTime() {
        return statementTime;
    }

    public void setStatementTime(String statementTime) {
        this.statementTime = statementTime;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public PUserEntity() {
    }

    public PUserEntity(String userId, String point, String money, String status, String statementDate, String statementTime, String bankName) {
        this.userId = userId;
        this.point = point;
        this.money = money;
        this.status = status;
        this.statementDate = statementDate;
        this.statementTime = statementTime;
        this.bankName = bankName;
    }

    @Override
    public String toString() {
        return "PUserEntity{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", point='" + point + '\'' +
                ", money='" + money + '\'' +
                ", status='" + status + '\'' +
                ", statementDate='" + statementDate + '\'' +
                ", statementTime='" + statementTime + '\'' +
                ", bankName='" + bankName + '\'' +
                '}';
    }
}
