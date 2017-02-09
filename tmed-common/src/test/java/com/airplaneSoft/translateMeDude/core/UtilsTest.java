package com.airplaneSoft.translateMeDude.core;

import com.airplaneSoft.translateMeDude.core.utils.EncoderUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by Mezentsev.Y on 12/2/2016.
 */
public class UtilsTest {
    @Test
    public void testGetPasswordEncoder() throws Exception {
        final String PASSWORD = "pass1234";
        String encode = EncoderUtils.getPasswordEncoder().encode(PASSWORD);
        Assert.assertTrue(EncoderUtils.getPasswordEncoder().matches(PASSWORD, encode));
    }

}