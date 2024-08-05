package vn.devpro.finaljavaweb26.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "create_date", nullable = true)
    private Date createDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "update_date", nullable = true)
    private Date updateDate;
    @Column(name = "status", nullable = true)
    private Boolean status = Boolean.TRUE;

    public BaseModel() {
    }

    public BaseModel(Integer id, Date createDate, Date updateDate, Boolean status) {
        this.id = id;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
