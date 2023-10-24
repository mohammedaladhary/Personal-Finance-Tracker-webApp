package com.sda.ironhack.Personal.Finance.Tracker.webApp.controllers;

import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.Report;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.User;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.services.implementations.ReportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ReportControllerImpl {

    @Autowired
    private ReportServiceImpl reportServiceImpl;

    @GetMapping("/users/reports")
    @ResponseStatus(HttpStatus.OK)
    public List<Report> showAllReports(){
        return reportServiceImpl.getAllReports();
    }

//    @PostMapping("/dashboard/generateReport")
//    @ResponseStatus(HttpStatus.OK)
//    public List<Report> generateReports() {
//        return reportServiceImpl.generateReport();
//    }
}
