import game.UIManager;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.BoxLayout;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JButton;

import java.awt.FlowLayout;

import javax.swing.JLabel;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Launcher extends JFrame implements ActionListener {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager
					.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Launcher frame = new Launcher();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Launcher() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 시작버튼. 누르면 게임 시작
		JButton btnNewButton = new JButton("Start");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(12, 201, 200, 50);
		contentPane.add(btnNewButton);

		// 종료버튼. 누르면 런쳐 종료
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setBounds(224, 201, 200, 50);
		contentPane.add(btnNewButton_1);

		JRadioButton rdbtnCharacter = new JRadioButton("Character 1");
		rdbtnCharacter.setBounds(12, 6, 121, 23);
		contentPane.add(rdbtnCharacter);

		JRadioButton rdbtnCharacter_1 = new JRadioButton("Character 2");
		rdbtnCharacter_1.setBounds(12, 31, 121, 23);
		contentPane.add(rdbtnCharacter_1);

		JRadioButton rdbtnCharacter_2 = new JRadioButton("Character 3");
		rdbtnCharacter_2.setBounds(12, 56, 121, 23);
		contentPane.add(rdbtnCharacter_2);

		JRadioButton rdbtnCharacter_3 = new JRadioButton("Character 4");
		rdbtnCharacter_3.setBounds(12, 81, 121, 23);
		contentPane.add(rdbtnCharacter_3);

		JRadioButton rdbtnCharacter_4 = new JRadioButton("Character 5");
		rdbtnCharacter_4.setBounds(12, 106, 121, 23);
		contentPane.add(rdbtnCharacter_4);

		ButtonGroup btngrp = new ButtonGroup();
		btngrp.add(rdbtnCharacter);
		btngrp.add(rdbtnCharacter_1);
		btngrp.add(rdbtnCharacter_2);
		btngrp.add(rdbtnCharacter_3);
		btngrp.add(rdbtnCharacter_4);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Start")) {
			this.setVisible(false);
			AppGameContainer app;
			try {
				app = new AppGameContainer(new Test("TestWindow"));
				app.setDisplayMode( 1280, 720, false);
				app.start();
				
			} catch (SlickException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} else if (e.getActionCommand().equals("Exit")) {
			System.exit(0);
		}
	}
}
