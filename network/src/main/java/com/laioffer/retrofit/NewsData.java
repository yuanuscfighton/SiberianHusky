package com.laioffer.retrofit;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.List;

/**
 * 类的描述: 第2步：创建 服务端返回的数据类
 * Created by 春夏秋冬在中南 on 2023/9/24 20:56
 */
public class NewsData implements Serializable {
  private static final long serialVersionUID = 2901111593401212409L;
  /**
   * code : 200
   * msg : success
   * result : {"curpage":1,"allnum":500,"list":[{"id":"cfd4b1b056a40d2fb199b94b81630fa5","ctime":"2021-02-04 13:57","title":"绿媒欢呼在圭亚外长泼冷水","description":"中华国内","source":"中华国内","picUrl":"https://img2.utuku.imgcdc.com/300x200/news/20210204/f91b682d-f1f2-4fee-95a9-1c92d39634a7.png","url":"https://news.china.com/domestic/945/20210204/39259849.html"},{"id":"9156081c67ce5c54741d6dab41f7644f","ctime":"2021-02-04 14:34","title":"三天两头来偷窥！美军MQ-4C无人机被爆3天内两次进入南海","description":"中华国内","source":"中华国内","picUrl":"https://img2.utuku.imgcdc.com/300x200/news/20210204/f673da0c-6aec-4cc5-8cca-301940ecec01.jpg","url":"https://news.china.com/domestic/945/20210204/39260149.html"},{"id":"ec8746e42feb713a1bb63023076b00f5","ctime":"2021-02-04 13:30","title":"第一次！中国军官指挥英国军人执行任务","description":"中华国内","source":"中华国内","picUrl":"https://img3.utuku.imgcdc.com/300x200/news/20210204/9d8b2d5c-c392-4983-abaa-fb5cb72c6d59.jpg","url":"https://news.china.com/domestic/945/20210204/392596181.html"}]}
   */

  private int code;

  private String msg;

  private ResultBean result;

  @NonNull
  @Override
  public String toString() {
    return "NewsData{" +
        "code=" + code +
        ", msg='" + msg + '\'' +
        ", result=" + result +
        '}';
  }

  public static class ResultBean implements Serializable {
    private static final long serialVersionUID = 6153322961875412631L;

    /**
     * curpage : 1
     * allnum : 500
     * list : [{"id":"cfd4b1b056a40d2fb199b94b81630fa5","ctime":"2021-02-04 13:57","title":"绿媒欢呼在圭亚外长泼冷水","description":"中华国内","source":"中华国内","picUrl":"https://img2.utuku.imgcdc.com/300x200/news/20210204/f91b682d-f1f2-4fee-95a9-1c92d39634a7.png","url":"https://news.china.com/domestic/945/20210204/39259849.html"},{"id":"9156081c67ce5c54741d6dab41f7644f","ctime":"2021-02-04 14:34","title":"三天两头来偷窥！美军MQ-4C无人机被爆3天内两次进入南海","description":"中华国内","source":"中华国内","picUrl":"https://img2.utuku.imgcdc.com/300x200/news/20210204/f673da0c-6aec-4cc5-8cca-301940ecec01.jpg","url":"https://news.china.com/domestic/945/20210204/39260149.html"},{"id":"ec8746e42feb713a1bb63023076b00f5","ctime":"2021-02-04 13:30","title":"第一次！中国军官指挥英国军人执行任务","description":"中华国内","source":"中华国内","picUrl":"https://img3.utuku.imgcdc.com/300x200/news/20210204/9d8b2d5c-c392-4983-abaa-fb5cb72c6d59.jpg","url":"https://news.china.com/domestic/945/20210204/392596181.html"}]
     */

    private int curpage;

    private int allnum;

    private List<Item> list;

    @NonNull
    @Override
    public String toString() {
      return "ResultBean{" +
          "curpage=" + curpage +
          ", allnum=" + allnum +
          ", list=" + list +
          '}';
    }

    public static class Item implements Serializable {

      private static final long serialVersionUID = 6841678634566993457L;

      /**
       * id : cfd4b1b056a40d2fb199b94b81630fa5
       * ctime : 2021-02-04 13:57
       * title : 绿媒欢呼在圭亚外长泼冷水
       * description : 中华国内
       * source : 中华国内
       * picUrl : https://img2.utuku.imgcdc.com/300x200/news/20210204/f91b682d-f1f2-4fee-95a9-1c92d39634a7.png
       * url : https://news.china.com/domestic/945/20210204/39259849.html
       */

      private String id;

      private String ctime;

      private String title;

      private String description;

      private String source;

      private String picUrl;

      private String url;


      @NonNull
      @Override
      public String toString() {
        return "Item{" +
            "id='" + id + '\'' +
            ", ctime='" + ctime + '\'' +
            ", title='" + title + '\'' +
            ", description='" + description + '\'' +
            ", source='" + source + '\'' +
            ", picUrl='" + picUrl + '\'' +
            ", url='" + url + '\'' +
            '}';
      }
    }
  }
}
