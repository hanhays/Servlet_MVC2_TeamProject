package mtp.util;


public class FileUploadTest {

	public static void main(String[] args) {
		FileUpload fu = new FileUpload("C:\\Users\\user\\Pictures\\2.jpg"); 
		fu.fileUpload();
		System.out.println(fu.getRoot());
		fu = new FileUpload("test",true);
		System.out.println(fu.getRoot()); 

	}

}
