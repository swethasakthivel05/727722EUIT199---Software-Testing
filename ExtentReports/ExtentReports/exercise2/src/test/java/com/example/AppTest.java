package com.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.codec.w3c.W3CHttpCommandCodec;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    WebDriver driver;
    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.ixigo.com/");
    }
    @Test(priority = 0)
    public void testCase1() throws InterruptedException, IOException {
        driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[1]/div[1]/div/button[2]")).click();
        driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[1]/div[1]/div")).click();
        WebElement from = driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[1]/div[2]/div/div/div[2]/input"));
        FileInputStream fs = new FileInputStream("C:\\Users\\91701\\Downloads\\input for websites.xlsx");
        XSSFWorkbook work = new XSSFWorkbook(fs);
        XSSFSheet sheet = work.getSheet("Bank login");
        XSSFRow row = sheet.getRow(5);
        String fromWhere = row.getCell(0).getStringCellValue();
        from.sendKeys(fromWhere);
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[1]/div[3]/div[1]/li")).click();
        driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div")).click();
        WebElement to = driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[2]/div[2]/div/div/div[2]/input"));
        String toWhere = row.getCell(1).getStringCellValue();
        to.sendKeys(toWhere);
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[2]/div[3]/div[1]/li")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[2]/div[1]/div/div/div")).click();
        Thread.sleep(2000);
        while(true)
        {
            String curr = driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[2]/div[3]/div/div[1]/div[1]/button[2]/span[1]")).getText();
            System.out.println(curr);
            String s[] = curr.split(" ");
            String currMonth = s[0];
            String currYear = s[1];
            if(currMonth.equals("November") && currYear.equals("2024"))
                break;
            driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[2]/div[3]/div/div[1]/div[1]/button[3]")).click();
            Thread.sleep(1000);
        }
        driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[2]/div[3]/div/div[1]/div[2]/div[1]/div/div/div[2]/button[11]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[2]/div[2]/div/div[1]/div")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[2]/div[3]/div/div[1]/div[2]/div[1]/div/div/div[2]/button[13]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[3]/div[1]/div")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[3]/div[2]/div/div[1]/div[1]/div[2]/div/button[3]")).click();
        driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[3]/div[2]/div/div[1]/div[2]/div[2]/div/button[3]")).click();
        driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[3]/div[2]/div/div[1]/div[5]/div/div[3]")).click();
        driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[3]/div[2]/div/div[2]/button")).click();
        Thread.sleep(1000);
        String returnDate = driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[2]/div[2]/div/div[1]/div/div/p[2]")).getText();
        if(returnDate.contains("08 Nov")) {
            System.out.println("The date was entered correctly");
        }
        else {
            System.out.println("The selected date was wrong");
        }
    }
    @Test(priority = 1)
    public void testCase2() throws InterruptedException {
        driver.findElement(By.linkText("About Us")).click();
        Thread.sleep(3000);
        String currWindow = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();
        for(String window : windows) {
            if(!window.equals(currWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
        String currUrl = driver.getCurrentUrl();
        if(currUrl.contains("about")) {
            System.out.println("The page has been redirected");
        }
        else {
            System.out.println("The page has not been redirected");
        }
    }
    @AfterTest
    public void quitDriver() {
        driver.quit();
    }
}
