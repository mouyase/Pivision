package com.crawler;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
//    @Test
//    public void addition_isCorrect() throws Exception {
//        assertEquals(4, 2 + 2);
//    }
//    @Test
//    public void parse() {
//        String string = "{\"id\":56260874,\"title\":\"\\u7537\\u5b50\\u4e2d\\u5b66\\u751f\\u306e\\u304b\\u3089\\u304b\\u3044\\u3068\\u3061\\u3087\\u3063\\u3068\\u304a\\u8336\\u76ee\\u306a\\u30a8\\u30ec\\u30f3\\u30fb\\u30d9\\u30fc\\u30ab\\u30fc\\u5148\\u751f\",\"type\":\"illust\",\"image_urls\":{\"square_medium\":\"https:\\/\\/i.pximg.net\\/c\\/360x360_70\\/img-master\\/img\\/2016\\/04\\/09\\/00\\/27\\/28\\/56260874_p0_square1200.jpg\",\"medium\":\"https:\\/\\/i.pximg.net\\/c\\/540x540_70\\/img-master\\/img\\/2016\\/04\\/09\\/00\\/27\\/28\\/56260874_p0_master1200.jpg\",\"large\":\"https:\\/\\/i.pximg.net\\/c\\/600x1200_90\\/img-master\\/img\\/2016\\/04\\/09\\/00\\/27\\/28\\/56260874_p0_master1200.jpg\"},\"caption\":\"\",\"restrict\":0,\"user\":{\"id\":464,\"name\":\"FBC\",\"account\":\"bbs268\",\"profile_image_urls\":{\"medium\":\"https:\\/\\/i1.pixiv.net\\/img01\\/profile\\/bbs268\\/794460.png\"},\"is_followed\":false},\"tags\":[{\"name\":\"\\u30a8\\u30ec\\u30f3\\u30fb\\u30d9\\u30fc\\u30ab\\u30fc\"},{\"name\":\"\\u5973\\u6559\\u5e2b\"},{\"name\":\"\\u3069\\u3084\\u9854\"},{\"name\":\"\\u30a8\\u30ec\\u30f3\\u5148\\u751f\"},{\"name\":\"\\u3042\\u3056\\u3068\\u3044\"},{\"name\":\"\\u30cb\\u30e5\\u30fc\\u30db\\u30e91000users\\u5165\\u308a\"}],\"tools\":[\"CLIP STUDIO PAINT\"],\"create_date\":\"2016-04-09T00:27:28+09:00\",\"page_count\":1,\"width\":636,\"height\":900,\"sanity_level\":2,\"meta_single_page\":{\"original_image_url\":\"https:\\/\\/i3.pixiv.net\\/img-original\\/img\\/2016\\/04\\/09\\/00\\/27\\/28\\/56260874_p0.png\"},\"meta_pages\":[],\"total_view\":102161,\"total_bookmarks\":4186,\"is_bookmarked\":false,\"visible\":true}";
//        for (int i = 0; i < 3; i++) {
//            long time = System.currentTimeMillis();
//            for (int j = 0; j < 30; j++) {
//                try {
//                    toObject(i, string);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            System.out.println(System.currentTimeMillis() - time);
//        }
//    }
//
//    private Work toObject(int type, String string) throws IOException {
//        switch (type) {
//            case 0:
//                return new Gson().fromJson(string, Work.class);
//            case 1:
//                return JSON.parseObject(string, Work.class);
//            case 2:
//                return LoganSquare.parse(string, Work.class);
//        }
//        return null;
//    }
//
//    private String toStr(int type, Work work) throws IOException {
//        switch (type) {
//            case 0:
//                return new Gson().toJson(work);
//            case 1:
//                return JSON.toJSONString(work);
//            case 2:
//                return LoganSquare.serialize(work);
//        }
//        return null;
//    }
}