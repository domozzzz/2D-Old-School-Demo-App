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
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SidePanel extends JPanel{
		
	JPanel sidePanel;
	App app;

	private static final long serialVersionUID = 1L;

	public SidePanel(App app, int w, int h) {
		
		this.setSize(new Dimension(w, h));
		addSidePanel();
		this.app = app;
		
	}

	private void addSidePanel() {

		//init
		sidePanel = new JPanel();
		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
		
		//demo select
		sidePanel.add(new JLabel("Demo: "));
		
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
		for (int i = 0; i < App.values.length; i++) {
			model.addElement(App.values[i]);
		}

		JComboBox<String> comboBox = new JComboBox<>(model);
		
		comboBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	App.DEMO = (String) comboBox.getSelectedItem();
		    }
		});
		sidePanel.add(comboBox);
		
		//pattern select
		sidePanel.add(new JLabel("Pattern: "));
		
		DefaultComboBoxModel<String> patternModel = new DefaultComboBoxModel<>();
		for (int i = 1; i <= 4; i++) {
			patternModel.addElement(String.valueOf(i));
		}

		JComboBox<String> patternComboBox = new JComboBox<>(patternModel);
		
		patternComboBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	Settings.PATTERN = Integer.valueOf((String) patternComboBox.getSelectedItem());
		    	app.reset();
		    }
		});
		sidePanel.add(patternComboBox);

		//colors
		
		//sidePanel.add(new JSeparator(JSeparator.VERTICAL));
	
		sidePanel.add(new JLabel("ColorMode: "));
		
		JCheckBox rBox = new JCheckBox("R", true);
		JCheckBox gBox = new JCheckBox("G", true);
		JCheckBox bBox = new JCheckBox("B", true);
		
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
		
		sidePanel.add(rBox);
		sidePanel.add(gBox);
		sidePanel.add(bBox);
		
		JSlider slider = new JSlider(0, 12, 1);
		
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
		sidePanel.add(slider);
		this.add(sidePanel);
	}
}
