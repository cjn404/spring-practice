package org.example.springpractice.memo.repository;

import org.example.springpractice.memo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MemoRepository extends JpaRepository<Memo, Long> {
}
