/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firmadigital;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 *
 * @author hector
 */
public class Principal {

    public static void main(String[] args) throws UnsupportedEncodingException {
        Security.addProvider(new BouncyCastleProvider());
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA", "BC");
            keyGen.initialize(1024);
            KeyPair clavesRSA = keyGen.generateKeyPair();
            PrivateKey clavePrivada = clavesRSA.getPrivate();
            PublicKey clavePublica = clavesRSA.getPublic();
            FirmaDigital fd = new FirmaDigital();
            byte [] prueba = fd.firmar("Chinga tu madre nidia", clavePrivada);
            if (fd.verificar(prueba, clavePublica)) {
                System.out.println("firma verifciada");
            } else {
                System.out.println("firma incorrecta");
            }
        } catch (NoSuchAlgorithmException | NoSuchProviderException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
