package test.generalweb.login.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import org.testng.annotations.DataProvider;

import test.framework.dataproviders.AbstractDataProvider;


public class RandomDataDataProviderLoginTestSuite extends AbstractDataProvider{

	public String userName;
	public String userPassword;
	public String characters = "123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"; 
	
	public int maxRndValueLength = 10;
	public static int repeatCount = 5;
	
	public RandomDataDataProviderLoginTestSuite() {
	}

	public RandomDataDataProviderLoginTestSuite(RandomDataDataProviderLoginTestSuite credentials) {
		this.userName = credentials.userName;
		this.userPassword = credentials.userPassword;
	}
	
	/**
	 * Data provider for test methods 
	 * return i time random generated credentials
	 * @return list
	 */
	@DataProvider(name = "randomCredentials")
	public static Iterator<Object[]> generateRandomCredentials() {
		List<RandomDataDataProviderLoginTestSuite> dataGroup = new RandomDataDataProviderLoginTestSuite().generateRandomCredentialsList(repeatCount);
		List<Object[]> list = new ArrayList<Object[]>();
			for (RandomDataDataProviderLoginTestSuite entry : dataGroup) {
				list.add(new Object[]{entry});
				}
			return list.iterator();
		}
			
	/**
	* Method return list with generated random User name and user password
	* @param count
	* @return
	*/
	private List<RandomDataDataProviderLoginTestSuite> generateRandomCredentialsList(int count) {
	Random rnd = new Random();
	List<RandomDataDataProviderLoginTestSuite> list = new ArrayList<RandomDataDataProviderLoginTestSuite>();
	for (int i = 0; i < count; i++) {
		RandomDataDataProviderLoginTestSuite group = new RandomDataDataProviderLoginTestSuite()
			.setUser(generateString(rnd, characters, getRandomLength(maxRndValueLength)))
			.setPassword(generateString(rnd, characters, getRandomLength(maxRndValueLength)));
			list.add(group);
			}
		return list;
	}
			
	/**
	* Method return generated random value
	* @param rnd
	* @param characters set of characters with are use for generate random string
	* @param length  random max length for generated value (getRandomLength())
	* @return  random String value
	*/
	public static String generateString(Random rnd, String characters, int length)
		{
			char[] text = new char[length];
			for (int i = 0; i < length; i++)
			   {
			   text[i] = characters.charAt(rnd.nextInt(characters.length()));
			   }
			return new String(text);
		}
			
	/**
	* Method used for everytime generate random length value
	* @param maxRndValuelength length for generated random value
	* @return random length between 0 and max  
	*/
	public static Integer getRandomLength(int maxRndValuelength)
	{
		Random rnd = new Random();
		Integer length = rnd.nextInt(maxRndValuelength);
	return length;			    
	}
			
	public String getUser() {
		return userName;
	}
		
	public String getPassword() {
		return userPassword;
	}
		
	public RandomDataDataProviderLoginTestSuite setPassword(String userPassword) {
		this.userPassword = userPassword;
		return this;
	}
			
	public RandomDataDataProviderLoginTestSuite setUser(String userName) {
		this.userName = userName;
		return this;
	}
			
	/**
	* Add possibility to see in method description clear values description
	*/
	@Override
	public String toString() {
		return "Credentials [" + userName + " : " + userPassword + "]";
	}
	
}
