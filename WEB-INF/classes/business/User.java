/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Abhishek
 */

@Entity
@Table(name = "User")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUseremail", query = "SELECT u FROM User u WHERE u.useremail = :useremail"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findBySaltedPassword", query = "SELECT u FROM User u WHERE u.saltedPassword = :saltedPassword"),
    @NamedQuery(name = "User.findBySalt", query = "SELECT u FROM User u WHERE u.salt = :salt"),
    @NamedQuery(name = "User.findByType", query = "SELECT u FROM User u WHERE u.type = :type"),
    @NamedQuery(name = "User.findByStudies", query = "SELECT u FROM User u WHERE u.studies = :studies"),
    @NamedQuery(name = "User.findByParticipation", query = "SELECT u FROM User u WHERE u.participation = :participation"),
    @NamedQuery(name = "User.findByCoins", query = "SELECT u FROM User u WHERE u.coins = :coins")})


public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Useremail")
    private String useremail;
    @Basic(optional = false)
    @Column(name = "Username")
    private String username;
    @Basic(optional = false)
    @Column(name = "SaltedPassword")
    private String saltedPassword;
    @Basic(optional = false)
    @Column(name = "Salt")
    private String salt;
    @Basic(optional = false)
    @Column(name = "Type")
    private String type;
    @Column(name = "Studies")
    private Integer studies;
    @Column(name = "Participation")
    private Integer participation;
    @Column(name = "Coins")
    private Integer coins;

    public User() {
    }

    public User(String useremail) {
        this.useremail = useremail;
    }

    public User(String useremail, String username, String saltedPassword, String salt, String type) {
        this.useremail = useremail;
        this.username = username;
        this.saltedPassword = saltedPassword;
        this.salt = salt;
        this.type = type;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSaltedPassword() {
        return saltedPassword;
    }

    public void setSaltedPassword(String saltedPassword) {
        this.saltedPassword = saltedPassword;
    }

    public String getSalt() {
        return salt;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getType() {
        return type;
    }

    public Integer getStudies() {
        return studies;
    }

    public void setStudies(Integer studies) {
        this.studies = studies;
    }

    public Integer getParticipation() {
        return participation;
    }

    public void setParticipation(Integer participation) {
        this.participation = participation;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }

    public Integer getCoins() {
        return coins;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (useremail != null ? useremail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.useremail == null && other.useremail != null) || (this.useremail != null && !this.useremail.equals(other.useremail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "business.User[ useremail=" + useremail + " ]";
    }
    
}
