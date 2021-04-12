package org.iesfm.socialnetwork;

import org.iesfm.socialnetwork.excepcions.TagNotFoundExcepcion;
import org.iesfm.socialnetwork.excepcions.UserNotFoundExcepcion;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;


public class SocialNetWorkTest {

    @Test
    public void getPostByUserTest() throws UserNotFoundExcepcion {
        TreeSet<Post> userWall = new TreeSet<>();

        userWall.add(new Post("Jose", "Hoy eh comido fabada", "2021-03-29", new HashSet<>()));

        User user = new User("Jose", userWall);

        TreeSet<Post> userWall1 = new TreeSet<>();

        userWall1.add(new Post("Ana", "pues bien", "2021-03-29", new HashSet<>()));

        User user1 = new User("Ana", userWall1);

        HashMap<String, User> users = new HashMap<>();

        users.put("Jose", user);

        users.put("Ana", user1);

        SocialNetWork socialNetWork = new SocialNetWork("tiwtter", users, new LinkedList<>());

        TreeSet<Post> expected = socialNetWork.getPostByUser("Jose");

        expected.add(new Post("Jose", "Hoy eh comido fabada", "2021-03-29", new HashSet<>()));


        Assert.assertEquals(expected, userWall);
    }

    @Test(expected = UserNotFoundExcepcion.class)
    public void getPostByUserExcepcionTest() throws UserNotFoundExcepcion {
        TreeSet<Post> userWall = new TreeSet<>();

        User user = new User("Jose", userWall);

        userWall.add(new Post("Jose", "Hoy eh comido fabada", "2021-03-29", new HashSet<>()));

        userWall.add(new Post("Ana", "pues que bien", "2021-03-29", new HashSet<>()));

        HashMap<String, User> users = new HashMap<>();

        users.put("Jose", user);

        SocialNetWork socialNetWork = new SocialNetWork("tiwtter", users, new LinkedList<>());

        TreeSet<Post> expected = socialNetWork.getPostByUser("Lka");

        expected.add(new Post("Jose", "Hoy eh comido fabada", "2021-03-29", new HashSet<>()));


        Assert.assertEquals(expected, userWall);
    }

    @Test
    public void getPostByUserAndTagTest() throws UserNotFoundExcepcion, TagNotFoundExcepcion {
        TreeSet<Post> userWall = new TreeSet<>();

        HashSet<String> tags = new HashSet<>();

        tags.add("Comida Y Bienestar");

        tags.add("Comida tradicional");

        User user = new User("Jose", userWall);

        User user1 = new User("Ana", userWall);

        userWall.add(new Post("Jose", "Hoy eh comido fabada", "2021-03-29", tags));

        userWall.add(new Post("Ana", "pues que bien", "2021-03-29", new HashSet<>()));

        HashMap<String, User> users = new HashMap<>();

        users.put("Jose", user);

        users.put("Ana", user1);

        SocialNetWork socialNetWork = new SocialNetWork("tiwtter", users, new LinkedList<>());

        TreeSet<Post> expected = socialNetWork.getPostByUserAndTag("Jose", "Comida Y Bienestar");

        expected.add(new Post("Jose", "Hoy eh comido fabada", "2021-03-29", tags));


        Assert.assertEquals(expected, userWall);
    }

    @Test
    public void insertPost() throws UserNotFoundExcepcion, TagNotFoundExcepcion {
        TreeSet<Post> userWall = new TreeSet<>();

        HashSet<String> tagsUser = new HashSet<>();

        tagsUser.add("Comida y vida");

        tagsUser.add("Comida");

        User user = new User("Jose", userWall);

        userWall.add(new Post("Jose", "Hoy eh comido fabada", "2021-03-29", tagsUser));

        HashMap<String, User> users = new HashMap<>();

        users.put("Jose", user);

        SocialNetWork socialNetWork = new SocialNetWork("tiwtter", users, new LinkedList<>());

        TreeSet<Post> expected = socialNetWork.insertPost("Jose", "Hoy eh comido fabada", "2021-03-29", "Comida y vida");

        expected.add(new Post("Jose", "Hoy eh comido fabada", "2021-03-29", new HashSet<>()));

        Assert.assertEquals(expected, userWall);
    }

    @Test
    public void listWithPostTest() throws UserNotFoundExcepcion {
        LinkedList<Post> actuals = new LinkedList<Post>();

        actuals.add(new Post("Jose", "Hoy eh comido fabada", "2021-03-29", new HashSet<>()));

        actuals.add(new Post("Ana", "que bien", "2021-03-29", new HashSet<>()));

        TreeSet<Post> userWall1 = new TreeSet<>();

        userWall1.add(new Post("Ana", "que bien", "2021-03-29", new HashSet<>()));

        User user = new User("Jose", actuals);

        User user1 = new User("Ana", userWall1);

        HashMap<String, User> users = new HashMap<>();

        users.put("Jose", user);

        users.put("Ana", user1);

        SocialNetWork socialNetWork = new SocialNetWork("tiwtter", users, new LinkedList<>());

        LinkedList<Post> expected = socialNetWork.listWithPost("Jose");

        expected.add(new Post("Jose", "Hoy eh comido fabada", "2021-03-29", new HashSet<>()));

        expected.add(new Post("Ana", "que bien", "2021-03-29", new HashSet<>()));

        Assert.assertEquals(expected, actuals);
    }

}
