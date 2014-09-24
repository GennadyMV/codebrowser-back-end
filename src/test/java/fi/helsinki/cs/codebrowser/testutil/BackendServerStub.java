package fi.helsinki.cs.codebrowser.testutil;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public final class BackendServerStub {

    public static final String ANY = ".*";
    public static final String MAYBE_PARAM = "(\\?.*)?";

    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APP_JSON = "application/json";
    public static final String APP_ZIP = "application/zip";
    public static final String TEXT_PLAIN = "text/plain";

    public static final String NO_SUCH_ID = "NoSuchId";

    public static final String INSTANCE_ID = "hy";
    public static final String INSTANCE_NAME = "hy";

    public static final String COURSE_PLAINID = "26";
    public static final String COURSE_NAME = "s2013-wepa";
    public static final String COURSE_ID = "czIwMTMtd2VwYQ";

    public static final String STUDENT_USERNAME = "014000000";
    public static final String STUDENT_ID = "MDE0MDAwMDAw";
    public static final String STUDENT_FIRSTNAME = "userFirstname";
    public static final String STUDENT_LASTNAME = "userLastname";
    public static final String STUDENT_EMAIL = "userEmail";

    public static final String EXERCISE_NAME = "WK1-W1E04.FirstHtmlForm";
    public static final String EXERCISE_ID = "V0sxLVcxRTA0LkZpcnN0SHRtbEZvcm0";

    public static final String SNAPSHOT_ID = "1392806595834377201443861";
    public static final String SNAPSHOT_TIMESTAMP = "1392806595834";

    public static final String FILE_NAME = "pom.xml";
    public static final String FILE_ID = "cG9tLnhtbDEzOTI4MDY1OTU4MzQzNzcyMDE0NDM4NjE";
    public static final String FILE_CONTENT = "This is pom.xml";

    // TMC API

    public static final String COURSES_JSON_URL = "/hy/courses.json";
    public static final String COURSE_JSON_URL = "/hy/courses/26.json";
    public static final String PARTICIPANTS_JSON_URL = "/hy/participants.json";
    public static final String POINTS_JSON_URL = "/hy/courses/26/points.json";
    public static final String EXERCISE_JSON_URL = "/hy/exercises/2112.json";

    public static final String COURSES_JSON = "{\"api_version\":7,\"courses\":[{\"id\":31,\"name\":\"k2014-ohja\",\"details_url\":\"http://tmc.mooc.fi/hy/courses/31.json\",\"unlock_url\":\"http://tmc.mooc.fi/hy/courses/31/unlock.json\",\"reviews_url\":\"http://tmc.mooc.fi/hy/courses/31/reviews.json\",\"comet_url\":\"http://tmc.mooc.fi:8080/comet\",\"spyware_urls\":[\"http://hy.spyware.testmycode.net/\"]},{\"id\":26,\"name\":\"s2013-wepa\",\"details_url\":\"http://tmc.mooc.fi/hy/courses/26.json\",\"unlock_url\":\"http://tmc.mooc.fi/hy/courses/26/unlock.json\",\"reviews_url\":\"http://tmc.mooc.fi/hy/courses/26/reviews.json\",\"comet_url\":\"http://tmc.mooc.fi:8080/comet\",\"spyware_urls\":[\"http://hy.spyware.testmycode.net/\"]}]}";
    public static final String COURSE_JSON = "{\"api_version\":7,\"course\":{\"id\":26,\"name\":\"s2013-wepa\",\"details_url\":\"http://tmc.mooc.fi/hy/courses/26.json\",\"unlock_url\":\"http://tmc.mooc.fi/hy/courses/26/unlock.json\",\"reviews_url\":\"http://tmc.mooc.fi/hy/courses/26/reviews.json\",\"comet_url\":\"http://tmc.mooc.fi:8080/comet\",\"spyware_urls\":[\"http://hy.spyware.testmycode.net/\"],\"unlockables\":[],\"exercises\":[{\"id\":2112,\"name\":\"WK1-W1E04.FirstHtmlForm\",\"locked\":false,\"deadline_description\":\"2013-09-11 23:59:00 +0300\",\"deadline\":\"2013-09-11T23:59:00+03:00\",\"checksum\":\"e41c0780ad6ffddcb12829e2c9f309a9\",\"return_url\":\"http://tmc.mooc.fi/hy/exercises/2112/submissions.json\",\"zip_url\":\"http://tmc.mooc.fi/hy/exercises/2112.zip\",\"returnable\":true,\"requires_review\":false,\"attempted\":false,\"completed\":false,\"reviewed\":false,\"all_review_points_given\":true,\"memory_limit\":null,\"runtime_params\":[],\"solution_zip_url\":\"http://tmc.mooc.fi/hy/exercises/2112/solution.zip\",\"exercise_submissions_url\":\"http://tmc.mooc.fi/hy/exercises/2112.json?api_version=7\"},{\"id\":2113,\"name\":\"WK1-W1E05.PageViewCounter\",\"locked\":false,\"deadline_description\":\"2013-09-11 23:59:00 +0300\",\"deadline\":\"2013-09-11T23:59:00+03:00\",\"checksum\":\"3136319ea5be1b33bbf105ef380f319c\",\"return_url\":\"http://tmc.mooc.fi/hy/exercises/2113/submissions.json\",\"zip_url\":\"http://tmc.mooc.fi/hy/exercises/2113.zip\",\"returnable\":true,\"requires_review\":false,\"attempted\":false,\"completed\":false,\"reviewed\":false,\"all_review_points_given\":true,\"memory_limit\":null,\"runtime_params\":[],\"solution_zip_url\":\"http://tmc.mooc.fi/hy/exercises/2113/solution.zip\",\"exercise_submissions_url\":\"http://tmc.mooc.fi/hy/exercises/2113.json?api_version=7\"}]}}";
    public static final String PARTICIPANTS_JSON = "{\"api_version\":7,\"participants\":[{\"id\":1,\"username\":\"014000000\",\"email\":\"userEmail\",\"first_name\":\"userFirstname\",\"last_name\":\"userLastname\"},{\"id\":2,\"username\":\"secondUser\",\"email\":\"secondUserEmail\",\"first_name\":\"secondUserFirst\",\"last_name\":\"secondUserLast\"},{\"id\":3,\"username\":\"nope\",\"email\":\"noEmail\",\"first_name\":\"no\",\"last_name\":\"pe\"}]}";
    public static final String POINTS_JSON = "{\"sheets\":[{\"name\":\"WK1\",\"total_awarded\":1107,\"total_available\":11},{\"name\":\"WK2\",\"total_awarded\":1262,\"total_available\":14},{\"name\":\"WK3\",\"total_awarded\":1153,\"total_available\":15},{\"name\":\"WK4\",\"total_awarded\":1269,\"total_available\":16},{\"name\":\"WK5\",\"total_awarded\":958,\"total_available\":13},{\"name\":\"WK6\",\"total_awarded\":762,\"total_available\":12}],\"total_awarded\":6511,\"total_available\":81,\"awarded_for_user_and_sheet\":{\"014000000\":{\"WK1\":11,\"WK2\":14,\"WK3\":15,\"WK4\":16,\"WK5\":13,\"WK6\":7}},\"total_for_user\":{\"014000000\":76},\"users\":[{\"administrator\":false,\"id\":1,\"login\":\"014000000\"}]}";
    public static final String EXERCISE_JSON = "{\"course_name\":\"s2013-wepa\",\"course_id\":26,\"exercise_name\":\"WK1-W1E04.FirstHtmlForm\",\"exercise_id\":2112,\"unlocked_at\":null,\"deadline\":\"2013-09-11T23:59:00+03:00\",\"submissions\":[{\"exercise_name\":\"WK1-W1E04.FirstHtmlForm\",\"id\":139385,\"course_id\":26,\"user_id\":1,\"created_at\":\"2013-09-11T23:58:48+03:00\",\"all_tests_passed\":true,\"points\":\"W1E04\",\"submitted_zip_url\":\"a\",\"paste_url\":null,\"processing_time\":45,\"reviewed?\":false,\"requests_review?\":false},{\"exercise_name\":\"WK1-W1E04.FirstHtmlForm\",\"id\":139369,\"course_id\":26,\"user_id\":2,\"created_at\":\"2013-09-11T23:45:48+03:00\",\"all_tests_passed\":true,\"points\":\"W1E04\",\"submitted_zip_url\":\"a\",\"paste_url\":null,\"processing_time\":33,\"reviewed?\":false,\"requests_review?\":false}]}";

    // Snapshot API

    public static final String INSTANCES_URL = "/";
    public static final String INSTANCE_URL = "/hy";
    public static final String STUDENTS_URL = "/hy/participants";
    public static final String STUDENT_URL = "/hy/participants/MDE0MDAwMDAw";
    public static final String STUDENT_COURSES_URL = "/hy/participants/MDE0MDAwMDAw/courses";
    public static final String STUDENT_COURSE_URL = "/hy/participants/MDE0MDAwMDAw/courses/czIwMTMtd2VwYQ";
    public static final String STUDENT_COURSE_EXERCISES_URL = "/hy/participants/MDE0MDAwMDAw/courses/czIwMTMtd2VwYQ/exercises";
    public static final String STUDENT_COURSE_EXERCISE_URL = "/hy/participants/MDE0MDAwMDAw/courses/czIwMTMtd2VwYQ/exercises/V0sxLVcxRTA0LkZpcnN0SHRtbEZvcm0";
    public static final String STUDENT_COURSE_EXERCISE_SNAPSHOTS_URL = "/hy/participants/MDE0MDAwMDAw/courses/czIwMTMtd2VwYQ/exercises/V0sxLVcxRTA0LkZpcnN0SHRtbEZvcm0/snapshots";
    public static final String STUDENT_COURSE_EXERCISE_SNAPSHOTS_ZIP_URL = "/hy/participants/MDE0MDAwMDAw/courses/czIwMTMtd2VwYQ/exercises/V0sxLVcxRTA0LkZpcnN0SHRtbEZvcm0/snapshots/files.zip";
    public static final String STUDENT_COURSE_EXERCISE_SNAPSHOT_URL = "/hy/participants/MDE0MDAwMDAw/courses/czIwMTMtd2VwYQ/exercises/V0sxLVcxRTA0LkZpcnN0SHRtbEZvcm0/snapshots/1392806595834377201443861";
    public static final String STUDENT_COURSE_EXERCISE_SNAPSHOT_FILES_URL = "/hy/participants/MDE0MDAwMDAw/courses/czIwMTMtd2VwYQ/exercises/V0sxLVcxRTA0LkZpcnN0SHRtbEZvcm0/snapshots/1392806595834377201443861/files";
    public static final String STUDENT_COURSE_EXERCISE_SNAPSHOT_FILE_URL = "/hy/participants/MDE0MDAwMDAw/courses/czIwMTMtd2VwYQ/exercises/V0sxLVcxRTA0LkZpcnN0SHRtbEZvcm0/snapshots/1392806595834377201443861/files/cG9tLnhtbDEzOTI4MDY1OTU4MzQzNzcyMDE0NDM4NjE";
    public static final String STUDENT_COURSE_EXERCISE_SNAPSHOT_FILE_CONTENT_URL = "/hy/participants/MDE0MDAwMDAw/courses/czIwMTMtd2VwYQ/exercises/V0sxLVcxRTA0LkZpcnN0SHRtbEZvcm0/snapshots/1392806595834377201443861/files/cG9tLnhtbDEzOTI4MDY1OTU4MzQzNzcyMDE0NDM4NjE/content";

    public static final String INSTANCES_JSON = "[{\"id\":\"hy\",\"name\":\"hy\"},{\"id\":\"mooc\",\"name\":\"mooc\"}]";
    public static final String INSTANCE_JSON = "{\"id\":\"hy\",\"name\":\"hy\"}";
    public static final String STUDENTS_JSON = "[{\"id\":\"MDE0MDAwMDAw\",\"username\":\"014000000\"},{\"id\":\"c2Vjb25kVXNlcg==\",\"username\":\"secondUser\"}]";
    public static final String STUDENT_JSON = "{\"id\":\"MDE0MDAwMDAw\",\"username\":\"014000000\",\"courses\":[{\"id\":\"czIwMTMtd2VwYQ\",\"name\":\"s2013-wepa\",\"exercises\":[{\"id\":\"V0sxLVcxRTA0LkZpcnN0SHRtbEZvcm0\",\"name\":\"WK1-W1E04.FirstHtmlForm\"},{\"id\":\"V0sxLVcxRTA1LlBhZ2VWaWV3Q291bnRlcg\",\"name\":\"WK1-W1E05.PageViewCounter\"}]}]}";
    public static final String STUDENT_COURSES_JSON = "[{\"id\":\"czIwMTMtd2VwYQ\",\"name\":\"s2013-wepa\",\"exercises\":[{\"id\":\"V0sxLVcxRTA0LkZpcnN0SHRtbEZvcm0\",\"name\":\"WK1-W1E04.FirstHtmlForm\"},{\"id\":\"V0sxLVcxRTA1LlBhZ2VWaWV3Q291bnRlcg\",\"name\":\"WK1-W1E05.PageViewCounter\"}]}]";
    public static final String STUDENT_COURSE_JSON = "{\"id\":\"czIwMTMtd2VwYQ\",\"name\":\"s2013-wepa\",\"exercises\":[{\"id\":\"V0sxLVcxRTA0LkZpcnN0SHRtbEZvcm0\",\"name\":\"WK1-W1E04.FirstHtmlForm\"}]}";
    public static final String STUDENT_COURSE_EXERCISES_JSON = "[{\"id\":\"V0sxLVcxRTA0LkZpcnN0SHRtbEZvcm0\",\"name\":\"WK1-W1E04.FirstHtmlForm\"},{\"id\":\"V0sxLVcxRTA1LlBhZ2VWaWV3Q291bnRlcg\",\"name\":\"WK1-W1E05.PageViewCounter\"}]";
    public static final byte[] STUDENT_COURSE_EXERCISE_SNAPSHOTS_ZIP = {0x00, 0x01, 0x02, 0x03};
    public static final String STUDENT_COURSE_EXERCISE_JSON = "{\"id\":\"V0sxLVcxRTA0LkZpcnN0SHRtbEZvcm0\",\"name\":\"WK1-W1E04.FirstHtmlForm\"}";
    public static final String STUDENT_COURSE_EXERCISE_SNAPSHOTS_JSON = "[{\"id\":\"1392806595834377201443861\",\"timestamp\":1392806595834,\"files\":[{\"id\":\"bmJhY3Rpb25zLnhtbDEzOTI4MDY1OTU4MzQzNzcyMDE0NDM4NjE\",\"path\":\"nbactions.xml\",\"name\":\"nbactions.xml\"},{\"id\":\"cG9tLnhtbDEzOTI4MDY1OTU4MzQzNzcyMDE0NDM4NjE\",\"path\":\"pom.xml\",\"name\":\"pom.xml\"}]}]";
    public static final String STUDENT_COURSE_EXERCISE_SNAPSHOT_JSON = "{\"id\":\"1392806595834377201443861\",\"timestamp\":1392806595834,\"files\":[{\"id\":\"bmJhY3Rpb25zLnhtbDEzOTI4MDY1OTU4MzQzNzcyMDE0NDM4NjE\",\"path\":\"nbactions.xml\",\"name\":\"nbactions.xml\"},{\"id\":\"cG9tLnhtbDEzOTI4MDY1OTU4MzQzNzcyMDE0NDM4NjE\",\"path\":\"pom.xml\",\"name\":\"pom.xml\"}]}";
    public static final String STUDENT_COURSE_EXERCISE_SNAPSHOT_FILES_JSON = "[{\"id\":\"bmJhY3Rpb25zLnhtbDEzOTI4MDY1OTU4MzQzNzcyMDE0NDM4NjE\",\"path\":\"nbactions.xml\",\"name\":\"nbactions.xml\"},{\"id\":\"cG9tLnhtbDEzOTI4MDY1OTU4MzQzNzcyMDE0NDM4NjE\",\"path\":\"pom.xml\",\"name\":\"pom.xml\"}]";
    public static final String STUDENT_COURSE_EXERCISE_SNAPSHOT_FILE_JSON = "{\"id\":\"cG9tLnhtbDEzOTI4MDY1OTU4MzQzNzcyMDE0NDM4NjE\",\"path\":\"pom.xml\",\"name\":\"pom.xml\"}";
    public static final String STUDENT_COURSE_EXERCISE_SNAPSHOT_FILE_CONTENT = "This is pom.xml";

    private void stubGetWithJsonResponse(final int priority, final String url, final String response) {

        stubFor(get(urlMatching(url + MAYBE_PARAM))
                .atPriority(priority)
                .willReturn(aResponse()
                            .withBody(response)
                            .withHeader(CONTENT_TYPE, APP_JSON)));
    }

    private void stubGetWithPlainResponse(final int priority, final String url, final String response) {

        stubFor(get(urlMatching(url + MAYBE_PARAM))
                .atPriority(priority)
                .willReturn(aResponse()
                            .withBody(response)
                            .withHeader(CONTENT_TYPE, TEXT_PLAIN)));
    }

    private void stubGetWithZipResponse(final int priority, final String url, final byte[] response) {

        stubFor(get(urlMatching(url + MAYBE_PARAM))
                .atPriority(priority)
                .willReturn(aResponse()
                            .withBody(response)
                            .withHeader(CONTENT_TYPE, APP_ZIP)));
    }

    public void initialiseServer() {

        // TMC API
        stubGetWithJsonResponse(1, COURSES_JSON_URL, COURSES_JSON);
        stubGetWithJsonResponse(1, COURSE_JSON_URL, COURSE_JSON);
        stubGetWithJsonResponse(1, PARTICIPANTS_JSON_URL, PARTICIPANTS_JSON);
        stubGetWithJsonResponse(1, POINTS_JSON_URL, POINTS_JSON);
        stubGetWithJsonResponse(1, EXERCISE_JSON_URL, EXERCISE_JSON);

        // Snapshot API
        stubGetWithJsonResponse(14, INSTANCES_URL, INSTANCES_JSON);
        stubGetWithJsonResponse(13, INSTANCE_URL, INSTANCE_JSON);
        stubGetWithJsonResponse(12, STUDENTS_URL, STUDENTS_JSON);
        stubGetWithJsonResponse(11, STUDENT_URL, STUDENT_JSON);
        stubGetWithJsonResponse(10, STUDENT_COURSES_URL, STUDENT_COURSES_JSON);
        stubGetWithJsonResponse(9, STUDENT_COURSE_URL, STUDENT_COURSE_JSON);
        stubGetWithJsonResponse(8, STUDENT_COURSE_EXERCISES_URL, STUDENT_COURSE_EXERCISES_JSON);
        stubGetWithJsonResponse(7, STUDENT_COURSE_EXERCISE_URL, STUDENT_COURSE_EXERCISE_JSON);
        stubGetWithJsonResponse(6, STUDENT_COURSE_EXERCISE_SNAPSHOTS_URL, STUDENT_COURSE_EXERCISE_SNAPSHOTS_JSON);
        stubGetWithZipResponse(5, STUDENT_COURSE_EXERCISE_SNAPSHOTS_ZIP_URL, STUDENT_COURSE_EXERCISE_SNAPSHOTS_ZIP);
        stubGetWithJsonResponse(4, STUDENT_COURSE_EXERCISE_SNAPSHOT_URL, STUDENT_COURSE_EXERCISE_SNAPSHOT_JSON);
        stubGetWithJsonResponse(3, STUDENT_COURSE_EXERCISE_SNAPSHOT_FILES_URL, STUDENT_COURSE_EXERCISE_SNAPSHOT_FILES_JSON);
        stubGetWithJsonResponse(2, STUDENT_COURSE_EXERCISE_SNAPSHOT_FILE_URL, STUDENT_COURSE_EXERCISE_SNAPSHOT_FILE_JSON);
        stubGetWithPlainResponse(1, STUDENT_COURSE_EXERCISE_SNAPSHOT_FILE_CONTENT_URL, STUDENT_COURSE_EXERCISE_SNAPSHOT_FILE_CONTENT);
    }
}
