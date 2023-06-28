package io.github.vanna.storageintegration.azure;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AzureUploadOptions {

    /**
     * Determines if the container should
     * be created if it doesn't already
     * exist
     */
    boolean createContainerIfNotExists;

    /**
     * Determines if the object can be overwritten.
     * If this is false, and the object with same name
     * already exists, an Exception will be thrown
     */
    boolean canOverwriteObject;
    
    /**
     * Determines if the container should
     * be created with public access policy or not
     */
    boolean publicAccessContainer;
    
}
