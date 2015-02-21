package GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class songLib extends JFrame implements ActionListener{

	JPanel display = new JPanel();
	JPanel editLibrary = new JPanel();

	JButton add = new JButton("Add");
	
	JButton edit = new JButton("Edit");
	JButton delete = new JButton("Delete");
	JList songDisplay;
	DefaultListModel dListModel;
	ArrayList<String>songsName=new ArrayList<String>();
	HashMap <String,ArrayList<String>> songsInfo=new HashMap<String,ArrayList<String>>();

		Vector temp=new Vector();
	public void addNewSong(String name,ArrayList<String> songList)
	{
		songsInfo.put(name,songList);
		songsName.add(name);
		dListModel.addElement(name); //data has type Object[]

		
	}
	
	public songLib(String title) {
		super(title);
		setLayout(new FlowLayout());
		editLibrary.add(add);
		editLibrary.add(edit);
		editLibrary.add(delete);

		add(editLibrary);
		//display.add(textArea1);
		add.addActionListener(this);
		
		dListModel = new DefaultListModel();
	/*	songDisplay.setSelectedIndex(0);
		songDisplay.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		songDisplay.setLayoutOrientation(JList.HORIZONTAL_WRAP); */
		
		System.out.println("herherhehrhe");
	//	JScrollPane listScroller = new JScrollPane(songDisplay);
		//listScroller.setPreferredSize(new Dimension(250, 80));
		songDisplay=new JList(dListModel);
		display.add(songDisplay);
		add(display);
		
	}
	
	public static void main(String args[]) {
		JFrame library = new songLib("Song Library Display");
		library.setSize(650, 300);
		library.setResizable(false);
		library.setLocationRelativeTo(null);
		library.setVisible(true);
		library.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ArrayList <String>info=new ArrayList<String>();
		info.add("snehit");
		addNewSong("drashti Patel", info);
		
	}

}
