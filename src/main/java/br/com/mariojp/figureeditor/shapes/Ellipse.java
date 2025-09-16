package br.com.mariojp.figureeditor.shapes;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class Ellipse implements ShapeRenderer {
    @Override
    public Shape render(int x, int y, int size) {
        return new Ellipse2D.Double(x, y, size, size);
    }
}
