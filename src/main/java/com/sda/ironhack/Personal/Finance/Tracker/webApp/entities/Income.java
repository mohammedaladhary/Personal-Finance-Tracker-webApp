package com.sda.ironhack.Personal.Finance.Tracker.webApp.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="tbl_income")
public class Income {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="income_id")
    private int incomeId;

    private String source;
    private double amount;
    private Date date;
    public Income() {
    }
    public Income(String source, double amount, Date date) {
        this.source = source;
        this.amount = amount;
        this.date = date;
    }

    // Getters and setters

    public int getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(int incomeId) {
        this.incomeId = incomeId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
