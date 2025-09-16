package br.com.mariojp.figureeditor;

import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import br.com.mariojp.figureeditor.shapes.Ellipse;
import br.com.mariojp.figureeditor.shapes.Rectangle;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }

            JFrame frame = new JFrame("Figure Editor — Clique para inserir figuras");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            DrawingPanel panel = new DrawingPanel();

            JMenuBar menuBar = createMenuBar(panel);
            frame.setJMenuBar(menuBar);

            frame.setLayout(new BorderLayout());
            frame.add(panel, BorderLayout.CENTER);

            frame.setSize(900, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    private static JMenuBar createMenuBar(DrawingPanel panel) {
        JMenuBar menuBar = new JMenuBar();

        JMenu shapesMenu = new JMenu("Formas");

        ButtonGroup shapesGroup = new ButtonGroup();

        Ellipse ellipseRenderer = new Ellipse();
        Rectangle rectangleRenderer = new Rectangle();

        JRadioButtonMenuItem ellipseItem = new JRadioButtonMenuItem("Elipse");
        ellipseItem.addActionListener(e -> {
            panel.setSelectedShape(ellipseRenderer);
        });

        JRadioButtonMenuItem rectangleItem = new JRadioButtonMenuItem("Retângulo");
        rectangleItem.addActionListener(e -> {
            panel.setSelectedShape(rectangleRenderer);
        });

        shapesGroup.add(ellipseItem);
        shapesGroup.add(rectangleItem);

        ellipseItem.setSelected(true);
        panel.setSelectedShape(ellipseRenderer);

        shapesMenu.add(ellipseItem);
        shapesMenu.add(rectangleItem);

        menuBar.add(shapesMenu);

        return menuBar;
    }
}
