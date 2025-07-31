package org.example.springpractice.memo.controller;

import lombok.RequiredArgsConstructor;
import org.example.springpractice.memo.dto.MemoRequest;
import org.example.springpractice.memo.dto.MemoResponse;
import org.example.springpractice.memo.service.MemoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @PostMapping("/memos")
    public ResponseEntity<MemoResponse> createMemo(
            @RequestBody MemoRequest memoRequest
    ) {
        return ResponseEntity.ok(memoService.save(memoRequest));
    }

    @GetMapping("/memos")
    public ResponseEntity<List<MemoResponse>> getMemos() {
        return ResponseEntity.ok(memoService.findMemos());
    }

    @GetMapping("/memos/{memoId}")
    public ResponseEntity<MemoResponse> getMemo(
            @PathVariable Long memoId
    ) {
        return ResponseEntity.ok(memoService.findMemo(memoId));
    }

    @PutMapping("/memos/{memoId}")
    public ResponseEntity<MemoResponse> updateMemo(
            @PathVariable Long memoId,
            @RequestBody MemoRequest memoRequest
    ) {
        return ResponseEntity.ok(memoService.update(memoId, memoRequest));
    }

    @DeleteMapping("/memos/{memoId}")
    public ResponseEntity<Void> deleteMemo(
            @PathVariable Long memoId
    ) {
        memoService.deleteMemo(memoId);
        return ResponseEntity.ok().build();
    }
}
