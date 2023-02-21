package com.gulteking.mqttbackendserver.utils.constants;

public class StringConstants {
    //Texts
    public static final String TEXT_EMPTY = "";
    public static final String TEXT_SPACE = " ";
    public static final String TEXT_FOR = "for ";
    public static final String TEXT_TO = " to ";
    public static final String TEXT_SLASH = "/";
    public static final String TEXT_TYPE_TEXT_HTML = "text/html";
    public static final String TEXT_DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String TEXT_TOPIC_PUBLISHING_KEY = "/p";

    //Swagger Constants
    public static final String TEXT_SWAGGER_TITLE = "All E-nurse APIs";
    public static final String TEXT_SWAGGER_DESC = "REST APIs reference for developers";
    public static final String TEXT_SWAGGER_CONTACT_EMAIL = "cmile@gmail.com";
    public static final String TEXT_SWAGGER_LICENCE = "http://licence-url.com";
    public static final String TEXT_APP_NAME = "E-nurse";
    public static final String TEXT_SWAGGER_API_VERSION = "1.0";
    public static final String TEXT_SWAGGER_REGEX_PATH = "/*.*";

    //Bed Controller constants
    public static final String REQUEST_SUCCESS_MESSAGE_BED_CREATED = "Bed Created Successfully!!";
    public static final String REQUEST_SUCCESS_MESSAGE_BED_UPDATED = "Bed Updated Successfully!!";
    public static final String REQUEST_SUCCESS_MESSAGE_BED_DELETED = "Bed Deleted Successfully!!";
    public static final String REQUEST_SUCCESS_MESSAGE_BED_FETCHED = "Bed Fetched Successfully!!";
    public static final String REQUEST_SUCCESS_MESSAGE_SELECTED_BED_FETCHED = "Fetched Bed by id: ";
    public static final String REQUEST_FAILURE_MESSAGE_NO_BED_FOUND = "No Beds Found";
    public static final String REQUEST_FAILURE_MESSAGE_BED_CANT_BE_CREATED = "Bed cant be created";
    public static final String REQUEST_FAILURE_MESSAGE_NO_BED_FOUND_FOR_SELECTED_ID = "No Bed found with selected Bed id :";


    public static final String TEXT_AUTHORIZATION = "Authorization";
    public static final String TEXT_AND_PAGE_SIZE = " and page size: ";

    //Path Variables
    public static final String PATH_VARIABLE_ID = "id";

    public static final Boolean FALSE = false;
    public static final Boolean TRUE = true;

    //Language Constants
    public static final String LANG_DEFAULT = "default";
    public static final String LANG_ENGLISH = "en";

    //Subscriber and Publisher Constants
    public static final String TEXT_SUBSCRIBER = "sub";
    public static final String TEXT_PUBLISHER = "pub";

    public static final String TEXT_ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "0123456789"
            + "abcdefghijklmnopqrstuvxyz";

}
