import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;


public class WavImageGenerator {
	private JFrame frame;
	/**
	 * @param args
	 */
	public void imgGenerator () {
		// TODO Auto-generated method stub
		String filename = "C:/Users/Jiang/Desktop/University/4thProject/running_outside_20ms.wav";
		frame = new JFrame();
		WaveFileReader reader = new WaveFileReader(filename);
		if (reader.isSuccess()) {
			int[] data = reader.getData()[1]; // 获取第2声道
			DrawPanel drawPanel = new DrawPanel(data); // 创建一个绘制波形的面板
			frame.add(drawPanel);
			frame.setTitle(filename);
			frame.setSize(800, 400);
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		} else {
			System.err.println(filename + "not a normal wav file");
		}
	}
	
	public void saveImg() {
		try
        {
			Container content = frame.getContentPane();
			BufferedImage image = new BufferedImage(800, 400, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2d = image.createGraphics();
			content.paint( g2d );
            ImageIO.write(image,"png", new File("C:/Users/Jiang/Desktop/University/4thProject/demo.png"));
        }
        catch(Exception exception)
        {
            //code
        }
	}
	
	public static void main(String[] args) {
		WavImageGenerator demo = new WavImageGenerator();
		demo.imgGenerator();
		demo.saveImg();
	}
}