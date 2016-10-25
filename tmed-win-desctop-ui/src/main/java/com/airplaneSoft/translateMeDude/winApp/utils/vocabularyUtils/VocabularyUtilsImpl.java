package com.airplaneSoft.translateMeDude.winApp.utils.vocabularyUtils;

import com.airplaneSoft.translateMeDude.models.vocabulary.WordsGroup;

import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;

/**
 * Created by Mezentsev.Y on 10/24/2016.
 */
public class VocabularyUtilsImpl implements VocabularyUtils {
    @Override
    public boolean saveFullVocabulary(List<WordsGroup> wordsGroupList) {
        Objects.requireNonNull(wordsGroupList);
        try {
            Files.deleteIfExists(VOCABULARY_FILE_PATH);
            Files.createFile(VOCABULARY_FILE_PATH);
        } catch (IOException e) {
            System.out.println("Vocabulary serialize failed.");
            e.printStackTrace();
            return false;
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(VOCABULARY_FILE_PATH.toFile());
             ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream)){
            outputStream.writeObject(wordsGroupList);
            System.out.println("Vocabulary serialize success.");
        } catch (IOException e) {
            System.out.println("Vocabulary serialize failed.");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<WordsGroup> getFullVocabulary() {
        try (FileInputStream fileInputStream = new FileInputStream(VOCABULARY_FILE_PATH.toFile());
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
            List<WordsGroup> list = (List<WordsGroup>) objectInputStream.readObject();
            System.out.println("Vocabulary deserialize success.");
            return list;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Vocabulary deserialize failed.");
            e.printStackTrace();
            return null;
        }
    }
}
