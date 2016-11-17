package com.csci4448.MediaManagementSystem.controller;

import com.csci4448.MediaManagementSystem.model.media.Media;
import com.csci4448.MediaManagementSystem.model.review.ReviewDAO;
import com.csci4448.MediaManagementSystem.model.user.UserDAO;
import com.csci4448.MediaManagementSystem.model.media.MediaDAO;
import com.csci4448.MediaManagementSystem.ui.*;
import com.csci4448.MediaManagementSystem.ui.components.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainController {

    private Display display;
    private UserDAO userDAO;
    private MediaDAO mediaDAO;
    private ReviewDAO reviewDAO;



    public MainController() {
        userDAO = new UserDAO();
        mediaDAO = new MediaDAO();
        reviewDAO = new ReviewDAO();
        display = new Display(this);
        loginRequest();
    }


    public void loginRequest() {
        display.setState(new LoginPanel(this));
    }

    public void loginSubmitRequest(String username, String password) {

        if (userDAO.userExists(username, password)) {
            userDAO.setActiveUser(username, password);
            storeRequest();
        }
        else {
            //Todo: Pass error message to UI saying that either username or password was incorrect

        }

    }

    public void createAccountRequest() {
        display.setState(new CreateAccountPanel(this));
    }

    public void createAccountSubmitRequest(String firstName, String lastName, String username, String email, String password) {

        if (userDAO.userExists(username, password)) {
            //ToDo: Send error message in UI that user already exists with that username
        }
        else {
            int res = userDAO.addUser(username, password, email, firstName, lastName);
            if (res == -1) {
                //ToDo: Error creating user, send error message to UI
            }
            else {
                userDAO.setActiveUser(username, password);
                storeRequest();
            }
        }

    }

    public void storeRequest() {
        GridMediaPanel store = new GridMediaPanel(this, 215, 327, 15, 35);
        store.getMenuPanel().getStoreButton().setIsSelected(true);

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        for(int i = 1; i <= 12; i++){



            Media media = null;
            int mediaID = i;

            Session session = sessionFactory.openSession();

            session.beginTransaction();

            media = (Media) session.createQuery("from Media where mediaID = :mediaID").setParameter("mediaID", mediaID).uniqueResult();


            MediaListing listing = new MediaListing(this, 1234, media.getImage(), media.getTitle(), media.getPrice());


            store.add(listing);
            session.close();
        }


        display.setState(store);
        sessionFactory.close();
    }



    public void libraryRequest() {
        GridMediaPanel library = new GridMediaPanel(this, 215, 327, 15, 35);
        library.getMenuPanel().getLibraryButton().setIsSelected(true);
        //ToDo: populate library with media in users inventory
        for (int i = 0; i < 3; i++) {
            //ToDo: modify MediaListing so it displays owned/rented instead of price
            MediaListing listing = new MediaListing(this, 1234, "src/main/resources/test.png", "Title", 4);
            library.add(listing);
        }
        display.setState(library);
    }

    public void adminRequest() {
        /* display.setState(new IndividualMediaPanel(this, null)); */
    }

    public void addFundsRequest() {

    }

    public void individualMediaRequest(int mediaId) {
        //ToDo: get info for particular media, mediaId, and add info to panel
        mediaDAO.setActiveMedia(mediaId);

        if (!mediaDAO.activeMediaSet()) {
            // ToDo: Send an error to UI saying that the individual media could not be set
        }

        IndividualMediaPanel indMedia = new IndividualMediaPanel(this, mediaDAO.getTitle(), mediaDAO.getImage(), mediaDAO.getDescription());
        display.setState(indMedia);
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }
    public boolean hasActiveUser() { return userDAO.activeUserSet(); }

    public MediaDAO getMediaDAO() {
        if (mediaDAO == null)
            mediaDAO = new MediaDAO();
        return mediaDAO;
    }
}
