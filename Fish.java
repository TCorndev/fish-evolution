public class Fish implements Comparable<Fish>
{
    private int gen;
    private int number;
    private int speed = 10;
    private boolean full = false;
    protected int xpos;
    protected int ypos;
    private int r;
    private int g;
    private int b;
    

    public Fish(int gen, int number)
    {
        this.gen = gen;
        this.number = number;
    }
    public Fish(int gen, int number, int speed, int r, int g, int b)
    {
        this.gen = gen;
        this.number = number;
        this.speed = speed;
        this.r = r;
        this.g = g;
        this.b = b;
    }
    public int getGen()
    {
        return this.gen;
    }
    public int getNumber()
    {
        return this.number;
    }
    public int getSpeed()
    {
        return this.speed;
    }
    public int getXPos()
    {
        return this.xpos;
    }
    public int getYPos()
    {
        return this.ypos;
    }
    public boolean getFull()
    {
        return this.full;
    }
    public int[] getColor()
    {
        int[] color = new int[3];
        color[0] = r;
        color[1] = g;
        color[2] = b;
        return color;
    }
    public int getR()
    {
        return r;
    }
    public int getG()
    {
        return g;
    }
    public int getB()
    {
        return b;
    }
    public void setGen(int gen)
    {
        this.gen = gen;
    }
    public void setNumber(int number)
    {
        this.number = number;
    }
    public void setSpeed(int speed)
    {
        this.speed = speed;
    }
    public void setPos(int xpos, int ypos)
    {
        this.xpos = xpos;
        this.ypos = ypos;
    }
    public void setXPos(int xpos)
    {
        this.xpos = xpos;
    }
    public void setYPos(int ypos)
    {
        this.ypos = ypos;
    }
    public void setFull(boolean full)
    {
        this.full = full;
    }
    public void setColor(int r, int g, int b)
    {
        this.r = r;
        this.g = g;
        this.b = b;
        if (this.r > 255)
        {
            this.r = 255;
        }
        if (this.r < 0)
        {
            this.r = 0;
        }
        if (this.g > 255)
        {
            this.g = 255;
        }
        if (this.g < 0)
        {
            this.g = 0;
        }
        if (this.b > 255)
        {
            this.b = 255;
        }
        if (this.b < 0)
        {
            this.b = 0;
        }
    }
    public int compareTo(Fish compfish)
    {
        return this.speed - compfish.getSpeed();
    }
}
