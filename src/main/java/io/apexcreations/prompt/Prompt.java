package io.apexcreations.prompt;

import io.apexcreations.ConversationContext;

public interface Prompt extends Cloneable {

    Prompt FINAL_PROMPT = null;

    Prompt processInput(ConversationContext conversationContext, String input);

    boolean waitForInput(ConversationContext conversationContext);

    String getPromptText(ConversationContext conversationContext);

}