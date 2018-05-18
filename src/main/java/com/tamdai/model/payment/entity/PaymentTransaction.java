package com.tamdai.model.payment.entity;

import com.tamdai.model.security.entity.UserEntity;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "paymentTransaction")
public class PaymentTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "accountId")
    private Long accountId;

    @Column(name = "transType")
    private String transType;

    @Column(name = "transRef")
    private String transRef;

    @Column(name = "transFlq")
    private Integer transFlq;

    @Column(name = "transAmount")
    private String transAmount;

    @Column(name = "transRemark")
    private String transRemark;

    @Column(name = "createDate")
    private String createDate;

    @Column(name = "createTime")
    private String createTime;

    @Column(name = "createUserId")
    private Long createUserId;

    @Column(name = "updateDate")
    private String updateDate;

    @Column(name = "updateTime")
    private String updateTime;

    @Column(name = "updateUserId")
    private Long updateUserId;

    @Column(name = "systemRemark")
    private String systemRemark;

    @ManyToMany(fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<UserEntity> users = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getTransRef() {
        return transRef;
    }

    public void setTransRef(String transRef) {
        this.transRef = transRef;
    }

    public Integer getTransFlq() {
        return transFlq;
    }

    public void setTransFlq(Integer transFlq) {
        this.transFlq = transFlq;
    }

    public String getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(String transAmount) {
        this.transAmount = transAmount;
    }

    public String getTransRemark() {
        return transRemark;
    }

    public void setTransRemark(String transRemark) {
        this.transRemark = transRemark;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }

    public String getSystemRemark() {
        return systemRemark;
    }

    public void setSystemRemark(String systemRemark) {
        this.systemRemark = systemRemark;
    }

    public PaymentTransaction() {
    }

    @Override
    public String toString() {
        return "PaymentTransaction{" +
                "id=" + id +
                ", accountId=" + accountId +
                ", transType='" + transType + '\'' +
                ", transRef='" + transRef + '\'' +
                ", transFlq=" + transFlq +
                ", transAmount='" + transAmount + '\'' +
                ", transRemark='" + transRemark + '\'' +
                ", createDate='" + createDate + '\'' +
                ", createTime='" + createTime + '\'' +
                ", createUserId=" + createUserId +
                ", updateDate='" + updateDate + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", updateUserId=" + updateUserId +
                ", systemRemark=" + systemRemark +
                ", users=" + users +
                '}';
    }

    public PaymentTransaction(Long accountId, String transType, String transRef, Integer transFlq, String transAmount, String transRemark, String createDate, String createTime, Long createUserId, String updateDate, String updateTime, Long updateUserId, Set<UserEntity> users) {
        this.accountId = accountId;
        this.transType = transType;
        this.transRef = transRef;
        this.transFlq = transFlq;
        this.transAmount = transAmount;
        this.transRemark = transRemark;
        this.createDate = createDate;
        this.createTime = createTime;
        this.createUserId = createUserId;
        this.updateDate = updateDate;
        this.updateTime = updateTime;
        this.updateUserId = updateUserId;
        this.users = users;
    }

    public PaymentTransaction(Long accountId, String transType, String transRef, Integer transFlq, String transAmount, String transRemark, String createDate, String createTime, Long createUserId, String updateDate, String updateTime, Long updateUserId, String systemRemark, Set<UserEntity> users) {
        this.accountId = accountId;
        this.transType = transType;
        this.transRef = transRef;
        this.transFlq = transFlq;
        this.transAmount = transAmount;
        this.transRemark = transRemark;
        this.createDate = createDate;
        this.createTime = createTime;
        this.createUserId = createUserId;
        this.updateDate = updateDate;
        this.updateTime = updateTime;
        this.updateUserId = updateUserId;
        this.systemRemark = systemRemark;
        this.users = users;
    }
}
