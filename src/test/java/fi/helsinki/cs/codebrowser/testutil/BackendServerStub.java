package fi.helsinki.cs.codebrowser.testutil;

import com.github.tomakehurst.wiremock.client.WireMock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public final class BackendServerStub {

    public static final String ANY = ".*";
    public static final String API_VERSION = "?api_version=7";

    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APP_JSON = "application/json";
    public static final String TEXT_PLAIN = "text/plain";

    public static final String NO_SUCH_ID = "NoSuchId";

    public static final String INSTANCE_ID = "hy";
    public static final String INSTANCE_NAME = "hy";

    public static final String COURSE_PLAINID = "26";
    public static final String COURSE_NAME = "s2013-wepa";
    public static final String COURSE_ID = "czIwMTMtd2VwYQ";

    public static final String STUDENT_USERNAME = "014000000";
    public static final String STUDENT_ID = "MDE0MDAwMDAw";

    public static final String EXERCISE_NAME = "WK1-W1E04.FirstHtmlForm";
    public static final String EXERCISE_ID = "V0sxLVcxRTA0LkZpcnN0SHRtbEZvcm0";

    public static final String SNAPSHOT_ID = "1392806595834377201443861";
    public static final String SNAPSHOT_TIMESTAMP = "1392806595834";

    public static final String FILE_NAME = "pom.xml";
    public static final String FILE_ID = "cG9tLnhtbDEzOTI4MDY1OTU4MzQzNzcyMDE0NDM4NjE";
    public static final String FILE_CONTENT = "This is pom.xml";


    // TMC
    public static final String COURSES_JSON_URL = "/hy/courses.json";
    public static final String COURSE_JSON_URL = "/hy/courses/26.json";

    public static final String COURSES_JSON = "{\"api_version\":7,\"courses\":[{\"id\":31,\"name\":\"k2014-ohja\",\"details_url\":\"http://tmc.mooc.fi/hy/courses/31.json\",\"unlock_url\":\"http://tmc.mooc.fi/hy/courses/31/unlock.json\",\"reviews_url\":\"http://tmc.mooc.fi/hy/courses/31/reviews.json\",\"comet_url\":\"http://tmc.mooc.fi:8080/comet\",\"spyware_urls\":[\"http://hy.spyware.testmycode.net/\"]},{\"id\":26,\"name\":\"s2013-wepa\",\"details_url\":\"http://tmc.mooc.fi/hy/courses/26.json\",\"unlock_url\":\"http://tmc.mooc.fi/hy/courses/26/unlock.json\",\"reviews_url\":\"http://tmc.mooc.fi/hy/courses/26/reviews.json\",\"comet_url\":\"http://tmc.mooc.fi:8080/comet\",\"spyware_urls\":[\"http://hy.spyware.testmycode.net/\"]}]}";
    public static final String COURSE_JSON = "{\"api_version\":7,\"course\":{\"id\":26,\"name\":\"s2013-wepa\",\"details_url\":\"http://tmc.mooc.fi/hy/courses/26.json\",\"unlock_url\":\"http://tmc.mooc.fi/hy/courses/26/unlock.json\",\"reviews_url\":\"http://tmc.mooc.fi/hy/courses/26/reviews.json\",\"comet_url\":\"http://tmc.mooc.fi:8080/comet\",\"spyware_urls\":[\"http://hy.spyware.testmycode.net/\"],\"unlockables\":[],\"exercises\":[{\"id\":2112,\"name\":\"WK1-W1E04.FirstHtmlForm\",\"locked\":false,\"deadline_description\":\"2013-09-11 23:59:00 +0300\",\"deadline\":\"2013-09-11T23:59:00+03:00\",\"checksum\":\"e41c0780ad6ffddcb12829e2c9f309a9\",\"return_url\":\"http://tmc.mooc.fi/hy/exercises/2112/submissions.json\",\"zip_url\":\"http://tmc.mooc.fi/hy/exercises/2112.zip\",\"returnable\":true,\"requires_review\":false,\"attempted\":false,\"completed\":false,\"reviewed\":false,\"all_review_points_given\":true,\"memory_limit\":null,\"runtime_params\":[],\"solution_zip_url\":\"http://tmc.mooc.fi/hy/exercises/2112/solution.zip\",\"exercise_submissions_url\":\"http://tmc.mooc.fi/hy/exercises/2112.json?api_version=7\"},{\"id\":2113,\"name\":\"WK1-W1E05.PageViewCounter\",\"locked\":false,\"deadline_description\":\"2013-09-11 23:59:00 +0300\",\"deadline\":\"2013-09-11T23:59:00+03:00\",\"checksum\":\"3136319ea5be1b33bbf105ef380f319c\",\"return_url\":\"http://tmc.mooc.fi/hy/exercises/2113/submissions.json\",\"zip_url\":\"http://tmc.mooc.fi/hy/exercises/2113.zip\",\"returnable\":true,\"requires_review\":false,\"attempted\":false,\"completed\":false,\"reviewed\":false,\"all_review_points_given\":true,\"memory_limit\":null,\"runtime_params\":[],\"solution_zip_url\":\"http://tmc.mooc.fi/hy/exercises/2113/solution.zip\",\"exercise_submissions_url\":\"http://tmc.mooc.fi/hy/exercises/2113.json?api_version=7\"}]}}";

    //Snapshot API
    public static final String INSTANCES_URL = "/";
    public static final String INSTANCE_URL = "/hy";
    public static final String STUDENT_URL = "/hy/participants/MDE0MDAwMDAw";
    public static final String STUDENT_COURSES_URL = "/hy/participants/MDE0MDAwMDAw/courses";
    public static final String STUDENT_COURSE_URL = "/hy/participants/MDE0MDAwMDAw/courses/czIwMTMtd2VwYQ";
    public static final String STUDENT_COURSE_EXERCISES_URL = "/hy/participants/MDE0MDAwMDAw/courses/czIwMTMtd2VwYQ/exercises";
    public static final String STUDENT_COURSE_EXERCISE_URL = "/hy/participants/MDE0MDAwMDAw/courses/czIwMTMtd2VwYQ/exercises/V0sxLVcxRTA0LkZpcnN0SHRtbEZvcm0";
    public static final String STUDENT_COURSE_EXERCISE_SNAPSHOTS_URL = "/hy/participants/MDE0MDAwMDAw/courses/czIwMTMtd2VwYQ/exercises/V0sxLVcxRTA0LkZpcnN0SHRtbEZvcm0/snapshots";
    public static final String STUDENT_COURSE_EXERCISE_SNAPSHOT_URL = "/hy/participants/MDE0MDAwMDAw/courses/czIwMTMtd2VwYQ/exercises/V0sxLVcxRTA0LkZpcnN0SHRtbEZvcm0/snapshots/1392806595834377201443861";
    public static final String STUDENT_COURSE_EXERCISE_SNAPSHOT_FILES_URL = "/hy/participants/MDE0MDAwMDAw/courses/czIwMTMtd2VwYQ/exercises/V0sxLVcxRTA0LkZpcnN0SHRtbEZvcm0/snapshots/1392806595834377201443861/files";
    public static final String STUDENT_COURSE_EXERCISE_SNAPSHOT_FILE_URL = "/hy/participants/MDE0MDAwMDAw/courses/czIwMTMtd2VwYQ/exercises/V0sxLVcxRTA0LkZpcnN0SHRtbEZvcm0/snapshots/1392806595834377201443861/files/cG9tLnhtbDEzOTI4MDY1OTU4MzQzNzcyMDE0NDM4NjE";
    public static final String STUDENT_COURSE_EXERCISE_SNAPSHOT_FILE_CONTENT_URL = "/hy/participants/MDE0MDAwMDAw/courses/czIwMTMtd2VwYQ/exercises/V0sxLVcxRTA0LkZpcnN0SHRtbEZvcm0/snapshots/1392806595834377201443861/files/cG9tLnhtbDEzOTI4MDY1OTU4MzQzNzcyMDE0NDM4NjE/content";

    public static final String INSTANCES_JSON = "[{\"id\":\"hy\",\"name\":\"hy\"},{\"id\":\"mooc\",\"name\":\"mooc\"}]";
    public static final String INSTANCE_JSON = "{\"id\":\"hy\",\"name\":\"hy\"}";
    public static final String STUDENT_JSON = "{\"id\":\"MDE0MDAwMDAw\",\"username\":\"014000000\",\"courses\":[{\"id\":\"czIwMTMtd2VwYQ\",\"name\":\"s2013-wepa\",\"exercises\":[{\"id\":\"V0sxLVcxRTA0LkZpcnN0SHRtbEZvcm0\",\"name\":\"WK1-W1E04.FirstHtmlForm\"},{\"id\":\"V0sxLVcxRTA1LlBhZ2VWaWV3Q291bnRlcg\",\"name\":\"WK1-W1E05.PageViewCounter\"}]}]}";
    public static final String STUDENT_COURSES_JSON = "[{\"id\":\"czIwMTMtd2VwYQ\",\"name\":\"s2013-wepa\",\"exercises\":[{\"id\":\"V0sxLVcxRTA0LkZpcnN0SHRtbEZvcm0\",\"name\":\"WK1-W1E04.FirstHtmlForm\"},{\"id\":\"V0sxLVcxRTA1LlBhZ2VWaWV3Q291bnRlcg\",\"name\":\"WK1-W1E05.PageViewCounter\"}]}]";
    public static final String STUDENT_COURSE_JSON = "{\"id\":\"czIwMTMtd2VwYQ\",\"name\":\"s2013-wepa\",\"exercises\":[{\"id\":\"V0sxLVcxRTA0LkZpcnN0SHRtbEZvcm0\",\"name\":\"WK1-W1E04.FirstHtmlForm\"}]}";
    public static final String STUDENT_COURSE_EXERCISES_JSON = "[{\"id\":\"V0sxLVcxRTA0LkZpcnN0SHRtbEZvcm0\",\"name\":\"WK1-W1E04.FirstHtmlForm\"},{\"id\":\"V0sxLVcxRTA1LlBhZ2VWaWV3Q291bnRlcg\",\"name\":\"WK1-W1E05.PageViewCounter\"}]";
    public static final String STUDENT_COURSE_EXERCISE_JSON = "{\"id\":\"V0sxLVcxRTA0LkZpcnN0SHRtbEZvcm0\",\"name\":\"WK1-W1E04.FirstHtmlForm\"}";
    public static final String STUDENT_COURSE_EXERCISE_SNAPSHOTS_JSON = "[{\"id\":\"1392806595834377201443861\",\"timestamp\":1392806595834,\"files\":[{\"id\":\"bmJhY3Rpb25zLnhtbDEzOTI4MDY1OTU4MzQzNzcyMDE0NDM4NjE\",\"path\":\"nbactions.xml\",\"name\":\"nbactions.xml\"},{\"id\":\"cG9tLnhtbDEzOTI4MDY1OTU4MzQzNzcyMDE0NDM4NjE\",\"path\":\"pom.xml\",\"name\":\"pom.xml\"}]}]";
    public static final String STUDENT_COURSE_EXERCISE_SNAPSHOT_JSON = "{\"id\":\"1392806595834377201443861\",\"timestamp\":1392806595834,\"files\":[{\"id\":\"bmJhY3Rpb25zLnhtbDEzOTI4MDY1OTU4MzQzNzcyMDE0NDM4NjE\",\"path\":\"nbactions.xml\",\"name\":\"nbactions.xml\"},{\"id\":\"cG9tLnhtbDEzOTI4MDY1OTU4MzQzNzcyMDE0NDM4NjE\",\"path\":\"pom.xml\",\"name\":\"pom.xml\"}]}";
    public static final String STUDENT_COURSE_EXERCISE_SNAPSHOT_FILES_JSON = "[{\"id\":\"bmJhY3Rpb25zLnhtbDEzOTI4MDY1OTU4MzQzNzcyMDE0NDM4NjE\",\"path\":\"nbactions.xml\",\"name\":\"nbactions.xml\"},{\"id\":\"cG9tLnhtbDEzOTI4MDY1OTU4MzQzNzcyMDE0NDM4NjE\",\"path\":\"pom.xml\",\"name\":\"pom.xml\"}]";
    public static final String STUDENT_COURSE_EXERCISE_SNAPSHOT_FILE_JSON = "{\"id\":\"cG9tLnhtbDEzOTI4MDY1OTU4MzQzNzcyMDE0NDM4NjE\",\"path\":\"pom.xml\",\"name\":\"pom.xml\"}";
    public static final String STUDENT_COURSE_EXERCISE_SNAPSHOT_FILE_CONTENT = "This is pom.xml";

    private void stubGetWithApiVersionWithJsonResponse(final String url, final String response) {

        stubFor(get(urlMatching(url + ANY))
                .willReturn(aResponse()
                           .withBody(response)
                           .withHeader(CONTENT_TYPE, APP_JSON)));
    }

    private void stubGetWithJsonResponse(final String url, final String response) {

        stubFor(get(urlMatching(url))
                .willReturn(aResponse()
                           .withBody(response)
                           .withHeader(CONTENT_TYPE, APP_JSON)));
    }

    private void stubGetWithPlainResponse(final String url, final String response) {

        stubFor(get(urlMatching(url))
                .willReturn(aResponse()
                           .withBody(response)
                           .withHeader(CONTENT_TYPE, TEXT_PLAIN)));
    }

    public void reset() {
        WireMock.reset();

        //TMC
        stubGetWithApiVersionWithJsonResponse(COURSES_JSON_URL, COURSES_JSON);
        stubGetWithApiVersionWithJsonResponse(COURSE_JSON_URL, COURSE_JSON);

        //SnapshotAPI
        stubGetWithJsonResponse(INSTANCES_URL, INSTANCES_JSON);
        stubGetWithJsonResponse(INSTANCE_URL, INSTANCE_JSON);
        stubGetWithJsonResponse(STUDENT_URL, STUDENT_JSON);
        stubGetWithJsonResponse(STUDENT_COURSES_URL, STUDENT_COURSES_JSON);
        stubGetWithJsonResponse(STUDENT_COURSE_URL, STUDENT_COURSE_JSON);
        stubGetWithJsonResponse(STUDENT_COURSE_EXERCISES_URL, STUDENT_COURSE_EXERCISES_JSON);
        stubGetWithJsonResponse(STUDENT_COURSE_EXERCISE_URL, STUDENT_COURSE_EXERCISE_JSON);
        stubGetWithJsonResponse(STUDENT_COURSE_EXERCISE_SNAPSHOTS_URL, STUDENT_COURSE_EXERCISE_SNAPSHOTS_JSON);
        stubGetWithJsonResponse(STUDENT_COURSE_EXERCISE_SNAPSHOT_URL, STUDENT_COURSE_EXERCISE_SNAPSHOT_JSON);
        stubGetWithJsonResponse(STUDENT_COURSE_EXERCISE_SNAPSHOT_FILES_URL, STUDENT_COURSE_EXERCISE_SNAPSHOT_FILES_JSON);
        stubGetWithJsonResponse(STUDENT_COURSE_EXERCISE_SNAPSHOT_FILE_URL, STUDENT_COURSE_EXERCISE_SNAPSHOT_FILE_JSON);
        stubGetWithPlainResponse(STUDENT_COURSE_EXERCISE_SNAPSHOT_FILE_CONTENT_URL, STUDENT_COURSE_EXERCISE_SNAPSHOT_FILE_CONTENT);
    }
}
