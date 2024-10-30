package com.example.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.Utils.AdminUtil;

@Service("imageOnServerService")
public class ImageOnServerService 
{	
	private final Path fileLocation;
	
	public ImageOnServerService() {
		 this.fileLocation = Paths.get("Images/Static").toAbsolutePath().normalize();
	        
		 // To Check if the file requires Admin Privileges
	        if (AdminUtil.requiresAdminPrivileges(this.fileLocation)) {
	            System.out.println("Warning: Path requires administrative privileges: " + this.fileLocation);
	        }
		 
	        try {
	            Files.createDirectories(this.fileLocation);
	        } catch(Exception e) {
	            throw new RuntimeException(
	                "Could not create directory. Admin privileges may be required: " + this.fileLocation, e);
	        }
	}
	
	public String getFileExtension(String fileName) {
		if(fileName == null){
			return null;
		}
		String[] splitfileName = fileName.split("\\.");
		return splitfileName[splitfileName.length-1];
	}
	
	public String StoreFile(MultipartFile file) {
		String fileName = new Date().getTime()+"-file."+getFileExtension(file.getOriginalFilename());
		
		try {
			if (fileName.contains("..")) {
		        throw new RuntimeException(
		            "Sorry! Filename contains invalid path sequence " + fileName);
		      }
			Path targetLocation = this.fileLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation,StandardCopyOption.REPLACE_EXISTING);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}

	public byte[] RetriveFile(String filename) throws IOException {
		String path = Paths.get("Images/Static")
						   .toAbsolutePath()
						   .resolve(filename)
						   .normalize().toString();
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		byte[] image_data = fis.readAllBytes();
		fis.close();
		return image_data;
	}
}
