package com.sda.ironhack.Personal.Finance.Tracker.webApp.services.interfaces;

import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.Report;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.User;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {
//    Report generateReport(User user, LocalDate startDate, LocalDate endDate);

    List<Report> getAllReports();

    String deleteReport(int userId);

    List<Report> generateReport(int userId, Report report);
}
