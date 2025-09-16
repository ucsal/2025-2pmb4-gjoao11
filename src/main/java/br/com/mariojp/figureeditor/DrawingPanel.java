
package br.com.mariojp.figureeditor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import br.com.mariojp.figureeditor.shapes.ShapeRenderer;

class DrawingPanel extends JPanel {
    private static class ColoredShape {
        final Shape shape;
        final Color color;

        ColoredShape(Shape shape, Color color) {
            this.shape = shape;
            this.color = color;
        }
    }

    private static final long serialVersionUID = 1L;
    private static final int DEFAULT_SIZE = 60;
    private final List<ColoredShape> shapes = new ArrayList<>();
    private Point startDrag = null;
    private ShapeRenderer selectedShapeRenderer = null;
    private Color selectedColor = new Color(30, 144, 255);

    DrawingPanel() {

        setBackground(Color.WHITE);
        setOpaque(true);
        setDoubleBuffered(true);

        var mouse = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1 && startDrag == null) {
                    int size = Math.max(Math.min(DEFAULT_SIZE, DEFAULT_SIZE), 10);
                    Shape s = selectedShapeRenderer.render(e.getPoint().x, e.getPoint().y, size);
                    // return new Rectangle2D.Double(e.getPoint().x, e.getPoint().y,
                    // Math.max(DEFAULT_SIZE, 10), Math.max(DEFAULT_SIZE, 10));
                    shapes.add(new ColoredShape(s, selectedColor));
                    repaint();
                }
            }
        };
        addMouseListener(mouse);
        addMouseMotionListener(mouse);

    }

    public void setSelectedShape(ShapeRenderer shapeRenderer) {
        selectedShapeRenderer = shapeRenderer;
    }

    public Color getSelectedColor() {
        return selectedColor;
    }

    public void setSelectedColor(Color color) {
        selectedColor = color;
    }

    void clear() {
        shapes.clear();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (ColoredShape s : shapes) {
            g2.setColor(s.color);
            g2.fill(s.shape);
            g2.setColor(new Color(0, 0, 0, 70));
            g2.setStroke(new BasicStroke(1.2f));
            g2.draw(s.shape);
        }

        g2.dispose();
    }

}
