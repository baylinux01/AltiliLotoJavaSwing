package Loto_altili;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

public class Loto extends JFrame {

	protected static Loto frame;
	protected JPanel contentPane;
	protected JTextField t1,t2,t3,t4,t5,t6;
	protected JLabel lb0,lb1, lb2, l1,l2,l3,l4,l5,l6,l7;
	protected JButton btn;
	protected Robot robot;
	protected ArrayList <Long> kullanicigiris= new ArrayList <Long>();
	protected ArrayList <Long> bilgisayargiris= new ArrayList <Long>();
	protected boolean tekrarOyna=false;

	/**
	 * Launch the application.
	 */
	public static boolean isSpacedEnded(String s)
	{
		return s!=null && Character.isWhitespace(s.charAt(s.length()-1));
	}
	
	public boolean isFractional(String s) {  
	    return s != null && (s.matches("[-+]?\\d+\\.+") || s.matches("[-+]?\\d+[^0-9.]") || s.matches("[-+]?\\d+\\.+0*[1-9]*+[^0-9]+"));  
	}  
	
	public boolean isFractionalNumeric(String s) {  
	    return s != null && (s.matches("[-+]?\\d+\\.+0*[1-9]+"));  
	}  
	
	public boolean isPositiveExactNumeric(String s) {  
	    return s != null && s.matches("[+]?\\d*\\.?0*");  
	}  
	
	
	public boolean isNegativeExactNumeric(String s) {  
	    return s != null && s.matches("[-]\\d*\\.?0*");  
	}  
	
	public boolean isExactNumeric(String s) {  
	    return s != null && s.matches("[-+]?\\d*\\.?0*");  
	}  
	
	public boolean isNegativeNumeric(String s) {  
	    return s != null && s.matches("[-]\\d*\\.?\\d+");  
	} 
	
	public boolean isNumeric(String s) {  
	    return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
	}  
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Loto();
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
	
	
	
	public Loto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,500);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		t1=new JTextField();
		t1.setSize(100,20);
		t1.setLocation(150,90);
		contentPane.add(t1);
		t1.getDocument().addDocumentListener(dl1);
		
		t2=new JTextField();
		t2.setSize(100,20);
		t2.setLocation(150,120);
		contentPane.add(t2);
		t2.getDocument().addDocumentListener(dl2);
		
		t3=new JTextField();
		t3.setSize(100,20);
		t3.setLocation(150,150);
		contentPane.add(t3);
		t3.getDocument().addDocumentListener(dl3);
		
		t4=new JTextField();
		t4.setSize(100,20);
		t4.setLocation(150,180);
		contentPane.add(t4);
		t4.getDocument().addDocumentListener(dl4);
		
		t5=new JTextField();
		t5.setSize(100,20);
		t5.setLocation(150,210);
		contentPane.add(t5);
		t5.getDocument().addDocumentListener(dl5);
		
		t6=new JTextField();
		t6.setSize(100,20);
		t6.setLocation(150,240);
		contentPane.add(t6);
		t6.getDocument().addDocumentListener(dl6);
		
		Border border =BorderFactory.createLineBorder(Color.black,1);
		
		lb0=new JLabel("0 ile 13 arasında 6 sayı girip sonra oyna butonuna tıklayınız");
		lb0.setSize(340,18);
		lb0.setLocation(110,30);
		contentPane.add(lb0);
		lb0.setBorder(border);
		lb0.setHorizontalAlignment(SwingConstants.CENTER);
		
		lb1=new JLabel("Tahminleriniz");
		lb1.setSize(98,18);
		lb1.setLocation(150,60);
		contentPane.add(lb1);
		lb1.setBorder(border);
		lb1.setHorizontalAlignment(SwingConstants.CENTER);
		
		lb2=new JLabel("Çekiliş sonuçları");
		lb2.setSize(98,18);
		lb2.setLocation(300,60);
		contentPane.add(lb2);
		lb2.setBorder(border);
		lb2.setHorizontalAlignment(SwingConstants.CENTER);
		
		l1=new JLabel();
		l1.setSize(98,18);
		l1.setLocation(300,90);
		contentPane.add(l1);
		l1.setBorder(border);
		
		l2=new JLabel();
		l2.setSize(98,18);
		l2.setLocation(300,120);
		contentPane.add(l2);
		l2.setBorder(border);
		
		l3=new JLabel();
		l3.setSize(98,18);
		l3.setLocation(300,150);
		contentPane.add(l3);
		l3.setBorder(border);
		
		l4=new JLabel();
		l4.setSize(98,18);
		l4.setLocation(300,180);
		contentPane.add(l4);
		l4.setBorder(border);
		
		l5=new JLabel();
		l5.setSize(98,18);
		l5.setLocation(300,210);
		contentPane.add(l5);
		l5.setBorder(border);
		
		l6=new JLabel();
		l6.setSize(98,18);
		l6.setLocation(300,240);
		contentPane.add(l6);
		l6.setBorder(border);
		
		l7=new JLabel();
		l7.setSize(340,18);
		l7.setLocation(110,280);
		contentPane.add(l7);
		l7.setBorder(border);
		l7.setHorizontalAlignment(SwingConstants.CENTER);
		
		btn=new JButton("Oyna");
		btn.setSize(170,20);
		btn.setLocation(195,310);
		contentPane.add(btn);
		btn.addActionListener(al1);
		btn.addFocusListener(fl1);
		btn.addMouseListener(ml1);
		//focus sırasında noktalı görünümü yok ederek focusun belli olmamasını sağlayan kod
		btn.setFocusPainted(false);
		//hover effectini kaldıran kod
		btn.setBorderPainted(false);
		btn.setBackground(Color.lightGray);
		
	}
	
	MouseListener ml1=new MouseListener() 
	{

		
		public void mouseClicked(MouseEvent e) 
		{
			// TODO Auto-generated method stub
			
		}

		
		public void mousePressed(MouseEvent e) 
		{
			if(e.getButton()==1) 
			{

				
				
			}
			
		}

		
		public void mouseReleased(MouseEvent e) 
		{
			// TODO Auto-generated method stub
			
		}

		public void mouseEntered(MouseEvent e)
        {
	    btn.setBackground(Color.white);
	    getRootPane().setDefaultButton(btn);
	    btn.requestFocus();
        }
		
         public void mouseExited(MouseEvent e)
         {
         btn.setBackground(Color.lightGray);
         getRootPane().setDefaultButton(null);

         }
		
	};
	
	FocusListener fl1=new FocusListener() 
	{

		
			
			public void focusGained(FocusEvent e) {
				btn.setBackground(Color.white);
				
			}

			
			public void focusLost(FocusEvent e) {
				btn.setBackground(Color.lightGray);
				
			}
			
	
		
	};
	
	ActionListener al1=new ActionListener() 
	{

		public void actionPerformed(ActionEvent e) 
		{
			
			if(tekrarOyna==false)
			{
				if(t1.getText().equals("") || t2.getText().equals("") 
						|| t3.getText().equals("") || t4.getText().equals("") 
						|| t5.getText().equals("") || t6.getText().equals(""))
				{
					l7.setText("Eksik ya da hatalı veri tekrar deneyin");
					kullanicigiris.clear();
					bilgisayargiris.clear();
					l1.setText("");
					l2.setText("");
					l3.setText("");
					l4.setText("");
					l5.setText("");
					l6.setText("");
				}
				else 
				{
					int count=0;
					kullanicigiris.clear();
					bilgisayargiris.clear();
					l1.setText("");
					l2.setText("");
					l3.setText("");
					l4.setText("");
					l5.setText("");
					l6.setText("");
					l7.setText("");
					if(t1.getText().length()<2) t1.setText("0"+t1.getText());
					if(t2.getText().length()<2) t2.setText("0"+t2.getText());
					if(t3.getText().length()<2) t3.setText("0"+t3.getText());
					if(t4.getText().length()<2) t4.setText("0"+t4.getText());
					if(t5.getText().length()<2) t5.setText("0"+t5.getText());
					if(t6.getText().length()<2) t6.setText("0"+t6.getText());
					kullanicigiris.add(Long.valueOf(t1.getText()));
					kullanicigiris.add(Long.valueOf(t2.getText()));
					kullanicigiris.add(Long.valueOf(t3.getText()));
					kullanicigiris.add(Long.valueOf(t4.getText()));
					kullanicigiris.add(Long.valueOf(t5.getText()));
					kullanicigiris.add(Long.valueOf(t6.getText()));
					
					Random rnd = new Random();
					int a=0;
					while(a<6)
					{
						long deger=rnd.nextLong(14);
						bilgisayargiris.add(deger);
						a++;
					}
					
					l1.setText(bilgisayargiris.get(0)+"");
					l2.setText(bilgisayargiris.get(1)+"");
					l3.setText(bilgisayargiris.get(2)+"");
					l4.setText(bilgisayargiris.get(3)+"");
					l5.setText(bilgisayargiris.get(4)+"");
					l6.setText(bilgisayargiris.get(5)+"");
					
					
					if(l1.getText().length()<2) l1.setText("0"+l1.getText());
					if(l2.getText().length()<2) l2.setText("0"+l2.getText());
					if(l3.getText().length()<2) l3.setText("0"+l3.getText());
					if(l4.getText().length()<2) l4.setText("0"+l4.getText());
					if(l5.getText().length()<2) l5.setText("0"+l5.getText());
					if(l6.getText().length()<2) l6.setText("0"+l6.getText());
					
					a=0;
					int b=0;
					count=0;
					while( b<bilgisayargiris.size())
					{
						if(kullanicigiris.get(a)==bilgisayargiris.get(b))
						{
							count++;
							kullanicigiris.remove(a);
							bilgisayargiris.remove(b);
							a=0;
							b=0;
						}
						else a++;
						if(a==kullanicigiris.size())
						{
							a=0;
							b++;
						}
						
					}
					if(count==0)
					{l7.setText("Hiçbir sayıyı bilemediniz. Üzgünüm.");}
					if(count==1 || count==2)
					{l7.setText(count+" adet sayı bildiniz");}
					if(count==3 || count==4)
					{l7.setText(count+" adet sayı bildiniz. Amorti'yi kazandınız");}
					if(count==5)
					{l7.setText(" Tebrikler 5'liyi tutturdunuz");}
					if(count==6)
					{l7.setText(" Tebrikler 6'lıyı tutturdunuz. Artık zenginsiniz :) ");}
					btn.setText("Temizle ve tekrar oyna");
					tekrarOyna=true;
				}
			}
			else
			{
				t1.setText("");
				t2.setText("");
				t3.setText("");
				t4.setText("");
				t5.setText("");
				t6.setText("");
				
				l1.setText("");
				l2.setText("");
				l3.setText("");
				l4.setText("");
				l5.setText("");
				l6.setText("");
				l7.setText("");
				btn.setText("Oyna");
				tekrarOyna=false;
			}
			
			
			
		}
		
	};
	
	DocumentListener dl1=new DocumentListener() 
	{

		
		public void insertUpdate(DocumentEvent e) 
		{
			if(!t1.getText().matches("^[0]?[0]?") && !t1.getText().matches("^[0]?[1-9]") 
					&& !t1.getText().matches("[1-9][0-9]*") && !t1.getText().matches("") )
			{
	
				t1.requestFocus();
				t1.setCaretPosition(t1.getText().length()); 
				try {
					robot=new Robot();
				} catch (AWTException e1) {
					// TODO Auto-generated catch block
					
				}
				robot.keyPress(KeyEvent.VK_BACK_SPACE);
				robot.keyRelease(KeyEvent.VK_BACK_SPACE);
				
			}
			
			if(t1.getText().length()>0 && (Integer.valueOf(t1.getText())<14)==false) 
			{
				
					t1.requestFocus();
					t1.setCaretPosition(t1.getText().length()); 
					try {
						robot=new Robot();
					} catch (AWTException e1) {
						// TODO Auto-generated catch block
						
					}
					robot.keyPress(KeyEvent.VK_BACK_SPACE);
					robot.keyRelease(KeyEvent.VK_BACK_SPACE);
				
			}
			
				l1.setText("");
				l2.setText("");
				l3.setText("");
				l4.setText("");
				l5.setText("");
				l6.setText("");
				l7.setText("");
			
			
			
			
			
		}

		public void removeUpdate(DocumentEvent e) 
		{
			if(!t1.getText().matches("^[0]?[0]?") && !t1.getText().matches("^[0]?[1-9]") 
					&& !t1.getText().matches("[1-9][0-9]*") && !t1.getText().matches(""))
			{
	
				t1.requestFocus();
				t1.setCaretPosition(t1.getText().length()); 
				try {
					robot=new Robot();
				} catch (AWTException e1) {
					// TODO Auto-generated catch block
					
				}
				robot.keyPress(KeyEvent.VK_BACK_SPACE);
				robot.keyRelease(KeyEvent.VK_BACK_SPACE);
				
			}
			if(t1.getText().length()>0 && (Integer.valueOf(t1.getText())<14)==false) 
			{
				
					t1.requestFocus();
					t1.setCaretPosition(t1.getText().length()); 
					try {
						robot=new Robot();
					} catch (AWTException e1) {
						// TODO Auto-generated catch block
						
					}
					robot.keyPress(KeyEvent.VK_BACK_SPACE);
					robot.keyRelease(KeyEvent.VK_BACK_SPACE);
				
			}
			l1.setText("");
			l2.setText("");
			l3.setText("");
			l4.setText("");
			l5.setText("");
			l6.setText("");
			l7.setText("");
			
		}

		public void changedUpdate(DocumentEvent e) 
		{
			if(!t1.getText().matches("^[0]?[0]?") && !t1.getText().matches("^[0]?[1-9]") 
					&& !t1.getText().matches("[1-9][0-9]*") && !t1.getText().matches("") || (Integer.valueOf(t1.getText())<100)==false)
			{
	
				t1.requestFocus();
				t1.setCaretPosition(t1.getText().length()); 
				try {
					robot=new Robot();
				} catch (AWTException e1) {
					// TODO Auto-generated catch block
					
				}
				robot.keyPress(KeyEvent.VK_BACK_SPACE);
				robot.keyRelease(KeyEvent.VK_BACK_SPACE);
				
			}

			if(t1.getText().length()>0 && (Integer.valueOf(t1.getText())<14)==false) 
			{
				
					t1.requestFocus();
					t1.setCaretPosition(t1.getText().length()); 
					try {
						robot=new Robot();
					} catch (AWTException e1) {
						// TODO Auto-generated catch block
						
					}
					robot.keyPress(KeyEvent.VK_BACK_SPACE);
					robot.keyRelease(KeyEvent.VK_BACK_SPACE);
				
			}
			l1.setText("");
			l2.setText("");
			l3.setText("");
			l4.setText("");
			l5.setText("");
			l6.setText("");
			l7.setText("");
			
			
		}
	};
	
	DocumentListener dl2 = new DocumentListener() 
	{

		public void insertUpdate(DocumentEvent e) 
		{
			
			if(!t2.getText().matches("^[0]?[0]?") && !t2.getText().matches("^[0]?[1-9]") 
					&& !t2.getText().matches("[1-9][0-9]*") && !t2.getText().matches(""))
			{
	
				t2.requestFocus();
				t2.setCaretPosition(t2.getText().length()); 
				try {
					robot=new Robot();
				} catch (AWTException e1) {
					// TODO Auto-generated catch block
					
				}
				robot.keyPress(KeyEvent.VK_BACK_SPACE);
				robot.keyRelease(KeyEvent.VK_BACK_SPACE);
				
			}

			if(t2.getText().length()>0 && (Integer.valueOf(t2.getText())<14)==false) 
			{
				
					t2.requestFocus();
					t2.setCaretPosition(t2.getText().length()); 
					try {
						robot=new Robot();
					} catch (AWTException e1) {
						// TODO Auto-generated catch block
						
					}
					robot.keyPress(KeyEvent.VK_BACK_SPACE);
					robot.keyRelease(KeyEvent.VK_BACK_SPACE);
				
			}
			l1.setText("");
			l2.setText("");
			l3.setText("");
			l4.setText("");
			l5.setText("");
			l6.setText("");
			l7.setText("");
			
			
			
		}

		public void removeUpdate(DocumentEvent e) 
		{
			if(!t2.getText().matches("^[0]?[0]?") && !t2.getText().matches("^[0]?[1-9]") 
					&& !t2.getText().matches("[1-9][0-9]*") && !t2.getText().matches(""))
			{
	
				t2.requestFocus();
				t2.setCaretPosition(t2.getText().length()); 
				try {
					robot=new Robot();
				} catch (AWTException e1) {
					// TODO Auto-generated catch block
					
				}
				robot.keyPress(KeyEvent.VK_BACK_SPACE);
				robot.keyRelease(KeyEvent.VK_BACK_SPACE);
				
			}
			if(t2.getText().length()>0 && (Integer.valueOf(t2.getText())<14)==false) 
			{
				
					t2.requestFocus();
					t2.setCaretPosition(t2.getText().length()); 
					try {
						robot=new Robot();
					} catch (AWTException e1) {
						// TODO Auto-generated catch block
						
					}
					robot.keyPress(KeyEvent.VK_BACK_SPACE);
					robot.keyRelease(KeyEvent.VK_BACK_SPACE);
				
			}
			l1.setText("");
			l2.setText("");
			l3.setText("");
			l4.setText("");
			l5.setText("");
			l6.setText("");
			l7.setText("");
			
		}

		public void changedUpdate(DocumentEvent e) 
		{
			if(!t2.getText().matches("^[0]?[0]?") && !t2.getText().matches("^[0]?[1-9]") 
					&& !t2.getText().matches("[1-9][0-9]*") && !t2.getText().matches("") )
			{
	
				t2.requestFocus();
				t2.setCaretPosition(t2.getText().length()); 
				try {
					robot=new Robot();
				} catch (AWTException e1) {
					// TODO Auto-generated catch block
					
				}
				robot.keyPress(KeyEvent.VK_BACK_SPACE);
				robot.keyRelease(KeyEvent.VK_BACK_SPACE);
				
			}
			if(t2.getText().length()>0 && (Integer.valueOf(t2.getText())<14)==false) 
			{
				
					t2.requestFocus();
					t2.setCaretPosition(t2.getText().length()); 
					try {
						robot=new Robot();
					} catch (AWTException e1) {
						// TODO Auto-generated catch block
						
					}
					robot.keyPress(KeyEvent.VK_BACK_SPACE);
					robot.keyRelease(KeyEvent.VK_BACK_SPACE);
				
			}
			l1.setText("");
			l2.setText("");
			l3.setText("");
			l4.setText("");
			l5.setText("");
			l6.setText("");
			l7.setText("");
			
		}
	};
	
	DocumentListener dl3=new DocumentListener() 
	{

		public void insertUpdate(DocumentEvent e) 
		{
			if(!t3.getText().matches("^[0]?[0]?") && !t3.getText().matches("^[0]?[1-9]") 
					&& !t3.getText().matches("[1-9][0-9]*") && !t3.getText().matches(""))
			{
	
				t3.requestFocus();
				t3.setCaretPosition(t3.getText().length()); 
				try {
					robot=new Robot();
				} catch (AWTException e1) {
					// TODO Auto-generated catch block
					
				}
				robot.keyPress(KeyEvent.VK_BACK_SPACE);
				robot.keyRelease(KeyEvent.VK_BACK_SPACE);
				
			}
			if(t3.getText().length()>0 && (Integer.valueOf(t3.getText())<14)==false) 
			{
				
					t3.requestFocus();
					t3.setCaretPosition(t3.getText().length()); 
					try {
						robot=new Robot();
					} catch (AWTException e1) {
						// TODO Auto-generated catch block
						
					}
					robot.keyPress(KeyEvent.VK_BACK_SPACE);
					robot.keyRelease(KeyEvent.VK_BACK_SPACE);
				
			}
			l1.setText("");
			l2.setText("");
			l3.setText("");
			l4.setText("");
			l5.setText("");
			l6.setText("");
			l7.setText("");
			
			
			
		}

		public void removeUpdate(DocumentEvent e) 
		{
			if(!t3.getText().matches("^[0]?[0]?") && !t3.getText().matches("^[0]?[1-9]") 
					&& !t3.getText().matches("[1-9][0-9]*") && !t3.getText().matches(""))
			{
	
				t3.requestFocus();
				t3.setCaretPosition(t3.getText().length()); 
				try {
					robot=new Robot();
				} catch (AWTException e1) {
					// TODO Auto-generated catch block
					
				}
				robot.keyPress(KeyEvent.VK_BACK_SPACE);
				robot.keyRelease(KeyEvent.VK_BACK_SPACE);
				
			}
			if(t3.getText().length()>0 && (Integer.valueOf(t3.getText())<14)==false) 
			{
				
					t3.requestFocus();
					t3.setCaretPosition(t3.getText().length()); 
					try {
						robot=new Robot();
					} catch (AWTException e1) {
						// TODO Auto-generated catch block
						
					}
					robot.keyPress(KeyEvent.VK_BACK_SPACE);
					robot.keyRelease(KeyEvent.VK_BACK_SPACE);
				
			}
			l1.setText("");
			l2.setText("");
			l3.setText("");
			l4.setText("");
			l5.setText("");
			l6.setText("");
			l7.setText("");
		}

		public void changedUpdate(DocumentEvent e) 
		{
			if(!t3.getText().matches("^[0]?[0]?") && !t3.getText().matches("^[0]?[1-9]") 
					&& !t3.getText().matches("[1-9][0-9]*") && !t3.getText().matches(""))
			{
	
				t3.requestFocus();
				t3.setCaretPosition(t3.getText().length()); 
				try {
					robot=new Robot();
				} catch (AWTException e1) {
					// TODO Auto-generated catch block
					
				}
				robot.keyPress(KeyEvent.VK_BACK_SPACE);
				robot.keyRelease(KeyEvent.VK_BACK_SPACE);
				
			}
			if(t3.getText().length()>0 && (Integer.valueOf(t3.getText())<14)==false) 
			{
				
					t3.requestFocus();
					t3.setCaretPosition(t3.getText().length()); 
					try {
						robot=new Robot();
					} catch (AWTException e1) {
						// TODO Auto-generated catch block
						
					}
					robot.keyPress(KeyEvent.VK_BACK_SPACE);
					robot.keyRelease(KeyEvent.VK_BACK_SPACE);
				
			}
			l1.setText("");
			l2.setText("");
			l3.setText("");
			l4.setText("");
			l5.setText("");
			l6.setText("");
			l7.setText("");
			
			
		}
	};
	
	DocumentListener dl4=new DocumentListener() 
	{

		public void insertUpdate(DocumentEvent e) 
		{
			
			if(!t4.getText().matches("^[0]?[0]?") && !t4.getText().matches("^[0]?[1-9]") 
					&& !t4.getText().matches("[1-9][0-9]*") && !t4.getText().matches(""))
			{
	
				t4.requestFocus();
				t4.setCaretPosition(t4.getText().length()); 
				try {
					robot=new Robot();
				} catch (AWTException e1) {
					// TODO Auto-generated catch block
					
				}
				robot.keyPress(KeyEvent.VK_BACK_SPACE);
				robot.keyRelease(KeyEvent.VK_BACK_SPACE);
				
			}
			if(t4.getText().length()>0 && (Integer.valueOf(t4.getText())<14)==false) 
			{
				
					t4.requestFocus();
					t4.setCaretPosition(t4.getText().length()); 
					try {
						robot=new Robot();
					} catch (AWTException e1) {
						// TODO Auto-generated catch block
						
					}
					robot.keyPress(KeyEvent.VK_BACK_SPACE);
					robot.keyRelease(KeyEvent.VK_BACK_SPACE);
				
			}
			l1.setText("");
			l2.setText("");
			l3.setText("");
			l4.setText("");
			l5.setText("");
			l6.setText("");
			l7.setText("");
			
			
		}

		public void removeUpdate(DocumentEvent e) 
		{
			if(!t4.getText().matches("^[0]?[0]?") && !t4.getText().matches("^[0]?[1-9]") 
					&& !t4.getText().matches("[1-9][0-9]*") && !t4.getText().matches(""))
			{
	
				t4.requestFocus();
				t4.setCaretPosition(t4.getText().length()); 
				try {
					robot=new Robot();
				} catch (AWTException e1) {
					// TODO Auto-generated catch block
					
				}
				robot.keyPress(KeyEvent.VK_BACK_SPACE);
				robot.keyRelease(KeyEvent.VK_BACK_SPACE);
				
			}
			if(t4.getText().length()>0 && (Integer.valueOf(t4.getText())<14)==false) 
			{
				
					t4.requestFocus();
					t4.setCaretPosition(t4.getText().length()); 
					try {
						robot=new Robot();
					} catch (AWTException e1) {
						// TODO Auto-generated catch block
						
					}
					robot.keyPress(KeyEvent.VK_BACK_SPACE);
					robot.keyRelease(KeyEvent.VK_BACK_SPACE);
				
			}
			l1.setText("");
			l2.setText("");
			l3.setText("");
			l4.setText("");
			l5.setText("");
			l6.setText("");
			l7.setText("");
			
		}

		public void changedUpdate(DocumentEvent e) 
		{
			if(!t4.getText().matches("^[0]?[0]?") && !t4.getText().matches("^[0]?[1-9]") 
					&& !t4.getText().matches("[1-9][0-9]*") && !t4.getText().matches(""))
			{
	
				t4.requestFocus();
				t4.setCaretPosition(t4.getText().length()); 
				try {
					robot=new Robot();
				} catch (AWTException e1) {
					// TODO Auto-generated catch block
					
				}
				robot.keyPress(KeyEvent.VK_BACK_SPACE);
				robot.keyRelease(KeyEvent.VK_BACK_SPACE);
				
			}
			if(t4.getText().length()>0 && (Integer.valueOf(t4.getText())<14)==false) 
			{
				
					t4.requestFocus();
					t4.setCaretPosition(t4.getText().length()); 
					try {
						robot=new Robot();
					} catch (AWTException e1) {
						// TODO Auto-generated catch block
						
					}
					robot.keyPress(KeyEvent.VK_BACK_SPACE);
					robot.keyRelease(KeyEvent.VK_BACK_SPACE);
				
			}
			l1.setText("");
			l2.setText("");
			l3.setText("");
			l4.setText("");
			l5.setText("");
			l6.setText("");
			l7.setText("");
			
			
		}
	};
	
	DocumentListener dl5=new DocumentListener() 
	{

		public void insertUpdate(DocumentEvent e) 
		{
			
			if(!t5.getText().matches("^[0]?[0]?") && !t5.getText().matches("^[0]?[1-9]") 
					&& !t5.getText().matches("[1-9][0-9]*") && !t5.getText().matches(""))
			{
	
				t5.requestFocus();
				t5.setCaretPosition(t5.getText().length()); 
				try {
					robot=new Robot();
				} catch (AWTException e1) {
					// TODO Auto-generated catch block
					
				}
				robot.keyPress(KeyEvent.VK_BACK_SPACE);
				robot.keyRelease(KeyEvent.VK_BACK_SPACE);
				
			}
			if(t5.getText().length()>0 && (Integer.valueOf(t5.getText())<14)==false) 
			{
				
					t5.requestFocus();
					t5.setCaretPosition(t5.getText().length()); 
					try {
						robot=new Robot();
					} catch (AWTException e1) {
						// TODO Auto-generated catch block
						
					}
					robot.keyPress(KeyEvent.VK_BACK_SPACE);
					robot.keyRelease(KeyEvent.VK_BACK_SPACE);
				
			}
			l1.setText("");
			l2.setText("");
			l3.setText("");
			l4.setText("");
			l5.setText("");
			l6.setText("");
			l7.setText("");
			
			
		}

		public void removeUpdate(DocumentEvent e) 
		{
			if(!t5.getText().matches("^[0]?[0]?") && !t5.getText().matches("^[0]?[1-9]") 
					&& !t5.getText().matches("[1-9][0-9]*") && !t5.getText().matches(""))
			{
	
				t5.requestFocus();
				t5.setCaretPosition(t5.getText().length()); 
				try {
					robot=new Robot();
				} catch (AWTException e1) {
					// TODO Auto-generated catch block
					
				}
				robot.keyPress(KeyEvent.VK_BACK_SPACE);
				robot.keyRelease(KeyEvent.VK_BACK_SPACE);
				
			}
			if(t5.getText().length()>0 && (Integer.valueOf(t5.getText())<14)==false) 
			{
				
					t5.requestFocus();
					t5.setCaretPosition(t5.getText().length()); 
					try {
						robot=new Robot();
					} catch (AWTException e1) {
						// TODO Auto-generated catch block
						
					}
					robot.keyPress(KeyEvent.VK_BACK_SPACE);
					robot.keyRelease(KeyEvent.VK_BACK_SPACE);
				
			}
			l1.setText("");
			l2.setText("");
			l3.setText("");
			l4.setText("");
			l5.setText("");
			l6.setText("");
			l7.setText("");
			
		}

		public void changedUpdate(DocumentEvent e) 
		{
			if(!t5.getText().matches("^[0]?[0]?") && !t5.getText().matches("^[0]?[1-9]") 
					&& !t5.getText().matches("[1-9][0-9]*") && !t5.getText().matches(""))
			{
	
				t5.requestFocus();
				t5.setCaretPosition(t5.getText().length()); 
				try {
					robot=new Robot();
				} catch (AWTException e1) {
					// TODO Auto-generated catch block
					
				}
				robot.keyPress(KeyEvent.VK_BACK_SPACE);
				robot.keyRelease(KeyEvent.VK_BACK_SPACE);
				
			}
			if(t5.getText().length()>0 && (Integer.valueOf(t5.getText())<14)==false) 
			{
				
					t5.requestFocus();
					t5.setCaretPosition(t5.getText().length()); 
					try {
						robot=new Robot();
					} catch (AWTException e1) {
						// TODO Auto-generated catch block
						
					}
					robot.keyPress(KeyEvent.VK_BACK_SPACE);
					robot.keyRelease(KeyEvent.VK_BACK_SPACE);
				
			}
			l1.setText("");
			l2.setText("");
			l3.setText("");
			l4.setText("");
			l5.setText("");
			l6.setText("");
			l7.setText("");
			
			
		}
	};
	
	DocumentListener dl6=new DocumentListener() 
	{

		public void insertUpdate(DocumentEvent e) 
		{
			
			if(!t6.getText().matches("^[0]?[0]?") && !t6.getText().matches("^[0]?[1-9]") 
					&& !t6.getText().matches("[1-9][0-9]*") && !t6.getText().matches(""))
			{
	
				t6.requestFocus();
				t6.setCaretPosition(t6.getText().length()); 
				try {
					robot=new Robot();
				} catch (AWTException e1) {
					// TODO Auto-generated catch block
					
				}
				robot.keyPress(KeyEvent.VK_BACK_SPACE);
				robot.keyRelease(KeyEvent.VK_BACK_SPACE);
				
			}
			if(t6.getText().length()>0 && (Integer.valueOf(t6.getText())<14)==false) 
			{
				
					t6.requestFocus();
					t6.setCaretPosition(t6.getText().length()); 
					try {
						robot=new Robot();
					} catch (AWTException e1) {
						// TODO Auto-generated catch block
						
					}
					robot.keyPress(KeyEvent.VK_BACK_SPACE);
					robot.keyRelease(KeyEvent.VK_BACK_SPACE);
				
			}
			l1.setText("");
			l2.setText("");
			l3.setText("");
			l4.setText("");
			l5.setText("");
			l6.setText("");
			l7.setText("");
			
			
		}

		public void removeUpdate(DocumentEvent e) 
		{
			if(!t6.getText().matches("^[0]?[0]?") && !t6.getText().matches("^[0]?[1-9]") 
					&& !t6.getText().matches("[1-9][0-9]*") && !t6.getText().matches(""))
			{
	
				t6.requestFocus();
				t6.setCaretPosition(t6.getText().length()); 
				try {
					robot=new Robot();
				} catch (AWTException e1) {
					// TODO Auto-generated catch block
					
				}
				robot.keyPress(KeyEvent.VK_BACK_SPACE);
				robot.keyRelease(KeyEvent.VK_BACK_SPACE);
				
			}
			if(t6.getText().length()>0 && (Integer.valueOf(t6.getText())<14)==false) 
			{
				
					t6.requestFocus();
					t6.setCaretPosition(t6.getText().length()); 
					try {
						robot=new Robot();
					} catch (AWTException e1) {
						// TODO Auto-generated catch block
						
					}
					robot.keyPress(KeyEvent.VK_BACK_SPACE);
					robot.keyRelease(KeyEvent.VK_BACK_SPACE);
				
			}
			l1.setText("");
			l2.setText("");
			l3.setText("");
			l4.setText("");
			l5.setText("");
			l6.setText("");
			l7.setText("");
			
		}

		public void changedUpdate(DocumentEvent e) 
		{
			if(!t6.getText().matches("^[0]?[0]?") && !t6.getText().matches("^[0]?[1-9]") 
					&& !t6.getText().matches("[1-9][0-9]*") && !t6.getText().matches(""))
			{
	
				t6.requestFocus();
				t6.setCaretPosition(t6.getText().length()); 
				try {
					robot=new Robot();
				} catch (AWTException e1) {
					// TODO Auto-generated catch block
					
				}
				robot.keyPress(KeyEvent.VK_BACK_SPACE);
				robot.keyRelease(KeyEvent.VK_BACK_SPACE);
				
			}
			if(t6.getText().length()>0 && (Integer.valueOf(t6.getText())<14)==false) 
			{
				
					t6.requestFocus();
					t6.setCaretPosition(t6.getText().length()); 
					try {
						robot=new Robot();
					} catch (AWTException e1) {
						// TODO Auto-generated catch block
						
					}
					robot.keyPress(KeyEvent.VK_BACK_SPACE);
					robot.keyRelease(KeyEvent.VK_BACK_SPACE);
				
			}
			l1.setText("");
			l2.setText("");
			l3.setText("");
			l4.setText("");
			l5.setText("");
			l6.setText("");
			l7.setText("");
			
			
		}
	};
	
	
	


}
