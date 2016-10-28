package com.airplaneSoft.translateMeDude.core.wordGenerator;

import com.airplaneSoft.translateMeDude.models.vocabulary.Word;

/**
 * Provide to get Word entity to display on popup balloon
 */
//@FunctionalInterface
public interface WordGenerator {
    Word getWord();
}
