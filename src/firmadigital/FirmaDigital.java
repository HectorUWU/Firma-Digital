/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firmadigital;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.nio.charset.StandardCharsets;
/**
 *
 * @author hector
 */
public class FirmaDigital {
    
    public byte [] firmar(String message, PrivateKey clavePrivada){
        RSA rsa = new RSA();
        SHA1 sha1 = new SHA1();
        byte[] rsaIn, firma, messageByte;
        rsaIn = sha1.getSHA1(message);
        firma = rsa.cifrarLLavePrivada(rsaIn, clavePrivada);
        messageByte = message.getBytes();
        byte [] out = new byte[messageByte.length + firma.length];
        System.arraycopy(messageByte, 0, out, 0, messageByte.length);
        System.arraycopy(firma, 0, out, messageByte.length , firma.length);
        return out;   
    }
    
    public Boolean verificar(byte [] message, PublicKey clavePublica) throws UnsupportedEncodingException{
        byte[] digest;
        int j = 0;
        byte[] mess = new byte[message.length-128];
        byte[] firma = new byte[128];
        System.arraycopy(message, 0, mess, 0, message.length-128);
        String messStr =  new String(mess, StandardCharsets.UTF_8);
        System.out.println(messStr);
        for(int i=(message.length -128); i<message.length; i++){
            firma[j]= message[i];
            j++;
        }
        RSA rsa = new RSA();
        SHA1 sha1 = new SHA1();
        firma = rsa.decifrarLLavePublica(firma, clavePublica);
        String firmaStr = new String(firma, StandardCharsets.UTF_8);
        System.out.println(firmaStr);
        digest = sha1.getSHA1(messStr);
        String digestStr = new String(digest, StandardCharsets.UTF_8);
        System.out.println(digestStr);
        return firmaStr.equals(digestStr);
    }

    
}
