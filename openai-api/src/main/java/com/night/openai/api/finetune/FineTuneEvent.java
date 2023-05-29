package com.night.openai.api.finetune;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * An object representing an event in the lifecycle of a fine-tuning job
 *
 * https://beta.openai.com/docs/api-reference/fine-tunes
 */
@Data
public class FineTuneEvent {
    /**
     * The type of object returned, should be "fine-tune-event".
     */
    String object;

    /**
     * The creation time in epoch seconds.
     */
    @JsonProperty("created_at")
    Long createdAt;

    /**
     * The log level of this message.
     */
    String level;

    /**
     * The event message.
     */
    String message;
}
