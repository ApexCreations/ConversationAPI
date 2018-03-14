package io.apexcreations.prompt.prompts;

import io.apexcreations.ConversationContext;
import io.apexcreations.prompt.Prompt;

public abstract class InputIndependentPrompt implements Prompt {

    @Override
    public boolean waitForInput(ConversationContext conversationContext) {
        return false;
    }
}
