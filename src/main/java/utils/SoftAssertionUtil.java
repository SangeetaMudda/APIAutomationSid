package utils;

import org.testng.asserts.SoftAssert;

public class SoftAssertionUtil {
    private static SoftAssert softAssertInstance;

    private SoftAssertionUtil(){

    }

    public static SoftAssert getSoftAssertInstance(){
        if(softAssertInstance == null){
            softAssertInstance = new SoftAssert();
        }
        return softAssertInstance;
    }
    /*public SoftAssertionUtil(){
        softAssert = new SoftAssert();
    }*/
    public static void assertTrue(boolean condition, String message){
        try{
            getSoftAssertInstance().assertTrue(condition, message);
    }catch(AssertionError e){
            getSoftAssertInstance().fail(message);
        }
    }
    public static void assertFalse(boolean condition, String message){
        try{
            getSoftAssertInstance().assertFalse(condition, message);
        }catch(AssertionError e){
            getSoftAssertInstance().fail(message);
        }
    }
    public static void assertEquals(Object actual, Object expected, String message){
        try{
            getSoftAssertInstance().assertEquals(actual, expected, message);
        }catch(AssertionError e){
            getSoftAssertInstance().fail(message);
        }
    }
    public static void assertNotEquals(Object actual, Object expected, String message){
        try{
            getSoftAssertInstance().assertNotEquals(actual, expected, message);
        }catch(AssertionError e){
            getSoftAssertInstance().fail(message);
        }
    }
    public static void assertAll(){
        getSoftAssertInstance().assertAll();
    }
}
