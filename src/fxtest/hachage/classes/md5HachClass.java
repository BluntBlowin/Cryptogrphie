/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxtest.hachage.classes;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 *
 * @author Blunt_Blowin
 */
public class md5HachClass {
    private static MD5 instance = new MD5();
	
	public md5HachClass(){
            instance=MD5.getInstance();
	}

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

    public TypeHachage getAlgorithme() {
        return TypeHachage.MD5;
    }
    public static MD5 getInstance() {
		return instance;
	}
    
}
