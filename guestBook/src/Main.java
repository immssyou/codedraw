import java.util.*;

public class Main {

	public static void main(String[] args) {
		MenuAction action = new  MenuAction();
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("\n======== 회원 전용 방명록 ========");
			System.out.println("1. 글쓰기(회원전용)");
			System.out.println("2. 글보기");
			System.out.println("3. 회원가입");
			System.out.println("4. 회원명단(관리자전용)");
			System.out.println("5. 글삭제");
			System.out.println("6. 글수정");
			System.out.print("선택(1~4, -1 종료)->");
			
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
