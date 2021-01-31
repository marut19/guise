# guise

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Prerequisites](#prerequisites)
* [API info](#api-info)
* [Docker Running Process](#docker-run-process)

## General info
* This project consists of Image upload and download related functionalities.
* EC2 domain : 
    - IPv4 address: [3.133.104.177](3.133.104.177)
    - IPv4 DNS: [ec2-3-133-104-177.us-east-2.compute.amazonaws.com](ec2-3-133-104-177.us-east-2.compute.amazonaws.com)
* API ENDPOINT: 
    - EC2 : [http://3.133.104.177:8080](http://3.133.104.177:8080) or [http://ec2-3-133-104-177.us-east-2.compute.amazonaws.com:8080](http://ec2-3-133-104-177.us-east-2.compute.amazonaws.com:8080)
    - Local : [http://localost:8080](http://localost:8080)
	
## Technologies
Project is created with:
* Springboot JAVA
* PostgreSQL
* Docker
* AWS

## Prerequisites
 1. Install [Docker](https://docs.docker.com/engine/install/ubuntu/)  
 2. Install postgres database server	
 3. Install OpenJDK 11
 
## API info
```
1. Upload API:
  * Description: This is a POST API to upload an image.
  * Api URI: /api/v1/image/upload
  * Query Param: 
      -name(String)
      -type(ENUM (JPEG, JPG, PNG))
  * Success Response: 201
  * Error Response: 400, 422, 500
2. Get all image records API:
  * Description: This is a GET API to give image detail list.
  * Api URI: /api/v1/image/details
  * Success Response: 200
  * Error Response: 500
3. Search image by name:
	* Description: This is a GET API to download an image.
  * Api URI: /api/v1/image/search
  * Query Param: name(String)
  * Success Response: 200
  * Error Response: 400, 404, 500
4. Delete an image record API:
  * Description: This is a DELETE API to delete an image record.
  * Api URI: /api/v1/image/{id}
  * Path Param: id(Long)
  * Success Response: 204
  * Error Response: 400, 404, 500
``` 
## Docker Running Process
```
#Build JAR
mvn clean package

# Build Docker Image
docker build -t <ImageName>:<ImageTag> .

# Stop the container if exist
docker stop <containerId>

# Run Docker Image
docker run -p 8080:8080 --network=host --restart=unless-stopped -d <imageId or imageName>
```
