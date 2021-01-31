# guise

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [API info](#api-info)

## General info
* This project consists of Image upload and download related functionalities.
* EC2 domain : 
    - IPv4 address: [3.133.104.177](3.133.104.177)
    - IPv4 DNS: [ec2-3-133-104-177.us-east-2.compute.amazonaws.com](ec2-3-133-104-177.us-east-2.compute.amazonaws.com)
	
## Technologies
Project is created with:
* Springboot JAVA
* PostgreSQL
* Docker
* AWS

## API info
```
1. Upload API:
  * Description: This is a POST API to upload an image.
  * Api URI: /api/v1/image/upload
  * Query Param: 
      -name(String)
      -type(ENUM (JPEG, JPG, PNG))
2. Get all image records API:
  * Description: This is a GET API to give image detail list.
  * Api URI: /api/v1/image/details
3. Search image by name:
	* Description: This is a GET API to download an image.
  * Api URI: /api/v1/image/search
  * Query Param: name(String)
4. Delete an image record API:
  * Description: This is a DELETE API to delete an image record.
  * Api URI: /api/v1/image/{id}
  * Path Param: id(Long)
``` 
