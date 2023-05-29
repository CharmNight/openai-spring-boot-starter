package com.night.openai.service.server.ai.openai;


import com.night.openai.api.DeleteResult;
import com.night.openai.api.OpenAiResponse;
import com.night.openai.api.completion.CompletionRequest;
import com.night.openai.api.completion.CompletionResult;
import com.night.openai.api.completion.chat.ChatCompletionRequest;
import com.night.openai.api.completion.chat.ChatCompletionResult;
import com.night.openai.api.edit.EditRequest;
import com.night.openai.api.edit.EditResult;
import com.night.openai.api.embedding.EmbeddingRequest;
import com.night.openai.api.embedding.EmbeddingResult;
import com.night.openai.api.engine.Engine;
import com.night.openai.api.file.File;
import com.night.openai.api.finetune.FineTuneEvent;
import com.night.openai.api.finetune.FineTuneRequest;
import com.night.openai.api.finetune.FineTuneResult;
import com.night.openai.api.image.CreateImageRequest;
import com.night.openai.api.image.ImageResult;
import com.night.openai.api.model.Model;
import com.night.openai.api.moderation.ModerationRequest;
import com.night.openai.api.moderation.ModerationResult;
import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface OpenAiApi {

    @GET("v1/models")
    Single<OpenAiResponse<Model>> listModels();

    @GET("/v1/models/{model_id}")
    Single<Model> getModel(@Path("model_id") String modelId);

    @POST("/v1/completions")
    Single<CompletionResult> createCompletion(@Body CompletionRequest request);

    @Streaming
    @POST("/v1/completions")
    Call<ResponseBody> createCompletionStream(@Body CompletionRequest request);
    
    @POST("/v1/chat/completions")
    Single<ChatCompletionResult> createChatCompletion(@Body ChatCompletionRequest request);

    @Streaming
    @POST("/v1/chat/completions")
	Call<ResponseBody> createChatCompletionStream(@Body ChatCompletionRequest request);

    @Deprecated
    @POST("/v1/engines/{engine_id}/completions")
    Single<CompletionResult> createCompletion(@Path("engine_id") String engineId, @Body CompletionRequest request);

    @POST("/v1/edits")
    Single<EditResult> createEdit(@Body EditRequest request);

    @Deprecated
    @POST("/v1/engines/{engine_id}/edits")
    Single<EditResult> createEdit(@Path("engine_id") String engineId, @Body EditRequest request);

    @POST("/v1/embeddings")
    Single<EmbeddingResult> createEmbeddings(@Body EmbeddingRequest request);

    @Deprecated
    @POST("/v1/engines/{engine_id}/embeddings")
    Single<EmbeddingResult> createEmbeddings(@Path("engine_id") String engineId, @Body EmbeddingRequest request);

    @GET("/v1/files")
    Single<OpenAiResponse<File>> listFiles();

    @Multipart
    @POST("/v1/files")
    Single<File> uploadFile(@Part("purpose") RequestBody purpose, @Part MultipartBody.Part file);

    @DELETE("/v1/files/{file_id}")
    Single<DeleteResult> deleteFile(@Path("file_id") String fileId);

    @GET("/v1/files/{file_id}")
    Single<File> retrieveFile(@Path("file_id") String fileId);

    @POST("/v1/fine-tunes")
    Single<FineTuneResult> createFineTune(@Body FineTuneRequest request);

    @POST("/v1/completions")
    Single<CompletionResult> createFineTuneCompletion(@Body CompletionRequest request);

    @GET("/v1/fine-tunes")
    Single<OpenAiResponse<FineTuneResult>> listFineTunes();

    @GET("/v1/fine-tunes/{fine_tune_id}")
    Single<FineTuneResult> retrieveFineTune(@Path("fine_tune_id") String fineTuneId);

    @POST("/v1/fine-tunes/{fine_tune_id}/cancel")
    Single<FineTuneResult> cancelFineTune(@Path("fine_tune_id") String fineTuneId);

    @GET("/v1/fine-tunes/{fine_tune_id}/events")
    Single<OpenAiResponse<FineTuneEvent>> listFineTuneEvents(@Path("fine_tune_id") String fineTuneId);

    @DELETE("/v1/models/{fine_tune_id}")
    Single<DeleteResult> deleteFineTune(@Path("fine_tune_id") String fineTuneId);

    @POST("/v1/images/generations")
    Single<ImageResult> createImage(@Body CreateImageRequest request);

    @POST("/v1/images/edits")
    Single<ImageResult> createImageEdit(@Body RequestBody requestBody);

    @POST("/v1/images/variations")
    Single<ImageResult> createImageVariation(@Body RequestBody requestBody);

    @POST("/v1/moderations")
    Single<ModerationResult> createModeration(@Body ModerationRequest request);

    @Deprecated
    @GET("v1/engines")
    Single<OpenAiResponse<Engine>> getEngines();

    @Deprecated
    @GET("/v1/engines/{engine_id}")
    Single<Engine> getEngine(@Path("engine_id") String engineId);
}
