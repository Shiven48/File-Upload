package com.example.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Service.ImageInRepoService;
import com.example.Service.ImageOnServerService;

@RestController
@RequestMapping("/file")
public class ImageController 
{
	private ImageInRepoService repoService;
	private ImageOnServerService serverService;
	
	public ImageController(@Qualifier("imageOnServerService") ImageOnServerService serverService,
						   @Qualifier("imageInRepoService") ImageInRepoService repoService )
	{	
		this.serverService = serverService;
		this.repoService = repoService;
	}
	
	@PostMapping("/upload")
	public ResponseEntity<String> upload(@RequestParam("input") MultipartFile file) throws IOException
	{
		String returnupload = repoService.imageupload(file);
		return ResponseEntity.ok(returnupload);
	}
	
	@PostMapping("/uploads")
	public ResponseEntity<String> serverUpload(@RequestParam("input") MultipartFile file) throws IOException
	{
		String returnupload = serverService.StoreFile(file);
		return ResponseEntity.ok(returnupload);
	}
	
	@GetMapping("/download/jpeg/{filename}")
	public ResponseEntity<byte[]> download(@PathVariable("filename") String filename) throws IOException
	{
		byte[] retdownload = repoService.downloadupload(filename);
		System.out.println(retdownload.length/1000);
		return ResponseEntity.status(HttpStatus.OK)
							 .contentType(MediaType.IMAGE_JPEG)
							 .body(retdownload);
	}
	
	@GetMapping("/download/pdf/{filename}")
	public ResponseEntity<byte[]> downloadpdf(@PathVariable("filename") String filename) throws IOException
	{
		byte[] retdownload = repoService.downloadupload(filename);
		System.out.println(retdownload.length/1000);
		return ResponseEntity.status(HttpStatus.OK)
							 .contentType(MediaType.APPLICATION_PDF)
							 .body(retdownload);
	}
	
	@GetMapping("/type/{type}")
	public ResponseEntity<List<String>> getTypes(@PathVariable("type") String type) {
	    System.out.println("Received image type: " + type);
	    List<String> returned_list_imagesname_by_type = repoService.getType(type);
	    return ResponseEntity.ok(returned_list_imagesname_by_type);
	}
	
	@GetMapping("/{filename}")
	public ResponseEntity<byte[]> downloadfile(@PathVariable("filename") String filename) throws IOException {
		byte[] retdownload = serverService.RetriveFile(filename);
		return ResponseEntity.status(200)
							 .contentType(MediaType.APPLICATION_PDF)
							 .body(retdownload);
	}

}
