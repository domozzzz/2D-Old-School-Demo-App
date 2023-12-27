package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import shader.Plasma;

public class SidePanel extends JPanel{
	
	private int w, h;
	
	JPanel plasmaPanel;
	JPanel demoPanel;

	private static final long serialVersionUID = 1L;

	public SidePanel(int w, int h) {
		
		this.w = w;
		this.h = h;
		this.setSize(new Dimension(w, h));
		
		addComponents();
		
		plasmaSettings();
				
		switch (Game.DEMO) {
		case "Plasma" -> plasmaPanel.setVisible(true);
		case "Fire" -> plasmaPanel.setVisible(false);
		}
	}
	
	private void FireSettings() {
		this.remove(plasmaPanel);
		this.revalidate();
	}
	
	private void plasmaSettings() {
		
		
		plasmaPanel = new JPanel();
		plasmaPanel.add(new JLabel("ColorMode: "));
		
		JCheckBox rBox = new JCheckBox("R");
		JCheckBox gBox = new JCheckBox("G");
		JCheckBox bBox = new JCheckBox("B");
		
		//rBox.addItemListener() => {;
		
		plasmaPanel.add(rBox);
		plasmaPanel.add(gBox);
		plasmaPanel.add(bBox);
		
		JSlider slider = new JSlider(0, 12, 1);
		
		slider.setPaintTrack(true);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
	        
		slider.setMajorTickSpacing(12);
        slider.setMinorTickSpacing(1); 

		slider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				Plasma.speed = slider.getValue();
			}
		});
		plasmaPanel.add(slider);
		this.add(plasmaPanel);
	}
	


	private void addComponents() {
		demoSelect();
	}
	
	private void demoSelect() {
		demoPanel = new JPanel();
		//demoPanel.setBackground(Color.RED);
		
		demoPanel.add(new JLabel("Demo: "));
		
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
		for (int i = 0; i < Game.values.length; i++) {
			model.addElement(Game.values[i]);
		}

		JComboBox<String> comboBox = new JComboBox<>(model);
		comboBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	Game.DEMO = (String) comboBox.getSelectedItem();
		    }
		});
		demoPanel.add(comboBox);
		this.add(demoPanel);
	}
}
