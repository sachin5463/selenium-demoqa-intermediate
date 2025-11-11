//public class ExtentManager {
//
//    private static ExtentReports extent;
//
//    public static ExtentReports getInstance() {
//        if (extent == null) {
//            createInstance();
//        }
//        return extent;
//    }
//
//    public static ExtentReports createInstance() {
//        // ✅ timestamped file name, har run me nayi file
//        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport_" + timestamp + ".html";
//
//        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
//        reporter.config().setDocumentTitle("DemoQA Test Report");
//        reporter.config().setReportName("Automation Execution Report");
//
//        extent = new ExtentReports();
//        extent.attachReporter(reporter);
//        extent.setSystemInfo("Tester", "Sachin Bende");
//        extent.setSystemInfo("Environment", "QA");
//
//        System.out.println("📁 Report path: " + reportPath);
//        return extent;
//    }
//}


//package com.demoqa.utils;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.aventstack.extentreports.reporter.configuration.Theme;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class ExtentManager {
//    private static ExtentReports extent;
//
//    public static ExtentReports createInstance() {
//        if (extent == null) {
//            String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport.html";
//            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
//            reporter.config().setTheme(Theme.DARK);
//            reporter.config().setDocumentTitle("DemoQA Automation Report");
//            reporter.config().setReportName("Intermediate Framework Execution");
//
//            extent = new ExtentReports();
//            extent.attachReporter(reporter);
//        }
//        return extent;
//    }
//
//    public static ExtentReports getInstance() {
//        if (extent == null)
//            createInstance();
//        return extent;
//    }
//}


//package com.demoqa.utils;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.aventstack.extentreports.reporter.configuration.Theme;
//import java.io.File;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class ExtentManager {
//    private static ExtentReports extent;
//
//    public static ExtentReports createInstance() {
//        if (extent == null) {
//            // 📁 Create reports folder if not exists
//            String reportsDir = System.getProperty("user.dir") + "/reports";
//            File dir = new File(reportsDir);
//            if (!dir.exists()) {
//                dir.mkdirs();
//                System.out.println("📁 Created missing reports directory: " + dir.getAbsolutePath());
//            }
//
//            // 🕒 Unique timestamped report name
//            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//            String reportPath = reportsDir + "/ExtentReport_" + timestamp + ".html";
//
//            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
//            reporter.config().setTheme(Theme.DARK);
//            reporter.config().setDocumentTitle("DemoQA Automation Report");
//            reporter.config().setReportName("Intermediate Framework Execution");
//
//            extent = new ExtentReports();
//            extent.attachReporter(reporter);
//            extent.setSystemInfo("Tester", "Sachin Bende");
//            extent.setSystemInfo("Environment", "QA");
//            extent.setSystemInfo("Browser", "Chrome");
//        }
//        return extent;
//    }
//
//    public static ExtentReports getInstance() {
//        if (extent == null)
//            createInstance();
//        return extent;
//    }
//}

package com.demoqa.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.File;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports createInstance() {
        if (extent == null) {
            // 📁 Ensure test-output folder exists
            String outputDir = System.getProperty("user.dir") + "/test-output";
            File dir = new File(outputDir);
            if (!dir.exists()) {
                dir.mkdirs();
                System.out.println("📁 Created missing test-output directory: " + dir.getAbsolutePath());
            }

            // ✅ Keep report inside test-output (same as before)
            String reportPath = outputDir + "/ExtentReport.html";

            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
            reporter.config().setTheme(Theme.DARK);
            reporter.config().setDocumentTitle("DemoQA Automation Report");
            reporter.config().setReportName("Intermediate Framework Execution");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Tester", "Sachin Bende");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Browser", "Chrome");
        }
        return extent;
    }

    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance();
        return extent;
    }
}