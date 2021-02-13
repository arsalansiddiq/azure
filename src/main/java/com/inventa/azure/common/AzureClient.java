package com.inventa.azure.common;

import com.microsoft.azure.AzureEnvironment;
import com.microsoft.azure.credentials.ApplicationTokenCredentials;
import org.springframework.stereotype.Component;

@Component
public class AzureClient {

    public ApplicationTokenCredentials getAzureClient(String clientId, String clientSecret, String tenentId) {
        ApplicationTokenCredentials credentials =
                new ApplicationTokenCredentials(
                        clientId,
                        tenentId,
                        clientSecret,
                        AzureEnvironment.AZURE
                );
        return credentials;
    }

}
