package io.github.vanna.storageintegration.azure;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.PublicAccessType;

public class AzureStorageImpl implements AzureStorage {

	private final BlobServiceClient blobServiceClient;
	
    public AzureStorageImpl(String storageConnectionString) throws Exception {
        if(storageConnectionString == null || storageConnectionString.trim().isEmpty()) {
            throw new Exception("Blob storage connection string cannot be null");
        }
        this.blobServiceClient = new BlobServiceClientBuilder()
                .connectionString(storageConnectionString)
                .buildClient();
    }
    
    
	public Object uploadFile(String container, byte[] artifact, String fileName, AzureUploadOptions options)
			throws Exception{
		
		BlobContainerClient blobContainerClient = blobServiceClient.getBlobContainerClient(container);
        if(options.isCreateContainerIfNotExists()) {
            blobContainerClient.createIfNotExists();
        }
        if(options.isPublicAccessContainer()) {
        	blobContainerClient.setAccessPolicy(PublicAccessType.CONTAINER, null);
        }

		BlobClient blobClient = blobContainerClient.getBlobClient(fileName);
		
		try (ByteArrayInputStream dataStream = new ByteArrayInputStream(artifact)) {
			blobClient.upload(dataStream,options.isCanOverwriteObject());
		} catch (IOException e) {
		    e.printStackTrace();
		    throw new Exception("Error while uploading the file :"+e.getMessage());
		}
		
		return blobClient.getBlobUrl();
	}
    

}
