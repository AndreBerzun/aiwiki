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
        Given the following chat between a user and a software assistant, briefly rephrase the users last question so that it contains the context of the entire chat.
        %s
        """;
    public static final String POLARIZE_SYSTEM_MESSAGE = """
        You are a text polarizer.
        Your job is to identify the semantically strongest parts of a user provided text, filter out any noise text and create a list of tags that describe the chunk.
        If specific technologies, characters, events, dates etc. are mentioned, include them with the tag phrase.
        When for example processing a Java installation guide, don't just write "Installing Java" but rather "Installing Java on Ubuntu 22.4 using the official repo" for example.
        Or instead of just writing "Jimmy says farewell and dies " when processing a character death, rather write "Jimmy's final words before being slain by Steve".
        Always return a JSON array of plain strings with up to three elements.
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

    public static String polarize(String text) {
        return "Polarize the following text and return a JSON array of strings:\n\n" + text;
    }
}
