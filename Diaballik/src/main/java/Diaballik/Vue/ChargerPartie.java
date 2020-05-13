package Diaballik.Vue;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ChargerPartie extends JPanel {
	public ChargerPartie(JFrame parent) {
		String data[][] = { { "101", "Amit", "670000" }, { "102", "Jai", "780000" }, { "101", "Sachin", "700000" } };
		String column[] = { "ID", "NAME", "SALARY" };
		JTable jt = new JTable(data, column);
		jt.setBounds(30, 40, 200, 300);
		JScrollPane sp = new JScrollPane(jt);
		this.add(sp);
		parent.add(this);
	}
}