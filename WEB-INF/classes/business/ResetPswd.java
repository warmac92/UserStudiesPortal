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
@Table(name = "ResetPswd")
@NamedQueries({
    @NamedQuery(name = "ResetPswd.findAll", query = "SELECT r FROM ResetPswd r"),
    @NamedQuery(name = "ResetPswd.findByToken", query = "SELECT r FROM ResetPswd r WHERE r.token = :token"),
    @NamedQuery(name = "ResetPswd.findByUseremail", query = "SELECT r FROM ResetPswd r WHERE r.useremail = :useremail"),
    @NamedQuery(name = "ResetPswd.findByIssueDate", query = "SELECT r FROM ResetPswd r WHERE r.issueDate = :issueDate"),
    @NamedQuery(name = "ResetPswd.findByExpirationDate", query = "SELECT r FROM ResetPswd r WHERE r.expirationDate = :expirationDate")})
public class ResetPswd implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Token")
    private String token;
    @Column(name = "Useremail")
    private String useremail;
    @Column(name = "IssueDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date issueDate;
    @Column(name = "ExpirationDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDate;

    public ResetPswd() {
    }

    public ResetPswd(String token) {
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

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
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
        if (!(object instanceof ResetPswd)) {
            return false;
        }
        ResetPswd other = (ResetPswd) object;
        if ((this.token == null && other.token != null) || (this.token != null && !this.token.equals(other.token))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "business.ResetPswd[ token=" + token + " ]";
    }
    
}
