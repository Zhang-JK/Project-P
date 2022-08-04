package com.jk.projectp.model;

import com.jk.projectp.utils.dataenum.WebPages;

import javax.persistence.*;

@Entity
@Table(name = "role_page")
public class RolePage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(name = "page", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private WebPages page;

    @Column(name = "allow_modify", nullable = false)
    private Boolean allowModify = false;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public WebPages getPage() {
        return page;
    }

    public void setPage(WebPages page) {
        this.page = page;
    }

    public Boolean getAllowModify() {
        return allowModify;
    }

    public void setAllowModify(Boolean allowModify) {
        this.allowModify = allowModify;
    }

}