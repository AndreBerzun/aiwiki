package ch.lianto.aiwiki.cli.api;

import ch.lianto.aiwiki.cli.service.CliContext;
import ch.lianto.aiwiki.engine.entity.Chat;
import ch.lianto.aiwiki.engine.entity.PageChunk;
import ch.lianto.aiwiki.engine.policy.assistant.AssistantService;
import ch.lianto.aiwiki.engine.policy.assistant.Similarity;
import org.jline.terminal.Terminal;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.CommandAvailability;
import org.springframework.shell.command.annotation.Option;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Command
public class AssistantCommand {
    private final CliContext context;
    private final AssistantService assistantService;
    private final Terminal terminal;

    public AssistantCommand(
        CliContext context,
        AssistantService assistantService,
        Terminal terminal
    ) {
        this.context = context;
        this.assistantService = assistantService;
        this.terminal = terminal;
    }

    @Command(command = "assistant", description = "Enter assistant mode and ask free flowing questions")
    public String enterAssistantMode() {
        context.setAssistantMode(true);
        return "Welcome, go ahead and start asking questions:";
    }

    @Command(command = "assistant ask", description = "Ask the wiki any questions")
    public void ask(String prompt) {
        Chat chat = context.getAssistantChat().question(prompt);

        chat = assistantService.ask(chat);

        printAnswerChunks(chat);
    }

    private void printAnswerChunks(Chat chat) {
        chat.getLatestAnswerChunks().toStream().forEach(chunk -> {
            terminal.writer().print(chunk);
            terminal.writer().flush();
        });
        terminal.writer().println();
    }

    @Command(command = "assistant search", description = "Search the wiki for page chunks that match the prompt")
    public String search(
        String phrase,
        @Option(shortNames = 't', defaultValue = "false") String showChunkText
    ) {
        List<Similarity<PageChunk>> foundChunks = assistantService.search(phrase);
        return mapFoundChunksToStringResultList(foundChunks, Boolean.parseBoolean(showChunkText));
    }

    private String mapFoundChunksToStringResultList(List<Similarity<PageChunk>> chunks, boolean showChunkText) {
        StringBuilder sb = new StringBuilder();

        appendSummary(sb, chunks);
        for (int i = 0; i < chunks.size(); i++)
            appendChunk(sb, i, chunks.get(i), showChunkText);

        return sb.toString();
    }

    private void appendSummary(StringBuilder sb, List<Similarity<PageChunk>> chunks) {
        sb.append(String.format("# Found %d matching page chunks:", chunks.size()));
        sb.append(System.lineSeparator());
        sb.append(System.lineSeparator());
    }

    private void appendChunk(StringBuilder sb, int index, Similarity<PageChunk> chunkSimilarity, boolean showChunkText) {
        sb.append(String.format("## %d. %s (Sim: %f)", index + 1, chunkSimilarity.data().getPage().getName(), chunkSimilarity.similarity()));
        sb.append(System.lineSeparator());
        if (showChunkText) {
            sb.append("-----------------------");
            sb.append(System.lineSeparator());
            sb.append(chunkSimilarity.data().getText());
            sb.append(System.lineSeparator());
            sb.append(System.lineSeparator());
        }
        sb.append("#############################################################################");
        sb.append(System.lineSeparator());
        sb.append(System.lineSeparator());
    }
}
