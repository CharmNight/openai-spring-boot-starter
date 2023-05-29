package com.night.openai.api.completion;

import lombok.Data;

/**
 * A completion generated by OpenAI
 *
 * https://beta.openai.com/docs/api-reference/completions/create
 */
@Data
public class CompletionChoice {
    /**
     * 生成的文本。将包括提示，如果  {@link CompletionRequest#echo } 为 true
     */
    String text;

    /**
     * 此完成的索引在返回的列表中。
     */
    Integer index;

    /**
     * The log probabilities of the chosen tokens and the top {@link CompletionRequest#logprobs} tokens
     */
    LogProbResult logprobs;

    /**
     * The reason why GPT stopped generating, for example "length".
     */
    String finish_reason;
}