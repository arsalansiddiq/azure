package com.inventa.azure.common;

import com.azure.core.credential.TokenCredential;
import com.azure.core.management.AzureEnvironment;
import com.azure.core.management.profile.AzureProfile;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.azure.resourcemanager.AzureResourceManager;
import org.springframework.stereotype.Component;

@Component
public class AzureClient {

    public AzureResourceManager getAzureClient(String clientId, String clientSecret, String tenentId, String subscriptionId) {
        TokenCredential credential = new ClientSecretCredentialBuilder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .tenantId(tenentId)
                .build();

        AzureProfile profile = new AzureProfile(tenentId, subscriptionId, AzureEnvironment.AZURE);

        AzureResourceManager azureResourceManager = AzureResourceManager.configure()
                .authenticate(credential, profile)
                .withSubscription(subscriptionId);

        return azureResourceManager;
    }

}
