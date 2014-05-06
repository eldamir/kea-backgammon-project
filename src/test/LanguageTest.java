package test;

import static org.junit.Assert.assertEquals;
import model.internationalization.LanguageResource;

import org.junit.Test;

public class LanguageTest 
{

	@Test
	public void getTest() 
	{
		String test1 = "SÃ¥ er der fest!";
		
		String value = LanguageResource.getText("test");
		
		assertEquals(test1,value);
	}
	
	@Test
    public void setLocaleLanguage()
	{
		String test2 = "Now, there is a party";
		
		LanguageResource.setLocale("en","US");
		
		String val = LanguageResource.getText("test");
		
		assertEquals(test2,val);
	}

}
