import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel {
    int[] xpos = new int[10];
    int[] ypos = new int[10];
    int[] foodx = new int[1];
    int[] foody = new int[1];
    int[][] newcolor = new int[10][3];
    int colorcount = 0;
    int fishcount;
    int[] dir = new int[10];
    int dircount = 0;
    GamePanel()
    {
        this.setPreferredSize(new Dimension(1100, 1100));
        this.setBackground(Color.black);
        this.setFocusable(true);
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g)
    {
        for (int i = 0; i < xpos.length; i++)
        {
            g.setColor(new Color(newcolor[i][0], newcolor[i][1], newcolor[i][2]));
            g.drawOval(xpos[i], ypos[i], 15, 10);
            int[] trix = new int[3];
            switch(dir[i])
            {
                case 0:
                    g.drawLine(xpos[i] + 5, ypos[i] + 3, xpos[i] + 6, ypos[i] + 4);
                    trix[0] = xpos[i] + 15;
                    trix[1] = xpos[i] + 20;
                    trix[2] = xpos[i] + 20;
                    break;
                case 1:
                    g.drawLine(xpos[i] + 10, ypos[i] + 3, xpos[i] + 9, ypos[i] + 4);
                    trix[0] = xpos[i];
                    trix[1] = xpos[i] - 5;
                    trix[2] = xpos[i] - 5;
                    break;
            }
            int[] triy = {ypos[i] + 5, ypos[i], ypos[i] + 10};
            g.drawPolygon(trix, triy, 3);
        }
        for (int i = 0; i < foodx.length; i++)
        {
            g.setColor(Color.orange);
            g.fillRect(foodx[i], foody[i], 5, 5);
        }
    }
    public void addFish(int x, int y, int i)
    {
        this.xpos[i] = x;
        this.ypos[i] = y;
    }
    public void addFood(int x, int y)
    {
        this.foodx[0] = x;
        this.foody[0] = y;
    }
    public void setColor(int[] color)
    {
        this.newcolor[colorcount][0] = color[0];
        this.newcolor[colorcount][1] = color[1];
        this.newcolor[colorcount][2] = color[2];
        this.colorcount++;
    }
    public void reset()
    {
        this.colorcount = 0;
    }
    public void setDir(int dir, int i)
    {
        this.dir[i] = dir;
    }
}
