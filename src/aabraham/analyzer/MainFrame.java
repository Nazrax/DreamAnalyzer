package aabraham.analyzer;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;

public class MainFrame extends JFrame {
  private Map<String, IAnalyzer> analyzers;
  private String path;
  private JPanel controlPanel;
  private JFreeChart chart;
  
  public MainFrame() {
    analyzers = new HashMap<String, IAnalyzer>();
    
    setTitle("Reading Analyzer");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    JPanel contentPane = new JPanel(new BorderLayout());
    setContentPane(contentPane);
    
    ValueAxis timeAxis = new DateAxis("Time Axis");
    timeAxis.setLowerMargin(.02);
    timeAxis.setUpperMargin(.02);
    XYPlot plot = new XYPlot(null, timeAxis, null, null);

    chart = new JFreeChart("Chart Title", JFreeChart.DEFAULT_TITLE_FONT, plot, true);
    
    ChartPanel chartPanel = new ChartPanel(chart);
    contentPane.add(chartPanel, BorderLayout.CENTER);
    
    controlPanel = new JPanel();
    controlPanel.setBorder(BorderFactory.createTitledBorder("Options"));
    
    contentPane.add(controlPanel, BorderLayout.SOUTH);

    Action openAction = new AbstractAction() {
      private static final long serialVersionUID = 1L;

      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("Hello actions");
      }
    };

    Action leftAction = new AbstractAction() {
      private static final long serialVersionUID = 1L;
      
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("Left");
      }
    };
    
    InputMap inputMap = contentPane.getInputMap();
    ActionMap actionMap = contentPane.getActionMap();

    KeyStroke ctrlO = KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK);
    KeyStroke leftStroke = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0);

    inputMap.put(ctrlO, "open");
    actionMap.put("open", openAction);
    
    inputMap.put(leftStroke, "leftAction");
    actionMap.put("leftAction", leftAction);
    
  }
  
  public static void main(String args[]) {
    if (args.length != 1) {
      System.err.println("Usage: MainFrame <path>");
      System.exit(1);
    }
    
    MainFrame frame = new MainFrame();
    frame.pack();
    frame.setVisible(true);
  }
}
