import java.awt.image.*;
import java.awt.*;
public class NegativeImage extends BufferedImage
{
    public NegativeImage(BufferedImage img, int option)
    {
        super(img.getWidth(),img.getHeight(),BufferedImage.TYPE_INT_ARGB);
        Color color;
        for(int r = 0; r < this.getWidth(); r ++)
        {
            for(int c = 0; c < this.getHeight(); c ++)
            {
                color = new Color(img.getRGB(r,c));
                float[] hsb = Color.RGBtoHSB(color.getRed(),color.getGreen(),color.getBlue(),null);
                if(option == 1 || option == 2)
                {
                    hsb[0] += .5f;
                    hsb[0] %= 1;
                }
                if(option == 1 || option == 3)
                {
                    hsb[2] = 1 - hsb[2];
                }
                color = new Color(Color.HSBtoRGB(hsb[0],hsb[1],hsb[2]));
                this.setRGB(r,c,color.getRGB());
                //System.out.println("Red: " + color.getRed() + " Green: " + color.getGreen() + " Blue: " + color.getBlue()); 
            }
        }
    }
}