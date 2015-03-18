package onl.jon.controller;

import onl.jon.domain.Comment;
import onl.jon.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @RequestMapping()
    @ResponseBody
    public List<Comment> approved() {
        return commentRepository.findByApprovedTrue();
    }

    @RequestMapping("/unapproved")
    public String unapproved(Model model) {
        model.addAttribute("comments", commentRepository.findByApprovedFalse());
        return "comments/unapproved";
    }

    @RequestMapping("/approve/{Comment_ID}")
    @ResponseStatus(value = HttpStatus.OK)
    public void approve(@PathVariable(value = "Comment_ID") Long id) {
        Comment comment = commentRepository.findById(id);
        comment.setApproved(true);
        commentRepository.save(comment);
    }

    @RequestMapping("/add")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void add() {
        Comment comment = new Comment();
        comment.setTitle("comment title");
        comment.setDetail("some detail related to the comment");
        commentRepository.save(comment);
    }
}