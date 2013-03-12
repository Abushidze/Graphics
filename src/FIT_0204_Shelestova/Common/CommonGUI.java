package FIT_0204_Shelestova.Common;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public abstract class CommonGUI extends JFrame{
    protected Controller controller;
    protected DrawPanel drawPanel;
    protected Canvas canvas;
    protected ControlPanel controlPanel;

    private String title = "Graphics";
    protected JPanel panel = new JPanel(new GridBagLayout());
    protected int width = 800;
    protected int height = 500;

    private JMenuBar menuBar = new JMenuBar();
    private JMenu menu = new JMenu("File");
    private JMenuItem loadItem = new JMenuItem("load from ...");
    private JMenuItem saveItem = new JMenuItem("save");
    private JMenuItem saveAsItem = new JMenuItem("save as ...");
    private File propertyFile;


    public CommonGUI(Controller controller){
        this.controller = controller;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(title);

        add(panel);

        createMenu();
        createWidgets();

        setHeights();
        addWidgetsToGUI();

        this.pack();
    }


    protected void createWidgets(){
        drawPanel = new DrawPanel(this);
        canvas = new Canvas(this, controller);
        controlPanel = new ControlPanel(this);

    }
    protected void addWidgetsToGUI(){
        addWidgetsToControlPanel();
        addWidgetToDrawPanel();

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        panel.add(drawPanel, gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.0;
        panel.add(controlPanel, gbc);
    }
    protected void setHeights(){

    }
    protected void addWidgetsToControlPanel() {

    }
    private void addWidgetToDrawPanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        drawPanel.add(canvas, gbc);
    }

    // for calling fillMemory in drawPanel
    public Controller getController() {
        return controller;
    }
    private void createMenu(){
        loadItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fc = new JFileChooser();
                int returnVal = fc.showOpenDialog(panel);
                if (returnVal == JFileChooser.APPROVE_OPTION){
                    propertyFile = fc.getSelectedFile();
                    loadProperties();
                }
            }
        });
        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveProperties();
            }
        });
        saveAsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fc = new JFileChooser();
                int returnVal = fc.showOpenDialog(panel);
                if (returnVal == JFileChooser.APPROVE_OPTION){
                    propertyFile = fc.getSelectedFile();
                    saveProperties();
                }
            }
        });

        menu.add(loadItem);
        menu.add(saveItem);
        menu.add(saveAsItem);
        menuBar.add(menu);

        setJMenuBar(menuBar);


    }

    private void loadProperties() {
        try {
            controller.loadPropertiesFromFile(propertyFile);
        } catch (Exception e){
            JOptionPane.showMessageDialog(this,
                    "Can't load properties.",
                    "Properties warning",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
    private void saveProperties() {
        try{
            controller.storePropertiesToFile(propertyFile);
        } catch (Exception e){
            JOptionPane.showMessageDialog(this,
                    "Can't save properties.",
                    "Properties warning",
                    JOptionPane.WARNING_MESSAGE);

        }
    }
    protected int getFullHeight(JComponent component){
        return component.getMinimumSize().height +
                component.getInsets().top +
                component.getInsets().bottom;
    }

    public void widgetChanged(JComponent widget){

    }
}
