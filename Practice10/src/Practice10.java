import java.util.Scanner;

public class Practice10 {

	public static void main(String[] args) {
		System.out.println("[Practice10] Digital Signature\n");

		Scanner scanner = new Scanner(System.in);

//      �߽���
		System.out.println("- Sender");
		System.out.print("Send data: ");
		
//		data ����
//		13��: �ؽ� �Լ��� �̿��Ͽ� �н����带 �����϶�
		byte[] data = scanner.nextLine().getBytes();
		DigitSign.saveFile(DigitSign.datafname, data);

//		key ����
		DigitSign.createAndSaveKeys();

//		�߽����� private key�� sign
		DigitSign.sign(DigitSign.datafname, DigitSign.sender_privatefname);

		DigitSign.signWithEnvelope(DigitSign.datafname, DigitSign.sigfname, DigitSign.sender_publicfname,
				DigitSign.receiver_publicfname, DigitSign.sender_secretfname);

		boolean envTrue = DigitSign.verifyEnvelope(DigitSign.envelope_datafname, DigitSign.envelope_sigfname,
				DigitSign.envelope_publicfname, DigitSign.receiver_privatefname, DigitSign.envelopefname);
		System.out.println("verifyEnvelope ���: " + envTrue);

//      ������
		System.out.println("\n- Receiver");
		
//      �߽����� public key�� verify
		boolean sigTrue = DigitSign.verify(DigitSign.datafname, DigitSign.sigfname, DigitSign.sender_publicfname);

//		���� ����
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