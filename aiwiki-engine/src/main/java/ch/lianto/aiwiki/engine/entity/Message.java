package ch.lianto.aiwiki.engine.entity;

public record Message(String text, Role role) {

    public enum Role {
        USER, ASSISTANT
    }
}
