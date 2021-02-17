package br.com.consumer.configuration

import br.com.consumer.configuration.properties.AwsMessagingProperties
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.sqs.AmazonSQSAsync
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service

@Service
class AwsMessagingConfiguration(
        private val awsMessagingProperties: AwsMessagingProperties
) {

    @Bean
    fun amazonSQS(): AmazonSQSAsync? {
        return AmazonSQSAsyncClientBuilder
                .standard()
                .withCredentials(getCredentials())
                .withRegion(awsMessagingProperties.region)
                .build()
    }

    fun getCredentials(): AWSStaticCredentialsProvider {
        return AWSStaticCredentialsProvider(
                BasicAWSCredentials(awsMessagingProperties.accessKey,awsMessagingProperties.secretKey))
    }

//    @Bean
//    fun queueMessageTemplate(): QueueMessagingTemplate {
//        return QueueMessagingTemplate(this.amazonSQS())
//    }



}