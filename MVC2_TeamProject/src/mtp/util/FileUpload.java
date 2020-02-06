package mtp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileUpload {
	private final StringBuffer TEMP_ROOT = new StringBuffer();
	private final StringBuffer MEMBER_ROOT = new StringBuffer();
	private final String SEPARATOR = File.separator;
	private String file;// 사용자가 올리는 파일
	private boolean target;
	private String id;
	private String getFileName;
	private FileUpload() {
		root_set();
		boolean flag = folderCreate();
		if (flag) {
			TEMP_ROOT.append(SEPARATOR);
			TEMP_ROOT.append("temp");
			TEMP_ROOT.append(SEPARATOR);
			
			MEMBER_ROOT.append(SEPARATOR);
			MEMBER_ROOT.append("member");
			MEMBER_ROOT.append(SEPARATOR);
		}
	}

	public FileUpload(String file) {
		this();
		this.file = file;
		getFileName = new File(file).getName();
		tempRootSet();
	}

	public FileUpload(String id, boolean target) {// insert 성공적으로 이뤄지면 temp에 저장되있는 이미지 파일을 member로 이동시켜주는 역활을 하는것 ==
		this();
		this.id = id;
		this.target = target;
		memberRootSet();
	}

	private void root_set() {
		TEMP_ROOT.append("c:");
		TEMP_ROOT.append(SEPARATOR);
		TEMP_ROOT.append("img");

		MEMBER_ROOT.append("c:");
		MEMBER_ROOT.append(SEPARATOR);
		MEMBER_ROOT.append("img");
		
		
	}
	private void tempRootSet() {
		TEMP_ROOT.append("(temp)");
		TEMP_ROOT.append(getFileName);
	}
	private void memberRootSet() {
		MEMBER_ROOT.append("(");
		MEMBER_ROOT.append(id);
		MEMBER_ROOT.append(")");
		System.out.println("MEMBER ->"+TEMP_ROOT.toString());
		File file = new File(TEMP_ROOT.toString());
		if(file.exists()) {
			getFileName = file.getName().split("(temp)")[0];
		}
		MEMBER_ROOT.append(getFileName);
	}
	public String getRoot() {
		return target? MEMBER_ROOT.toString():TEMP_ROOT.toString();
	}
	
	private boolean folderCreate() {
		final String root = getRoot();
		File file = new File(root);
		final List<Boolean> list1 = new ArrayList<>();
		list1.add(true);
		list1.add(true);
		list1.add(true);
		final List<Boolean> list2 = new ArrayList<>();
		boolean flag = false;
		if (!file.exists()) {
			// System.out.println(file.getName());
			flag = file.mkdir();
		}
//		System.out.println("img폴더의 생성 여부:"+flag);
//		System.out.println("img폴더의 여부:"+file.exists());
		if (flag || file.exists()) {
			file = new File(root + SEPARATOR + "temp");
			file.mkdir();

			// System.out.println("temp파일이면 ok="+file.getName());
			file = new File(root + SEPARATOR + "member");
			file.mkdir();
			// System.out.println("member파일이면 ok="+file.getName());
		}
		list2.add(new File(root).exists());
		list2.add(new File(root + SEPARATOR + "temp").exists());
		list2.add(new File(root + SEPARATOR + "member").exists());
		return Arrays.deepEquals(list1.toArray(), list2.toArray());
	}

	public void fileUpload() {
		InputStream in = null;
		OutputStream out = null;
		FileChannel infc = null;
		FileChannel outfc = null;
		String outroot = target?MEMBER_ROOT.toString():TEMP_ROOT.toString();
		String inroot = target? TEMP_ROOT.toString():file;
		boolean closeFlag = false;
		try {
			System.out.println("error1");
			in = new FileInputStream(inroot);
			infc = ((FileInputStream) in).getChannel();
			System.out.println("error2");
			out = new FileOutputStream(outroot);
			outfc = ((FileOutputStream)out).getChannel();
			System.out.println("error3");
			infc.transferTo(0, infc.size(), outfc);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeFlag = closeAll(outfc,out,infc,in);
			System.out.println("close 성공 여부 :"+closeFlag); 
		}
	}

	private boolean closeAll(Object... obj) {
		final List<Boolean> list1 = new ArrayList<>();
		list1.add(true);
		list1.add(true);
		list1.add(true);
		list1.add(true);
		final List<Boolean> list2 = new ArrayList<>();
		System.out.println("close 대상자냐 >?="+obj);
		try {
			if (obj != null) {
				for (int i = 0; i < obj.length; i++) {
					if(obj[i] instanceof OutputStream) {
						((OutputStream)obj[i]).close();
						list2.add(true);
					}
					if(obj[i] instanceof InputStream) {
						((InputStream) obj[i]).close();
						list2.add(true);
					}
					if(obj[i] instanceof FileChannel) {
						((FileChannel)obj[i]).close();
						list2.add(true);
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Arrays.deepEquals(list1.toArray(), list2.toArray());
	}
}
