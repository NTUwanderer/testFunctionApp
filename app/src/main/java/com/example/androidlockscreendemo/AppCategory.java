package com.example.androidlockscreendemo;

import java.io.Serializable;

public enum AppCategory implements Serializable{
    GAMES,
    BOOKS_REFERENCE,
    BUSINESS,
    COMICS,
    EDUCATION,
    ENTERTAINMENT,
    FINANCE,
    HEALTH_FITNESS,
    LIBRARIES_DEMO,
    LIFESTYLE,
    LIVE_WALLPAPER,
    MEDIA_VIDEO,
    MEDICAL,
    MUSIC_AUDIO,
    NEWS_MAGAZINES,
    PERSONALIZATION,
    PHOTOGRAPHY,
    PRODUCTIVITY,
    SHOPPING,
    SOCIAL,
    TOOLS,
    TRANSPORTATION,
    TRAVEL_LOCAL,
    WEATHER,
    WIDGETS;

    public String toString() {
        switch(this) {
            case GAMES:
                return "Games";
            case BOOKS_REFERENCE:
                return "Books & Reference";
            case BUSINESS:
                return "Business";
            case COMICS:
                return "Comics";
            case EDUCATION:
                return "Education";
            case ENTERTAINMENT:
                return "Entertainment";
            case FINANCE:
                return "Finance";
            case HEALTH_FITNESS:
                return "Health & Fitness";
            case LIBRARIES_DEMO:
                return "Libraries & Demo";
            case LIFESTYLE:
                return "Lifestyle";
            case LIVE_WALLPAPER:
                return "Live_Wallpaper";
            case MEDIA_VIDEO:
                return "Media & Video";
            case MEDICAL:
                return "Medical";
            case MUSIC_AUDIO:
                return "Music & Audio";
            case NEWS_MAGAZINES:
                return "News & Magazines";
            case PERSONALIZATION:
                return "Personalization";
            case PHOTOGRAPHY:
                return "Photography";
            case PRODUCTIVITY:
                return "Productivity";
            case SHOPPING:
                return "Shopping";
            case SOCIAL:
                return "Social";
            case TOOLS:
                return "Tools";
            case TRANSPORTATION:
                return "Transportation";
            case TRAVEL_LOCAL:
                return "Travel & Local";
            case WEATHER:
                return "Weather";
            case WIDGETS:
                return "Widgets";
        }

        return null;
    }

    public static AppCategory ValueOf(Class<AppCategory> enumType, String value){
        if(value.equalsIgnoreCase(GAMES.toString()))
            return AppCategory.GAMES;
        else if(value.equalsIgnoreCase(BOOKS_REFERENCE.toString()))
            return AppCategory.BOOKS_REFERENCE;
        else if(value.equalsIgnoreCase(BUSINESS.toString()))
            return AppCategory.BUSINESS;


        else if(value.equalsIgnoreCase(COMICS.toString()))
            return AppCategory.COMICS;
        else if(value.equalsIgnoreCase(EDUCATION.toString()))
            return AppCategory.EDUCATION;
        else if(value.equalsIgnoreCase(ENTERTAINMENT.toString()))
            return AppCategory.ENTERTAINMENT;
        else if(value.equalsIgnoreCase(FINANCE.toString()))
            return AppCategory.FINANCE;
        else if(value.equalsIgnoreCase(HEALTH_FITNESS.toString()))
            return AppCategory.HEALTH_FITNESS;
        else if(value.equalsIgnoreCase(LIBRARIES_DEMO.toString()))
            return AppCategory.LIBRARIES_DEMO;
        else if(value.equalsIgnoreCase(LIFESTYLE.toString()))
            return AppCategory.LIFESTYLE;
        else if(value.equalsIgnoreCase(LIVE_WALLPAPER.toString()))
            return AppCategory.LIVE_WALLPAPER;

        else if(value.equalsIgnoreCase(MEDIA_VIDEO.toString()))
            return AppCategory.MEDIA_VIDEO;
        else if(value.equalsIgnoreCase(MEDICAL.toString()))
            return AppCategory.MEDICAL;
        else if(value.equalsIgnoreCase(MUSIC_AUDIO.toString()))
            return AppCategory.MUSIC_AUDIO;
        else if(value.equalsIgnoreCase(NEWS_MAGAZINES.toString()))
            return AppCategory.NEWS_MAGAZINES;

        else if(value.equalsIgnoreCase(PERSONALIZATION.toString()))
            return AppCategory.PERSONALIZATION;
        else if(value.equalsIgnoreCase(PHOTOGRAPHY.toString()))
            return AppCategory.PHOTOGRAPHY;
        else if(value.equalsIgnoreCase(PRODUCTIVITY.toString()))
            return AppCategory.PRODUCTIVITY;
        else if(value.equalsIgnoreCase(SHOPPING.toString()))
            return AppCategory.SHOPPING;
        else if(value.equalsIgnoreCase(SOCIAL.toString()))
            return AppCategory.SOCIAL;

        else if(value.equalsIgnoreCase(TOOLS.toString()))
            return AppCategory.TOOLS;
        else if(value.equalsIgnoreCase(TRANSPORTATION.toString()))
            return AppCategory.TRANSPORTATION;
        else if(value.equalsIgnoreCase(TRAVEL_LOCAL.toString()))
            return AppCategory.TRAVEL_LOCAL;
        else if(value.equalsIgnoreCase(WEATHER.toString()))
            return AppCategory.WEATHER;
        else if(value.equalsIgnoreCase(WIDGETS.toString()))
            return AppCategory.WIDGETS;
        else
            return null;
    }

}