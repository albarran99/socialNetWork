package org.iesfm.socialnetwork;

import org.iesfm.socialnetwork.excepcions.TagNotFoundExcepcion;
import org.iesfm.socialnetwork.excepcions.UserNotFoundExcepcion;

import java.util.*;

public class SocialNetWork implements ISocialNetWork {
    private String name;
    private Map<String, User> users;
    private LinkedList<String> trendingTopics;

    public SocialNetWork(String name, Map<String, User> users, LinkedList<String> trendingTopics) {
        this.name = name;
        this.users = users;
        this.trendingTopics = trendingTopics;
    }

    @Override
    public TreeSet<Post> getPostByUser(String username) throws UserNotFoundExcepcion {
        User findUser = users.get(username);
        if (findUser == null) {
            throw new UserNotFoundExcepcion();
        }
        return findUser.getWall();
    }

    @Override
    public TreeSet<Post> getPostByUserAndTag(String username, String userTag) throws UserNotFoundExcepcion, TagNotFoundExcepcion {
        TreeSet<Post> wallPost = new TreeSet<>();

        for (Post userPost : getPostByUser(username)) {
            if (userPost.getTag().contains(userTag)) {
                wallPost.add(userPost);
            }
            if (userTag == null) {
                throw new TagNotFoundExcepcion();
            }
        }

        return wallPost;
    }

    @Override
    public TreeSet<Post> insertPost(String username, String message, String date, String tag) throws UserNotFoundExcepcion, TagNotFoundExcepcion {
        Post posts = new Post(username, message, date, new HashSet<>());
        TreeSet<Post> newPost = getPostByUserAndTag(username, tag);
        newPost.add(posts);

        return newPost;
    }

    @Override
    public LinkedList<Post> listWithPost(String username) throws UserNotFoundExcepcion {
        LinkedList<Post> userWall = new LinkedList<>();
        for (User user : users.values()) {
            userWall.addAll(user.getWallByAuthor(username));
        } if (getUsers() != users) {
            throw new UserNotFoundExcepcion();
        }

        return userWall;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }

    public LinkedList<String> getTrendingTopics() {
        return trendingTopics;
    }

    public void setTrendingTopics(LinkedList<String> trendingTopics) {
        this.trendingTopics = trendingTopics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SocialNetWork)) return false;
        SocialNetWork that = (SocialNetWork) o;
        return Objects.equals(name, that.name) && Objects.equals(users, that.users) && Objects.equals(trendingTopics, that.trendingTopics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, users, trendingTopics);
    }
}