package com.photogram.fake.api.modules.repositories;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.photogram.fake.api.modules.entities.domain.Post;
import com.photogram.fake.api.modules.entities.domain.User;
import com.photogram.fake.api.modules.exceptions.RepositoryException;
import com.photogram.fake.api.modules.stereotypes.Repository;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/*
 User's Repository
 */
@Repository
@AllArgsConstructor
@DefaultProperties(defaultFallback = "fallback")
public class UsersRepository {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private Gson gson;

    /*
     Gets users

     * @param userId a user id
     * @return a user
     * @throws RepositoryException a Repository Exception
     */
    @HystrixCommand
    public User get(long userId) throws RepositoryException {
        try {
            String url = String.format("https://jsonplaceholder.typicode.com/users/%d", userId);

            Thread.sleep(4000);

            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            return gson.fromJson(response.getBody(), User.class);
        } catch (Exception exc) {
            throw new RepositoryException("couldn't get the user", null);
        }
    }

    /*
    Gets posts from a fan

     * @param userId a user id
     * @return list of publications
     * @throws RepositoryException a Repository Exception
     */
    @HystrixCommand
    public List<Post> getPosts(long userId) throws RepositoryException {
        try {
            String url = String.format("https://jsonplaceholder.typicode.com/users/%d/posts", userId);

            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            Type postListType = new TypeToken<ArrayList<Post>>() {
            }.getType();

            return gson.fromJson(response.getBody(), postListType);
        } catch (Exception exc) {
            throw new RepositoryException("couldn't get user's posts", exc);
        }
    }

    private void fallback() {
        throw new RepositoryException("https://jsonplaceholder.typicode.com/users is too busy!", null);
    }
}
