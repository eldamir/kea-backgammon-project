package internationalization;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @version 0.1
 * Created by Ruben Nielsen on 5/1/14.
 *
 * A helper for retrieving text from the internationalization library.
 * As this class is abstract, you cannot instantiate it. Instead, simply
 * use the static methods available.
 */
public abstract class LanguageResource
{
    private static Locale locale = new Locale("da", "DK");
    private static ResourceBundle bundle = ResourceBundle.getBundle(
        "properties.LanguageBundle", LanguageResource.locale
    );


    /**
     * Looks in the dictionary and retrieves the text with the given id.
     *
     * @param id The ID of the text that you wish to retrieve.
     * @return The text you retrieved, as a String.
     */
    public static String getText(String id)
    {
        return LanguageResource.bundle.getString(id);
    }

    /**
     * Sets the locale - thereby the language - to the given locale.
     * Due to localization differences of language, e.g. American english
     * and British english, but country and language is required.
     *
     * @param language Is the language that you want.
     * @param country Is the country of the desired language.
     */
    public static void setLocale(String language, String country)
    {
        LanguageResource.locale = new Locale(language, country);
        LanguageResource.bundle = ResourceBundle.getBundle(
            "properties.LanguageBundle", LanguageResource.locale
        );
    }

    public static void main(String[] args)
    {
        System.out.println(LanguageResource.getText("test"));
        LanguageResource.setLocale("en","US");
        System.out.println(LanguageResource.getText("test"));
    }
}
