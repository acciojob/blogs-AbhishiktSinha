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

    public Blog createAndReturnBlog(Integer userId, String title, String content)throws Exception {
        //create a blog at the current time
        Optional<User> optionalUser = userRepository1.findById(userId);
        if(!optionalUser.isPresent()) {
            throw new Exception("User not found");
        }
        else {
            Blog blog = new Blog(title, content);
            User user = optionalUser.get();

            blog.setUser(user);
            List<Blog> userBlogList = user.getBlogList();
            userBlogList.add(blog);
            user.setBlogList(userBlogList);

            userRepository1.save(user);

            return blog;
        }
    }

    public void deleteBlog(int blogId)throws Exception{
        //delete blog and corresponding images
        Optional<Blog> optionalBlog = blogRepository1.findById(blogId);
        if(!optionalBlog.isPresent()) {
            throw new Exception("Blog not found");
        }
        else
            userRepository1.deleteById(blogId);
    }
}
