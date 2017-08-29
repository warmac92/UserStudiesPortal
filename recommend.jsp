<%--
        Document: aboutl.jsp
        Created On: Feb 4, 2016
        Authors: Deepak Rohan, Abhishek

--%>
<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<%-- Code to display Page Name --%>
<h3 id="page_name">Recommend to a friend</h3>
<%-- Code to display Page Name --%>
<a href="main.jsp" id="back_to_page">&laquo;Back to the Main Page</a>
<%-- Section to input Contact details and Send Message --%>
<section>
    <form class="form-horizontal" action="users" method="post">
        <div class="form-group">
        <label class="col-sm-4 control-label" ></label>
            <div class="col-sm-4">
                <label><c:out value="${errorMsg}"/></label>
            </div>
        </div>
        <input type="hidden" name="action" value="recommend">
        <div class="form-group">
            <label class="col-sm-4 control-label">User Verification (email) *</label>
            <div class="col-sm-4">
                <input type="email" class="form-control" name="recommendFrom" required/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-4 control-label">Recommend To (email) *</label>
            <div class="col-sm-4">
                <input type="email" class="form-control" name="recommendTo" required/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-4 control-label">Message *</label>
            <div class="col-sm-4"> 
                <textarea name="message" class="form-control" required></textarea>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-5 col-sm-4">
                <button type="submit"  class="btn btn-primary">Submit</button>
            </div>
        </div>

    </form>
</section>

<p class="text-center">When your friend logs in and participates in one user study, you'll get 2 coins bonus</p>
<br><br><br><br>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>