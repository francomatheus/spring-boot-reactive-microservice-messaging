package br.com.producer.adapter.output.producer.notify

import br.com.producer.application.domain.EventDataDomain
import br.com.producer.application.port.output.ProducerPort
import br.com.producer.configuration.properties.AwsMessagingProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate
import org.springframework.stereotype.Component

@Component
class EventProducer(
        @Autowired private val notificationMessagingTemplate: NotificationMessagingTemplate,
        private val awsMessagingProperties: AwsMessagingProperties
) : ProducerPort {

    override fun produce(event: EventDataDomain) {
        return notificationMessagingTemplate.convertAndSend(
                awsMessagingProperties.topic, event.message
        )
    }
}