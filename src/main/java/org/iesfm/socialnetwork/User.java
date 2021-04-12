package org.iesfm.socialnetwork;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;

public class User {
    private String username;
    private TreeSet<Post> wall;

    public User(String username, TreeSet<Post> wall) {
        this.username = username;
        this.wall = wall;
    }

    public ArrayList<Post> getWallByAuthor(String username) {
        ArrayList<Post> authorWall = new ArrayList<>();
        for(Post post : wall) {
            if (post.getAuthor().equals(username)) {
                authorWall.add(post);
            }
        }
        return authorWall;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public TreeSet<Post> getWall() {
        return wall;
    }

    public void setWall(TreeSet<Post> wall) {
        this.wall = wall;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User users = (User) o;
        return Objects.equals(username, users.username) && Objects.equals(wall, users.wall);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, wall);
    }

}
