Description :
This is a simple spring boot project that has some functionality related to file upload.This project has two services one to save image/files in database(BLOB datatype) 
and other is storing image/file on the server(Images/Static folder).

The file upload can be done in the following types :
1) In Database (Not Recommended)
2) On Server
3) On cloud with the help of services like S3 (Recommended) 

Project Structure :
* Images/Static - It will contain all the file that you upload with the help of the defined Apis. This are the copy of the files that you upload that are saved on the server
* pom.xml - It contains all the required external dependencies 
* src - This folder contains all the source code
* Controller package - Contains all the routes to which our services are mapped to
* Entity package - It is the model of the object we are using
* Repository package - This layer interacts with the database based on the properties we configure in application.properties file (application.yml can also be used)
* Service package - This is the main layer that is responsible for the main business logic we provide to our application
* Utils package - It provides some extra utility for the project like compressing the file while uploading and decompressing the file while downloading

The future idea for this project will be enlisted in the TODO.md file of this project

------------------ x ----------------- x ----------------- x ----------------- x ----------------- x ----------------- x ----------------- x ----------------- 
