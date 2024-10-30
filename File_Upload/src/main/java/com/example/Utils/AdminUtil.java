package com.example.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.stereotype.Component;

@Component
public class AdminUtil {
	
	public static boolean requiresAdminPrivileges(Path path) {
	    try {
	        if (Files.exists(path)) {
	            return !Files.isWritable(path);
	        }
	        
	        Path parent = path.getParent();
	        if (parent != null && Files.exists(parent)) {
	            return !Files.isWritable(parent);
	        }
	        
	        try {
	            Path testFile = Files.createTempFile(parent, "test", ".tmp");
	            Files.delete(testFile);
	            return false;
	        } catch (IOException e) {
	            return true;
	        }
	        
	    } catch (SecurityException e) {
	        return true;
	    }
	}
}
