import java.util.*;

public class Main {

	public static void main(String[] args) {
		MenuAction action = new  MenuAction();
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("\n======== ȸ�� ���� ���� ========");
			System.out.println("1. �۾���(ȸ������)");
			System.out.println("2. �ۺ���");
			System.out.println("3. ȸ������");
			System.out.println("4. ȸ�����(����������)");
			System.out.println("5. �ۻ���");
			System.out.println("6. �ۼ���");
			System.out.print("����(1~4, -1 ����)->");
			
			String menu = sc.next();
			if (menu.equals("-1")) {
				break;
			}
			
			switch (menu) {
			case "1": action.guestBookAdd(); break;
			case "2": action.guestBookLists(); break;
			case "3": action.memberAdd(); break;
			case "4": action.memberLists(); break;
			case "5": action.guestBookDelete(); break;
			case "6": action.guestBookModify(); break;
			}
			
		}while(true);
	}
}
