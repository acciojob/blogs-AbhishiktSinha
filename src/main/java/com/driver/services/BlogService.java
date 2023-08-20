package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    UserRepository userRepository1;

    public Blog createAndReturnBlog(Integer userId, String title, String content){
        //create a blog at the current time
        Blog blog = new Blog(title, content);

        if(userId != null ) {
            Optional<User> optionalUser = userRepository1.findById(userId);
            User user = optionalUser.get();

            if (user != null) {
                blog.setUser(user);
                List<Blog> userBlogList = user.getBlogList();
                userBlogList.add(blog);
                user.setBlogList(userBlogList);

                userRepository1.save(user);
            }
        }
        else {
            blogRepository1.save(blog);
        }

        return blog;

    }

    public void deleteBlog(int blogId) {
        //delete blog and corresponding images
        if(blogRepository1.findById(blogId).isPresent() == false) return;
       userRepository1.deleteById(blogId);
    }
}
