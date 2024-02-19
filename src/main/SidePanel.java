package main;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SidePanel extends JPanel{
		
	private App app;
	
	private JLabel demoLabel;
	private JLabel patternLabel;
	private JLabel colorLabel;
	private JLabel speedLabel;
	
	private JSlider slider;
	private JComboBox<String> patternComboBox;
	
	
	private JCheckBox rBox;
	private JCheckBox gBox;
	private JCheckBox bBox;

	private static final long serialVersionUID = 1L;

	public SidePanel(App app, int w, int h) {
		this.app = app;
		
		this.setSize(new Dimension(w, h));
		addthis();
	}

	private void addthis() {
		
		//init pannel
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		addDemoSelect();
		addPatternSelect();
		addRGBToggles();
		addSpeedSlider();
	}
		
	private void addDemoSelect() {
		//demo select
		
		demoLabel = new JLabel("Demo: ");
		this.add(demoLabel);
		
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
		for (int i = 0; i < App.values.length; i++) {
			model.addElement(App.values[i]);
		}

		JComboBox<String> comboBox = new JComboBox<>(model);
		comboBox.setMaximumSize(new Dimension(150, 25));
		
		comboBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	App.DEMO = (String) comboBox.getSelectedItem();
		    	
				switch (App.DEMO) {
					case "None":
						hidePatternSelect();
						hideRGBSelect();
						hideSlider();
						break;
					case "Plasma":
						showPatternSelect();
						showRGBSelect();
						showSlider();
						break;
						
					case "Fire":
						hidePatternSelect();
						hideRGBSelect();
						hideSlider();
						break;
						
					case "Noise":
						hidePatternSelect();
						hideRGBSelect();
						hideSlider();
						break;
						
					case "Tunnel":
						hidePatternSelect();
						hideRGBSelect();
						showSlider();
						
						break;
					
					case "XOR":
						showPatternSelect();
						showRGBSelect();
						hideSlider();
						break;
				}
				
		    }
		});
		this.add(comboBox);
	}
	
	private void showRGBSelect() {
		colorLabel.setVisible(true);
		rBox.setVisible(true);
		gBox.setVisible(true);
		bBox.setVisible(true);
	}
	
	private void hideRGBSelect() {
		colorLabel.setVisible(false);
		rBox.setVisible(false);
		gBox.setVisible(false);
		bBox.setVisible(false);
	}
	
	private void showSlider() {
		speedLabel.setVisible(true);
		slider.setVisible(true);	
	}
	
	private void hideSlider() {
		speedLabel.setVisible(false);
		slider.setVisible(false);
	}
	
	private void showPatternSelect() {
		patternLabel.setVisible(true);
		patternComboBox.setVisible(true);
		
	}
	
	private void hidePatternSelect() {
		patternLabel.setVisible(false);	
		patternComboBox.setVisible(false);
		
	}
		
	private void addPatternSelect() {
		//pattern select
		patternLabel = new JLabel("Pattern: ");
		this.add(patternLabel);
		
		DefaultComboBoxModel<String> patternModel = new DefaultComboBoxModel<>();
		for (int i = 1; i <= 4; i++) {
			patternModel.addElement(String.valueOf(i));
		}

		patternComboBox = new JComboBox<>(patternModel);
		patternComboBox.setMaximumSize(new Dimension(150, 25));
		
		patternComboBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	Settings.PATTERN = Integer.valueOf((String) patternComboBox.getSelectedItem());
		    	app.reset();
		    }
		});
		this.add(patternComboBox);
		hidePatternSelect();
	}
		
	private void addRGBToggles() {

		colorLabel = new JLabel("Color Mode: ");
		this.add(colorLabel);
		
		rBox = new JCheckBox("R", true);
		gBox = new JCheckBox("G", true);
		bBox = new JCheckBox("B", true);
		
		//RED
		rBox.addItemListener (new ItemListener () {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {
					Settings.RED = true;
				} else {
					Settings.RED = false;
				}
				app.reset();
			}
		});
		
		//GREEN
		gBox.addItemListener (new ItemListener () {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {
					Settings.GREEN = true;
				} else {
					Settings.GREEN = false;
				}
				app.reset();
			}
		});
		
		//BLUE
		bBox.addItemListener (new ItemListener () {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {
					Settings.BLUE = true;
				} else {
					Settings.BLUE = false;
				}
				app.reset();
			}
		});
		
		this.add(rBox);
		this.add(gBox);
		this.add(bBox);
		hideRGBSelect();
	}
	
	private void addSpeedSlider() {
		
		speedLabel = new JLabel("Speed: ");
		this.add(speedLabel);
		
		slider = new JSlider(0, 12, 1);
		
		slider.setPaintTrack(true);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
	        
		slider.setMajorTickSpacing(12);
	    slider.setMinorTickSpacing(1); 
	
		slider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				Settings.SPEED = slider.getValue();
			}
		});
		this.add(slider);
		hideSlider();
	}
}
