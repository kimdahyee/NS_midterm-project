import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;

public class MyKeyPair extends KeyHandler {
	private static final String keyAlgorithm = "RSA";

	private KeyPairGenerator keyGen;
	private KeyPair pair;

	private PrivateKey privateKey;
	private PublicKey publicKey;

	public static MyKeyPair getInstance(int keylength) {
		MyKeyPair rslt = new MyKeyPair();

		try {
			rslt.keyGen = KeyPairGenerator.getInstance(keyAlgorithm);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		rslt.keyGen.initialize(keylength);

		return rslt;
	}

	public void createKeys() {
		this.pair = this.keyGen.generateKeyPair();
		this.privateKey = pair.getPrivate();
		this.publicKey = pair.getPublic();
	}

	public PrivateKey getPrivateKey() {
		return this.privateKey;
	}

	public PublicKey getPublicKey() {
		return this.publicKey;
	}

}
