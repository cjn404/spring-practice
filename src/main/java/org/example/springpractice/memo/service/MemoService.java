package org.example.springpractice.memo.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.springpractice.memo.dto.MemoRequest;
import org.example.springpractice.memo.dto.MemoResponse;
import org.example.springpractice.memo.entity.Memo;
import org.example.springpractice.memo.repository.MemoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional
    public MemoResponse save(MemoRequest memoRequest) {
        Memo savedmemo = memoRepository.save(new Memo(memoRequest.getTitle(), memoRequest.getContent()));
        return new MemoResponse(savedmemo.getId(), savedmemo.getTitle(), savedmemo.getContent());
    }

    @Transactional(readOnly = true)
    public List<MemoResponse> findMemos() {
        List<Memo> memos = memoRepository.findAll();
        List<MemoResponse> dtos = new ArrayList<>();

        for (Memo memo : memos) {
            MemoResponse memoResponse = new MemoResponse(
                    memo.getId(),
                    memo.getTitle(),
                    memo.getContent()
            );
            dtos.add(memoResponse);
        }
        return dtos;
    }

    @Transactional
    public MemoResponse update(Long memoId, MemoRequest memoRequest) {
        Memo memo = memoRepository.findById(memoId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 memoID가 없습니다.")
        );
        memo.updateMemo(memoRequest.getTitle(), memoRequest.getContent());
        return new MemoResponse(
                memo.getId(),
                memo.getTitle(),
                memo.getContent()
        );
    }

    @Transactional
    public void deleteMemo(Long memoId) {
        boolean b = memoRepository.existsById(memoId);
        if (!b) {
            throw new IllegalArgumentException("해당하는 memoID가 없습니다.");
        }
        memoRepository.deleteById(memoId);
    }

    @Transactional(readOnly = true)
    public MemoResponse findMemo(Long memoId) {

        Memo memo = memoRepository.findById(memoId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 memoID가 없습니다.")
        );
        return new MemoResponse(memo.getId(), memo.getTitle(), memo.getContent());
    }
}
