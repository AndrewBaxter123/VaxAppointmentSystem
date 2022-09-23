package sample;

public class Utilities {


    private static String numbersOnly = "[0-9]{10}";
    private static String ppsNumsandChars = "[0-9]{7}[a-zA-Z]{2}"; // 7 digits and 2 chars
    private static String eircodeCheck = "[0-9A-Z]{7}"; // 7 containing digits or chars
    private static String BoothID1Letter1Number = "[A-Za-z]{1}[0-9]{1}"; // 7 containing digits or chars


    public static boolean oneLetterOneNumber(String text) { // TEST THIS BEFORE USING IT!
        if (text != null) {
            return (text.matches(BoothID1Letter1Number));
        }
        return false;
    }


    public static boolean onlyContainsNumbers(String text) {
        if (text != null) {
            return (text.matches(numbersOnly));
        }
        return false;
    }

    public static boolean validPPS(String string) {
        if (string != null) {
            return (string.matches(ppsNumsandChars));
        }
        return false;
    }

    public static boolean validEircode(String eir) {
        if (eir != null) {
            return (eir.matches(eircodeCheck));
        }
        return false;
    }

}
