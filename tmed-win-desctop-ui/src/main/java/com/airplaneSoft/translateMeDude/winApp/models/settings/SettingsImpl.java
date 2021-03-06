package com.airplaneSoft.translateMeDude.winApp.models.settings;

import com.airplaneSoft.translateMeDude.winApp.App;
import com.airplaneSoft.translateMeDude.winApp.utils.AppUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * This class allows keep settings as settings map as key-value model with
 * hard drive saving.
 */
public class SettingsImpl implements Settings {
    private static final Logger LOGGER = Logger.getLogger(SettingsImpl.class);
    private static final String SETTINGS_FILE_NAME = "settings.xml";
    public static final String APP_FOLDER = "TranslateMe_Dude";
    private static final Path APP_PATH = Paths.get(System.getProperty("user.home"), APP_FOLDER);
    private static final File SETTINGS_FILE = APP_PATH.resolve(SETTINGS_FILE_NAME).toFile();
    private static final SettingsImpl instance = new SettingsImpl();
    private String VOCABULARY_FILE_NAME = "vocabulary.vcb";
    private Path VOCABULARY_FILE_PATH = APP_PATH.resolve(VOCABULARY_FILE_NAME);

    private final Map<String, String> settingsMap = new TreeMap<>();


    private SettingsImpl() {
        load();
    }

    public static SettingsImpl getInstance() {
        return instance;
    }

    @Override
    public void set(String key, Object value) {
        settingsMap.put(key, Objects.toString(value, null));
    }

    @Override
    public String get(String key) {
        return settingsMap.get(key);
    }

    @Override
    public Map<String,String> getSettingsMap(){
        return settingsMap;
    }

    @Override
    public void clearSettingsMap() {
        settingsMap.clear();
        set(SettingsKeys.URL, AppUtils.getStringProperty("settings.url"));
        set(SettingsKeys.SSOID, AppUtils.getStringProperty("settings.ssoid"));
        set(SettingsKeys.PASSWORD, AppUtils.getStringProperty("settings.password"));
        set(SettingsKeys.SHOW, AppUtils.getStringProperty("settings.show"));
        set(SettingsKeys.SHOW_TIMER, SettingsKeys.TimerValues.RANDOM);
        set(SettingsKeys.TIMER_VALUE, AppUtils.getStringProperty("settings.timer.value.min"));
    }

    /**
     * This method provides to save settings to hard drive as xml file.
     * If file doesn't exist, it will be create.
     */
    public void save() {
        try {
            if(SETTINGS_FILE == null){
                LOGGER.warn("File path is not set. " + SETTINGS_FILE);
                return;
            }

            File settingsDir = SETTINGS_FILE.getParentFile();
            if(!settingsDir.exists() && !settingsDir.mkdirs()) {
                LOGGER.warn("Settings directory " + settingsDir + " not exist.");
                return;
            }

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            putSettingsToDocument(document);
            saveDocument(document);

            LOGGER.info("Settings saved");
        } catch (Exception e) {
            LOGGER.error("Settings save failed.", e);
        }
    }

    /**
     * Puts settings map to DOM document
     */
    private void putSettingsToDocument(Document document){
        Element rootElement = document.createElement("settings");
        document.appendChild(rootElement);
        for (String key: settingsMap.keySet()) {
            Element itemElement = document.createElement("setting");
            Attr k = document.createAttribute("key");
            k.setValue(key);
            itemElement.setAttributeNode(k);

            Attr v = document.createAttribute("value");
            v.setValue(settingsMap.get(key));
            itemElement.setAttributeNode(v);
            rootElement.appendChild(itemElement);
        }
    }

    private void saveDocument(Document document) throws TransformerException, FileNotFoundException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
        DOMSource domSource = new DOMSource(document);
        OutputStream output = new FileOutputStream(SETTINGS_FILE);
        StreamResult streamResult = new StreamResult(output);
        transformer.transform(domSource, streamResult);

    }

    /**
     * Load settings from settings file to settingsMap
     */
    public void load() {
        clearSettingsMap();
        try {
            if(SETTINGS_FILE == null){
                LOGGER.warn("File path is not set. " + SETTINGS_FILE);
                return;
            }
            if (!SETTINGS_FILE.exists()) {
                save();
                return;
            }
            fillSettingsMap();
        }catch (Exception e){
            LOGGER.warn(e + "Failed to load " + SETTINGS_FILE);
            clearSettingsMap();
        }
    }


    private void fillSettingsMap() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(SETTINGS_FILE);
        document.getDocumentElement().normalize();
        NodeList nodeList = document.getElementsByTagName(document.getDocumentElement().getChildNodes().item(1).getNodeName());
        for (int tmp = 0; tmp < nodeList.getLength(); tmp++) {
            Node node = nodeList.item(tmp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element el = (Element) node;
                settingsMap.put(el.getAttribute("key"), el.getAttribute("value"));
            }
        }

    }

    /**
     * @return application folder name
     */
    public static String getAppFolder() {
        return APP_FOLDER;
    }

    /**
     * @return path to application system folder
     */
    public static Path getAppPath() {
        return APP_PATH;
    }

    /**
     * @return settings xml file
     */
    public static File getSettingsFile() {
        return SETTINGS_FILE;
    }

    /**
     *
     * @return path of file of serialized words groups
     */
    public Path getVocabularyPath() {
        return VOCABULARY_FILE_PATH;
    }
}
