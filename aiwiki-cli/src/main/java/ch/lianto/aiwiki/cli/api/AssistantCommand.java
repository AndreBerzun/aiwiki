package ch.lianto.aiwiki.cli.api;

import ch.lianto.aiwiki.cli.service.CliContext;
import ch.lianto.aiwiki.engine.entity.Chat;
import ch.lianto.aiwiki.engine.entity.PageSegment;
import ch.lianto.aiwiki.engine.repository.PageSegmentRepository;
import ch.lianto.aiwiki.engine.service.assistant.AssistantService;
import ch.lianto.aiwiki.engine.service.assistant.Similarity;
import org.jline.terminal.Terminal;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.CommandAvailability;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Command
public class AssistantCommand {
    private final CliContext context;
    private final AssistantService assistantService;
    private final PageSegmentRepository pageSegmentRepo;
    private final Terminal terminal;

    public AssistantCommand(
        CliContext context,
        AssistantService assistantService,
        PageSegmentRepository pageSegmentRepo,
        Terminal terminal
    ) {
        this.context = context;
        this.assistantService = assistantService;
        this.pageSegmentRepo = pageSegmentRepo;
        this.terminal = terminal;
    }

    @Command(command = "assistant", description = "Enter assistant mode and ask free flowing questions")
    @CommandAvailability(provider = "projectAvailability")
    public String enterAssistantMode() {
        context.setAssistantMode(true);
        return "Welcome, go ahead and start asking questions:";
    }

    @Command(command = "assistant ask", alias = ">", description = "Ask the wiki any questions")
    @CommandAvailability(provider = "projectAvailability")
    public void ask(String prompt) {
        Chat chat = context.getAssistantChat().question(prompt);

        chat = assistantService.ask(chat, context.getSelectedProject().getName());

        printAnswerChunks(chat);
    }

    private void printAnswerChunks(Chat chat) {
        chat.getLatestAnswerChunks().toStream().forEach(chunk -> {
            terminal.writer().print(chunk);
            terminal.writer().flush();
        });
        terminal.writer().println();
    }

    @Command(command = "assistant search", description = "Search the wiki for page segments that match the prompt")
    @CommandAvailability(provider = "projectAvailability")
    public String search(String phrase) {
        List<Similarity<PageSegment>> segments = pageSegmentRepo.findBySimilarity(phrase, context.getSelectedProject());
        return matchingSegmentsToStringOverview(segments);
    }

    private String matchingSegmentsToStringOverview(List<Similarity<PageSegment>> segments) {
        StringBuilder sb = new StringBuilder();

        appendSummary(sb, segments);
        for (int i = 0; i < segments.size(); i++)
            appendSegment(sb, i, segments.get(i));

        return sb.toString();
    }

    private void appendSummary(StringBuilder sb, List<Similarity<PageSegment>> segments) {
        sb.append(String.format("# Found %d matching page segments:", segments.size()));
        sb.append(System.lineSeparator());
        sb.append(System.lineSeparator());
    }

    private void appendSegment(StringBuilder sb, int index, Similarity<PageSegment> segmentSimilarity) {
        sb.append(String.format("## %d. %s (Sim: %f)", index + 1, segmentSimilarity.data().getPage().getName(), segmentSimilarity.similarity()));
        sb.append(System.lineSeparator());
        sb.append("-----------------------");
        sb.append(System.lineSeparator());
        sb.append(segmentSimilarity.data().getText());
        sb.append(System.lineSeparator());
        sb.append(System.lineSeparator());
        sb.append("#############################################################################");
        sb.append(System.lineSeparator());
        sb.append(System.lineSeparator());
    }
}
