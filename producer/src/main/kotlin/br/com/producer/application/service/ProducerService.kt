package br.com.producer.application.service

import br.com.producer.application.domain.EventDataDomain
import br.com.producer.application.port.input.ProducerUseCase
import br.com.producer.application.port.output.ProducerPort
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class ProducerService(
        private val producerPort: ProducerPort
) : ProducerUseCase {

    override fun produce(message: EventDataDomain){
        return producerPort.produce(message)
    }

}