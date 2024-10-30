package com.example.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Entity.Image;

import java.util.List;


@Repository
public interface ImageRepository extends JpaRepository<Image, Integer>
{
	
	Optional<Image> findByImageName(String imageName);
	
	List<Image> findByImageType(String imageType);

}