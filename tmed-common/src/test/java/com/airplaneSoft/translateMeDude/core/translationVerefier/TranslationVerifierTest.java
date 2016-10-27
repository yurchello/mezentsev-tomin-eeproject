package com.airplaneSoft.translateMeDude.core.translationVerefier;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by Mezentsev.Y on 10/27/2016.
 */
public class TranslationVerifierTest {
    //input correct strings
    private static String EXMPL_CORRECT_1 = "собака";
    private static String EXMPL_CORRECT_2 = "собака,псина,пес";
    private static String EXMPL_CORRECT_3 = "собака, псина, пес";
    private static String EXMPL_CORRECT_4 = "собака,псина, пес";
    //input correct strings with whitespaces
    private static String EXMPL_WHITESPACES_CORRECT_1 = "собака ";
    private static String EXMPL_WHITESPACES_CORRECT_2 = "собака ,псина ,  пес";
    private static String EXMPL_WHITESPACES_CORRECT_3 = "собака,     псина, пес   ";
    private static String EXMPL_WHITESPACES_CORRECT_4 = "собака ,псина, пес ";

    //input incorrect strings
    private static String EXMPL_INCORRECT_1 = "собак";
    private static String EXMPL_INCORRECT_2 = "собака,псина,белка";
    private static String EXMPL_INCORRECT_3 = "собака, псинка, пес";


    @Test(priority = 10)
    public void testVerifyExample1() throws Exception {
        TranslationVerifier translationVerifier = new TranslationVerifierImpl();
        boolean result = translationVerifier.verify(EXMPL_CORRECT_1,EXMPL_CORRECT_1);
        assertTrue(result);
    }

    @Test(priority = 20)
    public void testVerifyExample2() throws Exception {
        TranslationVerifier translationVerifier = new TranslationVerifierImpl();
        boolean result = translationVerifier.verify(EXMPL_CORRECT_1,EXMPL_CORRECT_2);
        assertTrue(result);
    }

    @Test(priority = 30)
    public void testVerifyExample3() throws Exception {
        TranslationVerifier translationVerifier = new TranslationVerifierImpl();
        boolean result = translationVerifier.verify(EXMPL_CORRECT_1,EXMPL_CORRECT_3);
        assertTrue(result);
    }

    @Test(priority = 40)
    public void testVerifyExample4() throws Exception {
        TranslationVerifier translationVerifier = new TranslationVerifierImpl();
        boolean result = translationVerifier.verify(EXMPL_CORRECT_1,EXMPL_CORRECT_4);
        assertTrue(result);
    }

    @Test(priority = 50)
    public void testVerifyExample5() throws Exception {
        TranslationVerifier translationVerifier = new TranslationVerifierImpl();
        boolean result = translationVerifier.verify(EXMPL_CORRECT_2,EXMPL_CORRECT_3);
        assertTrue(result);
    }

    @Test(priority = 60)
    public void testVerifyExample6() throws Exception {
        TranslationVerifier translationVerifier = new TranslationVerifierImpl();
        boolean result = translationVerifier.verify(EXMPL_CORRECT_3,EXMPL_CORRECT_4);
        assertTrue(result);
    }

    //correct with whitespaces


    //incorect cases
    @Test(priority = 70)
    public void testVerifyExample7() throws Exception {
        TranslationVerifier translationVerifier = new TranslationVerifierImpl();
        boolean result = translationVerifier.verify(EXMPL_INCORRECT_1,EXMPL_CORRECT_1);
        assertTrue(!result);
    }

    @Test(priority = 80)
    public void testVerifyExample8() throws Exception {
        TranslationVerifier translationVerifier = new TranslationVerifierImpl();
        boolean result = translationVerifier.verify(EXMPL_INCORRECT_1,EXMPL_CORRECT_2);
        assertTrue(!result);
    }

    @Test(priority = 90)
    public void testVerifyExample9() throws Exception {
        TranslationVerifier translationVerifier = new TranslationVerifierImpl();
        boolean result = translationVerifier.verify(EXMPL_INCORRECT_1,EXMPL_CORRECT_3);
        assertTrue(!result);
    }
    @Test(priority = 100)
    public void testVerifyExample10() throws Exception {
        TranslationVerifier translationVerifier = new TranslationVerifierImpl();
        boolean result = translationVerifier.verify(EXMPL_INCORRECT_1,EXMPL_CORRECT_4);
        assertTrue(!result);
    }

    @Test(priority = 110)
    public void testVerifyExample11() throws Exception {
        TranslationVerifier translationVerifier = new TranslationVerifierImpl();
        boolean result = translationVerifier.verify(EXMPL_INCORRECT_2,EXMPL_CORRECT_2);
        assertTrue(!result);
    }

    @Test(priority = 120)
    public void testVerifyExample12() throws Exception {
        TranslationVerifier translationVerifier = new TranslationVerifierImpl();
        boolean result = translationVerifier.verify(EXMPL_INCORRECT_3,EXMPL_CORRECT_3);
        assertTrue(!result);
    }

    // correct with whitespaces
    @Test(priority = 130)
    public void testVerifyExample13() throws Exception {
        TranslationVerifier translationVerifier = new TranslationVerifierImpl();
        boolean result = translationVerifier.verify(EXMPL_CORRECT_1,EXMPL_WHITESPACES_CORRECT_1);
        assertTrue(result);
    }

    @Test(priority = 140)
    public void testVerifyExample14() throws Exception {
        TranslationVerifier translationVerifier = new TranslationVerifierImpl();
        boolean result = translationVerifier.verify(EXMPL_CORRECT_1,EXMPL_WHITESPACES_CORRECT_2);
        assertTrue(result);
    }

    @Test(priority = 150)
    public void testVerifyExample15() throws Exception {
        TranslationVerifier translationVerifier = new TranslationVerifierImpl();
        boolean result = translationVerifier.verify(EXMPL_CORRECT_1,EXMPL_WHITESPACES_CORRECT_3);
        assertTrue(result);
    }

    @Test(priority = 160)
    public void testVerifyExample16() throws Exception {
        TranslationVerifier translationVerifier = new TranslationVerifierImpl();
        boolean result = translationVerifier.verify(EXMPL_CORRECT_1,EXMPL_WHITESPACES_CORRECT_4);
        assertTrue(result);
    }

    @Test(priority = 170)
    public void testVerifyExample17() throws Exception {
        TranslationVerifier translationVerifier = new TranslationVerifierImpl();
        boolean result = translationVerifier.verify(EXMPL_CORRECT_2,EXMPL_WHITESPACES_CORRECT_3);
        assertTrue(result);
    }

    @Test(priority = 180)
    public void testVerifyExample18() throws Exception {
        TranslationVerifier translationVerifier = new TranslationVerifierImpl();
        boolean result = translationVerifier.verify(EXMPL_CORRECT_3,EXMPL_WHITESPACES_CORRECT_4);
        assertTrue(result);
    }

}