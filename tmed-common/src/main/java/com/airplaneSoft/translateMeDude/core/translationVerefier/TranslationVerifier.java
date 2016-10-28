package com.airplaneSoft.translateMeDude.core.translationVerefier;

public interface TranslationVerifier {
    /**
     * delimiter char for word translation
     */
    String DELIMITER = ",";

    /**
     * @param inputString is string from dialog form
     * @param vocabularyString is string from vocabulary
     * @return true if translation is correct
     */
    boolean verify(String inputString, String vocabularyString);
}
