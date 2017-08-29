<%-- Include tag is used to import header page --%>
<%@include file="header.jsp" %>
<%-- Section to input login details --%>
<br/>
<%-- Code to create login form--%>
<form class="form-horizontal" action="users" method="post">
    <div class="form-group">
        <label class="col-sm-4 control-label" ></label>
        <div class="col-sm-4">
            <label>Dear User: ${userEmail}</label>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-4 control-label" ></label>
        <div class="col-sm-4">
            <label><c:out value="${resetPswdErrorMsg}"/></label>
        </div>
    </div>
    <input type="hidden" name="action" value="updatePswd">
    <input type="hidden" name="token" value="${token}">
    <div class="form-group">
        <label class="col-sm-4 control-label" >New Password *</label>
        <div class="col-sm-4">
            <input type="password"  class="form-control" name="password" required/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-4 control-label" >Confirm Password *</label>
        <div class="col-sm-4">
            <input class="form-control" type="password" name="confirm_password" required/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-4 col-sm-10">
            <input type="submit" value="Submit" class="btn btn-primary" >
        </div>
    </div>
</form>
<br/>
<br/>
<br/>

<%-- Include tag is used to import footer page --%>
<%@include file="footer.jsp" %>