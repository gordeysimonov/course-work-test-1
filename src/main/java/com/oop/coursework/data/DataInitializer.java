package com.oop.coursework.data;

import com.oop.coursework.model.*;
import com.oop.coursework.repo.*;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class DataInitializer implements ApplicationRunner {

    private final AppUserRepo userRepository;
    private final AuthenticationSessionRepo authenticationSessionRepository;
    private final CategoryRepo categoryRepository;
    private final CommentRepo commentRepository;
    private final GenreRepo genreRepository;
    private final MusicFileRepo musicFileRepository;
    private final NotificationRepo notificationRepository;
    private final PlaylistRepo playlistRepository;
    private final RateRepo rateRepository;
    private final SubscriptionRepo subscriptionRepository;
    private final TagRepo tagRepository;

    @Autowired
    public DataInitializer(AppUserRepo userRepository, AuthenticationSessionRepo authenticationSessionRepository, CategoryRepo categoryRepository, CommentRepo commentRepository, GenreRepo genreRepository, MusicFileRepo musicFileRepository, NotificationRepo notificationRepository, PlaylistRepo playlistRepository, RateRepo rateRepository, SubscriptionRepo subscriptionRepository, TagRepo tagRepository) {
        this.userRepository = userRepository;
        this.authenticationSessionRepository = authenticationSessionRepository;
        this.categoryRepository = categoryRepository;
        this.commentRepository = commentRepository;
        this.genreRepository = genreRepository;
        this.musicFileRepository = musicFileRepository;
        this.notificationRepository = notificationRepository;
        this.playlistRepository = playlistRepository;
        this.rateRepository = rateRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public void run(ApplicationArguments args) {

        // Users

        AppUser user1 = new AppUser();
        user1.setUsername("Hordii");
        user1.setEmail("western.qu@gmail.com");
        user1.setPassword("Veter111");
        user1.setRole("Account owner");
        user1.setPhotoUrl("https://www.google.com/url?sa=i&url=https%3A%2F%2Fleagueoflegends.fandom.com%2Fwiki%2FEkko%2FArcane&psig=AOvVaw0xt9fzlOez3MIN0XjbCENG&ust=1713899499177000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCOjZgqjD1oUDFQAAAAAdAAAAABAE");
        user1.setRegistrationDate(LocalDateTime.parse("2024-04-20T13:22:00"));
        user1.setStatus("online");
        userRepository.save(user1);

        AppUser user2 = new AppUser();
        user2.setUsername("Hanna");
        user2.setEmail("anna.altukhova@ukr.net");
        user2.setPassword("Veter222");
        user2.setRole("Account owner");
        user2.setPhotoUrl("https://www.google.com/url?sa=i&url=https%3A%2F%2Fleagueoflegends.fandom.com%2Fwiki%2FJinx%2FArcane&psig=AOvVaw3NFHp1FJ6yQxlhxSCW5Ccr&ust=1713899869796000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCMDWoNnE1oUDFQAAAAAdAAAAABAE");
        user2.setRegistrationDate(LocalDateTime.parse("2024-03-27T15:55:20"));
        user2.setStatus("online");
        userRepository.save(user2);

        AppUser user3 = new AppUser();
        user3.setUsername("Ivan");
        user3.setEmail("ivan_pubg@gmail.com");
        user3.setPassword("Veter333");
        user3.setRole("Account owner");
        user3.setPhotoUrl("https://www.google.com/url?sa=i&url=https%3A%2F%2Favatars.alphacoders.com%2Favatars%2Fview%2F372497&psig=AOvVaw1s7IYUav3KQvuXd4v0wPtb&ust=1713900017521000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCLCCx6DF1oUDFQAAAAAdAAAAABAE");
        user3.setRegistrationDate(LocalDateTime.parse("2024-04-21T19:15:30"));
        user3.setStatus("online");
        userRepository.save(user3);

        AppUser user4 = new AppUser();
        user4.setUsername("Susan");
        user4.setEmail("kitten.flavour@gmail.com");
        user4.setPassword("Veter444");
        user4.setRole("Account owner");
        user4.setPhotoUrl("https://www.google.com/url?sa=i&url=https%3A%2F%2Fcommons.wikimedia.org%2Fwiki%2FFile%3AAvatar_cat.png&psig=AOvVaw0q6lLOYylaubk8mAIzy6Ww&ust=1713900124496000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCOCyxd_F1oUDFQAAAAAdAAAAABAE");
        user4.setRegistrationDate(LocalDateTime.parse("2024-04-01T09:33:33"));
        user4.setStatus("online");
        userRepository.save(user4);

        AppUser user5 = new AppUser();
        user5.setUsername("Margo");
        user5.setEmail("litlandwein@gmail.com");
        user5.setPassword("Veter555");
        user5.setRole("Account owner");
        user5.setPhotoUrl("https://www.google.com/url?sa=i&url=https%3A%2F%2Favavatar.ru%2Fimage%2F31423&psig=AOvVaw18O6UgSQeNVCNirRpnMutB&ust=1713900293144000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCMCVn6PG1oUDFQAAAAAdAAAAABAc");
        user5.setRegistrationDate(LocalDateTime.parse("2024-04-12T19:53:42"));
        user5.setStatus("online");
        userRepository.save(user5);

        // Subscriptions

        Subscription subscription1_1 = new Subscription();
        subscription1_1.setSubscriber(user1);
        AppUser subscribedToUser1 = userRepository.findById(2L).orElse(null);
        subscription1_1.setSubscribedTo(subscribedToUser1);
        subscription1_1.setSubscriptionDate(LocalDateTime.parse("2024-04-21T07:23:40"));
        subscriptionRepository.save(subscription1_1);

        Subscription subscription1_2 = new Subscription();
        subscription1_2.setSubscriber(user1);
        AppUser subscribedToUser2 = userRepository.findById(4L).orElse(null);
        subscription1_2.setSubscribedTo(subscribedToUser2);
        subscription1_2.setSubscriptionDate(LocalDateTime.parse("2024-04-22T22:22:22"));
        subscriptionRepository.save(subscription1_2);

        Subscription subscription2_1 = new Subscription();
        subscription2_1.setSubscriber(user2);
        AppUser subscribedToUser3 = userRepository.findById(1L).orElse(null);
        subscription2_1.setSubscribedTo(subscribedToUser3);
        subscription2_1.setSubscriptionDate(LocalDateTime.parse("2024-04-21T08:01:00"));
        subscriptionRepository.save(subscription2_1);

        Subscription subscription2_2 = new Subscription();
        subscription2_2.setSubscriber(user2);
        AppUser subscribedToUser4 = userRepository.findById(3L).orElse(null);
        subscription2_2.setSubscribedTo(subscribedToUser4);
        subscription2_2.setSubscriptionDate(LocalDateTime.parse("2024-04-22T11:22:33"));
        subscriptionRepository.save(subscription2_2);

        Subscription subscription2_3 = new Subscription();
        subscription2_3.setSubscriber(user2);
        AppUser subscribedToUser5 = userRepository.findById(5L).orElse(null);
        subscription2_3.setSubscribedTo(subscribedToUser5);
        subscription2_3.setSubscriptionDate(LocalDateTime.parse("2024-04-15T16:32:33"));
        subscriptionRepository.save(subscription2_3);

        Subscription subscription3_1 = new Subscription();
        subscription3_1.setSubscriber(user3);
        AppUser subscribedToUser6 = userRepository.findById(4L).orElse(null);
        subscription3_1.setSubscribedTo(subscribedToUser6);
        subscription3_1.setSubscriptionDate(LocalDateTime.parse("2024-04-22T01:15:15"));
        subscriptionRepository.save(subscription3_1);

        Subscription subscription4_1 = new Subscription();
        subscription4_1.setSubscriber(user4);
        AppUser subscribedToUser7 = userRepository.findById(2L).orElse(null);
        subscription4_1.setSubscribedTo(subscribedToUser7);
        subscription4_1.setSubscriptionDate(LocalDateTime.parse("2024-04-19T14:10:50"));
        subscriptionRepository.save(subscription4_1);

        Subscription subscription4_2 = new Subscription();
        subscription4_2.setSubscriber(user4);
        AppUser subscribedToUser8 = userRepository.findById(1L).orElse(null);
        subscription4_2.setSubscribedTo(subscribedToUser8);
        subscription4_2.setSubscriptionDate(LocalDateTime.parse("2024-04-21T10:05:00"));
        subscriptionRepository.save(subscription4_2);

        Subscription subscription4_3 = new Subscription();
        subscription4_3.setSubscriber(user4);
        AppUser subscribedToUser9 = userRepository.findById(5L).orElse(null);
        subscription4_3.setSubscribedTo(subscribedToUser9);
        subscription4_3.setSubscriptionDate(LocalDateTime.parse("2024-04-20T12:30:45"));
        subscriptionRepository.save(subscription4_3);

        Subscription subscription5_1 = new Subscription();
        subscription5_1.setSubscriber(user5);
        AppUser subscribedToUser10 = userRepository.findById(1L).orElse(null);
        subscription5_1.setSubscribedTo(subscribedToUser10);
        subscription5_1.setSubscriptionDate(LocalDateTime.parse("2024-04-22T23:20:30"));
        subscriptionRepository.save(subscription5_1);

        Subscription subscription5_2 = new Subscription();
        subscription5_2.setSubscriber(user5);
        AppUser subscribedToUser11 = userRepository.findById(3L).orElse(null);
        subscription5_2.setSubscribedTo(subscribedToUser11);
        subscription5_2.setSubscriptionDate(LocalDateTime.parse("2024-04-22T08:45:00"));
        subscriptionRepository.save(subscription5_2);

        // Authentication Sessions User 1

        AuthenticationSession authenticationSession1_1 = new AuthenticationSession();
        authenticationSession1_1.setSessionStart(LocalDateTime.parse("2024-04-20T13:22:00"));
        authenticationSession1_1.setSessionEnd(LocalDateTime.parse("2024-04-20T13:53:20"));
        authenticationSession1_1.setUserId(user1);
        authenticationSessionRepository.save(authenticationSession1_1);

        AuthenticationSession authenticationSession1_2 = new AuthenticationSession();
        authenticationSession1_2.setSessionStart(LocalDateTime.parse("2024-04-21T07:20:05"));
        authenticationSession1_2.setSessionEnd(LocalDateTime.parse("2024-04-21T07:37:30"));
        authenticationSession1_2.setUserId(user1);
        authenticationSessionRepository.save(authenticationSession1_2);

        AuthenticationSession authenticationSession1_3 = new AuthenticationSession();
        authenticationSession1_3.setSessionStart(LocalDateTime.parse("2024-04-22T22:01:07"));
        authenticationSession1_3.setSessionEnd(LocalDateTime.parse("2024-04-22T23:21:11"));
        authenticationSession1_3.setUserId(user1);
        authenticationSessionRepository.save(authenticationSession1_3);

        // Authentication Sessions User 2

        AuthenticationSession authenticationSession2_1 = new AuthenticationSession();
        authenticationSession2_1.setSessionStart(LocalDateTime.parse("2024-03-27T15:55:20"));
        authenticationSession2_1.setSessionEnd(LocalDateTime.parse("2024-03-27T16:15:46"));
        authenticationSession2_1.setUserId(user2);
        authenticationSessionRepository.save(authenticationSession2_1);

        AuthenticationSession authenticationSession2_2 = new AuthenticationSession();
        authenticationSession2_2.setSessionStart(LocalDateTime.parse("2024-03-29T11:11:23"));
        authenticationSession2_2.setSessionEnd(LocalDateTime.parse("2024-03-29T11:31:27"));
        authenticationSession2_2.setUserId(user2);
        authenticationSessionRepository.save(authenticationSession2_2);

        AuthenticationSession authenticationSession2_3 = new AuthenticationSession();
        authenticationSession2_3.setSessionStart(LocalDateTime.parse("2024-04-08T16:33:33"));
        authenticationSession2_3.setSessionEnd(LocalDateTime.parse("2024-04-08T17:33:33"));
        authenticationSession2_3.setUserId(user2);
        authenticationSessionRepository.save(authenticationSession2_3);

        AuthenticationSession authenticationSession2_4 = new AuthenticationSession();
        authenticationSession2_4.setSessionStart(LocalDateTime.parse("2024-04-15T15:47:23"));
        authenticationSession2_4.setSessionEnd(LocalDateTime.parse("2024-04-15T17:11:27"));
        authenticationSession2_4.setUserId(user2);
        authenticationSessionRepository.save(authenticationSession2_4);

        AuthenticationSession authenticationSession2_5 = new AuthenticationSession();
        authenticationSession2_5.setSessionStart(LocalDateTime.parse("2024-04-21T07:55:01"));
        authenticationSession2_5.setSessionEnd(LocalDateTime.parse("2024-04-21T09:11:12"));
        authenticationSession2_5.setUserId(user2);
        authenticationSessionRepository.save(authenticationSession2_5);

        AuthenticationSession authenticationSession2_6 = new AuthenticationSession();
        authenticationSession2_6.setSessionStart(LocalDateTime.parse("2024-04-22T11:21:23"));
        authenticationSession2_6.setSessionEnd(LocalDateTime.parse("2024-04-22T11:34:11"));
        authenticationSession2_6.setUserId(user2);
        authenticationSessionRepository.save(authenticationSession2_6);

        // Authentication Sessions User 3

        AuthenticationSession authenticationSession3_1 = new AuthenticationSession();
        authenticationSession3_1.setSessionStart(LocalDateTime.parse("2024-04-21T19:15:30"));
        authenticationSession3_1.setSessionEnd(LocalDateTime.parse("2024-04-21T20:04:31"));
        authenticationSession3_1.setUserId(user3);
        authenticationSessionRepository.save(authenticationSession3_1);

        AuthenticationSession authenticationSession3_2 = new AuthenticationSession();
        authenticationSession3_2.setSessionStart(LocalDateTime.parse("2024-04-22T00:55:25"));
        authenticationSession3_2.setSessionEnd(LocalDateTime.parse("2024-04-22T01:26:31"));
        authenticationSession3_2.setUserId(user3);
        authenticationSessionRepository.save(authenticationSession3_2);

        // Authentication Sessions User 4

        AuthenticationSession authenticationSession4_1 = new AuthenticationSession();
        authenticationSession4_1.setSessionStart(LocalDateTime.parse("2024-04-01T09:33:33"));
        authenticationSession4_1.setSessionEnd(LocalDateTime.parse("2024-04-01T11:32:47"));
        authenticationSession4_1.setUserId(user4);
        authenticationSessionRepository.save(authenticationSession4_1);

        AuthenticationSession authenticationSession4_2 = new AuthenticationSession();
        authenticationSession4_2.setSessionStart(LocalDateTime.parse("2024-04-11T18:13:00"));
        authenticationSession4_2.setSessionEnd(LocalDateTime.parse("2024-04-11T19:02:45"));
        authenticationSession4_2.setUserId(user4);
        authenticationSessionRepository.save(authenticationSession4_2);

        AuthenticationSession authenticationSession4_3 = new AuthenticationSession();
        authenticationSession4_3.setSessionStart(LocalDateTime.parse("2024-04-12T12:41:23"));
        authenticationSession4_3.setSessionEnd(LocalDateTime.parse("2024-04-12T13:27:01"));
        authenticationSession4_3.setUserId(user4);
        authenticationSessionRepository.save(authenticationSession4_3);

        AuthenticationSession authenticationSession4_4 = new AuthenticationSession();
        authenticationSession4_4.setSessionStart(LocalDateTime.parse("2024-04-19T14:01:51"));
        authenticationSession4_4.setSessionEnd(LocalDateTime.parse("2024-04-19T14:49:22"));
        authenticationSession4_4.setUserId(user4);
        authenticationSessionRepository.save(authenticationSession4_4);

        AuthenticationSession authenticationSession4_5 = new AuthenticationSession();
        authenticationSession4_5.setSessionStart(LocalDateTime.parse("2024-04-20T11:41:33"));
        authenticationSession4_5.setSessionEnd(LocalDateTime.parse("2024-04-20T12:55:25"));
        authenticationSession4_5.setUserId(user4);
        authenticationSessionRepository.save(authenticationSession4_5);

        AuthenticationSession authenticationSession4_6 = new AuthenticationSession();
        authenticationSession4_6.setSessionStart(LocalDateTime.parse("2024-04-21T09:58:24"));
        authenticationSession4_6.setSessionEnd(LocalDateTime.parse("2024-04-21T11:15:15"));
        authenticationSession4_6.setUserId(user4);
        authenticationSessionRepository.save(authenticationSession4_6);

        // Authentication Sessions User 5

        AuthenticationSession authenticationSession5_1 = new AuthenticationSession();
        authenticationSession5_1.setSessionStart(LocalDateTime.parse("2024-04-12T19:53:42"));
        authenticationSession5_1.setSessionEnd(LocalDateTime.parse("2024-04-12T22:07:07"));
        authenticationSession5_1.setUserId(user5);
        authenticationSessionRepository.save(authenticationSession5_1);

        AuthenticationSession authenticationSession5_2 = new AuthenticationSession();
        authenticationSession5_2.setSessionStart(LocalDateTime.parse("2024-04-20T14:56:31"));
        authenticationSession5_2.setSessionEnd(LocalDateTime.parse("2024-04-20T15:49:01"));
        authenticationSession5_2.setUserId(user5);
        authenticationSessionRepository.save(authenticationSession5_2);

        AuthenticationSession authenticationSession5_3 = new AuthenticationSession();
        authenticationSession5_3.setSessionStart(LocalDateTime.parse("2024-04-22T08:41:20"));
        authenticationSession5_3.setSessionEnd(LocalDateTime.parse("2024-04-22T09:52:23"));
        authenticationSession5_3.setUserId(user5);
        authenticationSessionRepository.save(authenticationSession5_3);

        AuthenticationSession authenticationSession5_4 = new AuthenticationSession();
        authenticationSession5_4.setSessionStart(LocalDateTime.parse("2024-04-22T22:35:57"));
        authenticationSession5_4.setSessionEnd(LocalDateTime.parse("2024-04-22T23:30:03"));
        authenticationSession5_4.setUserId(user5);
        authenticationSessionRepository.save(authenticationSession5_4);

        // Notifications User 1

        Notification notification1_1 = new Notification();
        notification1_1.setNotificationType("New Subscriber");
        notification1_1.setNotificationText("User Hanna has followed you!");
        notification1_1.setStatus("read");
        notification1_1.setDateReceiving(LocalDateTime.parse("2024-04-21T08:01:00"));
        notification1_1.setUserId(user1);
        notificationRepository.save(notification1_1);

        Notification notification1_2 = new Notification();
        notification1_2.setNotificationType("New Subscriber");
        notification1_2.setNotificationText("User Susan has followed you!");
        notification1_2.setStatus("unread");
        notification1_2.setDateReceiving(LocalDateTime.parse("2024-04-21T10:05:00"));
        notification1_2.setUserId(user1);
        notificationRepository.save(notification1_2);

        Notification notification1_3 = new Notification();
        notification1_3.setNotificationType("New Subscriber");
        notification1_3.setNotificationText("User Margo has followed you!");
        notification1_3.setStatus("unread");
        notification1_3.setDateReceiving(LocalDateTime.parse("2024-04-22T23:20:30"));
        notification1_3.setUserId(user1);
        notificationRepository.save(notification1_3);

        // Notifications User 2

        Notification notification2_1 = new Notification();
        notification2_1.setNotificationType("New Subscriber");
        notification2_1.setNotificationText("User Hordii has followed you!");
        notification2_1.setStatus("read");
        notification2_1.setDateReceiving(LocalDateTime.parse("2024-04-21T07:23:40"));
        notification2_1.setUserId(user2);
        notificationRepository.save(notification2_1);

        Notification notification2_2 = new Notification();
        notification2_2.setNotificationType("New Subscriber");
        notification2_2.setNotificationText("User Susan has followed you!");
        notification2_2.setStatus("read");
        notification2_2.setDateReceiving(LocalDateTime.parse("2024-04-19T14:10:50"));
        notification2_2.setUserId(user2);
        notificationRepository.save(notification2_2);

        // Notifications User 3

        Notification notification3_1 = new Notification();
        notification3_1.setNotificationType("New Subscriber");
        notification3_1.setNotificationText("User Hanna has followed you!");
        notification3_1.setStatus("unread");
        notification3_1.setDateReceiving(LocalDateTime.parse("2024-04-22T11:22:33"));
        notification3_1.setUserId(user3);
        notificationRepository.save(notification3_1);

        Notification notification3_2 = new Notification();
        notification3_2.setNotificationType("New Subscriber");
        notification3_2.setNotificationText("User Margo has followed you!");
        notification3_2.setStatus("unread");
        notification3_2.setDateReceiving(LocalDateTime.parse("2024-04-22T08:45:00"));
        notification3_2.setUserId(user3);
        notificationRepository.save(notification3_2);

        // Notifications User 4

        Notification notification4_1 = new Notification();
        notification4_1.setNotificationType("New Subscriber");
        notification4_1.setNotificationText("User Hordii has followed you!");
        notification4_1.setStatus("unread");
        notification4_1.setDateReceiving(LocalDateTime.parse("2024-04-22T22:22:22"));
        notification4_1.setUserId(user4);
        notificationRepository.save(notification4_1);

        Notification notification4_2 = new Notification();
        notification4_2.setNotificationType("New Subscriber");
        notification4_2.setNotificationText("User Ivan has followed you!");
        notification4_2.setStatus("unread");
        notification4_2.setDateReceiving(LocalDateTime.parse("2024-04-22T01:15:15"));
        notification4_2.setUserId(user4);
        notificationRepository.save(notification4_2);

        // Notifications User 5

        Notification notification5_1 = new Notification();
        notification5_1.setNotificationType("New Subscriber");
        notification5_1.setNotificationText("User Hanna has followed you!");
        notification5_1.setStatus("read");
        notification5_1.setDateReceiving(LocalDateTime.parse("2024-04-15T16:32:33"));
        notification5_1.setUserId(user5);
        notificationRepository.save(notification5_1);

        Notification notification5_2 = new Notification();
        notification5_2.setNotificationType("New Subscriber");
        notification5_2.setNotificationText("User Susan has followed you!");
        notification5_2.setStatus("read");
        notification5_2.setDateReceiving(LocalDateTime.parse("2024-04-20T12:30:45"));
        notification5_2.setUserId(user5);
        notificationRepository.save(notification5_2);

        // Genres

        Genre genre1 = new Genre();
        genre1.setGenre("Rock");
        genre1.setDescription("Energetic music, highlighted by guitar riffs and strong rhythm");
        genreRepository.save(genre1);

        Genre genre2 = new Genre();
        genre2.setGenre("Pop");
        genre2.setDescription("Melodic songs with a wide audience and often infectious rhythms");
        genreRepository.save(genre2);

        Genre genre3 = new Genre();
        genre3.setGenre("Hip Hop");
        genre3.setDescription("Urban music genre featuring rhythmic and rhyming speech");
        genreRepository.save(genre3);

        Genre genre4 = new Genre();
        genre4.setGenre("Electronic");
        genre4.setDescription("Music genre characterized by the use of electronic instruments and technology");
        genreRepository.save(genre4);

        Genre genre5 = new Genre();
        genre5.setGenre("Classical");
        genre5.setDescription("Music genre rooted in Western tradition, typically with complex compositions and orchestration");
        genreRepository.save(genre5);

        Genre genre6 = new Genre();
        genre6.setGenre("Jazz");
        genre6.setDescription("Music genre characterized by improvisation, syncopation, and swing");
        genreRepository.save(genre6);

        Genre genre7 = new Genre();
        genre7.setGenre("Reggae");
        genre7.setDescription("Music genre originating in Jamaica, known for its offbeat rhythm and focus on social and political themes");
        genreRepository.save(genre7);

        Genre genre8 = new Genre();
        genre8.setGenre("Metal");
        genre8.setDescription("Heavy and aggressive music genre with distorted guitar sounds and powerful vocals");
        genreRepository.save(genre8);

        Genre genre9 = new Genre();
        genre9.setGenre("Country");
        genre9.setDescription("Music genre rooted in American folk traditions, often featuring storytelling lyrics and acoustic instruments");
        genreRepository.save(genre9);

        Genre genre10 = new Genre();
        genre10.setGenre("K-Pop");
        genre10.setDescription("Electrifying genre of pop music that blends energetic rhythms, vibrant performances, and unparalleled style");
        genreRepository.save(genre10);

        // Tags

        Tag tag1 = new Tag();
        tag1.setTagName("rock");
        tagRepository.save(tag1);

        Tag tag2 = new Tag();
        tag2.setTagName("pop");
        tagRepository.save(tag2);

        Tag tag3 = new Tag();
        tag3.setTagName("hiphop");
        tagRepository.save(tag3);

        Tag tag4 = new Tag();
        tag4.setTagName("electronic");
        tagRepository.save(tag4);

        Tag tag5 = new Tag();
        tag5.setTagName("classical");
        tagRepository.save(tag5);

        Tag tag6 = new Tag();
        tag6.setTagName("jazz");
        tagRepository.save(tag6);

        Tag tag7 = new Tag();
        tag7.setTagName("reggae");
        tagRepository.save(tag7);

        Tag tag8 = new Tag();
        tag8.setTagName("metal");
        tagRepository.save(tag8);

        Tag tag9 = new Tag();
        tag9.setTagName("country");
        tagRepository.save(tag9);

        Tag tag10 = new Tag();
        tag10.setTagName("billie_eilish");
        tagRepository.save(tag10);

        Tag tag11 = new Tag();
        tag11.setTagName("yssmiac");
        tagRepository.save(tag11);

        Tag tag12 = new Tag();
        tag12.setTagName("set_it_off");
        tagRepository.save(tag12);

        Tag tag13 = new Tag();
        tag13.setTagName("cartoon");
        tagRepository.save(tag13);

        Tag tag14 = new Tag();
        tag14.setTagName("ariana_grande");
        tagRepository.save(tag14);

        Tag tag15 = new Tag();
        tag15.setTagName("melody");
        tagRepository.save(tag15);

        Tag tag16 = new Tag();
        tag16.setTagName("lady_gaga");
        tagRepository.save(tag16);

        Tag tag17 = new Tag();
        tag17.setTagName("sheppard");
        tagRepository.save(tag17);

        Tag tag18 = new Tag();
        tag18.setTagName("game");
        tagRepository.save(tag18);

        Tag tag19 = new Tag();
        tag19.setTagName("arcadian_wild");
        tagRepository.save(tag19);

        Tag tag20 = new Tag();
        tag20.setTagName("street");
        tagRepository.save(tag20);

        Tag tag21 = new Tag();
        tag21.setTagName("kpop");
        tagRepository.save(tag21);

        // Music Files User 1

        MusicFile musicFile1_1 = new MusicFile();
        musicFile1_1.setName("you should see me in a crown");
        musicFile1_1.setAuthor("Billie Eilish");
        musicFile1_1.setYear(2019);
        musicFile1_1.setDescription("found some cool staff");
        musicFile1_1.setLyrics("https://genius.com/Billie-eilish-you-should-see-me-in-a-crown-lyrics");
        musicFile1_1.setFilePath("https://open.spotify.com/track/3XF5xLJHOQQRbWya6hBp7d");
        musicFile1_1.setFileType(MusicFile.FileType.MP3);
        musicFile1_1.setDownloadDate(LocalDateTime.parse("2024-04-21T07:24:35"));
        musicFile1_1.setDownloadsNumber(2);

        musicFile1_1.setUserId(user1);

        Set<Genre> genres1_1 = new HashSet<>();
        genres1_1.add(genre2);
        musicFile1_1.setGenres(genres1_1);

        Set<Tag> tags1_1 = new HashSet<>();
        tags1_1.add(tag2);
        tags1_1.add(tag10);
        tags1_1.add(tag11);
        tags1_1.add(tag15);
        musicFile1_1.setTags(tags1_1);

        musicFileRepository.save(musicFile1_1);

        MusicFile musicFile1_2 = new MusicFile();
        musicFile1_2.setName("Nightmare");
        musicFile1_2.setAuthor("Set It Off");
        musicFile1_2.setYear(2012);
        musicFile1_2.setDescription("i created a monster, a beast inside my brain");
        musicFile1_2.setLyrics("https://genius.com/Set-it-off-nightmare-lyrics");
        musicFile1_2.setFilePath("https://open.spotify.com/track/2FxfCsAdtZDqoqjWqiib5X");
        musicFile1_2.setFileType(MusicFile.FileType.MP3);
        musicFile1_2.setDownloadDate(LocalDateTime.parse("2024-04-22T22:15:09"));
        musicFile1_2.setDownloadsNumber(3);

        musicFile1_2.setUserId(user1);

        Set<Genre> genres1_2 = new HashSet<>();
        genres1_2.add(genre1);
        genres1_2.add(genre2);
        musicFile1_2.setGenres(genres1_2);

        Set<Tag> tags1_2 = new HashSet<>();
        tags1_2.add(tag1);
        tags1_2.add(tag2);
        tags1_2.add(tag12);
        tags1_2.add(tag15);
        musicFile1_2.setTags(tags1_2);

        musicFileRepository.save(musicFile1_2);

        // Music Files User 2

        MusicFile musicFile2_1 = new MusicFile();
        musicFile2_1.setName("thank you, next");
        musicFile2_1.setAuthor("Ariana Grande");
        musicFile2_1.setYear(2018);
        musicFile2_1.setDescription("Ariana 4 Life!!!");
        musicFile2_1.setLyrics("https://genius.com/Ariana-grande-thank-u-next-lyrics");
        musicFile2_1.setFilePath("https://open.spotify.com/track/3e9HZxeyfWwjeyPAMmWSSQ");
        musicFile2_1.setFileType(MusicFile.FileType.MP3);
        musicFile2_1.setDownloadDate(LocalDateTime.parse("2024-04-08T16:34:21"));
        musicFile2_1.setDownloadsNumber(4);

        musicFile2_1.setUserId(user2);

        Set<Genre> genres2_1 = new HashSet<>();
        genres2_1.add(genre2);
        musicFile2_1.setGenres(genres2_1);

        Set<Tag> tags2_1 = new HashSet<>();
        tags2_1.add(tag2);
        tags2_1.add(tag14);
        tags2_1.add(tag15);
        musicFile2_1.setTags(tags2_1);

        musicFileRepository.save(musicFile2_1);

        MusicFile musicFile2_2 = new MusicFile();
        musicFile2_2.setName("Kara Main Theme");
        musicFile2_2.setAuthor("Philip Sheppard");
        musicFile2_2.setYear(2018);
        musicFile2_2.setDescription("What a soulful melody!");
        musicFile2_2.setLyrics(null);
        musicFile2_2.setFilePath("https://open.spotify.com/track/1KOUWbi3StqWAT4odlEhu3");
        musicFile2_2.setFileType(MusicFile.FileType.MP3);
        musicFile2_2.setDownloadDate(LocalDateTime.parse("2024-04-15T15:52:09"));
        musicFile2_2.setDownloadsNumber(1);

        musicFile2_2.setUserId(user2);

        Set<Genre> genres2_2 = new HashSet<>();
        genres2_2.add(genre5);
        musicFile2_2.setGenres(genres2_2);

        Set<Tag> tags2_2 = new HashSet<>();
        tags2_2.add(tag5);
        tags2_2.add(tag17);
        tags2_2.add(tag15);
        musicFile2_2.setTags(tags2_2);

        musicFileRepository.save(musicFile2_2);

        MusicFile musicFile2_3 = new MusicFile();
        musicFile2_3.setName("Poster Girl");
        musicFile2_3.setAuthor("Zara Larsson");
        musicFile2_3.setYear(2021);
        musicFile2_3.setDescription("IM A POSTER GIRL!!!");
        musicFile2_3.setLyrics("https://genius.com/Zara-larsson-poster-girl-lyrics");
        musicFile2_3.setFilePath("https://open.spotify.com/track/1MGqtRnKlHNO4fuHMm2Dm9");
        musicFile2_3.setFileType(MusicFile.FileType.MP3);
        musicFile2_3.setDownloadDate(LocalDateTime.parse("2024-04-15T15:52:09"));
        musicFile2_3.setDownloadsNumber(4);

        musicFile2_3.setUserId(user2);

        Set<Genre> genres2_3 = new HashSet<>();
        genres2_3.add(genre2);
        musicFile2_3.setGenres(genres2_3);

        Set<Tag> tags2_3 = new HashSet<>();
        tags2_3.add(tag2);
        tags2_3.add(tag15);
        musicFile2_3.setTags(tags2_3);

        musicFileRepository.save(musicFile2_3);

        // Music Files User 3

        MusicFile musicFile3_1 = new MusicFile();
        musicFile3_1.setName("Feel Good Inc.");
        musicFile3_1.setAuthor("Gorillaz");
        musicFile3_1.setYear(2005);
        musicFile3_1.setDescription("Album : Demon Days. Recommend this!");
        musicFile3_1.setLyrics("https://genius.com/Gorillaz-feel-good-inc-lyrics");
        musicFile3_1.setFilePath("https://open.spotify.com/track/0d28khcov6AiegSCpG5TuT");
        musicFile3_1.setFileType(MusicFile.FileType.MP3);
        musicFile3_1.setDownloadDate(LocalDateTime.parse("2024-04-22T01:02:45"));
        musicFile3_1.setDownloadsNumber(0);

        musicFile3_1.setUserId(user3);

        Set<Genre> genres3_1 = new HashSet<>();
        genres3_1.add(genre1);
        genres3_1.add(genre3);
        musicFile3_1.setGenres(genres3_1);

        Set<Tag> tags3_1 = new HashSet<>();
        tags3_1.add(tag1);
        tags3_1.add(tag3);
        tags3_1.add(tag15);
        musicFile3_1.setTags(tags3_1);

        musicFileRepository.save(musicFile3_1);

        // Music Files User 4

        MusicFile musicFile4_1 = new MusicFile();
        musicFile4_1.setName("Ice Cream");
        musicFile4_1.setAuthor("Blackpink, Selena Gomez");
        musicFile4_1.setYear(2020);
        musicFile4_1.setDescription("Summer vibes!");
        musicFile4_1.setLyrics("https://genius.com/Blackpink-and-selena-gomez-ice-cream-lyrics");
        musicFile4_1.setFilePath("https://open.spotify.com/track/4JUPEh2DVSXFGExu4Uxevz");
        musicFile4_1.setFileType(MusicFile.FileType.MP3);
        musicFile4_1.setDownloadDate(LocalDateTime.parse("2024-04-11T18:53:33"));
        musicFile4_1.setDownloadsNumber(2);

        musicFile4_1.setUserId(user4);

        Set<Genre> genres4_1 = new HashSet<>();
        genres4_1.add(genre2);
        genres4_1.add(genre10);
        musicFile4_1.setGenres(genres4_1);

        Set<Tag> tags4_1 = new HashSet<>();
        tags4_1.add(tag2);
        tags4_1.add(tag21);
        tags4_1.add(tag15);
        musicFile4_1.setTags(tags4_1);

        musicFileRepository.save(musicFile4_1);

        MusicFile musicFile4_2 = new MusicFile();
        musicFile4_2.setName("Show Yourself");
        musicFile4_2.setAuthor("CG5");
        musicFile4_2.setYear(2020);
        musicFile4_2.setDescription("Who is the impostor?");
        musicFile4_2.setLyrics("https://genius.com/Cg5-show-yourself-lyrics");
        musicFile4_2.setFilePath("https://open.spotify.com/track/4hXhqKEIFqJpqFybpKtAWz");
        musicFile4_2.setFileType(MusicFile.FileType.MP3);
        musicFile4_2.setDownloadDate(LocalDateTime.parse("2024-04-19T14:03:31"));
        musicFile4_2.setDownloadsNumber(3);

        musicFile4_2.setUserId(user4);

        Set<Genre> genres4_2 = new HashSet<>();
        genres4_2.add(genre2);
        musicFile4_2.setGenres(genres4_2);

        Set<Tag> tags4_2 = new HashSet<>();
        tags4_2.add(tag2);
        tags4_2.add(tag18);
        tags4_2.add(tag15);
        musicFile4_2.setTags(tags4_2);

        musicFileRepository.save(musicFile4_2);

        MusicFile musicFile4_3 = new MusicFile();
        musicFile4_3.setName("Sweet Juice");
        musicFile4_3.setAuthor("Purple Kiss");
        musicFile4_3.setYear(2023);
        musicFile4_3.setDescription("You want some sweet juice?... Yes... Run!");
        musicFile4_3.setLyrics("https://genius.com/Purple-kiss-sweet-juice-lyrics");
        musicFile4_3.setFilePath("https://open.spotify.com/track/2th6UWbfVz3hsSDzQAKqy2");
        musicFile4_3.setFileType(MusicFile.FileType.MP3);
        musicFile4_3.setDownloadDate(LocalDateTime.parse("2024-04-20T12:52:21"));
        musicFile4_3.setDownloadsNumber(2);

        musicFile4_3.setUserId(user4);

        Set<Genre> genres4_3 = new HashSet<>();
        genres4_3.add(genre2);
        genres4_3.add(genre10);
        musicFile4_3.setGenres(genres4_3);

        Set<Tag> tags4_3 = new HashSet<>();
        tags4_1.add(tag2);
        tags4_1.add(tag21);
        tags4_1.add(tag15);
        musicFile4_3.setTags(tags4_3);

        musicFileRepository.save(musicFile4_3);

        // Music Files User 5

        MusicFile musicFile5_1 = new MusicFile();
        musicFile5_1.setName("Yurgen's Tune");
        musicFile5_1.setAuthor("The Arcadian Wild");
        musicFile5_1.setYear(2018);
        musicFile5_1.setDescription("Raise the rock where Yurgen fell!");
        musicFile5_1.setLyrics("https://genius.com/The-arcadian-wild-yurgens-tune-lyrics");
        musicFile5_1.setFilePath("https://open.spotify.com/track/0Q3vufKI3dfsOqFHPhT9FC");
        musicFile5_1.setFileType(MusicFile.FileType.MP3);
        musicFile5_1.setDownloadDate(LocalDateTime.parse("2024-04-20T14:59:48"));
        musicFile5_1.setDownloadsNumber(4);

        musicFile5_1.setUserId(user5);

        Set<Genre> genres5_1 = new HashSet<>();
        genres5_1.add(genre5);
        musicFile5_1.setGenres(genres5_1);

        Set<Tag> tags5_1 = new HashSet<>();
        tags5_1.add(tag5);
        tags5_1.add(tag13);
        tags5_1.add(tag19);
        tags5_1.add(tag15);
        musicFile5_1.setTags(tags5_1);

        musicFileRepository.save(musicFile5_1);

        MusicFile musicFile5_2 = new MusicFile();
        musicFile5_2.setName("Meet In The Middle");
        musicFile5_2.setAuthor("Diamond Rio");
        musicFile5_2.setYear(1991);
        musicFile5_2.setDescription("“Meet in the Middle” is a mid-tempo song about meeting halfway. First, as boyfriend and girlfriend meeting halfway between their houses; then as husband and wife meeting halfway when they disagree on something.");
        musicFile5_2.setLyrics("https://genius.com/Diamond-rio-meet-in-the-middle-lyrics");
        musicFile5_2.setFilePath("https://open.spotify.com/track/7lUE02KHkZM44BZgjCaWRO");
        musicFile5_2.setFileType(MusicFile.FileType.MP3);
        musicFile5_2.setDownloadDate(LocalDateTime.parse("2024-04-22T09:03:09"));
        musicFile5_2.setDownloadsNumber(1);

        musicFile5_2.setUserId(user5);

        Set<Genre> genres5_2 = new HashSet<>();
        genres5_2.add(genre9);
        musicFile5_2.setGenres(genres5_2);

        Set<Tag> tags5_2 = new HashSet<>();
        tags5_2.add(tag9);
        tags5_2.add(tag15);
        musicFile5_2.setTags(tags5_2);

        musicFileRepository.save(musicFile5_2);

        // Rates

        Rate rate1_1 = new Rate();
        rate1_1.setRate(Rate.Rating.EIGHT);
        rate1_1.setUserId(user2);
        rate1_1.setMusicFileId(musicFile1_1);

        rateRepository.save(rate1_1);

        Rate rate1_2 = new Rate();
        rate1_2.setRate(Rate.Rating.SIX);
        rate1_2.setUserId(user3);
        rate1_2.setMusicFileId(musicFile1_1);

        rateRepository.save(rate1_2);

        Rate rate1_3 = new Rate();
        rate1_3.setRate(Rate.Rating.SEVEN);
        rate1_3.setUserId(user4);
        rate1_3.setMusicFileId(musicFile1_1);

        rateRepository.save(rate1_3);

        Rate rate2_1 = new Rate();
        rate2_1.setRate(Rate.Rating.NINE);
        rate2_1.setUserId(user3);
        rate2_1.setMusicFileId(musicFile1_2);
        rateRepository.save(rate2_1);

        rateRepository.save(rate2_1);

        Rate rate2_2 = new Rate();
        rate2_2.setRate(Rate.Rating.TEN);
        rate2_2.setUserId(user5);
        rate2_2.setMusicFileId(musicFile1_2);
        rateRepository.save(rate2_2);

        rateRepository.save(rate2_2);

        Rate rate3_1 = new Rate();
        rate3_1.setRate(Rate.Rating.FIVE);
        rate3_1.setUserId(user1);
        rate3_1.setMusicFileId(musicFile2_1);
        rateRepository.save(rate3_1);

        rateRepository.save(rate3_1);

        Rate rate3_2 = new Rate();
        rate3_2.setRate(Rate.Rating.EIGHT);
        rate3_2.setUserId(user4);
        rate3_2.setMusicFileId(musicFile2_1);
        rateRepository.save(rate3_2);

        rateRepository.save(rate3_2);

        Rate rate3_3 = new Rate();
        rate3_3.setRate(Rate.Rating.EIGHT);
        rate3_3.setUserId(user5);
        rate3_3.setMusicFileId(musicFile2_1);
        rateRepository.save(rate3_3);

        rateRepository.save(rate3_3);

        Rate rate3_4 = new Rate();
        rate3_4.setRate(Rate.Rating.SEVEN);
        rate3_4.setUserId(user3);
        rate3_4.setMusicFileId(musicFile2_1);
        rateRepository.save(rate3_4);

        rateRepository.save(rate3_4);

        Rate rate4_1 = new Rate();
        rate4_1.setRate(Rate.Rating.TEN);
        rate4_1.setUserId(user3);
        rate4_1.setMusicFileId(musicFile2_2);
        rateRepository.save(rate4_1);

        rateRepository.save(rate4_1);

        Rate rate4_2 = new Rate();
        rate4_2.setRate(Rate.Rating.TEN);
        rate4_2.setUserId(user4);
        rate4_2.setMusicFileId(musicFile2_2);
        rateRepository.save(rate4_2);

        rateRepository.save(rate4_2);

        Rate rate5_1 = new Rate();
        rate5_1.setRate(Rate.Rating.EIGHT);
        rate5_1.setUserId(user1);
        rate5_1.setMusicFileId(musicFile2_3);
        rateRepository.save(rate5_1);

        rateRepository.save(rate5_1);

        Rate rate5_2 = new Rate();
        rate5_2.setRate(Rate.Rating.FIVE);
        rate5_2.setUserId(user5);
        rate5_2.setMusicFileId(musicFile2_3);
        rateRepository.save(rate5_2);

        rateRepository.save(rate5_2);

        Rate rate7_1 = new Rate();
        rate7_1.setRate(Rate.Rating.TEN);
        rate7_1.setUserId(user1);
        rate7_1.setMusicFileId(musicFile4_1);
        rateRepository.save(rate7_1);

        rateRepository.save(rate7_1);

        Rate rate7_2 = new Rate();
        rate7_2.setRate(Rate.Rating.NINE);
        rate7_2.setUserId(user2);
        rate7_2.setMusicFileId(musicFile4_1);
        rateRepository.save(rate7_2);

        rateRepository.save(rate7_2);

        Rate rate7_3 = new Rate();
        rate7_3.setRate(Rate.Rating.TEN);
        rate7_3.setUserId(user3);
        rate7_3.setMusicFileId(musicFile4_1);
        rateRepository.save(rate7_3);

        rateRepository.save(rate7_3);

        Rate rate7_4 = new Rate();
        rate7_4.setRate(Rate.Rating.TEN);
        rate7_4.setUserId(user5);
        rate7_4.setMusicFileId(musicFile4_1);
        rateRepository.save(rate7_4);

        rateRepository.save(rate7_4);

        Rate rate8_1 = new Rate();
        rate8_1.setRate(Rate.Rating.SIX);
        rate8_1.setUserId(user2);
        rate8_1.setMusicFileId(musicFile4_2);
        rateRepository.save(rate8_1);

        rateRepository.save(rate8_1);

        Rate rate8_2 = new Rate();
        rate8_2.setRate(Rate.Rating.ZERO);
        rate8_2.setUserId(user3);
        rate8_2.setMusicFileId(musicFile4_2);
        rateRepository.save(rate8_2);

        rateRepository.save(rate8_2);

        Rate rate9_1 = new Rate();
        rate9_1.setRate(Rate.Rating.TEN);
        rate9_1.setUserId(user1);
        rate9_1.setMusicFileId(musicFile4_3);
        rateRepository.save(rate9_1);

        rateRepository.save(rate9_1);

        Rate rate9_2 = new Rate();
        rate9_2.setRate(Rate.Rating.SEVEN);
        rate9_2.setUserId(user5);
        rate9_2.setMusicFileId(musicFile4_3);
        rateRepository.save(rate9_2);

        rateRepository.save(rate9_2);

        Rate rate10_1 = new Rate();
        rate10_1.setRate(Rate.Rating.EIGHT);
        rate10_1.setUserId(user2);
        rate10_1.setMusicFileId(musicFile5_1);
        rateRepository.save(rate10_1);

        rateRepository.save(rate10_1);

        Rate rate10_2 = new Rate();
        rate10_2.setRate(Rate.Rating.SEVEN);
        rate10_2.setUserId(user3);
        rate10_2.setMusicFileId(musicFile5_1);
        rateRepository.save(rate10_2);

        rateRepository.save(rate10_2);

        Rate rate10_3 = new Rate();
        rate10_3.setRate(Rate.Rating.EIGHT);
        rate10_3.setUserId(user4);
        rate10_3.setMusicFileId(musicFile5_1);
        rateRepository.save(rate10_3);

        rateRepository.save(rate10_3);

        Rate rate11_1 = new Rate();
        rate11_1.setRate(Rate.Rating.FIVE);
        rate11_1.setUserId(user1);
        rate11_1.setMusicFileId(musicFile5_2);
        rateRepository.save(rate11_1);

        rateRepository.save(rate11_1);

        // Comments

        Comment comment1_1 = new Comment();
        comment1_1.setCommentText("Who liked the track?");
        comment1_1.setReply(null);
        comment1_1.setPostDate(LocalDateTime.parse("2024-04-21T07:24:35"));
        comment1_1.setUserId(user1);
        comment1_1.setMusicFileId(musicFile1_1);

        commentRepository.save(comment1_1);

        Comment comment1_2 = new Comment();
        comment1_2.setCommentText("Not so bad :)");
        comment1_2.setReply(comment1_1);
        comment1_2.setPostDate(LocalDateTime.parse("2024-04-21T09:01:25"));
        comment1_2.setUserId(user2);
        comment1_2.setMusicFileId(musicFile1_1);

        commentRepository.save(comment1_2);

        Comment comment1_3 = new Comment();
        comment1_3.setCommentText("FOR REAL!");
        comment1_3.setReply(comment1_2);
        comment1_3.setPostDate(LocalDateTime.parse("2024-04-21T11:12:12"));
        comment1_3.setUserId(user4);
        comment1_3.setMusicFileId(musicFile1_1);

        commentRepository.save(comment1_3);

        Comment comment2_1 = new Comment();
        comment2_1.setCommentText("ROK");
        comment2_1.setReply(null);
        comment2_1.setPostDate(LocalDateTime.parse("2024-04-22T23:00:00"));
        comment2_1.setUserId(user5);
        comment2_1.setMusicFileId(musicFile1_2);

        commentRepository.save(comment2_1);

        Comment comment3_1 = new Comment();
        comment3_1.setCommentText("LOVE HER~!!!");
        comment3_1.setReply(null);
        comment3_1.setPostDate(LocalDateTime.parse("2024-04-20T15:19:23"));
        comment3_1.setUserId(user5);
        comment3_1.setMusicFileId(musicFile2_1);

        commentRepository.save(comment3_1);

        Comment comment3_2 = new Comment();
        comment3_2.setCommentText("Queen)");
        comment3_2.setReply(comment3_1);
        comment3_2.setPostDate(LocalDateTime.parse("2024-04-21T07:25:59"));
        comment3_2.setUserId(user1);
        comment3_2.setMusicFileId(musicFile2_1);

        commentRepository.save(comment3_2);

        Comment comment5_1 = new Comment();
        comment5_1.setCommentText("Just liked this song, nothing special)");
        comment5_1.setReply(null);
        comment5_1.setPostDate(LocalDateTime.parse("2024-04-15T15:52:09"));
        comment5_1.setUserId(user2);
        comment5_1.setMusicFileId(musicFile2_3);

        commentRepository.save(comment5_1);

        Comment comment7_1 = new Comment();
        comment7_1.setCommentText("Look so good, yeah, look so sweet!");
        comment7_1.setReply(null);
        comment7_1.setPostDate(LocalDateTime.parse("2024-04-20T13:33:01"));
        comment7_1.setUserId(user1);
        comment7_1.setMusicFileId(musicFile4_1);

        commentRepository.save(comment7_1);

        Comment comment7_2 = new Comment();
        comment7_2.setCommentText("Lookin' good enough to eat!))");
        comment7_2.setReply(comment7_1);
        comment7_2.setPostDate(LocalDateTime.parse("2024-04-22T22:41:58"));
        comment7_2.setUserId(user5);
        comment7_2.setMusicFileId(musicFile4_1);

        commentRepository.save(comment7_2);

        Comment comment7_3 = new Comment();
        comment7_3.setCommentText("cool-cool-cool");
        comment7_3.setReply(null);
        comment7_3.setPostDate(LocalDateTime.parse("2024-04-22T01:22:21"));
        comment7_3.setUserId(user3);
        comment7_3.setMusicFileId(musicFile4_1);

        commentRepository.save(comment7_3);

        Comment comment11_1 = new Comment();
        comment11_1.setCommentText("meh... so-so...");
        comment11_1.setReply(null);
        comment11_1.setPostDate(LocalDateTime.parse("2024-04-22T22:51:17"));
        comment11_1.setUserId(user1);
        comment11_1.setMusicFileId(musicFile5_2);

        commentRepository.save(comment11_1);

        // Categories

        List<MusicFile> musicFiles;
        musicFiles = musicFileRepository.findAll();

        Category category_1 = new Category();
        category_1.setName("Most Downloaded!");
        category_1.setDescription("The most downloaded songs from our users!");
        category_1.setImageUrl("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.dreamstime.com%2Fphotos-images%2Fedm-music.html&psig=AOvVaw3sg03noxWLZcQ3Yh5Bkvlc&ust=1714261147510000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCPCYmsiG4YUDFQAAAAAdAAAAABAJ");

        List<MusicFile> musicFiles1 = new ArrayList<>(musicFiles);

        musicFiles1.sort((file1, file2) -> {
            int downloadComparison = Integer.compare(file2.getDownloadsNumber(), file1.getDownloadsNumber());
            if (downloadComparison != 0) {
                return downloadComparison;
            } else {
                return Double.compare(file2.getAverageRate(), file1.getAverageRate());
            }
        });

        category_1.setMusicFiles(musicFiles1);

        categoryRepository.save(category_1);

        Category category_2 = new Category();
        category_2.setName("Most Rated!");
        category_2.setDescription("The most rated songs from our users!");
        category_2.setImageUrl("https://www.google.com/url?sa=i&url=https%3A%2F%2Funsplash.com%2Fs%2Fphotos%2Fmusic&psig=AOvVaw3sg03noxWLZcQ3Yh5Bkvlc&ust=1714261147510000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCPCYmsiG4YUDFQAAAAAdAAAAABAE");

        List<MusicFile> musicFiles2 = new ArrayList<>(musicFiles);

        musicFiles2.sort((file1, file2) -> {
            int ratingComparison = Double.compare(file2.getAverageRate(), file1.getAverageRate());
            if (ratingComparison != 0) {
                return ratingComparison;
            } else {
                return Integer.compare(file2.getMusicFileRatingList().size(), file1.getMusicFileRatingList().size());
            }
        });

        category_2.setMusicFiles(musicFiles2);

        categoryRepository.save(category_2);

        Category category_3 = new Category();
        category_3.setName("Most Recent!");
        category_3.setDescription("The most recent songs from our users!");
        category_3.setImageUrl("https://www.google.com/url?sa=i&url=https%3A%2F%2Funsplash.com%2Fs%2Fphotos%2Fmusic&psig=AOvVaw3sg03noxWLZcQ3Yh5Bkvlc&ust=1714261147510000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCPCYmsiG4YUDFQAAAAAdAAAAABAE");

        List<MusicFile> musicFiles3 = new ArrayList<>(musicFiles);
        musicFiles3.sort((file1, file2) -> file2.getDownloadDate().compareTo(file1.getDownloadDate()));
        category_3.setMusicFiles(musicFiles3);

        categoryRepository.save(category_3);

        // Playlists

        Playlist playlist1_1 = new Playlist();
        playlist1_1.setName("Pop");
        playlist1_1.setDescription("Some cool songs from others)");
        playlist1_1.setUserId(user1);

        Set<MusicFile> playlistMusic1_1 = new HashSet<>();
        playlistMusic1_1.add(musicFile1_1);
        playlistMusic1_1.add(musicFile2_1);
        playlistMusic1_1.add(musicFile2_3);
        playlist1_1.setMusicFiles(playlistMusic1_1);

        playlistRepository.save(playlist1_1);

        Playlist playlist5_1 = new Playlist();
        playlist5_1.setName("Huh?");
        playlist5_1.setDescription("IDK, just the songs");
        playlist5_1.setUserId(user5);

        Set<MusicFile> playlistMusic5_1 = new HashSet<>();
        playlistMusic5_1.add(musicFile4_3);
        playlistMusic5_1.add(musicFile2_2);
        playlistMusic5_1.add(musicFile2_1);
        playlist5_1.setMusicFiles(playlistMusic5_1);

        playlistRepository.save(playlist5_1);

    }

}
