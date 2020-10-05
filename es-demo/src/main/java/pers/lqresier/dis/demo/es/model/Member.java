package pers.lqresier.dis.demo.es.model;

import java.util.Date;

/**
 * Created with IDEA
 *
 * @author qiujiajin
 * @date 2020/7/21 22:57
 * Description
 */
public class Member {
    private Long id;
    private String username;
    private Date createTime;
    private String remark;
    private Date updateTime;
    private Byte isAvailable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Byte isAvailable) {
        this.isAvailable = isAvailable;
    }


    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isAvailable=" + isAvailable +
                '}';
    }

    public static final class MemberBuilder {
        private Long id;
        private String username;
        private Date createTime;
        private String remark;
        private Date updateTime;
        private Byte isAvailable;

        private MemberBuilder() {
        }

        public static MemberBuilder aMember() {
            return new MemberBuilder();
        }

        public MemberBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public MemberBuilder withUsername(String username) {
            this.username = username;
            return this;
        }

        public MemberBuilder withCreateTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        public MemberBuilder withRemark(String remark) {
            this.remark = remark;
            return this;
        }

        public MemberBuilder withUpdateTime(Date updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        public MemberBuilder withIsAvailable(Byte isAvailable) {
            this.isAvailable = isAvailable;
            return this;
        }

        public Member build() {
            Member member = new Member();
            member.setId(id);
            member.setUsername(username);
            member.setCreateTime(createTime);
            member.setRemark(remark);
            member.setUpdateTime(updateTime);
            member.setIsAvailable(isAvailable);
            return member;
        }
    }
}
