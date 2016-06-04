import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.beans.*;
import java.awt.*;
import java.awt.event.*; //�ޥγB�z�ƥ�event�M��
import java.util.*;
import java.util.Timer;

public class hw5 {
	
	boolean ck_flag = false;
	private JFrame frame;
	private JTextField adult;
	private JTextField child;
	private JButton checkout,weekday,weekend,cannel;
	private JLabel discount,total, money,price;
	private int moneyad,moneycd,service=0;
	int moneytotal=0,disad=0,moneyt=0,disch=0;
	ActionListener times = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			JButton source = (JButton) e.getSource();
			if(source.getActionCommand().equals("weekdays")){
				moneyad=268;
				moneycd=120;
				checkout.setEnabled(true);
				weekday.setEnabled(false);
				weekend.setEnabled(true);
				money.setText("����:�j�H268��,�p��120��");
				checkout.requestFocusInWindow();
			}
			else if(source.getActionCommand().equals("weekends")){
				moneyad=368;
				moneycd=150;
				checkout.setEnabled(true);
				weekday.setEnabled(true);
				weekend.setEnabled(false);
				money.setText("����:�j�H368��,�p��150��,10%�A�ȶO");
				checkout.requestFocusInWindow();
			}
		}
	};
	ActionListener Total = new ActionListener(){
		int adultnum=0;
		int childnum=0;
		int count=0;
		//boolean zero=true;
		public void actionPerformed(ActionEvent e){
			int result=JOptionPane.showConfirmDialog(checkout,"�T�w���b?","�T�w",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
			if(result==JOptionPane.YES_OPTION){
				adultnum=Integer.parseInt(adult.getText());
				childnum=Integer.parseInt(child.getText());
				moneyt=(childnum*moneycd)+(adultnum*moneyad);
				while((adultnum+childnum)!=0){
					if((adultnum+childnum)<3){
						discount.setText("�L�馩");
						//total.setText(""+moneytotal);
						adultnum=0;
						childnum=0;
					}
					else if((adultnum+childnum)%3==0){
						if(childnum==0){
							disad=disad+1 * moneyad;
							adultnum=adultnum-3;
						}
						else if(childnum!=0){
							disch=disch+1*moneycd;
							adultnum=adultnum-2;
							childnum=childnum-1;
						}
						else if(adultnum==0 &&childnum!=0){
							disch=disch+1*moneycd;
							childnum=childnum-1;
						}
						else if(adultnum!=0 && childnum==0){
							disad=disad+1 * moneyad;
							adultnum=adultnum-1;
						}
					}
					else if((adultnum+childnum)%3!=0){
						if(childnum==0){
							disad=disad+1 * moneyad;
							adultnum=adultnum-3;
						}
						else if(childnum!=0){
							disch=disch+1*moneycd;
							adultnum=adultnum-2;
							childnum=childnum-1;
						}
						else if(adultnum==0 &&childnum!=0){
							disch=disch+1*moneycd;
							childnum=childnum-1;
						}
						else if(adultnum!=0 && childnum==0){
							disad=disad+1 * moneyad;
							adultnum=adultnum-1;
						}
					}
				}
				if(weekday.isEnabled()==false){
					if((adultnum+childnum)<10){
						moneytotal=moneyt-disad-disch;
					}
					else{
						moneytotal=(int) ((moneyt-disad-disch)*0.95);
					}
					price.setText(""+moneyt+"��");
					total.setText(""+moneytotal+"��");
				}
				else if(weekend.isEnabled()==false){
					if((adultnum+childnum)<10){
						moneytotal=moneyt-disad-disch;
					}
					else{
						moneytotal=(int) ((moneyt-disad-disch)*0.95);
					}
					service=(int) (moneytotal*0.1);
					moneytotal=moneytotal+service;
					price.setText(""+moneyt+"��");
					total.setText(""+moneytotal+"��"+"(�A�ȶO:"+service+"��)");
				}
				System.out.println("disch:"+disch);
				System.out.println("disad"+disad);
				if(disad!=0 &&disch==0){
					discount.setText("�T�H�P��,�@�H�K�O,�j�H -"+disad+"��");
				}
				else if(disad==0 &&disch!=0){
					discount.setText("�T�H�P��,�@�H�K�O(�p���u��),�p�� -"+disch+"��");
				}
				else if(disad!=0 &&disch!=0){
					discount.setText("�T�H�P��,�@�H�K�O(�p���u��),�j�H -"+disad+"��"+"�p�� -"+disch+"��");
				}
				else{
					discount.setText("�L�馩");
				}
			}
			
			
			weekday.setEnabled(true);
			weekend.setEnabled(true);
			checkout.setEnabled(false);
		}
	};
	ActionListener Cannel= new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			int result=JOptionPane.showConfirmDialog(checkout,"�T�w����?","�T�w",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
			if(result==JOptionPane.YES_OPTION){
				adult.setText("0");
				child.setText("0");
				weekday.setEnabled(true);
				weekend.setEnabled(true);
			}
		}
	};
	ActionListener al = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JTextField input =(JTextField)e.getSource();
			boolean all = true;
			String ck = input.getText();
			byte w[] = ck.getBytes();
			for(int i = 0 ; i < ck.length() ; i++){
				if(w[i] >= 48 & w[i] <= 57){
					all=true;
				}
				else
					all=false;
			}
			if(all==false){
				JOptionPane.showMessageDialog(input,"�п�J�Ʀr","���~",JOptionPane.WARNING_MESSAGE);
				input.setText("");
			}
			ck_flag = all;
		}
	};
	FocusListener check = new FocusListener(){
		public void focusGained(FocusEvent e){
			JTextField input =(JTextField)e.getSource();
			input.setText("");
		}
		public void focusLost(FocusEvent e){
			JTextField input =(JTextField)e.getSource();
			boolean all = true;
			String ck = input.getText();
			byte w[] = ck.getBytes();
			if(ck.equals("")){
				//JOptionPane.showMessageDialog(input, "�п�J�Ʀr","���~",JOptionPane.WARNING_MESSAGE);
				input.setText("0");
			}				
			for(int i = 0 ; i < ck.length() ; i++){
				if(w[i] >= 48 & w[i] <= 57){
					all=true;
				}
				else
					all=false;
			}
			if(all==false){
				JOptionPane.showMessageDialog(input, "�п�J�Ʀr","���~",JOptionPane.WARNING_MESSAGE);
				input.setText("");
			}
		}
	};
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					hw5 window = new hw5();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	public hw5() {
		initialize();
	}


	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 480, 330);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		weekday = new JButton("���餤�Ȯɬq");
		weekday.setFont(new Font("�s�ө���", Font.PLAIN, 18));
		weekday.setBounds(10, 33, 151, 23);
		frame.getContentPane().add(weekday);
		weekday.setActionCommand("weekdays");
		weekday.addActionListener(times);
		weekday.requestFocusInWindow();
		weekend = new JButton("����ߤW,�P��,�Ұ���ɬq");
		weekend.setFont(new Font("�s�ө���", Font.PLAIN, 18));
		weekend.setBounds(171, 33, 253, 23);
		frame.getContentPane().add(weekend);
		weekend.setActionCommand("weekends");
		weekend.addActionListener(times);
		
		JLabel label = new JLabel("�j�H");
		label.setFont(new Font("�s�ө���", Font.PLAIN, 18));
		label.setBounds(20, 84, 46, 20);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("�p��");
		label_1.setFont(new Font("�s�ө���", Font.PLAIN, 18));
		label_1.setBounds(20, 114, 46, 20);
		frame.getContentPane().add(label_1);
		
		adult = new JTextField();
		adult.setFont(new Font("�s�ө���", Font.PLAIN, 18));
		adult.setBounds(76, 81, 96, 21);
		frame.getContentPane().add(adult);
		adult.setColumns(10);
		adult.addActionListener(al);
		adult.addFocusListener(check);
		adult.setText("0");
		
		child = new JTextField();
		child.setFont(new Font("�s�ө���", Font.PLAIN, 18));
		child.setBounds(76, 111, 96, 21);
		frame.getContentPane().add(child);
		child.setColumns(10);
		child.addActionListener(al);
		child.addFocusListener(check);
		child.setText("0");
		
		JLabel label_2 = new JLabel("�H");
		label_2.setFont(new Font("�s�ө���", Font.PLAIN, 18));
		label_2.setBounds(196, 84, 46, 20);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("�H");
		label_3.setFont(new Font("�s�ө���", Font.PLAIN, 18));
		label_3.setBounds(196, 114, 46, 20);
		frame.getContentPane().add(label_3);
		
		JLabel lblTotal = new JLabel("�`�p:");
		lblTotal.setFont(new Font("�s�ө���", Font.PLAIN, 18));
		lblTotal.setBounds(20, 222, 64, 20);
		frame.getContentPane().add(lblTotal);
		
		JLabel label_4 = new JLabel("�馩:");
		label_4.setFont(new Font("�s�ө���", Font.PLAIN, 18));
		label_4.setBounds(20, 192, 46, 20);
		frame.getContentPane().add(label_4);
		
		checkout = new JButton("���b");
		checkout.setFont(new Font("�s�ө���", Font.PLAIN, 18));
		checkout.setBounds(252, 93, 87, 23);
		frame.getContentPane().add(checkout);
		checkout.addActionListener(Total);
		checkout.setEnabled(false);
		
		discount = new JLabel("");
		discount.setFont(new Font("�s�ө���", Font.PLAIN, 18));
		discount.setBounds(76, 192, 378, 20);
		frame.getContentPane().add(discount);
		
		total = new JLabel("");
		total.setFont(new Font("�s�ө���", Font.PLAIN, 18));
		total.setBounds(76, 222, 263, 20);
		frame.getContentPane().add(total);
		
		money = new JLabel("���:");
		money.setFont(new Font("�s�ө���", Font.PLAIN, 18));
		money.setBounds(20, 252, 301, 29);
		frame.getContentPane().add(money);
		
		cannel = new JButton("����");
		cannel.setBounds(349, 93, 64, 23);
		frame.getContentPane().add(cannel);
		cannel.addActionListener(Cannel);
		
		JLabel lblNewLabel = new JLabel("���:");
		lblNewLabel.setFont(new Font("�s�ө���", Font.PLAIN, 18));
		lblNewLabel.setBounds(20, 162, 46, 20);
		frame.getContentPane().add(lblNewLabel);
		
		price = new JLabel("");
		price.setFont(new Font("�s�ө���", Font.PLAIN, 18));
		price.setBounds(76, 162, 263, 20);
		frame.getContentPane().add(price);
		
	}
}
