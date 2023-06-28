package io.github.vanna.storageintegration.azure;


public interface AzureStorage {

	public Object uploadFile(String container, byte[] file, String fileName, AzureUploadOptions options) throws Exception;
}
