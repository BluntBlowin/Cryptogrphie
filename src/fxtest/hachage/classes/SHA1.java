package fxtest.hachage.classes;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class SHA1 implements IHachage{
	
	private static final SHA1 instance = new SHA1();
	
	SHA1(){
		super();
	}

	@Override
	public String hacher(String message) {
		MessageDigest md=null;
		try {
			md = MessageDigest.getInstance( "SHA" );
			md.update(message.getBytes( "8859_1"));
		} 
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] digest = md.digest();
		ConversionHexa c =ConversionHexa.getInstance();
		return c.convertToHex(digest).toUpperCase();
	}
	
	@Override
	public TypeHachage getAlgorithme() {
		return TypeHachage.SHA1;
	}


	public static SHA1 getInstance() {
		return instance;
	}

}
