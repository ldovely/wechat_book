package ls.eclair.wechat_book.entity;

import java.util.List;

/**
 * @author lisbo
 * @description
 * @since 2017-12-7 15:22
 */


public class NewsMessage extends BaseMessage {
    private int ArticleCount;
    private List<Article> Articles;

    public int getArticleCount() {
        return ArticleCount;
    }
    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }
    public List<Article> getArticles() {
        return Articles;
    }
    public void setArticles(List<Article> articles) {
        Articles = articles;
    }
}
