import java.io.*;
import java.util.ArrayList;

public class ReadFromWav
{
	public double[] getData()
	{
		try
		{
			// Open the wav file specified as the first argument
			WavFile wavFile = WavFile.openWavFile(new File("C:/Users/Jiang/Desktop/University/4thProject/running_outside_20ms.wav"));

			// Display information about the wav file
			wavFile.display();

			// Get the number of audio channels in the wav file
			int numChannels = wavFile.getNumChannels();

			// Create a buffer contains 100 frames
			double[] buffer = new double[100 * numChannels];

			int framesRead;
			double min = Double.MAX_VALUE;
			double max = Double.MIN_VALUE;
			double[] data = new double[1024];
			//ArrayList<Double> data = new ArrayList<Double>();
			int count = 0;

			do
			{
				// Read frames into buffer
				framesRead = wavFile.readFrames(buffer, 100);
				
				//Select 1024 frames to FFT class
				if(count == 1024) break;
				data[count] = buffer[0];
				
				
				// Loop through frames and look for minimum and maximum value
				for (int s=0 ; s<framesRead * numChannels ; s++)
				{
					if (buffer[s] > max) max = buffer[s];
					if (buffer[s] < min) min = buffer[s];
				}
				count++;
			}
			while (framesRead != 0);

			// Close the wavFile
			wavFile.close();

			// Output the minimum and maximum value
			System.out.printf("Min: %f, Max: %f\n", min, max);
			return data;
			
		}
		catch (Exception e)
		{
			System.err.println(e);
		}
		return null;
	}
}
