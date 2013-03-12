package FIT_0204_Shelestova.Rectangle;

import FIT_0204_Shelestova.Common.CommandLineMode;

import javax.swing.*;

public class Main {
    public static void main(String[] args){
        if (args.length > 0){
            CommandLineMode commandLineMode = new CommandLineMode(args[0],
                    new RectangleController());
        }
        else{
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                RectangleGUI gui = new RectangleGUI(new RectangleController());
                gui.setVisible(true);
            }
        });
        }
    }
}
