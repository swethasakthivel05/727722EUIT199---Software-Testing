package com.example;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
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
        driver.get("https://www.demoblaze.com/");
    }
    @Test(priority = 1)
    public void testCase1() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.linkText("Laptops")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/div[3]/div/div/h4/a")).click();
        Thread.sleep(2000);
        driver.findElement(By.linkText("Add to cart")).click();
        Thread.sleep(1000);
        driver.switchTo().alert().accept();
        driver.findElement(By.linkText("Cart")).click();
        Thread.sleep(2000);
        String title = driver.findElement(By.xpath("//*[@id='tbodyid']/tr[1]/td[2]")).getText();
        String price = driver.findElement(By.xpath("//*[@id='tbodyid']/tr[1]/td[3]")).getText();
        Thread.sleep(5000);
        if(title.equals("MacBook Air") && price.equals("700")) {
            System.out.println("Title and price of the product is verified");
        }
        else {
            System.out.println("Tile and price of product is not verified");
        }
    }
    @Test(priority = 2)
    public void testCase2() throws IOException, InterruptedException {
        driver.findElement(By.linkText("Log in")).click();
        FileInputStream fs = new FileInputStream("C:\\Users\\91701\\Downloads\\input for websites.xlsx");
        XSSFWorkbook workBook = new XSSFWorkbook(fs);
        XSSFSheet sheet = workBook.getSheet("Bank login");
        XSSFRow row = sheet.getRow(3);
        String user = row.getCell(0).getStringCellValue();
        String password = row.getCell(1).getStringCellValue();
        driver.findElement(By.id("loginusername")).sendKeys(user);
        driver.findElement(By.id("loginpassword")).sendKeys(password);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[2]")).click();
        Thread.sleep(3000);
        String check = driver.findElement(By.linkText("Welcome Testalpha")).getText();
        if(check.equals("Welcome Testalpha")) {
            System.out.println("Login Successful");
        }
        else {
            System.out.println("Login unsuccessful");
          }
     }
    @AfterMethod
    public void navigate() {
        driver.navigate().to("https://www.demoblaze.com/");
    }
    @AfterTest
    public void driverQuit() {
        driver.quit();
    }
}
