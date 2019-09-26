package com.reiya.pixiv.network;


import com.reiya.pixiv.util.RxHelper;
import com.reiya.pixiv.util.UserData;
import com.reiya.pixiv.util.Value;

import okhttp3.ResponseBody;
import rx.Observable;

import static com.reiya.pixiv.network.HttpClient.getService;

/**
 * Created by lenovo on 2016/5/24.
 */

public class NetworkRequest {

//    public static Observable<HttpService.IllustListResponse> getRanking(String type) {
//        return getService()
//                .getRanking(type)
//                .compose(RxHelper.<HttpService.IllustListResponse>getSchedulerHelper());
//    }
//
//    public static Observable<HttpService.IllustListResponse> getRankingNext(String url) {
//        return getService()
//                .getRankingNext(url)
//                .compose(RxHelper.<HttpService.IllustListResponse>getSchedulerHelper());
//    }

    private static String getBearer() {
        return UserData.getBearer();
    }

    public static Observable<HttpService.IllustListResponse> getRankingWithBearer(String mode) {
        return getService()
                .getRanking(UserData.getBearer(), mode)
                .compose(RxHelper.<HttpService.IllustListResponse>getSchedulerHelper());
    }

    public static Observable<HttpService.IllustListResponse> getRanking(String mode, String date) {
        return getService()
                .getRanking(UserData.getBearer(), mode, date)
                .compose(RxHelper.<HttpService.IllustListResponse>getSchedulerHelper());
    }

    public static Observable<HttpService.IllustListResponse> getRankingNextWithBearer(String url) {
        return getService()
                .getRankingNext(UserData.getBearer(), url)
                .compose(RxHelper.<HttpService.IllustListResponse>getSchedulerHelper());
    }

    public static Observable<HttpService.AuthResponse> getAuth(String account, String password) {
        return getService(Value.URL_AUTH)
                .getAuth(account,
                        password,
                        "password",
                        "BVO2E8vAAikgUBW8FYpi6amXOjQj",
                        "LI1WsFUDrrquaINOdarrJclCrkTtc3eojCOswlog",
                        "")
                .compose(RxHelper.<HttpService.AuthResponse>getSchedulerHelper());
    }

    public static Observable<HttpService.FollowResponse> removeFollow(int id) {
        return getService()
                .removeFollow(UserData.getBearer(), String.valueOf(id))
                .compose(RxHelper.<HttpService.FollowResponse>getSchedulerHelper());
    }

    public static Observable<HttpService.FollowResponse> addFollow(int id, String type) {
        return getService()
                .addFollow(UserData.getBearer(), String.valueOf(id), type)
                .compose(RxHelper.<HttpService.FollowResponse>getSchedulerHelper());
    }

    public static Observable<HttpService.FollowResponse> getFollow(String type) {
        return getService()
                .getFollow(UserData.getBearer(), UserData.user.getId(), type)
                .compose(RxHelper.<HttpService.FollowResponse>getSchedulerHelper());
    }

    public static Observable<HttpService.FollowResponse> getFollowNext(String url) {
        return getService()
                .getFollow(UserData.getBearer(), url)
                .compose(RxHelper.<HttpService.FollowResponse>getSchedulerHelper());
    }

    public static Observable<HttpService.IllustListResponse> removeBookmark(int id) {
        return getService()
                .removeBookmark(UserData.getBearer(), String.valueOf(id))
                .compose(RxHelper.<HttpService.IllustListResponse>getSchedulerHelper());
    }

//    https://app-api.pixiv.net/v2/illust/bookmark/detail?illust_id=59486769
//{
//    "bookmark_detail": {
//        "is_bookmarked": false,
//                "tags": [{
//            "name": "\u30b8\u30d6\u30ea",
//                    "is_registered": false
//        }, {
//            "name": "\u98a8\u306e\u8c37\u306e\u30ca\u30a6\u30b7\u30ab",
//                    "is_registered": false
//        }],
//        "restrict": "public"
//    }
//}
    public static Observable<HttpService.IllustListResponse> addBookmark(int id, String type, String... tags) {
        return getService()
                .addBookmark(UserData.getBearer(), String.valueOf(id), type, tags)
                .compose(RxHelper.<HttpService.IllustListResponse>getSchedulerHelper());
    }

    public static Observable<HttpService.IllustListResponse> getBookmark(String type, String tag) {
        return getService()
                .getBookmark(UserData.getBearer(), UserData.user.getId(), type, tag)
                .compose(RxHelper.<HttpService.IllustListResponse>getSchedulerHelper());
    }

    public static Observable<HttpService.IllustListResponse> getBookmarkNext(String url) {
        return getService()
                .getBookmarkNext(UserData.getBearer(), url)
                .compose(RxHelper.<HttpService.IllustListResponse>getSchedulerHelper());
    }

    public static Observable<HttpService.BookmarkTagsResponse> getBookmarkTags(String type) {
        return getService()
                .getBookmarkTags(UserData.getBearer(), UserData.user.getId(), type)
                .compose(RxHelper.<HttpService.BookmarkTagsResponse>getSchedulerHelper());
    }

    public static Observable<HttpService.BookmarkTagsResponse> getBookmarkTagsNext(String url) {
        return getService()
                .getBookmarkTagsNext(UserData.getBearer(), url)
                .compose(RxHelper.<HttpService.BookmarkTagsResponse>getSchedulerHelper());
    }

    public static Observable<HttpService.BookmarkDetailResponse> getBookmarkDetail(int id) {
        return getService()
                .getBookmarkDetail(UserData.getBearer(), String.valueOf(id))
                .compose(RxHelper.<HttpService.BookmarkDetailResponse>getSchedulerHelper());
    }

    public static Observable<HttpService.IllustListResponse> getHistoryRanking(String type, String date) {
        return getService()
                .getHistoryRanking(type, date)
                .compose(RxHelper.<HttpService.IllustListResponse>getSchedulerHelper());
    }

    public static Observable<HttpService.IllustListResponse> getHistoryRankingNext(String url) {
        return getService()
                .getHistoryRanking(url)
                .compose(RxHelper.<HttpService.IllustListResponse>getSchedulerHelper());
    }

    public static Observable<HttpService.UserResponse> getUserWithBearer(int id) {
        return getService()
                .getUser(UserData.getBearer(), id)
                .compose(RxHelper.<HttpService.UserResponse>getSchedulerHelper());
    }

    public static Observable<HttpService.IllustListResponse> getWorksOfAUser(int id) {
        return getService()
                .getWorksOfAUser(UserData.getBearer(), id)
                .compose(RxHelper.<HttpService.IllustListResponse>getSchedulerHelper());
    }

    public static Observable<HttpService.IllustListResponse> getWorksOfAUserNext(String url) {
        return getService()
                .getWorksOfAUser(UserData.getBearer(), url)
                .compose(RxHelper.<HttpService.IllustListResponse>getSchedulerHelper());
    }

    public static Observable<HttpService.IllustListResponse> getSearchFromPixiv(String keyword) {
        return getService()
                .getFromPixiv(getBearer(), keyword, "illust_and_ugoira", "date_desc", "partial_match_for_tags")
                .compose(RxHelper.<HttpService.IllustListResponse>getSchedulerHelper());
    }

    public static Observable<HttpService.IllustListResponse> getSearchFromPixivNext(String url) {
        return getService()
                .getFromPixiv(getBearer(), url)
                .compose(RxHelper.<HttpService.IllustListResponse>getSchedulerHelper());
    }

    public static Observable<HttpService.CommentResponse> getComment(int id) {
        return getService()
                .getComment(getBearer(), id)
                .compose(RxHelper.<HttpService.CommentResponse>getSchedulerHelper());
    }

    public static Observable<HttpService.CommentResponse> getComment(String url) {
        return getService()
                .getComment(getBearer(), url)
                .compose(RxHelper.<HttpService.CommentResponse>getSchedulerHelper());
    }

    public static Observable<HttpService.TrendTagsResponse> getTrendTags() {
        return getService()
                .getTrendTags(UserData.getBearer())
                .compose(RxHelper.<HttpService.TrendTagsResponse>getSchedulerHelper());
    }

    public static Observable<HttpService.IllustListResponse> getNewWorks() {
        return getService()
                .getNewWorks(UserData.getBearer(), "all", "illust")
                .compose(RxHelper.<HttpService.IllustListResponse>getSchedulerHelper());
    }

    public static Observable<HttpService.IllustListResponse> getNewWorks(String url) {
        return getService()
                .getNewWorks(UserData.getBearer(), url)
                .compose(RxHelper.<HttpService.IllustListResponse>getSchedulerHelper());
    }

    public static Observable<HttpService.WorkResponse> getWorkDetail(int id) {
        return getService()
                .getWorkDetail(id)
                .compose(RxHelper.<HttpService.WorkResponse>getSchedulerHelper());
    }

    public static Observable<HttpService.WorkResponse> getWorkDetailWithBearer(int id) {
        return getService()
                .getWorkDetail(UserData.getBearer(), id)
                .compose(RxHelper.<HttpService.WorkResponse>getSchedulerHelper());
    }

    public static Observable<HttpService.SpotlightResponse> getSpotlight() {
        return getService()
                .getSpotlight(getBearer(), "zh_CN")
                .compose(RxHelper.<HttpService.SpotlightResponse>getSchedulerHelper());
    }

    public static Observable<HttpService.SpotlightResponse> getSpotlightNext(String url) {
        return getService()
                .getSpotlight(getBearer(), "zh_CN", url)
                .compose(RxHelper.<HttpService.SpotlightResponse>getSchedulerHelper());
    }

    public static Observable<ResponseBody> download(String url) {
        return getService()
                .download(url, url)
                .compose(RxHelper.<ResponseBody>getSchedulerHelper());
    }

    public static Observable<HttpService.UgoiraResponse> getUgoira(int id) {
        return getService(Value.URL_UGOIRA)
                .getUgoira(Value.URL_ILLUST_PAGE + id, "webm")
                .compose(RxHelper.<HttpService.UgoiraResponse>getSchedulerHelper());
    }

    public static Observable<HttpService.IllustListResponse> getRelatedWorks(int id) {
        return getService()
                .getRelatedWorks(getBearer(), id)
                .compose(RxHelper.<HttpService.IllustListResponse>getSchedulerHelper());
    }

    public static Observable<HttpService.RecommendResponse> getRecommendWorks() {
        if (!UserData.isLoggedIn()) {
            return getService()
                    .getRecommendedWorks("for_android", true)
                    .compose(RxHelper.<HttpService.RecommendResponse>getSchedulerHelper());
        }
        return getService()
                .getRecommendedWorks(UserData.getBearer(), "for_android", true)
                .compose(RxHelper.<HttpService.RecommendResponse>getSchedulerHelper());
    }


    public static Observable<HttpService.RecommendResponse> getRecommendWorks(String url) {
        if (!UserData.isLoggedIn()) {
            return getService()
                    .getRecommendedWorks(url)
                    .compose(RxHelper.<HttpService.RecommendResponse>getSchedulerHelper());
        }
        return getService()
                .getRecommendedWorks(UserData.getBearer(), url)
                .compose(RxHelper.<HttpService.RecommendResponse>getSchedulerHelper());
    }
}
