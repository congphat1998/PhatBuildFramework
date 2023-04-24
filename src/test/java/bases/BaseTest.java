package bases;

import org.example.core.drivers.DriverManager;
import org.example.report.AllureManager;
import org.example.utils.TestListener;
import org.testng.annotations.*;

import static org.example.constant.Constants.URL;

@Listeners({TestListener.class})
public class BaseTest {
    @BeforeClass(alwaysRun = true)
    @Parameters({"browser"})
    public void createDriver(@Optional("chrome") String browser) throws Exception{
        DriverManager.getLocalDriver(browser);
        AllureManager.setAllureEnvironmentInformation(URL, browser, "Moi Truong Nop Bai");
    }
    @AfterClass(alwaysRun = true)
    public void quitDriver(){ DriverManager.quitDriver(); }
}
