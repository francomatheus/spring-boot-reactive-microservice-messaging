package br.com.producer.application.port.output

import br.com.producer.application.domain.EventDataDomain
import reactor.core.publisher.Mono

interface ProducerPort {

    fun produce(message: EventDataDomain)
}