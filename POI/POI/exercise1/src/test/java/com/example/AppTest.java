package com.example;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    WebDriver driver;
    FileInputStream fs;
    XSSFWorkbook workBook;
    XSSFSheet sheet;
    XSSFRow row;
    String user, password;
    @BeforeTest
    public void setup() throws IOException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://dbankdemo.com/bank/login");
        fs = new FileInputStream("C:\\Users\\91701\\Downloads\\input for websites.xlsx");
        workBook = new XSSFWorkbook(fs);
        sheet = workBook.getSheet("Bank login");
        row = sheet.getRow(1);
        user = row.getCell(0).getStringCellValue();
        password = row.getCell(1).getStringCellValue();
    }
    @BeforeMethod
    public void login() {
        driver.findElement(By.id("username")).sendKeys(user);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("submit")).click();
    }
    @Test(priority = 0)
    public void testCase1() {
        String curr = driver.getCurrentUrl();
        if(curr.contains("home")) {
            System.out.println("Login Successful.\nThe redirected page contains the keyword \"home\"");
        }
        else {
            System.out.println("Login Unsuccessful.\nThe redirected page doesn't contain the keyword \"home\"");
        }
    }
    @Test(priority = 1)
    public void testCase2() {
        driver.findElement(By.linkText("Deposit")).click();
        Select account = new Select(driver.findElement(By.id("selectedAccount")));
        account.selectByValue("434969");
        driver.findElement(By.id("amount")).sendKeys("5000");
        driver.findElement(By.xpath("//*[@id=\"right-panel\"]/div[2]/div/div/div/div/form/div[2]/button[1]")).click();
        String depositedAmount = driver.findElement(By.xpath("//*[@id=\"transactionTable\"]/tbody/tr[1]/td[4]")).getText();
        if(depositedAmount.contains("5000")) {
            System.out.println("The deposited amount \"5000\" is present in the table");
        }
        else {
            System.out.println("The testCase is failed");
        }
    }
    @Test(priority = 2)
    public void testCase3() {
        driver.findElement(By.linkText("Withdraw")).click();
        Select account = new Select(driver.findElement(By.id("selectedAccount")));
        account.selectByValue("434969");
        driver.findElement(By.id("amount")).sendKeys("3000");
        driver.findElement(By.xpath("//*[@id=\"right-panel\"]/div[2]/div/div/div/div/form/div[2]/button[1]")).click();
        String withdrawalAmount = driver.findElement(By.xpath("//*[@id=\"transactionTable\"]/tbody/tr[1]/td[4]")).getText();
        if(withdrawalAmount.contains("-3000")) {
            System.out.println("The withdrawal amount \"3000\" is present inthe table");
        }
        else {
            System.out.println("The withdrawal amount \"3000\" is not present in the table");
        }
    }
    @AfterMethod
    public void logout() {
        driver.findElement(By.xpath("//*[@id=\"header\"]/div/div[2]/div[1]/a/img")).click();
        driver.findElement(By.xpath("//*[@id=\"header\"]/div/div[2]/div[1]/div/a[5]")).click();
    }
    @AfterTest
    public void quitDriver() {
        driver.quit();
    }
}
