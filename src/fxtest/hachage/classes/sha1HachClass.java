/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxtest.hachage.classes;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Blunt_Blowin
 */


public class sha1HachClass {
    private static SHA1 instance = new SHA1();
	
	public sha1HachClass(){
		instance=SHA1.getInstance();
	}

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
	
	public TypeHachage getAlgorithme() {
		return TypeHachage.SHA1;
	}


	public static SHA1 getInstance() {
		return instance;
	}
}
