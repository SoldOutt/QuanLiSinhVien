package main;
import java.io.*;
import java.util.*;
public class Main {
	static private ArrayList<SinhVien> arrSV = new ArrayList<SinhVien>();
	static private ArrayList<MonHoc> arrMH = new ArrayList<MonHoc>();
	static private ArrayList<Diem> arrD = new ArrayList<Diem>();
	public static void main(String[] args) {
		File dataDiem = new File("./data/diem.txt");
		File dataSV = new File("./data/sinhvien_en.txt");
		File dataMH = new File("./data/monhoc_en.txt");
		FileReader frd = null;
		BufferedReader bufRd = null;
		try {
			//Lay du lieu sinh vien
			frd = new FileReader(dataSV);
			bufRd = new BufferedReader(frd);
			String line;
			while((line = bufRd.readLine())!=null) {
				if(line.charAt(0)=='#')continue;
				String[] str = line.split(";");
				SinhVien x = new SinhVien(str[0],str[1],str[2],str[3],str[4]);
				arrSV.add(x);
			}
			//lay du lieu mon hoc
			frd = new FileReader(dataMH);
			bufRd = new BufferedReader(frd);
			while((line = bufRd.readLine())!=null) {
				if(line.charAt(0)=='#')continue;
				String[] str = line.split(";");
				MonHoc x = new MonHoc(str[0],str[1],str[2]);
				arrMH.add(x);
			}
			//lay du lieu diem 
			frd = new FileReader(dataDiem);
			bufRd = new BufferedReader(frd);
			while((line = bufRd.readLine())!=null) {
				if(line.charAt(0)=='#')continue;
				String[] str = line.split(";");
				Diem x = new Diem(str[0],str[1],str[2]);
				arrD.add(x);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		Comparator<SinhVien> c = new Comparator<SinhVien>() {
			@Override
			public int compare(SinhVien o1, SinhVien o2) {
				if(o1.getTen().compareTo(o2.getTen())>0)return 1;
				if(o1.getTen().compareTo(o2.getTen())==0)return 0;
				else return -1;
			}
		};
		Comparator<MonHoc> c1 = new Comparator<MonHoc>() {
			@Override
			public int compare(MonHoc o1, MonHoc o2) {
				if(o1.getTenMonHoc().compareTo(o2.getTenMonHoc())>0)return 1;
				else return -1;
			}
		};
		Comparator<Diem> c2 = new Comparator<Diem>() {
			@Override
			public int compare(Diem o1, Diem o2) {
				if(o1.getMaSV().compareTo(o2.getMaSV())>0)return 1;
				else return -1;
			}
		};
		arrSV.sort(c);
		arrMH.sort(c1);
		arrD.sort(c2);
		menuGoc();
	}
	static void menuGoc() {
		int luachon;
		do {
			System.out.println("┌────────────────────────────────┐");
			System.out.println("│             MENU               │");
			System.out.println("├────────────────────────────────┤");
			System.out.println("│ 1. Cap nhat danh sach          │");
			System.out.println("│ 2. Hien thi bang diem          │");
			System.out.println("│ 3. Tim kiem                    │");
			System.out.println("│ 0. Thoat                       │");
			System.out.println("└────────────────────────────────┘");
			Scanner in = new Scanner(System.in);
			luachon = in.nextInt();
			switch (luachon) {
			case 1:
				menu1();
				break;
			case 2:
				menu2();
				break;
			case 3:
				menu3();
				break;
			case 0:
				return;
			default:
				System.out.println("Nhap sai!!!");
				break;
			}
		}while(luachon!=0);
	}
	private static void menu2() {
		int luachon;
		do {
		System.out.println("┌──────────────────────────────────┐");
		System.out.println("│   BANG DIEM                      │");
		System.out.println("├──────────────────────────────────┤");
		System.out.println("│ 1. Bang diem theo ds sinh vien   │");
		System.out.println("│ 2. Bang diem theo ds mon hoc     │");
		System.out.println("│ 0. Tro ve menu truoc             │");
		System.out.println("└──────────────────────────────────┘");
		Scanner in = new Scanner(System.in);
		luachon = in.nextInt();
		switch (luachon) {
		case 1:
			danhSachBangDiemSV();
			break;
		case 2:
			bangTheoMonHoc();
			break;
		case 0:
			//menuGoc();
			return;
		default:
			System.out.println("Nhap sai!!!");
			break;
		}
	}while(luachon!=0);
		
	}
	private static void menu3() {
		int luachon;
		do {
			System.out.println("┌──────────────────────────────────┐");
			System.out.println("│   TIM KIEM                       │");
			System.out.println("├──────────────────────────────────┤");
			System.out.println("│ 1. Tim kiem theo ma sinh vien    │");
			System.out.println("│ 2. Tim kiem theo ten sinh vien   │");
			System.out.println("│ 3. Tim kiem ma mon hoc           │");
			System.out.println("│ 0. Tro ve menu truoc             │");
			System.out.println("└──────────────────────────────────┘");
			Scanner in = new Scanner(System.in);
			luachon = in.nextInt();
			switch (luachon) {
			case 1:
				timMaSV();
				break;
			case 2:
				timTenSV();
				break;
			case 3:
				timMaMH();
				break;
			case 0:
				//menuGoc();
				return;
			default:
				System.out.println("Nhap sai!!!");
				break;
			}
		}while(luachon!=0);
	}
	private static void menu1() {
		int luachon;
		do {
			System.out.println("┌──────────────────────────────────┐");
			System.out.println("│   Cap nhat danh sach             │");
			System.out.println("├──────────────────────────────────┤");
			System.out.println("│ 1. Cap nhat danh sach sinh vien  │");
			System.out.println("│ 2. Cap nhat danh sach mon hoc    │");
			System.out.println("│ 3. Cap nhat bang diem            │");
			System.out.println("│ 0. Tro ve menu truoc             │");
			System.out.println("└──────────────────────────────────┘");
			Scanner in = new Scanner(System.in);
			luachon = in.nextInt();
			switch (luachon) {
			case 1:
				menu11();
				break;
			case 2:
				menu12();
				break;
			case 3:
				menu13();
				break;
			case 0:
				return;
			default:
				System.out.println("Nhap sai!!!");
				break;
			}
		}while(luachon!=0);
		
	}
	private static void menu13() {
		int luachon;
		do {
			System.out.println("┌──────────────────────────────────┐");
			System.out.println("│   BANG DIEM                      │");
			System.out.println("├──────────────────────────────────┤");
			System.out.println("│ 1. Them diem vao ds              │");
			System.out.println("│ 2. Sua diem trong ds             │");
			System.out.println("│ 3. Xoa diem trong ds             │");
			System.out.println("│ 0. Tro ve menu truoc             │");
			System.out.println("└──────────────────────────────────┘");
			Scanner in = new Scanner(System.in);
			luachon = in.nextInt();
			switch (luachon) {
			case 1:
				themDiem();
				break;
			case 2:
				suaDiem();
				break;
			case 3:
				xoaDiem();
				break;
			case 0:
				//menu1();
				return;
			default:
				System.out.println("Nhap sai!!!");
				break;
			}
		}while(luachon!=0);
	}
	private static void menu12() {
		int luachon;
		do {
			System.out.println("┌──────────────────────────────────┐");
			System.out.println("│         DANH SACH MON HOC        │");
			System.out.println("├──────────────────────────────────┤");
			System.out.println("│ 1. Them mon hoc                  │");
			System.out.println("│ 2. Sua mon hoc                   │");
			System.out.println("│ 3. Xoa mon hoc                   │");
			System.out.println("│ 4. Hien thi danh sach            │");
			System.out.println("│ 0. Tro ve menu truoc             │");
			System.out.println("└──────────────────────────────────┘");
			Scanner in = new Scanner(System.in);
			luachon = in.nextInt();
			switch (luachon) {
			case 1:
				themMH();
				break;
			case 2:
				suaMH();
				break;
			case 3:
				xoaMH();
				break;
			case 4:
				danhSachMH();
				break;
			case 0:
				//menu1();
				return;
			default:
				System.out.println("Nhap sai!!!");
				break;
			}
		}while(luachon!=0);
	}
	private static void menu11() {
		int luachon;
		do {
			System.out.println("┌──────────────────────────────────┐");
			System.out.println("│       DANH SACH SINH VIEN        │");
			System.out.println("├──────────────────────────────────┤");
			System.out.println("│ 1. Them sinh vien                │");
			System.out.println("│ 2. Sua thong tin sv              │");
			System.out.println("│ 3. Xoa sinh vien                 │");
			System.out.println("│ 4. Hien thi danh sach            │");
			System.out.println("│ 0. Tro ve menu truoc             │");
			System.out.println("└──────────────────────────────────┘");
			Scanner in = new Scanner(System.in);
			luachon = in.nextInt();
			switch (luachon) {
			case 1:
				themSV();
				break;
			case 2:
				suaSV();
				break;
			case 3:
				xoaSV();
				break;
			case 4:
				danhSachSV();
				break;
			case 0:
//				menu1();
				return;
			default:
				System.out.println("Nhap sai!!!");
				break;
			}
		}while(luachon!=0);
	}
		
	static void themSV() {
		System.out.println("Nhap thong tin sinh vien theo dinh dang: ");
		System.out.println("[MaSV];[ho dem];[ten];[ngay sinh(dd/MM/yyyy)];[gioi tinh(Nam/Nu)]");
		System.out.println("Vi du:SV0001;Nguyen Van; Nam;14/06/1996;Nam");
		System.out.println("Nhap exit de quay lai");
		String s;
		Scanner in = new Scanner(System.in);
		try {
			System.out.println("Nhap thong tin sinh vien : ");
			s= in.nextLine();
			if(s.compareTo("exit")==0) {
				return ;
			}
			String[] str = s.split(";");
			for(SinhVien sv: arrSV) {
				if(sv.getMaSV().compareTo(str[0])==0) {
					System.out.println("Da co sinh vien nay roi");
					return;
				}
			}
			SinhVien x = new SinhVien(str[0],str[1],str[2],str[3],str[4]);
			arrSV.add(x);
			System.out.println("Them thanh cong!!! So sinh vien hien tai : " + arrSV.size());
		} catch (Exception e) {
			System.out.println("Nhap sai dinh dang");
		}
	}
	static void suaSV() {
		Scanner in = new Scanner(System.in);
		System.out.println("Nhap ma SV muon sua");
		String masv = in.nextLine();
		for (SinhVien sv : arrSV) {
			if(sv.getMaSV().compareTo(masv)==0) {
				System.out.println("Tim thay sinh vien ");
				sv.xuat();
				System.out.println("Nhap lai thong tin sinh vien theo dinh dang: ");
				System.out.println("[ho dem];[ten];[ngay sinh(dd/MM/yyyy)];[gioi tinh(Nam/Nu)]");
				System.out.println("Neu khong muon sua muc nao thi bo trong muc do");
				System.out.println("Vi du : Nguyen Van A;;Nam");
				System.out.println("Nhap exit de tro ve");
				try {
					String s;
					s=in.nextLine();
					if(s.compareTo("exit")==0) {
						return ;
					}
					String[] arrS = s.split(";");
					sv.suaSV(arrS[0],arrS[1],arrS[2],arrS[3]);
					System.out.println("Sua thanh cong");
					sv.xuat();
				} catch (Exception e) {
					System.out.println("Nhap sai dinh dang");
				}
				return;
			}
		}
		System.out.println("Khong tim thay sinh vien");
	}
	static void xoaSV() {
		Scanner in = new Scanner(System.in);
		System.out.println("Nhap ma SV muon xoa");
		String masv = in.nextLine();
		for (SinhVien sv : arrSV) {
			if(sv.getMaSV().compareTo(masv)==0) {
				for(Diem d: arrD) {
					if(d.getMaSV().compareTo(masv)==0){
						System.out.println("Sinh vien nay da co diem , khong the xoa !!!");
						return;
					}
				}
				arrSV.remove(sv);
				System.out.println("Xoa thanh cong");
				return;
			}
		}
		System.out.println("Khong tim thay sinh vien");
	}
	static void danhSachSV() {
		int num = 20;// so sinh vien 1 trang;
		int sotrang;
		if(arrSV.size()%num==0) {
			sotrang = arrSV.size()/num;
		}
		else {
			sotrang = 1+(arrSV.size()/num);
		}
		int trang = 1;
		pageSV(num,trang,sotrang);
	}
	private static void pageSV(int num, int trang,int sotrang) {
		trang = trang>0?trang:1;
		trang = trang<=sotrang?trang:sotrang;
		int luachon;
		do {
			System.out.println("--------DANH SACH SINH VIEN-----------");
			int start = (trang-1)*20;
			int end =trang*20<arrSV.size()?trang*20:arrSV.size();
			System.out.println("┌─────────┬───────────────────────┬───────────┬────────────┬─────────┐");
			System.out.println("│   Ma    │  Ho dem               │  Ten      │ ngay sinh  │Gioi tinh│");
			System.out.println("├─────────┼───────────────────────┼───────────┼────────────┼─────────┤");
			for(int i= start;i<end;i++) {
				arrSV.get(i).xuat();
			}
			System.out.println("└─────────┴───────────────────────┴───────────┴────────────┴─────────┘");
			System.out.println("                Trang thu : " + trang +"/" +sotrang);
			System.out.println("1.Xem trang ke");
			System.out.println("2.Xem trang truoc");
			System.out.println("3.Xem trang cuoi");
			System.out.println("4.Xem trang dau tien");
			System.out.println("5.Xem trang cu the");
			System.out.println("0.Ve menu truoc");
			Scanner in = new Scanner(System.in);
			luachon = in.nextInt();
			switch (luachon) {
			case 1:
				pageSV(num,trang+1,sotrang);
				break;
			case 2:
				pageSV(num,trang-1,sotrang);
				break;
			case 3:
				pageSV(num,sotrang,sotrang);
				break;
			case 4:
				pageSV(num,1,sotrang);
				break;
			case 5:
				System.out.println("Nhap so trang muon xem : ");
				int p = in.nextInt();
				if(p<1||p>sotrang) {
					System.out.println("So trang nhap khong hop le !!!");
					break;
				}
				pageSV(num,p,sotrang);
				break;
			case 0:
//				menu11();
				return;
			default:
				System.out.println("Lua chon khong hop le");
				break;
			}
		}while(luachon<0||luachon>5);
	}
	static void themMH() {
		System.out.println("Nhap thong tin mon hoc theo dinh dang: ");
		System.out.println("[MaMH];[TenMH];[Heso]");
		System.out.println("Vi du:MH001;MonHoc 1;2.5");
		System.out.println("Nhap exit de quay lai");
		String s;
		Scanner in = new Scanner(System.in);
		try {
			System.out.println("Nhap thong tin mon hoc : ");
			s= in.nextLine();
			if(s.compareTo("exit")==0) {
				return ;
			}
			String[] str = s.split(";");
			for(MonHoc mh: arrMH) {
				if(mh.getMaMonHoc().compareTo(str[0])==0) {
					System.out.println("Da co mon hoc nay roi");
					return;
				}
			}
			MonHoc x = new MonHoc(str[0],str[1],str[2]);
			arrMH.add(x);
			System.out.println("Them thanh cong");
		} catch (Exception e) {
			System.out.println("Nhap sai dinh dang");
		}
	}
	static void suaMH() {
		Scanner in = new Scanner(System.in);
		System.out.println("Nhap ma MH muon sua");
		String mamh = in.nextLine();
		for (MonHoc mh : arrMH) {
			if(mh.getMaMonHoc().compareTo(mamh)==0) {
				System.out.println("Tim thay mon hoc ");
				mh.xuat();
				System.out.println("Nhap lai thong tin mon hoc theo dinh dang: ");
				System.out.println("[TenMH];[Heso]");
				System.out.println("Neu khong muon sua muc nao thi bo trong muc do");
				System.out.println("Vi du : ;2.5");
				System.out.println("Nhap exit de quay lai");
				try {
					String s;
					s=in.nextLine();
					if(s.compareTo("exit")==0) {
						return ;
					}
					String[] arrS = s.split(";");
					mh.suaMH(arrS[0],arrS[1]);
					System.out.println("Sua thanh cong !!!");
				} catch (Exception e) {
					System.out.println("Nhap sai dinh dang");
				}
				return;
			}
		}
		System.out.println("Khong tim thay mon hoc");
	}
	static void xoaMH() {
		Scanner in = new Scanner(System.in);
		System.out.println("Nhap ma mon hoc muon xoa");
		String s = in.nextLine();
		for (MonHoc mh : arrMH) {
			if(mh.getMaMonHoc().compareTo(s)==0) {
				arrMH.remove(mh);
				System.out.println("Xoa thanh cong");
				return;
			}
		}
		System.out.println("Khong tim thay mon hoc");
	}
	static void danhSachMH() {
		int num = 20;// so mon hoc 1 trang;
		int sotrang;
		if(arrMH.size()%num==0) {
			sotrang = arrMH.size()/num;
		}
		else {
			sotrang = 1+(arrMH.size()/num);
		}
		int trang = 1;
		pageMH(num,trang,sotrang);
	}
	static void pageMH(int num,int trang,int sotrang) {
		trang = trang>0?trang:1;
		trang = trang<sotrang?trang:sotrang;
		int luachon;
		do {
			System.out.println("┌──────┬────────────────────────────┬───────┐");
			System.out.println("│  Ma  │        Ten mon hoc         │ he so │");
			System.out.println("├──────┼────────────────────────────┼───────┤");
			int start = (trang-1)*20;
			int end =trang*20<arrMH.size()?trang*20:arrMH.size();
			for(int i= start;i<end;i++) {
				arrMH.get(i).xuat();
			}
			System.out.println("└──────┴────────────────────────────┴───────┘");
			System.out.println("---------------Trang thu : " + trang +"/" +sotrang+"-------------");
			System.out.println("1.Xem trang ke");
			System.out.println("2.Xem trang truoc");
			System.out.println("3.Xem trang cuoi");
			System.out.println("4.Xem trang dau tien");
			System.out.println("5.Xem trang cu the");
			System.out.println("0.Ve menu truoc");
			Scanner in = new Scanner(System.in);
			luachon = in.nextInt();
			switch (luachon) {
			case 1:
				pageMH(num,trang+1,sotrang);
				break;
			case 2:
				pageMH(num,trang-1,sotrang);
				break;
			case 3:
				pageMH(num,sotrang,sotrang);
				break;
			case 4:
				pageMH(num,1,sotrang);
				break;
			case 5:
				System.out.println("Nhap so trang muon xem : ");
				int p = in.nextInt();
				if(p<1||p>sotrang) {
					System.out.println("So trang nhap khong hop le !!!");
					break;
				}
				pageMH(num,p,sotrang);
				break;
			case 0:
//				menu1();
				return;
			default:
				System.out.println("Lua chon khong hop le");
				break;
			}
		}while(luachon<0||luachon>5);
	}
	private static void xoaDiem() {
		System.out.println("Nhap ma sinh vien muon xoa diem : ");
		Scanner in = new Scanner(System.in);
		String s = in.nextLine();
		for(SinhVien sv: arrSV) {
			if(sv.getMaSV().compareTo(s)==0) {
				bangDiemSinhVien(sv);
				System.out.println("Nhap ma mon muon xoa diem: ");
				System.out.println("Vi du: MH01");
				System.out.println("Nhap exit de quay lai");
				String mamon;
				mamon=in.nextLine();
				for(Diem diem:arrD) {
					if(diem.getMaMonHoc().compareTo(mamon)==0 && diem.getMaSV().compareTo(s)==0) {
						arrD.remove(diem);
						System.out.println("Xoa thanh cong!!!");
						return;
					}
				}
				System.out.println("Khong tim thay mon hoc tren!!");
				return;
			}
		}
		System.out.println("Khong tim thay sinh vien nay!!!");
		
	}
	private static void suaDiem() {
		System.out.println("Nhap ma sinh vien muon sua diem : ");
		Scanner in = new Scanner(System.in);
		String s = in.nextLine();
		for(SinhVien sv: arrSV) {
			if(sv.getMaSV().compareTo(s)==0) {
				bangDiemSinhVien(sv);
				System.out.println("Nhap diem muon sua theo dang: ");
				System.out.println("[MaMonHoc];[diem]");
				System.out.println("Vi du: MH01;10");
				try {
					String info;
					System.out.println("Nhap thong tin muon sua: ");
					info = in.nextLine();
					String[] arrS = info.split(";");
					for(Diem diem:arrD) {
						if(diem.getMaSV().compareTo(s)==0 && diem.getMaMonHoc().compareTo(arrS[0])==0) {
							diem.suaDiem(arrS[1]);
							System.out.println("Sua thanh cong");
							return;
						}
					}
					System.out.println("Khong tim thay mon hoc nay!!!");
					return;
				}catch(Exception e) {
					System.out.println("Nhap sai dinh dang");
				}
			}
		}
		System.out.println("Khong tim thay sinh vien nay");
	}
	private static void themDiem() {
		System.out.println("Nhap diem muon them theo dinh dang: ");
		System.out.println("[maSV];[maMonHoc];[diem]");
		System.out.println("Vi du : SV001;Toan;10");
		System.out.println("Nhap exit de quay lai");
		Scanner in = new Scanner(System.in);
		String s = in.nextLine();
		if(s.compareTo("exit")==0) {
			return ;
		}
		try {
			String[] arrS = s.split(";");
			for (Diem diem : arrD) {
				if(diem.getMaSV().compareTo(arrS[0])==0 && diem.getMaMonHoc().compareTo(arrS[1])==0) {
					System.out.println("Sinh vien da co diem cho mon nay roi!!!");
					return ;
				}
			}
			Diem x = new Diem(arrS[0],arrS[1],arrS[2]);
			arrD.add(x);
			System.out.println("Them thanh cong !!!");
		} catch (Exception e) {
			System.out.println("Nhap sai dinh dang!!!");
		}
	}
	static void bangDiemSinhVien(SinhVien sv) {
				System.out.println("┌──────────────────────────────────────────┐");
				System.out.format("│MaSV:%-10s %26s│\n", sv.getMaSV(),sv.getHoTen());
				System.out.println("├──────────────────────────────────────────┤");
				int sotin=0;
				float tongdiem=0;
				for (Diem diem : arrD) {
					if(diem.getMaSV().compareTo(sv.getMaSV())==0) {
						for(MonHoc mh : arrMH) {
							if(mh.getMaMonHoc().compareTo(diem.getMaMonHoc())==0) {
								sotin+=mh.getHeSo();
								tongdiem+=diem.getDiem()*mh.getHeSo();
								System.out.format("│%-8s%25s %8.2f│\n", mh.getMaMonHoc(),mh.getTenMonHoc(),diem.getDiem());
							}
						}
					}
				}
				if(sotin==0) {
					System.out.println("│Sinh vien chua hoc mon nao                │");
					System.out.format("│Diem tong ket: 0                          │\n");
					System.out.println("└──────────────────────────────────────────┘");
					return;
				}
				System.out.println("├──────────────────────────────────────────┤");
				System.out.format("│Diem tong ket: %25.2f  │\n", tongdiem/sotin);
				System.out.println("└──────────────────────────────────────────┘");
				return;
	}
	static void danhSachBangDiemSV() {
		int num = 10;// so sinh vien 1 trang;
		int sotrang;
		if(arrSV.size()%num==0) {
			sotrang = arrSV.size()/num;
		}
		else {
			sotrang = 1+(arrSV.size()/num);
		}
		int trang = 1;
		pageBangDiem(num,trang,sotrang);
	}
	private static void pageBangDiem(int num, int trang,int sotrang) {
		trang = trang>0?trang:1;
		trang = trang<sotrang?trang:sotrang;
		int luachon;
		do {
			System.out.println("--------DANH SACH BANG DIEM SINH VIEN-----------");
			int start = (trang-1)*num;
			int end =trang*num<arrSV.size()?trang*num:arrSV.size();
			for(int i= start;i<end;i++) {
				bangDiemSinhVien(arrSV.get(i));
			}
			System.out.println("----------------Trang thu : " + trang +"/" +sotrang+"---------------");
			System.out.println("1.Xem trang ke");
			System.out.println("2.Xem trang truoc");
			System.out.println("3.Xem trang cuoi");
			System.out.println("4.Xem trang dau tien");
			System.out.println("5.Xem trang cu the");
			System.out.println("0.Ve menu truoc");
			Scanner in = new Scanner(System.in);
			luachon = in.nextInt();
			switch (luachon) {
			case 1:
				pageBangDiem(num,trang+1,sotrang);
				break;
			case 2:
				pageBangDiem(num,trang-1,sotrang);
				break;
			case 3:
				pageBangDiem(num,sotrang,sotrang);
				break;
			case 4:
				pageBangDiem(num,1,sotrang);
				break;
			case 5:
				System.out.println("Nhap so trang muon xem : ");
				int p = in.nextInt();
				if(p<1||p>sotrang) {
					System.out.println("So trang nhap khong hop le !!!");
					break;
				}
				pageBangDiem(num,p,sotrang);
				break;
			case 0:
				break;
			default:
				System.out.println("Lua chon khong hop le");
				break;
			}
		}while(luachon<0||luachon>5);
	}
	static void bangTheoMonHoc() { 
		System.out.println("┌───────────────────────────────────────────┐");
		System.out.println("│              DANH SACH MON HOC            │");
		System.out.println("├───────────────────────────────────────────┤");
		for(MonHoc mh:arrMH) {
			mh.xuat();
		}
		System.out.println("└──────┴────────────────────────────┴───────┘");
		System.out.println("Nhap bang diem mon hoc muon xem : ");
		Scanner in = new Scanner(System.in);
		String s;
		s = in.nextLine();
		for(MonHoc mh:arrMH) {
			if(mh.getMaMonHoc().compareTo(s)==0) {
				int soSV =0;
				float tongDiem =0;
				for(Diem diem:arrD) {
					if(diem.getMaMonHoc().compareTo(mh.getMaMonHoc())==0) {
						soSV++;
						tongDiem += diem.getDiem();
					}
				}
				int num =20;
				int sotrang;
				if(soSV%num==0) {
					sotrang = soSV/num;
				}
				else {
					sotrang = 1+(soSV/num);
				}
				float dtb = tongDiem/soSV;
				pageMonHoc(num,1,sotrang,dtb,mh.getMaMonHoc(),mh.getTenMonHoc());
				return;
			}
		}
		System.out.println("Khong tim thay mon hoc nay");
	}
	private static void pageMonHoc(int num, int trang, int sotrang, float dtb,String mamon,String tenmon) {
		trang = trang>0?trang:1;
		trang = trang<sotrang?trang:sotrang;
		int luachon;
		do {
			int start = (trang-1)*num;
			int end =trang*num<arrSV.size()?trang*num:arrSV.size();
			int d=0;
			System.out.println("┌───────────────────────────────────────────┐");
			System.out.format("│Ma Mon :%-5s  │Ten Mon: %-18s│\n",mamon,tenmon);
			System.out.format("│Diem trung binh: %-26.2f│\n",dtb);
			System.out.println("├───────────────────────────────────────────┤");
			for(Diem diem:arrD) {
				if(diem.getMaMonHoc().compareTo(mamon)==0) {
					d++;
					if(d>start&&d<=end)
					for(SinhVien sv : arrSV) {
						if(sv.getMaSV().compareTo(diem.getMaSV())==0) {
							System.out.format("│%-10s │%-25s %-5.2f│\n", diem.getMaSV(),sv.getHoTen(),diem.getDiem());
						}
					}
				}
			}
			System.out.println("└───────────────────────────────────────────┘");
			System.out.println("------------Trang "+ trang+"/"+sotrang+"-------------");
			System.out.println("1.Xem trang ke");
			System.out.println("2.Xem trang truoc");
			System.out.println("3.Xem trang cuoi");
			System.out.println("4.Xem trang dau tien");
			System.out.println("5.Xem trang cu the");
			System.out.println("0.Ve menu truoc");
			Scanner in = new Scanner(System.in);
			luachon = in.nextInt();
			switch (luachon) {
			case 1:
				pageMonHoc(num,trang+1,sotrang,dtb,mamon,tenmon);
				break;
			case 2:
				pageMonHoc(num,trang-1,sotrang,dtb,mamon,tenmon);
				break;
			case 3:
				pageMonHoc(num,sotrang,sotrang,dtb,mamon,tenmon);
				break;
			case 4:
				pageMonHoc(num,1,sotrang,dtb,mamon,tenmon);
				break;
			case 5:
				System.out.println("Nhap so trang muon xem : ");
				int p = in.nextInt();
				if(p<1||p>sotrang) {
					System.out.println("So trang nhap khong hop le !!!");
					break;
				}
				pageMonHoc(num,p,sotrang,dtb,mamon,tenmon);
				break;
			case 0:
//				menu2();
				break;
			default:
				System.out.println("Lua chon khong hop le");
				break;
			}
		}while(luachon<0||luachon>5);
	}
	static void timMaSV() {
		Scanner in = new Scanner(System.in);
		System.out.println("Nhap ma SV muon tim : ");
		String masv = in.nextLine();
		for(SinhVien sv:arrSV) {
			if(sv.getMaSV().compareTo(masv)==0) {
				bangDiemSinhVien(sv);
				return;
			}
		}
		System.out.println("Khong tim thay sinh vien nay");
	}
	static void timTenSV() {
		Scanner in = new Scanner(System.in);
		System.out.println("Nhap ten SV muon tim : ");
		String ten = in.nextLine();
		int c=-1;
		System.out.println("┌─────────┬───────────────────────┬───────────┬────────────┬─────────┐");
		System.out.println("│   Ma    │  Ho dem               │  Ten      │ ngay sinh  │Gioi tinh│");
		System.out.println("├─────────┼───────────────────────┼───────────┼────────────┼─────────┤");
		for(SinhVien sv:arrSV) {
			if(sv.getTen().indexOf(ten)!=-1) {
				sv.xuat();
				c=1;
			}
		}
		System.out.println("└─────────┴───────────────────────┴───────────┴────────────┴─────────┘");
		if(c!=1) {
			System.out.println("Khong tim thay sinh vien nay");
		}
	}
	static void timMaMH() {
		Scanner in = new Scanner(System.in);
		System.out.println("Nhap ma mon hoc muon tim : ");
		String s = in.nextLine();
		for(MonHoc mh:arrMH) {
			if(mh.getMaMonHoc().compareTo(s)==0) {
				int soSV =0;
				float tongDiem =0;
				for(Diem diem:arrD) {
					if(diem.getMaMonHoc().compareTo(mh.getMaMonHoc())==0) {
						soSV++;
						tongDiem += diem.getDiem();
					}
				}
				int num =20;
				int sotrang = (int) Math.ceil(soSV/num);
				float dtb = tongDiem/soSV;
				pageMonHoc(num,1,sotrang,dtb,mh.getMaMonHoc(),mh.getTenMonHoc());
				return;
			}
		}
		System.out.println("Khong tim thay mon hoc nay");
	}
}
	