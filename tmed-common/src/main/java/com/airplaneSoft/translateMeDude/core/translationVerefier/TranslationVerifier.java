package com.airplaneSoft.translateMeDude.core.translationVerefier;

/**
 * Created by Mezentsev.Y on 10/27/2016.
 */
public interface TranslationVerifier {
    String DELIMITER = ",";

    /**
     * @param inputString is string from dialog form
     * @param vocabularyString is string from vocabulary
     * @return true if translation is correct
     */
    boolean verify(String inputString, String vocabularyString);
}
