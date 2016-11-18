package com.airplaneSoft.translateMeDude.core.translationVerefier;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TranslationVerifierImpl implements TranslationVerifier {
    /**
     * Verify translation from input form and vocabulary word translation
     * @param inputString is string from dialog form
     * @param vocabularyString is string from vocabulary
     * @return true if translation is correct
     */
    @Override
    public boolean verify(String inputString, String vocabularyString) {
        List<String> inputStringList = getWords(inputString);
        List<String> vocabularyStringList = getWords(vocabularyString);
        if (inputStringList.size() == 0)return false;
        for (String word: inputStringList){
            if (!vocabularyStringList.contains(word))return false;
        }
        return true;
    }

    private List<String> getWords(String string){
        List<String> list = new ArrayList<>();
        StringTokenizer inputStringTokenizer = new StringTokenizer(string, DELIMITER);
        while (inputStringTokenizer.hasMoreTokens()) {
            String token = inputStringTokenizer.nextToken();
            token = token.trim();
            token = token.replaceAll("\\s+"," ");
            list.add(token);
            System.out.println("|" + token + "|");
        }
        return list;
    }
}
