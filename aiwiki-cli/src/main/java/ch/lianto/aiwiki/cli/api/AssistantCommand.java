package ch.lianto.aiwiki.cli.api;

import ch.lianto.aiwiki.cli.service.CliContext;
import ch.lianto.aiwiki.engine.entity.PageSegment;
import ch.lianto.aiwiki.engine.repository.PageSegmentRepository;
import ch.lianto.aiwiki.engine.service.assistant.AssistantService;
import ch.lianto.aiwiki.engine.service.assistant.Similarity;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.CommandAvailability;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Command(command = "assistant")
public class AssistantCommand {
    private final CliContext context;
    private final AssistantService assistantService;
    private final PageSegmentRepository pageSegmentRepo;

    public AssistantCommand(CliContext context, AssistantService assistantService, PageSegmentRepository pageSegmentRepo) {
        this.context = context;
        this.assistantService = assistantService;
        this.pageSegmentRepo = pageSegmentRepo;
    }

    @Command(command = "ask", description = "Ask the wiki any questions")
    @CommandAvailability(provider = "projectAvailability")
    public String ask(String prompt) {
        return assistantService.ask(prompt, context.getSelectedProject().getName());
    }

    @Command(command = "search", description = "Search the wiki for page segments that match the prompt")
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
