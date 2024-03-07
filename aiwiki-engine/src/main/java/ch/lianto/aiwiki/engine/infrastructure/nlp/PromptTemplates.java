package ch.lianto.aiwiki.engine.infrastructure.nlp;

import ch.lianto.aiwiki.engine.entity.Chat;

public class PromptTemplates {
    public static final String RAG_TEMPLATE = """
        You're an assistant that helps users answer questions related to their private wikis.
        Be brief in your responses. Answer ONLY with the facts listed in the sources below.
        If there isn't enough information to answer the question, say you don't know. Ask clarifying questions though, if needed.
        Each source contains a page name surrounded by brackets followed by the actual source text.
                
        Sources:
        %s
        """;
    public static final String SUMMARY_TEMPLATE = """
        Given the following chat between a user and a software assistant, briefly rephrase the users last question so that it contains the context of the entire chat:
        %s
        """;

    public static String rag(String[] context) {
        return String.format(
            RAG_TEMPLATE,
            context.length == 0 ? "None" : String.join("\n", context)
        );
    }

    public static String summary(Chat chat) {
        return String.format(SUMMARY_TEMPLATE, chat.toString());
    }
}
