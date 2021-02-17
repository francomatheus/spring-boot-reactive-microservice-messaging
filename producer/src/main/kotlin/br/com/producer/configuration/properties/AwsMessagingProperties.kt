package br.com.producer.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "aws.cloud")
data class AwsMessagingProperties(
        val accessKey: String,
        val secretKey: String,
        val region: String,
        val topic: String
)