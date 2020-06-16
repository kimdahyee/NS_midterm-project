import java.util.Scanner;

// 22��: �������� ���� ������ �ּ�ȭ 
// 37��: �κ� ������ �ĺ��ڵ��� �������ϰų� �������� ����
// 38��: �ϳ��� ���𹮿� �ϳ��� ���� ����
// 62��: �������ְ� �ϰ��� �ּ� ���

public class Practice09 {

   static final String sigFilename = "signing.txt";
   static final String privatefname = "privateKey.txt";
   static final String publicfname = "publicKey.txt";

   static final String datafname = "data.txt";

   public static void main(String[] args) {
      System.out.println("[Practice09] Digital Signature\n");

      Scanner scanner = new Scanner(System.in);
      
//    �߽���
      System.out.println("- Sender");
      System.out.print("Send data: ");
      
//    ������ ����
//    13��
      byte[] data = scanner.nextLine().getBytes();
      DigitSign.saveFile(datafname, data);

//    key ����
      DigitSign.createAndSaveKeys();

//    �߽����� private key�� ���� ���� ����
      DigitSign.sign(datafname, privatefname);

//    ������
      System.out.println("\n- Receiver");
      
//    �߽����� public key�� ����
      boolean isTrue = DigitSign.verify(datafname, sigFilename, publicfname);

//    ���� ����
      System.out.print("Is it from the real sender? ");
      System.out.println(isTrue + "!");

      if (isTrue) {
         System.out.println("\n[Received Data]");
         printData(datafname);
      }
      
      scanner.close();
   }

   public static void printData(String fname) {
      byte[] data = DigitSign.readFile(fname);
      System.out.println(new String(data));
   }
   
}