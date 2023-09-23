package com.example.demo;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Here we tell spring data to look for interfaces that extends spring data repository,
 * then add to them implementation and instantiate them.
 * Also, here we sets up the AmazonDynamoDB client, which is the object that we (spring data) use in order
 * to make calls to DynamoDB.
 */
@Configuration
@EnableDynamoDBRepositories
public class DynamoDBConfiguration {


    /**
     * sets up the AmazonDynamoDB client, which is the object that spring data use in order
     * to make calls to DynamoDB. It needs aws credentials, so it calls under the hood to awsCredentials method.
     */
    @Bean
    public AmazonDynamoDB amazonDynamoDB(AWSCredentials awsCredentials, @Value("${aws.dynamoDBUrl}") String dynamoDBUrl) {


        // TODO: the signing region needs to be the same region where the dynamo db service is.
        AwsClientBuilder.EndpointConfiguration endpointConf = new AwsClientBuilder.EndpointConfiguration(dynamoDBUrl, "us-west-2");
        AWSStaticCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(awsCredentials);


        AmazonDynamoDBClientBuilder builder = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(endpointConf)
                .withCredentials(credentialsProvider);

        return builder.build();


    }


    @Bean
    public AWSCredentials awsCredentials(@Value("${aws.accessKey}") String accessKey,
                                         @Value("${aws.secretKey}") String secretKey) {
        return new BasicAWSCredentials(accessKey, secretKey);
    }

}
