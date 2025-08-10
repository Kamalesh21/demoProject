package newSele;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import org.openqa.selenium.Keys;
public class MyTest {
public WebDriver driver;
public Actions AU;
@Parameters({"URL"})
@BeforeMethod
public void beforeMethod(String url) throws InterruptedException {
	ChromeOptions options = new ChromeOptions();
	options.addArguments("--remote-allow-origins=*");
System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
driver = new ChromeDriver(options);
driver.get(url);
driver.manage().window().maximize();
Thread.sleep(3000);
AU = new Actions(driver);
}
@Test
public void draggable() throws InterruptedException {
System.out.println("first one");
driver.navigate().to("https://jqueryui.com/draggable/");
driver.switchTo().frame(0);

//Draggable
WebElement draggable = driver.findElement(By.id("draggable"));
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
int x = draggable.getLocation().getX();
int y = draggable.getLocation().getY();
AU.dragAndDropBy(draggable, x+30, y+25).perform();
}
@Test(priority=-1)
public void droppable() throws InterruptedException{
//Droppable
System.out.println("fifth one");
driver.navigate().to("https://jqueryui.com/droppable/");
driver.switchTo().frame(0);
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
WebElement source = driver.findElement(By.id("draggable"));
WebElement dest = driver.findElement(By.id("droppable"));
AU.dragAndDrop(source, dest).perform();

}
@Test
public void resizable() throws InterruptedException{
//Resizable
System.out.println("fourth one");
driver.navigate().to("https://jqueryui.com/resizable/");
driver.switchTo().frame(0);
WebElement resize = driver.findElement(By.xpath("//div[@id='resizable']/div[2]"));
AU.clickAndHold(resize).pause(3).moveByOffset(5, 6).release().perform();
}
@Test(dependsOnMethods="newSele.actionsCl.sortable",alwaysRun=true)
public void selectable() throws InterruptedException{
//selectable
System.out.println("Third one");
driver.navigate().to("https://jqueryui.com/selectable/");
driver.switchTo().frame(0);
WebElement it1 = driver.findElement(By.xpath("//ol/li[text()='Item 1']"));
WebElement it2 = driver.findElement(By.xpath("//ol/li[text()='Item 2']"));
WebElement it4 = driver.findElement(By.xpath("//ol/li[text()='Item 4']"));
AU.keyDown(Keys.CONTROL).click(it1).click(it2).click(it4).keyUp(Keys.CONTROL).perform();
}
@Test
public void sortable() throws InterruptedException{
//sortable
System.out.println("second one");
driver.navigate().to("https://jqueryui.com/sortable/");
Assert.assertEquals(driver.getTitle(), "Sortable | jQuery UI");
driver.switchTo().frame(0);
WebElement li1 = driver.findElement(By.xpath("//ul/li[1]"));
WebElement li4 = driver.findElement(By.xpath("//ul/li[4]"));
int x = li4.getLocation().getX();
int y = li4.getLocation().getY();
AU.dragAndDropBy(li1, x,y).perform();

}

@AfterMethod
public void afterMethod()
{
driver.close();
}
}