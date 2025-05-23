package org.translation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * This class provides the service of converting language codes to their names.
 */
public class LanguageCodeConverter {

    // TODO Task: pick appropriate instance variables to store the data necessary for this class
    public Map<String, String> languagesCodes = new HashMap<>();
    /**
     * Default constructor which will load the language codes from "language-codes.txt"
     * in the resources folder.
     */
    public LanguageCodeConverter() {
        this("language-codes.txt");
    }

    /**
     * Overloaded constructor which allows us to specify the filename to load the language code data from.
     * @param filename the name of the file in the resources folder to load the data from
     * @throws RuntimeException if the resource file can't be loaded properly
     */
    public LanguageCodeConverter(String filename) {

        try {
            List<String> lines = Files.readAllLines(Paths.get(getClass()
                    .getClassLoader().getResource(filename).toURI()));

            // TODO Task: use lines to populate the instance variable
            //           tip: you might find it convenient to create an iterator using lines.iterator()

            Iterator<String> iterator = lines.iterator(); // quickly populate with Iterator
            if(iterator.hasNext()){ // skip first line
                iterator.next();
            }
            while(iterator.hasNext()){ // populate Hashmap {language=code, .....}
                String line = iterator.next().trim();
                String[] parts = line.split("\\s+");
                if(parts.length >= 2){
                    String language = parts[parts.length - 1];
                    String code = String.join(" ", java.util.Arrays
                            .copyOfRange(parts, 0, parts.length - 1));
                    this.languagesCodes.put(language, code); // now hashmap is populated
                }
            }

        }
        catch (IOException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Returns the name of the language for the given language code.
     * @param code the language code
     * @return the name of the language corresponding to the code
     */
    public String fromLanguageCode(String code) {
        // TODO Task: update this code to use your instance variable to return the correct value
        for (Map.Entry<String, String> entry : this.languagesCodes.entrySet()) { // use entryset to loop entire hashmap
            if (entry.getValue().equals(code)) {
                return entry.getKey();
            }
        }
        return "Provided country code does not exist";
    }

    /**
     * Returns the code of the language for the given language name.
     * @param language the name of the language
     * @return the 2-letter code of the language
     */
    public String fromLanguage(String language) {
        // TODO Task: update this code to use your instance variable to return the correct value
        for(Map.Entry<String, String> entry : this.languagesCodes.entrySet()){
            if(entry.getKey().equals(language)){
                return entry.getValue();
            }
        }
        return "Provided language does not exist";
    }

    /**
     * Returns how many languages are included in this code converter.
     * @return how many languages are included in this code converter.
     */
    public int getNumLanguages() {
        // TODO Task: update this code to use your instance variable to return the correct value
        int counter = 0;
        for(String languages: this.languagesCodes.keySet()){
            counter++;
        }
        return counter;

    }
}
