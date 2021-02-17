package br.com.producer.configuration

import br.com.producer.configuration.properties.AwsMessagingProperties
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.sns.AmazonSNS
import com.amazonaws.services.sns.AmazonSNSClientBuilder
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AwsMessagingConfiguration(
        private val awsMessagingProperties: AwsMessagingProperties
) {

    fun getCredentials(): AWSStaticCredentialsProvider {
        return AWSStaticCredentialsProvider(
                BasicAWSCredentials(awsMessagingProperties.accessKey,
                        awsMessagingProperties.secretKey)
        )
    }

    @Bean
    fun amazonSNS(): AmazonSNS {
        return AmazonSNSClientBuilder
                .standard()
                .withCredentials(getCredentials())
                .withRegion(awsMessagingProperties.region)
                .build()
    }

    @Bean
    fun notificationMessagingTemplate(): NotificationMessagingTemplate {
        return NotificationMessagingTemplate(amazonSNS())
    }
}