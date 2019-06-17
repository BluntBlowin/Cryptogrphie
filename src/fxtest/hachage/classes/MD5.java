package fxtest.hachage.classes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import fxtest.hachage.classes.ConversionHexa;

class MD5 implements IHachage{
	
	private static final MD5 instance = new MD5();
	
	MD5(){
		super();
	}

	@Override
	public String hacher(String message) {
		byte[] m = message.getBytes();
		byte[] hash = null;
		try{
			hash = MessageDigest.getInstance("MD5").digest(m);
		}
		catch (NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		return ConversionHexa.getInstance().convertToHex(hash);
	}

	@Override
	public TypeHachage getAlgorithme() {
		return TypeHachage.MD5;
	}
	
	public static MD5 getInstance() {
		return instance;
	}
	
	
}
