package FIT_0204_Shelestova.Plotter;

import javax.swing.*;

import static java.lang.StrictMath.pow;

public class Main {
    public static void main(String[] args){
        if (args.length > 0){
            CommandLineMode commandLineMode = new CommandLineMode(args[0],
                    new PlotterController());
        }
        else{
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    PlotterGUI gui = new PlotterGUI(new PlotterController());
                    gui.setVisible(true);
                }
            });
        }
    }
}
