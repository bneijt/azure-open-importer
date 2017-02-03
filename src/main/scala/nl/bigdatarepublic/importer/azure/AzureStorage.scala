package nl.bigdatarepublic.importer.azure

import java.io.{File, FileInputStream}

import com.microsoft.azure.storage.CloudStorageAccount
import com.microsoft.azure.storage.blob.{CloudBlobClient, CloudBlobContainer, CloudBlockBlob}

object AzureStorage {
  def uploadFile(basepath: String, filename: String): String = {
    val outputPath: String = new File(basepath, filename).toString
    println("Uploading: " + filename + " to " + outputPath)
    val storageConnectionString: String =
      "DefaultEndpointsProtocol=https;" +
        "secret accountName and AccountKey"
    val containerReference: String = "some container name"

    // Retrieve storage account from connection-string.
    val storageAccount: CloudStorageAccount = CloudStorageAccount.parse(storageConnectionString);

    // Create the blob client.
    val blobClient: CloudBlobClient = storageAccount.createCloudBlobClient();

    // Get a reference to a container.
    // The container name must be lower case
    val container: CloudBlobContainer = blobClient.getContainerReference(containerReference);

    // Create the container if it does not exist.
    //container.createIfNotExists();

    val blob: CloudBlockBlob = container.getBlockBlobReference(outputPath);
    val source: File = new File(filename);
    blob.upload(new FileInputStream(source), source.length());
    filename
  }
}
