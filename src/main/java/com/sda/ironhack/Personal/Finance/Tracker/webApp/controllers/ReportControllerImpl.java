package com.sda.ironhack.Personal.Finance.Tracker.webApp.controllers;

import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.Report;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.User;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.services.implementations.ReportServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ReportControllerImpl {

    @Autowired
    private ReportServiceImpl reportServiceImpl;

    @GetMapping("/financeTracker/dashBoard/users/reports")
    @ResponseStatus(HttpStatus.OK)
    public List<Report> showAllReports(){
        return reportServiceImpl.getAllReports();
    }

    @DeleteMapping("/financeTracker/dashBoard/users/reports/delete/{userId}")
    public String deleteReport(@PathVariable int userId){
        return reportServiceImpl.deleteReport(userId);
    }

    @PostMapping("/financeTracker/dashBoard/users/reports/generateReportById/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Report> generateReports(@PathVariable int userId, @RequestBody @Valid Report report) {
        return reportServiceImpl.generateReport(userId, report);
    }
}
