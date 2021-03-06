import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

// 22번: 변수들의 영역 범위를 최소화 
// 37번: 부분 영역의 식별자들을 섀도잉하거나 차폐하지 말라
// 38번: 하나의 선언문에 하나의 변수 선언
// 62번: 가독성있고 일관된 주석 사용
// 33번: 일반적인 예외 타입 보다는 사용자-정의 예외를 사용하라 (ERR08-J : NullPointerException과 그 상위 예외들을 포착하지 말라)

public class DigitSign {
   private static final String signAlgorithm = "SHA1withRSA";

   static final String sigFilename = "signing.txt";
   static final String privatefname = "privateKey.txt";
   static final String publicfname = "publicKey.txt";
   static MyKeyPair myKeyPair;

   static void createAndSaveKeys() {
//    4번: 보안에 민감한 메서드들이 검증된 매개변수를 가지고 호출되도록 보장하라 
      try {
         myKeyPair = MyKeyPair.getInstance(1024);
         myKeyPair.createKeys();
      } catch (NoSuchProviderException e) {
         e.printStackTrace();
      } catch (NoSuchAlgorithmException e) {
         e.printStackTrace();
      }
      myKeyPair.savePublicKey(myKeyPair.getPublicKey(), publicfname);
      myKeyPair.savePrivateKey(myKeyPair.getPrivateKey(), privatefname);
   }

   static void sign(String dataFilename, String keyFilename) {
      try {
         byte[] data = readFile(dataFilename); 
         PrivateKey privateKey = myKeyPair.restorePrivateKey(keyFilename);
         Signature sig = Signature.getInstance(signAlgorithm);

         sig.initSign(privateKey);
         sig.update(data);

         byte[] signature = sig.sign();
         saveFile(sigFilename, signature);

      } catch (InvalidKeyException e) {
         e.printStackTrace();
      } catch (SignatureException e) {
         e.printStackTrace();
      } catch (NoSuchAlgorithmException e) {
         e.printStackTrace();
      }
   }
//   26번: 메서드의 결과값에 대해 항상 피드백을 제공하라
   static boolean verify(String dataFilename, String sigFilename, String keyFilename) {

      boolean rslt = false;

      try {
         byte[] data = readFile(dataFilename);
         PublicKey publicKey = myKeyPair.restorePublicKey(keyFilename);
         Signature sig = Signature.getInstance(signAlgorithm);

         sig.initVerify(publicKey);
         sig.update(data);

         rslt = sig.verify(readFile(sigFilename));

      } catch (InvalidKeyException e) {
         e.printStackTrace();
      } catch (SignatureException e) {
         e.printStackTrace();
      } catch (NoSuchAlgorithmException e) {
         e.printStackTrace();
      }

      return rslt;
   }

   public static byte[] readFile(String fname) {
      byte[] data = new byte[128];

      try {
         FileInputStream fis = new FileInputStream(fname);
         fis.read(data);
         fis.close();
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }

      return data;
   }

	public static void saveFile(String fname, byte[] data) {
		if (data == null) {
			return;
		}

		FileOutputStream fos = null;

		try {
			fos = new FileOutputStream(fname);
			fos.write(data);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}