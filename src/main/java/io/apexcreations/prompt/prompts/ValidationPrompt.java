package io.apexcreations.prompt.prompts;

import io.apexcreations.ConversationContext;
import io.apexcreations.prompt.Prompt;

public abstract class ValidationPrompt implements Prompt {

    protected abstract boolean isInputValidated(ConversationContext conversationContext, String input);

    protected abstract Prompt processValidatedInput(ConversationContext conversationContext, String input);

    @Override
    public Prompt processInput(ConversationContext conversationContext, String input) {
        if (isInputValidated(conversationContext, input)) {
            return processValidatedInput(conversationContext, input);
        }
        return this;
    }

    @Override
    public boolean waitForInput(ConversationContext conversationContext) {
        return true;
    }
}
