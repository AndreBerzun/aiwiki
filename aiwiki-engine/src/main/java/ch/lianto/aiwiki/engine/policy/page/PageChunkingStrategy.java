package ch.lianto.aiwiki.engine.policy.page;

import java.util.List;

public interface PageChunkingStrategy {
    List<String> split(String text);
}
