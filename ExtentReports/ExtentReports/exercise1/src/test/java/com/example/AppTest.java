package com.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    WebDriver driver;
    ExtentReports reports;
    ExtentTest test;
    @BeforeTest
    public void setup() {
        reports = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(
                "C:\\Users\\91701\\Desktop\\it sckcet\\softwareTesting-1\\ExtentReports\\exercise1\\report.html");
        reports.attachReporter(spark);
        test = reports.createTest("Demo Result");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://groww.in/");
    }
    @Test(priority = 1)
    public void test() throws IOException, InterruptedException {
        driver.findElement(By.linkText("Calculators")).click();
        Thread.sleep(2000);
        File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = "C:\\Users\\91701\\Desktop\\it sckcet\\softwareTesting-1\\ExtentReports\\exercise1\\calculator.png";
        FileUtils.copyFile(screen, new File(path));
        test.addScreenCaptureFromPath(path);
        reports.flush();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[2]/a[15]")).click();
        Thread.sleep(2000);
        WebElement loanAmount = driver.findElement(By.xpath("//*[@id=\"LOAN_AMOUNT\"]"));
        Thread.sleep(2000);
        loanAmount.clear();
        Thread.sleep(2000);
        FileInputStream fs = new FileInputStream("C:\\Users\\91701\\Downloads\\input for websites.xlsx");
        Thread.sleep(2000);
        XSSFWorkbook workBook = new XSSFWorkbook(fs);
        XSSFSheet sheet = workBook.getSheet("Bank login");
        XSSFRow row = sheet.getRow(4);
        String amt = row.getCell(0).toString();
        loanAmount.sendKeys(amt);
        Thread.sleep(2000);
        WebElement interest = driver.findElement(By.xpath("//*[@id=\"RATE_OF_INTEREST\"]"));
        interest.clear();
        Thread.sleep(2000);
        String in = row.getCell(1).toString();
        interest.sendKeys(in);
        Thread.sleep(2000);
        WebElement loanTenure = driver.findElement(By.xpath("//*[@id=\"LOAN_TENURE\"]"));
        loanTenure.clear();
        Thread.sleep(2000);
        String ten = row.getCell(2).toString();
        interest.sendKeys(ten);
        Thread.sleep(2000);
        String ver = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div/p")).getText();
        if(ver.equals("Your Amortization Details (Yearly/Monthly)")) {
            System.out.println("Successful");
        }
        else {
            System.out.println("Unsuccessful");
        }
    }
    @AfterTest
    public void quitDriver() {
        driver.quit();
    }
}
