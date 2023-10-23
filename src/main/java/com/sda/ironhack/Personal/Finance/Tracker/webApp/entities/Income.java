package com.sda.ironhack.Personal.Finance.Tracker.webApp.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

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
    private LocalDate date;
    public Income() {
    }

    public Income(User user, String source, double amount, LocalDate date) {
        this.user = user;
        this.source = source;
        this.amount = amount;
        this.date = date;
    }
    // Getters and setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getIncomeId() {
        return incomeId;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Income income = (Income) o;
        return incomeId == income.incomeId && Double.compare(amount, income.amount) == 0 && Objects.equals(user, income.user) && Objects.equals(source, income.source) && Objects.equals(date, income.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, incomeId, source, amount, date);
    }

    @Override
    public String toString() {
        return "Income{" +
                "user=" + user +
                ", incomeId=" + incomeId +
                ", source='" + source + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}
