package edu.project4.render;

import edu.project4.models.FractalImage;
import edu.project4.transformation.Variation;
import java.util.List;

public interface Render {
    FractalImage render(FractalImage canvas, List<Variation> variations, int symmetry, int samples, short iterPerSample)
        throws InterruptedException;
}
