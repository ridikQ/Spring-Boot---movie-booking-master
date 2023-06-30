package com.example.moviebookingticket.services;

import com.example.moviebookingticket.entity.*;
import com.example.moviebookingticket.repository.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private TimeTableRepository timeTableRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public String exportReportForUser(String reportFormat) throws FileNotFoundException, JRException {
        String path="C:\\Users\\AlbaniaLab\\Desktop\\Report";
        List<UserEntity>userEntities=userRepository.findAll();
        File file= ResourceUtils.getFile("classpath:user.jrxml");
        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(userEntities);
        Map<String,Object>parameters=new HashMap<>();
        parameters.put("createdBy","Java");
        JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,parameters,dataSource);
        if (reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\user.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\user.pdf");
        }
        return "report generated in path : "+path;


    }
    public String exportReportForTheater(String reportFormat) throws FileNotFoundException, JRException {
        String path="C:\\Users\\AlbaniaLab\\Desktop\\Report";
        List<TheaterEntity>theaterEntities=theaterRepository.findAll();
        File file= ResourceUtils.getFile("classpath:theater.jrxml");
        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(theaterEntities);
        Map<String,Object>parameters=new HashMap<>();
        parameters.put("createdBy","Java");
        JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,parameters,dataSource);
        if (reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\theater.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\theater.pdf");
        }
        return "report generated in path : "+path;


    }
    public String exportReportForTimeTable(String reportFormat) throws FileNotFoundException, JRException {
        String path="C:\\Users\\AlbaniaLab\\Desktop\\Report";
        List<TimeTableEntity>timeTableEntities=timeTableRepository.findAll();
        File file= ResourceUtils.getFile("classpath:timetable.jrxml");
        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(timeTableEntities);
        Map<String,Object>parameters=new HashMap<>();
        parameters.put("createdBy","Java");
        JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,parameters,dataSource);
        if (reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\timetable.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\timetable.pdf");
        }
        return "report generated in path : "+path;


    }
    public String exportReportForMovie(String reportFormat) throws FileNotFoundException, JRException {
        String path="C:\\Users\\AlbaniaLab\\Desktop\\Report";
        List<MovieEntity>movieEntities=movieRepository.findAll();
        File file= ResourceUtils.getFile("classpath:movie.jrxml");
        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(movieEntities);
        Map<String,Object>parameters=new HashMap<>();
        parameters.put("createdBy","Java");
        JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,parameters,dataSource);
        if (reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\movie.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\movie.pdf");
        }
        return "report generated in path : "+path;


    }
    public String exportReportForBooking(String reportFormat) throws FileNotFoundException, JRException {
        String path="C:\\Users\\AlbaniaLab\\Desktop\\Report";
        List<BookingEntity>bookingEntities=bookingRepository.findAll();
        File file= ResourceUtils.getFile("classpath:booking.jrxml");
        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(bookingEntities);
        Map<String,Object>parameters=new HashMap<>();
        parameters.put("createdBy","Java");
        JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,parameters,dataSource);
        if (reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\booking.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\booking.pdf");
        }
        return "report generated in path : "+path;


    }




}
