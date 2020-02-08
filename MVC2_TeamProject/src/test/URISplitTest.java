package test;

public class URISplitTest {

	public static void main(String[] args) {
		String uri = "/MVC2_TeamProject/member/loginui.do";
		String path = "/MVC2_TeamProject";
		String[] uris = uri.split(path);
		String target = uris[uris.length-1].contains("/member")?"/member":"/board";
		System.out.println("target = "+target );
		uri = uris[uris.length-1].substring(target.length());
		System.out.println("uri ="+uri);
		uri = uri.contains(target)?uri.substring(target.length()):uri;
		System.out.println("sub -> uri ="+uri); 
		String root = uris[uris.length-1].split(uri)[0];
		System.out.println("root="+root);
		System.out.println("root length ="+root.length());
		System.out.println("target length ="+target.length());
		root = root.contains(target)?root.length()!=target.length()?
				root.substring(target.length()):root:root;
		System.out.println("sub -> root ="+root);
	} 

}
