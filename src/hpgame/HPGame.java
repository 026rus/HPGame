/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hpgame;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.text.Utilities;

/**
 *
 * @author borodin
 */
public class HPGame
{

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args)
	{
		// TODO code application logic here
		HPGame game = new HPGame();
		File photod = game.makehomedir();
		List<QandA> qas = game.readAllQandAs(photod);
		game.printAllQandAs(qas);
		new GameUI(qas);
	}

	private File makehomedir()
	{
		String userDirPath = System.getProperty("user.home") + "/HPGamePhotos";
		File photoDir = new File(userDirPath);
		if (!photoDir.exists())
		{
			Utilitis.print("Making the folder for photos");
			try
			{
				photoDir.mkdir();
				Utilitis.print("Photo dir created!");
			} catch (Exception e)
			{
				Utilitis.print("Erroe makeing dir: " + e.getLocalizedMessage());
			}
		}
		return photoDir;
	}

	private List<QandA> readAllQandAs(final File folder)
	{
		List<QandA> qas = new ArrayList<>();
		List<File> filesA = new ArrayList<>();
		List<File> filesQ = new ArrayList<>();
		// TODO return tuple of strings with Q and A for each photo
		for (final File fileEntry : folder.listFiles())
		{
			if (fileEntry.isDirectory())
			{
				readAllQandAs(fileEntry);
			} else
			{
				if (fileEntry.getAbsoluteFile().toString().contains("(2)"))
				{
					filesA.add(fileEntry);
				} else
				{
					filesQ.add(fileEntry);
				}
			}
		}

		for (int i=0; i< filesQ.size(); i++)
		{
			String q = filesQ.get(i).getName().toLowerCase();
			String a;
			boolean notfound = true;
			for (int j=0; j < filesA.size(); j++)
			{
				a = filesA.get(j).getName()
								 .toLowerCase()
								 .replaceAll(".jpg", "")
								 .replaceAll(".JPG", "")
								 .replaceAll("[^a-zA-Z]","");


				if(q.contains(a))
				{
					QandA qa = new QandA(filesQ.get(i), filesA.get(j));
					qas.add(qa);
					notfound = false;
					break;
				}
			}
			if (notfound)
			{
				QandA qa = new QandA(filesQ.get(i), null);
				qas.add(qa);
				notfound = false;
			}
		}
		return qas;
	}

	private void printAllQandAs(List<QandA> qas)
	{
		Utilitis.print("----------------------------------------------------------------");
		for (final QandA qa: qas)
		{
			Utilitis.print(qa.Q + "\t:" +qa.A);
		}
		Utilitis.print("----------------------------------------------------------------");
	}
}
