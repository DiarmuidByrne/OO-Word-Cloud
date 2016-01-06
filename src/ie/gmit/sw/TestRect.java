package ie.gmit.sw;
import java.awt.*;
import java.awt.font.*;
import javax.swing.*;
  
public class TestRect extends JPanel
{
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth();
        int h = getHeight();
        int x = w/3;
        int y = h/4;
        int rectW = w*3/8;
        int rectH = h/4;
        g.setColor(Color.red);
        g.drawRect(x, y, rectW, rectH);
        Font font = g2.getFont().deriveFont(34f);
        g2.setFont(font);
        String text = "hello world";
        FontRenderContext frc = g2.getFontRenderContext();
        int textWidth = (int)font.getStringBounds(text, frc).getWidth();
        LineMetrics lm = font.getLineMetrics(text, frc);
        int textHeight = (int)(lm.getAscent() + lm.getDescent());
        int sx = x + (rectW - textWidth)/2;
        int sy = (int)(y + (rectH + textHeight)/2 - lm.getDescent());
        g.setColor(Color.blue);
        g.drawString(text, sx, sy);
    }
  
    public static void main(String[] args)
    {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(new TestRect());
        f.setSize(400,400);
        f.setLocation(200,200);
        f.setVisible(true);
    }
}