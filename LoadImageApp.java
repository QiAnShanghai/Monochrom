import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class LoadImageApp extends Component {
          
    BufferedImage img;
    public static String filename;

    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
	
	public LoadImageApp() {
		try {
			img = ImageIO.read(new File(filename));
			int h = img.getHeight();
			int w = img.getWidth();
			int[][] input = new int[100][100];
			/*
			int average = 0;
		        int n = 0;
		       	
			for(int i=0;i<h;i++) {
				for(int j=0;j<w;j++) {
					if(img.getRGB(i,j)==-1) continue;
					average += img.getRGB(i,j);
					n += 1;
				}
			}
			average /= n;
			*/
			for(int i=0;i<w;i++) {
				for(int j=0;j<h;j++) {
					int c = img.getRGB(i,j);
					//System.out.print(c);
					if(c==-1) continue;
					int a = 256;
					int b = c % a;
					c = (c-b)/a;
					int g = c % a;
					int r = (c-g)/a;
					c = (r+g+b);
					c = (c-c%3)/3;
					c = c + c*a + c*a*a;
					
					//System.out.print(r);
					
					if(c>-0x888888) {
						c = 0xffffff;
					}
					else {
						c = 0x000000;
					}
					img.setRGB(i,j,c);
				}
			}
		} catch (IOException e) {
		}
	}

    public Dimension getPreferredSize() {
        if (img == null) {
             return new Dimension(100,100);
        } else {
           return new Dimension(img.getWidth(null), img.getHeight(null));
       }
    }

    public static void main(String[] args) {
	
	filename = args[0];
        JFrame f = new JFrame("Load Image Sample");
            
        f.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });

        f.add(new LoadImageApp());
        f.pack();
        f.setVisible(true);
    }
}

