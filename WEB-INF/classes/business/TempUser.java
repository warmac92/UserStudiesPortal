/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Abhishek
 */

@Entity
@Table(name = "TempUser")
@NamedQueries({
    @NamedQuery(name = "TempUser.findAll", query = "SELECT t FROM TempUser t"),
    @NamedQuery(name = "TempUser.findByToken", query = "SELECT t FROM TempUser t WHERE t.token = :token"),
    @NamedQuery(name = "TempUser.findByUseremail", query = "SELECT t FROM TempUser t WHERE t.useremail = :useremail"),
    @NamedQuery(name = "TempUser.findByUsername", query = "SELECT t FROM TempUser t WHERE t.username = :username"),
    @NamedQuery(name = "TempUser.findByPassword", query = "SELECT t FROM TempUser t WHERE t.password = :password"),
    @NamedQuery(name = "TempUser.findByIssueDate", query = "SELECT t FROM TempUser t WHERE t.issueDate = :issueDate"),
    @NamedQuery(name = "TempUser.findByRecommender", query = "SELECT t FROM TempUser t WHERE t.recommender = :recommender")})
public class TempUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Token")
    private String token;
    @Column(name = "Useremail")
    private String useremail;
    @Column(name = "Username")
    private String username;
    @Column(name = "Password")
    private String password;
    @Column(name = "IssueDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date issueDate;
    @Column(name = "Recommender")
    private String recommender;

    public TempUser() {
    }

    public TempUser(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public void setUsername(String username) {
        this.username = username;
    }
        
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public void setRecommender(String recommender) {
        this.recommender = recommender;
    }
        
    public String getRecommender() {
        return recommender;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (token != null ? token.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TempUser)) {
            return false;
        }
        TempUser other = (TempUser) object;
        if ((this.token == null && other.token != null) || (this.token != null && !this.token.equals(other.token))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "business.TempUser[ token=" + token + " ]";
    }
    
}
