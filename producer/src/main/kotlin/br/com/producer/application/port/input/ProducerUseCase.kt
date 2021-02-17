package br.com.producer.application.port.input

import br.com.producer.application.domain.EventDataDomain
import reactor.core.publisher.Mono

interface ProducerUseCase {

    fun produce(message: EventDataDomain)
}