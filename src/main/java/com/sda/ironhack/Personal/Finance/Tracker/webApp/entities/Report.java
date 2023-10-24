package com.sda.ironhack.Personal.Finance.Tracker.webApp.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tbl_report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private int reportId;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "total_income")
    private double totalIncome;

    @Column(name = "total_expense")
    private double totalExpense;

    @Column(name = "updated_balance")
    private double updatedBalance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Report() {
    }

    public Report(LocalDate startDate, LocalDate endDate, double totalIncome, double totalExpense, double updatedBalance, User user) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
        this.updatedBalance = updatedBalance;
        this.user = user;
    }

    public int getReportId() {
        return reportId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(double totalExpense) {
        this.totalExpense = totalExpense;
    }

    public double getUpdatedBalance() {
        return updatedBalance;
    }

    public void setUpdatedBalance(double updatedBalance) {
        this.updatedBalance = updatedBalance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return reportId == report.reportId && Double.compare(totalIncome, report.totalIncome) == 0 && Double.compare(totalExpense, report.totalExpense) == 0 && Double.compare(updatedBalance, report.updatedBalance) == 0 && Objects.equals(startDate, report.startDate) && Objects.equals(endDate, report.endDate) && Objects.equals(user, report.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reportId, startDate, endDate, totalIncome, totalExpense, updatedBalance, user);
    }

    @Override
    public String toString() {
        return "Report{" +
                "reportId=" + reportId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", totalIncome=" + totalIncome +
                ", totalExpense=" + totalExpense +
                ", updatedBalance=" + updatedBalance +
                ", user=" + user +
                '}';
    }
}

