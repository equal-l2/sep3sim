package sep3.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import sep3.*;
import sep3.misc.FileIO;

public class FileMenuListener implements ActionListener {
	private Model model;
	private View  view;

	public FileMenuListener(Model m, View v) {
		model = m; view = v;
	}

	public void actionPerformed(ActionEvent e){
		//System.out.println("enter actionlistener of FILE menu");
		// メニューバー上で.binファイル読み込みが指定されたら、それを読み込んでメモリ（主記憶）にセットする
		JFileChooser fc = new JFileChooser();
		int selected = fc.showOpenDialog(view.getContentPane());
		if (selected == JFileChooser.APPROVE_OPTION){
			File file = fc.getSelectedFile();
			FileIO fio = new FileIO(model.getMemory());
			//System.out.println("  load '" + file +"'");
			fio.load(file);
		}
	}
}
