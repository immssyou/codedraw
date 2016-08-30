import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuAction {
	private MemberDAO mdao;
	private GuestBookDAO gdao;

	public MenuAction() {
		mdao = new MemberDAO();
		gdao = new GuestBookDAO();
	}

	Scanner sc = new Scanner(System.in);

	// 1.글쓰기(회원전용)
	public void guestBookAdd() {
		do {
			System.out.println("ID 입력 : ");
			String id = sc.nextLine();

			System.out.println("PW 입력 : ");
			String pwd = sc.nextLine();
			if (id.equals("-1")) {
				break;
			}

			try {
				mdao.connect();
				gdao.connect();
				MemberDTO mdto = new MemberDTO();
				GuestBookDTO gdto = new GuestBookDTO();
				mdto.setId(id);
				mdto.setPwd(pwd);

				if (mdao.memberchk(mdto) != 1) {
					System.out.println("회원이 아닙니다.");
					continue;
				} else {
					System.out.println("글내용??");
					String content = sc.nextLine();

					gdto.setId(mdto.getId());
					gdto.setContent(content);

					int result = gdao.add(gdto);
					System.out.printf("%d개의 행이 삽입되었습니다.", result);
					System.out.println("방명록이 입력됨");

				}

			} catch (Exception e) {
				System.out.println(e.toString());
			} finally {
				try {
					mdao.close();
					gdao.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} while (true);

	}

	// 2.글보기
	public void guestBookLists() {
		try {
			mdao.connect();
			gdao.connect();

			System.out.printf("글건수  : %d건", gdao.count());
			System.out.println("-----------------------------");
			List<GuestBookDTO> list = new ArrayList<GuestBookDTO>();
			list = gdao.view();
			for (GuestBookDTO gdto : list) {
				System.out.printf("%s %s %s %s %n", gdto.getGid(), gdto.getId(), gdto.getContent(), gdto.getWdate());

			}
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			try {
				mdao.close();
				gdao.close();
			} catch (Exception e) {
				System.out.println(e.toString());
			}

		}
	}

	// 3.회원가입
	public void memberAdd() {
		do{
			System.out.println("---------회원가입--------");
			System.out.println("ID 입력 : ");
			String id = sc.nextLine();
			
			if (id.equals("-1")) {
				break;
			}
			
			System.out.println("PW 입력 : ");
			String pwd = sc.nextLine();
			
			try {
				mdao.connect();
				MemberDTO mdto = new MemberDTO();
				mdto.setId(id);
				mdto.setPwd(pwd);
				
				if (mdao.memberchk(mdto) == 1) {
					System.out.println("중복된 ID 입니다. 다시 입력 해주세요");
					continue;
				} else {
					
					int result = mdao.add(mdto); 
					System.out.printf("%d개의 행이 삽입되었습니다.", result);
					System.out.println("방명록이 입력됨");
					
				}
				
			} catch (Exception e) {
				System.out.println(e.toString());
			} finally {
				try {
					mdao.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		} while(true);
	
	}

	// 4.회원명단
	public void memberLists() {

	}

	// 5 글삭제 //내거만 되야되 // 어떤거 삭제할건가
	public void guestBookDelete() {
	
	}

	// 6 글수정
	public void guestBookModify() {
		
	}

}
