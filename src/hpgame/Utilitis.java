/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hpgame;

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

}
