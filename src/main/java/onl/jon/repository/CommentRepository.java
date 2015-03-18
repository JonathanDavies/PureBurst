package onl.jon.repository;

import onl.jon.domain.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findAll();

    List<Comment> findByApprovedTrue();

    List<Comment> findByApprovedFalse();

    Comment findById(Long Id);
}