package world.startoy.polling.adapter.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import world.startoy.polling.usecase.UserService;
import world.startoy.polling.usecase.VoteService;
import world.startoy.polling.usecase.dto.VoteCreateRequest;
import world.startoy.polling.usecase.dto.VoteCreateResponse;


@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/vote")
@Tag(name = "Vote", description = "Vote 투표 행위 기록 API")
public class VoteController {

    private final VoteService voteService;
    private final UserService userService;


    @PostMapping
    public VoteCreateResponse createVote(@RequestBody VoteCreateRequest request, HttpServletRequest httpRequest) {
        String voterIp = userService.getClientIp(httpRequest);
        return voteService.createVote(request, voterIp);
    }

    @DeleteMapping("/{voteUid}")
    public ResponseEntity<String> deleteVote(@PathVariable String voteUid, HttpServletRequest httpRequest) {
        String voterIp = userService.getClientIp(httpRequest);
        voteService.deleteVote(voteUid, voterIp);
        return ResponseEntity.ok("투표 취소가 완료됐습니다.");
    }
}

