import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

@SuppressWarnings("serial")
public class DrawGraph extends JPanel{
    private static final int PREF_W = 600;
    private static final int PREF_H = 250;
    private static final int BORDER_GAP = 30;
    private static final Stroke GRAPH_STROKE = new BasicStroke(1f);//line width
    private static final int Y_HATCH_CNT = 10;//y axis gradations
    private static final int HATCH_WIDTH = 4;

    private final int minValue;
    private final int maxValue;//dictates the scale to which we plot
    private final Color graphColor;//line color
    private int maxPlotValues;
    private LinkedList<Double> plotValues;

    public DrawGraph(int minValue, int maxValue, Color graphColor, int maxPlotValues) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.graphColor = graphColor;
        this.maxPlotValues = maxPlotValues;
        this.plotValues = new LinkedList<>();
    }

    public void addPlotValue(double plotValue) {
        while (plotValues.size() > maxPlotValues) {
            plotValues.removeFirst();
        }
        plotValues.addLast(plotValue);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - 2 * BORDER_GAP) / (maxPlotValues - 1);
        double yScale = ((double) getHeight() - 2 * BORDER_GAP) / (maxValue - minValue);

        List<Point> graphPoints = new ArrayList<Point>();

        for (int i = 0; i < plotValues.size(); i++) {
            int x1 = (int) (i * xScale + BORDER_GAP);
            int y1 = (int) ((maxValue - plotValues.get(i)) * yScale + BORDER_GAP);
            graphPoints.add(new Point(x1, y1));
        }

        // create x and y axes
        g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, BORDER_GAP, BORDER_GAP);
        g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, getWidth() - BORDER_GAP, getHeight() - BORDER_GAP);

        // create hatch marks for y axis.
        for (int i = 0; i < Y_HATCH_CNT; i++) {
            int x0 = BORDER_GAP;
            int x1 = BORDER_GAP - HATCH_WIDTH;
            int y0 = getHeight() - (((i + 1) * (getHeight() - BORDER_GAP * 2)) / Y_HATCH_CNT + BORDER_GAP);
            int y1 = y0;
            g2.drawLine(x0, y0, x1, y1);
        }

        // and for x axis
        for (int i = 0; i < maxPlotValues - 1; i++) {
            int x0 = (i + 1) * (getWidth() - BORDER_GAP * 2) / (maxPlotValues - 1) + BORDER_GAP;
            int x1 = x0;
            int y0 = getHeight() - BORDER_GAP;
            int y1 = y0 + HATCH_WIDTH;
            g2.drawLine(x0, y0, x1, y1);
        }

        Stroke oldStroke = g2.getStroke();
        g2.setColor(graphColor);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }
        g2.setStroke(oldStroke);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

    private static void createAndShowGui() {
        LinkedList<Integer> values = new LinkedList<>();
        Random random = new Random();
        int maxDataPoints = 16;
        int maxValue = 20;
        for (int i = 0; i < maxDataPoints ; i++) {
            values.add(random.nextInt(maxValue));
        }
        DrawGraph mainPanel = new DrawGraph(0, 20, Color.red, 100);

        JFrame frame = new JFrame("DrawGraph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();
            }
        });
    }

    public void setPlotDuration(double duration) {
        maxPlotValues = (int) duration/3;
    }
}