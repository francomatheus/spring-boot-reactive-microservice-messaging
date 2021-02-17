package br.com.producer.adapter.input.model.request

import br.com.producer.application.domain.EventDataDomain
import javax.validation.constraints.NotBlank

data class MessagingRequest(
        @field: NotBlank
        val message: String
) {
    fun toEventDomain(): EventDataDomain {
        return EventDataDomain(message)
    }
}
