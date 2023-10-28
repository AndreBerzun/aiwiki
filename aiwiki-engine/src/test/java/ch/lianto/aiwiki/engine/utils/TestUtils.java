package ch.lianto.aiwiki.engine.utils;

import com.thedeanda.lorem.LoremIpsum;

public class TestUtils {
    public static String loremIpsumWithWordCount(int count) {
        LoremIpsum loremIpsum = LoremIpsum.getInstance();
        return loremIpsum.getWords(count);
    }
}
