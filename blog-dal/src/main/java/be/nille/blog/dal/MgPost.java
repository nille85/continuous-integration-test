/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.blog.dal;

import be.nille.blog.domain.post.Post;
import be.nille.blog.domain.post.Comment;
import be.nille.blog.domain.post.Content;
import be.nille.blog.domain.post.Post.Status;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import lombok.Getter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.utils.IndexType;

/**
 *
 * @author Niels Holvoet
 */
@Entity(value="post",noClassnameStored = true)
@Indexes(
        @Index(fields = @Field(value = "content", type = IndexType.TEXT))
       
)
@Getter
@ToString
public class MgPost implements Post {

    @Id
    private ObjectId id;

    @Reference
    private MgAuthor author;

    @Embedded
    private Content content;

    @Reference
    private MgCategory category;

    @Embedded
    private List<Comment> comments;
    
    private Status status;
    
    private final Date createdDate;

    /*
    *For Morphia
    */
    public MgPost() {
        this(null,null,null);
    }

    public MgPost(final MgCategory category, final MgAuthor author, final Content content) {
        this.content = content;
        this.comments = new ArrayList<>();
        this.author = author;
        this.category = category;
        this.status = Status.DRAFT;
        this.createdDate = new Date();
    }
    
    @Override
    public void publish(){
        this.status = Status.PUBLISHED;
    }

    @Override
    public void addComment(Comment comment) {
        comments.add(0,comment);
    }

    @Override
    public String getId() {
        return id.toHexString();
    }

    
    

}
