package br.com.consumer.adapter.input.listener

import br.com.consumer.configuration.properties.AwsMessagingProperties
import org.slf4j.LoggerFactory
import org.springframework.cloud.aws.messaging.listener.Acknowledgment
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.lang.RuntimeException

@Component
class SqsListener(
        private val awsMessagingProperties: AwsMessagingProperties
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @SqsListener("sqs-example-terraform", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    fun listener(message: String){
        logger.info("Message= {}", message)
        Mono.just(message)
                .doOnSuccess {
                    logger.info("Message consuming with success")
                }
                .doOnError {
                    Mono.error<RuntimeException>(RuntimeException("Error when consumer queue."))
                }
                .subscribe()
    }
}