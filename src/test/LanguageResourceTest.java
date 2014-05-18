package test;


import model.internationalization.LanguageResource;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class LanguageResourceTest
{

	@Test
	public void test()
	{
        String translation;

        // Test danish
        LanguageResource.setLocale("da", "DK");
        translation = LanguageResource.getText("test");
        assertEquals("Så er der fest!", translation);

        // Test english
        LanguageResource.setLocale("en", "US");
        translation = LanguageResource.getText("test");
        assertEquals("Now, there is a party", translation);
	}

}
