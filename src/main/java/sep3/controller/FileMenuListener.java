package sep3.controller;

import sep3.Model;
import sep3.View;
import sep3.misc.FileIO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Paths;

public class FileMenuListener implements ActionListener {
	private final Model model;
	private final View view;

	// 一度開いたディレクトリを覚えておきたいので、JFileChooserを使い回す
	private final JFileChooser fc = new JFileChooser(Paths.get("").toAbsolutePath().toFile());

	public FileMenuListener(Model m, View v) {
		model = m;
		view = v;
	}

	public void actionPerformed(ActionEvent e) {
		//System.out.println("enter actionlistener of FILE menu");
		// メニューバー上で.binファイル読み込みが指定されたら、それを読み込んでメモリ（主記憶）にセットする
		int selected = fc.showOpenDialog(view.getContentPane());
		if (selected == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			FileIO fio = new FileIO(model.getMemory());
			//System.out.println("  load '" + file +"'");
			fio.load(file);
		}
	}
}
