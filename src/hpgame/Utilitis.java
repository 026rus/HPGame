/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hpgame;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 *
 * @author borodin
 */
public class Utilitis
{

	public static void print(String str)
	{
		System.out.println(str);
	}

	public static String getFilename(File photo)
	{
		String str = photo.getName().toLowerCase().replaceAll(".jpg", "");
		char[] chars = str.toLowerCase().toCharArray();
		boolean found = false;
		for (int i = 0; i < chars.length; i++)
		{
			if (!found && Character.isLetter(chars[i]))
			{
				chars[i] = Character.toUpperCase(chars[i]);
				found = true;
			} else if (Character.isWhitespace(chars[i]) || chars[i] == '.' || chars[i] == '\'')
			{ // You can add other chars here
				found = false;
			}
		}
		return String.valueOf(chars);
	}

	public static BufferedImage scaleImage(BufferedImage image, int imageType, int newWidth, int newHeight)
	{
		// Make sure the aspect ratio is maintained, so the image is not distorted
		double thumbRatio = (double) newWidth / (double) newHeight;
		int imageWidth = image.getWidth(null);
		int imageHeight = image.getHeight(null);
		double aspectRatio = (double) imageWidth / (double) imageHeight;

		if (thumbRatio < aspectRatio)
		{
			newHeight = (int) (newWidth / aspectRatio);
		} else
		{
			newWidth = (int) (newHeight * aspectRatio);
		}

		// Draw the scaled image
		BufferedImage newImage = new BufferedImage(newWidth, newHeight, imageType);
		Graphics2D graphics2D = newImage.createGraphics();
		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2D.drawImage(image, 0, 0, newWidth, newHeight, null);

		return newImage;
	}
}
