package sdmcet.cse.oop;//in the package sdmcet.cse.oop

//importing various package
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

//Exception class for IA Exception
class IAException extends Throwable {
	public String toString() {
		return "Invalid IA marks.";
	}
}

//Exception class for CTA Exception
class CTAException extends Throwable {
	public String toString() {
		return "Invalid CTA Marks";
	}
}

//Exception class for CIE Exception
class CIEException extends Throwable {
	public String toString() {

		return "Student Detained.Student not permitted to attend SEE";
	}
}

//Exception class for SEE Exception
class SEEException extends Throwable {
	public String toString() {
		return "F Grade. Student has failed.";
	}
}

//class for gradecalculator
class GradeCalculator extends JFrame implements ActionListener {
	Container contentPane;
	JButton b;
	JLabel lt, lg, lG, l1, l2, l3, l4, l5, l6;
	JTextField t1, t2, t3, t4, t5;
	JPanel p1, p2, p3, p4, p5, p6, p7, p8, p9;

	public GradeCalculator(String title) {
		super(title);
		contentPane = this.getContentPane();

		// instantiating panels and textfields
		b = new JButton("Calculate");
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		p4 = new JPanel();
		p5 = new JPanel();
		p6 = new JPanel();
		p7 = new JPanel();
		p8 = new JPanel();
		p9 = new JPanel();
		lt = new JLabel("Total Marks:");
		lg = new JLabel("Grade:");
		lG = new JLabel("Grade Calculator");
		l1 = new JLabel("Enter IA-1 Marks-");
		l2 = new JLabel("Enter IA-2 Marks-");
		l3 = new JLabel("Enter IA-3 Marks-");
		l4 = new JLabel("Enter CTA Marks-");
		l5 = new JLabel("Enter SEE Marks-");
		l6 = new JLabel("Note: If student is absent, Enter marks as 0");
		t1 = new JTextField(5);
		t2 = new JTextField(5);
		t3 = new JTextField(5);
		t4 = new JTextField(5);
		t5 = new JTextField(5);

		b.addActionListener(this);
		p1.add(lG);

		p2.add(l1);
		p2.add(t1);
		p3.add(l2);
		p3.add(t2);
		p4.add(l3);
		p4.add(t3);
		p5.add(l4);
		p5.add(t4);
		p6.add(l5);
		p6.add(t5);
		
		p4.add(p3);
		p7.add(b);

		p9.add(l6);

		// adding border space
		Border border = BorderFactory.createBevelBorder(2);
		lt.setBorder(border);
		p8.add(lt);
		p8.add(lg);

		// using grid layout to align
		this.setLayout(new GridLayout(9, 0));
		contentPane.add(p1);
		contentPane.add(p2);
		contentPane.add(p3);
		contentPane.add(p4);
		contentPane.add(p5);
		contentPane.add(p6);
		contentPane.add(p7);
		contentPane.add(p8);
		contentPane.add(p9);

	}

	public void actionPerformed(ActionEvent e) {
		int c = calculate();
		if (c != -1) {
			lt.setText("Total marks is:" + c);
			lg.setText("Grade:"+grade(c));
		} else {
			lt.setText(" ");
			lg.setText(" ");
		}
	}

	// using class "calculate" to handle exceptions
	int calculate() throws NumberFormatException {
		int ia1 = 0, ia2 = 0, ia3 = 0, cta = 0, see = 0, cie = 0;
		try {

			ia1 = Integer.parseInt(t1.getText());
			ia2 = Integer.parseInt(t2.getText());
			ia3 = Integer.parseInt(t3.getText());
			cta = Integer.parseInt(t4.getText());
			see = Integer.parseInt(t5.getText());
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(this, "IA marks should be <=20.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
			return -1;
		}
		if (ia1 < 0 || ia1 > 20 || ia2 < 0 || ia2 > 20 || ia3 < 0 || ia3 > 20) {
			JOptionPane.showMessageDialog(this, "Invalid IA marks", "Invalid Input", JOptionPane.ERROR_MESSAGE);
			return -1;
		}

		if (cta < 0 || cta > 10) {
			JOptionPane.showMessageDialog(this, "Invalid CTA marks", "Invalid Input", JOptionPane.ERROR_MESSAGE);
			return -1;
		}

		else {

			int minimum = ia1;
			if (ia2 < minimum)
				minimum = ia2;
			else if (ia3 < minimum)
				minimum = ia3;
			else
				minimum = ia1;
			cie = ia1 + ia2 + ia3 - minimum + cta;
		}
		if (cie < 20) {
			JOptionPane.showMessageDialog(this, "Student is Detained", "Error", JOptionPane.ERROR_MESSAGE);
			return -1;
		} else {
			if (see < 38 ) {
				JOptionPane.showMessageDialog(this, "F grade", "Warning", JOptionPane.ERROR_MESSAGE);
				return -1;
			}
			if (see > 100 ) {
				JOptionPane.showMessageDialog(this, "Invalid SEE marks", "Invalid Input", JOptionPane.ERROR_MESSAGE);
				return -1;
			}

			if (see == 38 || see == 39)
				see = 40;
			if (see % 2 == 0)
				see = see / 2;
			else
				see = (see + 1) / 2;
			int total = cie + see;
			return total;
		}
	}

	String grade(int a) {
		if (a <= 100 && a >= 90)
			return "S";
		else if (a < 90 && a >= 80)
			return "A";
		else if (a < 80 && a >= 70)
			return "B";
		else if (a < 70 && a >= 60)
			return "C";
		else if (a < 60 && a >= 50)
			return "D";
		else if (a < 50 && a >= 40)
			return "E";
		else
			return "F";

	}
}

//class containing main method
public class GradeCalculatorDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame f = new GradeCalculator("Students Grading System");
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setBounds(100, 100, 400, 300);
		f.setVisible(true);
	}

}