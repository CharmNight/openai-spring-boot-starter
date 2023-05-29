## OpenAI-Spring
基于[openai-java](https://github.com/TheoKanning/openai-java#deprecated-endpoints)项目做的修改
自用的一个starter

修改内容如下：

1. 项目基于SpringBoot， 开箱即用
2. 开放baseUrl配置及其他配置项
3. 优化代码结构



## 使用
### 自定义OpenAiService
```java
@EnableAi
public class OpenAiConfig {
    @Autowired
    DefaultProperties properties;

    @UseAiService
    public OpenAiService openAiService(){
        ObjectMapper mapper = defaultObjectMapper();
        OkHttpClient client = defaultClient(properties.getToken(), properties.getDefaultTimeout())
                .newBuilder()
                .build();
        Retrofit retrofit = defaultRetrofit(client, mapper, properties.getUrl());

        OpenAiApi api = retrofit.create(OpenAiApi.class);
        return new OpenAiService(api);
    }
}
```

### 使用GPT聊天
```java
@Autowired
private OpenAiService openaiAi;
public void demo(){
    String str = "hello";
    final List<ChatMessage> messages=new ArrayList<>();
    ChatMessage systemMessage=new ChatMessage(ChatMessageRole.SYSTEM.value(), str);
    messages.add(systemMessage);
    ChatCompletionRequest chatCompletionRequest=ChatCompletionRequest
    .builder()
    .model("gpt-3.5-turbo")
    .messages(messages)
    .n(1)
    .maxTokens(50)
    .logitBias(new HashMap<>())
    .build();

    openaiAi.streamChatCompletion(chatCompletionRequest)
    .doOnError(Throwable::printStackTrace)
    .blockingForEach(System.out::println);
}
```
