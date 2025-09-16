package br.com.mariojp.figureeditor.shapes;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class Rectangle implements ShapeRenderer {
    @Override
    public Shape render(int x, int y, int size) {
        return new Rectangle2D.Double(x, y, size, size);
    }
}
