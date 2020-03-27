package example.localstack.s3;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Runs the Amazon S3 localstack example.
 */
public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    private static final Regions REGION = Regions.DEFAULT_REGION;
    private static final String BUCKET_NAME = "";

    public static void main(String... args) throws Exception {
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(REGION)
                .build();

        runUploadExample(s3Client);
    }

    private static void runUploadExample(AmazonS3 s3Client) {
        LOG.info("Running the Upload Example...");

        try {
            s3Client.putObject(bucketName, stringObjKeyName, "Uploaded String Object");

            PutObjectRequest request = new PutObjectRequest(bucketName, fileObjKeyName, new File(fileName));
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType("plain/text");
            metadata.addUserMetadata("x-amz-meta-title", "someTitle");
            request.setMetadata(metadata);

            s3Client.putObject(request);
        } catch (SdkClientException e) {
            LOG.error("Error occurred during Upload Example", e);
        } finally {
            LOG.info("Upload Example Complete");
        }
    }

    private static void runMultipartUploadExample(AmazonS3Client s3Client) {
        LOG.info("Running the Multipart Upload Example...");
    }
}
