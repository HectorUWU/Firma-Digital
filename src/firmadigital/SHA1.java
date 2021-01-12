/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firmadigital;

import java.io.*;
import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.security.*;
import java.util.*;

import java.math.BigInteger;
import java.security.MessageDigest;

public class SHA1 {

	public static void main(String[] argv){

		String value = "hola kevin";
                byte [] sha1out;
		String sha1 = "";
		
		// With the java libraries
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
	        digest.reset();
	        digest.update(value.getBytes("utf8"));
	        sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
		} catch (Exception e){
			e.printStackTrace();
		}
                sha1out = sha1.getBytes();
		System.out.println( "The sha1 of \""+ value + "\" is:");
		System.out.println( sha1 );
		System.out.println();
        }
}