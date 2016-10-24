package com.airplaneSoft.translateMeDude.winApp.settings.settingsModel;

import com.airplaneSoft.translateMeDude.winApp.AppUtils;
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

import static com.airplaneSoft.translateMeDude.winApp.settings.settingsModel.SettingsKeys.*;


public class SettingsImpl implements Settings {

    private static final String SETTINGS_FILE_NAME = "settings.xml";
    public static final String APP_FOLDER = "TranslateMe_Dude";
    private static final Path APP_PATH = Paths.get(System.getProperty("user.home"), APP_FOLDER);
    private static final File SETTINGS_FILE = APP_PATH.resolve(SETTINGS_FILE_NAME).toFile();
    private static final SettingsImpl instance = new SettingsImpl();
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
        set(URL, AppUtils.getStringProperty("settings.url"));
        set(SSOID, AppUtils.getStringProperty("settings.ssoid"));
        set(PASSWORD, AppUtils.getStringProperty("settings.password"));
        set(SHOW, AppUtils.getStringProperty("settings.show"));
        set(SHOW_TIMER, SettingsKeys.TimerValues.RANDOM);
        set(TIMER_VALUE, AppUtils.getStringProperty("settings.timer.value.min"));
    }

    public void save() {
        try {
            if(SETTINGS_FILE == null){
                System.out.println("File path is not set. " + SETTINGS_FILE);
                return;
            }

            File settingsDir = SETTINGS_FILE.getParentFile();
            if(!settingsDir.exists() && !settingsDir.mkdirs()) {
                System.out.println("Settings directory " + settingsDir + " not exist.");
                return;
            }

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            putSettingsToDocument(document);
            saveDocument(document);

            System.out.println("Settings saved");
        } catch (Exception e) {
            System.out.println(e + "Settings save failed.");
        }
    }

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

    public void load() {
        clearSettingsMap();
        try {
            if(SETTINGS_FILE == null){
                System.out.println("File path is not set. " + SETTINGS_FILE);
                return;
            }
            if (!SETTINGS_FILE.exists()) {
                save();
                return;
            }
            fillSettingsMap();
        }catch (Exception e){
            System.out.println(e + "Failed to load " + SETTINGS_FILE);
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

}
