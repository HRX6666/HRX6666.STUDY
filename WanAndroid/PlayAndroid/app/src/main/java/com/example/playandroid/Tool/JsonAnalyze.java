package com.example.playandroid.Tool;




import android.util.Log;

import com.example.playandroid.Data.CoinData;
import com.example.playandroid.Data.CollectData;
import com.example.playandroid.Data.ErrorMsgData;
import com.example.playandroid.Data.MessageData;
import com.example.playandroid.Data.SubilsData;
import com.example.playandroid.Data.TreeData;
import com.example.playandroid.Data.UsefulData;
import com.example.playandroid.Data.WebData;
import com.example.playandroid.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class JsonAnalyze {

    public void JsonDataGet_web(String jsonData, List<WebData> list) {
        try {
            Log.e("json解析", "常用网站");
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray_Data = jsonObject.getJSONArray("data");

            for (int i = 0; i < jsonData.length(); i++) {
                JSONObject jsonObjectk = jsonArray_Data.getJSONObject(i);
                String link = jsonObjectk.getString("link");
                String name = jsonObjectk.getString("name");
                String category = jsonObjectk.getString("category");
                list.add(new WebData(category, link, name));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void JsonDataGet_article(String jsonData, List<UsefulData> list) {
        try {
            Log.e("json解析", "文章");
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject jsonObjectData = jsonObject.getJSONObject("data");
            JSONArray jsonArray_Datas = jsonObjectData.getJSONArray("datas");

            for (int i = 0; i < jsonData.length(); i++) {
                JSONObject jsonObjectk = jsonArray_Datas.getJSONObject(i);
                String title = jsonObjectk.getString("title");
                String niceDate = jsonObjectk.getString("niceDate");
                String link = jsonObjectk.getString("link");
                String shareUser = jsonObjectk.getString("shareUser");
                String desc = jsonObjectk.getString("desc");
                String author = jsonObjectk.getString("author");
                String chapterName = jsonObjectk.getString("chapterName");
                String superChapterName = jsonObjectk.getString("superChapterName");
                String projectLink = jsonObjectk.getString("projectLink");
                int id = jsonObjectk.getInt("id");
                int userId = jsonObjectk.getInt("userId");
                Boolean fresh = jsonObjectk.getBoolean("fresh");
                Boolean collec = jsonObjectk.getBoolean("collect");
                int collect ;
                if (collec){
                    collect = R.drawable.like;
                }else {
                    collect = R.drawable.unselect;
                }
                list.add(new UsefulData(title, niceDate, link, shareUser, desc, author, chapterName, superChapterName, projectLink, id, null, null, true, userId,fresh,collect));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void JsonDataGet_top_article(String jsonData, List<UsefulData> list) {
        try {
            Log.e("json解析", "文章");
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray_Datas = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonData.length(); i++) {
                JSONObject jsonObjectk = jsonArray_Datas.getJSONObject(i);
                String title = jsonObjectk.getString("title");
                String niceDate = jsonObjectk.getString("niceDate");
                String link = jsonObjectk.getString("link");
                String shareUser = jsonObjectk.getString("shareUser");
                String desc = jsonObjectk.getString("desc");
                String author = jsonObjectk.getString("author");
                String chapterName = jsonObjectk.getString("chapterName");
                String superChapterName = jsonObjectk.getString("superChapterName");
                String projectLink = jsonObjectk.getString("projectLink");
                int id = jsonObjectk.getInt("id");
                int userId = jsonObjectk.getInt("userId");
                Boolean fresh = jsonObjectk.getBoolean("fresh");
                Boolean collec = jsonObjectk.getBoolean("collect");
                int collect ;
                if (collec){
                    collect = R.drawable.like;
                }else {
                    collect = R.drawable.unselect;
                }
                list.add(new UsefulData(title, niceDate, link, shareUser, desc, author, chapterName, superChapterName, projectLink, id, "置顶   ", null, true, userId,fresh,collect));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void JsonDataGet_collect(String jsonData, List<CollectData> list) {
        try {
            Log.e("json解析", "collect文章");
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject jsonObjectData = jsonObject.getJSONObject("data");
            JSONArray jsonArray_Datas = jsonObjectData.getJSONArray("datas");
            for (int i = 0; i < jsonData.length(); i++) {
                JSONObject jsonObjectk = jsonArray_Datas.getJSONObject(i);
                String title = jsonObjectk.getString("title");
                String niceDate = jsonObjectk.getString("niceDate");
                String link = jsonObjectk.getString("link");
                String desc = jsonObjectk.getString("desc");
                String chapterName = jsonObjectk.getString("chapterName");
                int id = jsonObjectk.getInt("id");
                int originId = jsonObjectk.getInt("originId");
                list.add(new CollectData(title, niceDate, link, desc, chapterName, null, id,originId));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void JsonDataGet_project_tree(String jsonData, List<TreeData> list) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray_Data = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonData.length(); i++) {
                JSONObject jsonObjectk = jsonArray_Data.getJSONObject(i);
                int id = jsonObjectk.getInt("id");
                String name = jsonObjectk.getString("name");
                list.add(new TreeData(id, name));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void JsonDataGet_knowledge_tree(String jsonData, List<TreeData> list) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray_Data = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonData.length(); i++) {
                JSONObject jsonObjectk = jsonArray_Data.getJSONObject(i);
                int id = jsonObjectk.getInt("id");
                String name = jsonObjectk.getString("name");
                list.add(new TreeData(id, name));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void JsonDataGet_knowledge_tree_item(String jsonData, List<TreeData> list, String key) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray_Data = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonData.length(); i++) {
                JSONObject jsonObjectk = jsonArray_Data.getJSONObject(i);
                String name = jsonObjectk.getString("name");
                if (name.equals(key)) {
                    JSONArray jsonArray_children = jsonObjectk.getJSONArray("children");
                    for (int k = 0; k < jsonArray_children.length(); k++) {
                        JSONObject jsonObject2 = jsonArray_children.getJSONObject(k);
                        int id = jsonObject2.getInt("id");
                        String mname = jsonObject2.getString("name");
                        list.add(new TreeData(id, mname));
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void JsonDataGet_project_list(String jsonData, List<UsefulData> list) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject jsonObjectData = jsonObject.getJSONObject("data");
            JSONArray jsonArray_Datas = jsonObjectData.getJSONArray("datas");

            for (int i = 0; i < jsonData.length(); i++) {
                JSONObject jsonObjectk = jsonArray_Datas.getJSONObject(i);
                String title = jsonObjectk.getString("title");
                String niceDate = jsonObjectk.getString("niceDate");
                String link = jsonObjectk.getString("link");
                String shareUser = jsonObjectk.getString("shareUser");
                String desc = jsonObjectk.getString("desc");
                String author = jsonObjectk.getString("author");
                String chapterName = jsonObjectk.getString("chapterName");
                String superChapterName = jsonObjectk.getString("superChapterName");
                String projectLink = jsonObjectk.getString("projectLink");
                String envelopePic = jsonObjectk.getString("envelopePic");
                int id = jsonObjectk.getInt("id");
                int userId = jsonObjectk.getInt("userId");
                Boolean fresh = jsonObjectk.getBoolean("fresh");
                Boolean collec = jsonObjectk.getBoolean("collect");
                int collect ;
                if (collec){
                    collect = R.drawable.like;
                }else {
                    collect = R.drawable.unselect;
                }
                list.add(new UsefulData(title, niceDate, link, shareUser, desc, author, chapterName, superChapterName, projectLink, id, null, envelopePic, true, userId,fresh,collect));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void JsonDataGet_hotkey(String jsonData, List<TreeData> list) {
        try {
            Log.e("hotkey", "hot_key");
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray_Data = jsonObject.getJSONArray("data");

            for (int i = 0; i < jsonData.length(); i++) {
                JSONObject jsonObjectk = jsonArray_Data.getJSONObject(i);
                String name = jsonObjectk.getString("name");
                String id = jsonObjectk.getString("id");
                int iid = Integer.parseInt(id);
                list.add(new TreeData(iid, name));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void JsonDataGet_shareUser_list(String jsonData, List<UsefulData> list) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject jsonObjectData = jsonObject.getJSONObject("data");

            JSONObject shareArticles = jsonObjectData.getJSONObject("shareArticles");
            JSONArray jsonArray_Datas = shareArticles.getJSONArray("datas");
            for (int i = 0; i < jsonData.length(); i++) {
                JSONObject jsonObjectk = jsonArray_Datas.getJSONObject(i);
                String title = jsonObjectk.getString("title");
                String niceDate = jsonObjectk.getString("niceDate");
                String link = jsonObjectk.getString("link");
                String shareUser = jsonObjectk.getString("shareUser");
                String desc = jsonObjectk.getString("desc");
                String author = jsonObjectk.getString("author");
                String chapterName = jsonObjectk.getString("chapterName");
                String superChapterName = jsonObjectk.getString("superChapterName");
                String projectLink = jsonObjectk.getString("projectLink");
                String envelopePic = jsonObjectk.getString("envelopePic");
                int id = jsonObjectk.getInt("id");
                int userId = jsonObjectk.getInt("userId");
                Boolean fresh = jsonObjectk.getBoolean("fresh");
                Boolean collec = jsonObjectk.getBoolean("collect");
                int collect ;
                if (collec){
                    collect = R.drawable.like;
                }else {
                    collect = R.drawable.unselect;
                }
                list.add(new UsefulData(title, niceDate, link, shareUser, desc, author, chapterName, superChapterName, projectLink, id, null, envelopePic, true, userId,fresh,collect));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void JsonDataGet_shareUser_data(String jsonData,List<String> list1,List<Integer> list2) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject jsonObjectData = jsonObject.getJSONObject("data");
            JSONObject coinInfo = jsonObjectData.getJSONObject("coinInfo");

            String nickname = coinInfo.getString("nickname");
            Log.e("name",nickname);
            String rank = coinInfo.getString("rank");
            list1.add(nickname);
            list1.add(rank);
            int coinCount = coinInfo.getInt("coinCount");
            int level = coinInfo.getInt("level");
            list2.add(coinCount);
            list2.add(level);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void JsonDataGet_my_coin(String jsonData, List<CoinData> list) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject jsonObjectData = jsonObject.getJSONObject("data");
            JSONArray jsonArray_Datas = jsonObjectData.getJSONArray("datas");

            for (int i = 0; i < jsonData.length(); i++) {
                JSONObject jsonObjectk = jsonArray_Datas.getJSONObject(i);
                String desc = jsonObjectk.getString("desc");
                String reason= jsonObjectk.getString("reason");

                list.add(new CoinData(desc,reason,null,null,null,null)  );
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void JsonDataGet_coin_rank(String jsonData, List<CoinData> list) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject jsonObjectData = jsonObject.getJSONObject("data");
            JSONArray jsonArray_Datas = jsonObjectData.getJSONArray("datas");

            for (int i = 0; i < jsonData.length(); i++) {
                JSONObject jsonObjectk = jsonArray_Datas.getJSONObject(i);
                String username = jsonObjectk.getString("username");
                String coinCount =String.valueOf(jsonObjectk.getInt("coinCount")) ;
                String level=String.valueOf(jsonObjectk.getInt("level")) ;
                String rank=String.valueOf(jsonObjectk.getInt("rank")) ;
                list.add(new CoinData(null,null,coinCount,level,username,rank));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void JsonDataGet_collect_web(String jsonData, List<WebData> list) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray_Data = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonData.length(); i++) {
                JSONObject jsonObjectk = jsonArray_Data.getJSONObject(i);
                String link = jsonObjectk.getString("link");
                String name = jsonObjectk.getString("name");
                list.add(new WebData(null,link,name));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void JsonDataGet_share_web(String jsonData, ErrorMsgData errorMsgData) {
        try {
            Log.e("webpost","entry");
            JSONObject jsonObject = new JSONObject(jsonData);
            errorMsgData.setErrorMsg(jsonObject.getString("errorMsg")) ;
            errorMsgData.setErrorCode(jsonObject.getInt("errorCode"));
            Log.e("errorCode", String.valueOf(errorMsgData.getErrorCode()));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void JsonDataGet_subils_tree(String jsonData, List<SubilsData> list) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray_Data = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonData.length(); i++) {
                JSONObject jsonObjectk = jsonArray_Data.getJSONObject(i);
                int id = jsonObjectk.getInt("id");
                String name = jsonObjectk.getString("name");
                String cover = jsonObjectk.getString("cover");
                String desc = jsonObjectk.getString("desc");
                String author = jsonObjectk.getString("author");
                //String link = jsonObjectk.getString("link");,link
                list.add(new SubilsData(id,name,cover,desc,author));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void JsonDataGet_msg(String jsonData, List<MessageData> list) {
//        this.title = title;
//        this.niceDate = niceDate;
//        this.link = link;
//        this.desc = desc;
//        this.fromUser = fromUser;
//        this.message = message;
//        this.tag = tag;
//        this.id = id;
//        this.originId = originId;
        try {
            Log.e("json解析", "消息");
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject jsonObjectData = jsonObject.getJSONObject("data");
            JSONArray jsonArray_Datas = jsonObjectData.getJSONArray("datas");
            for (int i = 0; i < jsonData.length(); i++) {
                JSONObject jsonObjectk = jsonArray_Datas.getJSONObject(i);
                String title = jsonObjectk.getString("title");
                String niceDate = jsonObjectk.getString("niceDate");
                String link = jsonObjectk.getString("link");
                String fromUser=jsonObject.getString("fromUser");
                String message=jsonObject.getString("message");
                String tag=jsonObject.getString("tag");
                String desc = jsonObjectk.getString("desc");
                int id = jsonObjectk.getInt("id");
                int originId = jsonObjectk.getInt("originId");
                list.add(new MessageData(title, niceDate, link, desc, fromUser,message,tag, id,originId));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
