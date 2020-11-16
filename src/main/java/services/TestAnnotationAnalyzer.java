package services;

import pages.MainPage;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class TestAnnotationAnalyzer {

    public void parse(Class<?> clazz) {
        Method[] methods = clazz.getMethods();
        int pass = 0;
        int fail = 0;

        for (Method method : methods) {
            if (method.isAnnotationPresent(Timeout.class)) {
                Timeout timeout = method.getAnnotation(Timeout.class);
                int seconds = timeout.seconds();
                MainPage.driver.manage().timeouts().pageLoadTimeout(seconds, TimeUnit.SECONDS);
                try {
                    method.invoke(null);
                    pass++;
                } catch (Exception e) {
                    fail++;
                }
            }
        }
    }
}
