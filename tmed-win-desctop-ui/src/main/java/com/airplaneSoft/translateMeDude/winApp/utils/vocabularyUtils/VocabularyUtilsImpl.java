package com.airplaneSoft.translateMeDude.winApp.utils.vocabularyUtils;

import com.airplaneSoft.translateMeDude.models.vocabulary.WordsGroup;
import com.airplaneSoft.translateMeDude.winApp.App;
import org.apache.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

/**
 * This class provide save vocabulary to drive and get it from drive.
 */
public class VocabularyUtilsImpl implements VocabularyUtils {
    private Path path;

    public VocabularyUtilsImpl(Path path) {
        this.path = path;
    }

    private static final Logger LOGGER = Logger.getLogger(VocabularyUtilsImpl.class);
    /**
     * Serialize  wordsGroupList to hard drive.
     * @param wordsGroupList
     * @return true if operation is success
     */
    @Override
    public boolean saveFullVocabulary(List<WordsGroup> wordsGroupList) {
        Objects.requireNonNull(wordsGroupList);
        try {
            Files.deleteIfExists(this.path);
            Files.createFile(this.path);
        } catch (IOException e) {
            LOGGER.error("Vocabulary serialize failed.", e);
            return false;
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(this.path.toFile());
             ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream)){
            outputStream.writeObject(wordsGroupList);
            LOGGER.info("Vocabulary serialize success.");
        } catch (IOException e) {
            LOGGER.error("Vocabulary serialize failed.", e);
            return false;
        }
        return true;
    }

    /**
     * Deserialize from hard drive.
     * @return
     */
    @Override
    public List<WordsGroup> getFullVocabulary() {
        try (FileInputStream fileInputStream = new FileInputStream(this.path.toFile());
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
            List<WordsGroup> list = (List<WordsGroup>) objectInputStream.readObject();
            LOGGER.info("Vocabulary deserialize success.");
            return list;
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.error("Vocabulary deserialize failed.", e);
            return null;
        }
    }
}
