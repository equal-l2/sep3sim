package sep3.misc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import sep3.model.Memory;

// binファイル（sep3asmの出力ファイル）の読み込み
public class FileIO {
	private Memory mem;

	public FileIO(Memory m) {
		mem = m;
	}
	public void load(File file) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			loadBody(br);
			br.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	public void load(InputStream in) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			loadBody(br);
			br.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	private void loadBody(BufferedReader br) throws NumberFormatException, IOException {
    	String line;
    		// 各行の先頭にある２つの数値だけを読む。その他は読み捨てる。
    		while ((line = br.readLine()) != null) {
    			StringTokenizer st = new StringTokenizer(line, " \t:");
    			String addr = st.nextToken();
    			String word = st.nextToken();
    			//System.out.println("read: addr='" + addr +"' word='" + word + "'");
    			//int a = Integer.decode(addr).intValue();
    			//int w = Integer.decode(word).intValue();
    			int a = Integer.parseInt(addr, 16);
    			int w = Integer.parseInt(word, 16);
    			mem.setValue(a, w);
    			//System.out.println("loaded mem[" + String.format("%1$04x", a)  + "]=" + String.format("%1$04x", w));
    		}
	}
}
