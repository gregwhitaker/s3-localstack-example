package example.localstack.s3;

import com.amazonaws.SdkClientException;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;

/**
 * Runs the Amazon S3 localstack example.
 */
public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String... args) throws Exception {
        final String bucketName = "com.github.gregwhitaker.example";
        final File uploadFile = new File(ClassLoader.getSystemClassLoader().getResource("cat.jpeg").getFile());

        AmazonS3 s3Client;
        if (isRunWithLocalStack(args)) {
            s3Client = AmazonS3ClientBuilder.standard()
                    .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:4572", "us-west-2"))
                    .withPathStyleAccessEnabled(true)
                    .build();

            s3Client.createBucket(bucketName);
        } else {
            s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_WEST_2)
                .build();
        }

        runUploadExample(s3Client, bucketName, uploadFile);
        runMultipartUploadExample(s3Client, bucketName, uploadFile);
    }

    private static void runUploadExample(AmazonS3 s3Client, String bucketName, File uploadFile) {
        LOG.info("Running the Upload Example...");

        final String s3Key = "basic/cat.jpeg";

        try {
            PutObjectResult putObjectResult = s3Client.putObject(bucketName, s3Key, uploadFile);
        } catch (SdkClientException e) {
            LOG.error("Error occurred during Upload Example", e);
        } finally {
            LOG.info("Upload Example Complete");
        }
    }

    private static void runMultipartUploadExample(AmazonS3 s3Client, String bucketName, File uploadFile){
        LOG.info("Running the Multipart Upload Example...");

        final String s3Key = "multipart/cat.jpeg";

        try {
            final S3WritableByteChannel s3Channel = new S3WritableByteChannel(s3Client, bucketName, s3Key);

            FileChannel fileChannel = FileChannel.open(uploadFile.toPath(), StandardOpenOption.READ);

            ByteBuffer buf = ByteBuffer.allocate(256);
            while (fileChannel.read(buf) > 0) {
                buf.flip();
                s3Channel.write(buf);
                buf.clear();
            }

            s3Channel.close();
        } catch (IOException e) {
            LOG.error("Error occurred during Multipart Upload Example", e);
        } finally {
            LOG.info("Multipart Upload Example Complete");
        }
    }

    private static boolean isRunWithLocalStack(String... args) {
        return args.length == 0;
    }
}
