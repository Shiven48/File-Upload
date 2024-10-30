package com.example.Entity;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "image")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Image 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "imageid")
	private Integer imageId;
	
	@Column(name = "imagename")
	private String imageName;
	
	@Column(name = "imagetype")
	private String imageType;
	
	@Lob
	@Column(name = "imagedata")
	private byte[] imageData;

	public String getImageName() {
		return imageName;
	}

	public void setImage_name(String imageName) {
		this.imageName = imageName;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	@Override
	public String toString() {
		return "Image [image_id=" + imageId + ", image_name=" + imageName + ", image_type=" + imageType
				+ ", imagedata=" + Arrays.toString(imageData) + "]";
	}
}
