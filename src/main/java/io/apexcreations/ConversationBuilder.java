package io.apexcreations;

import io.apexcreations.cancelling.InactivityConversationCanceller;
import io.apexcreations.prompt.Prompt;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ConversationBuilder {

    private Prompt initialPrompt;
    private Map<Object, Object> sessionData;
    private long timeout;
    private TimeUnit timeUnit;
    private String exitCommand;
    private Conversable forWhom;

    public ConversationBuilder(Conversable forWhom) {
        this.forWhom = forWhom;
        this.initialPrompt = initialPrompt;
    }

    public ConversationBuilder forWhom(Conversable forWhom) {
        this.forWhom = forWhom;
        return this;
    }

    public ConversationBuilder initialPrompt(Prompt prompt) {
        this.initialPrompt = prompt;
        return this;
    }

    public ConversationBuilder sessionData(Map<Object, Object> sessionData) {
        this.sessionData = sessionData;
        return this;
    }

    public ConversationBuilder timeout(long timeout, TimeUnit timeUnit) {
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        return this;
    }

    private ConversationBuilder exitCommand(String exitCommand) {
        this.exitCommand = exitCommand;
        return this;
    }

    public Conversation build() {
        Conversation conversation = new Conversation(forWhom, initialPrompt, sessionData);
        conversation.addConversationCanceller(
                new InactivityConversationCanceller(timeout, timeUnit));
        return conversation;
    }
}
