import java.util.Scanner;

public class Practice10 {

	public static void main(String[] args) {
		System.out.println("[Practice10] Digital Signature\n");

		Scanner scanner = new Scanner(System.in);

//      발신자
		System.out.println("- Sender");
		System.out.print("Send data: ");
		
//		data 저장
//		13번: 해시 함수를 이용하여 패스워드를 저장하라
		byte[] data = scanner.nextLine().getBytes();
		DigitSign.saveFile(DigitSign.datafname, data);

//		key 생성
		DigitSign.createAndSaveKeys();

//		발신자의 private key로 sign
		DigitSign.sign(DigitSign.datafname, DigitSign.sender_privatefname);

		DigitSign.signWithEnvelope(DigitSign.datafname, DigitSign.sigfname, DigitSign.sender_publicfname,
				DigitSign.receiver_publicfname, DigitSign.sender_secretfname);

		boolean envTrue = DigitSign.verifyEnvelope(DigitSign.envelope_datafname, DigitSign.envelope_sigfname,
				DigitSign.envelope_publicfname, DigitSign.receiver_privatefname, DigitSign.envelopefname);
		System.out.println("verifyEnvelope 결과: " + envTrue);

//      수신자
		System.out.println("\n- Receiver");
		
//      발신자의 public key로 verify
		boolean sigTrue = DigitSign.verify(DigitSign.datafname, DigitSign.sigfname, DigitSign.sender_publicfname);

//		위조 여부
		System.out.print("Is it from the real sender? ");
		System.out.println(sigTrue + "!");

		if (envTrue) {
			System.out.println("\n[Received Data]");
			printData(DigitSign.datafname);
		}
		
		scanner.close();
	}

	public static void printData(String fname) {
		byte[] data = DigitSign.readFile(fname);
		System.out.println(new String(data));
	}

}