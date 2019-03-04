// ----------------------------------------------SEPARATA -------------------------------------------------
// Program to separate Files present in one Directory such as Audio , Video ,Text files
// usefull methods and topics regarding to this program are :-
//		ArrayList<> a part of collection-frmwrk & its methods like add,size,remove etc...
//			Ex - for mantaining the list (of String) of all the files present in the directory
//		File class in IO & its method list
//		String methods are used for smart-work
//			Ex- endsWith() instead of contains() to justify the extension
//		for moving the whole file i used
//			Files.move(source , target ,Replace_Existing) method seen on oracle documentation using the Path class and the FileSystems class
//   https://www.javatpoint.com/java-file-class
//		http://javaconceptoftheday.com/advantages-of-using-arraylist-over-arrays/
//		https://www.computerhope.com/issues/ch001789.htm

import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.FileSystems;
import java.nio.file.StandardCopyOption;
public class FileMovers {
	public static void main(String[] args) {
		new FileMoverUI();
		String source = new String (args[0]);
		File f = new File (source);
		String fileNames[] = f.list();

		ArrayList<String> TextFiles = new ArrayList<String>();
		TextFiles = findTextFiles(fileNames,source);
		String type = "TextFiles";
		if (TextFiles.size() == 0){}
		else{
			new File (source + "//TextFiles").mkdir();
			moveFiles(TextFiles,source,type);
		}
		//-------------------------------- for audio files------------------------------------------
		ArrayList<String> AudioFiles = new ArrayList<String>();
		AudioFiles = findAudioFiles(fileNames,source);
		type = "AudioFiles";
		if (AudioFiles.size() == 0){}
			else{
				new File(source+"//AudioFiles").mkdir();
				moveFiles(AudioFiles,source,type);
			}

		// --------------------------------for video files------------------------------------------
		ArrayList<String> VideoFiles = new ArrayList<String>();
		VideoFiles = findVideoFiles(fileNames,source);
		type = "VideoFiles";
		if (VideoFiles.size() == 0){}
			else{
				new File(source+"//VideoFiles").mkdir();
				moveFiles(VideoFiles,source,type);
			}

		// --------------------------------for image files------------------------------------------
		ArrayList<String> ImageFiles = new ArrayList<String>();
		ImageFiles = findImageFiles(fileNames,source);
		type = "ImageFiles";
		if (ImageFiles.size() == 0){}
			else{
				new File(source+"//ImageFiles").mkdir();
				moveFiles(ImageFiles,source,type);
			}
	}
	static ArrayList<String> findTextFiles (String Text[],String source)
	{
		ArrayList<String> TextFiles = new ArrayList<String>();
		for (String files: Text)
		{
			if(files.endsWith(".txt") || files.endsWith(".pdf") || files.endsWith(".docx") || files.endsWith(".rtf") || files.endsWith(".html") || files.endsWith(".htm") || files.endsWith(".ppt") || files.endsWith(".pptx")) 
			{
				TextFiles.add(files);
			}
		}
		System.out.println("findTextFiles working");
		return TextFiles;
	}
	static ArrayList<String> findAudioFiles (String Audio[],String source)
	{
		ArrayList<String> AudioFiles = new ArrayList<String>();
		for (String files: Audio)
		{
			if(files.endsWith(".mp3") || files.endsWith(".mpa") || files.endsWith(".opus") || files.endsWith(".ogg") || files.endsWith(".wav") || files.endsWith(".wma") || files.endsWith(".wpl") || files.endsWith(".aif") || files.endsWith(".mid")) 
			{
				AudioFiles.add(files);
			}
		}
		System.out.println("findAudioFiles working");
		return AudioFiles;
	}
	static ArrayList<String> findVideoFiles (String Video[],String source)
	{
		ArrayList<String> VideoFiles = new ArrayList<String>();
		for (String files: Video)
		{
			if(files.endsWith(".mp4") || files.endsWith(".avi") || files.endsWith(".m4v") || files.endsWith(".mpg") || files.endsWith(".wmv") || files.endsWith(".mkv") )
			{
				VideoFiles.add(files);
			}
		}
		System.out.println("findVideoFiles working");
		return VideoFiles;
	}
	static ArrayList<String> findImageFiles (String Image[],String source)
	{
		ArrayList<String> ImageFiles = new ArrayList<String>();
		for (String files: Image)
		{
			if(files.endsWith(".jpg") || files.endsWith(".png") || files.endsWith(".bmp") || files.endsWith(".ico") || files.endsWith(".gif") || files.endsWith(".jpeg") )
			{
				ImageFiles.add(files);
			}
		}
		System.out.println("findImageFiles working");
		return ImageFiles;
	}
	// ------- this i done to copy the files----------
	// static void movetoSpecific(ArrayList<String> TextFiles, String source, String type)
	// {
	// 	for (String obj : TextFiles)		// For text files only
	// 	{

	// 		try{
	// 		copyFileUsingStream ( new File (source + "//" +obj) , new File (source+"//"+type+"//"+obj) );
	// 		}
	// 		catch(IOException e){e.printStackTrace();}
	// 	}
	// }

	//-----------new feature to directly move
	// Best method to move the files Referred by the oracle  :) :)
	static void moveFiles(ArrayList<String> TextFiles, String source, String type)	
	{	//http://www.java2s.com/Tutorials/Java/java.nio.file/Files/Java_Files_move_Path_source_Path_target_CopyOption_options_.htm
		Path movefrom , target;			//  nio.file.Path package
		for (String obj : TextFiles)
		{
		movefrom = FileSystems.getDefault().getPath(source + "//" +obj);
        target = FileSystems.getDefault().getPath(source+"//"+type+"//"+obj);
        try{
        	Files.move (movefrom , target, StandardCopyOption.REPLACE_EXISTING);
        	}
        catch(IOException e){e.printStackTrace();}
		}
	}
// 	static void copyFileUsingStream(File source, File dest) throws IOException {
//     InputStream is = null;
//     OutputStream os = null;
//     try {
//         is = new FileInputStream(source);
//         os = new FileOutputStream(dest);
//         byte[] buffer = new byte[1024];
//         int length;
//         while ((length = is.read(buffer)) > 0) {
//             os.write(buffer, 0, length);
//         }
//     } finally {
//         is.close();
//         os.close();
//     }
// }
}