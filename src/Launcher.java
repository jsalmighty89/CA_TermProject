import game.GameDataManager;
import game.character.PlayerKJS;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import javax.swing.JLabel;

class CharacterButton extends JRadioButton {
	public int idx;

	public CharacterButton(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}
}

public class Launcher extends JFrame implements ActionListener {

	private JPanel contentPane;
	private ButtonGroup btngrp;
	private JLabel lblNewLabel;

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

		CharacterButton rdbtnCharacter = new CharacterButton("Character 1");
		rdbtnCharacter.setBounds(12, 6, 121, 23);
		contentPane.add(rdbtnCharacter);
		rdbtnCharacter.idx = 0;
		rdbtnCharacter.addActionListener(this);

		CharacterButton rdbtnCharacter_1 = new CharacterButton("Character 2");
		rdbtnCharacter_1.setBounds(12, 31, 121, 23);
		contentPane.add(rdbtnCharacter_1);
		rdbtnCharacter_1.idx = 1;
		rdbtnCharacter_1.addActionListener(this);

		CharacterButton rdbtnCharacter_2 = new CharacterButton("Character 3");
		rdbtnCharacter_2.setBounds(12, 56, 121, 23);
		contentPane.add(rdbtnCharacter_2);
		rdbtnCharacter_2.idx = 2;
		rdbtnCharacter_2.addActionListener(this);

		CharacterButton rdbtnCharacter_3 = new CharacterButton("Character 4");
		rdbtnCharacter_3.setBounds(12, 81, 121, 23);
		contentPane.add(rdbtnCharacter_3);
		rdbtnCharacter_3.idx = 3;
		rdbtnCharacter_3.addActionListener(this);

		CharacterButton rdbtnCharacter_4 = new CharacterButton("Character 5");
		rdbtnCharacter_4.setBounds(12, 106, 121, 23);
		contentPane.add(rdbtnCharacter_4);
		rdbtnCharacter_4.idx = 4;
		rdbtnCharacter_4.addActionListener(this);

		btngrp = new ButtonGroup();
		btngrp.add(rdbtnCharacter);
		btngrp.add(rdbtnCharacter_1);
		btngrp.add(rdbtnCharacter_2);
		btngrp.add(rdbtnCharacter_3);
		btngrp.add(rdbtnCharacter_4);

		lblNewLabel = new JLabel(new ImageIcon("res/images/player.png"));
		lblNewLabel.setBounds(224, 10, 198, 139);
		contentPane.add(lblNewLabel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getActionCommand().equals("Start")) {
			this.setVisible(false);
			AppGameContainer app;
			try {

				PlayerKJS.getDescription();
				// GameDataManager.getInstance().setSelectedPlayerIdx(2);

				app = new AppGameContainer(new Test("TestWindow"));
				app.setDisplayMode(1280, 720, false);
				app.start();

			} catch (SlickException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if (e.getActionCommand().equals("Exit")) {
			System.exit(0);
		} else if (e.getSource() instanceof CharacterButton) {
			CharacterButton btnCharacter = (CharacterButton) e.getSource();
			GameDataManager.selectedPlayerIdx = btnCharacter.idx;
			
			if (e.getActionCommand().equals("Character 2")) {
				lblNewLabel.setIcon(new ImageIcon("res/images/ironman.png"));
			} else if (e.getActionCommand().equals("Character 4")) {
				lblNewLabel.setIcon(new ImageIcon("res/images/playerhun.png"));
			} else if (e.getActionCommand().equals("Character 5")) {
				lblNewLabel.setIcon(new ImageIcon("res/images/sas.png"));
			} else {
				lblNewLabel.setIcon(new ImageIcon("res/images/player.png"));
			}
		}
	}
}
