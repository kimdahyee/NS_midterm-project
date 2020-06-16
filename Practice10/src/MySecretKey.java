import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class MySecretKey extends KeyHandler {
	private static final String ALGO = "AES";
	private static final byte[] keyValue = { 'T', 'h', 'e', 'B', 'e', 's', 't', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y' };

	public Key generateKey() {
		return (new SecretKeySpec(keyValue, ALGO));
	}

//  33번: ERR08-J : NullPointerException과 그 상위 예외들을 포착하지 말라
	public byte[] encrypt(byte[] data, Key skey) {
		byte[] encVal = null;
		
		try {
			Cipher c = Cipher.getInstance(ALGO);
			c.init(Cipher.ENCRYPT_MODE, skey);
			encVal = c.doFinal(data);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return encVal;
	}

//  33번: ERR08-J : NullPointerException과 그 상위 예외들을 포착하지 말라	
	public byte[] decrypt(byte[] encryptedData, Key skey) {
		Cipher c = null;
		byte[] decVal = null;
		
		try {
			c = Cipher.getInstance(ALGO);
			c.init(Cipher.DECRYPT_MODE, skey);
			decVal = c.doFinal(encryptedData);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return decVal;
	}

}