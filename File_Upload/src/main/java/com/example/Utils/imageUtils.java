package com.example.Utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.stereotype.Component;

import com.example.Entity.Image;

@Component
public class imageUtils 
{
	private static final int BYTE_SIZE = 4*1024;
	
	// To Compress the image
	public static byte[] compress(byte[] data) throws IOException
	{
		Deflater deflater = new Deflater();
		deflater.setLevel(Deflater.BEST_COMPRESSION);
		deflater.setInput(data);
		deflater.finish();
		ByteArrayOutputStream ops = new ByteArrayOutputStream(data.length);
		
		// Byte size is the portion of data that would be filled inside the buffer
		byte[] buffer = new byte[BYTE_SIZE];  
		
		while(!deflater.finished())
		{
			int size = deflater.deflate(buffer);
			ops.write(buffer,0,size);
		}
		ops.close();
		
		return ops.toByteArray();	
	}
	
	//To de-compress the image
	public static byte[] decompress(byte[] data) throws DataFormatException, IOException
	{
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		
		// total data is the size of the data that we are inputing
		ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length);
		
		// Byte size is the portion of data that would be filled inside the buffer
		byte[] buffer = new byte[BYTE_SIZE];
		
		while(!inflater.finished())
		{
			int size = inflater.inflate(buffer);
			bos.write(buffer,0,size);
		}
		bos.close();
		
		return bos.toByteArray();
	}
	
	// To get image of the file 
	public static String ToFileName(Image image)
	{
		return image.getImageName();
	}
	
}
