import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class DrawGraph extends JPanel{
    private static final int PREF_W = 600;                               //Graph width
    private static final int PREF_H = 250;                               //Graph height
    private static final int BORDER_GAP = 45;                            //Padding
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);//Line width

    private final Color graphColor;                                      //Line color
    private final int valueInterval;                                     //The time between values in milliseconds
    private final LinkedList<Double> plotValues;                         //Values to be plotted. A LinkedList so we can addFirst and removeLast
    private final double maxMinValue;                                    //Maximum minValue
    private final double minMaxValue;                                    //Minimum maxValue

    private int timeFrameMillis;                                         //The number of milliseconds to plot
    private int maxPlotValues;                                           //The number of values to be plotted

    public DrawGraph(double maxMinValue, double minMaxValue, Color graphColor, int valueInterval) {
        this.maxMinValue = maxMinValue;
        this.minMaxValue = minMaxValue;
        this.graphColor = graphColor;
        this.valueInterval = valueInterval;
        this.plotValues = new LinkedList<>();
        timeFrameMillis = 5000; //Default 5 seconds
        maxPlotValues = timeFrameMillis / valueInterval; //Number of values = plot duration / time between values
    }

    public void addPlotValue(double plotValue) {
        while (plotValues.size() >= maxPlotValues) {
            //Remove the last value that exceeds the maximum number of values
            plotValues.removeLast();
        }
        plotValues.addFirst(plotValue);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        //Turn on antialiasing for smoother plots
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Calculate the minimum and maximum values of the plot so the y-axis can be scaled to fit them in
        double minValue = Double.MAX_VALUE;
        double maxValue = Double.MIN_VALUE;

        for (int i = 0; i < plotValues.size(); i++) {
            maxValue = Math.max(maxValue, plotValues.get(i));
            minValue = Math.min(minValue, plotValues.get(i));
        }

        minValue = Math.min(minValue, maxMinValue);
        maxValue = Math.max(maxValue, minMaxValue);

        //Display the range of values for the axes
        BigDecimal bdMin = new BigDecimal(minValue);
        BigDecimal bdMax = new BigDecimal(maxValue);
        bdMin = bdMin.round(new MathContext(3));
        bdMax = bdMax.round(new MathContext(3));
        g.drawString(bdMax.toPlainString(), 7, 50);
        g.drawString(bdMin.toPlainString(), 7, 210);
        g.drawString("T-" + String.valueOf(timeFrameMillis/1000) + "s",43, 230);
        g.drawString("T",570, 230);

        //Calculate scaling factors for the values on the 2 axes
        double xScale = ((double) getWidth() - 2 * BORDER_GAP) / (maxPlotValues - 1);
        double yScale = ((double) getHeight() - 2 * BORDER_GAP) / (maxValue - minValue);

        List<Point> graphPoints = new ArrayList<Point>();

        //Calculate the coordinates of the plot values on the display (in pixels)
        for (int i = 0; i < plotValues.size(); i++) {
            int x1 = (int) ((maxPlotValues - 1 - i) * xScale + BORDER_GAP);
            int y1 = (int) ((maxValue - plotValues.get(i)) * yScale + BORDER_GAP);
            graphPoints.add(new Point(x1, y1));
        }

        //Draw x and y axes
        g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, BORDER_GAP, BORDER_GAP);
        g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, getWidth() - BORDER_GAP, getHeight() - BORDER_GAP);

        //Draw the plot by drawing lines between values
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

    public void setPlotDuration(int timeFrameMillis) {
        this.timeFrameMillis = timeFrameMillis;
        maxPlotValues = timeFrameMillis / valueInterval; //Number of values = plot duration / time between values
    }

    /*
        //Intended to add hatch marks (gradations) on the plots, but ended up just adding values
        //Create hatch marks for y axis

        private static final int Y_HATCH_CNT = 10;                           //y axis gradations
        private static final int HATCH_WIDTH = 4;                            //x axis gradations
        for (int i = 0; i < Y_HATCH_CNT; i++) {
            int x0 = BORDER_GAP;
            int x1 = BORDER_GAP - HATCH_WIDTH;
            int y0 = getHeight() - (((i + 1) * (getHeight() - BORDER_GAP * 2)) / Y_HATCH_CNT + BORDER_GAP);
            int y1 = y0;
            g2.drawLine(x0, y0, x1, y1);
        }

        //Create hatch marks for x axis.
        for (int i = 0; i < maxPlotValues - 1; i++) {
            int x0 = (i + 1) * (getWidth() - BORDER_GAP * 2) / (maxPlotValues - 1) + BORDER_GAP;
            int x1 = x0;
            int y0 = getHeight() - BORDER_GAP;
            int y1 = y0 + HATCH_WIDTH;
            g2.drawLine(x0, y0, x1, y1);
        }
         */
}