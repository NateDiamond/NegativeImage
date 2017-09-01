import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import java.awt.*;
public class Swing
{
    public static int length = 500, height = 400;
    private static final int LENGTH_MAX = 700, HEIGHT_MAX = 400;
    public static void main(String [] args)
    {
        JFrame frame = new JFrame();
        frame.setSize(length, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.DARK_GRAY);
        frame.setVisible(false);
        frame.setResizable(false);
        JFileChooser fc = new JFileChooser();
        int response = fc.showOpenDialog(frame);
        BufferedImage img = null;
        if(response == JFileChooser.APPROVE_OPTION)
        {
            try
            {
                img = ImageIO.read(fc.getSelectedFile());
                
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            return;
        }
        NegativeImage negativeImg = new NegativeImage(img, 1);
        NegativeImage complement = new NegativeImage(img, 2);
        NegativeImage dark = new NegativeImage(img, 3);
        //System.out.println(negativeImg.getWidth() + "" + negativeImg.getHeight());
        length = negativeImg.getWidth();
        height = negativeImg.getHeight();
        while(length > LENGTH_MAX || height > HEIGHT_MAX)
        {
            length = (int)(length * .9);
            height = (int)(height * .9);
        }
        Canvas canvas = new Canvas();
        frame.setVisible(false);
        frame.setSize(length*2,height*2);
        frame.setVisible(true);
        canvas.setSize(length*2,height*2);
        frame.add(canvas);
        if(canvas.getBufferStrategy() == null) 
        {
            canvas.createBufferStrategy(3); 
        }
        Graphics g = canvas.getGraphics();
        
        while(true)
        {
            g.drawImage(img.getScaledInstance(length,height,Image.SCALE_DEFAULT), 0, 0, null);
            g.drawImage(negativeImg.getScaledInstance(length,height, Image.SCALE_DEFAULT), length, height, null);
            g.drawImage(dark.getScaledInstance(length,height, Image.SCALE_DEFAULT), length, 0, null);
            g.drawImage(complement.getScaledInstance(length,height, Image.SCALE_DEFAULT), 0, height, null);
            g.dispose();
        }
    }
    
}