package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test(priority = 1)
    public void testSuccessfulLogin() {
        System.out.println("\n=== Test 1: Başarılı Login ===");
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Şu anki URL: " + currentUrl);
        
        Assert.assertTrue(currentUrl.contains("inventory.html"), 
            "Login başarısız! Inventory sayfasına yönlendirilmedi. URL: " + currentUrl);
        
        System.out.println("✓ Test Başarılı!");
    }

    @Test(priority = 2)
    public void testLockedOutUser() {
        System.out.println("\n=== Test 2: Kilitli Kullanıcı ===");
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        boolean errorDisplayed = loginPage.isErrorDisplayed();
        String errorMsg = loginPage.getErrorMessage();
        String currentUrl = driver.getCurrentUrl();
        
        System.out.println("Hata gösteriliyor mu: " + errorDisplayed);
        System.out.println("Hata mesajı: " + errorMsg);
        System.out.println("Şu anki URL: " + currentUrl);
        
        Assert.assertTrue(errorDisplayed, "Hata mesajı görüntülenmedi!");
        Assert.assertFalse(currentUrl.contains("inventory.html"), 
            "Kilitli kullanıcı inventory sayfasına erişmemeli!");
        
        System.out.println("✓ Test Başarılı!");
    }
}