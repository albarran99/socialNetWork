package org.iesfm.socialnetwork;

import org.iesfm.socialnetwork.excepcions.TagNotFoundExcepcion;
import org.iesfm.socialnetwork.excepcions.UserNotFoundExcepcion;

import java.util.*;

public interface ISocialNetWork {

    TreeSet<Post> getPostByUser(String username) throws UserNotFoundExcepcion;

    TreeSet<Post> getPostByUserAndTag(String username, String tag) throws UserNotFoundExcepcion, TagNotFoundExcepcion;

    TreeSet<Post> insertPost(String username, String message, String date, String tag) throws UserNotFoundExcepcion, TagNotFoundExcepcion;

    LinkedList<Post> listWithPost(String username) throws UserNotFoundExcepcion;

}
