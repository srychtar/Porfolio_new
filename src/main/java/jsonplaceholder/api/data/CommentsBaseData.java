package jsonplaceholder.api.data;

import jsonplaceholder.api.models.comments;

public class CommentsBaseData extends DataGeneratorBase {

    public comments createComments(){

        comments comment = new comments();
        comment.setPostId(faker().number().numberBetween(1,99));
        comment.setId(faker().number().numberBetween(1,99));
        comment.setName(faker().book().genre());
        comment.setEmail(faker().internet().emailAddress());
        comment.setBody(faker().book().title());
        return comment;
    }
}
