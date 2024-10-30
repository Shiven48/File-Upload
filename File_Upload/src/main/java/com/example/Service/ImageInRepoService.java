package com.example.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.Entity.Image;
import com.example.Repository.ImageRepository;
import com.example.Utils.imageUtils;

@Service("imageInRepoService")
public class ImageInRepoService
{
	private ImageRepository repo;
	
	ImageInRepoService(ImageRepository repo)
	{
		this.repo = repo;
	}

	public String imageupload(MultipartFile file) throws IOException
	{
		var imagetosave = Image.builder()
						  .imageName(file.getOriginalFilename())
						  .imageType(file.getContentType().split("/")[1])
						  .imageData(imageUtils.compress(file.getBytes()))
						  .build();
		var imagesaved = repo.save(imagetosave);
		return "Image Saved Successfully!"+file.getOriginalFilename()+" Size "+imagesaved.getImageData().length/1000;
	}
	
	public byte[] downloadupload(String imageName) throws IOException
	{
		Optional<Image> returnedImage = repo.findByImageName(imageName);
		
		return returnedImage.map(image -> { 
			try 
			{
				return imageUtils.decompress(image.getImageData());
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return null;
			}
		}).orElse(null);
		
	}
	
	public List<String> getType(String type) {
	    System.out.println("Querying database for image type: " + type);
	    List<Image> needed_type = repo.findByImageType(type);
	    System.out.println("Query result size: " + (needed_type != null ? needed_type.size() : "null"));

	    if (needed_type != null && !needed_type.isEmpty()) {
	        for (Image n : needed_type) {
	            System.out.println(n.toString());
	        }
	        List<String> retType = needed_type.stream()
	        								  .map(img -> imageUtils.ToFileName(img))
	        								  .collect(Collectors.toList());
	        System.out.println("Returning file names: " + retType);
	        return retType;
	    } else {
	        System.out.println("No images found for type: " + type);
	        return new ArrayList<>();
	    }
	}
	
	
}
