package io.github.wangqifox.sample;

/**
 * @author: wangqi
 * @description:
 * @date: Created in 2018/10/25 上午11:13
 */
public class CommentDTO {
    private String id;
    /**
     * 文章标题
     */
    private String articleTitle;
    /**
     * 文章id
     */
    private String articleId;
    /**
     * 文章url
     */
    private String articleUrl;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 产品id
     */
    private String projectId;
    /**
     * 类别id（最低一级的类别，频道或者栏目）
     */
    private String categoryId;
    /**
     * 评论者id
     */
    private String commentUserId;
    /**
     * 评论者名称
     */
    private String commentUserName;
    /**
     * 父评论id
     */
    private String parentCommentId;
    /**
     * 审核状态（0-待审核，1-审核通过，2-删除）
     */
    private Integer state;

    /**
     * 评论点赞数
     */
    private Integer likeCount;
    /**
     * 所在楼层数
     */
    private Integer floorNum;
    /**
     * 排序号，createdAt的时间戳
     */
    private Long sortNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(String commentUserId) {
        this.commentUserId = commentUserId;
    }

    public String getCommentUserName() {
        return commentUserName;
    }

    public void setCommentUserName(String commentUserName) {
        this.commentUserName = commentUserName;
    }

    public String getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(String parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(Integer floorNum) {
        this.floorNum = floorNum;
    }

    public Long getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(Long sortNumber) {
        this.sortNumber = sortNumber;
    }
}
