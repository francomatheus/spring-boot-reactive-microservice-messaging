package br.com.producer.adapter.input.controller

import br.com.producer.adapter.input.model.request.MessagingRequest
import br.com.producer.application.port.input.ProducerUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import reactor.core.publisher.Mono
import javax.validation.Valid

@Controller
@RequestMapping("/v1")
class ProducerController(
        private val producerUseCase: ProducerUseCase
) {

    @PostMapping("/producer")
    fun producer(
            @Valid @RequestBody messagingRequest: MessagingRequest
    ): ResponseEntity<String> {
        producerUseCase.produce(messagingRequest.toEventDomain())
        return ResponseEntity.status(HttpStatus.OK).body("Mensagem enviada")
    }
}