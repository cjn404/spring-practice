package org.example.springpractice.controller;

import lombok.RequiredArgsConstructor;
import org.example.springpractice.dto.MemberRequest;
import org.example.springpractice.dto.MemberResponse;
import org.example.springpractice.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members")
    public MemberResponse createMember(
            @RequestBody MemberRequest memberRequest
    ) {
        return memberService.save(memberRequest);
    }

    @GetMapping("/members")
    public List<MemberResponse> getMembers() {
        return memberService.findMembers();}

    @GetMapping("/member/{memberId}")
    public MemberResponse getMember(
            @PathVariable Long memberId
    ) {
        return memberService.findMember(memberId);
    }

    @PutMapping("/member/{memberId}")
    public MemberResponse updateMember(
            @PathVariable Long memberId,
            @RequestBody MemberRequest memberRequest
    ) {
        return memberService.update(memberId, memberRequest);
    }

    @DeleteMapping("/member/{memberId}")
    public void deleteMember(
            @PathVariable Long memberId
    ) {
        memberService.deleteMember(memberId);
    }

}
