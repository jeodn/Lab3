package org.translation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main class for this program.
 * Complete the code according to the "to do" notes.<br/>
 * The system will:<br/>
 * - prompt the user to pick a country name from a list<br/>
 * - prompt the user to pick the language they want it translated to from a list<br/>
 * - output the translation<br/>
 * - at any time, the user can type quit to quit the program<br/>
 */
public class Main {

    public static final String QUIT = "quit";

    /**
     * This is the main entry point of our Translation System!<br/>
     * A class implementing the Translator interface is created and passed into a call to runProgram.
     * @param args not used by the program
     */
    public static void main(String[] args) {
        Translator translator = new JSONTranslator(null);
        // Translator translator = new InLabByHandTranslator();
        runProgram(translator);
    }

    /**
     * This is the method which we will use to test your overall program, since
     * it allows us to pass in whatever translator object that we want!
     * See the class Javadoc for a summary of what the program will do.
     * @param translator the Translator implementation to use in the program
     */
    public static void runProgram(Translator translator) {
        CountryCodeConverter conv = new CountryCodeConverter();
        LanguageCodeConverter lanconv = new LanguageCodeConverter();

        while (true) {
            String country = promptForCountry(translator, conv);
            if (QUIT.equals(country)) {
                break;
            }
            country = conv.fromCountryCode(country);
            String language = promptForLanguage(translator, country, lanconv);
            if (QUIT.equals(language)) {
                break;
            }
            String code = lanconv.fromLanguageCode(language);

            System.out.println(country + " in " + language + " is " + translator.translate(country, code));
            System.out.println("Press enter to continue or quit to exit.");
            Scanner s = new Scanner(System.in);
            String textTyped = s.nextLine();

            if (QUIT.equals(textTyped)) {
                break;
            }
        }
    }

    // Note: CheckStyle is configured so that we don't need javadoc for private methods
    private static String promptForCountry(Translator translator, CountryCodeConverter conv) {
        List<String> countries = translator.getCountries();
        List<String> convertedCountries = new ArrayList<>();

        for (String country : countries) {
            String converted = conv.fromCountryCode(country);
            convertedCountries.add(converted);
            System.out.println(converted);
        }
        convertedCountries.sort(String::compareToIgnoreCase);
        Scanner s = new Scanner(System.in);
        return s.nextLine();

    }

    // Note: CheckStyle is configured so that we don't need javadoc for private methods
    private static String promptForLanguage(Translator translator, String country, LanguageCodeConverter lanconv) {

        List<String> languages = translator.getCountryLanguages(country);
        List<String> convertedLanguages = new ArrayList<>();
        for (String language : languages) {
            String converted = lanconv.fromLanguageCode(language);
            convertedLanguages.add(converted);
            System.out.println(converted);
        }

        convertedLanguages.sort(String::compareToIgnoreCase);

        System.out.println("select a language from above:");

        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }
}
