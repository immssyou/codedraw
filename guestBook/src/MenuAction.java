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

	// 1.�۾���(ȸ������)
	public void guestBookAdd() {
		do {
			System.out.println("ID �Է� : ");
			String id = sc.nextLine();

			System.out.println("PW �Է� : ");
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
					System.out.println("ȸ���� �ƴմϴ�.");
					continue;
				} else {
					System.out.println("�۳���??");
					String content = sc.nextLine();

					gdto.setId(mdto.getId());
					gdto.setContent(content);

					int result = gdao.add(gdto);
					System.out.printf("%d���� ���� ���ԵǾ����ϴ�.", result);
					System.out.println("������ �Էµ�");

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

	// 2.�ۺ���
	public void guestBookLists() {
		try {
			mdao.connect();
			gdao.connect();

			System.out.printf("�۰Ǽ�  : %d��", gdao.count());
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

	// 3.ȸ������
	public void memberAdd() {
		do{
			System.out.println("---------ȸ������--------");
			System.out.println("ID �Է� : ");
			String id = sc.nextLine();
			
			if (id.equals("-1")) {
				break;
			}
			
			System.out.println("PW �Է� : ");
			String pwd = sc.nextLine();
			
			try {
				mdao.connect();
				MemberDTO mdto = new MemberDTO();
				mdto.setId(id);
				mdto.setPwd(pwd);
				
				if (mdao.memberchk(mdto) == 1) {
					System.out.println("�ߺ��� ID �Դϴ�. �ٽ� �Է� ���ּ���");
					continue;
				} else {
					
					int result = mdao.add(mdto); 
					System.out.printf("%d���� ���� ���ԵǾ����ϴ�.", result);
					System.out.println("������ �Էµ�");
					
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

	// 4.ȸ�����
	public void memberLists() {

	}

	// 5 �ۻ��� //���Ÿ� �Ǿߵ� // ��� �����Ұǰ�
	public void guestBookDelete() {
	
	}

	// 6 �ۼ���
	public void guestBookModify() {
		
	}

}
