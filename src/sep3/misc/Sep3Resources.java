package sep3.misc;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

// Eclipse上でも、jarファイルにした後でも、ファイルが確実に読み込めるようにする
public class Sep3Resources {
	public URL resource2url(String pos, String name) {
		System.out.println("Check: " + name);
		URL url = getClass().getClassLoader().getResource(name);
		System.out.println(url);
		if (url == null) {
			System.out.println("Check: " + pos + "/" + name);
			url = getClass().getClassLoader().getResource(pos + "/" + name);
			System.out.println(url);
		}
		if (url == null) {
			System.out.println("Check: " + "resources/" + name);
			url = getClass().getClassLoader().getResource("resources/" + name);
			System.out.println(url);
		}
		if (url == null) {
			System.out.println("Check: " + "src/" + pos + "/" + name);
			url = getClass().getClassLoader().getResource("src/" + pos + "/" + name);
			System.out.println(url);
		}
		if (url == null) {
			System.err.println("Can't find resource: (" + pos + ", " + name + ")");
		}
		return url;
	}
	public File resource2file(String pos, String name) {
		//File f;
		//System.out.println("Check: " + name);
		//f = new File(name);
		//System.out.println(f);
		//if (!f.exists()) {
		//	System.out.println("Check: " + pos + "/" + name);
		//	f = new File(pos + "/" + name);
		//	System.out.println(f);
		//}
		//if (!f.exists()) {
		//	System.out.println("Check: " + "resources/" + name);
		//	f = new File("resources/" + name);
		//	System.out.println(f);
		//}
		//if (!f.exists()) {
		//	System.out.println("Check: " + "src/" + pos + "/" + name);
		//	f = new File("src/" + pos + "/" + name);
		//	System.out.println(f);
		//}
		//if (!f.exists()) {
		//	System.err.println("Can't find resource: (" + pos + ", " + name + ")");
		//}
		File f = null;
		try {
			f = new File(getClass().getResource("toggleon.png").toURI());
		} catch (URISyntaxException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return f;
	}
	public URI resource2uri(String pos, String name) throws URISyntaxException {
		System.out.println("Check: " + name);
		//URL url = getClass().getClassLoader().getResource(name);
		//System.out.println(url);
		//if (url == null) {
		//	System.out.println("Check: " + pos + "/" + name);
		//	url = getClass().getClassLoader().getResource(pos + "/" + name);
		//	System.out.println(url);
		//}
		//if (url == null) {
		//	System.out.println("Check: " + "resources/" + name);
		//	url = getClass().getClassLoader().getResource("resources/" + name);
		//	System.out.println(url);
		//}
		//if (url == null) {
		//	System.out.println("Check: " + "src/" + pos + "/" + name);
		//	url = getClass().getClassLoader().getResource("src/" + pos + "/" + name);
		//	System.out.println(url);
		//}
		//if (url == null) {
		//	System.err.println("Can't find resource: (" + pos + ", " + name + ")");
		//}
		URL url = getClass().getClassLoader().getResource("resources/pro.png");
		return url.toURI();
	}
}
