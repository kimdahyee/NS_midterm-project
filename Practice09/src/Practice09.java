import java.util.Scanner;

// 22번: 변수들의 영역 범위를 최소화하라
// 37번: 부분 영역의 식별자들을 섀도잉하거나 차폐하지 말라
// 38번: 하나의 선언문에 두 개 이상의 변수를 선언하지 말라
// 62번: 가독성 있고 일관된 주석을 사용하라
// 64번: 논리적 완벽을 추구하라
public class Practice09 {

   static final String sigFilename = "signing.txt";
   static final String privatefname = "privateKey.txt";
   static final String publicfname = "publicKey.txt";

   static final String datafname = "data.txt";

   public static void main(String[] args) {
      System.out.println("[Practice09] Digital Signature\n");

      Scanner scanner = new Scanner(System.in);
      
//    발신자
      System.out.println("- Sender");
      System.out.print("Send data: ");
      
//    데이터 저장
//    13번: 해시 함수를 이용하여 패스워드를 저장하라
      byte[] data = scanner.nextLine().getBytes();
      DigitSign.saveFile(datafname, data);

//    key 생성
      DigitSign.createAndSaveKeys();

//    발신자의 private key로 전자 서명 진행
      DigitSign.sign(datafname, privatefname);

//    수신자
      System.out.println("\n- Receiver");
      
//    발신자의 public key로 검증
      boolean isTrue = DigitSign.verify(datafname, sigFilename, publicfname);

//    위조 여부
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