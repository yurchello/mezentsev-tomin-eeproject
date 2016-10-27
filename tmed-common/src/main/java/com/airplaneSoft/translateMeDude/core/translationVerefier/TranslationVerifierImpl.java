package com.airplaneSoft.translateMeDude.core.translationVerefier;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Mezentsev.Y on 10/27/2016.
 */
public class TranslationVerifierImpl implements TranslationVerifier {
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
