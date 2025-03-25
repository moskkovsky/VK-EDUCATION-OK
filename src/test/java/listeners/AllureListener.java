package listeners;

import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import java.lang.reflect.Method;

import static com.codeborne.selenide.Selenide.screenshot;
import static com.codeborne.selenide.Selenide.sleep;
import static org.openqa.selenium.OutputType.BYTES;

public class AllureListener implements AfterTestExecutionCallback {

    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        Method testMethod = context.getRequiredTestMethod();
        String testName = testMethod.getName();
        boolean testFailed = context.getExecutionException().isPresent();

        if (testFailed && !testName.contains("Screenshot")) {
            sleep(500);
            byte[] screenshotBytes = screenshot(BYTES);
            saveScreenshot(screenshotBytes);
        }
    }
}