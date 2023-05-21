package ma.richebois.transport;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;
 
public class GeneratedKayPair {
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		
		KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance("RSA");
		KeyPair KeyPair = keyPairGenerator.generateKeyPair();
		byte[] pub = KeyPair.getPublic().getEncoded();
		byte[] pri = KeyPair.getPrivate().getEncoded();
		PemWriter pemWriter = new PemWriter(new OutputStreamWriter(new FileOutputStream("public.pem")));
		PemObject pemObject= new PemObject("PUBLUC KEY", pub);
		pemWriter.writeObject(pemObject);
		pemWriter.close();
		PemWriter pemWriter2 =  new PemWriter(new OutputStreamWriter(new FileOutputStream("private.pem")));
		PemObject pemObject2= new PemObject("PUBLUC KEY", pri);
		pemWriter2.writeObject(pemObject2);
		pemWriter2.close();
		
		
	}


}
